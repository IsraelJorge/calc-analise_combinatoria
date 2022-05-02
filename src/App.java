import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.JRadioButton;

public class App extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    JLabel lElementos, lAgrupamentos, lRes;
    JTextField tfElementos, tfAgrupamentos, tfRes;
    JButton bCalcular, bLimpar, bSair;
    JRadioButton rbPermutacao, rbArranjo, rbCombinacao;
    ButtonGroup buttonGroup;
    JCheckBox cbListar;
    JTextArea taListar;
    Formulas formula = new Formulas();

    public App() {
        setLayout(new FlowLayout());
        setSize(500, 500);
        setTitle("Análise Combinatória");
        setLocationRelativeTo(null); //Centraliza a janela no meio da tela
        setVisible(true);

        JPanel painel = new JPanel();
        painel.setPreferredSize(new Dimension(475, 500));
        add(painel);

        lElementos = new JLabel("Digite o número de elementos do conjunto : n =");
        painel.add(lElementos);

        tfElementos = new JTextField(5);
        tfElementos.setDocument(new InputSoNumeros());
        painel.add(tfElementos);

        lAgrupamentos = new JLabel("Digite o número de agrupamentos do conjunto : k =");
        painel.add(lAgrupamentos);

        tfAgrupamentos = new JTextField(5);
        tfAgrupamentos.setDocument(new InputSoNumeros());
        painel.add(tfAgrupamentos);

        rbCombinacao = new JRadioButton("Combinação simples");
        rbCombinacao.setSelected(true); // Para o Rádio button já vir selecionado.
        rbCombinacao.addActionListener(this);
        painel.add(rbCombinacao);

        rbArranjo = new JRadioButton("Arranjo simples");
        rbArranjo.addActionListener(this);
        painel.add(rbArranjo);

        rbPermutacao = new JRadioButton("Permutações Simples");
        rbPermutacao.addActionListener(this);
        painel.add(rbPermutacao);

        buttonGroup = new ButtonGroup();//Determina que os rádios buttons fazem parte de um mesmo"Grupo"
        buttonGroup.add(rbPermutacao);
        buttonGroup.add(rbArranjo);
        buttonGroup.add(rbCombinacao);

        lRes = new JLabel("Resultado:");
        painel.add(lRes);
        tfRes = new JTextField(8);
        tfRes.setEditable(false);
        painel.add(tfRes);

        taListar = new JTextArea(10, 30);
        taListar.setEditable(false);
        painel.add(taListar);

        bCalcular = new JButton("Calcular");
        bCalcular.addActionListener(this);
        painel.add(bCalcular);

        bLimpar = new JButton("Limpar");
        bLimpar.addActionListener(this);
        painel.add(bLimpar);

        bSair = new JButton("Sair");
        bSair.addActionListener(this);
        painel.add(bSair);

    }

    public static void main(String[] args) {
        App calc = new App();
        calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (rbPermutacao.isSelected()) {
            lAgrupamentos.setVisible(false);
            tfAgrupamentos.setVisible(false);

            if (ae.getSource() == bCalcular) {
                if (tfElementos.getText().length() > 0) {
                    int n = Integer.parseInt(tfElementos.getText());

                    double resultado = formula.permutacao(n);
                    String res = String.format("%.0f", resultado); // formata o resultado em String e tira o ponto.
                    tfRes.setText(res);

                    taListar.setText(taListar.getText() + "P = " + res + "\n");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Campo Vazio.", 
                            "Digite um valor inteiro.",
                            JOptionPane.INFORMATION_MESSAGE);
                }

                limparInputs();
            }
        }

        if (rbArranjo.isSelected()) {
            lAgrupamentos.setVisible(true);
            tfAgrupamentos.setVisible(true);
            if (ae.getSource() == bCalcular) {
                if (tfElementos.getText().length() > 0 && tfAgrupamentos.getText().length() > 0) {
                    int n = Integer.parseInt(tfElementos.getText());
                    int k = Integer.parseInt(tfAgrupamentos.getText());

                    if (n >= k) {

                        double resultado = formula.arranjo(n, k);
                        String res = String.format("%.0f", resultado);

                        tfRes.setText(res);
                        taListar.setText(taListar.getText() + "A = " + res + "\n");

                    } else {
                        JOptionPane.showMessageDialog(null,
                            "O número de Agrupamentos não pode ser maior que o de Elementos.", "ERRO!!!.",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Campo Vazio.", 
                            "Digite um valor inteiro.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                
                limparInputs();

            }
        }

        if (rbCombinacao.isSelected()) {
            lAgrupamentos.setVisible(true);
            tfAgrupamentos.setVisible(true);

            if (ae.getSource() == bCalcular) {
                if (tfElementos.getText().length() > 0 && tfAgrupamentos.getText().length() > 0) {
                    int n = Integer.parseInt(tfElementos.getText());
                    int k = Integer.parseInt(tfAgrupamentos.getText());

                    if (n >= k) {

                        double resultado = formula.combinacao(n, k);
                        String res = String.format("%.0f", resultado);

                        tfRes.setText(res);
                        taListar.setText(taListar.getText() + "C = " + res + "\n");

                    } else {
                        JOptionPane.showMessageDialog(null,
                            "O número de Agrupamentos não pode ser maior que o de Elementos.", "ERRO!!!.",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Campo Vazio.", 
                            "Digite um valor inteiro.",
                            JOptionPane.INFORMATION_MESSAGE);
                }

                limparInputs();
            }
        }

        if (ae.getSource() == bLimpar) {

            limparInputs();
            tfRes.setText("");
            taListar.setText("");

            
        }

        if (ae.getSource() == bSair) {
            System.exit(0);
        }

    }

    public void limparInputs(){
        tfElementos.setText("");
        tfAgrupamentos.setText("");
        tfElementos.requestFocus();
    }
}



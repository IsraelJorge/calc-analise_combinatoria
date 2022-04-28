public class Formulas {
    
    public double permutacao(int n) {
        double fat = 1;

        for (int i = n; i > 0; i--) {
            fat *= i;
        }

        return fat;
    }

    public double arranjo(int n, int k) {
        double fat = 1;
        double fat2 = 1;
        int m = n - k;

        for (int i = n; i > 0; i--) {
            fat = fat * i;
        }

        for (int i = m; i > 0; i--) {
            fat2 *= i;
        }

        double arranjo = fat / fat2;

        return arranjo;
    }

    public double combinacao(int n, int k) {
        double fat = 1;
        double fat2 = 1;
        double fat3 = 1;
        int m = n - k;

        for (int i = n; i > 0; i--) {
            fat = fat * i;
        }

        for (int i = m; i > 0; i--) {
            fat2 *= i;
        }

        for (int i = k; i > 0; i--) {
            fat3 *= i;
        }

        double combinacao = fat / (fat3 * fat2);

        return combinacao;
    }
}
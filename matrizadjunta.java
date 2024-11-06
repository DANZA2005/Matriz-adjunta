import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class matrizadjunta {

    public static BufferedReader bufer = new BufferedReader(new InputStreamReader(System.in));
    public static String entrada;

    public static int[][] llenarDatos() throws IOException {

        int r, c;
        System.out.println("Dame el número de renglones:");
        entrada = bufer.readLine();
        r = Integer.parseInt(entrada);
        System.out.println("Dame el número de columnas:");
        entrada = bufer.readLine();
        c = Integer.parseInt(entrada);

        if (r != c) {
            System.out.println("La matriz no es cuadrada papu");
            return null;
        }

        int[][] m = new int[r][c];
        System.out.println("Ingresa los elementos de la matriz:");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print("Elemento [" + i + "][" + j + "]: ");
                entrada = bufer.readLine();
                m[i][j] = Integer.parseInt(entrada);
            }
        }
        return m;
    }

    public static int[][] calcularAdjunta(int[][] matriz) {
        int n = matriz.length;
        int[][] adjunta = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int[][] menor = obtenerMenor(matriz, i, j);
                adjunta[i][j] = (int) Math.pow(-1, i + j) * calcularDeterminante(menor);
            }
        }

        return transponerMatriz(adjunta);
    }

    public static int[][] obtenerMenor(int[][] matriz, int fila, int columna) {
        int n = matriz.length;
        int[][] menor = new int[n - 1][n - 1];
        int filaMenor = 0, columnaMenor = 0;

        for (int i = 0; i < n; i++) {
            if (i == fila)
                continue;
            columnaMenor = 0;
            for (int j = 0; j < n; j++) {
                if (j == columna)
                    continue;
                menor[filaMenor][columnaMenor++] = matriz[i][j];
            }
            filaMenor++;
        }

        return menor;
    }

    public static int calcularDeterminante(int[][] matriz) {
        int n = matriz.length;
        if (n == 1)
            return matriz[0][0];
        if (n == 2)
            return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];

        int determinante = 0;
        for (int j = 0; j < n; j++) {
            int[][] menor = obtenerMenor(matriz, 0, j);
            determinante += Math.pow(1, j) * matriz[0][j] * calcularDeterminante(menor);
        }

        return determinante;
    }

    public static int[][] transponerMatriz(int[][] matriz) {
        int n = matriz.length;
        int[][] transpuesta = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                transpuesta[j][i] = matriz[i][j];
            }
        }

        return transpuesta;
    }

    public static void imprimirMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int resultado : fila) {
                System.out.print(resultado + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        int[][] matriz = llenarDatos();

        if (matriz != null) {
            int[][] adjunta = calcularAdjunta(matriz);
            System.out.println("Matriz Original:");
            imprimirMatriz(matriz);
            System.out.println("\nMatriz Adjunta:");
            imprimirMatriz(adjunta);
        }
    }

}
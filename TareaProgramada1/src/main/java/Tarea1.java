import java.util.Scanner;

/**
 * @author David Gonzlez Villanueva C13388
 * @author Anthony Navarro Brenes C15479
 */

public class Tarea {

    /**
     * Gets data from standard input.
     */
    private Scanner input = null;

    /**
     * Start the execution of the solution.
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        Tarea solution = new Tarea();
        solution.run();
    }
    /**
     * Run the solution. This method is called from main()
     */
    public void run() {
        System.out.println();
        this.input = new Scanner(System.in);

        String entrada = this.input.nextLine();

        if (entradaInvalida(entrada) >= 1) {
            System.out.println("entrada invalida");

        } else {
            String[] y = entrada.split(" ");

            int nRows = Integer.parseInt(y[0]);
            int nCols = Integer.parseInt(y[1]);
            int cajas = Integer.parseInt(y[2]);

            char[][] matriz = new char[nRows][nCols];

            this.input.useDelimiter("[\\s]*");
            for (int row = 0; row < nRows; row++) {
                for (int col = 0; col < nCols; col++) {
                    matriz[row][col] = this.input.next().charAt(0);
                }
            }

            //si la matriz contiene algun valor
            // invalido para el tablero no pasara este filtro
            if (matrizValida(matriz, nRows, nCols) >= 1) {
                System.out.println("matriz invalida");

            } else {

                // para saber si la partida termino o no
                victoria(matriz, nRows, nCols);

                //para saber donde se escuentran las cajas
                cajas(matriz, nRows, nCols);

                // encuentra las cajas bloqueadas
                cajasBloqueadas(matriz, nRows, nCols);

                //para ver si se puede mover
                movimientosValidos(matriz, nRows, nCols);

            }
        }
    }

    /**
     * este metodo recibe la entrada para analizar
     * si alguna entrada no es valida, como ejemplo una letra.
     * @param entrada
     * @return retorna el valor del int valido, si este
     * valor es mayor o igual a 1 significa que hay
     * una entrada que no es valida.
     */

    public int entradaInvalida(final String entrada) {

        String[] y = entrada.split(" ");
        int valido = 0;

        for (int i = 0; i < y.length; i++) {

            int num;
            try {
                num = Integer.parseInt(y[i]);
            } catch (Exception e) {
                ++valido;
            }

        }
        return valido;
    }

    /**
     * este metodo recibe la matriz y busca por
     * algun caracter que no sea valido.
     * @param matriz
     * @param nRows
     * @param nCol
     * @return este metodo retorna el int invalida,
     * la cual si es mayor o igual a 1 significa
     * que hay elementos en el tablero(matriz), que no son validos.
     */
    public int matrizValida(final char[][] matriz, final int nRows, final int nCol) {

        char cajacon = 'X';
        char cajasin = 'O';
        char pared = '#';
        char box = '*';
        char pers = '@';
        char piso = '.';

        int invalida = 0;

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCol; j++) {

                if (matriz[i][j] != pers
                        && matriz[i][j] != cajacon
                        && matriz[i][j] != cajasin
                        && matriz[i][j] != pared
                        && matriz[i][j] != box
                        && matriz[i][j] != piso) {

                    ++invalida;
                    break;
                }
            }
        }

        return invalida;
    }

    /**
     * este metodo revisa si hay algun agujero en
     * el tablero ("O"), si hay alguno significa
     * que la partida no ha terminado y si no
     * encuentra alguno significa que la partida termino.
     * @param matriz
     * @param nRows
     * @param nCol
     */
    public void victoria(final char[][] matriz, final int nRows, final int nCol) {

        char cajasin = 'O';
        int victory = 0;

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCol; j++) {

                if (matriz[i][j] == cajasin) {

                    ++victory;
                }
            }
        }
        if (victory >= 1) {

            System.out.println("           Victoria: No");
        }
        if (victory == 0) {

            System.out.println("           Victoria: Si");
        }

    }


    /**
     * este metodo busca en la matriz por cajas,
     * ("*" o "X") e imprime su ubicacion.
     * @param matriz
     * @param nRows
     * @param nCol
     */
    public void cajas(final char[][] matriz, final int nRows, final int nCol) {

        char box = '*';
        char cajacon = 'X';

        System.out.print("              Cajas: ");
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCol; j++) {

                if (matriz[i][j] == cajacon) {
                    System.out.print("r");
                    System.out.format("%02d", i);
                    System.out.print("c");
                    System.out.format("%02d", j);
                    System.out.print("* ");
                }
                if (matriz[i][j] == box)  {
                    System.out.print("r");
                    System.out.format("%02d", i);
                    System.out.print("c");
                    System.out.format("%02d", j);
                    System.out.print(" ");
                }
            }
        }
        System.out.println();

    }
    /**
     * este metodo revisa los objetos alrededor del personaje
     * y revisa si puede moverse, en caso de
     * encontrarse una caja revisara si puede mover la caja o no.
     * @param matriz
     * @param nRows
     * @param nCol
     */
    public void movimientosValidos(final char[][] matriz, final int nRows, final int nCol) {
        char cajacon = 'X';
        char cajasin = 'O';
        char pared = '#';
        char box = '*';
        char pers = '@';
        char piso = '.';
        System.out.print("Movimientos vÃ¡lidos: ");

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCol; j++) {
                if (matriz[i][j] == pers) {
                    //norte
                    if (matriz[i - 1][j] == piso) {
                        System.out.print("N:");
                        System.out.print("r");
                        System.out.format("%02d", i - 1);
                        System.out.print("c");
                        System.out.format("%02d", j);
                        System.out.print(" ");
                    }
                    if (matriz[i - 1][j] == cajasin) {
                        System.out.print("N:");
                        System.out.print("r");
                        System.out.format("%02d", i - 1);
                        System.out.print("c");
                        System.out.format("%02d", j);
                        System.out.print(" ");
                    }
                    if (matriz[i - 1][j] == pared) {
                        System.out.print("N:- ");
                    }
                    if (matriz[i - 1][j] == box
                            || matriz[i - 1][j] == cajacon) {
                        if (matriz[i - 2][j] == box
                                || matriz[i - 2][j] == cajacon
                                || matriz[i - 2][j] == pared) {
                            System.out.print("N:- ");
                        }
                        else {
                            System.out.print("N:");
                            System.out.print("r");
                            System.out.format("%02d", i - 1);
                            System.out.print("c");
                            System.out.format("%02d", j);
                            System.out.print(" ");
                        }
                    }
                    //este
                    if (matriz[i][j + 1] == pared) {
                        System.out.print("E:- ");

                    }
                    if (matriz[i][j + 1] == box
                            || matriz[i][j + 1] == cajacon) {
                        if (matriz[i][j + 2] == box
                                || matriz[i][j + 2] == cajacon
                                || matriz[i][j + 2] == pared) {

                            System.out.print("E:- ");
                        }
                        else {
                            System.out.print("E:");
                            System.out.print("r");
                            System.out.format("%02d", i);
                            System.out.print("c");
                            System.out.format("%02d", j + 1);
                            System.out.print(" ");
                        }
                    }
                    if (matriz[i][j + 1] == piso
                            || matriz[i][j + 1] == cajasin) {
                        System.out.print("E:");
                        System.out.print("r");
                        System.out.format("%02d", i);
                        System.out.print("c");
                        System.out.format("%02d", j + 1);
                        System.out.print(" ");
                    }
                    //sur
                    if (matriz[i + 1][j] == pared) {
                        System.out.print("S:- ");

                    }
                    if (matriz[i + 1][j] == box
                            || matriz[i + 1][j] == cajacon) {
                        if (matriz[i + 2][j] == box
                                || matriz[i + 2][j] == cajacon
                                || matriz[i + 2][j] == pared) {

                            System.out.print("S:- ");
                        }
                        else {
                            System.out.print("S:");
                            System.out.print("r");
                            System.out.format("%02d", i + 1);
                            System.out.print("c");
                            System.out.format("%02d", j);
                            System.out.print(" ");
                        }
                    }
                    if (matriz[i + 1][j] == piso
                            || matriz[i + 1][j] == cajasin) {
                        System.out.print("S:");
                        System.out.print("r");
                        System.out.format("%02d", i + 1);
                        System.out.print("c");
                        System.out.format("%02d", j);
                        System.out.print(" ");

                    }
                    //oeste
                    if (matriz[i][j - 1] == pared) {
                        System.out.print("O:- ");

                    }
                    if (matriz[i][j - 1] == box
                            || matriz[i][j - 1] == cajacon) {
                        if (matriz[i][j - 2] == box
                                || matriz[i][j - 2] == cajacon
                                || matriz[i][j - 2] == pared) {

                            System.out.print("O:- ");
                        }
                        else {
                            System.out.print("O:");
                            System.out.print("r");
                            System.out.format("%02d", i);
                            System.out.print("c");
                            System.out.format("%02d", j - 1);
                            System.out.print(" ");
                        }
                    }
                    if (matriz[i][j - 1] == piso
                            || matriz[i][j - 1] == cajasin) {
                        System.out.print("O:");
                        System.out.print("r");
                        System.out.format("%02d", i);
                        System.out.print("c");
                        System.out.format("%02d", j - 1);
                        System.out.print(" ");
                    }
                }
            }
        }
        System.out.println();
    }


    /**
     * este metodo busca si una caja se encuentra en una
     * "esquina", osea si esta bloqueda en los casos,
     * (arriba y derecha, arriba e izquierda,
     * abajo y derecha y abajo e izquierda).
     * @param matriz
     * @param nRows
     * @param nCol
     */
    public void cajasBloqueadas(final char[][] matriz, final int nRows, final int nCol) {

        char cajacon = 'X';
        char pared = '#';
        char box = '*';

        System.out.print("   Cajas Bloqueadas: ");
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCol; j++) {

                if (matriz[i][j] == box) {

                    if ((matriz[i + 1][j] == pared
                            || matriz[i + 1][j] == box
                            || matriz[i + 1][j] == cajacon)
                            && (matriz[i][j + 1] == pared
                            || matriz[i][j + 1] == box
                            || matriz[i][j + 1] == cajacon)) {

                        System.out.print("r");
                        System.out.format("%02d", i);
                        System.out.print("c");
                        System.out.format("%02d", j);
                        System.out.print(" ");
                        continue;

                    }

                    if ((matriz[i + 1][j] == pared
                            || matriz[i + 1][j] == box
                            || matriz[i + 1][j] == cajacon)
                            && (matriz[i][j - 1] == pared
                            || matriz[i][j - 1] == box
                            || matriz[i][j - 1] == cajacon)) {

                        System.out.print("r");
                        System.out.format("%02d", i);
                        System.out.print("c");
                        System.out.format("%02d", j);
                        System.out.print(" ");
                        continue;
                    }
                    if ((matriz[i - 1][j] == box
                            || matriz[i - 1][j] == pared
                            || matriz[i - 1][j] == cajacon)
                            && (matriz[i][j + 1] == pared
                            || matriz[i][j + 1] == box
                            || matriz[i][j + 1] == cajacon)) {

                        System.out.print("r");
                        System.out.format("%02d", i);
                        System.out.print("c");
                        System.out.format("%02d", j);
                        System.out.print(" ");
                        continue;
                    }
                    if ((matriz[i - 1][j] == box
                            || matriz[i - 1][j] == pared
                            || matriz[i - 1][j] == cajacon)
                            && (matriz[i][j - 1] == pared
                            || matriz[i][j - 1] == box
                            || matriz[i][j - 1] == cajacon)) {

                        System.out.print("r");
                        System.out.format("%02d", i);
                        System.out.print("c");
                        System.out.format("%02d", j);
                        System.out.print(" ");
                        continue;
                    }
                }
            }
        }
        System.out.println();
    }
}
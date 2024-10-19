package co.edu.uniquindio.bustiquetes.modelo;

public class Prueba {

    public static byte suma(int n, int m){
        return (byte)(n+m);
    }

    static int[] americano ( int[] arreglo1, int[] arreglo2 ){
        int[] resultado = new int [arreglo1.length + arreglo2.length];
        for ( int i = arreglo2.length-1 ; i >= 0; i-- ){
            for ( int j = arreglo1.length-1; j >= 0 ; j-- ){
                resultado [ i + j + 1 ] += arreglo1 [ j ] * arreglo2 [ i ];
                resultado [ i + j     ] += (resultado [ i + j + 1 ] / 10);
                resultado [ i + j + 1 ]  %= 10;
            }
        }
        return resultado;
    }

    static int[] americano2( int[] arreglo1, int[] arreglo2 ){
        int k = arreglo1.length+arreglo2.length -1;
        int pos = arreglo1.length+arreglo2.length -1;
        int[] resultado = new int[arreglo1.length + arreglo2.length];

        for(int i = arreglo1.length-1; i>=0; i--){
            for(int j = arreglo2.length-1; j>=0; j--){
                resultado[k] = resultado[k] + arreglo1[i]*arreglo2[j];
                if(resultado[k]>9){
                    resultado[k-1] += resultado[k]/10;
                    resultado[k] = resultado[k]%10;
                }
                k--;
            }
            k = pos;
            pos--;
            k--;
        }
        return resultado;
    }

    static int[] misterio (int[] arreglo1, int[] arreglo2) {

        int tam = arreglo1.length + arreglo2.length;
        int[] resultado = new int [tam];

        for (int i=0; i< arreglo2.length; i++) {
            for(int j=0; j< arreglo1.length; j++) {
                resultado[i+j+1]+= arreglo1[j]* arreglo2[i];
            }
        }
        for (int k=tam-1; k>0; k--) {
            resultado[k-1]+= resultado[k]/10;
            resultado [k]%=10;
        }
        return resultado;
    }


    static void imprimir(int[] arreglo){
        for(int i = 0; i<arreglo.length; i++){
            System.out.print(arreglo[i]);
        }
    }

    static long multiplicacionKaratsuba(long x, long y) {

        int n = (int) Math.log10(Math.max(x, y)) + 1;

        if (n < 2){
            return x * y;
        }

        int mitad = (n / 2) + (n % 2);
        long m = (long) Math.pow(10, mitad);

        long x1 = x / m;
        long x0 = x % m;

        long y1 = y / m;
        long y0 = y % m;

        long z0 = multiplicacionKaratsuba(x0, y0);
        long z1 = multiplicacionKaratsuba(x0 + x1, y0 + y1);
        long z2 = multiplicacionKaratsuba(x1, y1);

        return z0 + ((z1 - z0 - z2) * m) + (z2 * m * m);
    }

    static int[][] calcularMultiplicacion(int[][] A, int[][] B){
        int k=0, parcial=0; int [][] resultado = new int [A.length][B[0].length];
        if (A[0].length == B.length) {
            for ( int i=0; i<A.length; i++){
                for ( int j=0; j<B[0].length; j++){
                    while(k < B.length ){
                        parcial += (A[i][k])*(B[k][j]);
                        k++;
                    }
                    resultado[i][j] = parcial;
                    k = 0;
                    parcial = 0;
                }
            }
        }
        return resultado;
    }

    static void imprimir(int[][] arreglo){
        for(int i = 0; i<arreglo.length; i++){
            for(int j = 0; j<arreglo[0].length; j++){
                System.out.print(arreglo[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //System.out.println(suma(127, 200));

        /*int[] arreglo1 = {1, 2, 3};
        int[] arreglo2 = {4, 5, 6};
        int[] resultado1 = americano(arreglo1, arreglo2);
        int[] resultado2 = americano2(arreglo1, arreglo2);
        int[] resultado3 = misterio(arreglo1, arreglo2);


        imprimir(resultado1);
        System.out.println();
        imprimir(resultado2);
        System.out.println();
        imprimir(resultado3);*/

        long x = 123;
        long y = 3234;

        System.out.println(multiplicacionKaratsuba(x, y));
        System.out.println(x*y);

        //int[][] A = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        //int[][] B = {{1, 0, 0, 1}, {0, 1, 1, 0}, {1, 0, 0, 1}, {0, 1, 1, 0}};

        //int[][] resultado = calcularMultiplicacion(A, B);
        //imprimir(resultado);

    }

}

package algos.divideconquer.matrix;

import utils.Printer;

/**
 * Created by Allan on 1/15/2017.
 */
public class MatrixClient {
    public static void main(String[] args)
    {
        MatrixOps ops;
        Matrix a;
        Matrix b;

        //ops = new StandardOps();
        ops = new SquareOps();

        //a = new Matrix(2,2);
        //a.setMatrix(1,2,3,4);


        //b = new Matrix(2,2);
        //b.setMatrix(5,6,7,8);

        a = new Matrix(2,3);
        a.setMatrix(
                1,-1,2,
                0,-3,1);

        b = new Matrix(3,3);
        b.setMatrix(1,1,1,
                2,2,2,
                3,3,3);

        doMultiply(a,b, ops);
    }

    private static void doMultiply(Matrix a, Matrix b, MatrixOps ops) {

        a.print();
        Printer.println("");

        b.print();
        Printer.println("");

        Matrix c = ops.multiply(a,b);
        c.print();

    }
}

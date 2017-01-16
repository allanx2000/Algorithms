package algos.misc.complex;

import utils.Printer;

/**
 * Created by Allan on 1/15/2017.
 */
public class Client {
    public static void main(String[] args)
    {
        ComplexNumber a = new ComplexNumber(1,2);
        ComplexNumber b = new ComplexNumber(3,4);

        Printer.println(a.toString());
        Printer.println(b.toString());
        Printer.println("");
        ComplexOperations ops = new ComplexOperations();

        ComplexNumber r = ops.multiply(a,b);
        Printer.println(r.toString());
    }
}

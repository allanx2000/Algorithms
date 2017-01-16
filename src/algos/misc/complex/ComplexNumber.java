package algos.misc.complex;

import utils.Formatters;

/**
 * Created by Allan on 1/15/2017.
 */
public class ComplexNumber {

    private double real;
    private double imaginary;

    public double getImaginary() {
        return imaginary;
    }

    public double getReal() {
        return real;
    }

    public ComplexNumber(double real, double imaginary)
    {
        this.real = real;
        this.imaginary = imaginary;
    }

    @Override
    public String toString()
    {
        String sign = imaginary < 0? "-" : "+";

        String real = Formatters.decimalFormat.format(this.real);
        String imaginary = Formatters.decimalFormat.format(this.imaginary);

        String eq = String.format("%s %s %si", real,sign,imaginary);
        return eq;
    }
}

package algos.misc.complex;

/**
 * Created by Allan on 1/15/2017.
 */
public class ComplexOperations {
    public ComplexNumber add(ComplexNumber a, ComplexNumber b)
    {
        return new ComplexNumber(a.getReal() + b.getReal(), a.getImaginary() + b.getImaginary());
    }

    public ComplexNumber substract(ComplexNumber a, ComplexNumber b)
    {
        return new ComplexNumber(a.getReal() - b.getReal(), a.getImaginary() - b.getImaginary());
    }

    public ComplexNumber multiply(ComplexNumber a, ComplexNumber b)
    {
        double real = a.getReal()*b.getReal() -a.getImaginary()*b.getImaginary();
        double imaginary = a.getReal()*b.getImaginary() + b.getReal()*a.getImaginary();

        ComplexNumber result = new ComplexNumber(real, imaginary);
        return result;
    }
}

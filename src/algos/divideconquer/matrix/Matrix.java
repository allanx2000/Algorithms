package algos.divideconquer.matrix;

import utils.Formatters;
import utils.Printer;

import java.security.InvalidParameterException;


/**
 * Created by Allan on 1/15/2017.
 */
public class Matrix {

    private int rows, cols;
    private double[][] matrix;

    public Matrix(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;

        matrix = new double[rows][cols];
    }

    public void print()
    {
        print(1);
    }

    public void print(int space)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < space; i++)
            sb.append(" ");

        String SPACE = sb.toString();

        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < cols; c++)
            {
                double value = matrix[r][c];
                String strValue = Formatters.decimalFormat.format(value);

                Printer.print(strValue + SPACE);
            }

            Printer.println("");
        }
    }

    public int getColumnsCount() {
        return cols;
    }

    public int getRowsCount() {
        return rows;
    }

    public double[][] getMatrix()
    {
        return matrix;
    }

    public double[] getRow(int row) {
        double[] data = new double[cols];

        for (int i = 0; i < cols; i++)
        {
            data[i] = matrix[row][i];
        }

        return data;
    }

    public double[] getColumns(int col) {
        double[] data = new double[rows];

        for (int i = 0; i < rows; i++)
        {
            data[i] = matrix[i][col];
        }

        return data;
    }

    public void setValue(int r, int c, double cellValue) {
        matrix[r][c] = cellValue;
    }

    public void setMatrix(double ... values) {
        int length = rows*cols;

        if (values.length != length)
            throw new InvalidParameterException("Length of arguments don't match");

        int ctr = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                matrix[r][c] = values[ctr++];
            }
        }
    }

    public double getCell(int r, int c) {
        return matrix[r][c];
    }
}

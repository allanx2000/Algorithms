package algos.divideconquer.matrix;

/**
 * Created by Allan on 1/15/2017.
 */
public class StandardOps implements MatrixOps {
    @Override
    public Matrix multiply(Matrix a, Matrix b) {

        if (!canMultiply(a, b))
            throw new UnsupportedOperationException("Invalid size");

        Matrix result = new Matrix(a.getRowsCount(), b.getColumnsCount());

        for (int r = 0; r < a.getRowsCount(); r++) {
            for (int c = 0; c < b.getColumnsCount(); c++) {
                double[] a_row = a.getRow(r);
                double[] b_col = b.getColumns(c);

                double cellValue = 0;
                for (int x = 0; x < a_row.length; x++) {
                    cellValue += a_row[x] * b_col[x];
                }

                result.setValue(r,c, cellValue);
            }
        }

        return result;

    }

    private boolean canMultiply(Matrix a, Matrix b) {
        if (a.getColumnsCount() != b.getRowsCount())
            return false;
        else
            return true;
    }
}

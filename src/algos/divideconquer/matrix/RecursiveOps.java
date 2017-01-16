package algos.divideconquer.matrix;

/**
 * Created by Allan on 1/15/2017.
 */
public class RecursiveOps implements MatrixOps {
    @Override
    public Matrix multiply(Matrix a, Matrix b) {

        if (!canMultiply(a, b))
            throw new UnsupportedOperationException("Invalid size");

        int n = getN(a,b);

        Matrix result = multiply(a,b, n);

        return result;

    }

    /**
     * Returns the N to use to prepare arrays and calculate the values
     * @param a
     * @param b
     * @return
     */
    private int getN(Matrix a, Matrix b) {
        int i = 0;

        i = Math.max(i, a.getRowsCount());
        i = Math.max(i, b.getRowsCount());
        i = Math.max(i, a.getColumnsCount());
        i = Math.max(i, b.getColumnsCount());

        int pow = 1;

        while (pow < i)
        {
            pow = pow*2;
        }

        return pow;
    }

    /**
     * Helper method to convert and prepare the matrices for multiplication
     * @param a
     * @param b
     * @param n Size of the result matrix
     * @return
     */
    private Matrix multiply(Matrix a, Matrix b, int n)
    {
        Matrix c;

        if (n == 1) {
            c = new Matrix(1, 1);

            double val = a.getCell(0, 0) * b.getCell(0, 0);
            c.setValue(0, 0, val);

            return c;
        }

        c = new Matrix(n, n);

        double[][] array_a = toArray(a, n);
        double[][] array_b = toArray(b, n);

        double[][] array = multiply(array_a,array_b, n);

        c = toMatrix(array);

        return c;
    }

    /**
     * Converts and resizes an array matrix to a Matrix
     * @param array
     * @return
     */
    private Matrix toMatrix(double[][] array) {
        int col = array[0].length;
        int row = array.length;

        col = clean(array, row, col, false);
        row = clean(array, row, col, true);

        Matrix matrix = new Matrix(row,col);

        for (int r = 0; r < row; r++)
            for (int c = 0; c < col; c++)
                matrix.setValue(r,c, array[r][c]);

        return matrix;
    }

    /**
     * Gets the "actual" length of a row/column
     * @param array
     * @param row
     * @param col
     * @param isRowDimension whether you want the row or column length
     * @return
     */
    private int clean(double[][] array, int row, int col, boolean isRowDimension) {

        int loop1 = isRowDimension? row : col;
        int loop2 = isRowDimension? col : row;

        int ctr = isRowDimension? row : col;

        for (int x = loop1 - 1; x >= 0; x--)
        {
            boolean exit = false;
            for (int y = 0; y < loop2; y++)
            {
                boolean val = isRowDimension && array[x][y] != 0 || !isRowDimension && array[y][x] != 0;
                if (val)
                {
                    exit = true;
                    break;
                }
            }

            if (exit)
                break;

            ctr -= 1;
        }

        return ctr;

    }

    private double[][] toArray(Matrix a, int n) {
        double[][] array = new double[n][n];

        for (int r = 0; r < a.getRowsCount(); r++)
            for (int c = 0; c < a.getColumnsCount(); c++)
                array[r][c] = a.getCell(r,c);

        return array;
    }

    /**
     * Multiplies 2 matrices of N using partitioning
     * @param a
     * @param b
     * @param n
     * @return
     */
    private double[][] multiply(double[][] a, double[][] b, int n)
    {
        double[][] c;

        if (n == 1)
        {
            c = new double[1][1];

            double val = a[0][0] * b[0][0];
            c[0][0] = val;
        }
        else
        {
            c = new double[n][n];
            int partitionSize = n/2;

            //1 2
            //3 4

            //11 12
            //21 22
            double[][] p_a11 = getPartition(a, 1, partitionSize);
            double[][] p_a12 = getPartition(a, 2, partitionSize);
            double[][] p_a21 = getPartition(a, 3, partitionSize);
            double[][] p_a22 = getPartition(a, 4, partitionSize);

            double[][] p_b11 = getPartition(b, 1, partitionSize);
            double[][] p_b12 = getPartition(b, 2, partitionSize);
            double[][] p_b21 = getPartition(b, 3, partitionSize);
            double[][] p_b22 = getPartition(b, 4, partitionSize);

            double[][] c_11 = add(
                    multiply(p_a11, p_b11, partitionSize),
                    multiply(p_a12, p_b21, partitionSize),
                    partitionSize);

            double[][] c_12 = add(
                    multiply(p_a11, p_b12, partitionSize),
                    multiply(p_a12, p_b22, partitionSize),
                    partitionSize);

            double[][] c_21 = add(
                    multiply(p_a21, p_b11, partitionSize),
                    multiply(p_a22, p_b21, partitionSize),
                    partitionSize);

            double[][] c_22 = add(
                    multiply(p_a21, p_b12, partitionSize),
                    multiply(p_a22, p_b22, partitionSize),
                    partitionSize);

            c = join(c_11, c_12, c_21, c_22, n);
        }

        return c;
    }

    /**
     * Creates a new NxN matrix using submatrices of N/2cN/2
     * @param c_11 Top-Left, Partition 1
     * @param c_12 Top-Right, Partition 2
     * @param c_21 Bottom-Left, Partition 3
     * @param c_22 Bottom-Right, Partition 4
     * @param n
     * @return
     */
    private double[][] join(double[][] c_11, double[][] c_12, double[][] c_21, double[][] c_22, int n) {
        double[][] array = new double[n][n];

        int div = n/2;

        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {
                double val;

                if (row < div)
                {
                    if (col < div)
                    {
                        val = c_11[row][col];
                    }
                    else
                    {
                        val = c_12[row][col-div];
                    }
                }
                else
                {
                    if (col < div)
                    {
                        val = c_21[row-div][col];
                    }
                    else
                    {
                        val = c_22[row-div][col-div];
                    }
                }

                array[row][col] = val;
            }
        }

        return array;
    }

    /**
     * Returns a new matrix with the sum of A, B
     * @param a
     * @param b
     * @param n
     * @return
     */
    private double[][] add(double[][] a, double[][] b, int n) {
        double[][] result = new double[n][n];

        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {
                result[row][col] = a[row][col] + b[row][col];
            }
        }

        return result;
    }

    /**
     * Helper function for getPartition
     * @param a matrix to get partitions from
     * @param partitionId the partition to return
     * @param partitionSize the size of the partition to be returned
     * @return
     */
    private double[][] getPartition(double[][] a, int partitionId, int partitionSize) {
        int r_from, r_to, c_from, c_to;

        //1 2
        //3 4

        switch (partitionId)
        {
            case 1:
                r_from = 0;
                r_to = partitionSize;

                c_from = 0;
                c_to = partitionSize;
                break;
            case 2:
                r_from = 0;
                r_to = partitionSize;

                c_from = partitionSize;
                c_to = a[0].length;
                break;
            case 3:
                r_from = partitionSize;
                r_to = a.length;

                c_from = 0;
                c_to = partitionSize;
                break;
            case 4:
                r_from = partitionSize;
                r_to = a.length;

                c_from = partitionSize;
                c_to = a[0].length;
                break;
            default:
                throw new IllegalArgumentException("Partition must be 1-4");
        }

        return getPartition(a, r_from, r_to, c_from, c_to, partitionSize);
    }

    /**
     * Creates a matrix of Size x Size by copying the values from the input matrix
     * @param a Input Matrix
     * @param r_from
     * @param r_to
     * @param c_from
     * @param c_to
     * @param size Size of output matrix
     * @return
     */
    private double[][] getPartition(double[][] a, int r_from, int r_to, int c_from, int c_to, int size) {
        double[][] array = new double[size][size];

        for (int row = r_from; row < r_to; row++)
        {
            for (int col = c_from; col < c_to; col++)
            {
                array[row-r_from][col-c_from] = a[row][col];
            }
        }

        return array;
    }


    private boolean canMultiply(Matrix a, Matrix b) {
        if (a.getColumnsCount() != b.getRowsCount())
            return false;
        else
            return true;
    }

    private boolean isPower2(int rowsCount) {

        int i = 2;

        while (i <= rowsCount)
        {
            if (i == rowsCount)
                return true;
            else
                i*=2;
        }

        return false;
    }
}

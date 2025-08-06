public class MatrixCalculator {
    public static void main(String[] args) {
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] matrixB = {
            {7, 8, 9},
            {10, 11, 12}
        };

        // 矩陣加法
        System.out.println("=== 矩陣加法 ===");
        int[][] sum = addMatrices(matrixA, matrixB);
        printMatrix(sum);

        // 矩陣乘法 (這裡示範 2x3 乘 3x2)
        int[][] matrixC = {
            {1, 2},
            {3, 4},
            {5, 6}
        };
        System.out.println("=== 矩陣乘法 ===");
        int[][] product = multiplyMatrices(matrixA, matrixC);
        printMatrix(product);

        // 矩陣轉置
        System.out.println("=== 矩陣轉置 ===");
        int[][] transpose = transposeMatrix(matrixA);
        printMatrix(transpose);

        // 最大值與最小值
        System.out.println("=== 最大值與最小值 (MatrixA) ===");
        int max = findMax(matrixA);
        int min = findMin(matrixA);
        System.out.println("最大值: " + max);
        System.out.println("最小值: " + min);
    }

    // 矩陣加法
    public static int[][] addMatrices(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    // 矩陣乘法
    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int colsB = b[0].length;
        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    // 矩陣轉置
    public static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    // 找最大值
    public static int findMax(int[][] matrix) {
        int max = matrix[0][0];
        for (int[] row : matrix) {
            for (int val : row) {
                if (val > max) max = val;
            }
        }
        return max;
    }

    // 找最小值
    public static int findMin(int[][] matrix) {
        int min = matrix[0][0];
        for (int[] row : matrix) {
            for (int val : row) {
                if (val < min) min = val;
            }
        }
        return min;
    }

    // 印出矩陣
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}
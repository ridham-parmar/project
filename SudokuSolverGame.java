public class SudokuSolverGame {
    public static boolean isSafe(char sudoku[][], int row, int col, char digit) {
        // rowWise
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][col] == digit) {
                return false;
            }
        }

        // ColumnWise
        for (int j = 0; j < 9; j++) {
            if (sudoku[row][j] == digit) {
                return false;
            }
        }

        // gridWise
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (sudoku[i][j] == digit) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean sudokuSolver(char sudoku[][], int row, int col) {
        // baseCase
        if (row == sudoku.length) {
            return true;
        }

        int nextRow = row, nextCol = col + 1;
        if (col + 1 == sudoku[0].length) {
            nextRow = row + 1;
            nextCol = 0;
        }

        if (sudoku[row][col] != '0') {
            return sudokuSolver(sudoku, nextRow, nextCol);
        }

        for (char digit = '1'; digit <= '9'; digit++) {
            if (isSafe(sudoku, row, col, digit)) {
                sudoku[row][col] = digit;
                if (sudokuSolver(sudoku, nextRow, nextCol)) {
                    return true;
                }
                sudoku[row][col] = '0';
            }
        }
        return false;
    }

    public static void printSudoku(char sudoku[][]) {
        System.out.print("[");
        for (int i = 0; i < 9; i++) {
            System.out.print("[");
            for (int j = 0; j < 9; j++) {
                System.out.print("\"" + sudoku[i][j] + "\"");
                if (j != 8) {
                    System.out.print(",");
                }
            }
            System.out.print("],");
            System.out.println();
        }
        System.out.print("]");
    }

    public static void main(String[] args) {
        /*
         * int sudoku[][] = {{0, 0, 8, 0, 0, 0, 0, 0, 0},
         * {4, 9, 0, 1, 5, 7, 0, 0, 2},
         * {0, 0, 3, 0, 0, 4, 1, 9, 0},
         * {1, 8, 5, 0, 6, 0, 0, 2, 0},
         * {0, 0, 0, 0, 2, 0, 0, 6, 0},
         * {9, 6, 0, 4, 0, 5, 3, 0, 0},
         * {0, 3, 0, 0, 7, 2, 0, 0, 4},
         * {0, 4, 9, 0, 3, 0, 0, 5, 7},
         * {8, 2, 7, 0, 0, 9, 0, 1, 3}} ;
         */

        char sudoku[][] = { { '5', '3', '0', '0', '7', '0', '0', '0', '0' },
                { '6', '0', '0', '1', '9', '5', '0', '0', '0' },
                { '0', '9', '8', '0', '0', '0', '0', '6', '0' },
                { '8', '0', '0', '0', '6', '0', '0', '0', '3' },
                { '4', '0', '0', '8', '0', '3', '0', '0', '1' },
                { '7', '0', '0', '0', '2', '0', '0', '0', '6' },
                { '0', '6', '0', '0', '0', '0', '2', '8', '0' },
                { '0', '0', '0', '4', '1', '9', '0', '0', '5' },
                { '0', '0', '0', '0', '8', '0', '0', '7', '9' } };
        if (sudokuSolver(sudoku, 0, 0)) {
            printSudoku(sudoku);
        } else {
            System.out.println("Solution is not possible");
        }

    }

}

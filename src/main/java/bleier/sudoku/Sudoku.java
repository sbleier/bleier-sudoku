package bleier.sudoku;

import java.util.*;

public class Sudoku {
    int[][] board;

    public Sudoku(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int row, int col, int value) {
        this.board[row][col] = value;
    }

    public List<SudokuError> getErrors() {
        Set<SudokuError> errors = new HashSet<>();
        checkColumns(errors);
        checkRows(errors);
        checkBoxes(errors);
        return errors.stream().toList();
    }

    public void checkRows(Set<SudokuError> errors) {
        for (int r = 0; r < 9; r++) {
            Set<Integer> hashmap = new HashSet<>();
            for (int c = 0; c < 9; c++) {
                int num = board[r][c];
                if (hashmap.contains(num)) {
                    errors.add(new SudokuError(r, c, num));
                }
                hashmap.add(num);
            }
        }
    }

    public void checkColumns(Set<SudokuError> errors) {
        for (int c = 0; c < 9; c++) {
            Set<Integer> hashmap = new HashSet<>();
            for (int r = 0; r < 9; r++) {
                int num = board[r][c];
                if (hashmap.contains(num)) {
                    errors.add(new SudokuError(r, c, num));
                }
                hashmap.add(num);
            }
        }
    }

    public void checkBoxes(Set<SudokuError> errors) {
        for (int bRow = 0; bRow < 3; bRow++) {
            for (int bCol = 0; bCol < 3; bCol++) {
                Set<Integer> hashmap = new HashSet<>();
                for (int r = bRow * 3; r < (bRow * 3) + 3; r++) {
                    for (int c = bCol * 3; c < (bCol * 3) + 3; c++) {
                        int num = board[r][c];
                        if (hashmap.contains(num)) {
                            errors.add(new SudokuError(r, c, num));
                        }
                        hashmap.add(num);
                    }
                }
            }
        }
    }
}

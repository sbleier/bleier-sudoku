package bleier.sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        List<SudokuError> errors = new ArrayList<>();
        checkColumns(errors);
        checkRows(errors);
        checkBoxes(errors);
        return errors;
    }

    public void checkRows(List<SudokuError> errors) {
        for (int r = 0; r < 9; r++) {
            HashMap<Integer, Integer>  hashmap = new HashMap<>();
            for (int c = 0; c < 9; c++) {
                int num = board[r][c];
                if (hashmap.containsKey(num)) {
                    errors.add(new SudokuError(r, c, num));
                }
                hashmap.put(num, hashmap.getOrDefault(num, 0) + 1);
            }
        }
    }

    public void checkColumns(List<SudokuError> errors) {
        for (int c = 0; c < 9; c++) {
            HashMap<Integer, Integer>  hashmap = new HashMap<>();
            for (int r = 0; r < 9; r++) {
                int num = board[r][c];
                if (hashmap.containsKey(num)) {
                    errors.add(new SudokuError(r, c, num));
                }
                hashmap.put(num, hashmap.getOrDefault(num, 0) + 1);
            }
        }
    }

    public void checkBoxes(List<SudokuError> errors) {
        for (int bRow = 0; bRow < 3; bRow++) {
            for (int bCol = 0; bCol < 3; bCol++) {
                HashMap<Integer, Integer>  hashmap = new HashMap<>();
                for (int r = bRow * 3; r < (bRow * 3) + 3; r++) {
                    for (int c = bCol * 3; c < (bCol * 3) + 3; c++) {
                        int num = board[r][c];
                        if (hashmap.containsKey(num)) {
                            errors.add(new SudokuError(r, c, num));
                        }
                        hashmap.put(num, hashmap.getOrDefault(num, 0) + 1);
                    }
                }
            }
        }
    }
}

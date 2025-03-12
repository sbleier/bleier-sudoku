package bleier.sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SudokuTest {

    @Test
    public void noErrors() {
        int[][] sudokuBoard = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        Sudoku sudoku = new Sudoku(sudokuBoard);
        List<SudokuError> errors = sudoku.getErrors();

        assertTrue(errors.isEmpty());
    }

    @Test
    public void yesErrors() {
        int[][] sudokuBoard = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 5, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        Sudoku sudoku = new Sudoku(sudokuBoard);
        List<SudokuError> errors = sudoku.getErrors();
        List<String> expectedErrors = List.of("Row 4, Column, 5 contains duplicate value: 5",
                "Row 4, Column 5 contains duplicate value: 5",
                "Box 5, Row 4, Column 5 contains duplicate value: 5");

        assertFalse(errors.isEmpty());
        assertEquals(errors, expectedErrors);



    }

}
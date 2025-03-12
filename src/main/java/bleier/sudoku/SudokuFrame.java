package bleier.sudoku;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;

public class SudokuFrame extends JFrame {
    private Sudoku sudoku;
    private JTextField[][] cells = new JTextField[9][9];

    public SudokuFrame(Sudoku sudoku) {
        this.sudoku = sudoku;
        setTitle("Sudoku");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridLayout(9, 9));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setText(String.valueOf(sudoku.getBoard()[i][j]));
                add(cells[i][j]);
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final int row = i;
                final int col = j;
                cells[i][j].getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        updateBoard(row, col);

                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        updateBoard(row, col);

                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        updateBoard(row, col);

                    }

                    private void updateBoard(int i, int j) {
                            try {
                                if (!cells[i][j].getText().isEmpty()) {
                                    sudoku.setBoard(i, j, Integer.parseInt(cells[i][j].getText()));
                                }

                                if (fullBoard()) {
                                    highlightErrors();
                                }
                            }
                            catch (NumberFormatException e) {
                                cells[i][j].setText("0");
                            }
                    }
                });

            }
        }

    }

    private boolean fullBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Integer.parseInt(cells[i][j].getText().trim()) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void highlightErrors() {
        List<SudokuError> errors = sudoku.getErrors();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setBackground(Color.WHITE);
            }
        }

        for (SudokuError error : errors) {
            cells[error.row()][error.col()].setBackground(Color.RED);
        }
    }

    public static void main(String[] args) {
        int[][] board = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        Sudoku sudoku = new Sudoku(board);
        SudokuFrame frame = new SudokuFrame(sudoku);
        frame.setVisible(true);
    }
}


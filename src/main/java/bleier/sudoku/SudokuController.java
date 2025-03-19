package bleier.sudoku;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;

public class SudokuController {

    private Sudoku model;
    private JTextField[][] cells;

    public SudokuController(Sudoku model, JTextField[][] cells) {
        this.model = model;
        this.cells = cells;
        addListeners();
    }

    private void addListeners() {
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
                            if (cells[i][j].getText().isEmpty()) {
                                model.setBoard(i, j, Integer.parseInt(cells[i][j].getText()));
                            }

                            if (fullBoard()) {
                                highlightErrors(model.getErrors());
                            }
                        } catch (NumberFormatException e) {
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

    public void highlightErrors(List<SudokuError> errors) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setBackground(Color.WHITE);
            }
        }

        for (SudokuError error : errors) {
            cells[error.row()][error.col()].setBackground(Color.RED);
        }
    }
}

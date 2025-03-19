package bleier.sudoku;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SudokuController {

    private Sudoku model;
    private SudokuFrame view;

    public SudokuController(Sudoku model, SudokuFrame view) {
        this.model = model;
        this.view = view;
    }

    private void addListeners(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final int row = i;
                final int col = j;
                view.getCell(i, j).getDocument().addDocumentListener(new DocumentListener() {
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
                            if (!view.getCell(i, j).getText().isEmpty()) {
                                model.setBoard(i, j, Integer.parseInt(view.getCell(i, j).getText()));
                            }

                            if (fullBoard()) {
                                view.highlightErrors(model.getErrors());
                            }
                        }
                        catch (NumberFormatException e) {
                            view.getCell(i, j).setText("0");
                        }
                    }
                });

            }
        }
    }

    private boolean fullBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Integer.parseInt(view.getCell(i, j).getText().trim()) == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}

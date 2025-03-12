package bleier.sudoku;

public class SudokuError {
    private int row;
    private int column;
    private int value;

    public SudokuError(int row, int column, int value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SudokuError that = (SudokuError) obj;
        return row == that.row && column == that.column && value == that.value;
    }

    @Override
    public String toString() {
        return "Row " + row + ", Column " + column + ", Value " + value + ": is a duplicate.";
    }
}

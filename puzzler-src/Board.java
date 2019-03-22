package ca.uottawa.eecs.puzzler;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * In this application, <b>Board</b> is a specialized type of <b>JPanel</b> that
 * holds cells (balls). The board also holds the higher level logic of the game
 * for selecting and removing cells.
 * 
 * @author Marcel Turcotte, University of Ottawa
 * 
 */

public class Board extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    /**
     * Defines the total number of rows.
     */

    public static final int NUMBER_OF_ROWS = 7;

    /**
     * Defines the total number of columns.
     */

    public static final int NUMBER_OF_COLUMNS = 7;

    /**
     * A two dimensional array to keep references to all the cells of the board.
     */

    private Cell[][] board;

    /**
     * Used by the logic to avoid processing multiple clicks.
     */

    private boolean allowsClicks = false;

    /**
     * Displays all the cells on a two dimensional grid.
     */

    public Board() {
	setBackground(Color.WHITE);
	setLayout(new GridLayout(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS));
	setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
	board = new Cell[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];

	for (int row = 0; row < NUMBER_OF_ROWS; row++) {
	    for (int column = 0; column < NUMBER_OF_COLUMNS; column++) {
		board[row][column] = new Cell(this, row, column);
		add(board[row][column]);
	    }
	}

    }

    /**
     * Re-initializes all the cells of the grid.
     */

    public void reset() {
	for (int row = 0; row < NUMBER_OF_ROWS; row++) {
	    for (int column = 0; column < NUMBER_OF_COLUMNS; column++) {
		board[row][column].reset();
	    }
	}
    }

    /**
     * Returns <b>true</b> if clicks are allowed, and false otherwise.
     * 
     * @return true if clicks are allowed
     */

    public boolean allowsClicks() {
	return allowsClicks;
    }

    /**
     * A setter for the attribute <b>allowsClick</b>.
     * 
     * @param allowClicks
     *            the allowClicks to set
     */

    public void setAllowsClicks(boolean allowClicks) {
	this.allowsClicks = allowClicks;
    }

    /**
     * Sets the attribute <b>selected</b> to <b>false</b> for all the cells of
     * the grid.
     */

    public void deselectAllCells() {
	for (int row = 0; row < NUMBER_OF_ROWS; row++) {
	    for (int column = 0; column < NUMBER_OF_COLUMNS; column++) {
		board[row][column].setSelected(false);
	    }
	}
    }

    /**
     * Returns <b>true</b> if the cell found at <b>row</b>, <b>column</b> has at
     * least one neighbor of the same type, and <b>false</b> otherwise.
     * 
     * @param row
     *            the specified row
     * @param column
     *            the specified column
     * @return true if the specified cell has at least one neighbor of the same
     *         type
     */

    public boolean hasIdenticalNeighbours(int row, int column) {
	Cell cell = board[row][column];
	boolean result = false;

	if ((row - 1 >= 0 && cell.sameType(board[row - 1][column]))
		|| (row + 1 < NUMBER_OF_ROWS && cell
			.sameType(board[row + 1][column]))
			|| (column - 1 >= 0 && cell.sameType(board[row][column - 1]))
			|| (column + 1 < NUMBER_OF_COLUMNS && cell
				.sameType(board[row][column + 1]))) {
	    result = true;
	}

	return result;
    }

    /**
     * This method is called after a call to <b>hasIdenticalNeighbours</b> in
     * order to select (set the attribute selected to true) all the adjacent
     * cells of the same type.
     * 
     * @param row
     *            the specified row
     * @param column
     *            the specified column
     */

    public void selectCellAndContiguousCells(int row, int column) {
	Cell cell = board[row][column];
	cell.setSelected(true);
	if (column - 1 >= 0 && cell.sameType(board[row][column - 1])
		&& (!board[row][column - 1].selected())) {
	    selectCellAndContiguousCells(row, column - 1);
	}
	if (column + 1 < NUMBER_OF_ROWS
		&& cell.sameType(board[row][column + 1])
		&& (!board[row][column + 1].selected())) {
	    selectCellAndContiguousCells(row, column + 1);
	}
	if (row - 1 >= 0 && cell.sameType(board[row - 1][column])
		&& (!board[row - 1][column].selected())) {
	    selectCellAndContiguousCells(row - 1, column);
	}
	if (row + 1 < NUMBER_OF_COLUMNS
		&& cell.sameType(board[row + 1][column])
		&& (!board[row + 1][column].selected())) {
	    selectCellAndContiguousCells(row + 1, column);
	}
    }

    /**
     * The selected cells are removed. Actually, this simply means setting the
     * type of the selected cells to <b>Empty</b>. As the method proceeds it
     * unselects cells. This is important to avoid looping infinitely.
     * 
     * @param row
     *            the specified row
     * @param column
     *            the specified column
     */

    public void removeCellAndContiguousCells(int row, int column) {
	Cell cell = board[row][column];
	cell.setSelected(false);
	if (column - 1 >= 0 && board[row][column - 1].selected()) {
	    removeCellAndContiguousCells(row, column - 1);
	}
	if (column + 1 < NUMBER_OF_ROWS && board[row][column + 1].selected()) {
	    removeCellAndContiguousCells(row, column + 1);
	}
	if (row - 1 >= 0 && board[row - 1][column].selected()) {
	    removeCellAndContiguousCells(row - 1, column);
	}
	if (row + 1 < NUMBER_OF_COLUMNS && board[row + 1][column].selected()) {
	    removeCellAndContiguousCells(row + 1, column);
	}
	cell.setType(Cell.EMPTY);
    }

    /**
     * Detects vertical gaps and collapse those cells.
     */

    public void fallDown() {

	for (int column = NUMBER_OF_COLUMNS - 1; column >= 0; column--) {

	    boolean foundGap = false;
	    int fallTo = -1;

	    for (int row = NUMBER_OF_ROWS - 1; row >= 0; row--) {

		if (board[row][column].isEmpty()) {
		    if (!foundGap) {
			foundGap = true;
			fallTo = row;
		    }
		} else {
		    if (foundGap) {
			board[fallTo][column].setType(board[row][column]
				.getType());
			board[row][column].setType(Cell.EMPTY);
			fallTo--;
		    }
		}

	    }

	}
    }

    /**
     * Detects horizontal gaps and collapse those cells.
     */

    public void fallRight() {

	for (int row = NUMBER_OF_ROWS - 1; row >= 0; row--) {

	    boolean foundGap = false;
	    int fallTo = -1;

	    for (int column = NUMBER_OF_COLUMNS - 1; column >= 0; column--) {

		if (board[row][column].isEmpty()) {
		    if (!foundGap) {
			foundGap = true;
			fallTo = column;
		    }
		} else {
		    if (foundGap) {
			board[row][fallTo]
				.setType(board[row][column].getType());
			board[row][column].setType(Cell.EMPTY);
			fallTo--;
		    }
		}

	    }

	}
    }

    /**
     * Returns <b>true</b> if and only if all the cells are <b>Empty</b>.
     * 
     * @return <b>true</b> if and only if all the cells are <b>Empty</b>
     */

    public boolean solved() {
	boolean flag = false;
	for (int row = 0; row < NUMBER_OF_ROWS && !flag; row++) {
	    for (int column = 0; column < NUMBER_OF_COLUMNS && !flag; column++) {
		if (!board[row][column].isEmpty()) {
		    flag = true;
		}
	    }
	}

	return !flag;
    }

    /**
     * This method must be implemented as part of the contract specified by
     * ActionListener. This method will be called each time the user clicks a
     * button. Upon a first click, if there are adjacent cells of the same type,
     * the cell and the adjacent cells of the same type are selected. Upon a
     * second click, the cell and the adjacent cells of the same type are
     * removed from the <b>Board</b>. This causes other cells to fall down, and
     * right.
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */

    public void actionPerformed(ActionEvent e) {

	if (e.getSource() instanceof Cell) {

	    Cell src = (Cell) e.getSource();

	    if (src.getType() != Cell.EMPTY && allowsClicks()) {

		setAllowsClicks(false);

		int row = src.getRow(), column = src.getColumn();

		if (!src.selected()) {
		    deselectAllCells();
		    if (hasIdenticalNeighbours(row, column)) {
			selectCellAndContiguousCells(row, column);
		    }
		} else {
		    removeCellAndContiguousCells(row, column);
		    fallDown();
		    fallRight();
		    if (solved()) {
			System.out.println("Solved!");
			reset();
		    }
		    src.setSelected(false);
		}

		setAllowsClicks(true);
	    }
	}

    }
}

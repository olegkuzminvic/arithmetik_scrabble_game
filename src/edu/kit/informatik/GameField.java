package edu.kit.informatik;

/**
 * This class describes the gaming field.
 * 
 * @author Oleh Kuzmin<uvdxz@student.kit.edu>
 * @version 1.0
 */
public final class GameField {

    private Character[][] field;
    // Copy of field,but with user names(instead figures)
    private String[][] playersMap;
    private Player activePlayer;
    private final int size;

    /**
     * Creates new field 10x10
     * 
     * @param size
     *            of the field.(default:10)
     */
    public GameField(final int size) {
        this.field = new Character[size][size];
        this.playersMap = new String[size][size];
        this.size = size;
        initField();
        initPlayerMap();

    }

    /**
     * Init the gaming field.If the cell is empty,the value is {#}
     */
    private void initField() {
        for (int oY = 0; oY < field.length; oY++) {

            for (int oX = 0; oX < field.length; oX++) {

                this.field[oY][oX] = '#';

            }

        }

    }

    /**
     * init copy
     */
    private void initPlayerMap() {
        for (int oY = 0; oY < field.length; oY++) {

            for (int oX = 0; oX < field.length; oX++) {

                this.playersMap[oY][oX] = "##";

            }

        }

    }

    /**
     * This method adds singly the figure to the game field.
     * 
     * @param character
     *            Figure.
     * @param row
     *            coordinate of the start position of expression
     * @param col
     *            coordinate of the start position of expression
     * @return true if successfully added, else false
     * 
     * 
     */
    public boolean addToField(final Character character, final int row, final int col) {
        if (row < field.length && row >= 0) {
            if (col < field.length && col >= 0) {
                if (this.field[row][col].equals('#')) {
                    this.field[row][col] = character;
                    // to avoid NPE
                    if (this.activePlayer != null) {
                        this.playersMap[row][col] = this.activePlayer.toString();
                    }

                    return true;
                }

            }
        }

        return false;
    }

    /**
     * 
     * @param colNumber
     *            coordinate of game field
     * @return column of the game
     * @throws IllegalArgumentException
     *             if the coordinate is false
     */
    public String colPrint(final int colNumber) throws IllegalArgumentException {
        if (!checkCoordinate(colNumber)) {
            throw new IllegalArgumentException(Messages.ERROR_MSG_ACCESS_STRUCT);
        }

        String tempColumns = "";
        for (int i = 0; i < field.length; i++) {

            tempColumns += this.field[i][colNumber];

        }
        return tempColumns;

    }

    /**
     * 
     * @param rowNumber
     *            coordinate of game field
     * @return row
     * @throws IllegalArgumentException
     *             if the coordinate is false
     * 
     */
    public String rowPrint(final int rowNumber) throws IllegalArgumentException {
        if (!checkCoordinate(rowNumber)) {
            throw new IllegalArgumentException(Messages.ERROR_MSG_ACCESS_STRUCT);
        }
        String tempRows = "";
        for (int i = 0; i < field.length; i++) {
            tempRows += this.field[rowNumber][i];
        }
        return tempRows;

    }

    // Checs field and coord sizes
    private boolean checkCoordinate(final int coordinate) {
        return (coordinate < this.size) && (coordinate >= 0);
    }

    /**
     * Get singleton figures from the field.
     * 
     * @param row
     *            coordinate of the start position of expression
     * @param col
     *            coordinate of the start position of expression
     * @return figures
     */
    public Character getFigure(final int row, final int col) {
        return this.field[row][col];
    }

    /**
     * Get the size of the field.
     * 
     * @return the size of field
     */
    public int getSize() {
        return size;
    }

    /**
     * Displays player map
     * 
     * @return player Map
     */
    public String getPlayerMap() {
        String out = "";
        for (int oY = 0; oY < playersMap.length; oY++) {
            out += "\n";
            for (int oX = 0; oX < playersMap.length; oX++) {

                out += " " + this.playersMap[oY][oX];
            }
        }
        return out;
    }

    /**
     * Get player map
     * 
     * @return field with player names
     */
    public String[][] getPlayerMapArray() {
        return this.playersMap;
    }

    /**
     * Set active player to the strukture.
     * 
     * @param activePlayer
     *            of the game
     * 
     */
    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    @Override
    public String toString() {
        String out = "";
        for (int oY = 0; oY < field.length; oY++) {
            out += "\n";
            for (int oX = 0; oX < field.length; oX++) {

                out += " " + field[oY][oX];
            }
        }
        return out;
    }

}

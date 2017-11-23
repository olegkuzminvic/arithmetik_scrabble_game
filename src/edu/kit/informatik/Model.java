package edu.kit.informatik;

/**
 * This method describes the game.
 * 
 * @author Oleh Kuzmin<uvdxz@student.kit.edu>
 * @version 1.0
 *
 */
public final class Model {
    private GameField field;

    /**
     * Created the field 10x10
     * 
     */

    public Model() {
        this.field = new GameField(10);

    }

    /**
     * 
     * @param expression
     *            to place
     * @param row
     *            coordinate of the start position of expression
     * @param col
     *            coordinate of the start position of expression
     * @param orientation
     *            Vertical or Horizontal,how to place the expession
     * @return {true} if successfully placed, else {false}
     */
    public boolean place(final String expression, final int row, final int col, final Orientation orientation) {
        char[] figuresChar = expression.toCharArray();
        // checks the format of figures
        boolean checkFigureTemp = figuresCheck(expression);
        // check the rule of the adding to field
        boolean checkTemp = false;
        switch (orientation) {

        case HORIZONTAL:

            for (int i = 0; i < figuresChar.length; i++) {
                checkTemp = this.field.addToField(figuresChar[i], row, col + i);
            }
            break;
        case VERTICAL:
            for (int i = 0; i < figuresChar.length; i++) {
                checkTemp = this.field.addToField(figuresChar[i], row + i, col);

            }

        default:
            break;
        }
        return checkTemp && checkFigureTemp;
    }

    /**
     * Checks the expression format and length
     * 
     * @param figures
     *            -expression to check
     * @return {true} if the format is right , else {false}
     */
    private boolean figuresCheck(final String figures) {
        final int figuresLength = figures.length();
        if (figuresLength >= 3) {
            try {
                Word.evaluatePostfixExpression(figures);
            } catch (IllegalArgumentException e) {
                return false;
            }
            return true;
        }

        return Word.checkExpression(figures);

    }

    /**
     * Get the field
     * 
     * @return the field
     */
    public GameField getField() {
        return field;
    }

}

package edu.kit.informatik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class implements a player in the game.
 * 
 * @author Oleh Kuzmin<uvdxz@student.kit.edu>
 * @version 4.0
 */
public final class Player {
    private int score = 0;

    private final String name;
    private final Model model;

    private final ArrayList<Character> stock;
    private final Map<String, Integer> progressStock;

    /**
     * Contruct to generate new players.Each player has a name,score state,stock with the play figures and progress
     * stock that saves a history of the game(the correct expression from figures)
     * 
     * @param name
     *            name of each gamer
     * @param model
     *            the game strukture,that reacts with the player
     *
     */
    public Player(final String name, final Model model) {

        this.name = name;
        this.model = model;
        this.stock = new ArrayList<>();
        this.progressStock = new HashMap<>();

    }

    /**
     * Add epressions to the history of moves with the correct value
     * 
     * @param expression
     *            of the player
     */
    private void addToProgressStock(final String expression) {
        if (expression.length() > 2) {
            this.progressStock.put(expression, countScore(expression));

            this.score += countScore(expression);
        }
    }

    /**
     * This methods counts the numeric value of expression.
     * 
     * @param expression
     *            of the player
     * @return numeric value of the expression
     */
    private int countScore(final String expression) {
        return Word.evaluatePostfixExpression(expression);
    }

    /**
     * This method adds new figures to the bag of player.
     * 
     * @param stock
     *            the stock with the starting figures of the player
     */
    public void fillTheStock(final String stock) {
        char[] stockChar = stock.toCharArray();
        for (int i = 0; i < stockChar.length; i++) {
            this.stock.add(stockChar[i]);
        }
    }

    /**
     * This method is responsible for the placing of the figures by player.
     * 
     * @param expression
     *            to place
     * @param row
     *            coordinate of the start position of expression
     * @param col
     *            coordinate of the start position of expression
     * @param orientation
     *            Vertical or Horizontal,how to place the expession
     * @return true if correctly added,else false
     */
    public boolean placeExpression(final String expression, final int row, final int col, Orientation orientation) {
        final int expressionLength = expression.length();

        boolean checkContains = containsInStock(expression);
        // 3 capabilities by placing of the figures
        if (checkContains) {
            switch (expressionLength) {
            case 3:

                boolean checkPlaceOrig = this.model.place(expression, row, col, orientation);
                if (checkPlaceOrig) {
                    removeFromStock(expression);
                    addToProgressStock(expression);
                    return true;

                }
                break;
            case 2:

                String preCheck = precheckPlaceTwo(expression, row, col, orientation);

                if (preCheck != null) {

                    if (this.model.place(expression, row, col, orientation)) {
                        removeFromStock(expression);

                        addToProgressStock(preCheck);
                        return true;
                    }
                }
                return false;

            case 1:
                boolean checkPlaceOne = this.model.place(expression, row, col, orientation);
                if (checkPlaceOne) {
                    String newExpression = "";
                    removeFromStock(expression);
                    addToProgressStock(newExpression);
                    return true;
                }
            default:
                break;

            }
        }
        return false;
    }

    /**
     * This mehtod checks the expressions after adding new expessions(if this new expessi)
     * 
     * @param expression
     *            old expression
     * @param row
     *            coordinate of the start position of expression
     * @param col
     *            coordinate of the start position of expression
     * @param orientation
     *            Vertical or Horizontal,how to place the expession
     * @return null if new expression is false,else the String value of expression
     */
    private String precheckPlaceTwo(final String expression, final int row, final int col, Orientation orientation) {
        char tempChar;
        String newExpression = "";
        if (orientation == Orientation.HORIZONTAL) {
            tempChar = this.model.getField().getFigure(row, col + 2);
            if (new Character(tempChar).equals('#')) {
                return null;
            }
            newExpression = expression.concat(Character.toString(tempChar));
            try {
                Word.evaluatePostfixExpression(newExpression);
            } catch (IllegalArgumentException e) {
                return null;
            }

        } else if (orientation == Orientation.VERTICAL) {
            tempChar = this.model.getField().getFigure(row + 2, col);

            if (new Character(tempChar).equals('#')) {

                return null;
            }
            newExpression = expression.concat(Character.toString(tempChar));
            try {
                Word.evaluatePostfixExpression(newExpression);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }

        return newExpression;
    }

    /**
     * This method checks availability of the expression in the bag of player
     * 
     * @param expression
     *            to check
     * @return true if contains,else false
     */
    private boolean containsInStock(final String expression) {
        // true if contains,to prevent the searching the same letters
        boolean[] markStock = new boolean[this.stock.size()];
        char[] expressionArray = expression.toCharArray();
        if (expression.length() == 0) {
            return false;
        }
        for (int i = 0; i < expressionArray.length; i++) {
            if (!this.stock.contains(expressionArray[i])) {
                return false;
            }
            for (int j = 0; j < this.stock.size(); j++) {

                if (this.stock.get(j).equals(expressionArray[i])) {
                    if (markStock[j] == false) {

                        markStock[j] = true;
                        break;
                    }

                }

            }
        }
        // if the quantity of marked values and epression are equals,the full expession is in bag(without duplicates)
        return (countTrueValues(markStock) == expressionArray.length);
    }

    /**
     * This is supporting method for contains algorithm
     * 
     * @param array
     *            of the marked values
     * @return cnt of the marked values
     */
    private int countTrueValues(boolean[] array) {
        int tempCnt = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == true) {
                tempCnt++;
            }
        }
        return tempCnt;
    }

    /**
     * This method removes the expression from the bag of player
     * 
     * @param expression
     *            to remove
     */

    private void removeFromStock(final String expression) {
        char[] expressionArray = expression.toCharArray();
        // contains check!!
        for (int i = 0; i < expressionArray.length; i++) {
            for (int j = 0; j < this.stock.size(); j++) {
                if (this.stock.get(j).equals(expressionArray[i])) {
                    this.stock.remove(j);
                    break;
                }
            }
        }

    }

    /**
     * This method returns a balance of the bag by player
     * 
     * @return bag of the player
     */
    public String balanceStock() {
        String balance = "";
        Iterator<Character> it = this.stock.iterator();
        while (it.hasNext()) {
            balance += it.next();
        }

        return balance;
    }

    /**
     * This getter return a score
     * 
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the name of player
     * @return the name of player
     */
    @Override
    public String toString() {
        return this.name;
    }

}

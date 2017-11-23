package edu.kit.informatik;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * This ("static")class describes an algorithm that checks and validates expression(String) in postfix annotation.This
 * class checks correctness of the expression too.
 * 
 * @author Oleh Kuzmin <uvdxz@student.kit.edu>
 * @version 2.0
 */
public final class Word {
    /**
     * Private constructor to avoid object generation.
     */
    private Word() {

    }

    /**
     * This is an algorithm implementation. More information to the algorithm you can find in part D of the task.
     * 
     * @param expression
     *            that to check in postfix notation.
     * 
     * @return the numeric value of the expression
     * @throws IllegalArgumentException
     *             illegal expression format or size(for example:not postfix annotation)
     *
     */
    public static int evaluatePostfixExpression(final String expression) throws IllegalArgumentException {
        // expression length and correct values
        if (!preCheckExpression(expression)) {
            throw new IllegalArgumentException(Messages.ERROR_MSG_FORMAT_EXPR
                    + "(Check the size or expression contains invalid values)");
        }
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < expression.length(); i++) {
            if (Character.isDigit(expression.charAt(i))) {
                stack.push(Character.getNumericValue(expression.charAt(i)));
            } else if (isOperator(expression.charAt(i))) {
                int right = 0;
                int left = 0;
                // Fix NoSuchElementException
                if (stack.size() > 1) {

                    right = stack.pop();
                    left = stack.pop();
                }
                int result = calculate(expression.charAt(i), left, right);
                stack.push(result);
            }

        }
        // if there are more that 1 element in stack, expression is wrong
        if (!(stack.size() == 1)) {
            throw new IllegalArgumentException(Messages.ERROR_MSG_FORMAT_EXPR + "(Check the postfix annotation)");
        }
        return stack.pop();
    }

    /**
     * Supporting method for algorithm.
     * 
     * @param operator
     *            math operator
     * @param right
     *            number
     * @param left
     *            number
     * @return -1 if false or result of math operation
     */
    private static int calculate(final Character operator, final int right, final int left) {

        switch (operator) {
        case '+':
            return right + left;
        case '-':
            return right - left;
        case '*':
            return right * left;
        default:
            return -1;

        }

    }

    /**
     * Supporting method for algorithm that checks an expression like the precondition.
     * 
     * @param expression
     *            to check
     * @return true if correct, false if incorrect format
     */
    private static boolean preCheckExpression(final String expression) {

        return expression.matches("[0-9+*-]*") && expression.length() > 1;
    }

    /**
     * Supporting method for algorithm that determines and checks correct (Math)operator("+","-",*")
     * 
     * @param character
     *            to check
     * @return true if correct, false if incorrect or operator is unsupported
     */
    private static boolean isOperator(final Character character) {

        return character.charValue() == 43 || character.charValue() == 45 || character.charValue() == 42;

    }

    /**
     * This method additional checks(precondition) of expression for 3 or 1 elements.
     * 
     * @param expression
     *            to check
     * @return true if correct, false if incorrect or unsupported
     */
    public static boolean checkExpression(final String expression) {
        return expression.matches("[0-9+*-]{1,3}");
    }

}

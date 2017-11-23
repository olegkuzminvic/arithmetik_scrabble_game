package edu.kit.informatik;

/**
 * This is pseudo class for output by the "mvc pattern". It was supposed to be here (for example) GUI implementation,but
 * i wanted to simulate the view part.
 * 
 * @author Oleh Kuzmin <uvdxz@student.kit.edu>
 * @version 1.0
 *
 */
public final class View {
    /**
     * Private constructor to avoid object generation.
     */
    private View() {

    }

    /**
     * Print a String to the standard output.
     * 
     * @param output
     *            String to print
     */
    public static void printLine(final String output) {
        Terminal.printLine(output);
    }
}

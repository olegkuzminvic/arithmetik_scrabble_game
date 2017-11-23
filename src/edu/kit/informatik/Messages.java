package edu.kit.informatik;

/**
 * This (static)class contains all error,notification messages of the game.
 * 
 * @author Oleh Kuzmin<uvdxz@student.kit.edu>
 * @version 1.0
 */
public final class Messages {
    // CLA handling
    /**
     * Error, your input has invalid format!
     */
    public static final String ERROR_MSG_FORMAT_CLA = "Error, your input has invalid format!";
    /**
     * Error, your input has invalid size!
     */
    public static final String ERROR_MSG_SIZE_CLA = "Error, your input has invalid size!";
    /**
     * Error, your input has invalid size of line!
     */
    public static final String ERROR_MSG_SIZE_LINE_CLA = "Error, your input has invalid size of line!";
    /**
     * Error, your number value is false!
     */
    // Access to struktur
    public static final String ERROR_MSG_ACCESS_STRUCT = "Error, your number value is false!";
    // Expression handling
    /**
     * Error, expression has wrong format!
     */
    public static final String ERROR_MSG_FORMAT_EXPR = "Error, expression has wrong format!";

    // User input handling
    /**
     * Error, your command has wrong format!
     */
    public static final String ERROR_MSG_FORMAT_USER = "Error, your command has wrong format!";
    /**
     * Error, your command has wrong format(size)!
     */
    public static final String ERROR_MSG_SIZE_FORMAT_USER = "Error, your command has wrong format(size)!";
    /**
     * Error, your command has wrong argument!
     */
    public static final String ERROR_MSG_ARG_FORMAT_USER = "Error, your command has wrong argument!";
    /**
     * Error, your command is not supported!
     */
    public static final String ERROR_MSG_COMMAND_UNSUPPORTED = "Error, your command  is not supported!";
    /**
     * Error, your comands argument is empty!
     */
    public static final String ERROR_MSG_EMPTY_ARG = "Error, your comands argument is empty!";
    /**
     * OK
     */
    // Notification messages by user input
    public static final String NOTIFI_MSG_OK = "OK";
    /**
     * P1 wins
     */
    public static final String NOTIFI_MSG_WIN_P1 = "P1 wins";
    /**
     * P2 wins
     */
    public static final String NOTIFI_MSG_WIN_P2 = "P2 wins";
    /**
     * draw
     */
    public static final String NOTIFI_MSG_DRAW = "draw";

    /**
     * Private constructor to avoid object generation.
     */
    private Messages() {

    }

}

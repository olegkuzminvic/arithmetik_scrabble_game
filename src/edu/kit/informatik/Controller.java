package edu.kit.informatik;

/**
 * Input handling class.
 * 
 * 
 * @author Oleh Kuzmin <uvdxz@student.kit.edu>
 * @version 1.0
 */
public final class Controller {
    // User input command
    private static final String PLACE = "place";
    private static final String END = "end";
    private static final String SCORE = "score";
    private static final String BAG = "bag";
    private static final String ROW_PRINT = "rowprint";
    private static final String COL_PRINT = "colprint";
    private static final String QUIT = "quit";

    private final String[] args;
    private final Model model;
    // by init activePlayer is the first player
    private Player activePlayer;
    private Player firstPlayer;
    private Player secondPlayer;

    private boolean commandEndUsed = false;

    /**
     * Created new Game,2 Players,set active player default to the P1
     * 
     * @param args
     *            CLA
     * 
     */
    public Controller(final String[] args) {

        this.model = new Model();
        this.args = args;
        this.firstPlayer = new Player("P1", this.model);
        this.secondPlayer = new Player("P2", this.model);
        this.activePlayer = firstPlayer;

    }

    /**
     * This method started the game.
     */
    public void start() {
        readCla(this.args);
        initReadUser();

    }

    // Methods for INPUT HANDLING
    /**
     * Command Line Argument handling
     * 
     * @param args
     *            of the Main class
     */
    private void readCla(final String[] args) {
        if (checkArgs(args)) {
            handleCla(args);
        } else {
            // Exit
            System.exit(1);
        }

    }

    /**
     * This is supporting method by the CLA handling.
     * 
     * @param args
     *            from the Main
     * @return {true}if correctly format, else {false}
     */

    private boolean checkArgs(final String[] args) {
        boolean checkFormat = false;
        boolean checkSizeLine = false;
        if (args.length == 2) {
            for (int i = 0; i < args.length; i++) {
                checkFormat = args[i].matches("[0-9*+-]+");
                // multiset check
                checkSizeLine = (args[i].length() >= 2);
                if (!checkFormat) {
                    View.printLine(String.format("%s(%s)", Messages.ERROR_MSG_FORMAT_CLA, args[i]));
                    return false;
                } else if (!checkSizeLine) {
                    View.printLine(String.format("%s(%d, but expected 2 or more)", Messages.ERROR_MSG_SIZE_LINE_CLA,
                            args[i].length()));
                    return false;
                }

            }
            return true;

        } else
            View.printLine(String.format("%s(%d,but expected 2)", Messages.ERROR_MSG_SIZE_CLA, args.length));

        return false;
    }

    /**
     * This method handles CLA. It fills the bag of players.
     * 
     * @param args
     *            CLA
     */
    private void handleCla(final String[] args) {
        final String figuresP1 = args[0];
        final String figuresP2 = args[1];
        this.firstPlayer.fillTheStock(figuresP1);
        this.secondPlayer.fillTheStock(figuresP2);
    }

    /**
     * User Input handling
     */
    private void initReadUser() {
        String read = Terminal.readLine();
        while (!isQuit(read)) {
            handleInputwithModule(read);
            read = Terminal.readLine();
        }
    }

    /**
     * Exit of the game
     * 
     * @param command
     *            of the player
     * @return true if quit were used
     */
    private boolean isQuit(final String command) {
        return command.equals(QUIT);
    }

    private void handleInputwithModule(final String input) {
        String trimInput = input.trim();
        // 2 splitted string for simplify by parsing
        String[] splittedInput = trimInput.split(" ");
        String[] splittedInputSemikolon = trimInput.split(";");
        // check command
        switch (splittedInput[0]) {
        case PLACE:

            if (checkCommandPlace(splittedInputSemikolon) && !commandEndUsed) {
                this.model.getField().setActivePlayer(activePlayer);
                if (handlePlace(splittedInputSemikolon)) {

                    View.printLine(Messages.NOTIFI_MSG_OK);
                    switchPlayer();

                     View.printLine(this.activePlayer + "\n" + this.model.getField().toString());
                     View.printLine(this.model.getField().getPlayerMap());
                } else
                    View.printLine(Messages.ERROR_MSG_SIZE_FORMAT_USER);
            } else
                View.printLine(Messages.ERROR_MSG_SIZE_FORMAT_USER);

            break;
        case END:
            String endTemp = handleEnd(splittedInput);
            if (endTemp != null) {
                commandEndUsed = true;
                View.printLine(endTemp);
            } else {
                View.printLine(String.format("%s", Messages.ERROR_MSG_FORMAT_USER));
            }
            break;
        case SCORE:
            int score = handleScore(splittedInput);
            if (score != 387420489) {
                View.printLine(score + "");

            } else {

                View.printLine(String.format("%s", Messages.ERROR_MSG_FORMAT_USER));
            }
            break;
        case BAG:
            String bag = handleBag(splittedInput);
            if (bag != null) {
                View.printLine(handleBag(splittedInput));
            }
            break;
        case ROW_PRINT:
            String rows = handleRowPrint(splittedInput);
            if (rows != null) {
                View.printLine(handleRowPrint(splittedInput));
            }
            break;
        case COL_PRINT:
            String columns = handleColPrint(splittedInput);
            if (columns != null) {
                View.printLine(handleColPrint(splittedInput));
            }
            break;
        // not supported commands
        default:
            View.printLine(String.format("%s(%s not found)", Messages.ERROR_MSG_COMMAND_UNSUPPORTED, splittedInput[0]));
            break;
        }

    }

    // Commands handling
    private boolean handlePlace(final String[] splittedInputSemikolon) throws IllegalArgumentException {

        String tempFigures = splittedInputSemikolon[0].replace("place ", "").trim();
        int tempRow = Integer.parseInt(splittedInputSemikolon[1].trim());
        int tempCol = Integer.parseInt(splittedInputSemikolon[2].trim());
        String tempOrient = splittedInputSemikolon[3].trim();

        Orientation orientation = null;
        if (tempOrient.equals("V")) {
            orientation = Orientation.VERTICAL;

        } else if (tempOrient.equals("H")) {
            orientation = Orientation.HORIZONTAL;

        }
        return this.activePlayer.placeExpression(tempFigures, tempRow, tempCol, orientation);

    }

    private String handleEnd(final String[] splittedInput) {
        String output = null;
        if (splittedInput.length == 1) {
            int currScoreP1 = this.firstPlayer.getScore();
            int currScoreP2 = this.secondPlayer.getScore();
            // P1 win
            if (currScoreP1 > currScoreP2) {
                output = currScoreP1 + "\n" + currScoreP2 + "\n" + Messages.NOTIFI_MSG_WIN_P1;
                // draw
            } else if (currScoreP1 == currScoreP2) {
                output = currScoreP1 + "\n" + currScoreP2 + "\n" + Messages.NOTIFI_MSG_DRAW;
                // P2 win
            } else {
                output = currScoreP1 + "\n" + currScoreP2 + "\n" + Messages.NOTIFI_MSG_WIN_P2;
            }
        }
        return output;
    }

    private int handleScore(final String[] splittedInput) throws IllegalArgumentException {
        int tempScore = 387420489;

        if (splittedInput.length == 2) {

            if (splittedInput[1].trim().equals(this.firstPlayer.toString())) {
                tempScore = this.firstPlayer.getScore();
            } else if (splittedInput[1].trim().equals(this.secondPlayer.toString()))
                tempScore = this.secondPlayer.getScore();

        }

        return tempScore;

    }

    private String handleBag(final String[] splittedInput) {
        if (!checkCommandSize(splittedInput.length)) {
            return null;
        }
        String player = splittedInput[1];
        String tempBag = "";
        if (splittedInput[1].trim().equals(this.firstPlayer.toString())) {
            tempBag = this.firstPlayer.balanceStock();
        } else if (splittedInput[1].trim().equals(this.secondPlayer.toString()))
            tempBag = this.secondPlayer.balanceStock();
        else {
            View.printLine(String.format("%s(%s ,but expected P1 or P2)", Messages.ERROR_MSG_ARG_FORMAT_USER, player));
            return null;
        }

        return tempBag;
    }

    private String handleRowPrint(final String[] splittedInput) {
        int tempRow = -1;
        String outRow = "";

        if (!checkCommandSize(splittedInput.length)) {
            return null;
        }
        try {
            tempRow = Integer.parseInt(splittedInput[1].trim());
            outRow = this.model.getField().rowPrint(tempRow);
            // Parse int handling
        } catch (NumberFormatException e) {
            View.printLine(String.format("%s(%s)", Messages.ERROR_MSG_ARG_FORMAT_USER, splittedInput[1]
                    + " ,but expected int value"));
            return null;
            // Access to unavaible coordinates
        } catch (IllegalArgumentException e) {
            View.printLine(String.format("%s(%s, but expected >=0 and < %d)", Messages.ERROR_MSG_FORMAT_USER,
                    splittedInput[1], this.model.getField().getSize()));
            return null;
        }

        return outRow;

    }

    private String handleColPrint(final String[] splittedInput) {
        int tempCol = -1;
        String outputCol = "";
        if (!checkCommandSize(splittedInput.length)) {
            return null;
        }
        try {
            tempCol = Integer.parseInt(splittedInput[1].trim());
            outputCol = this.model.getField().colPrint(tempCol);
            // Parse int handling
        } catch (NumberFormatException e) {
            View.printLine(String.format("%s(%s)", Messages.ERROR_MSG_ARG_FORMAT_USER, splittedInput[1]
                    + " ,but expected int value"));
            return null;
            // Access to unavaible coordinates
        } catch (IllegalArgumentException e) {
            View.printLine(String.format("%s(%s, but expected >=0 and < %d)", Messages.ERROR_MSG_FORMAT_USER,
                    splittedInput[1], this.model.getField().getSize()));
            return null;
        }

        return outputCol;

    }

    // additional check methods for handling of commands
    private boolean checkCommandPlace(final String[] splittedInputSemikolon) {

        // 1-3 figures only [0-9+*-]
        boolean tempCheckExpression = false;
        // 0-9 only
        boolean tempCheckCoordinate = false;
        // only "V" or "H"
        boolean tempCheckOrientation = false;
        if (splittedInputSemikolon.length == 4) {

            final String tempFigures = splittedInputSemikolon[0].replace("place ", "").trim();
            final String tempOrient = splittedInputSemikolon[3].trim();
            tempCheckExpression = Word.checkExpression(tempFigures);

            int tempRow = -1;
            int tempCol = -1;

            try {
                tempRow = Integer.parseInt(splittedInputSemikolon[1].trim());
                tempCol = Integer.parseInt(splittedInputSemikolon[2].trim());
            } catch (NumberFormatException e) {
                return false;
            }
            if ((tempRow >= 0 && tempRow < 10)) {
                if (tempCol >= 0 && tempCol < 10) {
                    tempCheckCoordinate = true;
                }
            }
            if (tempOrient.equals("V") || tempOrient.equals("H")) {
                tempCheckOrientation = true;
            }
        }
        return tempCheckExpression && tempCheckCoordinate && tempCheckOrientation;
    }

    private boolean checkCommandSize(final int length) {
        switch (length) {
        case 1:
            View.printLine(String.format("%s", Messages.ERROR_MSG_EMPTY_ARG));
            return false;

        case 2:

            return true;

        default:
            View.printLine(String.format("%s(%d,but expected 2)", Messages.ERROR_MSG_SIZE_FORMAT_USER, length));
            return false;

        }

    }

    private void switchPlayer() {
        if (this.activePlayer == this.firstPlayer) {
            this.activePlayer = secondPlayer;
        } else if (this.activePlayer == this.secondPlayer) {
            this.activePlayer = this.firstPlayer;
        }

    }
}

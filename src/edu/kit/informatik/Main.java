package edu.kit.informatik;

/**
 * Here i use a "very simplified MVC" pattern to divides app into three parts .(I/O(by view and controller) and the
 * model)
 * 
 * @author Oleh Kuzmin<uvdxz@student.kit.edu>
 * @version 1.0
 */
public class Main {
    /**
     * 
     * @param args
     *            input of the 2 starting bag by player1 and player 2
     */
    public static void main(String[] args) {
        Controller controller = new Controller(args);
        controller.start();

    }
}

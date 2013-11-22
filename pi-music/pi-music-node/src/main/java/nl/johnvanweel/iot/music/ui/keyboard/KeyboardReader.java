package nl.johnvanweel.iot.music.ui.keyboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Component to read keyboard input
 */
public class KeyboardReader implements Runnable {
    private KeyboardCommandHandler keyboardController;

    public KeyboardReader(KeyboardCommandHandler keyboardController) {
        this.keyboardController = keyboardController;
    }

    @Override
    public void run() {
        String currentLine = ""; // Line read from standard in

        System.out.println("Enter a line of text (type 'quit' to exit): ");
        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);


        while (!(currentLine.equals("quit"))) {
            try {
                currentLine = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            keyboardController.handleCommand(currentLine);
        }
    }
}

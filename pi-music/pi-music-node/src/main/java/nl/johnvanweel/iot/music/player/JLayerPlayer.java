package nl.johnvanweel.iot.music.player;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.PipedInputStream;

/**
 *
 */
public class JLayerPlayer extends Thread {


    private PipedInputStream inputStream;

    public JLayerPlayer(PipedInputStream inputStream) {

        this.inputStream = inputStream;
    }

    @Override
    public void run() {

        try {
            new Player(inputStream).play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
}

package nl.johnvanweel.iot.music.ui.keyboard;

import nl.johnvanweel.iot.music.service.PiMusicService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Interprets keyboard events.
 */
public class KeyboardController implements KeyboardCommandHandler {
    private final PiMusicService service;

    @Autowired
    public KeyboardController(final PiMusicService service) {
        this.service = service;
    }

    @PostConstruct
    public void postConstruct() {
        new Thread(new KeyboardReader(this)).start();
    }

    public void handleCommand(String input) {
        if (input.startsWith("play ")) {
            service.search(input.substring(5, input.length()));
        }
    }
}

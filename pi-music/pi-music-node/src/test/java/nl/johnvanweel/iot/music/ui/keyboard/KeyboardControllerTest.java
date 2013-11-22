package nl.johnvanweel.iot.music.ui.keyboard;

import nl.johnvanweel.iot.music.service.PiMusicService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class KeyboardControllerTest {
    private KeyboardController controller;

    private PiMusicService service;

    @Before
    public void setUp() throws Exception {
        service = mock(PiMusicService.class);
        controller = new KeyboardController(service);
    }

    @Test
    public void testPlayCommand() throws Exception {
        controller.handleCommand("play Black Sabbath");

        verify(service).search("Black Sabbath");
    }
}

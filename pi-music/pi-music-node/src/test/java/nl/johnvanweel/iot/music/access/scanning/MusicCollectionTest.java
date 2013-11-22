package nl.johnvanweel.iot.music.access.scanning;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class MusicCollectionTest {
    private MusicCollection collection;

    @Before
    public void setUp() throws Exception {
        collection = new MusicCollection();
    }

    @Test
    public void testFindByTitle() throws Exception {
        MusicFile file = new MusicFile(null, "artist", "title", "filename");
        collection.setMusicList(Arrays.asList(file));

        assertEquals(file, collection.findMusic("title").findAny().get());
    }

    @Test
    public void testFindByArtist() throws Exception {
        MusicFile file = new MusicFile(null, "artist", "title", "filename");
        collection.setMusicList(Arrays.asList(file));

        assertEquals(file, collection.findMusic("artist").findAny().get());
    }

    @Test
    public void testFindByFilename() throws Exception {
        MusicFile file = new MusicFile(null, "artist", "title", "filename");
        collection.setMusicList(Arrays.asList(file));

        assertEquals(file, collection.findMusic("filename").findAny().get());
    }
}

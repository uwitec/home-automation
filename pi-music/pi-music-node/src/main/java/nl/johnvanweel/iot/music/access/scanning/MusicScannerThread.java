package nl.johnvanweel.iot.music.access.scanning;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * User: John van Weel
 * Date: 1/16/12
 * Time: 9:29 PM
 */
public class MusicScannerThread extends Thread {
    private final String location;
    private final List<MusicFile> musicFiles;

    public MusicScannerThread(String location, List<MusicFile> musicFiles) {
        this.location = location;
        this.musicFiles = musicFiles;
    }


    @Override
    public void run() {
        try {
            scanDirectory(Paths.get(location));

            if (musicFiles.isEmpty()) {
                System.err.println("No mp3 files found on " + location);
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void scanDirectory(Path p) throws IOException {
        FileVisitor<Path> visitor = new MusicScannerFileVisitor();

        Files.walkFileTree(p, visitor);
    }

    private class MusicScannerFileVisitor extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (file.getFileName().toString().endsWith(".mp3")) {
                try {
                    MP3File mp3 = new MP3File(file.toFile());
                    if (mp3.getID3v2Tag() != null) {
                        String artist = mp3.getID3v2Tag().getAuthorComposer();
                        String title = mp3.getID3v2Tag().getSongTitle();
                        String filename = file.getFileName().toString();
                        Path location = file;

                        System.out.println(String.format("Scanned file: %s - %s", artist, title));

                        musicFiles.add(new MusicFile(location, artist, title, filename));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TagException e) {
                    e.printStackTrace();
                }

            }

            return FileVisitResult.CONTINUE;
        }
    }
}

package nl.johnvanweel.iot.music.player;

import org.gstreamer.*;
import org.gstreamer.elements.DecodeBin2;
import org.gstreamer.io.InputStreamSrc;

import java.io.InputStream;

/**
 * Thread that starts a GStreamer player around the given stream.
 */
public class GStreamerPlayer extends Thread {
    private static final String name = "PiGStreamer";

    private final InputStream stream;

    public GStreamerPlayer(InputStream stream) {
        this.stream = stream;
    }

    @Override
    public void run() {
        play(stream);
    }

    private void play(final InputStream stream) {
        Gst.init(name, new String[]{});

        Element src = new InputStreamSrc(stream, "input file");

        DecodeBin2 decodeBin = (DecodeBin2) ElementFactory.make("decodebin2", "Decode Bin");
        Pipeline pipe = new Pipeline("main pipeline");
        pipe.addMany(src, decodeBin);
        src.link(decodeBin);

				/* create audio output */
        final Bin audioBin = new Bin("Audio Bin");

        Element conv = ElementFactory.make("audioconvert", "Audio Convert");
        Element sink = ElementFactory.make("autoaudiosink", "sink");
        audioBin.addMany(conv, sink);
        Element.linkMany(conv, sink);
        audioBin.addPad(new GhostPad("sink", conv.getStaticPad("sink")));

        pipe.add(audioBin);

        decodeBin.connect(new DecodeBin2.NEW_DECODED_PAD() {
            public void newDecodedPad(DecodeBin2 elem, Pad pad, boolean last) {
                                /* only link once */
                Pad audioPad = audioBin.getStaticPad("sink");
                if (pad.isLinked()) {
                    return;
                }

								/* check media type */
                Caps caps = pad.getCaps();
                Structure struct = caps.getStructure(0);
                if (struct.getName().startsWith("audio/")) {
                    System.out.println("Got audio pad");
                                        /* link'n'play */
                    pad.link(audioPad);

                }

            }
        });

        pipe.play();
        Gst.main();
    }
}

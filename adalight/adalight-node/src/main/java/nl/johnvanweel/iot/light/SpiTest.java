package nl.johnvanweel.iot.light;

import com.pi4j.wiringpi.Spi;

import java.util.Arrays;
import java.util.Random;

/**
 *
 */
public class SpiTest {
    private static final int GAMMA_LENGTH = 256;
    private static final byte[] GAMMA = new byte[GAMMA_LENGTH];

    static {
        for (int i = 0; i < GAMMA_LENGTH; i++) {
            int j = (int) (Math.pow(((float) i) / 255.0, 2.5) * 127.0 + 0.5);
            GAMMA[i] = (byte) (0x80 | j);
        }

//
//        for (int i = 0,j=-127; i < GAMMA_LENGTH; i++,j++) {
//            GAMMA[i] = (byte) j;
//        }

        System.out.println(Arrays.toString(GAMMA));
    }


    private static final int numberOfLeds = 20;

    public static final void main(String... args) throws InterruptedException {
        int fd = Spi.wiringPiSPISetup(0, 10000000);
        if (fd <= -1) {
            System.out.println("SPI initialization FAILED.");
            return;
        }
        System.out.println("SPI initialization SUCCEEDED.");


        Random r = new Random();

        RGBLed led = new RGBLed(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), 1);
        update(led);
        System.out.println(led);
    }

    /**
     * Update the strip in order to show its new settings.
     */
    public static void update(RGBLed led) {

        final byte packet[] = new byte[numberOfLeds * 3];

        for (int i = 0; i < numberOfLeds; i++) {
            packet[(i * 3)] = led.getGreen();
            packet[(i * 3) + 1] = led.getRed();
            packet[(i * 3) + 2] = led.getBlue();
        }

        // Update the strand
        System.out.println(Arrays.toString(packet));
        Spi.wiringPiSPIDataRW(0, packet, numberOfLeds * 3);

        byte endPacket[] = {(byte) 0x00};

        // Flush the update
        Spi.wiringPiSPIDataRW(0, endPacket, 1);
    }

    private static class RGBLed {
        private byte red;
        private byte green;
        private byte blue;

        /**
         * Initiate a single led in a led strip.
         *
         * @param red        value between 0 and 255 for the red led
         * @param green      value between 0 and 255 for the green led
         * @param blue       value between 0 and 255 for the blue led
         * @param brightness overall brightness for the led combination
         */
        public RGBLed(final int red, final int green, final int blue, final float brightness) {
            this.red = GAMMA[(int) (red * brightness)];
            this.green = GAMMA[(int) (green * brightness)];
            this.blue = GAMMA[(int) (blue * brightness)];
        }

        /**
         * @return the value for the green led (between 0 and 255)
         */
        public byte getGreen() {
            return green;
        }

        /**
         * @return the value for the blue led (between 0 and 255)
         */
        public byte getBlue() {
            return blue;
        }

        /**
         * @return the value for the red led (between 0 and 255)
         */
        public byte getRed() {
            return red;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("RGBLed{");
            sb.append("red=").append(red);
            sb.append(", green=").append(green);
            sb.append(", blue=").append(blue);
            sb.append('}');
            return sb.toString();
        }
    }
}

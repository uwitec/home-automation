package nl.johnvanweel.iot.light.service;

import com.sun.jna.Pointer;
import jdk.nashorn.internal.ir.annotations.Ignore;
import nl.johnvanweel.iot.light.access.boblight.BobILightService;
import nl.johnvanweel.iot.light.access.boblight.BoblightDaemon;
import nl.johnvanweel.iot.light.access.boblight.BoblightDeamonFactory;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.AdditionalMatchers.aryEq;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 *
 */
@Ignore
public class BobServiceTest {
    private BobILightService controller;

    private BoblightDeamonFactory factory;

    private BoblightDaemon daemon;
    private Pointer bobPointer;

    @Before
    public void setUp() throws Exception {
        factory = mock(BoblightDeamonFactory.class);
        daemon = mock(BoblightDaemon.class);
        bobPointer = mock(Pointer.class);

        int SUCCESS_CODE = 1;
        when(daemon.boblight_connect(eq(bobPointer), eq(null), eq(-1), anyInt())).thenReturn(SUCCESS_CODE);
        when(daemon.boblight_setpriority(eq(bobPointer), anyInt())).thenReturn(SUCCESS_CODE);

        when(factory.getInstance()).thenReturn(daemon);
        when(daemon.boblight_init()).thenReturn(bobPointer);

        controller = new BobILightService(factory);
    }

    @Test
    public void testConstructionCreatesDaemon() throws Exception {
        verify(factory).getInstance();
    }

    @Test
    public void testPrioritySet() throws Exception {
        verify(daemon).boblight_setpriority(bobPointer, 128);
    }

    @Test(expected = LightException.class)
    public void testExceptionWhenConnectionFailed() throws Exception {
        int FAILURE_CODE = 0;
        when(daemon.boblight_connect(eq(bobPointer), eq(null), eq(-1), anyInt())).thenReturn(FAILURE_CODE);

        new BobILightService(factory);
    }

    @Test
    public void testSetPixel() throws Exception {
        controller.setPixel(0, 255, 255, 255);

        verify(daemon).boblight_addpixel(eq(bobPointer), eq(0), aryEq(new int[]{255, 255, 255}));
    }

    @Test
    public void testSetAllPixels() throws Exception {
        controller.allPixels(255, 255, 255);

        verify(daemon).boblight_addpixel(eq(bobPointer), eq(-1), aryEq(new int[]{255, 255, 255}));
    }

    @Test
    public void testSend() throws Exception {
        controller.send();

        verify(daemon).boblight_sendrgb(eq(bobPointer), eq(1), eq(new int[]{255}));
    }
}

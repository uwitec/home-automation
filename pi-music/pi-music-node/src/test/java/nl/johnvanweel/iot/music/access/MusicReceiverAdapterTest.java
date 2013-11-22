package nl.johnvanweel.iot.music.access;

import org.jgroups.Message;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.mockito.Mockito.*;

public class MusicReceiverAdapterTest {
    private MusicReceiverAdapter adapter;
    private IMessageListener messageListener;

    @Before
    public void setUp() throws Exception {
        messageListener = mock(IMessageListener.class);

        adapter = new MusicReceiverAdapter(messageListener);
    }

    @Test
    public void testMessageDelegation() throws Exception {
        Message m = new Message();

        when(messageListener.canHandle(m)).thenReturn(true);

        adapter.receive(m);

        verify(messageListener).handle(m);
    }

    @Test
    public void testUnknownDelegation() throws Exception {
        Message m = new Message();

        when(messageListener.canHandle(m)).thenReturn(false);

        adapter.receive(m);

        verify(messageListener, VerificationModeFactory.times(0)).handle(m);
    }
}

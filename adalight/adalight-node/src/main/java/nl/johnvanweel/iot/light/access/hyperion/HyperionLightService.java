package nl.johnvanweel.iot.light.access.hyperion;

import nl.johnvanweel.iot.light.service.ILightService;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static proto.Message.*;

/**
 * Created by john on 3/19/16.
 */
@org.springframework.stereotype.Component
public class HyperionLightService implements ILightService {
    private final Logger log = Logger.getLogger(HyperionLightService.class);

    private Socket mSocket;

    @PostConstruct
    public void postConstruct() throws IOException {
        log.info("connecting to pi");
        mSocket = new Socket("192.168.0.24", 19445);
    }

    @PreDestroy
    public void close() throws IOException {
        if (mSocket != null && mSocket.isConnected()) {
            mSocket.close();
        }
    }

    private Color color = new Color(255, 255, 255);

    @Override
    public void setPixel(int pixelNumber, int red, int green, int blue) {
        allPixels(red, green, blue);
    }

    @Override
    public void allPixels(int red, int green, int blue) {
        color = new Color(red, green, blue);
        send();
    }

    @Override
    public void send() {
        try {
            setColor(color, 50);
        } catch (IOException e) {
            log.error("Cannot set color!", e);
        }
    }


    @Override
    protected void finalize() throws Throwable {
        if (mSocket != null && mSocket.isConnected()) {
            mSocket.close();
        }
        super.finalize();
    }

    public void clear(int priority) throws IOException {
        ClearRequest clearRequest = ClearRequest.newBuilder()
                .setPriority(priority)
                .build();

        HyperionRequest request = HyperionRequest.newBuilder()
                .setCommand(HyperionRequest.Command.CLEAR)
                .setExtension(ClearRequest.clearRequest, clearRequest)
                .build();

        sendRequest(request);
    }

    public void clearall() throws IOException {
        HyperionRequest request = HyperionRequest.newBuilder()
                .setCommand(HyperionRequest.Command.CLEARALL)
                .build();

        sendRequest(request);
    }

    public void setColor(Color color, int priority) throws IOException {
        setColor(color, priority, -1);
    }

    public void setColor(Color color, int priority, int duration_ms) throws IOException {
        ColorRequest colorRequest = ColorRequest.newBuilder()
                .setRgbColor(color.getRGB())
                .setPriority(priority)
                .setDuration(duration_ms)
                .build();

        HyperionRequest request = HyperionRequest.newBuilder()
                .setCommand(HyperionRequest.Command.COLOR)
                .setExtension(ColorRequest.colorRequest, colorRequest)
                .build();

        sendRequest(request);
    }

    private void sendRequest(HyperionRequest request) throws IOException {
        int size = request.getSerializedSize();

        // create the header
        byte[] header = new byte[4];
        header[0] = (byte) ((size >> 24) & 0xFF);
        header[1] = (byte) ((size >> 16) & 0xFF);
        header[2] = (byte) ((size >> 8) & 0xFF);
        header[3] = (byte) ((size) & 0xFF);

        // write the data to the socket
        OutputStream output = mSocket.getOutputStream();
        output.write(header);
        request.writeTo(output);
        output.flush();

        HyperionReply reply = receiveReply();
        if (!reply.getSuccess()) {
            System.out.println(reply.toString());
        }
    }

    private HyperionReply receiveReply() throws IOException {
        InputStream input = mSocket.getInputStream();

        byte[] header = new byte[4];
        input.read(header, 0, 4);
        int size = (header[0] << 24) | (header[1] << 16) | (header[2] << 8) | (header[3]);
        byte[] data = new byte[size];
        input.read(data, 0, size);
        HyperionReply reply = HyperionReply.parseFrom(data);

        return reply;
    }
}

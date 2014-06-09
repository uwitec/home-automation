package nl.johnvanweel.iot.light.debugger;

import nl.johnvanweel.iot.light.service.ILightService;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class DebugLightsPanel extends JPanel implements ILightService {
	private Color[] colors = new Color[20];
	private Color[] colorsBuffer = new Color[20];
	private Graphics g;



	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < colors.length; i++) {
			if (colors[i] != null) {
				g.setColor(colors[i]);
				g.fillOval(28 * i + 20, 100, 25, 25);
			}
		}
	}

	@Override
	public void setPixel(int pixelNumber, int red, int green, int blue) {
		System.out.println("setPixel " + pixelNumber + " " + red + " " + green + " " + blue);
		if (pixelNumber < colorsBuffer.length) {
			colorsBuffer[pixelNumber] = new Color(red, green, blue);
		}
	}

	@Override
	public void allPixels(int red, int green, int blue) {
		for (int i = 0; i < colorsBuffer.length; i++) {
			colorsBuffer[i] = new Color(red, green, blue);
		}

		send();
	}

	@Override
	public void send() {
		colors = colorsBuffer;
		colorsBuffer = new Color[20];
		repaint();
	}
}


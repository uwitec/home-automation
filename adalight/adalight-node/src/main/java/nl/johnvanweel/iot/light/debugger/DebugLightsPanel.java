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

	private Color[] gradientTest = new Color[100];

	JSlider sliderRed = new JSlider(0, 128, 127);
	JSlider sliderGreen = new JSlider(0, 128, 127);
	JSlider sliderBlue = new JSlider(0, 128, 127);
	JSlider sliderR = new JSlider(0, 10, 1);
	JSlider sliderG = new JSlider(0, 10, 1);
	JSlider sliderB = new JSlider(0, 10, 1);


	public DebugLightsPanel() {
		sliderRed.setMajorTickSpacing(10);
		sliderRed.setMinorTickSpacing(1);
		add(sliderRed);
		sliderGreen.setMajorTickSpacing(10);
		sliderGreen.setMinorTickSpacing(1);
		add(sliderGreen);
		sliderBlue.setMajorTickSpacing(10);
		sliderBlue.setMinorTickSpacing(1);
		add(sliderBlue);
		sliderR.setMajorTickSpacing(10);
		sliderR.setMinorTickSpacing(1);
		add(sliderR);
		sliderG.setMajorTickSpacing(10);
		sliderG.setMinorTickSpacing(1);
		add(sliderG);
		sliderB.setMajorTickSpacing(10);
		sliderB.setMinorTickSpacing(1);
		add(sliderB);

		new Thread(new Runnable() {
			@Override
			public void run() {


				while (true) {
					double frequency = ((double) sliderR.getValue()) / 10D;

					for (int i = 0; i < 100; i++) {
						int red = (int) (Math.sin((frequency) * i + sliderR.getValue()) * sliderRed.getValue() + 128);
						int green = (int) (Math.sin((frequency)* i + sliderG.getValue()) * sliderGreen.getValue() + 128);
						int blue = (int) (Math.sin((frequency)* i + sliderB.getValue()) * sliderBlue.getValue() + 128);

						gradientTest[i] = new Color(red, green, blue);
					}
				}
			}
		}).start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < colors.length; i++) {
			if (colors[i] != null) {
				g.setColor(colors[i]);
				g.fillOval(28 * i + 20, 100, 25, 25);
			}
		}

		for (int i = 0; i < gradientTest.length; i++) {
			if (gradientTest[i] != null) {
				g.setColor(gradientTest[i]);
				g.fillRect(200 + (3 * i), 100, 3, 250);
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


package nl.johnvanweel.iot.light.step;

import nl.johnvanweel.iot.light.service.ILightService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: jvweel
 * Date: 6/9/14
 * Time: 4:55 PM
 */
public class SettableGradient implements Steppable {
	private final int[][] colorGradient = new int[][]{{0, 0, 255}, {28, 15, 226}, {56, 31, 198}, {85, 46, 170}, {113, 62, 141}, {141, 77, 113}, {170, 93, 85}, {198, 108, 56}, {226, 124, 28}, {255, 140, 0}, {255, 124, 0}, {255, 108, 0}, {255, 93, 0}, {277, 73, 0}, {255, 62, 0}, {255, 46, 0}, {255, 31, 0}, {255, 15, 0}, {255, 0, 0}, {255, 0, 0}};

	private final ILightService lightService;
	private int gradientStep;

	@Autowired
	public SettableGradient(final ILightService lightService) {
		this.lightService = lightService;
	}


	@Override
	public void step() {
//		lightService.allPixels(colorGradient[gradientStep][0], colorGradient[gradientStep][1], colorGradient[gradientStep][2]);
		for (int i = 0; i  <gradientStep;i++){
			lightService.setPixel(i+1, colorGradient[i][0], colorGradient[i][1], colorGradient[i][2]);
		}

		lightService.send();
	}

	public void setGradientStep(int gradientStep) {
		this.gradientStep = gradientStep;
	}
}

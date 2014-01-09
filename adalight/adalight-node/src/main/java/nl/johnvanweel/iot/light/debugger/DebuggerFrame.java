package nl.johnvanweel.iot.light.debugger;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class DebuggerFrame extends JFrame {
    private final DebugLightsPanel panel;

    @Autowired
    public DebuggerFrame(final DebugLightsPanel panel) {
        this.panel = panel;
    }

    @PostConstruct
    public void postConstruct() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }

            private void createAndShowGUI() {
                DebuggerFrame instance = new DebuggerFrame(panel);
                instance.setSize(800, 600);
                panel.setBackground(Color.black);
                instance.add(panel);
                instance.setVisible(true);
            }
        });
    }
}

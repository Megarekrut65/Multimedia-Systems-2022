package ua.boa.panels;

import ua.boa.CustomMediaComponent;
import ua.boa.IconsLoader;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for displaying volume value and changing it
 */
public class VolumePanel extends JPanel {
    private final CustomMediaComponent mediaComponent;/*Component to change volume value*/
    private final JLabel volumeLabel;/*Label for volume image*/
    private final ImageIcon imageOn;/*Image of volume is on*/
    private final ImageIcon imageOff;/*Image of volume is off*/

    public VolumePanel(CustomMediaComponent mediaComponent, IconsLoader icons) {
        this.mediaComponent = mediaComponent;
        setLayout(new BorderLayout());
        imageOn = getScaledIcon(icons.VOLUME_ON_ICON);
        imageOff = getScaledIcon(icons.VOLUME_OFF_ICON);
        volumeLabel = new JLabel(imageOn);
        JSlider jSlider = createVolumeSlider();
        add(jSlider, BorderLayout.EAST);
        add(Box.createHorizontalStrut(10));
        add(volumeLabel, BorderLayout.WEST);
        mediaComponent.addEventToKeyListener(38/*UP*/, () -> jSlider.setValue(jSlider.getValue() + 5));
        mediaComponent.addEventToKeyListener(40/*DOWN*/, () -> jSlider.setValue(jSlider.getValue() - 5));
    }

    /**
     * Creates image with fixed size
     *
     * @param imageIcon - icon of image
     */
    private ImageIcon getScaledIcon(ImageIcon imageIcon) {
        return new ImageIcon(imageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
    }

    /**
     * Creates slider for changing volume value
     */
    private JSlider createVolumeSlider() {
        JSlider jSlider = new JSlider(0, 100, mediaComponent.getVolume());
        jSlider.setFocusable(false);
        jSlider.setPreferredSize(new Dimension(100, 20));
        jSlider.addChangeListener(l -> {
            JSlider slider = (JSlider) l.getSource();
            mediaComponent.changeVolume(slider.getValue());
            if (slider.getValue() == 0) volumeLabel.setIcon(imageOff);
            else volumeLabel.setIcon(imageOn);

        });
        return jSlider;
    }
}

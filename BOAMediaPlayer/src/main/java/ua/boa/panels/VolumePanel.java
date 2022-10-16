package ua.boa.panels;

import ua.boa.CustomMediaComponent;
import ua.boa.IconsLoader;

import javax.swing.*;
import java.awt.*;

public class VolumePanel extends JPanel {
    private final CustomMediaComponent mediaComponent;
    private final JLabel volumeLabel;
    private final ImageIcon imageOn;
    private final ImageIcon imageOff;
    public VolumePanel(CustomMediaComponent mediaComponent, IconsLoader icons) {
        this.mediaComponent = mediaComponent;
        setLayout(new BorderLayout());
        imageOn = getScaledIcon(icons.VOLUME_ON_ICON);
        imageOff = getScaledIcon(icons.VOLUME_OFF_ICON);
        volumeLabel = new JLabel(imageOn);
        add(createVolumeSlider(), BorderLayout.EAST);
        add(Box.createHorizontalStrut(10));
        add(volumeLabel, BorderLayout.WEST);
    }
    private ImageIcon getScaledIcon(ImageIcon imageIcon){
        return new ImageIcon(imageIcon.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT));
    }
    private JSlider createVolumeSlider(){
        JSlider jSlider = new JSlider(0, 100, mediaComponent.getVolume());
        jSlider.setPreferredSize(new Dimension(100, 20));
        jSlider.addChangeListener(l->{
            JSlider slider = (JSlider) l.getSource();
            mediaComponent.changeVolume(slider.getValue());
            if(slider.getValue() == 0) volumeLabel.setIcon(imageOff);
            else volumeLabel.setIcon(imageOn);

        });
        return jSlider;
    }
}

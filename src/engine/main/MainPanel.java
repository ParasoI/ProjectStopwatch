package engine.main;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private JLabel lbl_title;
    private JLabel lbl_taskName;
    private JLabel lbl_taskDuration;
    private JButton btn_playPause;
    private JButton btn_reset;
    private JButton btn_save;
    //Create task, Open task, Delete task will be on JMenu

    public MainPanel() {

        lbl_title = new JLabel("Project Stopwatch");
        lbl_taskName = new JLabel("Task Name");
        lbl_taskDuration = new JLabel("Task Duration");
        btn_playPause = new JButton();
        btn_reset = new JButton();
        btn_save = new JButton();

        this.setPreferredSize(new Dimension(500,300));
        this.setBackground(Color.BLUE);

        GridBagLayout gridB = new GridBagLayout();
        this.setLayout(gridB);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(lbl_title,gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(lbl_taskName,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(lbl_taskDuration,gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(btn_playPause,gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        this.add(btn_reset,gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        this.add(btn_save,gbc);

    }
}

package engine.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MainPanel extends JPanel {

    private final String TITLE;
    private final Font TITLE_FONT;
    private final Font BODY_FONT;
    private final Font BODY_FONT2;
    private final Dimension DIM_BUTTON;
    private final Color NEARBLACK2;
    private final ImageIcon ICO_PLAY;
    private final ImageIcon ICO_PAUSE;
    private final ImageIcon ICO_RESET;
    private final ImageIcon ICO_SAVE;
    private JLabel lbl_title;
    private JLabel lbl_taskName;
    private JLabel lbl_taskDuration;
    private JButton btn_playPause;
    private JButton btn_reset;
    private JButton btn_save;

    public MainPanel() {

        TITLE = "Project Stopwatch";
        TITLE_FONT = new Font("Bangers",Font.PLAIN,36);
        BODY_FONT = new Font("Roboto",Font.PLAIN,20);
        BODY_FONT2 = new Font("Roboto",Font.PLAIN,14);
        DIM_BUTTON = new Dimension(32,32);
        NEARBLACK2 = Color.decode("#0d0d0d");
        ICO_PLAY = new ImageIcon(this.getClass().getResource("../../resources/play_32px.png"));
        ICO_PAUSE = new ImageIcon(this.getClass().getResource("../../resources/pause_32px.png"));
        ICO_RESET = new ImageIcon(this.getClass().getResource("../../resources/reset_32px.png"));
        ICO_SAVE = new ImageIcon(this.getClass().getResource("../../resources/save_32px.png"));

        lbl_title = new JLabel(TITLE);
        lbl_taskName = new JLabel("Task Name");
        lbl_taskDuration = new JLabel("Task Duration");
        btn_playPause = new JButton();
        btn_reset = new JButton();
        btn_save = new JButton();

        lbl_title.setForeground(Color.WHITE);
        lbl_title.setFont(TITLE_FONT);

        lbl_taskName.setForeground(Color.WHITE);
        lbl_taskName.setFont(BODY_FONT2);

        lbl_taskDuration.setForeground(Color.WHITE);
        lbl_taskDuration.setFont(BODY_FONT);

        btn_playPause.setPreferredSize(DIM_BUTTON);
        btn_playPause.setBackground(null);
        btn_playPause.setBorderPainted(false);
        btn_playPause.setIcon(ICO_PLAY);

        btn_reset.setPreferredSize(DIM_BUTTON);
        btn_reset.setBackground(null);
        btn_reset.setBorderPainted(false);
        btn_reset.setIcon(ICO_RESET);

        btn_save.setPreferredSize(DIM_BUTTON);
        btn_save.setBackground(null);
        btn_save.setBorderPainted(false);
        btn_save.setIcon(ICO_SAVE);

        this.setPreferredSize(new Dimension(500,300));
        this.setBackground(NEARBLACK2);

        //Separating layout for readability
        initLayout();

    }

    public void initLayout(){

        GridBagLayout gridB = new GridBagLayout();
        this.setLayout(gridB);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.CENTER;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,0,0,0);
        this.add(lbl_title,gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10,0,0,0);
        this.add(lbl_taskName,gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10,0,0,0);
        this.add(lbl_taskDuration,gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,0,20,0);
        this.add(btn_playPause,gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,0,20,0);
        this.add(btn_reset,gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,0,20,0);
        this.add(btn_save,gbc);
        gbc.gridwidth = 1;
    }

    public JLabel getLbl_title() {
        return lbl_title;
    }

    public void setLbl_title(JLabel lbl_title) {
        this.lbl_title = lbl_title;
    }

    public JLabel getLbl_taskName() {
        return lbl_taskName;
    }

    public void setLbl_taskName(JLabel lbl_taskName) {
        this.lbl_taskName = lbl_taskName;
    }

    public JLabel getLbl_taskDuration() {
        return lbl_taskDuration;
    }

    public void setLbl_taskDuration(JLabel lbl_taskDuration) {
        this.lbl_taskDuration = lbl_taskDuration;
    }

    public JButton getBtn_playPause() {
        return btn_playPause;
    }

    public void setBtn_playPause(JButton btn_playPause) {
        this.btn_playPause = btn_playPause;
    }

    public JButton getBtn_reset() {
        return btn_reset;
    }

    public void setBtn_reset(JButton btn_reset) {
        this.btn_reset = btn_reset;
    }

    public JButton getBtn_save() {
        return btn_save;
    }

    public void setBtn_save(JButton btn_save) {
        this.btn_save = btn_save;
    }

    public String getTITLE() {
        return TITLE;
    }

    public Font getTITLE_FONT() {
        return TITLE_FONT;
    }

    public Font getBODY_FONT() {
        return BODY_FONT;
    }

    public Font getBODY_FONT2() {
        return BODY_FONT2;
    }

    public Dimension getDIM_BUTTON() {
        return DIM_BUTTON;
    }

    public Color getNEARBLACK2() {
        return NEARBLACK2;
    }

    public ImageIcon getICO_PLAY() {
        return ICO_PLAY;
    }

    public ImageIcon getICO_PAUSE() {
        return ICO_PAUSE;
    }

    public ImageIcon getICO_RESET() {
        return ICO_RESET;
    }

    public ImageIcon getICO_SAVE() {
        return ICO_SAVE;
    }
}

package engine.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel<btn_save, btn_reset> extends JPanel implements ActionListener {

    private JLabel lbl_title;
    private JLabel lbl_taskName;
    private JLabel lbl_taskDuration;
    private JButton btn_playPause;
    private JButton btn_reset;
    private JButton btn_save;
    private boolean isPlaying;
    private Stopwatch stopwatch;
    private Task task;
    //Create task, Open task, Delete task will be on JMenu

    public MainPanel(Task task, Stopwatch stopwatch) {

        lbl_title = new JLabel("Project Stopwatch");
        lbl_taskName = new JLabel("Task Name");
        lbl_taskDuration = new JLabel("Task Duration");
        btn_playPause = new JButton();
        btn_reset = new JButton();
        btn_save = new JButton();
        isPlaying = false;

        this.task = task;
        this.stopwatch = stopwatch;
        stopwatch.setTask(task);
        stopwatch.setLabel(lbl_taskDuration);

        lbl_title.setForeground(Color.WHITE);
        lbl_title.setFont(new Font("Bangers",Font.PLAIN,36));

        lbl_taskName.setForeground(Color.WHITE);
        lbl_taskName.setFont(new Font("Roboto",Font.PLAIN,14));
        lbl_taskName.setText(task.getTaskName());

        lbl_taskDuration.setForeground(Color.WHITE);
        lbl_taskDuration.setFont(new Font("Roboto",Font.PLAIN,20));
        lbl_taskDuration.setText(SecondsUtils.toLabel(task.getTaskDuration()));

        btn_playPause.setPreferredSize(new Dimension(32,32));
        btn_playPause.setBackground(null);
        btn_playPause.setBorderPainted(false);
        btn_playPause.setIcon(new ImageIcon(this.getClass().getResource("../../resources/play_32px.png")));
        btn_playPause.addActionListener(this);

        btn_reset.setPreferredSize(new Dimension(32,32));
        btn_reset.setBackground(null);
        btn_reset.setBorderPainted(false);
        btn_reset.setIcon(new ImageIcon(this.getClass().getResource("../../resources/reset_32px.png")));
        btn_reset.addActionListener(this);

        btn_save.setPreferredSize(new Dimension(32,32));
        btn_save.setBackground(null);
        btn_save.setBorderPainted(false);
        btn_save.setIcon(new ImageIcon(this.getClass().getResource("../../resources/save_32px.png")));
        btn_save.addActionListener(this);

        this.setPreferredSize(new Dimension(500,300));
        this.setBackground(Color.decode("#0d0d0d"));

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn_playPause){
            if(isPlaying == true){
                btn_reset.setEnabled(true);
                btn_save.setEnabled(true);
                stopwatch.pauseTime();
                isPlaying = false;
                btn_playPause.setIcon(new ImageIcon(this.getClass().getResource("../../resources/play_32px.png")));

            }else{
                btn_reset.setEnabled(false);
                btn_save.setEnabled(false);
                isPlaying = true;
                btn_playPause.setIcon(new ImageIcon(this.getClass().getResource("../../resources/pause_32px.png")));
                try {
                    stopwatch.playTime();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

        }
        if(e.getSource() == btn_save){
            stopwatch.saveTime();
        }
        if(e.getSource() == btn_reset){
            stopwatch.resetTime();
            isPlaying = false;
            btn_playPause.setIcon(new ImageIcon(this.getClass().getResource("../../resources/play_32px.png")));
        }
    }


}

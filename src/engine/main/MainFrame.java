package engine.main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenuItem mit_createTask;
    private JMenuItem mit_openDeleteTask;
    private final Color NEARBLACK;

    public MainFrame() {

        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        mit_createTask = new JMenuItem("Create Project");
        mit_openDeleteTask = new JMenuItem("Project List");
        NEARBLACK = Color.decode("#333333");

        UIManager.put("PopupMenu.border", new LineBorder(NEARBLACK));

        menuBar.setBackground(NEARBLACK);
        menuBar.setBorderPainted(false);

        menuFile.setBackground(NEARBLACK);
        menuFile.setForeground(Color.WHITE);
        menuFile.setBorderPainted(false);

        mit_createTask.setBackground(NEARBLACK);
        mit_createTask.setForeground(Color.WHITE);
        mit_createTask.setBorderPainted(false);

        mit_openDeleteTask.setBackground(NEARBLACK);
        mit_openDeleteTask.setForeground(Color.WHITE);
        mit_openDeleteTask.setBorderPainted(false);

        menuFile.add(mit_createTask);
        menuFile.add(mit_openDeleteTask);
        menuBar.add(menuFile);

        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setTitle("Project Scheduler");
        this.setResizable(false);

        this.setJMenuBar(menuBar);

        //Controller will set the frame visible
        //this.setVisible(true);
    }

    public JMenu getMenuFile() {
        return menuFile;
    }

    public void setMenuFile(JMenu menuFile) {
        this.menuFile = menuFile;
    }

    public JMenuItem getMit_createTask() {
        return mit_createTask;
    }

    public void setMit_createTask(JMenuItem mit_createTask) {
        this.mit_createTask = mit_createTask;
    }

    public JMenuItem getMit_openDeleteTask() {
        return mit_openDeleteTask;
    }

    public void setMit_openDeleteTask(JMenuItem openDeleteTask) {
        this.mit_openDeleteTask = openDeleteTask;
    }

    public Color getNEARBLACK() {
        return NEARBLACK;
    }
}
package engine.main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem createTask;
    private JMenuItem openDeleteTask;

    public MainFrame() {

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        createTask = new JMenuItem("Create Project");
        openDeleteTask = new JMenuItem("Project List");

        UIManager.put("PopupMenu.border", new LineBorder(Color.decode("#333333")));

        menuBar.setBackground(Color.decode("#333333"));
        menuBar.setBorderPainted(false);

        fileMenu.setBackground(Color.decode("#333333"));
        fileMenu.setForeground(Color.WHITE);
        fileMenu.setBorderPainted(false);

        createTask.setBackground(Color.decode("#333333"));
        createTask.setForeground(Color.WHITE);
        createTask.setBorderPainted(false);

        openDeleteTask.setBackground(Color.decode("#333333"));
        openDeleteTask.setForeground(Color.WHITE);
        openDeleteTask.setBorderPainted(false);

        fileMenu.add(createTask);
        fileMenu.add(openDeleteTask);
        menuBar.add(fileMenu);

        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setTitle("Project Scheduler");
        this.setResizable(false);

        this.setJMenuBar(menuBar);

        //this.setVisible(true);
    }

    public JMenu getFileMenu() {
        return fileMenu;
    }

    public void setFileMenu(JMenu fileMenu) {
        this.fileMenu = fileMenu;
    }

    public JMenuItem getCreateTask() {
        return createTask;
    }

    public void setCreateTask(JMenuItem createTask) {
        this.createTask = createTask;
    }

    public JMenuItem getOpenDeleteTask() {
        return openDeleteTask;
    }

    public void setOpenDeleteTask(JMenuItem openDeleteTask) {
        this.openDeleteTask = openDeleteTask;
    }
}
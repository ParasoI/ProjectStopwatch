package engine.main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem createTask;
    private JMenuItem openTask;
    private JMenuItem deleteTask;
    private Task task;
    private Stopwatch stopwatch;

    public MainFrame(){

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        createTask = new JMenuItem("Create Project");
        openTask = new JMenuItem("Open Project");
        deleteTask = new JMenuItem("Delete Project");
        task = new Task(99999,"IU - BBIBBI with Scribble", 54321);
        stopwatch = new Stopwatch();

        UIManager.put("PopupMenu.border", new LineBorder(Color.decode("#333333")));

        menuBar.setBackground(Color.decode("#333333"));
        menuBar.setBorderPainted(false);

        fileMenu.setBackground(Color.decode("#333333"));
        fileMenu.setForeground(Color.WHITE);
        fileMenu.setBorderPainted(false);

        createTask.setBackground(Color.decode("#333333"));
        createTask.setForeground(Color.WHITE);
        createTask.setBorderPainted(false);

        openTask.setBackground(Color.decode("#333333"));
        openTask.setForeground(Color.WHITE);
        openTask.setBorderPainted(false);

        deleteTask.setBackground(Color.decode("#333333"));
        deleteTask.setForeground(Color.WHITE);
        deleteTask.setBorderPainted(false);

        fileMenu.add(createTask);
        fileMenu.add(openTask);
        fileMenu.add(deleteTask);
        menuBar.add(fileMenu);

        this.setSize(500,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setTitle("Project Scheduler");
        this.setResizable(false);
        this.setContentPane(new MainPanel(task, stopwatch));

        this.setJMenuBar(menuBar);

        this.setVisible(true);
    }
}

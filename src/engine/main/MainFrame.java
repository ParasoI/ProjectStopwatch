package engine.main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(){

        this.setSize(500,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setTitle("Project Scheduler");
        this.setResizable(false);
        this.setContentPane(new MainPanel());

        this.setVisible(true);
    }
}

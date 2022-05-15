package engine.main;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {



    public static void main(String[] args) {

        MainFrame view1 = new MainFrame();
        MainPanel view2 = new MainPanel();
        ProjectListFrame view3 = new ProjectListFrame();
        Task model = new Task(0,"0",0);
        Stopwatch stopwatch = new Stopwatch();
        Controller controller = new Controller(view1, view2, view3, model, stopwatch);

        controller.init();
    }
}

package engine.main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Controller implements ActionListener {

    private Task model;
    private MainFrame view1;
    private MainPanel view2;
    private ProjectListFrame view3;
    private Stopwatch stopwatch;

    public Controller(MainFrame view1, MainPanel view2, ProjectListFrame view3, Task model, Stopwatch stopwatch) {

        this.view1 = view1;
        this.view2 = view2;
        this.view3 = view3;
        this.model = model;
        this.stopwatch = stopwatch;
    }

    public void init(){

        dbLoad(1);
        view2.getLbl_taskName().setText(model.getTaskName());
        view2.getLbl_taskDuration().setText(SecondsUtils.toLabel(model.getTaskDuration()));

        view1.getCreateTask().addActionListener(this);
        view1.getOpenDeleteTask().addActionListener(this);
        view2.getBtn_playPause().addActionListener(this);
        view2.getBtn_save().addActionListener(this);
        view2.getBtn_reset().addActionListener(this);

        view3.getMit_openTask().addActionListener(this);
        view3.getMit_deleteTask().addActionListener(this);

        view1.setContentPane(view2);
        view1.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view1.getCreateTask()){
            taskCreate();
        }
        if(e.getSource() == view1.getOpenDeleteTask()){
            taskList();
        }
        if(e.getSource() == view2.getBtn_playPause()){
            if(view2.isPlaying()){
                view2.getBtn_reset().setEnabled(true);
                view2.getBtn_save().setEnabled(true);
                timePause();
                view2.setPlaying(false);
                view2.getBtn_playPause().setIcon(new ImageIcon(this.getClass().getResource("../../resources/play_32px.png")));

            }else{
                view2.getBtn_reset().setEnabled(false);
                view2.getBtn_save().setEnabled(false);
                view2.setPlaying(true);
                view2.getBtn_playPause().setIcon(new ImageIcon(this.getClass().getResource("../../resources/pause_32px.png")));
                try {
                    timePlay();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

        }
        if(e.getSource() == view2.getBtn_save()){
            timeSave();
            dbSave();
        }
        if(e.getSource() == view2.getBtn_reset()){
            timeReset();
            view2.setPlaying(false);
            view2.getBtn_playPause().setIcon(new ImageIcon(this.getClass().getResource("../../resources/play_32px.png")));
        }
        if(e.getSource() == view3.getMit_openTask()){
            System.out.println("ell");
            taskOpen();
        }
        if(e.getSource() == view3.getMit_deleteTask()){
            taskDelete();
        }

    }

    public void taskCreate(){

        String taskName = JOptionPane.showInputDialog("Project Name:");
        System.out.println(taskName);
        if(taskName == null){
            return;
        }else if(taskName.equals("")){
            return;
        }else{
            String query = "INSERT INTO task (taskName, taskDuration) VALUES(\"" + taskName +"\", 0);";
            try{
                Connection connection = DriverManager.getConnection(Database.url, Database.username, Database.password);

                System.out.println("Database connected!");
                Statement statement = connection.createStatement();
                statement.execute(query);

            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }
        }

    }

    public void taskList(){

        //reset Project List Frame
        view3 = new ProjectListFrame();
        view3.getMit_openTask().addActionListener(this);
        view3.getMit_deleteTask().addActionListener(this);

        int[] rowId;
        String[] rowTaskName;
        long[] rowTaskDuration;
        int rowCount = 0;

        String query = "SELECT COUNT(taskId) FROM task;";

        try{
            Connection connection = DriverManager.getConnection(Database.url, Database.username, Database.password);

            System.out.println("Database connected!");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                rowCount = result.getInt("COUNT(taskId)");
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        rowId = new int[rowCount];
        rowTaskName = new String[rowCount];
        rowTaskDuration = new long[rowCount];

        query = "SELECT * FROM task;";

        try{
            Connection connection = DriverManager.getConnection(Database.url, Database.username, Database.password);

            System.out.println("Database connected!");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            for(int i = 0; i < rowCount; i++){
                if(result.next()){
                    rowId[i] = result.getInt("taskId");
                    rowTaskName[i] = result.getString("taskName");
                    rowTaskDuration[i] = result.getLong("taskDuration");
                }else{
                    break;
                }

            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        view3.getDefaultTableModel().addColumn("Project Id");
        view3.getDefaultTableModel().addColumn("Project Name");
        view3.getDefaultTableModel().addColumn("Project Duration");
        for(int i = 0; i < rowCount; i++){
            view3.getDefaultTableModel().addRow(new Object[]{rowId[i], rowTaskName[i], SecondsUtils.toLabel(rowTaskDuration[i])});
        }

        view3.decorateTable();
        view3.setVisible(true);

    }

    public void taskOpen(){

        int selectedTaskId = 0;
        selectedTaskId = (int)view3.getTable().getValueAt(view3.getTable().getSelectedRow(),0);

        dbLoad(selectedTaskId);
        view2.getLbl_taskName().setText(model.getTaskName());
        view2.getLbl_taskDuration().setText(SecondsUtils.toLabel(model.getTaskDuration()));
        view3.dispose();

    }

    public void taskDelete(){

        int selectedTaskId = 0;
        selectedTaskId = (int)view3.getTable().getValueAt(view3.getTable().getSelectedRow(),0);

        String query = "DELETE FROM task WHERE taskId = " + selectedTaskId + ";";

        try{
            Connection connection = DriverManager.getConnection(Database.url, Database.username, Database.password);

            System.out.println("Database connected!");
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }


        dbLoad(1);
        view2.getLbl_taskName().setText(model.getTaskName());
        view2.getLbl_taskDuration().setText(SecondsUtils.toLabel(model.getTaskDuration()));
        view3.dispose();

    }

    public void timePause(){

        stopwatch.setPlaying(false);

        stopwatch.setCurrentEnd(System.currentTimeMillis());
        stopwatch.setElapsed(stopwatch.getElapsed() + (stopwatch.getCurrentEnd() - stopwatch.getCurrentStart()) / 1000);
    };

    public void timePlay() throws InterruptedException {

        stopwatch.setPlaying(true);

        stopwatch.setCurrentStart(System.currentTimeMillis());

        SwingWorker swingWorker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {

                while(stopwatch.isPlaying()){
                    Long elapsedSeconds = model.getTaskDuration() + stopwatch.getElapsed() + ((System.currentTimeMillis() - stopwatch.getCurrentStart() ) / 1000);
                    view2.getLbl_taskDuration().setText(SecondsUtils.toLabel(elapsedSeconds));
                    Thread.sleep(1000);
                }

                return null;
            }
        };

        swingWorker.execute();

    }

    public void timeSave(){

        model.setTaskDuration(model.getTaskDuration() + stopwatch.getElapsed());
        timeReset();
    }

    public void timeReset(){

        stopwatch.setElapsed(0);
        stopwatch.setCurrentStart(0);
        stopwatch.setCurrentEnd(0);
        view2.getLbl_taskDuration().setText(SecondsUtils.toLabel(model.getTaskDuration()));
        stopwatch.setPlaying(false);
    }

    public void dbLoad(int taskId){

        String query = "SELECT * FROM task WHERE taskId = " + taskId + ";";

        try{
            Connection connection = DriverManager.getConnection(Database.url, Database.username, Database.password);

            System.out.println("Database connected!");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                model.setTaskId(result.getInt("taskId"));
                model.setTaskName(result.getString("taskName"));
                model.setTaskDuration(result.getLong("taskDuration"));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void dbSave(){

        String query = "UPDATE task SET taskDuration = " + model.getTaskDuration() + " WHERE taskId = " +  model.getTaskId() + ";";
        try{
            Connection connection = DriverManager.getConnection(Database.url, Database.username, Database.password);

            System.out.println("Database connected!");
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

    }

}

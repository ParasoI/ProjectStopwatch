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

    public Controller(MainFrame view1,
        MainPanel view2,
        ProjectListFrame view3,
        Task model,
        Stopwatch stopwatch) {

        this.view1 = view1;
        this.view2 = view2;
        this.view3 = view3;
        this.model = model;
        this.stopwatch = stopwatch;
    }

    public void init(){

        taskOpenLatest();
        view2.getLbl_taskName().setText(model.getTaskName());
        view2.getLbl_taskDuration().setText(SecondsUtils.toLabel(model.getTaskDuration()));

        view1.getMit_createTask().addActionListener(this);
        view1.getMit_openDeleteTask().addActionListener(this);
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
        if(e.getSource() == view1.getMit_createTask()){
            taskCreate();
            taskOpenLatest();
        }
        if(e.getSource() == view1.getMit_openDeleteTask()){
            taskList();
        }
        if(e.getSource() == view2.getBtn_playPause()){
            if(stopwatch.isPlaying()){
                timePause();
            }else{
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

        }
        if(e.getSource() == view3.getMit_openTask()){
            taskOpen();
        }
        if(e.getSource() == view3.getMit_deleteTask()){
            taskDelete();
        }

    }

    public void taskCreate(){

        String taskName = JOptionPane.showInputDialog("Project Name:");
        if(taskName == null){
            return;
        }else if(taskName.equals("")){
            return;
        }else{
            String query = "INSERT INTO task (taskName, taskDuration) VALUES(\"" + taskName +"\", 0);";
            try{
                Connection connection = DriverManager.getConnection(Database.url, Database.username, Database.password);
                Statement statement = connection.createStatement();
                statement.execute(query);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Cannot connect the database!","Error",JOptionPane.ERROR_MESSAGE);
                throw new IllegalStateException("Cannot connect the database!", e);
            }
        }

    }

    public void taskList(){

        //Reset Project List Frame
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
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                rowCount = result.getInt("COUNT(taskId)");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cannot connect the database!","Error",JOptionPane.ERROR_MESSAGE);
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        rowId = new int[rowCount];
        rowTaskName = new String[rowCount];
        rowTaskDuration = new long[rowCount];

        query = "SELECT * FROM task;";

        try{
            Connection connection = DriverManager.getConnection(Database.url, Database.username, Database.password);
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
            JOptionPane.showMessageDialog(null, "Cannot connect the database!","Error",JOptionPane.ERROR_MESSAGE);
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

    public void taskOpen(int taskId){

        dbLoad(taskId);
        view2.getLbl_taskName().setText(model.getTaskName());
        view2.getLbl_taskDuration().setText(SecondsUtils.toLabel(model.getTaskDuration()));

    }

    public void taskOpenLatest(){
        int latestTaskId;
        String query = "SELECT MAX(taskId) FROM task;";
        try{
            Connection connection = DriverManager.getConnection(Database.url, Database.username, Database.password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            result.next();
            latestTaskId = result.getInt("MAX(taskId)");

            taskOpen(latestTaskId);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cannot connect the database!","Error",JOptionPane.ERROR_MESSAGE);
            throw new IllegalStateException("Cannot connect the database!", e);
        }

    }

    public void taskDelete(){

        int selectedTaskId = 0;
        selectedTaskId = (int)view3.getTable().getValueAt(view3.getTable().getSelectedRow(),0);

        String query = "DELETE FROM task WHERE taskId = " + selectedTaskId + ";";

        try{
            Connection connection = DriverManager.getConnection(Database.url, Database.username, Database.password);
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cannot connect the database!","Error",JOptionPane.ERROR_MESSAGE);
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        if(selectedTaskId == model.getTaskId()){
            taskOpenLatest();
        }
        view3.dispose();

    }

    public void timePause(){

        view2.getBtn_reset().setEnabled(true);
        view2.getBtn_save().setEnabled(true);
        view2.getBtn_playPause().setIcon(view2.getICO_PLAY());

        stopwatch.setPlaying(false);

        stopwatch.setCurrentEnd(System.currentTimeMillis());
        stopwatch.setElapsed(stopwatch.getElapsed() + (stopwatch.getCurrentEnd() - stopwatch.getCurrentStart()) / 1000);
    };

    public void timePlay() throws InterruptedException {

        view2.getBtn_reset().setEnabled(false);
        view2.getBtn_save().setEnabled(false);
        view2.getBtn_playPause().setIcon(view2.getICO_PAUSE());

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

        model.setRunningDuration(stopwatch.getElapsed());
        model.setTaskDuration(model.getTaskDuration() + model.getRunningDuration());
        timeReset();
    }

    public void timeReset(){

        view2.getBtn_playPause().setIcon(view2.getICO_PLAY());

        model.setRunningDuration(0);
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
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                model.setTaskId(result.getInt("taskId"));
                model.setTaskName(result.getString("taskName"));
                model.setTaskDuration(result.getLong("taskDuration"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cannot connect the database!","Error",JOptionPane.ERROR_MESSAGE);
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void dbSave(){

        String query = "UPDATE task SET taskDuration = " + model.getTaskDuration() + " WHERE taskId = " +  model.getTaskId() + ";";
        try{
            Connection connection = DriverManager.getConnection(Database.url, Database.username, Database.password);
            Statement statement = connection.createStatement();
            statement.execute(query);

            JOptionPane.showMessageDialog(null, "Your Progress is saved!","Success",JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cannot connect the database!","Error",JOptionPane.ERROR_MESSAGE);
            throw new IllegalStateException("Cannot connect the database!", e);
        }

    }

}

package engine.main;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class ProjectListFrame extends JFrame {

    private final Font BODY_FONT2;
    private Color NEARBLACK2;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel defaultTableModel;
    private JPopupMenu popupMenu;
    private JMenuItem mit_openTask;
    private JMenuItem mit_deleteTask;

    public ProjectListFrame(){

        BODY_FONT2 = new Font("Roboto",Font.PLAIN,14);
        NEARBLACK2 = Color.decode("#0d0d0d");

        table = new JTable();
        scrollPane = new JScrollPane(table);
        defaultTableModel = new DefaultTableModel();
        popupMenu = new JPopupMenu();
        mit_openTask = new JMenuItem("Open Project");
        mit_deleteTask = new JMenuItem("Delete Project");

        popupMenu.add(mit_openTask);
        popupMenu.add(mit_deleteTask);

        table.setFont(BODY_FONT2);
        table.setForeground(Color.WHITE);
        table.setBackground(NEARBLACK2);
        table.setSelectionBackground(Color.WHITE);
        table.setSelectionForeground(Color.BLACK);

        table.setRowHeight(20);
        table.setModel(defaultTableModel);
        table.setComponentPopupMenu(popupMenu);

        this.setSize(500,300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setTitle("Project List");
        this.setResizable(false);
        this.setBackground(NEARBLACK2);

        this.add(scrollPane);

        //this.setVisible(true);
    }

    public void decorateTable(){

        table.setModel(defaultTableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);

        //Center text in the table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );

        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(NEARBLACK2);
        table.getTableHeader().setForeground(Color.WHITE);
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public DefaultTableModel getDefaultTableModel() {
        return defaultTableModel;
    }

    public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
        this.defaultTableModel = defaultTableModel;
    }

    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }

    public void setPopupMenu(JPopupMenu popupMenu) {
        this.popupMenu = popupMenu;
    }

    public JMenuItem getMit_openTask() {
        return mit_openTask;
    }

    public void setMit_openTask(JMenuItem mit_openTask) {
        this.mit_openTask = mit_openTask;
    }

    public JMenuItem getMit_deleteTask() {
        return mit_deleteTask;
    }

    public void setMit_deleteTask(JMenuItem mit_deleteTask) {
        this.mit_deleteTask = mit_deleteTask;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
}

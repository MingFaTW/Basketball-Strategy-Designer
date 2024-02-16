package termproject01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import java.io.Serializable;
public class ToolBar implements Serializable{
	private static final long serialVersionUID = 1L;
	mainWindow parent = null;
    private JMenuBar menuBar;
    private  JFrame frame;
    	//設置工具列
    public ToolBar(mainWindow p){
    	parent = p;
        menuBar = new JMenuBar();
        //file選項
        JMenu File = new JMenu("File");
        JMenuItem newProject = new JMenuItem("New Project");
        JMenuItem exportProject= new JMenuItem("Export Project");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem Exit = new JMenuItem("Exit");
         
        File.add(newProject);
        File.add(exportProject);
        File.add(save);
        File.add(load);
        File.add(Exit);
       
        menuBar.add(File);
        //Setting選項
        JMenu Setting = new JMenu("Setting");
        JMenuItem FullScreen = new JMenuItem("FullScreen");
        Setting.add(FullScreen);
        menuBar.add(Setting);
        
        		
        
        // ActionListner動作反饋
        Exit.addActionListener(
                e -> {
                	if(frame != null)
                        frame.dispose();
                }
        );
        FullScreen.addActionListener(
                e -> {
                	if(frame != null)
                        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
                }
        );
        newProject.addActionListener(
                e -> {
                	parent.newProjectWindow();
                	System.out.println("new Project for mainWindow");
                }
        );
        exportProject.addActionListener(
        		e -> {
        			parent.exportProject();
        		}
        );
        save.addActionListener(
                e -> {
                	parent.saveProjectWithFileChooser();
                }
        );
        load.addActionListener(
                e -> {
                	parent.loadProjectWithFileChooser();
                }
        );
    }
    public void setFrame(JFrame frame){
       this.frame = frame;
    }
    public JMenuBar getMenuBar() {
        return menuBar;
    }
}

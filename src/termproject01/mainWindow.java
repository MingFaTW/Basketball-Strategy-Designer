package termproject01;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class mainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int numTabs = 0;
	static int numTabsTempForGIFA = 0;
	List<TabData> TabDataList = new ArrayList<>();
	List<ProjectData> ProjectDatalist = new ArrayList<>();
	private ProjectData projectData = new ProjectData(this);
	private BasketballPanel basketballPanel = new BasketballPanel(this,projectData); 
    operationalPanel op_panel = new operationalPanel(basketballPanel,projectData);
    JSplitPane splitPane = null;;
    
	boolean loaded = false;
	Status drawMode = null;
	//  the value of each players
	int[] X_aPlayer = {1150,1210,1270,1330,1390};
    int[] Y_aPlayer = {10,10,10,10,10};
    int[] X_bPlayer = {50,110,170,230,290};
    int[] Y_bPlayer = {10,10,10,10,10};
    
    int X_ball = 740;
    int Y_ball = 407;
    
    String[] a_Number = {"1","2","3","4","5"};
    String[] b_Number = {"1","2","3","4","5"};
    
    
    private transient String redBallAddress = "src\\termproject01\\redCircle.png";
    private transient String blueBallAddress = "src\\termproject01\\blueCircle.png";
    private transient String ballAddress = "src\\termproject01\\Ball.png";
    private transient String folderAddress = "src/termproject01";
    private transient String saveFileName = "temp.ser";
    
    public static transient JTabbedPane tabbedPane;
	
	
	
    public mainWindow() {
        SwingUtilities.invokeLater(
                () -> {
                    // 更換為Main主程式進行呼叫新視窗
                	createGUI();  // 創建使用者介面
                    setDisplay(); // 設定視窗顯示
                }
        );
    }
    
    private void setDisplay() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createGUI() {
        setTitle("Basketball Strategy Designer");

        // 創建工具列
        ToolBar toolBar = new ToolBar(this);
        toolBar.setFrame(this);
        
        // 創建並添加分頁
        createAndAddTabs(); // 創建JTabbedPane
        add(tabbedPane); // 添加JTabbedPane到JFrame
        
        // 創建一個容器面板以容納工具列和分頁面板
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(toolBar.getMenuBar(), BorderLayout.NORTH);
        //containerPanel.add(tabbedPane, BorderLayout.CENTER);
        this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, op_panel ,tabbedPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(op_panel.getWidth());
	    containerPanel.add(splitPane);
        
        // 將容器面板添加到視窗框架
        add(containerPanel);
    }
    
    public ProjectData getProjectData() {
        return this.projectData;
    }
    
    private void createAndAddTabs() {
    	// 創建JTabbedPane，設置選項卡的位置和樣式
        tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

        // 添加第一個選項卡，並給它一個自定義的標題
        //#初始化分頁一
        basketballPanel = new BasketballPanel(this, projectData);
        op_panel = new operationalPanel(basketballPanel,projectData);
        tabbedPane.add(basketballPanel, "Pages " + String.valueOf(numTabs), numTabs++);
        numTabsTempForGIFA = numTabs;
        tabbedPane.setTabComponentAt(0, new Pages(this));
        
       
        // 添加一個選項卡，用於在點擊時添加新的選項卡
        tabbedPane.add(new JPanel(), "+", numTabs++);
        numTabsTempForGIFA = numTabs;
        tabbedPane.addChangeListener(changeListener);
        
        TabDataList.add(new TabData("Pages 0", 0, basketballPanel));
    }

    // 選項卡切換事件監聽器
    ChangeListener changeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            addNewTab(); // 在切換選項卡時添加新的選項卡
        }
    };


    
    // 添加新的選項卡
    private void addNewTab() {
        int index = numTabs-1;
        numTabsTempForGIFA = numTabs;
        System.out.println("Max: "+index);
        System.out.println("Selected Index Page: "+tabbedPane.getSelectedIndex());
        if (tabbedPane.getSelectedIndex() == index) {
            // 如果點擊了“+”選項卡，則添加新的選項卡
        	basketballPanel = new BasketballPanel(this, projectData);
        	op_panel = new operationalPanel(basketballPanel,projectData);

        	for(int i=0;i<5;i++) {
        		System.out.print(projectData.getX_bPlayer(i)+",");
        	}
            tabbedPane.add( basketballPanel, "Pages " + String.valueOf(index), index);
            tabbedPane.setTabComponentAt(index, new Pages(this));
            tabbedPane.removeChangeListener(changeListener);
            tabbedPane.setSelectedIndex(index);
            tabbedPane.addChangeListener(changeListener);
            numTabs++;
            numTabsTempForGIFA = numTabs;
            TabDataList.add(new TabData("Pages " + String.valueOf(index), numTabs ,basketballPanel));
            // 打印 TabDataList 中的标题
            for (TabData tabData : TabDataList) {
                System.out.println(tabData.getTitle());
                System.out.println(tabData.getComponent());
            }
        }else {
        	splitPane.setLeftComponent(op_panel);
        	//System.out.println(tabbedPane.getSelectedComponent());
        	//splitPane.getRightComponent().repaint();
        }
    }

    
    // 移除指定索引的選項卡
    public void removeTab(int index) {
        tabbedPane.remove(index);
        numTabs--;
        numTabsTempForGIFA = numTabs;
        int i;

        if (index == numTabs - 1 && index > 0) {
            tabbedPane.setSelectedIndex(numTabs - 2);
            numTabsTempForGIFA = numTabs;
        } else {//如果刪除的是中間的選項卡，執行以下程式
            tabbedPane.setSelectedIndex(index);
            for(i=index;i<numTabs-1;i++) {//此迴圈會將所有編號在'x'後的tabs瀝遍
            	renewIndex(tabbedPane,i);
            }
            
        }

        if (numTabs == 1) {
            addNewTab(); // 如果選項卡數量為1，則添加新的選項卡
        }
    }
    
    public void renewIndex(JTabbedPane tabbedPane,int index) {
    		tabbedPane.setTitleAt(index, "pages "+ index);
    		System.out.println("does");//除錯用
    }
    
    public void saveProjectWithFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
      
        // 设置默认的文件名和保存路径
        fileChooser.setCurrentDirectory(new File(folderAddress));
        fileChooser.setSelectedFile(new File(saveFileName));
        
        int option = fileChooser.showSaveDialog(this);
        
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            //saver.SaveProject(filePath);
            try {
            	System.out.println("Saving filepath: "+filePath);            
            	// 在这里将应用程序状态保存到 filePath
    	        FileOutputStream fileOut = new FileOutputStream(filePath);
    	        ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(fileOut));
    	        // Create a new ProjectData object and store the application state in it
    	        outputStream.writeObject(this.ProjectDatalist);
    	        outputStream.writeObject(this.projectData);
    	        
    	        outputStream.close();
    	        fileOut.close();
    	        JOptionPane.showMessageDialog(this, "檔案已成功保存到 " + filePath, "保存成功", JOptionPane.INFORMATION_MESSAGE);
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	        JOptionPane.showMessageDialog(this, "保存檔案時出現錯誤。", "保存失敗", JOptionPane.ERROR_MESSAGE);
    	    }
        }
    }

    
    public void loadProjectWithFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        
        // 设置默认的打开路径
        fileChooser.setCurrentDirectory(new File("src/termproject01"));
        
        int option = fileChooser.showOpenDialog(this);
        
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            
            // 在这里从 filePath 加载应用程序状态
            //loader.LoadProject(filePath);
            if (filePath == null) {
                filePath = "src\\termproject01\\temp.ser"; // 或者你的默认文件名
            }
            try {
                // 使用提供的 fileName 打开输入流以读取序列化的数据
                FileInputStream fileIn = new FileInputStream(filePath);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                // 读取序列化的 ProjectData 对象
                @SuppressWarnings("unchecked")
				List <ProjectData> projectDatalist = (List <ProjectData>) in.readObject();
                ProjectData projectData = (ProjectData) in.readObject();
                this.ProjectDatalist = projectDatalist;
                this.projectData = projectData;
                //System.out.println("ProjectDatalistSize: "+ProjectDatalist.size());                
                BasketballPanel basketballPanel = new BasketballPanel(this,projectData);
                op_panel = new operationalPanel(basketballPanel,projectData);
                splitPane.setLeftComponent(op_panel);
                tabbedPane.setComponentAt(0,basketballPanel);
                tabbedPane.setTabComponentAt(0, new Pages(this));
                // 关闭输入流
                in.close();
                fileIn.close();
                System.out.println("Serizable succesfullly.");
                System.out.println("ProjectDatalistSize: "+ProjectDatalist.size());                
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                //return null; // 如果出现错误或文件不存在，返回 null
                //序列化失敗
                System.out.println("Seriazable failed");
            }
        }
    }
    void newProjectWindow() {
    	new newProjectWindow();
    }
    
    void exportProject() {
    	new exportProject();
    }
    
    ProjectData getProjectDataFromMainWin() {
    	return this.projectData;
    }
}































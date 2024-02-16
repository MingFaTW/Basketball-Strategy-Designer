package termproject01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
class ImagePanel extends JPanel implements Serializable{
	private static final long serialVersionUID = 1L;
	private mainWindow mainWin;
    BasketballPanel basketPanel;
    private Image image;             // 球員圖片
    private int imageX;              // 圖片 X 座標
    private int imageY;              // 圖片 Y 座標
    private int[] playerX;
    private int[] playerY;
    private int playerNum;
    private char team;
    public int number;
    private int offsetX;              // 滑鼠點擊位置距離圖片左上角的 X 軸偏移
    private int offsetY;              // 滑鼠點擊位置距離圖片左上角的 Y 軸偏移
    private boolean isDragging;       // 是否正在拖曳圖片
    private boolean isEditing;        // 用於追蹤是否進入編輯模式
    private JLabel label;             // 顯示球員編號的標籤
    private JTextField textField;     // 用於編輯球員編號的文字輸入框
    private int numberTemp;
    // ImagePanel 的建構函式
    public ImagePanel(String imagePath, char team, int number, int x, int y, String Pnumber, BasketballPanel basketPanel) {
    	this.number = number;
        this.team = team;
        
        this.imageX = x;
        this.imageY = y;

        this.basketPanel = basketPanel;
        
        playerX = new int[5];
        playerY = new int[5];
        // 載入圖片
        image = new ImageIcon(imagePath).getImage();
        // 建立標籤顯示球員編號
        label = new JLabel(Pnumber);
        // 設定標籤字型和大小
        Font font = new Font("MV Boli", Font.PLAIN, 24);
        label.setFont(font);

        // 建立文字輸入框，用於編輯球員編號
        textField = new JTextField(Pnumber);
        textField.setFont(font);
        textField.setForeground(Color.white);
        textField.setOpaque(false);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setBounds(x, y + image.getHeight(null) + 10, 200, 30);
        textField.setVisible(false); // 初始時隱藏文字輸入框
        add(textField);

        // 為文字輸入框添加 ActionListener 以處理 Enter 鍵
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAndExitEditingMode();
                isEditing = true;
            }
        });

        // 添加 FocusListener，以便在失去焦點時更新標籤內容並隱藏文字輸入框
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // 不需要任何操作
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (isEditing) {
                    exitEditingMode();
                    String newPnumber = textField.getText();
                    label.setText(newPnumber);
                    basketPanel.setPlayerNum(ImagePanel.this.team,ImagePanel.this.number,newPnumber);
                    System.out.println("The Pnumber is saving!");
                }
            }
        });

        // 設定 ImagePanel 的位置和大小
        setBounds(imageX, imageY, image.getWidth(null), image.getHeight(null));

        // 設定標籤字型和顏色
        label.setFont(font);
        label.setForeground(Color.white);
        add(label);

        // 設定滑鼠事件
        addMouseListenerForEditing();  // 调用新的方法来添加鼠标监听器
        setVisible(true);
    }

    // Ball design
    public ImagePanel(String imagePath, int x, int y , BasketballPanel basketPanel) {
    	this.imageX = x;
    	this.imageY = y;
    	this.basketPanel = basketPanel;
    	image = new ImageIcon(imagePath).getImage();
    	setBounds(imageX, imageY, image.getWidth(null), image.getHeight(null));
    	addBallMouseListner();
    	setVisible(true);
    }
    
    // 获取球员队伍
    public char getTeam() {
        return team;
    }

    // 获取球员号码
    public int getNumber() {
        return number;
    }
    
    //球的MouseListner呼叫
    private void addBallMouseListner() {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                if (mouseX >= 0 && mouseX < getWidth() && mouseY >= 0 && mouseY < getHeight()) {
                    offsetX = mouseX;
                    offsetY = mouseY;
                    isDragging = true;
                }
            }
            private boolean isHandlingMouseRelease = false;

            // 添加一个方法，用于在编辑模式结束时更新视图
            private void updateViewAfterEditing() {
                if (isHandlingMouseRelease) {
                    isHandlingMouseRelease = false;
                    return;
                }
                if (!isDragging) {
                	basketPanel.updateView();
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                isDragging = false;
                isHandlingMouseRelease = true;
                // 更新球的位置
                basketPanel.setBallPosition(getX() - getParent().getX(), getY() - getParent().getY());
                updateViewAfterEditing();
            }


            @Override
            public void mouseClicked(MouseEvent e) {
                if (isEditing) {
                    exitEditingMode();  // 结束编辑模式
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging) {
                    int mouseX = e.getXOnScreen();
                    int mouseY = e.getYOnScreen();
                    int panelX = getParent().getLocationOnScreen().x;
                    int panelY = getParent().getLocationOnScreen().y;

                    setLocation(mouseX - panelX - offsetX, mouseY - panelY - offsetY);
                }
            }
        });
    }
    
    // 新的方法，用于添加鼠标监听器以处理编辑
    private void addMouseListenerForEditing() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                System.out.println("The number of Player: "+ImagePanel.this.number);
                if (mouseX >= 0 && mouseX < getWidth() && mouseY >= 0 && mouseY < getHeight()) {
                    offsetX = mouseX;
                    offsetY = mouseY;
                    isDragging = true;
                    enterEditingMode();  // 進入編輯模式
                } else {
                    textField.setFocusable(false);
                    saveAndExitEditingMode();  // 非球員元素，儲存並退出編輯模式
                }
            }

            private boolean isHandlingMouseRelease = false;

            // 添加一个方法，用于在编辑模式结束时更新视图
            private void updateViewAfterEditing() {
                if (isHandlingMouseRelease) {
                    isHandlingMouseRelease = false;
                    return;
                }
                if (!isDragging) {
                	basketPanel.updateView();  // 调用主窗口的updateView方法
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                isDragging = false;
                isHandlingMouseRelease = true;
                // 更新 createBasketPanel 中的球员位置信息
                System.out.println("MouseRelease PlayerNum: " + ImagePanel.this.number);
                basketPanel.setPlayerPosition(team, ImagePanel.this.number, getX() - getParent().getX()+2, getY() - getParent().getY()+32);
                // 将更新视图的操作推迟到事件分发线程中执行
                updateViewAfterEditing();
            }


            @Override
            public void mouseClicked(MouseEvent e) {
                if (isEditing) {
                    exitEditingMode();  // 结束编辑模式
                    String newPnumber = textField.getText();
                    label.setText(newPnumber);
                    
                }
            }
            //點擊非球員元素或籃球場進行更新球員隊號
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging) {
                    int mouseX = e.getXOnScreen();
                    int mouseY = e.getYOnScreen();
                    int panelX = getParent().getLocationOnScreen().x;
                    int panelY = getParent().getLocationOnScreen().y;

                    setLocation(mouseX - panelX - offsetX, mouseY - panelY - offsetY);
                    label.setLocation(mouseX - panelX - offsetX + 18, mouseY - panelY - offsetY + 7);
                }
            }
        });
    }

    private void enterEditingMode() {
        isEditing = true;
        textField.setVisible(true); // 顯示文字輸入框
        label.setVisible(false); // 隱藏標籤
    }

    private void exitEditingMode() {
        isEditing = false;
        textField.setVisible(false); // 隱藏文字輸入框
        if (!isDragging) {
            label.setVisible(true); // 如果不在拖動中，則顯示標籤
        }
        setDoubleBuffered(false);
        repaint();
    }

    private void saveAndExitEditingMode() {
        String newPnumber = textField.getText();
        label.setText(newPnumber);
        textField.setVisible(false); // 隱藏文字輸入框
        if (!isDragging) {
            label.setVisible(true); // 如果不在拖動中，則顯示標籤
        }
        textField.setFocusable(true);
        exitEditingMode();
    }
    
    // 繪製圖片
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}

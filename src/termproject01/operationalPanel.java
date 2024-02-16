package termproject01;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.Serializable;

public class operationalPanel extends JPanel implements Serializable{
	private static final long serialVersionUID = 1L;
    GridBagConstraints toolBarContainer = new GridBagConstraints();
    mainWindow mainWin = null;
    setFontWin fontwin;
    Text t;
    int words=0;
    private BasketballPanel parent;
    private ProjectData projectData;
    private transient ImageIcon cursor = new ImageIcon("src\\img\\cursor.png");
    private transient ImageIcon pen = new ImageIcon("src\\img\\pen.gif");
    private transient ImageIcon straightLine = new ImageIcon("src\\img\\straightLine.gif");
    private transient ImageIcon curve = new ImageIcon("src\\img\\curve.gif");
    private transient ImageIcon square = new ImageIcon("src\\img\\square.gif");
    private transient ImageIcon circle = new ImageIcon("src\\img\\circle.gif");
    private transient ImageIcon oval = new ImageIcon("src\\img\\oval.gif");
    private transient ImageIcon narrow = new ImageIcon("src\\img\\narrow.gif");
    private transient ImageIcon triangle = new ImageIcon("src\\img\\triangle.gif");
    private transient ImageIcon eraser = new ImageIcon("src\\img\\eraser.gif");
    private transient ImageIcon fontSize = new ImageIcon("src\\img\\fontSize.gif");
    //private transient ImageIcon wordSize = new ImageIcon("src\\img\\wordSize.gif");
    //private transient ImageIcon word = new ImageIcon("src\\img\\word.gif");
    private transient ImageIcon paint = new ImageIcon("src\\img\\paint.gif");
    private transient ImageIcon redo = new ImageIcon("src\\img\\redo.png");
    private transient ImageIcon undo = new ImageIcon("src\\img\\undo.png");
    
    JButton cursorBtn = new JButton(cursor);
    JButton penBtn = new JButton(pen);  // 建立畫筆按鈕
    JButton straightLineBtn = new JButton(straightLine);  // 建立直線按鈕
    JButton curveBtn = new JButton(curve);  // 建立曲線按鈕
    JButton squareBtn = new JButton(square);  // 建立矩形按鈕
    JButton circleBtn = new JButton(circle);
    JButton ovalBtn = new JButton(oval);  // 建立橢圓按鈕
    JButton triangleBtn = new JButton(triangle);
    JButton narrowBtn = new JButton(narrow);  
    JButton eraserBtn = new JButton(eraser);
    JButton fontSizeBtn = new JButton(fontSize);
    //JButton wordBtn = new JButton(word);
    JButton colorChooserBtn = new JButton(paint);  // 建立顏色選擇按鈕
    JButton redoBtn = new JButton(redo);
    JButton undoBtn = new JButton(undo);
    private Status currentDrawMode = Status.NULL;
    private OBJType currentOBJType = OBJType.NULL;
    private Color selectedColor;  // 儲存所選顏色
   
    public operationalPanel(BasketballPanel basketballPanel,ProjectData projectData) {
    	this.parent = basketballPanel;
    	this.projectData = projectData;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  // 設定面板佈局為垂直排列
        addButton(cursorBtn);
        addButton(penBtn);
        addButton(eraserBtn);
        addButton(straightLineBtn);
        addButton(curveBtn);
        addButton(squareBtn);
        addButton(circleBtn);
        addButton(ovalBtn);
        addButton(triangleBtn);
        addButton(narrowBtn);
        addButton(fontSizeBtn);
        //addButton(wordBtn);
        addButton(colorChooserBtn);
        addButton(redoBtn);
        addButton(undoBtn);
        // 添加按鈕事件監聽器
        
        colorChooserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(operationalPanel.this.parent.activeShape!=null)
				{
            		operationalPanel.this.parent.activeShape.status=Status.inselected;
            		operationalPanel.this.parent.activeShape.hideOutline();
            		operationalPanel.this.parent.activeShape=null;
				}
                // 呼叫顏色選擇器並獲取所選顏色
                Color newColor = JColorChooser.showDialog(operationalPanel.this, "選擇顏色", selectedColor);
                if (newColor != null) {
                    // 儲存所選顏色
                	selectedColor=newColor;
                	projectData.setColor(selectedColor);
                	System.out.println("ProjectData Color: "+projectData.getColor());
                }
            }
        });
        cursorBtn.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        		if(operationalPanel.this.parent.activeShape!=null)
				{
            		operationalPanel.this.parent.activeShape.status=Status.inselected;
            		operationalPanel.this.parent.activeShape.hideOutline();
            		operationalPanel.this.parent.activeShape=null;
				}
        		operationalPanel.this.currentDrawMode = Status.NULL;
        		projectData.setStatus(currentDrawMode);
        		System.out.println("ProjectData Status: "+projectData.getStatus());
        	}
        });
        penBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(operationalPanel.this.parent.activeShape!=null)
				{
            		operationalPanel.this.parent.activeShape.status=Status.inselected;
            		operationalPanel.this.parent.activeShape.hideOutline();
            		operationalPanel.this.parent.activeShape=null;
				}
            	operationalPanel.this.currentDrawMode = Status.ready2draw;
            	projectData.setStatus(currentDrawMode);
            	System.out.println("ProjectData Status: "+projectData.getStatus());
            	//System.out.println("basketballPanel Status: "+basketballPanel.getStatus());
            }
        });
        eraserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(operationalPanel.this.parent.activeShape!=null)
				{
            		operationalPanel.this.parent.activeShape.status=Status.inselected;
            		operationalPanel.this.parent.activeShape.hideOutline();
            		operationalPanel.this.parent.activeShape=null;
				}
                operationalPanel.this.currentDrawMode = Status.ERASER;
            	projectData.setStatus(currentDrawMode);
            	operationalPanel.this.currentOBJType=OBJType.ERASER;
                projectData.setOBJType(currentOBJType);
            	System.out.println("ProjectData Status: "+projectData.getStatus());
            }
        });
        straightLineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(operationalPanel.this.parent.activeShape!=null)
				{
            		operationalPanel.this.parent.activeShape.status=Status.inselected;
            		operationalPanel.this.parent.activeShape.hideOutline();
            		operationalPanel.this.parent.activeShape=null;
				}
            	operationalPanel.this.currentDrawMode = Status.ready2createOBJ;
            	projectData.setStatus(currentDrawMode);
            	System.out.println("ProjectData Status: "+projectData.getStatus());
            	operationalPanel.this.currentOBJType=OBJType.straight_line;
            	projectData.setOBJType(currentOBJType);
            }
        });
        curveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(operationalPanel.this.parent.activeShape!=null)
				{
            		operationalPanel.this.parent.activeShape.status=Status.inselected;
            		operationalPanel.this.parent.activeShape.hideOutline();
            		operationalPanel.this.parent.activeShape=null;
				}
            	operationalPanel.this.currentDrawMode = Status.ready2createOBJ;
            	projectData.setStatus(currentDrawMode);
            	System.out.println("ProjectData Status: "+projectData.getStatus());
            	operationalPanel.this.currentOBJType=OBJType.curve;
            	projectData.setOBJType(currentOBJType);
            }
        });
        squareBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println(operationalPanel.this.parent.activeShape);
            	if(operationalPanel.this.parent.activeShape!=null)
				{
            		operationalPanel.this.parent.activeShape.status=Status.inselected;
            		operationalPanel.this.parent.activeShape.hideOutline();
            		operationalPanel.this.parent.activeShape=null;
				}
            	System.out.println(operationalPanel.this.parent.activeShape);
            	operationalPanel.this.currentDrawMode = Status.ready2createOBJ;
            	projectData.setStatus(currentDrawMode);
            	System.out.println("ProjectData Status: "+projectData.getStatus());
            	operationalPanel.this.currentOBJType=OBJType.rect;
            	projectData.setOBJType(currentOBJType);
            }
        });
        circleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(operationalPanel.this.parent.activeShape!=null)
				{
            		operationalPanel.this.parent.activeShape.status=Status.inselected;
            		operationalPanel.this.parent.activeShape.hideOutline();
            		operationalPanel.this.parent.activeShape=null;
				}
            	operationalPanel.this.currentDrawMode = Status.ready2createOBJ;
                projectData.setStatus(currentDrawMode);
            	System.out.println("ProjectData Status: "+projectData.getStatus());
            	operationalPanel.this.currentOBJType=OBJType.circle;
            	projectData.setOBJType(currentOBJType);
            }
        });
        ovalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(operationalPanel.this.parent.activeShape!=null)
				{
            		operationalPanel.this.parent.activeShape.status=Status.inselected;
            		operationalPanel.this.parent.activeShape.hideOutline();
            		operationalPanel.this.parent.activeShape=null;
				}
            	operationalPanel.this.currentDrawMode = Status.ready2createOBJ;
            	projectData.setStatus(currentDrawMode);
            	System.out.println("ProjectData Status: "+projectData.getStatus());
            	operationalPanel.this.currentOBJType=OBJType.oval;
            	projectData.setOBJType(currentOBJType);
            }
        });
        triangleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(operationalPanel.this.parent.activeShape!=null)
				{
            		operationalPanel.this.parent.activeShape.status=Status.inselected;
            		operationalPanel.this.parent.activeShape.hideOutline();
            		operationalPanel.this.parent.activeShape=null;
				}
            	operationalPanel.this.currentDrawMode = Status.ready2createOBJ;
            	projectData.setStatus(currentDrawMode);
            	System.out.println("ProjectData Status: "+projectData.getStatus());
            	operationalPanel.this.currentOBJType=OBJType.triangle;
            	projectData.setOBJType(currentOBJType);
            }
        });
        narrowBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(operationalPanel.this.parent.activeShape!=null)
				{
            		operationalPanel.this.parent.activeShape.status=Status.inselected;
            		operationalPanel.this.parent.activeShape.hideOutline();
            		operationalPanel.this.parent.activeShape=null;
				}
            	operationalPanel.this.currentDrawMode = Status.ready2createOBJ;
            	projectData.setStatus(currentDrawMode);
            	System.out.println("ProjectData Status: "+projectData.getStatus());
            	operationalPanel.this.currentOBJType=OBJType.arrowline;
            	projectData.setOBJType(currentOBJType);
            }
        });
        fontSizeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//            	if(parent.activeShape==null)
//            	JOptionPane.showMessageDialog(null, "Choose a Textbox!", "title", JOptionPane.WARNING_MESSAGE);
//            	else
//            	fontwin=new setFontWin((Text)parent.activeShape, projectData);
            	
            	if(operationalPanel.this.parent.activeShape!=null)
				{
            		operationalPanel.this.parent.activeShape.status=Status.inselected;
            		operationalPanel.this.parent.activeShape.hideOutline();
            		operationalPanel.this.parent.activeShape=null;
				}
            	operationalPanel.this.currentDrawMode = Status.ready2createOBJ;
            	projectData.setStatus(currentDrawMode);
            	System.out.println("ProjectData Status: "+projectData.getStatus());
            	operationalPanel.this.currentOBJType=OBJType.text;
            	projectData.setOBJType(currentOBJType);
            }
        });
        /*
        wordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	words++;
            	if(words==2)
            		JOptionPane.showMessageDialog(null, "The second textbox is not really needed right?", "Advice", JOptionPane.INFORMATION_MESSAGE);
            	else if(words==3)
            		JOptionPane.showMessageDialog(null, "Right?", "Advice", JOptionPane.INFORMATION_MESSAGE);
            	else if(words==4)
            		JOptionPane.showMessageDialog(null, "I'm sorry, but do you really need it?", "Advice", JOptionPane.INFORMATION_MESSAGE);
            	else if(words>4)
            		JOptionPane.showMessageDialog(null, "Button not Available", "Advice", JOptionPane.ERROR_MESSAGE);
            	else
            	{

            	}
            }
        });
        */
        redoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(operationalPanel.this.parent.activeShape!=null)
				{
            		operationalPanel.this.parent.activeShape.status=Status.inselected;
            		operationalPanel.this.parent.activeShape.hideOutline();
            		operationalPanel.this.parent.activeShape=null;
				}
            	operationalPanel.this.currentDrawMode = Status.REDO;
            	projectData.setStatus(currentDrawMode);
            	basketballPanel.redo();
            	repaint();
            	System.out.println("ProjectData Status: "+projectData.getStatus());
            }
        });
        undoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(operationalPanel.this.parent.activeShape!=null)
				{
            		operationalPanel.this.parent.activeShape.status=Status.inselected;
            		operationalPanel.this.parent.activeShape.hideOutline();
            		operationalPanel.this.parent.activeShape=null;
				}
            	operationalPanel.this.currentDrawMode = Status.UNDO;
            	projectData.setStatus(currentDrawMode);
            	basketballPanel.undo();
            	repaint();
            	System.out.println("ProjectData Status: "+projectData.getStatus());
            }
        });
    }
    
    public void addButton(JButton btn) {
        Dimension maxButtonSize = new Dimension(100, 40);  // 設定按鈕的最大大小
        btn.setMaximumSize(maxButtonSize);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);  // 讓按鈕在中央對齊
        add(btn);
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }
    
}

package termproject01;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.*;
import java.util.Vector;

public class BasketballPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	mainWindow mainWin;
	TabData tab_data;
	private ProjectData projectData = new ProjectData(mainWin);
	int[] X_aPlayer;
	int[] Y_aPlayer;
	int[] X_bPlayer;
	int[] Y_bPlayer;
	
	int test=0;
	int X_ball;
	int Y_ball;

	String[] a_Number;
	String[] b_Number;

	ImagePanel blueplayer1;
	ImagePanel blueplayer2;
	ImagePanel blueplayer3;
	ImagePanel blueplayer4;
	ImagePanel blueplayer5;
	ImagePanel redplayer1;
	ImagePanel redplayer2;
	ImagePanel redplayer3;
	ImagePanel redplayer4;
	ImagePanel redplayer5;
	ImagePanel Ball;

	Point lp=null;
	Point cp=null;
	Point fp=null;
	Shape activeShape =null;
	
	boolean isDragging = false;
	boolean screenClicked = false;
	Vector<Line> lines = null;
//	Vector<Shape> shapes = null;
	Color color = Color.BLACK;  // jColorchooser for color stored.
	protected static RenderingHints hints;
	OBJType obj2create;

	private transient String redBallAddress = "src\\termproject01\\redCircle.png";
	private transient String blueBallAddress = "src\\termproject01\\blueCircle.png";
	private transient String ballAddress = "src\\termproject01\\Ball.png";
	private transient String basketballBg = "src\\termproject01\\basketball.png";

	ImageIcon basketball = new ImageIcon(basketballBg);
	int basketballWidth = (int)(basketball.getIconWidth() * 0.65);
	int basketballHeight = (int)(basketball.getIconHeight() * 0.65);

	Shape outlineshape;
	private int Y_shape;
	private int X_shape;
	
	private Stack<Shape> undoHistory = new Stack<>();//kc
	private Stack<Shape> redoHistory = new Stack<>();//kc
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setPreferredSize(new Dimension(basketballWidth, basketballHeight));
		g.drawImage(basketball.getImage(), 0, 0, (int) basketballWidth, (int) basketballHeight, this);
		g.setColor(this.color);
		
		Graphics2D g2d = (Graphics2D) g;
		if(lines!=null)
		{
			for(Line l:lines)
			{
				l.paintComponent(g2d);
			}
		}
		if(activeShape!=null)
		{
			if(activeShape.outlineVisible)
			{
			//	g.setXORMode(new Color(0,255,0));
				g.setColor(Color.red);
				Point l=activeShape.getLocation();
				Dimension s=activeShape.getSize();
				if(activeShape.getName()=="circle")
				{
					if(s.width>=s.height)
						g.drawRect(l.x-6, l.y-6, s.height+12, s.height+12);
					else
					g.drawRect(l.x-6, l.y-6, s.width+12, s.width+12);
				}
				else
				g.drawRect(l.x-6, l.y-6, s.width+12, s.height+12);
			}
		}

	}


	public BasketballPanel(mainWindow mainWin,ProjectData projectData) {
		
		super();
		this.mainWin = mainWin;
		this.projectData = projectData;
		// 初始化其他變數
		X_aPlayer = projectData.getX_aPlayer();
		Y_aPlayer = projectData.getY_aPlayer();
		X_bPlayer = projectData.getX_bPlayer();
		Y_bPlayer = projectData.getY_bPlayer();
		X_ball = projectData.getX_ball();
		Y_ball = projectData.getY_ball();
		a_Number = projectData.getPlayerNum('A');
		b_Number = projectData.getPlayerNum('B');
		this.setLayout(null);
		blueplayer1 = new ImagePanel(blueBallAddress, 'B' , 0 ,X_bPlayer[0], Y_bPlayer[0], b_Number[0], this);
		blueplayer2 = new ImagePanel(blueBallAddress, 'B' , 1 ,X_bPlayer[1], Y_bPlayer[1], b_Number[1], this);
		blueplayer3 = new ImagePanel(blueBallAddress, 'B' , 2 ,X_bPlayer[2], Y_bPlayer[2], b_Number[2], this);
		blueplayer4 = new ImagePanel(blueBallAddress, 'B' , 3 ,X_bPlayer[3], Y_bPlayer[3], b_Number[3], this);
		blueplayer5 = new ImagePanel(blueBallAddress, 'B' , 4 ,X_bPlayer[4], Y_bPlayer[4], b_Number[4], this);
		redplayer1 = new ImagePanel(redBallAddress, 'A' , 0 ,X_aPlayer[0], Y_aPlayer[0], a_Number[0], this);
		redplayer2 = new ImagePanel(redBallAddress, 'A' , 1 ,X_aPlayer[1], Y_aPlayer[1], a_Number[1], this);
		redplayer3 = new ImagePanel(redBallAddress, 'A' , 2 ,X_aPlayer[2], Y_aPlayer[2], a_Number[2], this);
		redplayer4 = new ImagePanel(redBallAddress, 'A' , 3 ,X_aPlayer[3], Y_aPlayer[3], a_Number[3], this);
		redplayer5 = new ImagePanel(redBallAddress, 'A' , 4 ,X_aPlayer[4], Y_aPlayer[4], a_Number[4], this);
		Ball = new ImagePanel(ballAddress ,X_ball , Y_ball, this);


		blueplayer1.setOpaque(false);
		blueplayer2.setOpaque(false);
		blueplayer3.setOpaque(false);
		blueplayer4.setOpaque(false);
		blueplayer5.setOpaque(false);
		
		
		

		redplayer1.setOpaque(false);
		redplayer2.setOpaque(false);
		redplayer3.setOpaque(false);
		redplayer4.setOpaque(false);
		redplayer5.setOpaque(false);
		Ball.setOpaque(false);


		this.add(redplayer1);
		this.add(redplayer2);
		this.add(redplayer3);	
		this.add(redplayer4);
		this.add(redplayer5);

		this.add(blueplayer1);
		this.add(blueplayer2);
		this.add(blueplayer3);
		this.add(blueplayer4);
		this.add(blueplayer5);
		
		this.setComponentZOrder(this.blueplayer1, 0);
		this.setComponentZOrder(this.blueplayer2, 0);
		this.setComponentZOrder(this.blueplayer3, 0);
		this.setComponentZOrder(this.blueplayer4, 0);
		this.setComponentZOrder(this.blueplayer5, 0);
		
		this.setComponentZOrder(this.redplayer1, 0);
		this.setComponentZOrder(this.redplayer2, 0);
		this.setComponentZOrder(this.redplayer3, 0);
		this.setComponentZOrder(this.redplayer4, 0);
		this.setComponentZOrder(this.redplayer5, 0);
		
		this.setComponentZOrder(this.Ball, 0);
		

		this.add(Ball);
		repaint();
		
		this.obj2create = projectData.getOBJType();
		System.out.println("BasketballPanel obj2create: "+this.obj2create);
		
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				SwingUtilities.invokeLater(() -> {
					
					BasketballPanel.this.color = BasketballPanel.this.projectData.getColor(BasketballPanel.this);
					Graphics g = BasketballPanel.this.getGraphics();
					
					g.setColor(BasketballPanel.this.color);
					if(BasketballPanel.this.activeShape!=null)
					{
						System.out.println("activeShape: "+BasketballPanel.this.activeShape);
						BasketballPanel.this.activeShape.hideOutline();
						BasketballPanel.this.activeShape.status=Status.inselected;
					}
					if(projectData.getStatus() == Status.ready2createOBJ)
					{
						
						projectData.setStatus(Status.creatingOBJ);
						System.out.println(projectData.getStatus());
						lp = e.getPoint();
						System.out.println(lp);
					}
					if(projectData.getStatus() == Status.ready2draw)
					{
						projectData.setStatus(Status.drawing);
						System.out.println(projectData.getStatus());
						fp = e.getPoint();

					}
					if(projectData.getStatus()==Status.NULL)
					{
						if(BasketballPanel.this.activeShape!=null)
						{
							System.out.println("activeShape: "+BasketballPanel.this.activeShape);
							BasketballPanel.this.activeShape.hideOutline();
							BasketballPanel.this.activeShape.status=Status.inselected;
							
							System.out.println("BasketballPanel.this.activeShape.status: "+BasketballPanel.this.activeShape.status);
							BasketballPanel.this.activeShape=null;
							System.out.println("activeShape: "+BasketballPanel.this.activeShape);
						}
					}
					System.out.println("activeShape: "+BasketballPanel.this.activeShape);
					isDragging = true;
				});
			}
			public void mouseReleased(MouseEvent e) {
				SwingUtilities.invokeLater(() -> {
					BasketballPanel.this.color = BasketballPanel.this.projectData.getColor(BasketballPanel.this);
					{
						if (projectData.getStatus() == Status.drawing) {

							fp=null;
							cp=null;
							isDragging = false;
							System.out.println("isDragging: " + isDragging);
		                    repaint();
							projectData.setStatus(Status.NULL);

							
						}
						else if (projectData.getStatus() == Status.creatingOBJ) {
							System.out.println(projectData.getOBJType());
							isDragging = false;
							//System.out.println("isDragging: " + isDragging);
							Graphics g=BasketballPanel.this.getGraphics();
							if(BasketballPanel.this.color==null) 
								BasketballPanel.this.color = Color.BLACK;
							else
								g.setXORMode(BasketballPanel.this.color);
							Shape s=null;
							
							System.out.print("Trying to new a object: "+projectData.getOBJType());
							if(projectData.getOBJType() == OBJType.rect)
								s=new Rect(lp, cp, color, BasketballPanel.this, obj2create, projectData);
							if(projectData.getOBJType() == OBJType.oval)
								s=new Oval(lp, cp, color, BasketballPanel.this, obj2create, projectData);
							if(projectData.getOBJType() == OBJType.circle) {
								s=new Circle(lp, cp, color, BasketballPanel.this, obj2create, projectData);
							s.setName("circle");}
							if(projectData.getOBJType() == OBJType.triangle)
								s=new Triangle(lp, cp, color, BasketballPanel.this, obj2create, projectData);
							if(projectData.getOBJType() == OBJType.straight_line)
								s=new StraightLine(lp, cp, color, BasketballPanel.this, obj2create, projectData);
							if(projectData.getOBJType() == OBJType.arrowline)
								s=new Narrow(lp, cp, color, BasketballPanel.this, obj2create, projectData);
							if(projectData.getOBJType() == OBJType.curve) 
								s=new Curve(lp, cp, color, BasketballPanel.this, obj2create, projectData);
							if(projectData.getOBJType() == OBJType.text) {
								s=new Text(lp, cp, color, BasketballPanel.this, obj2create, projectData);
								s.setName("text");}
							undoHistory.push(s);//kc
							smartSetSizeLocation(s, lp, cp);
							
							Dimension size = s.getSize();
							Point position = s.getLocation();
							s.setBounds(position.x,position.y,size.width,size.height);
							
							s.revalidate();
							System.out.println("This is validate test: "+s);
							BasketballPanel.this.add(s,BorderLayout.PAGE_START);
							System.out.println("This is validate test: "+s);
							BasketballPanel.this.validate();
							s.showOutline();
							BasketballPanel.this.activeShape=s;
							System.out.println("ActivateShape: "+BasketballPanel.this.activeShape);
							BasketballPanel.this.repaint();
							lp=null;
							cp=null;
							projectData.setStatus(Status.NULL);
						}
						
						else if(projectData.getOBJType()==OBJType.ERASER) {
							if(activeShape!=null) {
								//undoHistory.search(BasketballPanel.this.activeShape);
								Shape res=activeShape;
								
								redoHistory.push(res);
								undoHistory.remove(res);
								//undoHistory.push(activeShape);
								//undoHistory.pop();
								
								projectData.setStatus(Status.NULL);
								System.out.println("History: "+undoHistory);
					        	System.out.println("redo History: "+redoHistory);
					        }
						}
						else if(projectData.getStatus() == Status.REDO) {
							redo();
							projectData.setStatus(Status.NULL);
							
						}
						else if(projectData.getStatus() == Status.UNDO) {
							undo();
							projectData.setStatus(Status.NULL);
						}
					}
						
						
					
					System.out.println("activeshape: "+activeShape);
					
				});
			}
		});
		
		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				
				if(isDragging) {
					if(projectData.getStatus()==Status.drawing){
						Graphics g = BasketballPanel.this.getGraphics();
						cp = e.getPoint();
						if(BasketballPanel.this.color==null) {
							BasketballPanel.this.color = Color.BLACK;
						}
						if(lines==null)
							lines=new Vector<Line>();
						Line line = new Line(fp,cp,color,projectData,BasketballPanel.this);
						line.addMouseListenerForEraser();
						lines.add(line);
						BasketballPanel.this.add(line);
						System.out.println("Status: Drawing. (fp,cp)"+"("+fp+","+cp+")");
						System.out.println("Line: "+line.toString());
						smartSetDrawSizeLocation(line, lp, cp);

						BasketballPanel.this.repaint();
						BasketballPanel.this.validate();
						fp = cp;
					}
					if(projectData.getStatus() == Status.creatingOBJ){
						
						Graphics g=BasketballPanel.this.getGraphics();
						g.setXORMode(Color.red);

						if(cp!=null)
						{
							smartDrawRect(g, lp, cp);
						}
						cp=e.getPoint();
						smartDrawRect(g, lp, cp);
						
					}
				}
			}
		});
		//this.setLayout(new BorderLayout());
	}
	void smartSetSizeLocation(Shape s, Point fp, Point cp)
	{

		if(fp!=null && cp!=null) {
		if((fp.x <= cp.x)&&(fp.y <= cp.y))
		{
			s.setSize(cp.x-fp.x, cp.y-fp.y);
			s.setLocation(fp);
		}
		else if((fp.x > cp.x)&&(fp.y > cp.y))
		{
			s.setSize(fp.x-cp.x, fp.y-cp.y);
			s.setLocation(cp);

		}
		else if((fp.x <= cp.x)&&(fp.y > cp.y))
		{
			s.setSize(cp.x-fp.x, fp.y-cp.y);
			s.setLocation(fp.x, cp.y);
		}
		else 
		{
			s.setSize(fp.x-cp.x, cp.y-fp.y);
			s.setLocation(cp.x, fp.y);
		}	}
	}

	void smartSetDrawSizeLocation(Line s, Point fp, Point cp)
	{

		if(fp!=null && cp!=null) {
		if((fp.x <= cp.x)&&(fp.y <= cp.y))
		{
			s.setSize(cp.x-fp.x, cp.y-fp.y);
			s.setLocation(fp);
		}
		else if((fp.x > cp.x)&&(fp.y > cp.y))
		{
			s.setSize(fp.x-cp.x, fp.y-cp.y);
			s.setLocation(cp);

		}
		else if((fp.x <= cp.x)&&(fp.y > cp.y))
		{
			s.setSize(cp.x-fp.x, fp.y-cp.y);
			s.setLocation(fp.x, cp.y);
		}
		else 
		{
			s.setSize(fp.x-cp.x, cp.y-fp.y);
			s.setLocation(cp.x, fp.y);
		}	}
	}

	void smartDrawRect(Graphics g, Point fp, Point cp )
	{
		if(fp!=null && cp!=null) {
		if((fp.x <= cp.x)&&(fp.y <= cp.y))
			g.drawRect(fp.x, fp.y, cp.x-fp.x, cp.y-fp.y);
		else if((fp.x > cp.x)&&(fp.y > cp.y))
			g.drawRect(cp.x, cp.y, fp.x-cp.x, fp.y-cp.y);
		else if((fp.x <= cp.x)&&(fp.y > cp.y))
			g.drawRect(fp.x, cp.y, cp.x-fp.x, fp.y-cp.y);
		else 
			g.drawRect(cp.x, fp.y, fp.x-cp.x, cp.y-fp.y);	}
	}

	public void updateView() {
		SwingUtilities.invokeLater(() -> {
			String fileName = "src\\termproject01\\projectdata.ser"; // Your default file name
			// Get project data
			//loadProject loader = new loadProject(mainWin,this);
			//projectData = loader.loadProjectData(fileName);

			
			if (projectData != null) {
				int[] newX_aPlayer = projectData.getX_aPlayer();
				int[] newY_aPlayer = projectData.getY_aPlayer();
				int[] newX_bPlayer = projectData.getX_bPlayer();
				int[] newY_bPlayer = projectData.getY_bPlayer();

				int numPlayers = Math.min(Math.min(newX_aPlayer.length, newY_aPlayer.length), Math.min(newX_bPlayer.length, newY_bPlayer.length));
				// Ensure player count matches project data
				System.out.println("Number of players to update: " + numPlayers);
				for (int i = 0; i < numPlayers; i++) {
					int x_aPlayer = newX_aPlayer[i];
					int y_aPlayer = newY_aPlayer[i];
					int x_bPlayer = newX_bPlayer[i];
					int y_bPlayer = newY_bPlayer[i];

					// Update player A's position
					X_aPlayer[i] = x_aPlayer;
					Y_aPlayer[i] = y_aPlayer;

					// Update player B's position
					X_bPlayer[i] = x_bPlayer;
					Y_bPlayer[i] = y_bPlayer;

					// Update projectData object with new pㄋositions
					projectData.setX_aPlayer(i, x_aPlayer);
					projectData.setY_aPlayer(i, y_aPlayer);
					projectData.setX_bPlayer(i, x_bPlayer);
					projectData.setY_bPlayer(i, y_bPlayer);
				}
				System.out.println("The Ball Position to update!");
				projectData.setX_ball(this.X_ball);
				projectData.setY_ball(this.Y_ball);
			} else {
				System.out.println("projectData not founded!");
			}

			blueplayer1.setLocation(X_bPlayer[0], Y_bPlayer[0]);
			blueplayer2.setLocation(X_bPlayer[1], Y_bPlayer[1]);
			blueplayer3.setLocation(X_bPlayer[2], Y_bPlayer[2]);
			blueplayer4.setLocation(X_bPlayer[3], Y_bPlayer[3]);
			blueplayer5.setLocation(X_bPlayer[4], Y_bPlayer[4]);
			redplayer1.setLocation(X_aPlayer[0], Y_aPlayer[0]);
			redplayer2.setLocation(X_aPlayer[1], Y_aPlayer[1]);
			redplayer3.setLocation(X_aPlayer[2], Y_aPlayer[2]);
			redplayer4.setLocation(X_aPlayer[3], Y_aPlayer[3]);
			redplayer5.setLocation(X_aPlayer[4], Y_aPlayer[4]);
			Ball.setLocation(X_ball, Y_ball);
			// Redraw the entire view to reflect
			repaint();
		});

	}
	

	public ImagePanel findPlayerPanel(char team, int number) {
		// 获取所有组件
		Component[] components = mainWin.tabbedPane.getComponents();
		//System.out.println(components);
		// 遍历所有组件	

		for (Component component : components) {
			if (component instanceof ImagePanel) {
				ImagePanel playerPanel = (ImagePanel) component;
				System.out.println(playerPanel);
				// 获取 ImagePanel 的 team 和 number 属性
				char panelTeam = playerPanel.getTeam();
				int panelNumber = playerPanel.getNumber();
				System.out.println(panelTeam);
				System.out.println(panelNumber);
				// 检查是否匹配
				if (team == panelTeam && number == panelNumber) {
					return playerPanel;
				}
			}else {
				System.out.println("NULL");
			}
		}
		// 如果没有找到匹配的 ImagePanel，返回 null
		return null;
	}
	public void setPlayerPosition(char team, int number, int x, int y) {
		System.out.println("Updating player position - Team: " + team + ", Number: " + number + ", X: " + x + ", Y: " + y);
		if (team == 'A') {
			this.X_aPlayer[number] = x;
			this.Y_aPlayer[number] = y;
			this.projectData.setX_aPlayer(number,x);
			this.projectData.setY_aPlayer(number,y);
			System.out.println(team);
			System.out.println("X: "+X_aPlayer[number]);
			System.out.println("Y: "+Y_aPlayer[number]);
			System.out.println("Test for projectData Saving: ");
			System.out.println("X: "+this.projectData.getX_aPlayer(number));
			System.out.println("Y: "+this.projectData.getY_aPlayer(number));
		}
		if (team == 'B') {
			this.X_bPlayer[number] = x;
			this.Y_bPlayer[number] = y;
			this.projectData.setX_bPlayer(number,x);
			this.projectData.setY_bPlayer(number,y);
			System.out.println(team);
			System.out.println("X: "+X_bPlayer[number]);
			System.out.println("Y: "+Y_bPlayer[number]);
			System.out.println("Test for projectData Saving: ");
			System.out.println("X: "+this.projectData.getX_bPlayer(number));
			System.out.println("Y: "+this.projectData.getY_bPlayer(number));
		}
	}
	public void setBallPosition(int x, int y) {
		System.out.println("Updating Ball position - Team: , X: " + x + ", Y: " + y);
		this.X_ball = x;
		this.Y_ball = y;
		this.projectData.setX_ball(x);
		this.projectData.setY_ball(y);
	}
	public void setShapePosition(int x, int y) {
		System.out.println("Updating Shape position - Team: , X: " + x + ", Y: " + y);
		this.X_shape = x;
		this.Y_shape = y;
		this.projectData.setX_shape(x);
		this.projectData.setY_shape(y);
	}

	public Point getPlayerPosition(char team, int number) {
		if (team == 'A') {
			return new Point(X_aPlayer[number], Y_aPlayer[number]);
		} else if (team == 'B') {
			return new Point(X_bPlayer[number], Y_bPlayer[number]);
		}
		return null;
	}

	public void setPlayerNum(char team,int playerNum,String Pnumber) {
		System.out.println("Updating Player Number: "+playerNum);
		this.projectData.setPlayerNum(team, playerNum, Pnumber);
	}
	
	public void undo() {
		if (!undoHistory.isEmpty()) {
			Shape undoShape = (Shape)undoHistory.pop();
	        redoHistory.push(undoShape);
	        //System.out.println("|undo|="+ redoHistory.size());
	        
	        //System.out.println("|undo location to string|="+undoShape.getLocation().toString());
	        //System.out.println("!!!DEBUG before undo !!!: ");
	        printAll_Components();
	        BasketballPanel.this.remove(undoShape);
	        //System.out.println("!!!DEBUG before undo !!!: ");
	        printAll_Components();
	        BasketballPanel.this.repaint();
	        BasketballPanel.this.revalidate();
	        //validate();
	    }
	}
	
	public void redo() {
		 
		if (!redoHistory.isEmpty()) {
			Shape redoShape = (Shape)redoHistory.pop();
	        undoHistory.push(redoShape);
	        
	        //System.out.println("|redo|="+ redoHistory.size());
	        //System.out.println("|redo shape|"+redoShape);
	        //add(redoShape);
	        //System.out.println("!!!DEBUG before redo !!!: ");
	        printAll_Components();
	        BasketballPanel.this.add(redoShape);
	        
	        
	        repaint();
	        //System.out.println("!!!DEBUG before validate !!!: ");
	        printAll_Components();
	        //System.out.println("!!This is shape!!"+redoShape);
//	        revalidate();
	        //this.validate();
	        //System.out.println("!!!DEBUG after validate !!!: ");
	        printAll_Components();
	        //System.out.println("!!This is shape!!"+redoShape);

	       
	    }
	    
	}
	public void printAll_Components() {
		Component[] components = BasketballPanel.this.getComponents();
		for (int i = 0; i < components.length; i++) {
	        System.out.println(i+" components "+components[i]);
	    }
	}
}

package termproject01;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JPanel;



public abstract class Shape extends JComponent{
	private static final long serialVersionUID = 1L;
	BasketballPanel parent;
	ProjectData projectData;
	Status status;
	OBJType objtype;
	Point s,e;
	Point lp, cp;
	ControlPoints cps=null;
	Shape sh;
	boolean outlineVisible=false;
	Color color;
	double rotationAngle = 0.0;
	
	public void setRotationAngle(double angle) {
        this.rotationAngle = angle;
    }

    public double getRotationAngle() {
        return rotationAngle;
    } // Initial rotation angle

	    
	Shape(Point s, Point e, Color color, BasketballPanel b,OBJType objtype,ProjectData projectData)
	{
		super();
		this.s=s;
		this.e=e;
		this.parent=b;
		this.color=color;
		this.objtype=objtype;
		this.projectData=projectData;
		this.setLayout(null);
		this.status=Status.selected;
this.addMouseListener(new MouseAdapter() {
			
			
			public void mouseReleased(MouseEvent e)
			{
				System.out.println("Shape released "+this);
				if(Shape.this.status==Status.ready2Move)
				{
					
					showOutline();
					Shape.this.status=Status.selected;
//					Shape.this.doSelected();
					Shape.this.parent.repaint();
					
				}
				else if(Shape.this.status==Status.moving)
				{
					
					showOutline();
					Shape.this.status=Status.selected;
//					Shape.this.doSelected();
					Shape.this.repaint();
			
				}
				else if (projectData.getOBJType()==OBJType.ERASER) {
					Shape.this.repaint();
					projectData.setStatus(Status.NULL);
				}
				else if(projectData.getStatus()==Status.targetSelected)
				{
					projectData.setStatus(Status.NULL);
					
				}
				System.out.println("activeshape: "+Shape.this.parent.activeShape);
				System.out.println("NW location: " + cps.NW.getLocation());
			    System.out.println("SW location: " + cps.SW.getLocation());
			    System.out.println("NE location: " + cps.NE.getLocation());
			    System.out.println("SE location: " + cps.SE.getLocation());
			    System.out.println("NW dimension: " + cps.NW.getSize());
			    System.out.println("SW dimension: " + cps.SW.getSize());
			    System.out.println("NE dimension: " + cps.NE.getSize());
			    System.out.println("SE dimension: " + cps.SE.getSize());
			}
			
			
			
			public void mousePressed(MouseEvent e)
			{
				
				System.out.println("Shape pressed "+this);
				
				if(Shape.this.status==Status.inselected)
				{
					if(Shape.this.parent.activeShape!=null)
					{
						Shape.this.parent.activeShape.hideOutline();
						Shape.this.parent.activeShape.status=Status.inselected;
					}
					
					Shape.this.status=Status.selected;
					Shape.this.parent.activeShape=Shape.this;
					Shape.this.showOutline();
					
					//Shape.this.parent.setComponentZOrder(Shape.this, -1);
					 //Shape.this.parent.repaint();
					
					
				}
				else if(Shape.this.status==Status.selected)
				{
					Shape.this.status=Status.ready2Move;
					Shape.this.hideOutline();
					lp=e.getLocationOnScreen();
					if (projectData.getOBJType()==OBJType.ERASER) {
						//parent.undo();
						parent.remove(parent.activeShape);
						projectData.setStatus(Status.NULL);
						projectData.setOBJType(OBJType.NULL);
					}
				}
				
				System.out.println("SW panel visibility: " + cps.SW.isVisible());
				
				

			}
		});
		
		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				//System.out.println(this);
				if(Shape.this.status==Status.ready2Move)
				{
					Shape.this.status=Status.moving;
					cp=e.getLocationOnScreen();
					Point loc=Shape.this.getLocation();
					Shape.this.setLocation(loc.x+cp.x-lp.x, loc.y+cp.y-lp.y);
					lp=cp;
										
				}
				else if(Shape.this.status==Status.moving)
				{
					cp=e.getLocationOnScreen();
					Point loc=Shape.this.getLocation();
					Shape.this.setLocation(loc.x+cp.x-lp.x, loc.y+cp.y-lp.y);
					Shape.this.parent.repaint();
					lp=cp;
				}
			}
			
			
		});

		
		
	}
	void doSelected()
	{
		this.status=Status.selected;
		this.showOutline();
	}
	
	void showOutline()
	{
		System.out.println("Outline showewd!");
		/*Graphics g=parent.getGraphics();
		g.setXORMode(new Color(0,255,0));
		Point l=this.getLocation();
		Dimension s=this.getSize();
		g.drawRect(l.x-6, l.y-6, s.width+12, s.height+12);*/
		
		outlineVisible=true;
		if(cps==null)
		{
			cps=new ControlPoints(this);
		}
		
		cps.setVisible(true);
		
		parent.repaint();
	}
	
	void hideOutline()
	{
		System.out.println("Outline hided");
		/*
		Graphics g=parent.getGraphics();
		g.setXORMode(new Color(0,255,0));
		Point l=this.getLocation();
		Dimension s=this.getSize();
		g.drawRect(l.x-6, l.y-6, s.width+12, s.height+12);
		*/
		
		outlineVisible=false;
		
		if(cps!=null)
		{
			cps.setVisible(false);
		}
		parent.repaint();
		
	}
	public OBJType getShapeType() {
        return objtype;
    }
	
	
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}

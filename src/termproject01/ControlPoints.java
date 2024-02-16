package termproject01;


import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;



public class ControlPoints {
	
	JPanel E, W, NW, N, NE, SW, S, SE,RT,f;
	Shape parent;
	boolean visible=true;
	Cursor currentCursor;
	Point lp, cp;
	int timepressed=0;
	public int getTimePressed()
	{
		return timepressed;
	}
	
	ControlPoints(Shape p)
	{
		parent=p;
		
		E = new JPanel();
		W = new JPanel();
		S = new JPanel();
		N = new JPanel();
		NE = new JPanel();
		SE = new JPanel();
		NW = new JPanel();
		SW = new JPanel();
		RT=new JPanel();
		f=new JPanel();
		
		
		
		E.setSize(7,7);
		W.setSize(7,7);
		N.setSize(7,7);
		S.setSize(7,7);
		NE.setSize(7,7);
		NW.setSize(7,7);
		SE.setSize(7,7);
		SW.setSize(7,7);
		RT.setSize(7,7);
		f.setSize(1,1);
		if(parent.getName()=="circle")
			setControlPointsBounds4Circle();
		else
		setControlPointsBounds();
		
		E.setBackground(Color.red);
		S.setBackground(Color.red);
		W.setBackground(Color.red);
		N.setBackground(Color.red);
		NE.setBackground(Color.red);
		SE.setBackground(Color.red);
		NW.setBackground(Color.red);
		SW.setBackground(Color.red);
		RT.setBackground(Color.red);
		f.setBackground(Color.black);
		f.setVisible(false);
		
		E.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)
			{
				ControlPoints.this.currentCursor = ControlPoints.this.E.getCursor();
				ControlPoints.this.E.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
			}
			public void mouseExited(MouseEvent e)
			{
				ControlPoints.this.E.setCursor(ControlPoints.this.currentCursor);
			}
			
			public void mousePressed(MouseEvent e)
			{
				if(ControlPoints.this.parent.status==Status.selected)
				{
					ControlPoints.this.parent.status=Status.ready2Resize;
					
					lp=e.getLocationOnScreen();
					System.out.println("pressed");
				}
			}
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPoints.this.parent.status==Status.Resizing)
				{
					ControlPoints.this.parent.status=Status.selected;
				}
			}
		});
		E.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				if((ControlPoints.this.parent.status==Status.ready2Resize)
				 ||(ControlPoints.this.parent.status==Status.Resizing))

				{
					System.out.println("dragged");
					ControlPoints.this.parent.status=Status.Resizing;
					cp = e.getLocationOnScreen();
					int dx= cp.x - lp.x;
					Dimension s = ControlPoints.this.parent.getSize();
					ControlPoints.this.parent.setSize(s.width+dx, s.height);
					ControlPoints.this.parent.repaint();
					ControlPoints.this.setVisible(true);
					lp=cp;	
				}
			}
		});
		S.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)
			{
				ControlPoints.this.currentCursor = ControlPoints.this.S.getCursor();
				ControlPoints.this.S.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
			}
			public void mouseExited(MouseEvent e)
			{
				ControlPoints.this.S.setCursor(ControlPoints.this.currentCursor);
			}
			
			public void mousePressed(MouseEvent e)
			{
				if(ControlPoints.this.parent.status==Status.selected)
				{
					ControlPoints.this.parent.status=Status.ready2Resize;
					
					lp=e.getLocationOnScreen();
					System.out.println("pressed");
				}
			}
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPoints.this.parent.status==Status.Resizing)
				{
					ControlPoints.this.parent.status=Status.selected;
				}
			}
		});
		S.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				if((ControlPoints.this.parent.status==Status.ready2Resize)
				 ||(ControlPoints.this.parent.status==Status.Resizing))

				{
					System.out.println("dragged");
					ControlPoints.this.parent.status=Status.Resizing;
					cp = e.getLocationOnScreen();
					int dx= cp.y - lp.y;
					Dimension s = ControlPoints.this.parent.getSize();
					ControlPoints.this.parent.setSize(s.width, s.height+dx);
					ControlPoints.this.parent.repaint();
					ControlPoints.this.setVisible(true);
					lp=cp;	
				}
			}
		});
		W.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)
			{
				ControlPoints.this.currentCursor = ControlPoints.this.W.getCursor();
				ControlPoints.this.W.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
			}
			public void mouseExited(MouseEvent e)
			{
				ControlPoints.this.W.setCursor(ControlPoints.this.currentCursor);
			}
			
			public void mousePressed(MouseEvent e)
			{
				if(ControlPoints.this.parent.status==Status.selected)
				{
					ControlPoints.this.parent.status=Status.ready2Resize;
					
					lp=e.getLocationOnScreen();
					System.out.println("pressed");
				}
			}
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPoints.this.parent.status==Status.Resizing)
				{
					ControlPoints.this.parent.status=Status.selected;
				}
			}
		});
		W.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				if((ControlPoints.this.parent.status==Status.ready2Resize)
				 ||(ControlPoints.this.parent.status==Status.Resizing))

				{
					System.out.println("dragged");
					ControlPoints.this.parent.status=Status.Resizing;
					cp = e.getLocationOnScreen();
					int dx= lp.x - cp.x;
					Dimension s = ControlPoints.this.parent.getSize();
					Point p=ControlPoints.this.parent.getLocation();
					ControlPoints.this.parent.setSize(s.width+dx, s.height);
					ControlPoints.this.parent.setLocation(p.x-dx, p.y);
					
					ControlPoints.this.parent.repaint();
					ControlPoints.this.setVisible(true);
					lp=cp;	
				}
			}
		});
		N.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)
			{
				ControlPoints.this.currentCursor = ControlPoints.this.N.getCursor();
				ControlPoints.this.N.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
			}
			public void mouseExited(MouseEvent e)
			{
				ControlPoints.this.N.setCursor(ControlPoints.this.currentCursor);
			}
			
			public void mousePressed(MouseEvent e)
			{
				if(ControlPoints.this.parent.status==Status.selected)
				{
					ControlPoints.this.parent.status=Status.ready2Resize;
					
					lp=e.getLocationOnScreen();
					System.out.println("pressed");
				}
			}
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPoints.this.parent.status==Status.Resizing)
				{
					ControlPoints.this.parent.status=Status.selected;
				}
			}
		});
		N.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				if((ControlPoints.this.parent.status==Status.ready2Resize)
				 ||(ControlPoints.this.parent.status==Status.Resizing))

				{
					System.out.println("dragged");
					ControlPoints.this.parent.status=Status.Resizing;
					cp = e.getLocationOnScreen();
					int dx= cp.y - lp.y;
					Dimension s = ControlPoints.this.parent.getSize();
					Point p=ControlPoints.this.parent.getLocation();
					ControlPoints.this.parent.setSize(s.width, s.height-dx);
					ControlPoints.this.parent.setLocation(p.x, p.y+dx);
					
					ControlPoints.this.parent.repaint();
					ControlPoints.this.setVisible(true);
					lp=cp;	
				}
			}
		});
		NE.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)
			{
				ControlPoints.this.currentCursor = ControlPoints.this.NE.getCursor();
				ControlPoints.this.NE.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
			}
			public void mouseExited(MouseEvent e)
			{
				ControlPoints.this.NE.setCursor(ControlPoints.this.currentCursor);
			}
			
			public void mousePressed(MouseEvent e)
			{
				if(ControlPoints.this.parent.status==Status.selected)
				{
					ControlPoints.this.parent.status=Status.ready2Resize;
					
					lp=e.getLocationOnScreen();
					System.out.println("pressed");
				}
			}
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPoints.this.parent.status==Status.Resizing)
				{
					ControlPoints.this.parent.status=Status.selected;
				}
			}
		});
		NE.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				if((ControlPoints.this.parent.status==Status.ready2Resize)
				 ||(ControlPoints.this.parent.status==Status.Resizing))

				{
					System.out.println("dragged");
					ControlPoints.this.parent.status=Status.Resizing;
					cp = e.getLocationOnScreen();
					int dx= cp.x - lp.x;
					int dy=cp.y-lp.y;
					Dimension s = ControlPoints.this.parent.getSize();
					Point p=ControlPoints.this.parent.getLocation();
					ControlPoints.this.parent.setSize(s.width+dx, s.height-dy);
					ControlPoints.this.parent.setLocation(p.x, p.y+dy);
					ControlPoints.this.parent.repaint();
					ControlPoints.this.setVisible(true);
					lp=cp;	
				}
			}
		});
		SE.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)
			{
				ControlPoints.this.currentCursor = ControlPoints.this.SE.getCursor();
				ControlPoints.this.SE.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
			}
			public void mouseExited(MouseEvent e)
			{
				ControlPoints.this.SE.setCursor(ControlPoints.this.currentCursor);
			}
			
			public void mousePressed(MouseEvent e)
			{
				if(ControlPoints.this.parent.status==Status.selected)
				{
					ControlPoints.this.parent.status=Status.ready2Resize;
					
					lp=e.getLocationOnScreen();
					System.out.println("pressed");
				}
			}
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPoints.this.parent.status==Status.Resizing)
				{
					ControlPoints.this.parent.status=Status.selected;
				}
			}
		});
		SE.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				if((ControlPoints.this.parent.status==Status.ready2Resize)
				 ||(ControlPoints.this.parent.status==Status.Resizing))

				{
					System.out.println("dragged");
					ControlPoints.this.parent.status=Status.Resizing;
					cp = e.getLocationOnScreen();
					int dx= cp.x - lp.x;
					int dy=cp.y-lp.y;
					Dimension s = ControlPoints.this.parent.getSize();
					Point p=ControlPoints.this.parent.getLocation();
					ControlPoints.this.parent.setSize(s.width+dx, s.height+dy);
					ControlPoints.this.parent.setLocation(p.x, p.y);
					ControlPoints.this.parent.repaint();
					ControlPoints.this.setVisible(true);
					lp=cp;	
				}
			}
		});
		NW.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)
			{
				ControlPoints.this.currentCursor = ControlPoints.this.NW.getCursor();
				ControlPoints.this.NW.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
			}
			public void mouseExited(MouseEvent e)
			{
				ControlPoints.this.NW.setCursor(ControlPoints.this.currentCursor);
			}
			
			public void mousePressed(MouseEvent e)
			{
				if(ControlPoints.this.parent.status==Status.selected)
				{
					ControlPoints.this.parent.status=Status.ready2Resize;
					
					lp=e.getLocationOnScreen();
					System.out.println("pressed");
				}
			}
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPoints.this.parent.status==Status.Resizing)
				{
					ControlPoints.this.parent.status=Status.selected;
				}
			}
		});
		NW.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				if((ControlPoints.this.parent.status==Status.ready2Resize)
				 ||(ControlPoints.this.parent.status==Status.Resizing))

				{
					System.out.println("dragged");
					ControlPoints.this.parent.status=Status.Resizing;
					cp = e.getLocationOnScreen();
					int dx= cp.x - lp.x;
					int dy=cp.y-lp.y;
					Dimension s = ControlPoints.this.parent.getSize();
					Point p=ControlPoints.this.parent.getLocation();
					ControlPoints.this.parent.setSize(s.width-dx, s.height-dy);
					ControlPoints.this.parent.setLocation(p.x+dx, p.y+dy);
					ControlPoints.this.parent.repaint();
					ControlPoints.this.setVisible(true);
					lp=cp;	
				}
			}
		});
		SW.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)
			{
				ControlPoints.this.currentCursor = ControlPoints.this.SW.getCursor();
				ControlPoints.this.SW.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
			}
			public void mouseExited(MouseEvent e)
			{
				ControlPoints.this.SW.setCursor(ControlPoints.this.currentCursor);
			}
			
			public void mousePressed(MouseEvent e)
			{
				if(ControlPoints.this.parent.status==Status.selected)
				{
					ControlPoints.this.parent.status=Status.ready2Resize;
					
					lp=e.getLocationOnScreen();
					System.out.println("pressed");
				}
			}
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPoints.this.parent.status==Status.Resizing)
				{
					ControlPoints.this.parent.status=Status.selected;
				}
				System.out.println("NW location: " + NW.getLocation());
				System.out.println("SW location: " + SW.getLocation());
			}
		});
		SW.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				if((ControlPoints.this.parent.status==Status.ready2Resize)
				 ||(ControlPoints.this.parent.status==Status.Resizing))

				{
					System.out.println("dragged");
					ControlPoints.this.parent.status=Status.Resizing;
					cp = e.getLocationOnScreen();
					int dx= cp.x - lp.x;
					int dy=cp.y-lp.y;
					Dimension s = ControlPoints.this.parent.getSize();
					Point p=ControlPoints.this.parent.getLocation();
					ControlPoints.this.parent.setSize(s.width-dx, s.height+dy);
					ControlPoints.this.parent.setLocation(p.x+dx, p.y);
					ControlPoints.this.parent.repaint();
					ControlPoints.this.setVisible(true);
					lp=cp;	
				}
			}
		});
		RT.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
                if (ControlPoints.this.parent.status == Status.selected) {
                	ControlPoints.this.currentCursor = ControlPoints.this.RT.getCursor();
    				ControlPoints.this.RT.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
			public void mouseExited(MouseEvent e)
			{
				ControlPoints.this.RT.setCursor(ControlPoints.this.currentCursor);
			}
			setTextWin TextWin;
            public void mousePressed(MouseEvent e) {
                if (ControlPoints.this.parent.status == Status.selected) {
                	timepressed++;
                    lp = e.getLocationOnScreen();
                    System.out.println("Rotate pressed");
                    double angle ;
                    angle=ControlPoints.this.parent.getRotationAngle();
                    double angleInRadians = Math.toRadians(45);
                    ControlPoints.this.parent.setRotationAngle(angle+angleInRadians);
                    cp = e.getLocationOnScreen();
					System.out.println(parent.parent.activeShape.getName());
					if(parent.parent.activeShape.getName()==("text"))
						TextWin = new setTextWin((Text) parent);
                    ControlPoints.this.parent.repaint();
                    if(timepressed==8)
                    	timepressed=0;
                }
            }
            
            public void mouseRealesed(MouseEvent e) {
            	ControlPoints.this.parent.repaint();
                }
            
        });
		

		
		parent.parent.add(E);
		parent.parent.add(W);
		parent.parent.add(S);
		parent.parent.add(N);
		parent.parent.add(NE);
		parent.parent.add(SE);
		parent.parent.add(NW);
		parent.parent.add(RT);
		parent.parent.add(SW);
		parent.parent.add(f);
		parent.parent.repaint();
		
		
	}
	
	private void setControlPointsBounds() {
        Point loc = parent.getLocation();
        Dimension d = parent.getSize();

        E.setBounds(loc.x + d.width + 3, loc.y + (d.height / 2) - 3, 7, 7);
        W.setBounds(loc.x - 9, loc.y + (d.height / 2) - 3, 7, 7);
        N.setBounds(loc.x + (d.width / 2) - 3, loc.y - 9, 7, 7);
        S.setBounds(loc.x + (d.width / 2) - 3, loc.y + d.height + 3, 7, 7);
        SW.setBounds(loc.x - 9, loc.y + d.height + 3, 7, 7);
        NE.setBounds(loc.x + d.width + 3, loc.y - 9, 7, 7);
        SE.setBounds(loc.x + d.width + 3, loc.y + d.height + 3, 7, 7);
        NW.setBounds(loc.x - 9, loc.y - 9, 7, 7);
        RT.setBounds(loc.x + (d.width / 2) - 3, loc.y - 24, 7, 7);
        
    }
	private void setControlPointsBounds4Circle() {
        Point loc = parent.getLocation();
        Dimension d = parent.getSize();

        if(d.getWidth()>d.getHeight()) {
        E.setBounds(loc.x + d.height + 3, loc.y + (d.height / 2) - 3, 7, 7);
        W.setBounds(loc.x - 9, loc.y + (d.height / 2) - 3, 7, 7);
        N.setBounds(loc.x + (d.height / 2) - 3, loc.y - 9, 7, 7);
        S.setBounds(loc.x + (d.height / 2) - 3, loc.y + d.height + 3, 7, 7);
        SW.setBounds(loc.x - 9, loc.y + d.height + 3, 7, 7);
        NE.setBounds(loc.x + d.height + 3, loc.y - 9, 7, 7);
        SE.setBounds(loc.x + d.height + 3, loc.y + d.height + 3, 7, 7);
        NW.setBounds(loc.x - 9, loc.y - 9, 7, 7);
        RT.setBounds(loc.x + (d.height / 2) - 3, loc.y - 24, 7, 7);
        }
        
    }
	
	void setVisible(boolean b)
	{
		if(b)
		{
			
			if(parent.getName()=="circle")
				setControlPointsBounds4Circle();
			else
			setControlPointsBounds();

		}
		if(parent.getName()=="circle")
		{
			NE.setVisible(b);
			SE.setVisible(b);
			NW.setVisible(b);
			SW.setVisible(b);
			E.setVisible(b);
			W.setVisible(b);
			N.setVisible(b);
			S.setVisible(b);
			RT.setVisible(b);
		}
		else
		E.setVisible(b);
		W.setVisible(b);
		N.setVisible(b);
		S.setVisible(b);
		NE.setVisible(b);
		SE.setVisible(b);
		NW.setVisible(b);
		SW.setVisible(b);
		RT.setVisible(b);
	}
	
	

}

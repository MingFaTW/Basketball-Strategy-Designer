package termproject01;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

public class Triangle extends Shape  implements Serializable{
	
	int timepressed;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Triangle(Point s, Point e, Color color, BasketballPanel g,OBJType objtype,ProjectData projectData)
	{
		super(s,e,color,g, objtype, projectData);
	}
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;

        // Set the line color
        g2d.setColor(color);

        // Set the line thickness
        timepressed = cps.getTimePressed();
        if(timepressed==0 || timepressed==4) {
        g2d.setStroke(new BasicStroke(3));
		g.drawLine(1, this.getHeight()-1, this.getWidth()-1, this.getHeight()-1);
		g.drawLine(1, this.getHeight()-1, this.getWidth()/2, 1);
		g.drawLine(this.getWidth()-1, this.getHeight()-1, this.getWidth()/2, 1);
        }
        if(timepressed==1 || timepressed==5) {
            g2d.setStroke(new BasicStroke(3));
    		g.drawLine(1, 1, 1, this.getHeight()-1);
    		g.drawLine(1, 1, this.getWidth()-1, this.getHeight()/2);
    		g.drawLine(1, this.getHeight()-1, this.getWidth()-1, this.getHeight()/2);
            }
        if(timepressed==2 || timepressed==6) {
            g2d.setStroke(new BasicStroke(3));
    		g.drawLine(1, 1, this.getWidth()-1, 1);
    		g.drawLine(this.getWidth()/2, this.getHeight()-1, 1, 1);
    		g.drawLine(this.getWidth()/2, this.getHeight()-1, this.getWidth()-1, 1);
            }
        if(timepressed==3 || timepressed==7) {
            g2d.setStroke(new BasicStroke(3));
    		g.drawLine(this.getWidth()-1, 1, this.getWidth()-1, this.getHeight()-1);
    		g.drawLine(1, this.getHeight()/2, this.getWidth()-1, this.getHeight()-1);
    		g.drawLine(1, this.getHeight()/2, this.getWidth()-1, 1);
            }
	}
	void smartDrawTriangle(Graphics g,Point fp,Point cp){
		
		if((fp.x <= cp.x)&&(fp.y <= cp.y) )
		{
			g.drawLine(fp.x, cp.y, cp.x, cp.y);
			g.drawLine((cp.x+fp.x)/2,fp.y, fp.x, cp.y);
			g.drawLine((cp.x+fp.x)/2,fp.y, cp.x, cp.y);
		}
		else if((fp.x > cp.x)&&(fp.y > cp.y))
		{
			g.drawLine(fp.x, cp.y, cp.x, cp.y);
			g.drawLine((cp.x+fp.x)/2,fp.y, fp.x, cp.y);
			g.drawLine((cp.x+fp.x)/2,fp.y, cp.x, cp.y);
		}
		else if((fp.x <= cp.x)&&(fp.y > cp.y))
		{	
			g.drawLine(fp.x, cp.y, cp.x, cp.y);
			g.drawLine((cp.x+fp.x)/2,fp.y, fp.x, cp.y);
			g.drawLine((cp.x+fp.x)/2,fp.y, cp.x, cp.y);
		}
		else 
		{
			g.drawLine(fp.x, cp.y, cp.x, cp.y);
			g.drawLine((cp.x+fp.x)/2,fp.y, fp.x, cp.y);
			g.drawLine((cp.x+fp.x)/2,fp.y, cp.x, cp.y);
		}
	}
}
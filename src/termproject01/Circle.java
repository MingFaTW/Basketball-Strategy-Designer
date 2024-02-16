package termproject01;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

public class Circle extends Shape  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Circle(Point s, Point e, Color color,BasketballPanel b, OBJType objtype,ProjectData projectData)
	{
		super(s,e,color,b , objtype, projectData);
	}
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;

        // Set the line color
        g2d.setColor(color);

        // Set the line thickness
        g2d.setStroke(new BasicStroke(3));
		if(this.getWidth()>this.getHeight())
			g.drawOval(1, 1, this.getHeight()-2,this.getHeight()-2);
		if(this.getWidth()<=this.getHeight())
			g.drawOval(1, 1, this.getWidth()-2,this.getWidth()-2);
	}
	void smartDrawCircle(Graphics g,Point fp,Point cp){
		if((fp.x <= cp.x)&&(fp.y <= cp.y) )
		{
			if(cp.x-fp.x>cp.y-fp.y)
				g.drawOval(fp.x, fp.y, cp.y-fp.y, cp.y-fp.y);
			else
				g.drawOval(fp.x, fp.y, cp.x-fp.x, cp.x-fp.x);
		}
		else if((fp.x > cp.x)&&(fp.y > cp.y))
		{
			if(fp.x-cp.x>fp.y-cp.y)
				g.drawOval(cp.x, cp.y, fp.y-cp.y, fp.y-cp.y);
			else
				g.drawOval(cp.x, cp.y, fp.x-cp.x, fp.x-cp.x);
		}
		else if((fp.x <= cp.x)&&(fp.y > cp.y))
		{	
			if(cp.x-fp.x>fp.y-cp.y)
				g.drawOval(fp.x, cp.y, fp.y-cp.y, fp.y-cp.y);
			else
				g.drawOval(fp.x, cp.y, cp.x-fp.x, cp.x-fp.x);
		}
		else 
		{
			if(fp.x-cp.x>cp.y-fp.y)
				g.drawOval(cp.x, fp.y, cp.y-fp.y, cp.y-fp.y);
			else
				g.drawOval(cp.x, fp.y, fp.x-cp.x, fp.x-cp.x);
		}
	}
}
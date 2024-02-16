package termproject01;

import java.awt.Point;
import java.io.Serializable;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Rect extends Shape implements Serializable{

	int timepressed;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Rect(Point s, Point e, Color color, BasketballPanel g,OBJType objtype,ProjectData projectData)
	{
		super(s,e,color,g, objtype, projectData);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// Set the line color
		g2d.setColor(color);

		// Set the line thickness
		g2d.setStroke(new BasicStroke(3));
		timepressed = cps.getTimePressed();
			g.drawRect(1, 1, this.getWidth()-3, this.getHeight()-3);
		
	}
	void smartDrawRect(Graphics g, Point fp, Point kp )
	{
		Point cp=new Point(this.getX() + getWidth(), this.getY() + getHeight());
		if((fp.x <= cp.x)&&(fp.y <= cp.y))
			g.drawRect(fp.x, fp.y, cp.x-fp.x, cp.y-fp.y);
		else if((fp.x > cp.x)&&(fp.y > cp.y))
			g.drawRect(cp.x, cp.y, fp.x-cp.x, fp.y-cp.y);
		else if((fp.x <= cp.x)&&(fp.y > cp.y))
			g.drawRect(fp.x, cp.y, cp.x-fp.x, fp.y-cp.y);
		else 
			g.drawRect(cp.x, fp.y, fp.x-cp.x, cp.y-fp.y);	
	}

}

package termproject01;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

public class Oval extends Shape  implements Serializable{
	int timepressed;
	private static final long serialVersionUID = 1L;
	Oval(Point s, Point e, Color color,BasketballPanel b,OBJType objtype,ProjectData projectData)
	{
		super(s,e,color,b, objtype, projectData);
	}
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;

        // Set the line color
        g2d.setColor(color);

        // Set the line thickness
        g2d.setStroke(new BasicStroke(3));
        if(cps!=null)
        timepressed=cps.getTimePressed();
        	g.drawOval(1, 1, this.getWidth()-2,this.getHeight()-2);
		g2d.rotate(-getRotationAngle(), getLocation().x + getWidth() / 2, getLocation().y + getHeight() / 2);
	}
	
}

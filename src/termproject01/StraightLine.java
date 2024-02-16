package termproject01;

import java.awt.Point;
import java.io.Serializable;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class StraightLine extends Shape  implements Serializable{
	
	int timepressed;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StraightLine(Point s, Point e, Color color, BasketballPanel g,OBJType objtype,ProjectData projectData)
	{
		super(s,e,color,g, objtype, projectData);
	}
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;

        // Set the line color
        g2d.setColor(color);
        // Set the line thickness
        g2d.setStroke(new BasicStroke(3));
        timepressed = cps.getTimePressed();
        if(timepressed==0)
		g.drawLine(1, this.getHeight()/2, this.getWidth()-2, this.getHeight()/2);
        if(timepressed==1)
        	g.drawLine(1,1, this.getWidth()-2, this.getHeight()-2);
        if(timepressed==2)
        	g.drawLine(this.getWidth()/2,1, this.getWidth()/2, this.getHeight()-2);
        if(timepressed==3)
        	g.drawLine(1,this.getHeight()-2, this.getWidth()-2, 1);
        if(timepressed==4)
        	g.drawLine(1,this.getHeight()/2, this.getWidth()-2, this.getHeight()/2);
        if(timepressed==5)
        	g.drawLine(1,1, this.getWidth()-2, this.getHeight()-2);
        if(timepressed==6)
        	g.drawLine(this.getWidth()/2,1, this.getWidth()/2, this.getHeight()-2);
        if(timepressed==7)
        	g.drawLine(1,this.getHeight()-2, this.getWidth()-2, 1);
	}
	
}

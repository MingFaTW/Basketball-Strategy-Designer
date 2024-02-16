package termproject01;

import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import java.io.Serializable;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Curve extends Shape implements Serializable{

	int timepressed;
	private static final long serialVersionUID = 1L;
	
	
	
	Curve(Point s, Point e, Color color,BasketballPanel b,OBJType objtype,ProjectData projectData){
		super(s,e,color,b, objtype, projectData);
	}
	
	
	
	public void paint(Graphics g) {
	    Graphics2D g2d = (Graphics2D) g;

	    // Set the line color
	    g2d.setColor(color);

	    // Set the line thickness
	    g2d.setStroke(new BasicStroke(3));
	    timepressed=cps.getTimePressed();
	    
	    Point sp = new Point(1, 1);
	    Point lp = new Point(this.getWidth(), this.getHeight());

	    // Adjust control points based on your requirements
	    //Point controlPoint = new Point((sp.x + lp.x) / 2, (sp.y + lp.y) / 2 - 50);

	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	
	    if(timepressed==0) {
			int x1 = 1, y1 = this.getHeight()/2;
			int x2 = this.getWidth()-1, y2 = this.getHeight()/2;
			Point controlPoint = new Point((x1 + x2) / 2, (y1 + y2) / 2 - 50);
		    g2d.draw(new QuadCurve2D.Double(x1, y1, controlPoint.x, controlPoint.y, x2, y2));
		}
		if(timepressed==1) {
			int x1 = 1, y1 = 1;
			int x2 = this.getWidth()-1, y2 = this.getHeight()-1;
			Point controlPoint = new Point((x1 + x2) / 2, (y1 + y2) / 2 - 50);
		    g2d.draw(new QuadCurve2D.Double(x1, y1, controlPoint.x, controlPoint.y, x2, y2));
		}
		if(timepressed==2) {
			int x1 = this.getWidth()/2, y1 = 1;
			int x2 = this.getWidth()/2, y2 = this.getHeight()-1;
			Point controlPoint = new Point((x1 + x2) / 2, (y1 + y2) / 2 - 50);
		    g2d.draw(new QuadCurve2D.Double(x1, y1, controlPoint.x, controlPoint.y, x2, y2));
		}
		if(timepressed==3) {
			int x1 = this.getWidth()-1, y1 = 1;
			int x2 = 1, y2 = this.getHeight()-1;
			Point controlPoint = new Point((x1 + x2) / 2, (y1 + y2) / 2 - 50);
		    g2d.draw(new QuadCurve2D.Double(x1, y1, controlPoint.x, controlPoint.y, x2, y2));
		}
		if(timepressed==4) {
			int x1 = this.getWidth()-1, y1 = this.getHeight()/2;
			int x2 = 1, y2 = this.getHeight()/2;
			Point controlPoint = new Point((x1 + x2) / 2, (y1 + y2) / 2 - 50);
		    g2d.draw(new QuadCurve2D.Double(x1, y1, controlPoint.x, controlPoint.y, x2, y2));
		}
		if(timepressed==5) {
			int x1 = this.getWidth()-1, y1 = this.getHeight()-1;
			int x2 = 1, y2 = 1;
			Point controlPoint = new Point((x1 + x2) / 2, (y1 + y2) / 2 - 50);
		    g2d.draw(new QuadCurve2D.Double(x1, y1, controlPoint.x, controlPoint.y, x2, y2));
		}
		if(timepressed==6) {
			int x1 = this.getWidth()/2, y1 = this.getHeight()-1;
			int x2 = this.getWidth()/2, y2 = 1;
			Point controlPoint = new Point((x1 + x2) / 2, (y1 + y2) / 2 - 50);
		    g2d.draw(new QuadCurve2D.Double(x1, y1, controlPoint.x, controlPoint.y, x2, y2));
		}
		if(timepressed==7) {
			int x1 = 1, y1 = this.getHeight()-1;
			int x2 = this.getWidth()-1, y2 = 1;
			Point controlPoint = new Point((x1 + x2) / 2, (y1 + y2) / 2 - 50);
		    g2d.draw(new QuadCurve2D.Double(x1, y1, controlPoint.x, controlPoint.y, x2, y2));
		}
	}
}

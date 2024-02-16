package termproject01;

import java.awt.Point;
import java.io.Serializable;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Narrow extends Shape  implements Serializable{

	int timepressed;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Narrow(Point s, Point e, Color color,BasketballPanel b,OBJType objtype,ProjectData projectData)
	{
		super(s,e,color,b, objtype, projectData);
	}
	int arrowSize = 15;
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;

		// Set the line color
		g2d.setColor(color);

		// Set the line thickness
		g2d.setStroke(new BasicStroke(3));
		// Draw a line
		timepressed=cps.getTimePressed();
		if(timepressed==0) {
			int x1 = 1, y1 = this.getHeight()/2;
			int x2 = this.getWidth()-1, y2 = this.getHeight()/2;
			g2d.drawLine(x1, y1, x2, y2);
			drawArrowHead(g2d, x1, y1, x2, y2);
		}
		if(timepressed==1) {
			int x1 = 1, y1 = 1;
			int x2 = this.getWidth()-1, y2 = this.getHeight()-1;
			g2d.drawLine(x1, y1, x2, y2);
			drawArrowHead(g2d, x1, y1, x2, y2);
		}
		if(timepressed==2) {
			int x1 = this.getWidth()/2, y1 = 1;
			int x2 = this.getWidth()/2, y2 = this.getHeight()-1;
			g2d.drawLine(x1, y1, x2, y2);
			drawArrowHead(g2d, x1, y1, x2, y2);
		}
		if(timepressed==3) {
			int x1 = this.getWidth()-1, y1 = 1;
			int x2 = 1, y2 = this.getHeight()-1;
			g2d.drawLine(x1, y1, x2, y2);
			drawArrowHead(g2d, x1, y1, x2, y2);
		}
		if(timepressed==4) {
			int x1 = this.getWidth()-1, y1 = this.getHeight()/2;
			int x2 = 1, y2 = this.getHeight()/2;
			g2d.drawLine(x1, y1, x2, y2);
			drawArrowHead(g2d, x1, y1, x2, y2);
		}
		if(timepressed==5) {
			int x1 = this.getWidth()-1, y1 = this.getHeight()-1;
			int x2 = 1, y2 = 1;
			g2d.drawLine(x1, y1, x2, y2);
			drawArrowHead(g2d, x1, y1, x2, y2);
		}
		if(timepressed==6) {
			int x1 = this.getWidth()/2, y1 = this.getHeight()-1;
			int x2 = this.getWidth()/2, y2 = 1;
			g2d.drawLine(x1, y1, x2, y2);
			drawArrowHead(g2d, x1, y1, x2, y2);
		}
		if(timepressed==7) {
			int x1 = 1, y1 = this.getHeight()-1;
			int x2 = this.getWidth()-1, y2 = 1;
			g2d.drawLine(x1, y1, x2, y2);
			drawArrowHead(g2d, x1, y1, x2, y2);
		}
		
	}
	private void drawArrowHead(Graphics2D g2d, int x1, int y1, int x2, int y2) {
		double arrowSize = 10.0;
		double angle = Math.atan2(y2 - y1, x2 - x1);

		// Calculate the coordinates of the arrowhead
		int x3 = (int) (x2 - arrowSize * Math.cos(angle - Math.PI / 6));
		int y3 = (int) (y2 - arrowSize * Math.sin(angle - Math.PI / 6));
		int x4 = (int) (x2 - arrowSize * Math.cos(angle + Math.PI / 6));
		int y4 = (int) (y2 - arrowSize * Math.sin(angle + Math.PI / 6));

		// Draw the arrowhead
		g2d.drawLine(x2, y2, x3, y3);
		g2d.drawLine(x2, y2, x4, y4);
	}
	void smartDrawNarrow(Graphics g,Point fp){
		Point cp = new Point(this.getX() + getWidth(), this.getY() + getHeight());

		int arrowSize = 15;
		if (fp.x != cp.x) {
			double angle = Math.atan2(cp.y - fp.y, cp.x - fp.x);
			int x3 = cp.x - (int) (arrowSize * Math.cos(angle - Math.PI / 6));
			int y3 = cp.y - (int) (arrowSize * Math.sin(angle - Math.PI / 6));

			int x4 = cp.x - (int) (arrowSize * Math.cos(angle + Math.PI / 6));
			int y4 = cp.y - (int) (arrowSize * Math.sin(angle + Math.PI / 6));

			int[] arrowheadX = {cp.x, x3, x4};
			int[] arrowheadY = {cp.y, y3, y4};
			g.drawLine(fp.x, fp.y, cp.x, cp.y);
			g.fillPolygon(arrowheadX, arrowheadY, 3);
		} else {
			// Vertical line, adjust arrowhead based on y2 > y1 or y1 > y2
			int[] arrowheadX;
			int[] arrowheadY;

			if (cp.y > fp.y) {
				arrowheadX = new int[]{cp.x, cp.x - arrowSize, cp.x + arrowSize};
				arrowheadY = new int[]{cp.y, cp.y - arrowSize, cp.y - arrowSize};
			} else {
				arrowheadX = new int[]{cp.x, cp.x - arrowSize, cp.x + arrowSize};
				arrowheadY = new int[]{cp.y, cp.y + arrowSize, cp.y + arrowSize};
			}
			g.drawLine(fp.x, fp.y, cp.x, cp.y);
			g.fillPolygon(arrowheadX, arrowheadY, 3);
		}

	}
}
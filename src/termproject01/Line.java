package termproject01;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.JComponent;

public class Line extends JComponent implements Serializable {

    private static final long serialVersionUID = 1L;
    private Point sp, ep = null;
    private Color color = null;
    private ProjectData projectData = null;
    private BasketballPanel parent = null;

    public String toString() {
        return "Line[start=" + sp + ", end=" + ep + ", color=" + color + "]";
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
		g2d.setStroke(new BasicStroke(3));

        g2d.drawLine(this.sp.x, this.sp.y, this.ep.x, this.ep.y);
        Line.this.parent.repaint();
    }

    public Line(Point s, Point e, Color color, ProjectData projectData, BasketballPanel basketballPanel) {
        super();

        this.sp = s;
        this.ep = e;
        this.color = color;
        this.projectData = projectData;
        this.parent = basketballPanel;

        if (this.sp == null)
            System.out.println("Line: sp is null");
        else if (this.ep == null)
            System.out.println("Line: sp is null");
        else if (this.color == null)
            System.out.println("Line: color is null");
        else if (this.projectData == null)
            System.out.println("Line: projectData is null");
        else if (this.parent == null)
            System.out.println("Line: parent is null");
        else
            System.out.println("Line: All is Exist");

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                System.out.println("OBJType: " + projectData.getOBJType());
                if (projectData.getOBJType() == OBJType.ERASER) {
                    System.out.println("Line: " + Line.this);
                    removeLine();
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (projectData.getOBJType() == OBJType.ERASER) {

                }
            }

            public void mouseDragged(MouseEvent e) {
                if (projectData.getOBJType() == OBJType.ERASER) {
                    System.out.println("Line: " + Line.this);
                    parent.validate();
                    parent.repaint();
                }
            }
        });
    }

    public void removeLine() {
        if (parent != null) {
            parent.remove(this);
            parent.repaint();
        }
    }

    public void addMouseListenerForEraser() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("The Eraser is used in Line");
                if (projectData.getOBJType() == OBJType.ERASER) {
                    removeLine();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Additional actions for mouseReleased if needed
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (projectData.getOBJType() == OBJType.ERASER) {
                    removeLine();
                }
            }
        });
    }
}

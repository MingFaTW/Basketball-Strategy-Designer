package termproject01;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Text extends Shape implements Serializable {
	private boolean editable=true;
    private static final long serialVersionUID = 1L;
    private JLabel l;
    int FontOfText[];
    Shape parent;
    setTextWin s;
    String str="";
    ProjectData p;
    int h=1;
    Text(Point s, Point e, Color color, BasketballPanel b, OBJType objtype, ProjectData projectData) {
        super(s, e, color, b, objtype, projectData);
        
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        float strokeWidth = 2.0f;
        g2d.setStroke(new BasicStroke(strokeWidth));
        g2d.drawRect(1, 1, this.getWidth() - 3, this.getHeight() - 3);
        g2d.setFont(new Font(null, Font.PLAIN, projectData.getFont()));

        // Split the text into words
        String[] words = str.split("");
        StringBuilder line = new StringBuilder();
        int lineHeight = h * projectData.getFont();
        int maxWidth = this.getWidth() - 10; // Adjust as needed

        for (String word : words) {
            // Check if adding the word exceeds the width of the shape
            if (g2d.getFontMetrics().stringWidth(line.toString() + word) <= maxWidth) {
                line.append(word).append("");
            } else {
                // Draw the current line and move to the next line
                g2d.drawString(line.toString(), 5, lineHeight);
                line.setLength(0);
                line.append(word).append("");
                lineHeight += h * projectData.getFont();
            }
        }

        // Draw the remaining text
        g2d.drawString(line.toString(), 5, lineHeight);
    }





	

	public void setString(String str) {
		this.str=str;
		
	}
	
}
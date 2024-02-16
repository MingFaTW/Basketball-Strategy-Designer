package termproject01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
public class setFontWin implements ActionListener{

	JFrame frame = new JFrame();
	JLabel label = new JLabel("Input Font Size");
	JButton b=new JButton("Done");
	JTextField t=new JTextField();
	String s;
	Text parent;
	int sf;
	setFontWin(Text parent,ProjectData p){
		label.setBounds(0,0,100,50);
		label.setFont(new Font(null,Font.PLAIN,25));
		b.setBounds(100,160,200,40);
		t.setBounds(0,0,100,100);
		
		//t.setText(te.str);
		frame.add(label);
		b.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				s=t.getText();
				sf=Integer.parseInt(s);
				System.out.println(sf);
				
				p.setFont(sf);
				if(parent!=null)
                parent.repaint();
				
				frame.dispose();
				
			}
		});

		frame.add(t,BorderLayout.CENTER);
		frame.add(b,BorderLayout.SOUTH);
		frame.setSize(420,420);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
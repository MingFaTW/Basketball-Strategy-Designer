package termproject01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
public class setTextWin implements ActionListener{

	JFrame frame = new JFrame("Input Text");
	JButton b=new JButton("Done");
	JTextArea t=new JTextArea("Insert Text");
	String str;
	Text parent;
	
	setTextWin(Text parent){
		this.parent = parent;
		b.setBounds(100,160,200,40);
		t.setBounds(40,20,320,100);
		t.setLineWrap(true);
		t.setText(parent.str);
		//t.setText(te.str);
		b.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				str=t.getText();
				parent.setString(str);
				parent.repaint();
				System.out.println(str);
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
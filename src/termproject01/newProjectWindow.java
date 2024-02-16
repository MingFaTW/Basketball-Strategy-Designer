package termproject01;

import javax.swing.*;
import java.awt.*;
public class newProjectWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private static final long serialVersionUID = 1;
	public newProjectWindow(){
    	SwingUtilities.invokeLater(
                () -> {
                    mainWindow mainWin1 = new mainWindow();
                    mainWin1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
        );
    }
}



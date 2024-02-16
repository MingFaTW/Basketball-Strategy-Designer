package termproject01;

import javax.swing.*;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
        // 使用SwingUtilities的invokeLater方法以確保Swing元件在事件分派線程中被建立和執行
        SwingUtilities.invokeLater(
                () -> {
                	 mainWindow mainWin = new mainWindow();
                	 mainWin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
        );
    }
}


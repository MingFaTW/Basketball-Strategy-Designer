package termproject01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.io.File;

public class exportProject extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;

    public exportProject() {
        // Create JPanel as the main panel for the export window
        JPanel exportWin = new JPanel();
        // Set the title of the export window
        setTitle("Export Project");
        
        
        // Set the size of the export window
        setSize(400, 400);
        
        // Create a JLabel with the text 
        JLabel label = new JLabel("You created "+ (mainWindow.numTabsTempForGIFA-1) +" images and a Gif-Animation");
        JButton btn = new JButton("Open path folder");
        JButton btn2 = new JButton("Open Output.GIF");
        setContentPane(exportWin);
        Font font = new Font("Arial", Font.PLAIN, 20);
        label.setFont(font);        
        
        //String userInput = JOptionPane.showInputDialog(null, "請輸入GIF檔每張的播放間距(預設為100(一秒))", "時間設置", JOptionPane.QUESTION_MESSAGE);

        //String Fusetime = userInput;
        
        String userInput= "100";
        String Fusetime = "100";

        while (true) {
            userInput = JOptionPane.showInputDialog(null, "請輸入GIF檔每張的播放間距(預設為100(一秒))", "時間設置", JOptionPane.QUESTION_MESSAGE);
            
            if (userInput == null) {
                break;
            }
            try {
                // 嘗試將使用者輸入的字串轉換為整數
                int time = Integer.parseInt(userInput);
                
                // 檢查是否為正整數
                if (time > 0) {
                    Fusetime = userInput;
                    break;  // 跳出迴圈，使用者輸入正確
                } else {
                    JOptionPane.showMessageDialog(null, "請輸入大於零的正整數", "錯誤", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException e) {
                // 處理數字格式錯誤，顯示警告訊息
                JOptionPane.showMessageDialog(null, "「"+userInput +"」"+"不是有效的數字", "錯誤", JOptionPane.WARNING_MESSAGE);
            }
        }
        // Add the label to the exportWin panel
        exportWin.add(label);
        exportWin.add(btn);
        exportWin.add(btn2);
        
        btn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				String filePath = "src";
		        File file = new File(filePath);

		        try {
		            if (file.exists()) {
		                Desktop.getDesktop().open(file);
		            } else {
		                System.out.println("檔案不存在: " + filePath);
		            }
		        } catch (IOException e1) {
		            System.out.println("無法開啟檔案: " + e1.getMessage());
		        }
			}
		});
        
        btn2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				String filePath = "src\\outputGifa.gif";
		        File file = new File(filePath);

		        try {
		            if (file.exists()) {
		                Desktop.getDesktop().open(file);
		            } else {
		                System.out.println("檔案不存在: " + filePath);
		            }
		        } catch (IOException e1) {
		            System.out.println("無法開啟檔案: " + e1.getMessage());
		        }
			}
		});
        if (userInput == null) {
            return;
        }


        
        // Make the export window visible
        setVisible(true);
        System.out.println("選項卡數量：" + mainWindow.numTabsTempForGIFA);
        int tabIndex = 1; // 如果需要，請調整此索引
        System.out.println("在索引 " + tabIndex + " 的組件：" + mainWindow.tabbedPane.getComponentAt(tabIndex));
        mainWindow.tabbedPane.getComponent(1);
        System.out.println(mainWindow.numTabsTempForGIFA-1);
        //System.out.println(mainWindow.tabbedPane.getComponentAt(1));//先看是否取得正確page數
        //tabbedPane.getComponentAt(1) 取得內容
        
        //Component componentToExport = null;
        List<Component> componentToExport = new ArrayList<Component>();
        for(int i = 0;i < mainWindow.numTabsTempForGIFA-1;i++) {
        	componentToExport.add(mainWindow.tabbedPane.getComponentAt(i));
        }
        int index = 0;
        for(Component com:componentToExport) {
        	index = index + 1;
        	exportComponentAsImage(com, "src\\output"+index+".png");
        	System.out.println("src\\output"+index+".png");
        	System.out.println("Output Index: "+index);
        }
        index = 0;
    	GIFA gifa = new GIFA();
    	//gifa.createAndWriteGifAnimation("src\\output", getName());
        // 将组件导出为图像
        gifa.main(Fusetime,new String[] {});
        //開啟GIFA檔
        //String filePath = "src\\outputGifa.gif";
        //File file = new File(filePath);

        
       
    }
    private void exportComponentAsImage(Component component, String filePath) {
        // 创建一个 BufferedImage，用于保存组件的图像
        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // 获取图形上下文
        Graphics g = image.createGraphics();

        // 将组件绘制到图像上
        component.paint(g);

        // 释放图形上下文
        g.dispose();

        // 将图像保存为文件
        try {
            ImageIO.write(image, "png", new File(filePath));
            System.out.println("Image exported successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
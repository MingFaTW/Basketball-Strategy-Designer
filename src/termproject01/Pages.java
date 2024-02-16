package termproject01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.Serializable;
// 主要作為分頁使用
public class Pages extends JPanel implements Serializable{
	private static final long serialVersionUID = 1L;
    private mainWindow customJTabbedPane;
    private JLabel titleLabel;
    private JButton closeButton;

    public Pages(mainWindow customJTabbedPane) {
        this.customJTabbedPane = customJTabbedPane;
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setBorder(new EmptyBorder(5, 2, 2, 2));
        setOpaque(false);
        addLabel(); // 添加標籤
        add(new CustomButton("x")); // 添加關閉按鈕
    }

    
    
    private void addLabel() {
        JLabel label = new JLabel() {
            /** 設置標籤的文本，它將作為選項卡的標題 */
            public String getText() {
                int index = customJTabbedPane.tabbedPane
                        .indexOfTabComponent(Pages.this);
                if (index != -1) {
                    return customJTabbedPane.tabbedPane.getTitleAt(index);
                }
                return null;
            }
        };
        /** 增加標籤和按鈕之間的空間 */
        label.setBorder(new EmptyBorder(0, 0, 0, 10));
        add(label);
    }
    
    class CustomButton extends JButton implements MouseListener {
        public CustomButton(String text) {
            int size = 15;
            setText(text);
            /** 設置關閉按鈕的大小 */
            setPreferredSize(new Dimension(size, size));

            setToolTipText("關閉選項卡");

            /** 設置按鈕為透明 */
            setContentAreaFilled(false);

            /** 設置按鈕的邊框 */
            setBorder(new EtchedBorder());
            /** 不顯示邊框 */
            setBorderPainted(false);

            setFocusable(false);

            /** 添加滑鼠事件監聽器 */
            addMouseListener(this);
        }

        /** 當單擊按鈕時，關閉選項卡 */
        @Override
        public void mouseClicked(MouseEvent e) {
            int index = customJTabbedPane.tabbedPane
                    .indexOfTabComponent(Pages.this);
            if (index != -1) {
                customJTabbedPane.removeTab(index);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        /** 當滑鼠懸停在按鈕上時，顯示按鈕邊框 */
        @Override
        public void mouseEntered(MouseEvent e) {
            setBorderPainted(true);
            setForeground(Color.RED);
        }

        /** 當滑鼠移出按鈕時，隱藏按鈕邊框 */
        @Override
        public void mouseExited(MouseEvent e) {
            setBorderPainted(false);
            setForeground(Color.BLACK);
        }
    }
}



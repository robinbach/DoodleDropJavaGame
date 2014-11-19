package doodledrop.doodledropopen;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class playinterface {

	private JFrame frame = new JFrame("Play Interface");

	private JPanel imagePanel;

	private ImageIcon background;
	private ImageIcon characterpic;

	public static void main(String[] args) {
		new playinterface();
	}

	public playinterface() {
		background = new ImageIcon(getClass().getClassLoader().getResource("images/31.jpeg"));// ������������
		JLabel label = new JLabel(background);// ������������������������������������������
		// ���������������������������������������������������������������
		label.setBounds(0, 0, background.getIconWidth(),
				background.getIconHeight());
		// ������������������������JPanel������������������������setOpaque()������������������������
		imagePanel = (JPanel) frame.getContentPane();
		imagePanel.setOpaque(false);
		// ���������������������������������������BorderLayout
		imagePanel.setLayout(null);
		
		characterpic = new ImageIcon(getClass().getClassLoader().getResource("images/left0.png"));
		JLabel chalabel = new JLabel(characterpic);
		imagePanel.add(chalabel);
		
		chalabel.setBounds(10, 10, characterpic.getIconWidth(), characterpic.getIconHeight());
		
		chalabel.setLocation(30, 40);
		
		frame.getLayeredPane().setLayout(null);
		// ������������������������������������������������������������
		frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(background.getIconWidth(), background.getIconHeight());
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
}

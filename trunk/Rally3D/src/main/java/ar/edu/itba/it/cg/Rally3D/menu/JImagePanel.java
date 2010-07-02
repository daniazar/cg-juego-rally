package ar.edu.itba.it.cg.Rally3D.menu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;


import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JImagePanel extends JPanel {
	private static final BasicStroke TITLE_STROKE = new BasicStroke(3f);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;
	private boolean main;
	public JImagePanel(String img) {
		this(new ImageIcon(img).getImage(), null, false);
	}

	public JImagePanel(Image img, Rectangle rectangle, boolean main) {
		setBounds(rectangle);
		this.main = main;
		this.img = img;
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
		g.setColor(Color.WHITE);
		if(main){
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(TITLE_STROKE);
		g2.drawArc(-10, 5, getWidth() + 20, 10, 0, -180);
		g2.drawArc(-10, 54, getWidth() + 20, 10, 0, 180);
		}
	}

}

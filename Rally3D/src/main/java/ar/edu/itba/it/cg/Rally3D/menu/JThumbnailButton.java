package ar.edu.itba.it.cg.Rally3D.menu;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import ar.edu.itba.it.cg.Rally3D.menu.Menu.Action;


public class JThumbnailButton extends JButton {
	public static final Font FONT = new Font("Verdana", Font.BOLD, 12);
	private static final long serialVersionUID = 1L;
	public static final Color SELECTED_FONT_COLOR = new Color(255, 255, 0);
	public static final Color FONT_COLOR = new Color(255, 255, 255);
	private static final int THUMBNAIL_WIDTH = 100;
	private static final int THUMBNAIL_HEIGHT = 75;

	public JThumbnailButton(final String text, Image img, final Action action) {

		super(text);
		setFont(FONT);
		setForeground(FONT_COLOR);
		ImageIcon image = new ImageIcon(img.getScaledInstance(THUMBNAIL_WIDTH,
				THUMBNAIL_HEIGHT, Image.SCALE_SMOOTH));
		setIcon(image);
		// setMaximumSize(new Dimension(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT));
		// //Set the position of the text, relative to the icon:
		// setPreferredSize(new Dimension(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT));
		setBorderPainted(true);
		setContentAreaFilled(true);

		setVerticalTextPosition(JLabel.BOTTOM);
		setHorizontalTextPosition(JLabel.CENTER);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				Menu.getMenu().doAction(action, text);
			}
		});
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				0.5f));
		super.paint(g2);
		g2.dispose();
	}

}

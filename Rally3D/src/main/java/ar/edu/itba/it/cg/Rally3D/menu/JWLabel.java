package ar.edu.itba.it.cg.Rally3D.menu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class JWLabel extends JLabel {
	public static final Font FONT = new Font("Verdana", Font.BOLD, 12);
	private static final long serialVersionUID = 1L;
	public static final Color SELECTED_FONT_COLOR = new Color(255, 255, 0);
	public static final Color FONT_COLOR = new Color(255, 255, 255);

	public JWLabel(String text) {
		
		super(text);
		setFont(FONT);
		setForeground(FONT_COLOR);
	}
}

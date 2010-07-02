package ar.edu.itba.it.cg.Rally3D.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import org.pushingpixels.trident.Timeline;

public class ScreenButton extends JButton {

	public static final Font FONT = new Font("Verdana", Font.BOLD, 12);
	private static final long serialVersionUID = 1L;
	public static final Color SELECTED_FONT_COLOR = new Color(255, 255, 0);
	public static final Color FONT_COLOR = new Color(255, 255, 255);
	public static final Color COLOR = new Color(0, 0, 0);
	public static final int NORMAL_WIDTH = 120;
	public static final int NORMAL_HEIGHT = 120;

	public enum ButtonType {
		NORMAL
	}

	public static final Color BACKGROUND_COLOR_CENTER = new Color(0, 100, 255);

	/**
	 * Creates a new button with the specified text label.
	 * 
	 * @param label
	 *            The text label shown on the button.
	 * @param type
	 *            The appearance/skin of the button.
	 */
	public ScreenButton(String label, ButtonType type) {
		super(label);

		switch (type) {
		case NORMAL:
			paintNormalButton();
			break;

		// case SMALL : paintSmallButton(); break;
		default:
			throw new IllegalStateException("Invalid button type: " + type);
		}
	}

	private void paintNormalButton() {
		setBackground(BACKGROUND_COLOR_CENTER);
		setBorderPainted(true);
		setFont(FONT);
		setBounds(new Rectangle(0, 0, NORMAL_WIDTH, NORMAL_HEIGHT));
		
		setForeground(FONT_COLOR);
		final Timeline rolloverTimeline = new Timeline(this);
		rolloverTimeline.addPropertyToInterpolate("background", BACKGROUND_COLOR_CENTER,
				Color.red);
		rolloverTimeline.setDuration(1000);
		final Timeline rolloverTimeline2 = new Timeline(this);
		rolloverTimeline2.addPropertyToInterpolate("foreground", FONT_COLOR,
				SELECTED_FONT_COLOR);
		rolloverTimeline2.setDuration(1000);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rolloverTimeline.play();
				rolloverTimeline2.play();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rolloverTimeline.playReverse();
				rolloverTimeline.playReverse();
			}
		});

	}
}

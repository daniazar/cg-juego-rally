package ar.edu.itba.it.cg.Rally3D.menu;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


import ar.edu.itba.it.cg.Rally3D.main.Rally;
import ar.edu.itba.it.cg.Rally3D.menu.Menu.Action;
import ar.edu.itba.it.cg.Rally3D.menu.ScreenButton.ButtonType;

import com.jme.system.DisplaySystem;

public class Window {

	public static final Font TITLE_FONT = new Font("Verdana", Font.BOLD, 25);

	public static int STARTX = 80;
	public static int STARTY = 60;

	JPanel main, left;
	JLabel title;

	private static DisplaySystem display;
	private static Image mainBackground, back, backRight;
	private static JPanel right;

	public static void Init() {

		display = Rally.getGame().getDisplay();
		mainBackground = (new ImageIcon(Window.class.getClassLoader()
				.getResource("data/graphics/menu/background.jpg"))).getImage();
		mainBackground.getScaledInstance((int) (display.getWidth()),
				(int) (display.getHeight()), Image.SCALE_SMOOTH);

		back = (new ImageIcon(Window.class.getClassLoader().getResource(
				"data/graphics/menu/panel.png"))).getImage();

		back = back.getScaledInstance((int) (display.getWidth() * 0.7f),
				(int) (display.getHeight() * 0.78f), Image.SCALE_SMOOTH);

		backRight = (new ImageIcon(Window.class.getClassLoader().getResource(
				"data/graphics/menu/panel.png"))).getImage();
		backRight = backRight.getScaledInstance(
				(int) (display.getWidth() * 0.2f),
				(int) (display.getHeight() * 0.78f), Image.SCALE_SMOOTH);

		right = new JImagePanel(backRight, new Rectangle(0, 0, (int) (display
				.getWidth() * 0.2f), (int) (display.getHeight() * 0.78f)), false);
		FlowLayout experimentLayout = new FlowLayout();
		experimentLayout.setVgap(10);
		experimentLayout.setHgap(5);

		right.setLayout(experimentLayout);
		experimentLayout.setAlignment(FlowLayout.CENTER);
		right.setLocation(10 + ((int) (display.getWidth() * 0.75f)), 100);
		CreateRightBar();
	}

	public Window() {

		main = new JImagePanel(mainBackground, new Rectangle(STARTX, STARTY,
				display.getWidth(), display.getHeight()), true);
		left = new JImagePanel(back, new Rectangle(0, 0, (int) (display
				.getWidth() * 0.7f), (int) (display.getHeight() * 0.78f)), false);

		FlowLayout experimentLayout2 = new FlowLayout();

		left.setLayout(experimentLayout2);
		experimentLayout2.setAlignment(FlowLayout.CENTER);

		left.setLocation(10, 100);
		main.add(left);

		main.add(right);

	}

	public JPanel getRoot() {
		return main;
	}

	public static JPanel getRight() {
		return right;
	}

	public JPanel getLeft() {
		return left;
	}

	public static void CreateRightBar() {
		Menu.getMenu().createButton(right, "Start Game", Action.CHECK,
				ButtonType.NORMAL);

		Menu.getMenu().createButton(right, "Cars", Action.CARS,
				ButtonType.NORMAL);
		Menu.getMenu().createButton(right, "Circuit", Action.TRACK,
				ButtonType.NORMAL);

		Menu.getMenu().createButton(right, "Information", Action.SYSTEM_INFO,
				ButtonType.NORMAL);
		Menu.getMenu().createButton(right, "Exit", Action.EXIT,
				ButtonType.NORMAL);
		
		
	}

	public void setTitle(String titl) {
		this.title = new JLabel(titl);
		title.setFont(TITLE_FONT);
		title.setForeground(JWLabel.FONT_COLOR);
		title.setBounds(new Rectangle((int) (main.getWidth() - title
				.getPreferredSize().getWidth()) / 2, 20, (int) title
				.getPreferredSize().getWidth(), (int) title.getPreferredSize()
				.getHeight()));
		main.add(title);

	}
}

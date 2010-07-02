package ar.edu.itba.it.cg.Rally3D.menu;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ar.edu.itba.it.cg.Rally3D.main.Rally;
import ar.edu.itba.it.cg.Rally3D.main.Settings;
import ar.edu.itba.it.cg.Rally3D.util.Utils;

import com.jme.input.MouseInput;
import com.jmex.awt.swingui.JMEDesktopState;
import com.jmex.game.state.GameStateManager;

public class Menu {

	private JDesktopPane desktopPane;
	private Window current;
	private Screen selected;
	private Map<Screen, Window> menus;
	private static Settings settings = Settings.getInstance();
	private static Menu menu;
	private static final Logger logger = Logger.getLogger(Menu.class.getName());

	public static Menu getMenu() {
		if (menu == null)
			menu = new Menu();
		return menu;
	}

	public enum Screen {
		MAIN, SETTINGS, INFORMATION, RESULTS, RECORDS, CARS, TRACK, CHECK
	}

	public enum Action {
		EXIT, SYSTEM_INFO, SETTINGS, RECORDS, CARS, TRACK, START_GAME, CHECK
	}

	private Menu() {
		menu = this;
		final JMEDesktopState desktopState = new JMEDesktopState();
		GameStateManager.getInstance().attachChild(desktopState);
		System.out.println(desktopState.getName());
		desktopState.setActive(true);
		desktopPane = desktopState.getDesktop().getJDesktop();

		Window.Init();
		menus = new HashMap<Screen, Window>();
		setSelectedMenu(Screen.MAIN);

		/*
		 * Thread para que no desaparezcan la barra del menu ni los
		 * backgrounds....no es muy lindo, cambiarlo si encuentran una forma
		 * mejor de hacerlo.
		 */
		Thread ref = new Thread("Refresh") {
			public void run() {
				try {
					while (true) {
						if (desktopState.isActive()) {
							if (current != null) {
								current.getRoot().add(Window.getRight());
							}
							desktopPane.repaint();
						}
						Thread.sleep(1000);
						yield();
					}
				} catch (Exception e) {
					logger.throwing(this.getClass().toString(), "run()", e);
				}
			}
		};
		ref.start();
	}

	/**
	 * Sets the selected menu to the menu with the specified ID. When such as
	 * menu does not exist this method will create it.
	 */
	public void setSelectedMenu(Screen selected) {
		// Instantiate and add our JMEDesktopState
		desktopPane.removeAll();
		// Set the cursor to visible
		MouseInput.get().setCursorVisible(true);
		// If the menu does not exist yet, create it
		if (menus.get(selected) == null) {
			logger.log(Level.INFO, "Creating Menu " + selected.name() + ".");
			createMenu(selected);
			logger.log(Level.INFO, "Menu " + selected.name() + " created.");
		} else {
			current = menus.get(selected);
		}
		desktopPane.removeAll();
		desktopPane.add(current.getRoot());
	}

	private void createMenu(final Screen id) {
		// We check it on selected Menu.
		// // If the menu was already created before, exit
		// if (menus.get(id) != null) {
		// return;
		// }
		current = new Window();
		// por defecto no creamos los menus de vuelta, los que cambian hay que
		// recrearlos.
		menus.put(id, current);
		// GameTaskQueueManager.getManager().update(new Callable<Object>() {
		// public Object call() throws Exception {
		// SwingUtilities.invokeLater(new Runnable() {
		// public void run() {

		switch (id) {
		case MAIN:
			createMainMenu();
			break;
		case SETTINGS:
			// createSettingsMenu();
			break;
		case INFORMATION:
			createInformationMenu();
			break;
		case RESULTS:
			// createResultsMenu();
			break;
		case RECORDS:
			// createRecordsMenu();
			break;
		case CARS:
			createCarsMenu();
			break;
		case TRACK:
			createTrackMenu();
			break;
		case CHECK:
			createCheckMenu();
			break;
		default:
			throw new AssertionError();
		}
		// }
		// });

		// return null;
		//
		// }
		//
		// });

	}

	private void createCheckMenu() {
		menus.remove(Screen.CHECK);
		current.setTitle("Verify the settings");
		Image[] images = getCarThumbnails();
		List<String> names = settings.cars;
		for (int j = 0; j < images.length; j++) {
			if (names.get(j).equals(settings.car))
				current.getLeft().add(
						new JThumbnailButton(names.get(j), images[j],
								Action.CARS));
		}
		images = getCircuitThumbnails();
		names = settings.circuits;
		for (int j = 0; j < images.length; j++) {
			if (names.get(j).equals(settings.circuit))
				current.getLeft().add(
						new JThumbnailButton(names.get(j), images[j],
								Action.TRACK));
		}

		createButton(current.getLeft(), "Start Game", Action.START_GAME,
				ScreenButton.ButtonType.NORMAL);

	}

	protected void createCarsMenu() {
		current.setTitle("Select a vehicle");
		Image[] images = getCarThumbnails();
		List<String> names = settings.cars;
		for (int j = 0; j < images.length; j++) {
			current.getLeft().add(
					new JThumbnailButton(names.get(j), images[j], Action.CARS));
		}
	}

	protected void createTrackMenu() {
		current.setTitle("Select a Track");
		Image[] images = getCircuitThumbnails();
		List<String> names = settings.circuits;
		for (int j = 0; j < images.length; j++) {
			current.getLeft()
					.add(
							new JThumbnailButton(names.get(j), images[j],
									Action.TRACK));
		}
	}

	public void createMainMenu() {
		current.setTitle("Rally3D");
		current.getLeft().add(
				new JLabel(new ImageIcon(Menu.class.getClassLoader()
						.getResource("data/graphics/menu/logo.png"))));
		current.getRoot().repaint();
	}

	private void createInformationMenu() {
		JTextArea myJTextArea = new JTextArea();
		myJTextArea.setLineWrap(true);
		myJTextArea.setOpaque(false);
		myJTextArea.setWrapStyleWord(true);
		String text = "\n\n\n";
		// text += settings.getText("menu.copyright");
		// text += settings.getText("menu.version") + " " + Rally.VERSION;
		text += "This software is considered the intellectual property of its authors.\n"
				+ "It may not be distributed or changed in any way without the"
				+ "explicit approval of the authors.\n\n"
				+ "This software was made for a graphics computer course in ITBA.\n\n"
				+ "Uribe 100k uses the following software:\n"
				+ "Java (java.sun.com), jMonkeyEngine (jmonke<br>yengine.com),"
				+ "LWJGL (lwjgl.org)";

		myJTextArea.setFont(JWLabel.FONT);
		myJTextArea.setForeground(JWLabel.FONT_COLOR);
		myJTextArea.setText(text);
		myJTextArea.setBounds(0, 0, (int) (current.getLeft().getBounds()
				.getWidth() - 30), 10);
		current.getLeft().add(myJTextArea);

		JLabel image = new JLabel(new ImageIcon(Menu.class.getClassLoader()
				.getResource("data/graphics/java.png")));
		current.getLeft().add(image);
		current.setTitle("Information");
	}

	/**
	 * Creates a new menu button for the specified menu screen. When clicked,
	 * the specified action will be executed.
	 */
	public void createButton(JPanel menu, String label, final Action action,
			ScreenButton.ButtonType type) {
		ScreenButton button = new ScreenButton(label, type);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doAction(action);
			}
		});
		button.setBounds(0, 0, menu.getWidth() - 20, (int) button
				.getPreferredSize().getHeight());
		menu.add(button);
	}

	/**
	 * Executes an action. This method can be called by menu GUI components that
	 * want to execute a specific task. The name of the action should be on of
	 * the ACTION fields.
	 */
	private void doAction(Action action) {
		logger.info("Button " + action.name() + " pressed");
		switch (action) {
		case EXIT:
			Rally.getGame().finish();
			System.exit(0);
			break;
		case SYSTEM_INFO:
			setSelectedMenu(Screen.INFORMATION);
			break;
		case CARS:
			setSelectedMenu(Screen.CARS);
			break;
		case CHECK:
			setSelectedMenu(Screen.CHECK);
			break;
		case TRACK:
			setSelectedMenu(Screen.TRACK);
			break;
		case RECORDS:
			setSelectedMenu(Screen.RECORDS);
			break;
		case START_GAME:
			Rally.getInstance().startNewGame();
			break;

		default:
			throw new AssertionError();
		}
	}

	public Screen getSelectedMenu() {
		return selected;
	}

	private Image[] getCarThumbnails() {
		Image[] carThumbnails = new Image[settings.cars.size()];
		for (int i = 0; i < carThumbnails.length; i++) {
			carThumbnails[i] = Utils.loadImage("data/cars/" + settings.cars.get(i)
					+ "/preview.jpg");
		}
		return carThumbnails;
	}

	private Image[] getCircuitThumbnails() {
		Image[] circuitThumbnails = new Image[settings.circuits.size()];
		for (int i = 0; i < circuitThumbnails.length; i++) {
			circuitThumbnails[i] = Utils.loadImage("data/circuits/"
					+ settings.circuits.get(i) + "/preview.jpg");
		}
		return circuitThumbnails;
	}

	public void doAction(Action action, String info) {
		logger.info("Selected " + action.name() + " " + info);
		switch (action) {
		case CARS:
			setCar(info);
			break;
		case TRACK:
			setCircuit(info);
			break;

		default:
			throw new AssertionError();
		}
	}

	public void setCar(String car) {
		settings.car = car;
	}

	public void setCircuit(String circuit) {
		settings.circuit = circuit;
	}

}

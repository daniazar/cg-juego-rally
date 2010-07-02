package ar.edu.itba.it.cg.Rally3D.main;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;

import ar.edu.itba.it.cg.Rally3D.menu.Menu;

import com.jmex.game.StandardGame;
import com.jmex.game.state.GameStateManager;

public class Rally  {

	private static Rally instance;
    private static final Logger logger = Logger.getLogger(Rally.class
            .getName());
    
    private static StandardGame game = null;
	
    
    private Rally() {
    	//initSwing(); with this we get the default OS UI look and feel 
		// Create our StandardGame with default settings
    	game = new StandardGame("Rally3D");
		game.start();
    	logger.log(Level.INFO, "Starting Game.");

//		SkyBox.CreateBackGround();
    	logger.log(Level.INFO, "Starting Menu.");
		Menu menu = Menu.getMenu();
		
		
		
	}

	/**
	 * Initializes Swing by setting the look and feel, and any platform-dependent
	 * system properties. This method must be called before Swing is started.
	 */
	private static void initSwing() {		
		try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}

    public void startNewGame(){
    	//TODO Aca va el codigo del juego
    	GameStateManager.getInstance().deactivateAllChildren();
    }



    
    public static StandardGame getGame() {
		return game;
	}

	public static Rally getInstance() {
		return instance == null ? new Rally() : instance;
	}
	
	
    public static void main( String[] args ) throws Exception {
    	Rally.getInstance();

    }

}


	
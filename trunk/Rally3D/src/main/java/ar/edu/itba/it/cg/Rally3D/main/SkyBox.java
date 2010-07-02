package ar.edu.itba.it.cg.Rally3D.main;

import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jme.image.Image;
import com.jme.image.Texture;
import com.jme.scene.Node;
import com.jme.scene.Skybox;
import com.jme.util.GameTaskQueueManager;
import com.jme.util.TextureManager;
import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;

public class SkyBox {
	
	  private static Skybox skybox = null;
	  private static final Logger logger = Logger.getLogger(SkyBox.class
	            .getName());


/*	  protected void simpleUpdate() {
	    skybox.setLocalTranslation(cam.getLocation());
	  }
*/
	public static Node getSky(){  
		if(skybox!= null)
			return skybox;
    	logger.log(Level.INFO, "Creating Background.");

	    // Create a skybox
	    skybox = new Skybox("skybox", 10, 10, 10);

	    Texture north = TextureManager.loadTexture(
	    		SkyBox.class.getClassLoader().getResource(
	        "data/sky/texture/north.jpg"),
	        Texture.MinificationFilter.BilinearNearestMipMap,
	        Texture.MagnificationFilter.Bilinear, Image.Format.GuessNoCompression, 0.0f, true);
	    Texture south = TextureManager.loadTexture(
	    		SkyBox.class.getClassLoader().getResource(
	        "data/sky/texture/south.jpg"),
	        Texture.MinificationFilter.BilinearNearestMipMap,
	        Texture.MagnificationFilter.Bilinear, Image.Format.GuessNoCompression, 0.0f, true);
	    Texture east = TextureManager.loadTexture(
	    		SkyBox.class.getClassLoader().getResource(
	        "data/sky/texture/east.jpg"),
	        Texture.MinificationFilter.BilinearNearestMipMap,
	        Texture.MagnificationFilter.Bilinear, Image.Format.GuessNoCompression, 0.0f, true);
	    Texture west = TextureManager.loadTexture(
	    		SkyBox.class.getClassLoader().getResource(
	        "data/sky/texture/west.jpg"),
	        Texture.MinificationFilter.BilinearNearestMipMap,
	        Texture.MagnificationFilter.Bilinear, Image.Format.GuessNoCompression, 0.0f, true);
	    Texture up = TextureManager.loadTexture(
	    		SkyBox.class.getClassLoader().getResource(
	        "data/sky/texture/top.jpg"),
	        Texture.MinificationFilter.BilinearNearestMipMap,
	        Texture.MagnificationFilter.Bilinear, Image.Format.GuessNoCompression, 0.0f, true);
	    Texture down = TextureManager.loadTexture(
	    		SkyBox.class.getClassLoader().getResource(
	        "data/sky/texture/bottom.jpg"),
	        Texture.MinificationFilter.BilinearNearestMipMap,
	        Texture.MagnificationFilter.Bilinear, Image.Format.GuessNoCompression, 0.0f, true);

	    skybox.setTexture(Skybox.Face.North, north);
	    skybox.setTexture(Skybox.Face.West, west);
	    skybox.setTexture(Skybox.Face.South, south);
	    skybox.setTexture(Skybox.Face.East, east);
	    skybox.setTexture(Skybox.Face.Up, up);
	    skybox.setTexture(Skybox.Face.Down, down);
	    skybox.preloadTextures();
    	logger.log(Level.INFO, "Background created.");

	    return skybox;
	  }
	
	
	public static void CreateBackGround(){
		GameTaskQueueManager.getManager().update(new Callable<Object>() {
			public Object call() throws Exception {
				// Lets add a game state behind with some content
				BasicGameState debugState = new BasicGameState("BackgroundState");
				GameStateManager.getInstance().attachChild(debugState);
				debugState.setActive(true);
				debugState.getRootNode().attachChild(SkyBox.getSky());
				SkyBox.getSky().updateRenderState();
				return skybox;
			}
        });



	}
		
	
}

package ar.edu.itba.it.cg.Rally3D.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Miscellenous utility and convenience methods. This class can also be used for 
 * loading of simple resource files such as images.
 */
public final class Utils {
	
	/**
	 * Private constructor, utility class.
	 */
	private Utils() { 
		
	}
	
	/**
	 * Returns the amount of memory consumed by the JVM, in bytes. This amount is
	 * independant from the currently set heap size.
	 */
	public static long getConsumedMemory() {
		Runtime runtime = Runtime.getRuntime();
		return runtime.totalMemory() - runtime.freeMemory();
	}
	
    public static BufferedImage loadImage(InputStream input)
    throws IOException
{
    BufferedImage image = ImageIO.read(input);
    input.close();
    return image;
}

	/**
	 * Loads an image from the specified resource file.
	 * @throws NullPointerException when the image could not be located.
	 */
	public static BufferedImage loadImage(URL resource) {
		try {
			return loadImage(resource.openStream());
		} catch (IOException e) {
			throw new NullPointerException("Could not locate image: " + resource.getPath());
		}
	}
	
	/**
	 * Loads an image from the resource file at the specified path.
	 * @throws NullPointerException when the image could not be located.
	 */
	public static BufferedImage loadImage(String path) {
		URL url;
//		System.out.println("name: "+path); 
		url = Utils.class.getClassLoader().getResource(path);
//		System.out.println(url); 

		return loadImage(url);
	}	
	
	
	
}

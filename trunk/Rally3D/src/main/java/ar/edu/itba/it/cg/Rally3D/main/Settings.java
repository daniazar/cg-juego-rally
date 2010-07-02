package ar.edu.itba.it.cg.Rally3D.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import ar.edu.itba.it.cg.Rally3D.util.XMLUtil;

/**
 * Contains all global settings. This singleton class contains information that
 * needs to be accessed from anywhere, such as the <code>ResourceBundle</code>
 * for all text, the logger, and a number of constants.
 */
public final class Settings {

	public String name;
	public String car;
	public String circuit;
	public int mode;
	public int laps;
	public boolean sound;
	public int volume;

	public List<String> cars;
	public List<String> circuits;
	public int[][] controlsets;

	private static final Logger logger = Logger.getLogger(Settings.class
			.getName());

	public static final int MODE_TIME = 1;
	public static final int MODE_RACE = 2;
	// private static final String SETTINGS_URL = "settings.properties";
	private static final String CARS_URL = "data/cars/cars.xml";
	private static final String CIRCUITS_URL = "data/circuits/circuits.xml";

	private static final Settings instatnce = new Settings();

	/**
	 * Private singleton constructor.
	 * 
	 * @throws Exception
	 */
	private Settings() {

		cars = new ArrayList<String>();
		circuits = new ArrayList<String>();
		init();
	}

	/**
	 * Initializes this object by loading all required resources. No methods or
	 * fields from the class should be used before this method is called.
	 * 
	 * @throws IOException
	 * @throws SAXException
	 * @throws Exception
	 *             when one of the resources could not be loaded.
	 */
	public void init() {
		logger.info("Loading Settings.");
		// Load cars

		Document carsDocument;
		try {
			carsDocument = XMLUtil.parseXML(CARS_URL);
			Element carsNode = carsDocument.getDocumentElement();
			for (Element i : XMLUtil.getChildNodes(carsNode)) {
				cars.add(XMLUtil.getNodeAttribute(i, "name"));
			}

			// Load circuits

			Document circuitsDocument = XMLUtil.parseXML(CIRCUITS_URL);
			Element circuitsNode = circuitsDocument.getDocumentElement();
			for (Element i : XMLUtil.getChildNodes(circuitsNode)) {
				circuits.add(XMLUtil.getNodeAttribute(i, "name"));
			}
		} catch (Exception e) {
			System.err.println("Error parsing data" + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		car = cars.get(0);
		circuit = circuits.get(0);
		logger.info("Settings loaded.");
	}

	/**
	 * Returns the only existing instance of this class.
	 */
	public static Settings getInstance() {
		return instatnce;
	}
}

package hardcode.papierjosef.rajbo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import hardcode.papierjosef.bibliothek.JosefsFassade;
import hardcode.papierjosef.bibliothek.LibraryFacade;
import hardcode.papierjosef.rajbo.controller.WindowController;

public class Application implements PreferencesProvider {
	
	public final static String APP_NAME = "HardCode RAJBO";
	public final static String APP_VERSION = "0.0.1";
	public final static String WINDOW_TITLE = APP_NAME;
	public final static String HARDCODE_PAGE = "http://www.hard-code.de";
	public final static String HARDCODE_HELPPAGE = HARDCODE_PAGE + "/papierjosef/hilfe";
	public final static String APP_ABOUT = APP_NAME + "\n" + "Version " + APP_VERSION + "\n\n" + HARDCODE_PAGE;
	
	public final static String APP_PROPERTIES_FILENAME = "hardcode.josef.rajbo.properties.xml";
	public final static String PROPERTIES_SUBFOLDER = "settings";
	
	private Properties properties;
	private ResourceBundle i18n;
	
	private WindowController windowController;
	private LibraryFacade library;
	
	public Application(File applicationDirectory) {
		File propFile = new File(PROPERTIES_SUBFOLDER + File.separator + APP_PROPERTIES_FILENAME);
		try {
			properties = loadPropertiesFile(propFile);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		File libraryHome = new File(properties.getProperty("library_home"));
		library = new JosefsFassade(libraryHome);
		
		Locale locale = new Locale(properties.getProperty("locale"));
		i18n = ResourceBundle.getBundle("MessagesBundle", locale);
						
		windowController = new WindowController(this, WINDOW_TITLE, library);
		windowController.show(800,600);
	}

	@Override
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	@Override
	public String getLocaleString(String key) {
		return i18n.getString(key);
	}

	/**
	 * Loads a serialized XML Properties file and returns a Properties object
	 * @param filename String. Filename or path and filename to the XML serialized Properties file. 
	 * @return Properties.
	 * @throws InvalidPropertiesFormatException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static Properties loadPropertiesFile(File file) throws InvalidPropertiesFormatException, FileNotFoundException, IOException  {
		if(!file.exists()){
			throw new FileNotFoundException(file.getAbsolutePath());
		}
		Properties props = new Properties();
		FileInputStream fin = new FileInputStream(file);
		props.loadFromXML(fin);
		fin.close();
		return props;
	}

	@Override
	public String getPropertiesDirectory() {
		return System.getProperty("user.dir") + File.separator + PROPERTIES_SUBFOLDER;
	}
}

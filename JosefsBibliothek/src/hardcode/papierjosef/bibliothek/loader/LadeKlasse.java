package hardcode.papierjosef.bibliothek.loader;

import hardcode.papierjosef.bibliothek.exception.LibraryException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Laedt eine Klasse des Typs T aus einer class-Datei ein.
 */
public class LadeKlasse<T> {
	
	public LadeKlasse() {
		
	}
	
	/**
	 * Laedt eine Klasse des Typs T aus einer class-Datei
	 * @param pfad File: Pfad zum Ordner, in dem sich die Klassendatei befindet
	 * @param klassenName String: Name der Klassendatei, ohne .class, also z.B. nur "Klassenname".
	 * @return T: Die geladene Klasse
	 * @throws LibraryException
	 */
	@SuppressWarnings("unchecked")
	public T ladeKlasse(File pfad, String klassenName) throws LibraryException  {
		T object = null;
		try{	
			URL url = pfad.toURI().toURL();		
			//URLClassLoader loader = new URLClassLoader(new URL[]{url}, Regel.class.getClassLoader());
			URLClassLoader loader = new URLClassLoader(new URL[]{url});
			Class<?> c = loader.loadClass(klassenName);
			object = (T) c.newInstance();
			loader.close();
		} catch (MalformedURLException e) {
			throw new LibraryException("MalformedURLException");
		} catch (ClassNotFoundException e) {
			throw new LibraryException("ClassNotFoundException");
		} catch (IOException e) {
			throw new LibraryException("IOException");
		} catch (InstantiationException e) {
			throw new LibraryException("InstantiationException");
		} catch (IllegalAccessException e) {
			throw new LibraryException("IllegalAccessException");
		}
		return object;
	}
}
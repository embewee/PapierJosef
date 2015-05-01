package hardcode.papierjosef.bibliothek.loader;

import hardcode.papierjosef.bibliothek.exception.LibraryException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

@SuppressWarnings("unchecked")
public class XmlSerializer <T>{
	XStream xstream;
	
	public XmlSerializer() {
		xstream = new XStream(new StaxDriver());
	}
	
	public String serialize(T t) {
		return xstream.toXML(t);
	}
	
	public void serializeToFile(T t, File file) throws LibraryException {
		String xml = serialize(t);
		saveTextFile(file, xml);
	}
	
	public T deserialize(String xml) {
		Object o = xstream.fromXML(xml);
		return (T) o;
	}
	
	public T deserialize(File file) throws LibraryException {
		String xml = loadTextFile(file);
		Object o = xstream.fromXML(xml);
		return (T) o;
	}
	
	private String loadTextFile(File file) throws LibraryException {
		String text = null;
		BufferedReader br = null;
		try {
	    	br = new BufferedReader(new FileReader(file));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
	        text = sb.toString();
	    } catch (FileNotFoundException e) {
			throw new LibraryException("FileNotFoundException");
		} catch (IOException e) {
			throw new LibraryException("IOException");
		} finally {
	        try {
				br.close();
			} catch (IOException e) {
				throw new LibraryException("IOException");
			}
	    }
	    return text;
	}
	
	private void saveTextFile(File file, String text) throws LibraryException {
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter(new FileWriter(file));
		    writer.write(text);
		}
		catch ( IOException e)
		{
			throw new LibraryException("IOException");
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    	throw new LibraryException("IOException");
		    }
		}
	}
}

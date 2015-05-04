package hardcode.papierjosef.bibliothek.util;

import hardcode.papierjosef.model.document.Document;
import hardcode.papierjosef.model.document.Paragraph;
import hardcode.papierjosef.model.document.Sentence;
import hardcode.papierjosef.model.document.TextElement;
import hardcode.papierjosef.model.document.Token;

import java.util.Set;

public class DocumentPrinter {
	
	private DocumentPrinter(){}
	
	public static void printDocument(Document document) {
		System.out.println("DOCUMENT");
		printProperties(document);
		
		for (Paragraph p : document.getChildElements()) {
			System.out.println("PARAGRAPH");
			printProperties(p);
			
			for (Sentence s : p.getChildElements()) {
				System.out.println("SENTENCE");
				printProperties(s);
				
				for (Token t : s.getChildElements()) {
					System.out.println("TOKEN");
					printProperties(t);
					
				}
			}
		}
	}
	
	public static void printProperties(TextElement<?> te) {
		Set<String> keys = te.getPropertyKeys();
		for(String key : keys) {
			System.out.print("[" + key + ":" + te.getProperty(key) + "] ");
		}
		System.out.println();
	}
	

}

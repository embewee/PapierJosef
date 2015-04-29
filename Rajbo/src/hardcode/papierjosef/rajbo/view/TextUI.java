package hardcode.papierjosef.rajbo.view;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

public class TextUI extends JTextPane {
	
	
	public TextUI() {
		//TODO:
		//setEditable(false);
	}
	
	public void highlight(Color color, int start, int end) throws BadLocationException {
		DefaultHighlighter.DefaultHighlightPainter highlightPainter = 
		        new DefaultHighlighter.DefaultHighlightPainter(color);
			getHighlighter().addHighlight(start, end, highlightPainter);
	}
}

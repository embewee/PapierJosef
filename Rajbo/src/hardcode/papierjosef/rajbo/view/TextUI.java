package hardcode.papierjosef.rajbo.view;

import hardcode.papierjosef.model.document.Paragraph;
import hardcode.papierjosef.model.document.Sentence;
import hardcode.papierjosef.model.document.TextElement;
import hardcode.papierjosef.model.document.Token;
import hardcode.papierjosef.model.document.Word;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

public class TextUI extends JTextPane {

	private static final long serialVersionUID = 8000197036339578530L;
	
	Map<TextElement<?>, Object> highlights;

	public TextUI() {
		// TODO:
		// setEditable(false);
		highlights = new HashMap<TextElement<?>, Object>();
	}

	public void highlight(Color color, int start, int end)
			throws BadLocationException {
		DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(
				color);
		getHighlighter().addHighlight(start, end, highlightPainter);
	}

	public void highlightTextElement(Color color, TextElement<?> te)
			throws BadLocationException {
		DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(
				color);
		Object o = getHighlighter().addHighlight((int) te.getStart(),
				(int) te.getEnd(), highlightPainter);
		highlights.put(te, o);
	}

	public void unhighlightTextElement(TextElement<?> te)
			throws BadLocationException {
		getHighlighter().removeHighlight(highlights.get(te));
		highlights.remove(te);
	}

	public void colorize(List<Paragraph> paragraphs, String property,
			Class<? extends TextElement<?>> lvl, Color c)
			throws BadLocationException {
		for (Paragraph paragraph : paragraphs) {
			if (lvl == Paragraph.class) {
				if (paragraph.hasProperty(property))
					highlightTextElement(c, paragraph);
			} else if (lvl == Sentence.class) {
				for (Sentence sentence : paragraph.getChildElements()) {
					if (sentence.hasProperty(property))
						highlightTextElement(c, sentence);
				}
			} else if (lvl == Token.class) {
				for (Sentence sentence : paragraph.getChildElements()) {
					for (Token token : sentence.getChildElements()) {
						if (token.hasProperty(property))
							highlightTextElement(c, token);
					}
				}
			} else if (lvl == Word.class) {
				for (Sentence sentence : paragraph.getChildElements()) {
					for (Word token : sentence.getWordsOnly()) {
						if (token.hasProperty(property))
							highlightTextElement(c, token);
					}
				}
			}
		}
	}

	public void colorizeAllLevels(List<Paragraph> paragraphs, String property,
			Color c) throws BadLocationException {
		colorize(paragraphs,property,Paragraph.class,c);
		colorize(paragraphs,property,Sentence.class,c);
		colorize(paragraphs,property,Token.class,c);
		colorize(paragraphs,property,Word.class,c);
	}

	public void clearHighlight(List<Paragraph> paragraphs, String property,
			Class<? extends TextElement<?>> lvl) throws BadLocationException {
		for (Paragraph paragraph : paragraphs) {
			if (lvl == Paragraph.class) {
				if (paragraph.hasProperty(property))
					unhighlightTextElement(paragraph);
			} else if (lvl == Sentence.class) {
				for (Sentence sentence : paragraph.getChildElements()) {
					if (sentence.hasProperty(property))
						unhighlightTextElement(sentence);
				}
			} else if (lvl == Token.class) {
				for (Sentence sentence : paragraph.getChildElements())
					for (Token token : sentence.getChildElements())
						if (token.hasProperty(property))
							unhighlightTextElement(token);
			} else if (lvl == Word.class) {
				for (Sentence sentence : paragraph.getChildElements())
					for (Word token : sentence.getWordsOnly())
						if (token.hasProperty(property))
							unhighlightTextElement(token);
			}
		}
	}
}

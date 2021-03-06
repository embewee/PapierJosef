package hardcode.papierjosef.bibliothek.operation;

import hardcode.papierjosef.bibliothek.exception.ParameterNotSetException;
import hardcode.papierjosef.model.document.Document;
import hardcode.papierjosef.model.document.HumbugException;
import hardcode.papierjosef.model.document.Paragraph;
import hardcode.papierjosef.model.document.Punctuation;
import hardcode.papierjosef.model.document.Sentence;
import hardcode.papierjosef.model.document.Token;
import hardcode.papierjosef.model.document.Word;

import java.util.Iterator;
import java.util.List;

public class OperationProcessor {

	private OperationProcessor(){}
	
	public static void execute(OperationChain chain, Document document) throws ParameterNotSetException, HumbugException {
		Iterator it = chain.iterator();
		while(it.hasNext()) {
			Operation op = chain.next();
			execute(op, document);
		}
//		execute(chain.getChain(), document);
	}
		
	public static void execute(List<Operation<?>> operations, Document document) throws HumbugException, ParameterNotSetException {
		for(Operation<?> op : operations) {
			execute(op, document);
		}
	}
	
	public static void execute(Operation<?> op, Document document) throws HumbugException, ParameterNotSetException {
		if(op.getType().equals(Document.class)) {
			executeInternal(op, document);
		} else if (op.getType().equals(Paragraph.class)) {
			
			for(Paragraph p : document.getChildElements()) {
				executeInternal(op, p);
			}
						
		} else if (op.getType().equals(Sentence.class)) {
			
			for(Paragraph p : document.getChildElements()) {
				for(Sentence s : p.getChildElements())
				{
					executeInternal(op, s);
				}
			}
			
//		} else if (op.getType().equals(Token.class)) {
//			executeInternal(op, document);
		} else if (op.getType().equals(Word.class)) {
			
			for(Paragraph p : document.getChildElements()) {
				for(Sentence s : p.getChildElements())
				{
					for(Token t : s.getChildElements()) {
						if(t instanceof Word) {
							executeInternal(op, (Word) t);
						}
					}
				}
			}
			
		} else if (op.getType().equals(Punctuation.class)) {
			
			for(Paragraph p : document.getChildElements()) {
				for(Sentence s : p.getChildElements())
				{
					for(Token t : s.getChildElements()) {
						if(t instanceof Punctuation) {
							executeInternal(op, (Punctuation) t);
						}
					}
				}
			}
			
//		} else if (op.getType().equals(Residual.class)) {
//			executeInternal(op, document);
		} else {
			throw new HumbugException("Operation type '" + op.getClass().getName() + "' "
					+ "does not reflect a member of the document model.\n"
					+ "(Operating on TextElement, Token or Residual is also not allowed.)");
		}
	}
	
	/**
	 * Suppress warnings egal, weil Typpruefung vorher durchgefuhert wurde
	 * und Methode privat
	 * @param op
	 * @param document
	 * @throws ParameterNotSetException 
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void executeInternal (Operation op, Document patiens) throws ParameterNotSetException{
		check(op);
		op.execute(patiens);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void executeInternal (Operation op, Paragraph patiens) throws ParameterNotSetException{
		check(op);
		op.execute(patiens);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void executeInternal (Operation op, Sentence patiens) throws ParameterNotSetException{
		check(op);
		op.execute(patiens);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void executeInternal (Operation op, Word patiens) throws ParameterNotSetException{
		check(op);
		op.execute(patiens);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void executeInternal (Operation op, Punctuation patiens) throws ParameterNotSetException{
		check(op);
		op.execute(patiens);
	}
	
	private static void check(Operation<?> op) throws ParameterNotSetException {
		if(!op.allParametersSet()) {
			throw new ParameterNotSetException("Some parameter values are not set in Operation '"+ op.toString() + "'.");
		}
	}
}

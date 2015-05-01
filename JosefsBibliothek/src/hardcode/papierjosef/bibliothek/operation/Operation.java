package hardcode.papierjosef.bibliothek.operation;

import java.lang.reflect.ParameterizedType;

import hardcode.papierjosef.model.document.TextElement;


public abstract class Operation<T extends TextElement> {
	
	private Class<T> type;
	
	@SuppressWarnings("unchecked")
	public Operation() {
		//Determine type of T
		type = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}
	
	public Class<T> getType() {
		return type;
	}
	
	public abstract void fuehreAus(T t);
}

package hardcode.papierjosef.bibliothek.operation;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;

//TODO welches ist besser?
//public abstract class Operation<T extends TextElement> {
public abstract class Operation<T>{

	private Class<T> type;

	@SuppressWarnings("unchecked")
	public Operation() {
		// Determine type of T
		type = ((Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	public Class<T> getType() {
		return type;
	}

	public abstract void fuehreAus(T t);

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}

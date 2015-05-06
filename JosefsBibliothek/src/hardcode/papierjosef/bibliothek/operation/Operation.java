package hardcode.papierjosef.bibliothek.operation;

import hardcode.papierjosef.bibliothek.exception.ParameterNotFoundException;
import hardcode.papierjosef.bibliothek.exception.ParameterNotSetException;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Operation als abstrakte Klasse, die Regeln und Statistiken verallgemeinert.
 * Regeln kann man einfaerben.
 * Statistiken kann man berichten.  
 *
 * @param <T>
 */
public abstract class Operation<T>{

	private Map<String, String> arguments;
	
	private Class<T> type;

	@SuppressWarnings("unchecked")
	/**
	 * Creates a new Operation. Determines type of T, this means the type,
	 * on which this Operation operates.
	 */
	public Operation() {
		type = ((Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]);
		arguments = new HashMap<>();
	}

	public Class<T> getType() {
		return type;
	}

	public abstract void execute(T t);

	
	
	public Set<String> getArguments() {
		return java.util.Collections.unmodifiableSet(arguments.keySet());
	}
	
	public boolean hasArguments() {
		return arguments.size() > 0;
	}
	
	public boolean hasArgument(String key) {
		return arguments.containsKey(key);
	}
	
	/**
	 * 
	 * @param argument
	 * @param value
	 * @throws ParameterNotSetException 
	 */
	public void setParameter(String parameter, String value) 
			throws ParameterNotFoundException, ParameterNotSetException {
		if(!arguments.containsKey(parameter)) {
			//TODO: gibt er Name der abgeleiteten Klasse oder "Operation" aus?
			throw new ParameterNotFoundException("Parameter '" + parameter + "' not applicable in Operation '" + toString() + "'.");
		} 
		
		if(value == null) {
			//TODO: gibt er Name der abgeleiteten Klasse oder "Operation" aus?
			throw new ParameterNotSetException("Parameter '" + parameter + "' not set in Operation '" + toString() + "'.");
		}
		
		arguments.put(parameter, value);
	}
	
	public String getParameterValue(String parameter) {
		return arguments.get(parameter);
	}
	
	
	/**
	 * Defines a parameter for this operation with a default value.
	 * This value can be changed by the user.
	 * @param p
	 * @param v
	 * @return
	 */
	Operation<?> arg(String p, String v) {
		arguments.put(p, v);
		return this;
	}
	
	/**
	 * Defines a parameter for this operation which must be set by the user.
	 * @param p
	 * @return
	 */
	Operation<?> arg(String p) {
		return arg(p,null);
	}
	
	/**
	 * Checks, if all parameter values are set. Fails, if a value is not set (null).
	 * @return boolean
	 */
	public boolean allParametersSet() {
		for(String key : arguments.keySet()) {
			if(arguments.get(key) == null)
				return false;
		}
		return true;
	}
	
	
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}

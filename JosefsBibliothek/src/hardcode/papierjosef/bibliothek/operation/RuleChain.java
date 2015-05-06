package hardcode.papierjosef.bibliothek.operation;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;

@SuppressWarnings("rawtypes")
//TODO rawtypes weg
public class RuleChain extends OperationChain<Regel> {

	private List<String> properties;
	
	public RuleChain(){
		super();
		this.properties = new ArrayList<String>();
	}

	/**
	 * 
	 * @param properties Properties, die wichtig sind 
	 * (und nachher eingefaerbt werden sollen)
	 */
	public void setProperties(List<String> properties) {
		this.properties = properties;
	}
	
	public List<String> getProperties() {
		return properties;
	}
	
	/**
	 * Overrides the properties of this chain.
	 * Infers the properties from all member operations.
	 */
	public void inferAllProperties() {
		this.properties = new ArrayList<String>(); 
		List<Operation<?>> ops = getOperationList();
		for(Operation<?> op : ops) {
			Regel r = (Regel) op;
			properties.add(r.getProperty());
		}
	}

}

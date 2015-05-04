package hardcode.papierjosef.bibliothek.operation;

import java.util.List;

public class RuleChain extends OperationChain<Regel> {

	List<String> properties;
	
	public RuleChain(List<String> properties){
		super();
		this.properties=properties;
	}

	public List<String> getProperties() {
		return properties;
	}

}

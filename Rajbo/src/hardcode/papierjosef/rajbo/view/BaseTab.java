package hardcode.papierjosef.rajbo.view;

import javax.swing.JPanel;

public abstract class BaseTab extends JPanel {

	private static final long serialVersionUID = -1507640661303592652L;

	private Environment e; 
	private String titleKey;
	
	public BaseTab(Environment e, String titleKey) {
		this.e = e;
		this.titleKey = titleKey;
		init();
	}
	
	String getTitleKey() {
		return titleKey;
	}
	
	Environment getEnvironment() {
		return e;
	}
	
	abstract void init();

}

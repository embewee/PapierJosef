package hardcode.papierjosef.rajbo.view;

import hardcode.papierjosef.rajbo.Environment;

import javax.swing.JPanel;

public abstract class BaseTab extends JPanel {

	private static final long serialVersionUID = -1507640661303592652L;

	private Environment e; 
	
	public BaseTab(Environment e) {
		this.e = e;
		init();
	}
	
	/**
	 * 
	 * @return String the key for the tab name. The corresponding title for the key
	 * is looked up in the i18n.
	 */
	abstract String getTitleKey();
	
	Environment getEnvironment() {
		return e;
	}
	
	abstract void init();

}

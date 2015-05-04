package hardcode.papierjosef.rajbo.view;

import hardcode.papierjosef.rajbo.Environment;

import javax.swing.JTabbedPane;

public class SideBarUI extends JTabbedPane {
	
	private Environment environment;
	
	public SideBarUI(Environment environment) {
		this.environment = environment;
	}
	
	public void insertTab(BaseTab tab) {
		this.addTab(environment.getLocaleString(tab.getTitleKey()), tab);
	}
	
	
	


	
	
}

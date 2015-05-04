package hardcode.papierjosef.rajbo.view;

import hardcode.papierjosef.rajbo.PreferencesProvider;

import javax.swing.JTabbedPane;

public class SideBarUI extends JTabbedPane {
	
	private PreferencesProvider provider;
	
	public SideBarUI(PreferencesProvider provider) {
		this.provider = provider;
	}
	
	public void insertTab(BaseTab tab) {
		this.addTab(provider.getLocaleString(tab.getTitleKey()), tab);
	}
	
	
	


	
	
}

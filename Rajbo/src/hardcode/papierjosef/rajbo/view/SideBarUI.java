package hardcode.papierjosef.rajbo.view;

import hardcode.papierjosef.rajbo.PreferencesProvider;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SideBarUI extends JTabbedPane {
	
	public void insertTab(BaseTab tab) {
		this.addTab(provider.getLocaleString(tab.getTitleKey()), tab);
	}
	
	
	
	private JPanel analyzePanel;
	private PreferencesProvider provider;
	
	JComboBox<String> comboLanguage;
	private JButton btnAnalyze;
	
	public SideBarUI(PreferencesProvider provider) {
		this.provider = provider;
	}
	
	
	
	public void insertAnalyzePanel(Vector<String> languages, ActionListener btnAnalyzeActionListener) {
		analyzePanel = new JPanel();
		analyzePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel lblLanguage = new JLabel(provider.getLocaleString("sidebar_analyze_lblLanguage"));
		analyzePanel.add(lblLanguage);
		
		comboLanguage = new JComboBox<>(languages);
		analyzePanel.add(comboLanguage);
		
		btnAnalyze = new JButton(provider.getLocaleString("sidebar_analyze_btnAnalyze"));
		btnAnalyze.addActionListener(btnAnalyzeActionListener);
		analyzePanel.add(btnAnalyze);
		
		this.addTab(provider.getLocaleString("sidebar_analyze_tabtitle"), analyzePanel);
	}
	
	public String getSelectedLanguage() {
		return (String) comboLanguage.getSelectedItem();
	}

	
	
}

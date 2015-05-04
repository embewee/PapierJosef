package hardcode.papierjosef.rajbo.view;

import hardcode.papierjosef.bibliothek.operation.Regel;
import hardcode.papierjosef.model.document.HumbugException;
import hardcode.papierjosef.rajbo.Environment;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class AnalyzeTab extends BaseTab {

	private static final long serialVersionUID = -4122758110947825524L;

	private JComboBox<Regel> comboLanguage;
	private JButton btnAnalyze;

	public AnalyzeTab(Environment e) {
		super(e);
	}

	@Override
	void init() {
		setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lblLanguage = new JLabel(getEnvironment().getLocaleString(
				"sidebar_analyze_lblLanguage"));
		add(lblLanguage);

		comboLanguage = new JComboBox(getEnvironment().getLibrary().getInternalRules());
		add(comboLanguage);

		btnAnalyze = new JButton(getEnvironment().getLocaleString(
				"sidebar_analyze_btnAnalyze"));
		btnAnalyze.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Regel rule = (Regel) comboLanguage.getSelectedItem();
				try {
					getEnvironment().getLibrary().executeOperation(rule);
				} catch (HumbugException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		add(btnAnalyze);
	}

	@Override
	String getTitleKey() {
		return "sidebar_analyze_tabtitle";
	}
}

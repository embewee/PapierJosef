package hardcode.papierjosef.rajbo.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class AnalyzeTab extends BaseTab {

	private static final long serialVersionUID = -4122758110947825524L;

	private JComboBox<String> comboLanguage;
	private JButton btnAnalyze;

	public AnalyzeTab(Environment e) {
		super(e);
	}

	@Override
	void init() {
		setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lblLanguage = new JLabel(getEnvironment().getProvider().getLocaleString("sidebar_analyze_lblLanguage"));
		add(lblLanguage);

		comboLanguage = new JComboBox<>(getEnvironment().getLanguages());
		add(comboLanguage);

		btnAnalyze = new JButton(getEnvironment().getProvider().getLocaleString("sidebar_analyze_btnAnalyze"));
		btnAnalyze.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
				System.out.println("TODO");
				
			}
		});
		add(btnAnalyze);
	}

	@Override
	String getTitleKey() {
		return "sidebar_analyze_tabtitle";
	}
}

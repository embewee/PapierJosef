package hardcode.papierjosef.rajbo.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hardcode.papierjosef.bibliothek.operation.OperationChain;
import hardcode.papierjosef.bibliothek.operation.Regel;
import hardcode.papierjosef.bibliothek.statistik.Statistik;
import hardcode.papierjosef.model.document.HumbugException;
import hardcode.papierjosef.rajbo.Environment;

public class StatisticsTab extends BaseTab {

	private JComboBox<Statistik> comboStats;
	private JButton btnExecuteStat;
	
	public StatisticsTab(Environment e) {
		super(e);
	}
	
	@Override
	void init() {
//		setLayout(new FlowLayout(FlowLayout.LEFT));
		setLayout(new GridLayout(0, 1));

		JPanel paneStats = new JPanel();
		JLabel lblLanguage = new JLabel(getEnvironment().getLocaleString(
				"sidebar_statistics_lblStatistics"));
		paneStats.add(lblLanguage);

		comboStats = new JComboBox(getEnvironment().getLibrary().getInternalStatistics());
		paneStats.add(comboStats);

		btnExecuteStat = new JButton(getEnvironment().getLocaleString(
				"sidebar_statistics_btnExecuteStatistics"));
		btnExecuteStat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Statistik stat = (Statistik) comboStats.getSelectedItem();
				try {
					getEnvironment().getLibrary().executeOperation(stat);
					getEnvironment().getLibrary().printDocument();
				} catch (HumbugException e1) {
					e1.printStackTrace();
				}
			}
		});
		paneStats.add(btnExecuteStat);
		add(paneStats);
	}
	
	

	@Override
	String getTitleKey() {
		return "sidebar_statistics_tabtitle";
	}


}

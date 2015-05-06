package hardcode.papierjosef.rajbo.view;

import hardcode.papierjosef.bibliothek.PapierJosefFacade;
import hardcode.papierjosef.bibliothek.exception.ParameterNotSetException;
import hardcode.papierjosef.bibliothek.operation.OperationChain;
import hardcode.papierjosef.bibliothek.operation.RuleChain;
import hardcode.papierjosef.bibliothek.operation.StatistikChain;
import hardcode.papierjosef.bibliothek.statistik.Report;
import hardcode.papierjosef.bibliothek.statistik.Statistik;
import hardcode.papierjosef.model.document.HumbugException;
import hardcode.papierjosef.rajbo.Environment;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;

public class StatisticsTab extends BaseTab {

	private static final long serialVersionUID = -1963925770436466994L;
	private JComboBox<Statistik<?>> comboStats;
	private JComboBox<OperationChain<?>> comboChains;
	private JButton btnExecuteStat;
	private JButton btnExecuteChain;
	private JTable reportTable;

	public StatisticsTab(Environment e) {
		super(e);
	}

	@Override
	void init() {
		setLayout(new GridLayout(0, 1));

		JPanel paneStats = new JPanel();
		JLabel lblLanguage = new JLabel(getEnvironment().getLocaleString(
				"sidebar_statistics_lblStatistics"));
		paneStats.add(lblLanguage);

		comboStats = new JComboBox<Statistik<?>>(getEnvironment().getLibrary()
				.getInternalStatistics());
		paneStats.add(comboStats);

		btnExecuteStat = new JButton(getEnvironment().getLocaleString(
				"sidebar_statistics_btnExecuteStatistics"));
		btnExecuteStat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Statistik<?> stat = (Statistik<?>) comboStats.getSelectedItem();
				executeStatistic(stat);
			}
		});
		paneStats.add(btnExecuteStat);
		add(paneStats);
		
		
		//############
		
		JPanel paneChains = new JPanel();
		JLabel lblChains = new JLabel(getEnvironment().getLocaleString(
				"sidebar_statistics_lblChains"));
		paneChains.add(lblChains);
		comboChains = new JComboBox<OperationChain<?>>(getEnvironment()
				.getLibrary().getInternalStatisticChains());
		paneChains.add(comboChains);
		btnExecuteChain = new JButton(getEnvironment().getLocaleString(
				"sidebar_statistics_btnExecuteChains"));
		
		
		btnExecuteChain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StatistikChain chain = (StatistikChain) comboChains.getSelectedItem();
				PapierJosefFacade lib = getEnvironment().getLibrary();

				try {
					lib.executeOperationChain(chain);
				} catch (HumbugException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParameterNotSetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO
				Report report = chain.getReport();
				displayResults(report);

			}
		});
		paneChains.add(btnExecuteChain);
		
		//############
		add(paneChains);
		
		
		
		
		

		reportTable = new JTable();
		add(new JScrollPane(reportTable));
	}

	private void executeStatistic(Statistik<?> stat) {
		try {
			getEnvironment().getLibrary().executeOperation(stat);
//			getEnvironment().getLibrary().printDocument();
		} catch (HumbugException e1) {
			e1.printStackTrace();
		} catch (ParameterNotSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		displayResults(stat.getReport());
	}

	private void displayResults(Report report) {
		int i = 0;

		Object[][] data = new Object[report.size()][2];
		for (String key : report.keySet()) {
			System.out.println(key + ":" + report.get(key));
			data[i][0] = key;
			data[i][1] = report.get(key);
			i++;
		}

		//TODO: columnNames ggf weg
		String[] columnNames = new String[] { "Key", "Value" };
		DefaultTableModel tm = new DefaultTableModel();
		tm.setDataVector(data, columnNames);
		reportTable.setModel(tm);
	}

	@Override
	String getTitleKey() {
		return "sidebar_statistics_tabtitle";
	}

}

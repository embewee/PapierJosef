package hardcode.papierjosef.rajbo.view;

import hardcode.papierjosef.bibliothek.PapierJosefFacade;
import hardcode.papierjosef.bibliothek.exception.ParameterNotFoundException;
import hardcode.papierjosef.bibliothek.exception.ParameterNotSetException;
import hardcode.papierjosef.bibliothek.operation.Operation;
import hardcode.papierjosef.bibliothek.operation.OperationChain;
import hardcode.papierjosef.bibliothek.operation.StatistikChain;
import hardcode.papierjosef.bibliothek.statistik.Report;
import hardcode.papierjosef.bibliothek.statistik.Statistik;
import hardcode.papierjosef.model.document.HumbugException;
import hardcode.papierjosef.rajbo.Environment;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class StatisticsTab extends BaseTab {

	private static final long serialVersionUID = -1963925770436466994L;
	private JComboBox<Statistik<?>> comboStats;
	private JComboBox<OperationChain<?>> comboChains;
	private JButton btnLoadStat;
	private JButton btnLoadChain;
	private JTable reportTable;
	private JButton btnExecute;
	private JLabel lblCurrent;
	private JPanel argsPane;

	private Object currentOperationOrChain;

	public StatisticsTab(Environment e) {
		super(e);
	}

	@Override
	void init() {
		currentOperationOrChain = null;
		// ##########

		setLayout(new GridLayout(0, 1));

		JPanel paneStats = new JPanel();
		JLabel lblLanguage = new JLabel(getEnvironment().getLocaleString(
				"sidebar_statistics_lblStatistics"));
		paneStats.add(lblLanguage);

		comboStats = new JComboBox<Statistik<?>>(getEnvironment().getLibrary()
				.getInternalStatistics());
		paneStats.add(comboStats);

		btnLoadStat = new JButton(getEnvironment().getLocaleString(
				"sidebar_statistics_btnExecuteStatistics"));
		btnLoadStat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Statistik<?> stat = (Statistik<?>) comboStats.getSelectedItem();
				setCurrentOperation(stat);
			}
		});
		paneStats.add(btnLoadStat);
		add(paneStats);

		// ############

		argsPane = new JPanel();
		argsPane.setLayout(new BoxLayout(argsPane, BoxLayout.PAGE_AXIS));
		argsPane.add(new JLabel("Operation's /Operation chain's arguments:"));
		add(argsPane);
		
		

		// ############

		JPanel paneChains = new JPanel();
		JLabel lblChains = new JLabel(getEnvironment().getLocaleString(
				"sidebar_statistics_lblChains"));
		paneChains.add(lblChains);
		comboChains = new JComboBox<OperationChain<?>>(getEnvironment()
				.getLibrary().getInternalStatisticChains());
		paneChains.add(comboChains);
		btnLoadChain = new JButton(getEnvironment().getLocaleString(
				"sidebar_statistics_btnExecuteChains"));

		btnLoadChain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StatistikChain chain = (StatistikChain) comboChains
						.getSelectedItem();
				setCurrentChain(chain);
			}
		});
		paneChains.add(btnLoadChain);
		add(paneChains);

		// ############

		JPanel executePane = new JPanel();
		btnExecute = new JButton(getEnvironment().getLocaleString(
				"sidebar_statistics_btnExecute"));
		btnExecute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					executeCurrentOperationOrChain();
				} catch (HumbugException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParameterNotSetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		lblCurrent = new JLabel("Select or load a rule or chain.");
		executePane.add(lblCurrent);
		executePane.add(btnExecute);
		add(executePane);

		// ###############

		add(new JLabel("Report:"));
		reportTable = new JTable();
		add(new JScrollPane(reportTable));
	}

	private void executeCurrentOperationOrChain() throws HumbugException,
			ParameterNotSetException {
		if (currentOperationOrChain instanceof Statistik<?>) {
			executeStatistic((Statistik<?>) currentOperationOrChain);
		} else if (currentOperationOrChain instanceof StatistikChain) {
			executeChain((StatistikChain) currentOperationOrChain);
		}
	}

	private void executeChain(StatistikChain chain) throws HumbugException,
			ParameterNotSetException {
		PapierJosefFacade lib = getEnvironment().getLibrary();
		lib.executeOperationChain(chain);
		Report report = chain.getReport();
		displayResults(report);
	}

	private void setCurrentOperation(Operation<?> op) {
		lblCurrent.setText(op.toString());
		currentOperationOrChain = op;
		displayOperationArguments(op);
	}

	private void setCurrentChain(OperationChain<?> chain) {
		lblCurrent.setText(chain.getName());
		currentOperationOrChain = chain;
	}

	/**
	 * FIXME: Chains bieten keine Unterstuetzung fuer Argumente. Siehe Issue!
	 * 
	 * @param op
	 */
	private void displayOperationArguments(final Operation<?> op) {
		JPanel panel = new JPanel(new GridLayout(0, 1));
		argsPane.add(new JScrollPane(panel));
		argsPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		Set<String> args = op.getArguments();

		Map<String, JTextField> fields = new HashMap<>();
		for (final String key : args) {
			JLabel labl = new JLabel(key + ":");
			fields.put(key, new JTextField(1));
			JTextField f1 = fields.get(key);
			f1.setText(op.getParameterValue(key));
			f1.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent arg0) {}

				public void focusLost(FocusEvent arg0) {
					try {
						op.setParameter(key,
								((JTextField) arg0.getSource()).getText());
						System.out.println(op.getParameterValue(key));
					} catch (ParameterNotFoundException e) {
						e.printStackTrace();
					} catch (ParameterNotSetException e) {
						e.printStackTrace();
					}
				}
			});
			panel.add(labl);
			panel.add(f1);
		}
	}

	private void executeStatistic(Statistik<?> stat) throws HumbugException,
			ParameterNotSetException {
		getEnvironment().getLibrary().executeOperation(stat);
		displayResults(stat.getReport());
	}

	private void displayResults(Report report) {
		int i = 0;

		Object[][] data = new Object[report.size()][2];
		for (String key : report.keySet()) {
			// System.out.println(key + ":" + report.get(key));
			data[i][0] = key;
			data[i][1] = report.get(key);
			i++;
		}

		// TODO: columnNames ggf weg
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

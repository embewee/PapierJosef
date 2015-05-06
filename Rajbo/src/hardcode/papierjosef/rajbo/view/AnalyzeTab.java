package hardcode.papierjosef.rajbo.view;

import hardcode.papierjosef.bibliothek.PapierJosefFacade;
import hardcode.papierjosef.bibliothek.exception.LibraryException;
import hardcode.papierjosef.bibliothek.exception.ParameterNotFoundException;
import hardcode.papierjosef.bibliothek.exception.ParameterNotSetException;
import hardcode.papierjosef.bibliothek.operation.Operation;
import hardcode.papierjosef.bibliothek.operation.OperationChain;
import hardcode.papierjosef.bibliothek.operation.Regel;
import hardcode.papierjosef.bibliothek.operation.RuleChain;
import hardcode.papierjosef.model.document.HumbugException;
import hardcode.papierjosef.rajbo.Environment;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

public class AnalyzeTab extends BaseTab {

	private static final long serialVersionUID = -4122758110947825524L;

	private JComboBox<Regel<?>> comboRules;
	private JButton btnLoadRule;

	private JComboBox<OperationChain<?>> comboChains;
	private JButton btnLoadChain;

	private DefaultListModel<Operation<?>> historyModel;
	
	private JButton btnExecute;
	private JLabel lblCurrent;
	
	private JPanel argsPane;
	
	private Object currentRuleOrChain;
	

	public AnalyzeTab(Environment e) {
		super(e);
	}

	// TODO: comboRules mal typsichern!!
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	void init() {
		currentRuleOrChain = null;
		
		//########
		
		setLayout(new GridLayout(0, 1));

		JPanel paneRules = new JPanel();
		JLabel lblLanguage = new JLabel(getEnvironment().getLocaleString(
				"sidebar_analyze_lblRules"));
		paneRules.add(lblLanguage);

		comboRules = new JComboBox(getEnvironment().getLibrary()
				.getInternalRules());
		paneRules.add(comboRules);

		btnLoadRule = new JButton(getEnvironment().getLocaleString(
				"sidebar_analyze_btnAnalyzeRules"));
		
		btnLoadRule.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Regel rule = (Regel) comboRules.getSelectedItem();
				setCurrentRule(rule);
			}
		});
		
		
		paneRules.add(btnLoadRule);
		
		argsPane = new JPanel();
		argsPane.setLayout(new BoxLayout(argsPane, BoxLayout.PAGE_AXIS));
		argsPane.add(new JLabel("Operation's /Operation chain's arguments:"));

		btnExecute = new JButton(getEnvironment().getLocaleString(
				"sidebar_statistics_btnExecute"));

		JPanel paneChains = new JPanel();
		JLabel lblChains = new JLabel(getEnvironment().getLocaleString(
				"sidebar_analyze_lblChains"));
		paneChains.add(lblChains);
		comboChains = new JComboBox<OperationChain<?>>(getEnvironment()
				.getLibrary().getInternalRuleChains());
		paneChains.add(comboChains);
		btnLoadChain = new JButton(getEnvironment().getLocaleString(
				"sidebar_analyze_btnAnalyzeChains"));
		
		
		btnLoadChain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleChain chain = (RuleChain) comboChains.getSelectedItem();
				setCurrentRuleChain(chain);
			}
		});
		paneChains.add(btnLoadChain);
		
		
		JPanel executePane = new JPanel();
		lblCurrent = new JLabel("Select or load a rule or chain.");
		executePane.add(lblCurrent);
		btnExecute = new JButton(getEnvironment().getLocaleString(
				"sidebar_analyze_btnExecute"));
		btnExecute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				executeCurrentRuleOrChain();
			}
		});
		executePane.add(btnExecute);
		
		
		

		JPanel externalPane = new JPanel();
		JButton btnLoadExternal = new JButton(getEnvironment().getLocaleString(
				"sidebar_analyze_btnLoadExternal"));
		btnLoadExternal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadExternalRule();
			}
		});

		externalPane.add(btnLoadExternal);

		historyModel = new DefaultListModel<Operation<?>>();
		JList<Operation<?>> history = new JList<Operation<?>>(historyModel);
		history.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				JList<Operation> list = (JList<Operation>) ev.getSource();
				if (ev.getClickCount() == 2) {
					int index = list.locationToIndex(ev.getPoint());
					Regel op = (Regel) historyModel.get(index);
					try {
						getEnvironment()
								.getWindow()
								.getTextUI()
								.clearHighlight(
										getEnvironment().getLibrary()
												.getDocument()
												.getChildElements(),
										op.getProperty(), op.getType());
					} catch (BadLocationException e) {
						e.printStackTrace();
					}
					historyModel.remove(index);
				}
			}
		});

		add(paneRules);
		add(argsPane);
		add(paneChains);
		add(externalPane);
		add(executePane);
		add(new JScrollPane(history));
	}

	//########
	
	private void setCurrentRule(Regel<?> rule) {
		currentRuleOrChain = rule;
		lblCurrent.setText(rule.toString());
		displayOperationArguments(rule);
	}
	
	private void setCurrentRuleChain(RuleChain chain) {
		lblCurrent.setText(chain.getName());
		currentRuleOrChain = chain;
	}
	
	
	private void executeCurrentRuleOrChain() {
		if(currentRuleOrChain instanceof Regel<?>) {
			executeRule((Regel<?>)currentRuleOrChain);
		} else if (currentRuleOrChain instanceof RuleChain) {
			executeChain((RuleChain) currentRuleOrChain);
		}
	}

	private void executeRule(Regel<?> rule) {
		TextUI ui = getEnvironment().getWindow().getTextUI();
		PapierJosefFacade lib = getEnvironment().getLibrary();
		
		try {
			lib.executeOperation(rule);
			historyModel.add(0, rule);
			ui.colorize(lib.getDocument().getChildElements(),
					rule.getProperty(), rule.getType(), ui.nextColor());
//			lib.printDocument();
		} catch (HumbugException e1) {
			e1.printStackTrace();
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		} catch (ParameterNotSetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void executeChain(RuleChain chain) {
		TextUI ui = getEnvironment().getWindow().getTextUI();
		PapierJosefFacade lib = getEnvironment().getLibrary();
		
		try {
			lib.executeOperationChain(chain);
			// TODO FIXME
			// historyModel.add(0, chain);
			for (String property : chain.getProperties()) {
				ui.colorizeAllLevels(lib.getDocument()
						.getChildElements(), property, ui.nextColor());
			}
//			lib.printDocument();
		} catch (HumbugException e1) {
			e1.printStackTrace();
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		} catch (ParameterNotSetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
	
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
	
	
	
	//########
	
	private void loadExternalRule() {
		File file = chooseFile();
		Regel<?> op = null;
		PapierJosefFacade lib=getEnvironment().getLibrary();
		try {
			op = lib.loadRuleFromFile(file);
		} catch (LibraryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setCurrentRule(op);

//				lib.executeOperation(op);
//				historyModel.add(0, op);
	}

	private File chooseFile() {
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) { // clicked OK
			File file = fc.getSelectedFile();
			if (!file.isFile()) {
				JOptionPane.showMessageDialog(this, getEnvironment()
						.getLocaleString("err_wrongselection"),
						getEnvironment().getLocaleString("err_title"),
						JOptionPane.ERROR_MESSAGE);
				return null;
			}
			return file;
		}
		return null;
	}

	@Override
	String getTitleKey() {
		return "sidebar_analyze_tabtitle";
	}
}

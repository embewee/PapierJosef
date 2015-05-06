package hardcode.papierjosef.rajbo.view;

import hardcode.papierjosef.bibliothek.PapierJosefFacade;
import hardcode.papierjosef.bibliothek.exception.LibraryException;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;

public class AnalyzeTab extends BaseTab {

	private static final long serialVersionUID = -4122758110947825524L;

	private JComboBox<Regel<?>> comboRules;
	private JButton btnLoadRule;

	private JComboBox<OperationChain<?>> comboChains;
	private JButton btnLoadChain;

	private DefaultListModel<Operation<?>> historyModel;
	
	private JButton btnExecute;
	
	
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
		
		
		btnExecute = new JButton(getEnvironment().getLocaleString(
				"sidebar_analyze_btnExecute"));
		btnExecute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				executeCurrentRuleOrChain();
			}
		});

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
		add(paneChains);
		add(externalPane);
		add(btnExecute);
		add(new JScrollPane(history));
	}

	//########
	
	private void setCurrentRule(Regel<?> rule) {
		currentRuleOrChain = rule;
		displayOperationArguments(rule);
	}
	
	private void setCurrentRuleChain(RuleChain chain) {
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
	
	
	
	
	private void displayOperationArguments(Operation<?> op) {
		
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

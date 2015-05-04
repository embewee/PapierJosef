package hardcode.papierjosef.rajbo.view;

import hardcode.papierjosef.bibliothek.exception.LibraryException;
import hardcode.papierjosef.bibliothek.operation.Operation;
import hardcode.papierjosef.bibliothek.operation.OperationChain;
import hardcode.papierjosef.bibliothek.operation.Regel;
import hardcode.papierjosef.bibliothek.operation.RuleChain;
import hardcode.papierjosef.model.document.HumbugException;
import hardcode.papierjosef.rajbo.Environment;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

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

	private JComboBox<Regel> comboRules;
	private JButton btnExecuteRule;

	private JComboBox<OperationChain> comboChains;
	private JButton btnExecuteChain;

	private DefaultListModel<Operation> historyModel;

	public AnalyzeTab(Environment e) {
		super(e);
	}

	@Override
	void init() {
		// setLayout(new FlowLayout(FlowLayout.LEFT));
		setLayout(new GridLayout(0, 1));

		JPanel paneRules = new JPanel();
		JLabel lblLanguage = new JLabel(getEnvironment().getLocaleString(
				"sidebar_analyze_lblRules"));
		paneRules.add(lblLanguage);

		comboRules = new JComboBox(getEnvironment().getLibrary()
				.getInternalRules());
		paneRules.add(comboRules);

		btnExecuteRule = new JButton(getEnvironment().getLocaleString(
				"sidebar_analyze_btnAnalyzeRules"));
		btnExecuteRule.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Regel rule = (Regel) comboRules.getSelectedItem();
				try {
					getEnvironment().getLibrary().executeOperation(rule);
					historyModel.add(0, rule);
					getEnvironment()
							.getWindow()
							.getTextUI()
							.colorize(
									getEnvironment().getLibrary().getDocument()
											.getChildElements(),
									rule.getProperty(), rule.getType(),
									Color.ORANGE);
					getEnvironment().getLibrary().printDocument();
				} catch (HumbugException e1) {
					e1.printStackTrace();
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}
		});
		paneRules.add(btnExecuteRule);

		JPanel paneChains = new JPanel();
		JLabel lblChains = new JLabel(getEnvironment().getLocaleString(
				"sidebar_analyze_lblChains"));
		paneChains.add(lblChains);
		comboChains = new JComboBox<OperationChain>(getEnvironment()
				.getLibrary().getInternalRuleChains());
		paneChains.add(comboChains);
		btnExecuteChain = new JButton(getEnvironment().getLocaleString(
				"sidebar_analyze_btnAnalyzeChains"));
		btnExecuteChain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RuleChain chain = (RuleChain) comboChains.getSelectedItem();
				try {
					getEnvironment().getLibrary().executeOperationChain(chain);
					// TODO FIXME
					// historyModel.add(0, chain);
					for (String property : chain.getProperties()) {
						getEnvironment()
								.getWindow()
								.getTextUI()
								.colorizeAllLevels(
										getEnvironment().getLibrary()
												.getDocument()
												.getChildElements(), property,
										Color.ORANGE);
					}
					getEnvironment().getLibrary().printDocument();
				} catch (HumbugException e1) {
					e1.printStackTrace();
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}
		});
		paneChains.add(btnExecuteChain);

		JPanel externalPane = new JPanel();
		JButton btnLoadExternal = new JButton(getEnvironment().getLocaleString(
				"sidebar_analyze_btnLoadExternal"));
		btnLoadExternal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadAndExecuteExternalRule();
			}
		});

		externalPane.add(btnLoadExternal);

		historyModel = new DefaultListModel<Operation>();
		JList<Operation> history = new JList<Operation>(historyModel);
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
										getEnvironment().getLibrary().getDocument()
												.getChildElements(),
										op.getProperty(), op.getType());
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					historyModel.remove(index);
				}
			}
		});

		add(paneRules);
		add(paneChains);
		add(externalPane);
		add(new JScrollPane(history));
	}

	private void loadAndExecuteExternalRule() {
		File file = chooseFile();
		Operation<?> op = null;
		try {
			op = getEnvironment().getLibrary().loadOperationFromFile(file);
		} catch (LibraryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int res = JOptionPane.showConfirmDialog(this, "Execute rule '" + op
				+ "'");
		if (res == JOptionPane.YES_OPTION) {
			try {
				getEnvironment().getLibrary().executeOperation(op);
				historyModel.add(0, op);
				getEnvironment().getLibrary().printDocument();
			} catch (HumbugException e) {
				e.printStackTrace();
			}
		}
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

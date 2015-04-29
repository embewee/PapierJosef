package hardcode.papierjosef.rajbo.view;

import hardcode.papierjosef.rajbo.PreferencesProvider;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class OperationViewer extends JDialog {
	public final static String FILE_EXTENSION = ".class";
	
	private PreferencesProvider provider;
	private String result;
	private JDialog me;
	private DefaultListModel<String> listModel;
	private JPanel infoPanel;
	
	public OperationViewer(JFrame owner, final PreferencesProvider provider) {
		super(owner);
		this.provider = provider;
		this.me = this;
		result = null;
		this.setTitle(provider.getLocaleString("operationsviewer_title"));
		
		BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);

		JPanel topPanel = new JPanel();
		JCheckBox chkOperations = new JCheckBox("Operations");
		topPanel.add(chkOperations);
		JCheckBox chkChains = new JCheckBox("Chains");
		topPanel.add(chkChains);
		JCheckBox chkCollections = new JCheckBox("Collections");
		topPanel.add(chkCollections);
		getContentPane().add(topPanel, BorderLayout.PAGE_START);
		
		listModel = new DefaultListModel<>();
		JList<String> listView = new JList<>(listModel);
		listView.setVisibleRowCount(1);
		listView.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				showInfo(listModel.get(e.getFirstIndex()));
			}
		});
		JScrollPane listScrollPane = new JScrollPane(listView);
		
		infoPanel = new JPanel();
		BoxLayout infoPanelLayout = new BoxLayout(infoPanel, BoxLayout.Y_AXIS); 
		infoPanel.setLayout(infoPanelLayout);
		JLabel lblInfoName = new JLabel("Name");
		infoPanel.add(lblInfoName);
		JLabel lblInfoDescription = new JLabel("Description");
		infoPanel.add(lblInfoDescription);
		JLabel lblInfoAuthor = new JLabel("Author");
		infoPanel.add(lblInfoAuthor);
		JLabel lblVersion = new JLabel("Version");
		infoPanel.add(lblVersion);
				
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, infoPanel);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		FlowLayout bottomPanleLayout= new FlowLayout(FlowLayout.RIGHT);
		bottomPanel.setLayout(bottomPanleLayout);
		
		JButton btnChooseDirectory = new JButton("choose dir"); //TODO
		btnChooseDirectory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);	
				int returnVal = fc.showOpenDialog(me);
				if(returnVal == JFileChooser.APPROVE_OPTION) { //clicked OK
					File dir = fc.getSelectedFile();
					if(!dir.isDirectory()) {
						JOptionPane.showMessageDialog(me, "No directory selected.", provider.getLocaleString("operationsviewer_title"), JOptionPane.ERROR_MESSAGE);
						return;
					}
					changeDirectory(dir);
				}
			}
		});
		bottomPanel.add(btnChooseDirectory);
		
		JButton btnCancel = new JButton("Cancel"); //TODO
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				result = null;
				dispose();
			}
		});
		bottomPanel.add(btnCancel);
		
		JButton btnOK = new JButton("OK"); //TODO
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		bottomPanel.add(btnOK);
		
		getContentPane().add(bottomPanel, BorderLayout.PAGE_END);
		
		setSize(500, 400);
		setLocationRelativeTo(owner);
	}
	
	private void changeDirectory(File directory) {
		listModel.clear();
		for (File file : directory.listFiles()) {
			String fileName = file.getName();
			if(fileName.toLowerCase().endsWith(FILE_EXTENSION)) {
				String operationName = fileName.substring(0, fileName.length() - FILE_EXTENSION.length());
				listModel.addElement(operationName);
			}
		}
	}
	
	private void showInfo(String fileName) {
		System.out.println(fileName);
	}
	
	public void open(boolean modal) {
		setModal(modal);
		setVisible(true);
	}
	
	public String selectOperation(boolean modal) {
		setModal(modal);
		setVisible(true);
		//TODO
		return result;
	}
	
	public String selectOperationChain(boolean modal) {
		setModal(modal);
		setVisible(true);
		//TODO
		return result;
	}
	
	public String selectOperationCollection(boolean modal) {
		setModal(modal);
		setVisible(true);
		//TODO
		return result;
	}
}

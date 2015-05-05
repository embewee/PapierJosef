package hardcode.papierjosef.rajbo.view;

import hardcode.papierjosef.bibliothek.PapierJosefFacade;
import hardcode.papierjosef.bibliothek.exception.LibraryException;
import hardcode.papierjosef.model.document.Paragraph;
import hardcode.papierjosef.model.document.Sentence;
import hardcode.papierjosef.model.document.Word;
import hardcode.papierjosef.rajbo.Environment;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.naming.OperationNotSupportedException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.text.BadLocationException;

public class LoadTab extends BaseTab implements PropertyChangeListener {

	private static final long serialVersionUID = 5971000257848302039L;

	private ProgressDialog pd;

	public LoadTab(Environment e) {
		super(e);
	}

	@Override
	void init() {
		setLayout(new FlowLayout(FlowLayout.LEADING));
		JButton btnLoad = new JButton(getEnvironment().getLocaleString(
				"menuitem_load"));
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadAndPrepareDocument(chooseFile());
			}
		});
		add(btnLoad);

		JButton btnColor = new JButton(getEnvironment().getLocaleString(
				"menuitem_color"));
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorVAFIN();
			}
		});
		add(btnColor);
	}

	private void colorVAFIN() {
		TextUI ui = getEnvironment().getWindow().getTextUI();
		for (Paragraph p : getEnvironment().getLibrary().getDocument()
				.getChildElements()) {
			for (Sentence s : p.getChildElements()) {
				for (Word w : s.getWordsOnly()) {
					if (w.getPartOfSpeech().getPartOfSpeechName()
							.equals("VAFIN"))
						try {
							ui.highlight(Color.ORANGE, (int) w.getStart(),
									(int) w.getEnd() - 1);
						} catch (BadLocationException e) {
							e.printStackTrace();
						}
				}
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
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("EVNT:" + evt.getPropertyName());

		if (evt.getNewValue().equals(SwingWorker.StateValue.STARTED))
			pd = new ProgressDialog();
		else if (evt.getNewValue().equals(SwingWorker.StateValue.DONE)) {
			pd.kill();
			PapierJosefFacade lib = getEnvironment().getLibrary();
			System.out.println(lib);
			JOptionPane.showMessageDialog(this, "Datei erfolgreich geladen",
					"Erfolg", JOptionPane.INFORMATION_MESSAGE);
			getEnvironment().getWindow().setTextUIText(
					lib.getDocument().getText());
		}
	}

	private void loadAndPrepareDocument(File file) {
		if (file == null)
			return;

		class SwingWorkerNew extends SwingWorker<Void, Void> {
			File f;
			PapierJosefFacade lib;
			JPanel p;

			public SwingWorkerNew(File f, PapierJosefFacade lib, JPanel p) {
				this.f = f;
				this.lib = lib;
				this.p = p;
			}

			@Override
			protected Void doInBackground() {
				try {
					lib.readDocument(f);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(p, e1.getMessage(),
							getEnvironment().getLocaleString("err_title"),
							JOptionPane.ERROR_MESSAGE);
				} catch (LibraryException e1) {
					JOptionPane.showMessageDialog(p, e1.getMessage(),
							getEnvironment().getLocaleString("err_title"),
							JOptionPane.ERROR_MESSAGE);
				} catch (OperationNotSupportedException e) {
					JOptionPane.showMessageDialog(p, getEnvironment()
							.getLocaleString("err_unknown_filetype"),
							getEnvironment().getLocaleString("err_title"),
							JOptionPane.ERROR_MESSAGE);
				}
				return null;
			}
		}

		SwingWorkerNew swn = new SwingWorkerNew(file, getEnvironment()
				.getLibrary(), this);
		swn.addPropertyChangeListener(this);
		swn.execute();

	}

	@Override
	String getTitleKey() {
		return "sidebar_load_tabtitle";
	}
}
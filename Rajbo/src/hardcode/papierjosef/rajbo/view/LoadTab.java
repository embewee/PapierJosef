package hardcode.papierjosef.rajbo.view;

import hardcode.papierjosef.bibliothek.PapierJosefFacade;
import hardcode.papierjosef.bibliothek.exception.LibraryException;
import hardcode.papierjosef.rajbo.Environment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.naming.OperationNotSupportedException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class LoadTab extends BaseTab {

	private static final long serialVersionUID = 5971000257848302039L;

	public LoadTab(Environment e) {
		super(e);
	}

	@Override
	void init() {
		JButton btnLoad = new JButton(getEnvironment().getLocaleString(
				"menuitem_load"));
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadAndPrepareDocument(chooseFile());
			}
		});
		add(btnLoad);

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

	private void loadAndPrepareDocument(File file) {
		// TODO
		try {
			PapierJosefFacade lib = getEnvironment().getLibrary();
			lib.readDocument(file);
			JOptionPane.showMessageDialog(this, "Datei erfolgreich geladen",
					"Erfolg", JOptionPane.INFORMATION_MESSAGE);
			getEnvironment().getWindow().setTextUIText(
					lib.getDocument().getText());
			// lib.getDocument();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(),
					getEnvironment().getLocaleString("err_title"),
					JOptionPane.ERROR_MESSAGE);
		} catch (LibraryException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(),
					getEnvironment().getLocaleString("err_title"),
					JOptionPane.ERROR_MESSAGE);
		} catch (OperationNotSupportedException e) {
			JOptionPane.showMessageDialog(this, getEnvironment()
					.getLocaleString("err_unknown_filetype"), getEnvironment()
					.getLocaleString("err_title"), JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	String getTitleKey() {
		return "sidebar_load_tabtitle";
	}
}

package hardcode.papierjosef.rajbo.view;

import hardcode.papierjosef.bibliothek.PapierJosefFacade;
import hardcode.papierjosef.bibliothek.exception.LibraryException;
import hardcode.papierjosef.rajbo.PreferencesProvider;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class LoadTab extends BaseTab {

	private static final long serialVersionUID = 5971000257848302039L;

	public LoadTab(Environment e, String titleKey) {
		super(e, titleKey);
	}

	@Override
	void init() {

		PreferencesProvider provider = getEnvironment().getProvider();

		/*
		 * Laden und Vorverarbeiten
		 */

		// Laden
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) { // clicked OK
			File file = fc.getSelectedFile();
			if (!file.isFile()) {
				JOptionPane.showMessageDialog(this,
						provider.getLocaleString("err_wrongselection"),
						provider.getLocaleString("err_title"),
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			// TODO
			try {
				PapierJosefFacade lib = getEnvironment().getLibrary();
				lib.readDocument(file);
				lib.setFilter(null);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(),
						provider.getLocaleString("err_title"),
						JOptionPane.ERROR_MESSAGE);
			} catch (LibraryException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(),
						provider.getLocaleString("err_title"),
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}
}

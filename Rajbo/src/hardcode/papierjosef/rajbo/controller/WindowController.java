package hardcode.papierjosef.rajbo.controller;

import hardcode.papierjosef.bibliothek.PapierJosefFacade;
import hardcode.papierjosef.bibliothek.exception.LibraryException;
import hardcode.papierjosef.rajbo.PreferencesProvider;
import hardcode.papierjosef.rajbo.view.OperationViewer;
import hardcode.papierjosef.rajbo.view.Window;
import hardcode.preferences.PreferencesController;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

public class WindowController {
	private PreferencesProvider provider;
	private PapierJosefFacade library;
	private Window window;
	
	public WindowController(PreferencesProvider provider, String windowTitle, PapierJosefFacade library) {
		this.library = library;
		this.provider = provider;
		
		window = new Window(provider);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle(windowTitle);		
		
		setStandardActionListeners();
		
		insterSideBarAnalyzeTab();
		insertSideBarStatisticsTab();
		insertSideBarRuleTab();
		insertSideBarOperationsTab();

		//TODO: weg
		JPanel testPanel = new JPanel();
		JButton btn1 = new JButton("Färben");
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					window.getTextUI().highlight(Color.RED, 0, 5);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		testPanel.add(btn1);
		window.getSideBarUI().addTab("TEST", testPanel);
	}

	private void setStandardActionListeners() {
		window.setMenuItemLoadActionListener(new MenuItemLoadActionListener());
		window.setMenuItemSaveActionListener(new MenuItemSaveActionListener());
		window.setMenuItemPreferencesActionListener(new MenuItemPreferencesActionListener());
		window.setMenuItemExitActionListener(new MenuItemExitActionListener());
		window.setMenuItemOperationsActionListener(new MenuItemOperationsActionListener());
	}
	
	private void insterSideBarAnalyzeTab() {
		Vector<String> languages = new Vector<>();
		
		//TODO: collect languages
		languages.add("Deutsch");
		
		window.getSideBarUI().insertAnalyzePanel(languages, new BtnAnalyzeListener());
	}
	
	private void insertSideBarRuleTab() {
		// TODO Auto-generated method stub
		
	}

	private void insertSideBarStatisticsTab() {
		// TODO Auto-generated method stub
		
	}

	private void insertSideBarOperationsTab() {
		// TODO Auto-generated method stub
		
	}

	public void show(int width, int  height) {
		window.setSize(width, height);
		window.setVisible(true);
	}
	
	private class MenuItemLoadActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			final JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);	
			int returnVal = fc.showOpenDialog(window);
			if(returnVal == JFileChooser.APPROVE_OPTION) { //clicked OK
				File file = fc.getSelectedFile();
				if(!file.isFile()) {
					JOptionPane.showMessageDialog(window, provider.getLocaleString("err_wrongselection"), window.getTitle(), JOptionPane.ERROR_MESSAGE);
					return;
				}				
				//TODO
				try {
					library.readDocument(file);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(window, e1.getMessage(), window.getTitle(), JOptionPane.ERROR_MESSAGE);
				} catch (LibraryException e1) {
					JOptionPane.showMessageDialog(window, e1.getMessage(), window.getTitle(), JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	private class MenuItemSaveActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO
		}
	}
	
	private class MenuItemPreferencesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				new PreferencesController(window.getTitle(), provider.getPropertiesDirectory());
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(window, ex.getMessage(), window.getTitle(), JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class MenuItemExitActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			window.dispose();
			System.exit(0);
		}
	}
	
	private class MenuItemOperationsActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			OperationViewer viewer = new OperationViewer(window, provider);
			viewer.open(true);
		}
	}
	
	private class BtnAnalyzeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO
			String language = window.getSideBarUI().getSelectedLanguage(); //zB "Deutsch"
//			library.analyze();
			System.out.println("ASDF ASDF");
		}
	}
	
	private int safeLongToInt(long l) {
	    if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
	        throw new IllegalArgumentException (l + " cannot be cast to int without changing its value.");
	    }
	    return (int) l;
	}
}

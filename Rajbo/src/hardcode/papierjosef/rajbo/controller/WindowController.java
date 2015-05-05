package hardcode.papierjosef.rajbo.controller;

import hardcode.papierjosef.rajbo.Environment;
import hardcode.papierjosef.rajbo.view.AnalyzeTab;
import hardcode.papierjosef.rajbo.view.LoadTab;
import hardcode.papierjosef.rajbo.view.StatisticsTab;
import hardcode.papierjosef.rajbo.view.Window;
import hardcode.preferences.PreferencesController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("unused")
// TODO Was ist mit den ganzen ActionListeners - werden die gebraucht?
public class WindowController {
	private Environment environment;
	private Window window;

	public WindowController(Environment environment, String windowTitle) {
		this.environment = environment;

		window = new Window(environment);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle(windowTitle);

		window.getSideBarUI().insertTab(new LoadTab(environment));
		window.getSideBarUI().insertTab(new AnalyzeTab(environment));
		window.getSideBarUI().insertTab(new StatisticsTab(environment));
	}

	public void show(int width, int height) {
		window.setSize(width, height);
		window.setVisible(true);
	}

	private class MenuItemPreferencesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				new PreferencesController(window.getTitle(),
						environment.getPropertiesDirectory());
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(window, ex.getMessage(),
						window.getTitle(), JOptionPane.ERROR_MESSAGE);
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
			// OperationViewer viewer = new OperationViewer(window,
			// environment);
			// viewer.open(true);
		}
	}

	// #FIXME
	// Ist das hier vernünftig??
	public Window getWindow() {
		return window;
	}
}

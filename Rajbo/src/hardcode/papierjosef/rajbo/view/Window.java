package hardcode.papierjosef.rajbo.view;

import hardcode.papierjosef.rajbo.Application;
import hardcode.papierjosef.rajbo.Environment;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class Window extends JFrame {
	
	private JFrame me;
	private JMenuBar menuBar;
	private Environment environment;
	
	private TextUI textUI;
	private SideBarUI sideBarUI;	
		
	private JMenuItem menuItemLoad;
	private JMenuItem menuItemSave;
	private JMenuItem menuItemPreferences;
	private JMenuItem menuItemExit;
	private JMenuItem menuItemOperations;
	
	public Window(Environment environment) {
		me = this;
		this.environment = environment;		
		BorderLayout borderLayoutManager = new BorderLayout();
		setLayout(borderLayoutManager);
		insertMenu();
		insertUIElements();
	}
	
	@Override
	public void setVisible(boolean b) {
		setLocationRelativeTo(null);
		super.setVisible(b);
	}
	
	public void insertUIElements() {
		textUI = new TextUI();
		sideBarUI = new SideBarUI(environment);
		JScrollPane textAreaScroll = new JScrollPane(textUI, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, textAreaScroll, sideBarUI);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(0.25);
		Dimension minimumSize = new Dimension(100, 50);
		textAreaScroll.setMinimumSize(minimumSize);
		sideBarUI.setMinimumSize(minimumSize);
		getContentPane().add(splitPane, BorderLayout.CENTER);
	}

	public TextUI getTextUI() {
		return textUI;
	}
	
	public SideBarUI getSideBarUI() {
		return sideBarUI;
	}

	private void insertMenu() {
		menuBar = new JMenuBar();
		
		JMenu appMenu = new JMenu(environment.getLocaleString("menu_application"));
		menuBar.add(appMenu);

		menuItemLoad = new JMenuItem(environment.getLocaleString("menuitem_load"));
		appMenu.add(menuItemLoad);
		
		menuItemSave = new JMenuItem(environment.getLocaleString("menuitem_save"));
		appMenu.add(menuItemSave);
		
		appMenu.addSeparator();
		
		menuItemPreferences = new JMenuItem(environment.getLocaleString("menuitem_preferences"));
		appMenu.add(menuItemPreferences);
		
		appMenu.addSeparator();
		
		menuItemExit = new JMenuItem(environment.getLocaleString("menuitem_exit"));
		appMenu.add(menuItemExit);
		
		JMenu operationsMenu = new JMenu(environment.getLocaleString("menu_operations"));
		menuBar.add(operationsMenu);
		
		menuItemOperations = new JMenuItem(environment.getLocaleString("menuitem_operations"));
		operationsMenu.add(menuItemOperations);
		
		menuBar.add(Box.createHorizontalGlue());
		
		JMenu helpMenu = new JMenu("?");
		menuBar.add(helpMenu);

		JMenuItem helpItem = new JMenuItem(environment.getLocaleString("menuitem_help"));
		helpItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					URL url = new URL(Application.HARDCODE_HELPPAGE);
					URI uri = url.toURI();
					java.awt.Desktop.getDesktop().browse(uri);
				} catch (MalformedURLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (URISyntaxException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		helpMenu.add(helpItem);
		
		JMenuItem aboutItem = new JMenuItem(environment.getLocaleString("menuitem_about"));
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(me, Application.APP_ABOUT, Application.APP_NAME ,JOptionPane.INFORMATION_MESSAGE);
			}
		});
		helpMenu.add(aboutItem);
		
		getContentPane().add(menuBar, BorderLayout.PAGE_START);
	}
	
	public void setMenuItemLoadActionListener(ActionListener l) {
		menuItemLoad.addActionListener(l);
	}
	
	public void setMenuItemSaveActionListener(ActionListener l) {
		menuItemSave.addActionListener(l);
	}
	
	public void setMenuItemPreferencesActionListener(ActionListener l) {
		menuItemPreferences.addActionListener(l);
	}
	
	public void setMenuItemExitActionListener(ActionListener l) {
		menuItemExit.addActionListener(l);
	}
	
	public void setMenuItemOperationsActionListener(ActionListener l) {
		menuItemOperations.addActionListener(l);
	}
}
package hardcode.papierjosef.rajbo.view;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class ProgressDialog extends JDialog {

	private static final long serialVersionUID = -4456430664490373494L;

	public ProgressDialog() {
		super();
//		setModal(true);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setResizable(false);
		JProgressBar dpb = new JProgressBar(0, 500);
		dpb.setIndeterminate(true);
		add(BorderLayout.CENTER, dpb);
		add(BorderLayout.NORTH, new JLabel("Progress..."));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setSize(300, 75);
		setVisible(true);
	}

	public void kill(){
		setVisible(false);
	}
}

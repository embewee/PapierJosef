package hardcode.papierjosef.rajbo.view;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class ProgressDialog extends JDialog {
	
	private ProgressDialog (Frame parentFrame) {
		super(parentFrame, "Progress Dialog", true);
		JProgressBar dpb = new JProgressBar(0, 500);
		dpb.setIndeterminate(true);
		add(BorderLayout.CENTER, dpb);
		add(BorderLayout.NORTH, new JLabel("Progress..."));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setSize(300, 75);
		setLocationRelativeTo(parentFrame);
	}
	
	//TODO
	 public static void showProgressDialog(Frame parentFrame) {
		 final ProgressDialog dlg = new ProgressDialog(parentFrame);
		    Thread t = new Thread(new Runnable() {
		      public void run() {
		        dlg.setVisible(true);
		      }
		    });
	  }
}

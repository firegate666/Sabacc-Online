package sabacc.client.swing;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JStatusPanel extends JPanel {
	
	protected JLabel message = new JLabel("Status");
	
	public void showInfo(String text) {
		message.setForeground(new Color(0, 0, 0));
		message.setText(text);
	}
	
	public void showError(String text) {
		message.setForeground(new Color(255, 0, 0));
		message.setText(text);
	}

	public JStatusPanel() {
		this.setLayout(new BorderLayout());
		message.setAlignmentY(JLabel.CENTER_ALIGNMENT);
		message.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		add(message, BorderLayout.CENTER);
	}

}

package sabacc.client.swing;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import sabacc.ClientProperties;
import sabacc.Messages;
import sabacc.server.IServer;

public class Gui extends JFrame {
	
	protected IServer server;
	protected JStatusPanel status;
	
	public Gui() {
		super();
		initialize();
	}
	
	protected void initialize() {
		int x = ClientProperties.getInt("client.gui.x");
		int y = ClientProperties.getInt("client.gui.y");
		int width = ClientProperties.getInt("client.gui.width");
		int height = ClientProperties.getInt("client.gui.height");
		this.setBounds(x, y, width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(Messages.getString("client.gui.title"));
		this.setLayout(new BorderLayout());
		
		add(getStatusPanel(), BorderLayout.SOUTH);
	}
	
	protected JStatusPanel getStatusPanel() {
		status = new JStatusPanel();
		return status;
	}
}

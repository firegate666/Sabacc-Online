package sabacc.client.swing;

import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;

import sabacc.ClientProperties;
import sabacc.Messages;
import sabacc.ServerProperties;
import sabacc.client.Sabacc;
import sabacc.server.IServer;

public class Gui extends JFrame {
	
	protected IServer server;
	protected Sabacc sabacc;
	protected JStatusPanel status;
	
	public boolean pingServer() {
		boolean result = true;
		try {
			this.getServer().online();
		} catch (RemoteException e) {
			this.server = null;
			result = false;
		}
		return result;
	}
	
	public void writeMessage(String msg) {
		status.showInfo(msg);
	}
	
	public Gui(Sabacc sabacc) {
		super();
		this.sabacc = sabacc;
		initialize();
	}
	
	public void addlog(String message) {
		sabacc.addlog(message);
	}
	
	public IServer getServer() {
		if (this.server != null) {
			return this.server;
		}
		String serverip = ServerProperties.getString("server.rmi.ip");
		int serverport = ServerProperties.getInt("server.rmi.port");
		String servername = ServerProperties.getString("server.rmi.name");
		String rurl = "//" + serverip + ":" + serverport + "/" + servername;
		addlog("INFO: Lookup remote object "+rurl);
		Object o;
		try {
			o = Naming.lookup(rurl);
			addlog("INFO: Remote object found: "+o.getClass().toString());
			server = (IServer) o;
			return server;
		} catch (MalformedURLException e) {
			addlog("ERROR: "+e.getMessage());
		} catch (RemoteException e) {
			addlog("ERROR: "+e.getMessage());
		} catch (NotBoundException e) {
			addlog("ERROR: "+e.getMessage());
		}
		return null;
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
		Thread ping = new Thread(new ServerPing(this));
		ping.start();
	}
	
	protected JStatusPanel getStatusPanel() {
		status = new JStatusPanel();
		return status;
	}

}

class ServerPing implements Runnable {
	protected Gui parent;
	public ServerPing(Gui parent) {
		this.parent = parent;
	}
	public void run() {
		while(true) {
			if (parent.pingServer())
				parent.writeMessage("Server online");
			else
				parent.writeMessage("Server offline");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
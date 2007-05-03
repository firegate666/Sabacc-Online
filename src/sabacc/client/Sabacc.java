package sabacc.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;

import sabacc.ServerProperties;
import sabacc.beans.Answer;
import sabacc.beans.Request;
import sabacc.client.swing.Gui;
import sabacc.server.IServer;
import sabacc.util.Utils;

public class Sabacc {

	public String getIp() {
	    try {
	        InetAddress addr = InetAddress.getLocalHost();
	    
	        // Get IP Address
	        byte[] ipAddr = addr.getAddress();
	    
	        // Get hostname
	        String hostname = addr.getHostAddress();
		    return hostname;
	    } catch (UnknownHostException e) {
	    }
	    return "ip not recognized";
	}
	
	protected void debugOutput(String msg) {
		System.err.println(Utils.getTimestamp() + ": " + msg);
	}

	public void addlog(String msg) {
		File f = new File("client.log");
		try {
			if (!f.exists())
				f.createNewFile();
			FileWriter fw = new FileWriter(f, true);
			Date d = new Date(System.currentTimeMillis());
			msg = "" + Utils.getTimestamp() + ": " + msg + "\n";
			System.out.print(msg);
			fw.write(msg);
			fw.close();
		} catch (FileNotFoundException e) {
			debugOutput(ServerProperties
					.getString("Server.msg.general.errlocatelog")
					+ " " + "log.txt");
		} catch (IOException e) {
			debugOutput(ServerProperties
					.getString("Server.msg.general.errwritelog")
					+ " " + "log.txt");
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Sabacc sabacc = new Sabacc();
		Gui gui = new Gui(sabacc);
		gui.setVisible(true);
	}


}

package sabacc.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import sabacc.ServerProperties;
import sabacc.beans.Answer;
import sabacc.beans.Request;
import sabacc.util.Utils;

public class ServerImpl extends UnicastRemoteObject implements IServer, Serializable {

	protected void debugOutput(String msg) {
		System.err.println(Utils.getTimestamp() + ": " + msg);
	}

	public void addlog(String msg) {
		File f = new File("server.log");
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
	
	public ServerImpl() throws RemoteException {
		super();
	}
	
	public void online() throws RemoteException {
		// TODO Auto-generated method stub
	}

	public Answer request(Request request) throws RemoteException {
		addlog("ACTION: Request received from: "+request.getSenderip());
		return new Answer();
	}

	public static void main(String[] args) {
		ServerImpl robj = null;
		try {
			robj = new ServerImpl();
			String serverip = ServerProperties.getString("server.rmi.ip");
			int serverport = ServerProperties.getInt("server.rmi.port");
			String servername = ServerProperties.getString("server.rmi.name");
			String rurl = "//" + serverip + ":" + serverport + "/" + servername;
			robj.addlog("INFO: Start registry on port "+serverport);
			LocateRegistry.createRegistry(serverport);
			robj.addlog("INFO: Rebind remote object "+rurl);
			Naming.rebind(rurl, robj);
			robj.addlog("INFO: Server running");
		} catch (RemoteException e) {
			robj.addlog("ERROR: "+e.getMessage());
		} catch (MalformedURLException e) {
			robj.addlog("ERROR: "+e.getMessage());
		}
	}
}

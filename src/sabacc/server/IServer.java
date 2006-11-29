package sabacc.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sabacc.beans.Answer;
import sabacc.beans.Request;

public interface IServer extends Remote  {
	
	/**
	 * Simply ping server, if exception is thrown, server is down
	 * @throws RemoteException
	 */
	void online() throws RemoteException;
	
	/**
	 * Send request to server and receive answer
	 * 
	 * @param request
	 * @return
	 * @throws RemoteException
	 */
	Answer request(Request request) throws RemoteException;

}

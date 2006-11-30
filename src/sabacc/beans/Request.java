package sabacc.beans;

public class Request extends AbstractBean {

	private String senderip;
	
	public Request(String senderip) {
		this.senderip=senderip;
	}

	/**
	 * @return senderip
	 */
	public String getSenderip() {
		return senderip;
	}

	/**
	 * @param senderip Festzulegender senderip
	 */
	public void setSenderip(String senderip) {
		this.senderip = senderip;
	}
	
}

package q;

public class VipStatusException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VipStatusException() {
		super("Vip status is insufficient.");
	}
}

package q;

public class CapacityFullException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CapacityFullException() {
		super("Capacity is full.");
	}
}

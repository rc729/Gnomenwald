package finalProject;

public class Village extends PublicInfrastructure{
	
	private static int totalVillages = 0;
	private int ID;
	private String name;
	
	public Village(String name, int capacity, int VIP) {
		super(capacity, VIP);
		totalVillages++;
		this.ID = totalVillages;
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + "(ID:" + ID + ", name:" + name + 
				", capacity:" + capacity + ", population:" + gnomes +")";
	}
}

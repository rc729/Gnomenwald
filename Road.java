package finalProject;

public class Road extends PublicInfrastructure{
	
	private Village from, to;
	private int distance;
	
	public Road(Village from, Village to, int distance, int capacity, int VIP) {
		super(capacity, VIP);
		this.from = from;
		this.to = to;
		this.distance = distance;
	}
	
	public Village getFrom() {
		return from;
	}

	public Village getTo() {
		return to;
	}

	public int getDistance() {
		return distance;
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + "(" + from.getName() + ", " + to.getName() + ", distance:" + distance + ", " + gnomes + ")"; 
	}
	
}

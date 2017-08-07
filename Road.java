package q;

/**
 * Roads link two villages and have a direction. Assume road is a straight line and has 
 * a calculate-able distance.
 * @author Roger
 *
 */

public class Road extends PublicInfrastructure implements Comparable<Road>{
	
	private Village from, to;
	private static final int DEFAULT_CAPACITY = 5;
	private static final int DEFAULT_VIP = 0;
	
	public Road(Village from, Village to, int capacity, int VIP) {
		super(capacity, VIP);
		this.from = from;
		this.to = to;
		from.addOutboundRoad(this);
		to.addInboundRoad(this);
	}
	
	public Road(Village from, Village to) {
		this(from, to, DEFAULT_CAPACITY, DEFAULT_VIP);
	}
	
	public Village getFrom() {
		return from;
	}

	public Village getTo() {
		return to;
	}
	
	//Calculate the distance of this road
	public double getDistance() {
		double distance = Math.sqrt(Math.pow(to.getXpos()-from.getXpos(), 2) 
				+ Math.pow(to.getYpos()-from.getYpos(), 2));
		return distance;
	}
	
	public void destroy() {
		from.removeOutboundRoad(this);
		to.removeInboundRoad(this);
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof Road)) {
			return false;
		}
		else {
			Road otherRoad = (Road) other;
			return this.from.equals(otherRoad.getFrom()) && this.to.equals(otherRoad.getTo());
		}
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + "(" + from.getName() + ", " + to.getName() + ", distance:" + getDistance() + ", gnomes:" + gnomes + ")"; 
	}

	@Override
	public int compareTo(Road o) {
		return (int) (this.getDistance() - o.getDistance());
	}
	
}

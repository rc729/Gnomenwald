package q;

import java.awt.Point;
import java.util.LinkedList;

/**
 * Villages
 * @author Roger
 *
 */

public class Village extends PublicInfrastructure{
	
	
	private static int totalVillages = 0;
	private int ID;
	private String name;
	private int xpos,ypos;
	private LinkedList<Road> inboundRoads = new LinkedList<>();
	private LinkedList<Road> outboundRoads = new LinkedList<>();

	public Village(int xpos, int ypos) {
		super(DEFAULT_CAPACITY, DEFAULT_VIP);
		totalVillages++;
		this.ID = totalVillages;
		this.name = "v"+ ID;
		this.xpos= xpos;
		this.ypos = ypos;
	}
	
	public Village() {
		super(DEFAULT_CAPACITY, DEFAULT_VIP);
		totalVillages++;
		this.ID = totalVillages;
		this.name = "v"+ ID;
		this.xpos = 0;
		this.ypos = 0;
	}
	public Village(String name, int capacity, int VIP) {
		super(capacity, VIP);
		totalVillages++;
		this.ID = totalVillages;
		this.name = name;
		this.xpos = 0;
		this.ypos = 0;
	}
	
	public Village(String name, int capacity, int VIP, int xpos, int ypos) {
		super(capacity, VIP);
		totalVillages++;
		this.ID = totalVillages;
		this.name = name;
		this.xpos = xpos;
		this.ypos = ypos;
	}
	
	/**
	 * Adds road to village's list of roads. The road must end this village.
	 * If road does not end at this village, do nothing.
	 */
	public void addInboundRoad(Road road) {
		if(road.getTo().equals(this)) {
			this.inboundRoads.add(road);
		}
	}
	
	/**
	 * Adds road to village's list of roads. The road must start at this village.
	 * If road does not start at this village, do nothing.
	 */
	public void addOutboundRoad(Road road) {
		if(road.getFrom().equals(this)) {
			this.outboundRoads.add(road);
		}
	}
	
	/**
	 * Remove outbound road from village
	 * @return
	 */
	public void removeOutboundRoad(Road road) {
		outboundRoads.remove(road);
	}
	
	/**
	 * Remove inbound road from village
	 * @return
	 */
	public void removeInboundRoad(Road road) {
		inboundRoads.remove(road);
	}
	

	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}
	
	public Point getPoint(){
		return new Point(this.xpos, this.ypos);
	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}
	
	public LinkedList<Road> getInboundRoads() {
		return inboundRoads;
	}

	public void setInboundRoads(LinkedList<Road> inboundRoads) {
		this.inboundRoads = inboundRoads;
	}

	public LinkedList<Road> getOutboundRoads() {
		return outboundRoads;
	}

	public void setOutboundRoads(LinkedList<Road> outboundRoads) {
		this.outboundRoads = outboundRoads;
	}
	
	public Road getOutboundRoad(Village adjacent) {
		LinkedList<Road> roads = this.outboundRoads;
		for(Road road : roads) {
			if (road.getTo().equals(adjacent))
				return road;
		}
		return null;
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof Village)) {
			return false;
		}
		else {
			Village otherVillage = (Village) other;
			return this.ID == otherVillage.getID();
		}
	}

	public String toString() {
		return this.getClass().getSimpleName() + "(ID:" + ID + ", name:" + name + 
				", capacity:" + capacity + ", population:" + gnomes +")";
	}
}

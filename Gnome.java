package q;

import java.util.LinkedList;

/**
 * This class creates Gnomes. Gnomes have unique IDs. They also have a name, favorite color, and VIP status.
 * 
 */

public class Gnome {
	private static int IDs = 1000;
	private int id;
	private String name;
	private String favColour;
	private int VIP;
	private PublicInfrastructure currentLocation;
	private LinkedList<PublicInfrastructure> visited = new LinkedList<>();
	private double xpos, ypos;
	private static final double SPEED = 1.0;
	
	public int getID() { return this.id; }
	public String getName() { return this.name; }
	public String getColour() { return this.favColour; }
	public void setColour(String c) { this.favColour = c; }
	public int getVIP() { return this.VIP; }
	
	public Gnome(String n, String c, int v, Village start) throws Exception{
		this.id = IDs;
		this.name = n;
		this.favColour = c;
		this.VIP = v;
		this.currentLocation = start;
		start.addGnome(this);
		visited.add(currentLocation);
		IDs++;
		this.xpos = start.getXpos();
		this.ypos = start.getYpos();
		Census.instance.population.add(this);

	}
	
	public LinkedList<PublicInfrastructure> history() {
		return visited;
	}
	
	//TODO
	public void travel(Road road) throws VipStatusException {
		road.addGnome(this);
		road.getFrom().deleteGnome(this);
		visited.add(road);
		currentLocation = road;
		double xStart = road.getFrom().getXpos();
		double xEnd = road.getTo().getXpos();
		double yStart = road.getFrom().getYpos();
		double yEnd = road.getTo().getYpos();		
		double disTraveled = 0.0;
		
		while (disTraveled < road.getDistance()) {
			xpos = xStart + (disTraveled/road.getDistance()) * (xEnd - xStart);
			ypos = yStart + (disTraveled/road.getDistance()) * (yEnd - yStart);
			disTraveled += SPEED;
			System.out.println(name + "("+ xpos + ", " + ypos + ")");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Village dst = road.getTo();
		System.out.println("Entering: " + dst);
		dst.addGnome(this);
		visited.add(dst);
		road.deleteGnome(this);
		System.out.println("Entered: " + dst);
		
	}
	public void goToVillage(Village v) throws VipStatusException {
		
		if (currentLocation != null && currentLocation.equals(v)) {
			return;
		}
		if (currentLocation instanceof Village) {
			Village location = (Village) currentLocation;
			Path path = Graph.Gnomenwald.randomPath(location, v);
			if (path != null) {
				for(int i = 0; i < path.size() - 1; i++){
					Village village = path.get(i);
					Road road = village.getOutboundRoad(path.get(i+1));
					System.out.println(name + " Entering road: " + road);
					travel(road);//wait until road is available
				}
			}
		}
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof Gnome)) {
			return false;
		}
		else {
			Gnome otherGnome = (Gnome) other;
			return this.id == otherGnome.getID();
		}
	}
	
	public String toString() {
		String out = "";
		out += "Name: " + this.name + "\n";
		out += "ID: " + this.id + "\n";
		out += "Favorite Colour: " + this.favColour + "\n";
		out += "VIP Level: " + this.VIP;
		return out;
	}
}
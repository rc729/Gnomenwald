package finalProject;

import java.util.LinkedList;

public class Gnome {
	private static int IDs = 1000;
	private int id;
	private String name;
	private String favColour;
	private int VIP;
	private PublicInfrastructure currentLocation;
	private LinkedList<PublicInfrastructure> visited = new LinkedList<>();
	private double xpos, ypos;
	
	public int getID() { return this.id; }
	public String getName() { return this.name; }
	public String getColour() { return this.favColour; }
	public void setColour(String c) { this.favColour = c; }
	public int getVIP() { return this.VIP; }
	
	public Gnome(String n, String c, int v, Village start, Census census) throws Exception{
		this.id = IDs;
		this.name = n;
		this.favColour = c;
		this.VIP = v;
		IDs ++;
		this.currentLocation = start;
		start.addGnome(this);
		visited.add(currentLocation);
		this.xpos = start.getXpos();
		this.ypos = start.getYpos();
		Census.instance.population.add(this);
	}
	
	public LinkedList<PublicInfrastructure> history() {
		return visited;
	}
	
	public void go(PublicInfrastructure destination) throws Exception {
		destination.addGnome(this);
		visited.add(destination);
		if(currentLocation != null) {
			currentLocation.deleteGnome(this);
		}
		currentLocation = destination;
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

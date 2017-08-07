package q;

import java.util.LinkedList;
import java.util.Random;

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

	
	public double getXpos() {
		return xpos;
	}
	public double getYpos() {
		return ypos;
	}

	private static final double SPEED = 5.0;
	
	public int getID() { return this.id; }
	public String getName() { return this.name; }
	public String getColour() { return this.favColour; }
	public void setColour(String c) { this.favColour = c; }
	public int getVIP() { return this.VIP; }
	
	public Gnome(Village start) throws VipStatusException {
		IDs++;
		this.id = IDs;
		this.name = "n" + id;
		this.favColour = "c" + id;
		this.VIP = 1;
		this.currentLocation = start;
		start.addGnome(this);
		visited.add(currentLocation);
		this.xpos = start.getXpos();
		this.ypos = start.getYpos();
		Census.instance.population.add(this);
	}
	
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
	
	public PublicInfrastructure getCurrentLocation() {
		return this.currentLocation;
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
		
		while (disTraveled < road.getDistance() && GnomenWald.Gnomenwald.isRunning()) {
			xpos = xStart + (disTraveled/road.getDistance()) * (xEnd - xStart);
			ypos = yStart + (disTraveled/road.getDistance()) * (yEnd - yStart);
			disTraveled += SPEED;
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Village dst = road.getTo();
		System.out.println(name + " Entering: " + dst);
		dst.addGnome(this);
		visited.add(dst);
		road.deleteGnome(this);
		currentLocation = dst;
		System.out.println(name + " Entered: " + dst);
		
	}
	//Lazy = 0, urgent = 1. Lazy will pick a random path. Urgent will pick shortest path
	public void blockedGoToVillage(Village v, int urgency) throws VipStatusException {
		
		if (currentLocation != null && currentLocation.equals(v)) {
			return;
		}
		if (currentLocation instanceof Village) {
			Village location = (Village) currentLocation;
			
			Path path;

			if(urgency == 0) {
				path = GnomenWald.Gnomenwald.randomPath(location, v);
			} else {
				path = GnomenWald.Gnomenwald.shortestPath(location, v);
			}
			if (path != null) {
				walkThroughPath(path);
			}
		}
	}
	
	private void walkThroughPath(Path path) throws VipStatusException {
		for(int i = 0; i < path.size() - 1; i++){
			Village village = path.get(i);
			Road road = village.getOutboundRoad(path.get(i+1));
			System.out.println(name + " Entering road: " + road);
			travel(road);//wait until road is available
		}
	}
	
	public void goToVillage(Village v, int urgency) {
		GnomenWald.Gnomenwald.start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					blockedGoToVillage(v,urgency);
				} catch (VipStatusException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public void walkRandomly() {
		GnomenWald.Gnomenwald.start();
		if (currentLocation instanceof Road)
			return;
		Village location = (Village) this.currentLocation;
		new Thread(new Runnable(){
			
			@Override
			public void run() {
				Random rnd = new Random();
				LinkedList<Road> outRoads = location.getOutboundRoads();
				while(outRoads.size() > 0 && GnomenWald.Gnomenwald.isRunning()) {
					Road road = outRoads.get(rnd.nextInt(outRoads.size()));
					try {
						travel(road);
					} catch (VipStatusException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					outRoads = road.getTo().getOutboundRoads();
				}
			}	
		}).start();	
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
		out += "Name: " + this.name + ", ";
		out += "ID: " + this.id + ", ";
		out += "Favorite Colour: " + this.favColour + ", ";
		out += "VIP Level: " + this.VIP;
		return out;
	}
}
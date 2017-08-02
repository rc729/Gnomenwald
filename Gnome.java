package finalProject;

public class Gnome {
	private static int IDs = 1000;
	private int id;
	private String name;
	private String favColour;
	private int VIP;
	
	public int getID() { return this.id; }
	public String getName() { return this.name; }
	public String getColour() { return this.favColour; }
	public void setColour(String c) { this.favColour = c; }
	public int getVIP() { return this.VIP; }
	
	public Gnome(String n, String c, int v) {
		this.id = IDs;
		this.name = n;
		this.favColour = c;
		this.VIP = v;
		IDs ++;
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

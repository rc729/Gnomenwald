package finalProject;

import java.util.LinkedList;

/**
 * Public infrastructure is a base class for Road and Village. It has VIP and capacity.
 * VIP is the required status of gnomes to utilize this infrastructure. Capacity is the 
 * maximum number of gnomes that can utilize the infrastructure at any time.
 * 
 */

public class PublicInfrastructure {
	protected int capacity, VIP;
	protected LinkedList<Gnome> gnomes;
	
	protected PublicInfrastructure(int capacity, int VIP){
		this.capacity = capacity;
		this.VIP = VIP;
		this.gnomes = new LinkedList<>();
	}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public LinkedList<Gnome> getGnomes() {
		return gnomes;
	}
	
	public int getGnomeCount() {
		return gnomes.size();
	}
	
	/**
	 * Add gnomes to the infrastructure. If the gnome vip is not high enough
	 * or the capacity of the infrastructure has been reached, throw exception.
	 * @param gnome
	 * @throws Exception
	 */
	public void addGnome(Gnome gnome) throws Exception {
		if (gnomes.size() == capacity)
			throw new Exception("Infrastructure is full!");
		else 
			if (gnome.getVIP() >= VIP)
				gnomes.add(gnome);
			else
				throw new Exception("VIP status not high enough!");
	}
	
	public void deleteGnome(Gnome gnome) {
		gnomes.remove(gnome);
	}
}

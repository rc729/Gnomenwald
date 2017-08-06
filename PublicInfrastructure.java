package q;

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
	 * This method is blocked until capacity is available.
	 * @param gnome
	 * @throws Exception
	 */
	public void addGnome(Gnome gnome) throws VipStatusException {
		if (gnome.getVIP() < VIP)
			throw new VipStatusException();
		
		while(gnomes.size() >= capacity) {
			try {
				System.out.println("----------------------------" + gnome.getName() + " is Waiting for " + this.toString() + " -----------------------------");
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		gnomes.add(gnome);
	}
	
	public void deleteGnome(Gnome gnome) {
		gnomes.remove(gnome);
	}
}

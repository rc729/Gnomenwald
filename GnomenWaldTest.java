package q;

import java.util.LinkedList;

public class GnomenWaldTest {
	
	public static void main(String[] args) throws Exception {
		searchTest();
		shortestTest();
		System.out.println("-----------REMOVE VILLAGE 2 TEST---------------");
		removeVillage2Test();
		System.out.println("-----------GO TEST---------------");
		goTest();
	}

		public void addDeleteTest() throws Exception {
			GnomenWald g = new GnomenWald();
			Village a = new Village("A", 1000, 0, 0, 0);
			Village b = new Village("B", 10, 1, 3, 4);
			Village c = new Village("C", 10, 1);
			Village d = new Village("D", 10, 1);
			Village e = new Village("E", 10, 1);
			

			g.addVillage(a);
			g.addVillage(b);
			g.addVillage(c);
			g.addVillage(d);
			g.addVillage(e);
			
			System.out.println(g.toString() + "\n");
			System.out.println();

			
			g.createRoad(b,e,3, 1);
			g.createRoad(c,d,3, 0);
			g.createRoad(e,c,3, 0);
			g.createRoad(a,c,3, 0);
			g.createRoad(a,b,3, 0);
			
			
			System.out.println(g.toString() + "\n");
			
			g.deleteRoad(b, e);
			
			System.out.println(g.toString() + "\n");
			
//			g.deleteVillage1(a);
//			
//			System.out.println(g.getAllVillages());
//			System.out.println(g.getAllRoads() + "\n");
//			
			Gnome x = new Gnome("X", "BLUE", 1,a);
			Gnome y = new Gnome("Y", "GREEN", 1,a);
			Gnome z = new Gnome("Z", "BLUE", 1,a);
			Gnome n = new Gnome("N", "BLUE", 1,a);

			a.addGnome(x);
			a.addGnome(y);
			a.addGnome(z);
			try {
				a.addGnome(n);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			
			System.out.println(a);
			System.out.println("-------------------------------------------------");


			a.deleteGnome(x);
			a.addGnome(n);
			System.out.println(a);
			System.out.println("-------------------------------------------------");
			
			System.out.println(g.findAllPaths(a,d));
		}
		
		private static void searchTest() {
			GnomenWald g = new GnomenWald();
			Village a = new Village("A", 1000, 0, 0, 0);
			Village b = new Village("B", 10, 1, 3, 4);
			Village c = new Village("C", 10, 1);
			Village d = new Village("D", 10, 1);
			Village e = new Village("E", 10, 1);

			g.addVillage(a);
			g.addVillage(b);
			g.addVillage(c);
			g.addVillage(d);
			g.addVillage(e);
			
			g.createRoad(b,e,3, 1);
			g.createRoad(c,d,3, 0);
			g.createRoad(e,c,3, 0);
			g.createRoad(a,c,3, 0);
			g.createRoad(a,b,3, 0);
			
			
			LinkedList<Path> paths = g.findAllPaths(a, d);
			for (int i = 0; i < paths.size(); i++)
				System.out.println(paths.get(i));
		}
		
		private static void shortestTest() {
			GnomenWald g = new GnomenWald();
			Village a = new Village("A", 1000, 0, 0, 0);
			Village b = new Village("B", 10, 1, 0, 5);
			Village c = new Village("C", 10, 1,0,20);
			Village d = new Village("D", 10, 1,0,30);
			
			g.addVillage(a);
			g.addVillage(b);
			g.addVillage(c);
			g.addVillage(d);
			g.createRoad(a,b,3, 1);
			g.createRoad(b,c,3, 0);
			g.createRoad(a,d,3, 0);
			g.createRoad(d,c,3, 0);
			
			Path path = g.shortestPath(a, c);
			System.out.println(path);
		}
		
		private static void removeVillage2Test() {
			GnomenWald g = new GnomenWald();
			Village a = new Village("A", 1000, 0, 0, 0);
			Village b = new Village("B", 10, 1, 0, 5);
			Village c = new Village("C", 10, 1,0,20);
			Village d = new Village("D", 10, 1,0,30);
			Village e = new Village("E", 10, 1,0,50);

			g.addVillage(a);
			g.addVillage(b);
			g.addVillage(c);
			g.addVillage(d);
			g.addVillage(e);
			g.createRoad(a,b,3, 1);
			g.createRoad(b,c,3, 0);
			g.createRoad(a,d,3, 0);
			g.createRoad(d,c,3, 0);
			g.createRoad(c,e,3, 0);
			g.createRoad(c,b,3, 0);
			g.createRoad(e,c,3, 0);

			dumpVillage(b);
			System.out.println();

			g.deleteVillage2(c);
			dumpVillage(b);
			System.out.println();
			dumpVillage(d);
			System.out.println();
			dumpVillage(e);
						
		}
		
		private static void dumpVillage(Village v) {
			System.out.println(v);
			System.out.println("Outbound: " + v.getOutboundRoads());
			System.out.println("Inbound : " + v.getInboundRoads());
		}
		
		private static void goTest() throws Exception {
			GnomenWald g = new GnomenWald();
			Village a = new Village("A", 1000, 0, 0, 0);
			Village b = new Village("B", 10, 1, 5, 5);
			Village c = new Village("C", 10, 1, 5, 9);

			g.addVillage(a);
			g.addVillage(b);
			g.addVillage(c);
			g.createRoad(a,b,1, 1);
			g.createRoad(b,c,3, 1);

			
			Gnome x = new Gnome("X", "BLUE", 1,a);
			Gnome y = new Gnome("Y", "BLUE", 1,a);

			
			new Thread( new Runnable() {

				@Override
				public void run() {
					x.goToVillage(c,0);
				}
			}).start();
			new Thread( new Runnable() {

				@Override
				public void run() {
					y.goToVillage(c,0);					
				}
			}).start();
			
			Thread.sleep(25000);
			System.out.println(x.history());
			System.out.println(y.history());


			
		}
}

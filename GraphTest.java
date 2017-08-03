package q;

public class GraphTest {

		public static void main(String[] args) throws Exception {
			Graph g = new Graph();
			Village a = new Village("A", 1000, 0);
			Village b = new Village("B", 10, 1);
			Village c = new Village("C", 10, 1);
			Village d = new Village("D", 10, 1);
			Village e = new Village("E", 10, 1);
			
			Road ab = new Road(a,b,10, 10, 1);
			Road ac = new Road(a,c,10, 5, 1);
			Road db = new Road(d,b,10, 2, 0);

			g.addVillage(a);
			g.addVillage(b);
			g.addVillage(c);
			g.addVillage(d);
			g.addVillage(e);
			
			System.out.println(g.toString() + "\n");
			
			g.addRoad(b,e,5,3, 1);
			g.addRoad(c,d,5,3, 0);
			g.addRoad(a,e,5,2, 1);
			g.addRoad(ab);
			g.addRoad(ac);
			g.addRoad(db);
			
			System.out.println(g.toString() + "\n");
			
			g.deleteRoad(b, e);
			
			System.out.println(g.toString() + "\n");
			
			g.deleteVillage1(a);
			
			System.out.println(g.getAllVillages());
			System.out.println(g.getAllRoads() + "\n");
			
			Gnome x = new Gnome("X", "BLUE", 1,a);
			Gnome y = new Gnome("Y", "GREEN", 1,a);
			Gnome z = new Gnome("Z", "BLUE", 1,a);
			Gnome n = new Gnome("N", "BLUE", 1,a);
			Gnome m = new Gnome("M", "BLUE", 0,a);

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

			
			ab.addGnome(y);
			try {
				ab.addGnome(m);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			db.addGnome(x);
			
			System.out.println(ab);
			System.out.println("-------------------------------------------------");
			
			x.go(ab);
			x.go(b);
			System.out.println(ab);
			System.out.println("-------------------------------------------------");

			System.out.println(b);
			System.out.println("-------------------------------------------------");

			System.out.println(x.history());
			
			
		}
}

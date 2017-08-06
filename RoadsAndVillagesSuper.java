package gnomenwald;
import java.util.ArrayList;
import gnomenwald.Villagemap.Region;

/**
 * Created by dorothyquincy on 02/08/2017.
 */
public class RoadsAndVillagesSuper {

    private ArrayList<Gnome> LocalGnomes; //Gnomes at this Location
    public ArrayList<Gnome> getLocalGnomes() {return this.LocalGnomes;}

    public RoadsAndVillagesSuper(){ //Constructor
        this.LocalGnomes = new ArrayList<Gnome>();
    }
    public static class Village extends RoadsAndVillagesSuper{
        //Extension of Infrastructure to Represent a Gnome Village
        private static int number = 0; //Unique ids
        private int id; //Index in Region Objects Villages ArrayList
        private int Name; //Id+1
        private ArrayList<Gnome> GnomesArrived; //Holds Gnomes who have arrived at Village as final Destination
        private int MaxPop; //Max population per village
        private Region R; //Source Region

        private ArrayList<Village> VillageInboundConnections; //Villages that have roads directly leading here
        private ArrayList<Road> RoadInboundConnections; //Roads leading here
        private ArrayList<Village> VillageOutboundConnections; //Villages that you can get to from here on one road
        private ArrayList<Road> RoadOutboundConnections; //Roads leading out of here
        //private int DistancefromSource;
        //private Village Backpointer = null;
        //private Road RoadBackpointer = null;
        private double xcoord; //Point Location on GUI
        private double ycoord; //Point Location on GUI

        //Getters and Setters
        public ArrayList<Village> getVInConnections(){return this.VillageInboundConnections;}
        public ArrayList<Road> getRInConnections(){return this.RoadInboundConnections;}
        public ArrayList<Village> getVOutConnections(){return this.VillageOutboundConnections;}
        public ArrayList<Road> getROutConnections(){return this.RoadOutboundConnections;}
        public ArrayList<Gnome> getRestingGnomes(){return this.getLocalGnomes();}
        public int getId(){return this.id;}
        public Region getR() {return R;}
        public void setR(Region r) {R = r;}
        public double getX_Coord(){return this.xcoord;}
        public void setX_Coord(double x){this.xcoord = x;}
        public double getY_Coord(){return this.ycoord;}
        public void setY_Coord(double y){this.ycoord = y;}
        public int getName(){return this.Name;}
        public ArrayList<Gnome> getGnomesArrived(){return this.GnomesArrived;}
        private void setGnomesArrived(ArrayList<Gnome> A){this.GnomesArrived = A;}
        public int getMaxPop(){return this.MaxPop;}
        public void setMaxPop(int M){this.MaxPop = M;}

        public String toString(){ //String Representation of a Village
            return "Village "+this.Name;
        }

        public Village(Region R){ //Constructor
            super();
            this.id = Village.number; number++;
            this.Name = number;
            this.VillageInboundConnections = new ArrayList<Village>();
            this.RoadInboundConnections = new ArrayList<Road>();
            this.VillageOutboundConnections = new ArrayList<Village>();
            this.RoadOutboundConnections = new ArrayList<Road>();
            setGnomesArrived(new ArrayList<Gnome>());
            this.setMaxPop(10); //Default Max population
            this.setR(R);

            R.getVillages().add(this);
        }
        public Village(Region R, int M){ //Constructor with a Max Size
            super();
            this.id = Village.number; number++;
            this.Name = number;
            this.VillageInboundConnections = new ArrayList<Village>();
            this.RoadInboundConnections = new ArrayList<Road>();
            this.VillageOutboundConnections = new ArrayList<Village>();
            this.RoadOutboundConnections = new ArrayList<Road>();
            setGnomesArrived(new ArrayList<Gnome>());
            this.setMaxPop(M); //Set Max Population to argument M
            this.setR(R);

            R.getVillages().add(this);
        }
    }
    public static class Road extends Infrastructure{
        //Extension of Infrastructure to Represent a Road between Villages
        private int length;
        private int toll;
        private Village source;
        private Village target;

        public String toString(){//String Representation of a Road
            return "Road from Village "+this.getSource().getName()+ " to Village "+
                    this.getTarget().getName()+".";
        }

        public Road(Region R, Village S, Village T, int L){//Constructor with a known length
            this.setSource(S);
            this.setTarget(T);
            this.setLength(L);
            this.setToll(0);

            S.RoadOutboundConnections.add(this);
            T.RoadInboundConnections.add(this);
            S.VillageOutboundConnections.add(T);
            T.VillageInboundConnections.add(S);
        }
        public Road(Region R, Village S, Village T, int L, int Toll){
            this.setSource(S);
            this.setTarget(T);
            this.setLength(L);
            this.setToll(Toll);

            S.RoadOutboundConnections.add(this);
            T.RoadInboundConnections.add(this);
            S.VillageOutboundConnections.add(T);
            T.VillageInboundConnections.add(S);
        }
        //WRITE accessor and mutator methods


    }
}
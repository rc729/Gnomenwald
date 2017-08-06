package gnomenwald;

/**
 * Created by dorothyquincy on 02/08/2017.
 */
import java.awt.*;
        import java.awt.event.*;
        import java.util.ArrayList;
        import java.util.Random;

        import javax.swing.*;

        import gnomenwald.RoadsAndVillagesSuper.*;
        //*****insert import relevant gnome class here*******
        import gnomenwald.Villagemap.GUI.VillageIcon.VillageListener;

public class Villagemap {
    public static void main (String [] args) throws Exception{
        Region R = new Region();

        //Create Some Gnomes by Method 1
        ArrayList<Village> path1 = new ArrayList<Village>(); //Start at V1
        path1.add(R.getVillages().get(1)); //Go to V2 (Ids = Name-1)
        path1.add(R.getVillages().get(2)); //Go to V3
        path1.add(R.getVillages().get(3)); //Go to V4
        path1.add(R.getVillages().get(4)); //Go to V5
        Gnome G1 = new Gnome(R,path1,"gnomey");
        R.addGnome(G1, R.Villages.get(0));

        ArrayList<Village> path11 = new ArrayList<Village>(); //Start at V1
        path11.add(R.getVillages().get(1)); //Go to V2 (Ids = Name-1)
        path11.add(R.getVillages().get(2)); //Go to V3
        path11.add(R.getVillages().get(3)); //Go to V4
        path11.add(R.getVillages().get(4)); //Go to V5
        Gnome G11 = new Gnome(R,path11,"gnomer");
        R.addGnome(G11, R.Villages.get(0));

        ArrayList<Village> path2 = new ArrayList<Village>(); //Start at V1
        path2.add(R.getVillages().get(1)); //Go to V2
        path2.add(R.getVillages().get(3)); //Go to V4
        path2.add(R.getVillages().get(2)); //Go to V3
        path2.add(R.getVillages().get(0)); //Go to V1
        Gnome G2 = new Gnome(R,path2,"gnomen");
        R.addGnome(G2, R.Villages.get(0));

        }

        R.GUI.Initalize(); //Build the GUI

        //For each gnome in Regions gnome arraylist, start thread execution
        for(Gnome G:R.Gnomes){
            G.start();
        }
    }

    public static class Region{
        //lists of existing objects
        private ArrayList<Village> Villages;
        private ArrayList<Road> Roads;
        private ArrayList<Gnome> Gnomes;
        private ArrayList<Gnome> InactiveGnomes; // //All of the Exisiting Inactive Gnomes

        private GUI GUI; //The Swing GUI

        //accessor and mutator methods
        public ArrayList<Road> getRoads() {return this.Roads;}
        public ArrayList<Village> getVillages() {return this.Villages;}
        public ArrayList<Gnome> getGnomes() {return this.Gnomes;}
        public GUI getGUI() {return GUI;}
        public ArrayList<Gnome> getInactiveGnomes() {return InactiveGnomes;}
        public void setInactiveGnomes(ArrayList<Gnome> inactiveGnomes) {InactiveGnomes = inactiveGnomes;}

        //Add a Gnome and set location info pointers
        public void addGnome(Gnome gnom, Village vill){
            vill.getLocalGnomes().add(gnom);
            this.Gnomes.add(gnom);
            gnom.setLocation(vill);
        }

        //Constructor of a Region with known villages/roads
        public Region(){
            this.Villages = new ArrayList<Village>();
            this.Roads = new ArrayList<Road>();
            this.Gnomes = new ArrayList<Gnome>();
            this.setInactiveGnomes(new ArrayList<Gnome>());

            //Build Villages
            Village V1 = new Village(this); V1.setX_Coord(1200/2.0-Villagemap.GUI.DEFAULTDIMENSION*5);V1.setY_Coord(800/2.0-Villagemap.GUI.DEFAULTDIMENSION*5);
            Village V2 = new Village(this); V2.setX_Coord(1200/2.0+Villagemap.GUI.DEFAULTDIMENSION*5);V2.setY_Coord(800/2.0-Villagemap.GUI.DEFAULTDIMENSION*5);
            Village V3 = new Village(this); V3.setX_Coord(1200/2.0-Villagemap.GUI.DEFAULTDIMENSION*5);V3.setY_Coord(800/2.0+Villagemap.GUI.DEFAULTDIMENSION*5);
            Village V4 = new Village(this); V4.setX_Coord(1200/2.0+Villagemap.GUI.DEFAULTDIMENSION*5);V4.setY_Coord(800/2.0+Villagemap.GUI.DEFAULTDIMENSION*5);
            Village V5 = new Village(this); V5.setX_Coord(1200/2.0);V5.setY_Coord(800/2.0+Villagemap.GUI.DEFAULTDIMENSION*8);
            Village V6 = new Village(this); V6.setX_Coord(1200/2.0-Villagemap.GUI.DEFAULTDIMENSION*9);V6.setY_Coord(800/2.0-Villagemap.GUI.DEFAULTDIMENSION*9);
            Village V7 = new Village(this); V7.setX_Coord(1200/2.0+Villagemap.GUI.DEFAULTDIMENSION*9);V7.setY_Coord(800/2.0-Villagemap.GUI.DEFAULTDIMENSION*9);
            Village V8 = new Village(this); V8.setX_Coord(1200/2.0-Villagemap.GUI.DEFAULTDIMENSION*9);V8.setY_Coord(800/2.0+Villagemap.GUI.DEFAULTDIMENSION*9);
            Village V9 = new Village(this); V9.setX_Coord(1200/2.0+Villagemap.GUI.DEFAULTDIMENSION*9);V9.setY_Coord(800/2.0+Villagemap.GUI.DEFAULTDIMENSION*9);

            //Build Roads
            this.getRoads().add(new Road(this,V1,V2,4)); //One way from V1 to V2 of length 4
            this.getRoads().add(new Road(this,V2,V1,4)); //One way from V2 to V1 of length 4
            this.getRoads().add(new Road(this,V2,V3,6)); //One way from V2 to V3 of length 6
            this.getRoads().add(new Road(this,V3,V2,6)); //One way from V3 to V2 of length 6
            this.getRoads().add(new Road(this,V2,V4,4)); //One way from V2 to V4 of length 4
            this.getRoads().add(new Road(this,V4,V2,4)); //One way from V4 to V2 of length 4
            this.getRoads().add(new Road(this,V3,V4,4)); //One way from V3 to V4 of length 4
            this.getRoads().add(new Road(this,V4,V3,4)); //One way from V4 to V3 of length 4
            this.getRoads().add(new Road(this,V4,V5,3)); //One way from V4 to V5 of length 3
            this.getRoads().add(new Road(this,V5,V3,2)); //One way from V5 to V3 of length 2
            this.getRoads().add(new Road(this,V3,V1,4)); //One way from V5 to V3 of length 4
            this.getRoads().add(new Road(this,V6,V7,7)); //
            this.getRoads().add(new Road(this,V7,V6,7)); //
            this.getRoads().add(new Road(this,V7,V9,7)); //
            this.getRoads().add(new Road(this,V9,V8,7)); //
            this.getRoads().add(new Road(this,V8,V9,7)); //
            this.getRoads().add(new Road(this,V8,V6,7)); //
            this.getRoads().add(new Road(this,V6,V1,2)); //
            this.getRoads().add(new Road(this,V1,V6,2)); //
            this.getRoads().add(new Road(this,V7,V2,2)); //
            this.getRoads().add(new Road(this,V2,V7,2)); //
            this.getRoads().add(new Road(this,V9,V4,2)); //
            this.getRoads().add(new Road(this,V4,V9,2)); //
            this.getRoads().add(new Road(this,V8,V3,2)); //
            this.getRoads().add(new Road(this,V3,V8,2)); //

            //Create GUI Framework
            this.GUI = new GUI(this);
        }
    }
    public static class GUI extends Thread{
        //Swing GUI for User Interaction with a Region Simulation
        private JFrame main; //Main Window
        private Region R; //Region that has relavant Data
        private ArrayList<VillageIcon> VillageIcons; //Icons to Represent Villages
        private ArrayList<RoadIcon> RoadIcons; //Icons to Represent Roads
        private ArrayList<TravelAnimation> TravelIcons; //Icons to represent travelling Gnomes
        private static final int DEFAULTDIMENSION = 20; //sample dimension coordinate for scaling to screen
        private ViewGnomeListener GnomeViewer; //Gnome Position Info window
        //accessor and mutator methods
        public JFrame getMain() {return main;}
        private void setMain(JFrame main) {this.main = main;}
        public ArrayList<VillageIcon> getVillageIcons() {return VillageIcons;}
        public void setVillageIcons(ArrayList<VillageIcon> villageIcons) {VillageIcons = villageIcons;}
        public ViewGnomeListener getGnomeViewer() {return GnomeViewer;}
        public void setGnomeViewer(ViewGnomeListener gnomeViewer) {GnomeViewer = gnomeViewer;}

        public static class CreateVAction implements ActionListener{
            //ActionListener to Open Village Creation window
            private Region R;
            private JTextField Xtext;
            private JTextField Ytext;
            public synchronized void actionPerformed(ActionEvent e) {
                JFrame window = new JFrame();
                JLabel l = new JLabel("	Please enter the X and Y coordinates of the new Village:	");
                window.setTitle("Create a New Village");
                window.setLayout(new GridLayout(4,1));
                window.add(l);
                JPanel Xhold = new JPanel();
                JLabel Xl = new JLabel("X Coordinate:");
                Xhold.setBorder(BorderFactory.createRaisedBevelBorder());
                JTextField Xtext = new JTextField(4);
                this.Xtext = Xtext;
                Xhold.add(Xl);
                Xhold.add(Xtext);
                JPanel Yhold = new JPanel();
                JLabel Yl = new JLabel("Y Coordinate:");
                Xhold.setVisible(true);
                Yhold.setBorder(BorderFactory.createRaisedBevelBorder());
                JTextField Ytext = new JTextField(4);
                this.Ytext = Ytext;
                Yhold.add(Yl);
                Yhold.add(Ytext);
                Yhold.setVisible(true);

                window.add(Xhold);
                window.add(Yhold);
                window.setVisible(true);
                JButton enter = new JButton("Build Village");
                enter.addActionListener(new BuildAction(this));//
                window.add(enter);
                window.pack();
            }
            public static class BuildAction implements ActionListener{
                //ActionListener to Build desired Village
                private CreateVAction CVA;

                public void actionPerformed(ActionEvent e) {
                    try { //Try to Create with Desired Parameters
                        int x = Integer.parseInt(CVA.Xtext.getText());
                        int y = Integer.parseInt(CVA.Ytext.getText());
                        if (CVA.Xtext.getText().equals("")){throw new Exception();}
                        if (CVA.Ytext.getText().equals("")){throw new Exception();}
                        if (x < 0){throw new Exception();}
                        if (y < 0){throw new Exception();}

                        Village New = new Village(CVA.R);
                        New.setX_Coord(x);
                        New.setY_Coord(y);

                        this.CVA.R.GUI.VillageIcons.add(new VillageIcon(New, this.CVA.R.GUI));

                        JPanel b = new JPanel();
                        this.CVA.R.GUI.main.add(b);
                        this.CVA.R.GUI.main.remove(b);
                        Dimension d = this.CVA.R.GUI.main.getSize();
                        this.CVA.R.GUI.main.setSize(d.width+1,d.height);
                        this.CVA.R.GUI.main.setSize(d);
                        this.CVA.R.GUI.main.validate();
                    } catch (Exception E){ //Error window if Parameters are not valid
                        JFrame error = new JFrame();
                        error.setLayout(new GridLayout(2, 1));
                        error.setTitle("Input Error");
                        JButton close = new JButton("Ok");
                        JLabel errmess = new JLabel("Input Coordinates were Invalid.");
                        close.addActionListener(new closelistener(error));
                        error.add(errmess);
                        error.add(close);
                        error.setVisible(true);
                        error.pack();
                    }
                }
                public static class closelistener implements ActionListener{
                    //ActionListener to Close windows
                    private JFrame main;
                    public void actionPerformed(ActionEvent arg0) {
                        main.setVisible(false);
                        main.dispose();
                    }
                    public closelistener(JFrame main){
                        super();
                        this.main = main;
                    }
                }
                public BuildAction(CreateVAction CVA){
                    super();
                    this.CVA = CVA;
                }
            }
            public CreateVAction(Region R){
                super();
                this.R = R;
            }
        }
        public static class AddRoadListener implements ActionListener{
            //ActionListener to Open Road creation Dialog
            private Region R;
            private JTextField targetText;
            private JTextField sourceText;
            private JTextField lengthText;
            private JTextField tollText;

            public void actionPerformed(ActionEvent arg0) {
                JFrame NewRoad = new JFrame();
                NewRoad.setTitle("Create New Road");
                JTextField targetText = new JTextField(3);
                this.targetText = targetText;
                JTextField sourceText = new JTextField(3);
                this.sourceText = sourceText;
                JTextField lengthText = new JTextField(3);
                this.lengthText = lengthText;
                JTextField tollText = new JTextField(3);
                this.tollText = tollText;
                JLabel sourceL = new JLabel("Start Village Name:");
                JLabel targetL = new JLabel("End Village Name:");
                JLabel lengthL = new JLabel("Road Length:");
                JLabel tollL = new JLabel("Road Toll:");
                JButton build = new JButton("Build Road");
                build.addActionListener(new BuildRoadAction(this));

                JPanel T = new JPanel();
                JPanel S = new JPanel();
                JPanel L = new JPanel();
                JPanel TT = new JPanel();

                S.add(sourceL); S.add(sourceText);
                T.add(targetL); T.add(targetText);
                L.add(lengthL); L.add(lengthText);
                TT.add(tollL); TT.add(tollText);
                NewRoad.add(T);
                NewRoad.add(S);
                NewRoad.add(L);
                NewRoad.add(TT);
                NewRoad.add(build);
                NewRoad.setLayout(new GridLayout(5,1));
                NewRoad.setSize(200, 200);
                NewRoad.setVisible(true);
            }
            public AddRoadListener(Region R){
                super();
                this.R = R;
            }
            public static class BuildRoadAction implements ActionListener{
                //ActionListener to Attempt to Build a Road based on User Input
                private AddRoadListener ARL;

                public void actionPerformed(ActionEvent e) {
                    try{
                        Village Source = null;
                        Village Target = null;
                        for(Village v:this.ARL.R.Villages){ //Find Source Village
                            if(Integer.parseInt(this.ARL.sourceText.getText()) == v.getName()){
                                Source = v;
                                break;
                            }
                        }
                        for(Village v:this.ARL.R.Villages){ //Find Target Village
                            if(Integer.parseInt(this.ARL.targetText.getText()) == v.getName()){
                                Target = v;
                                break;
                            }
                        }
                        if(Target == Source){
                            throw new Exception(); //Invalid Arguments
                        }
                        int l = (int)Float.parseFloat(this.ARL.lengthText.getText());
                        if (l <= 0){
                            throw new Exception(); //Invalid Lengths
                        }
                        int t = (int)Float.parseFloat(this.ARL.tollText.getText());
                        if (t <= 0){
                            throw new Exception(); //Invalid Toll
                        }
                        Road road = new Road(this.ARL.R, Source, Target, l, t); //Build

                        //Create GUI Representation
                        this.ARL.R.GUI.RoadIcons.add(new RoadIcon(road,this.ARL.R.GUI));
                        Arrow a = new Arrow(this.ARL.R.GUI, (int)road.getSource().getX_Coord(),
                                (int)road.getSource().getY_Coord(),
                                (int)road.getTarget().getX_Coord(),
                                (int)road.getTarget().getY_Coord());
                        this.ARL.R.GUI.ADrawer.Arrows.add(a);
                        this.ARL.R.GUI.main.remove(this.ARL.R.GUI.ADrawer);
                        this.ARL.R.GUI.main.add(this.ARL.R.GUI.ADrawer);

                        //Ensure Window Renders Properly (validate() does not work)
                        Dimension d = this.ARL.R.GUI.main.getSize();
                        this.ARL.R.GUI.main.setSize(d.width+1,d.height);
                        this.ARL.R.GUI.main.setSize(d);

                    } catch (Exception E){
                        //Invalid Inputs Window
                        JFrame error = new JFrame();
                        error.setLayout(new GridLayout(2, 1));
                        error.setTitle("Input Error");
                        JButton close = new JButton("Ok");
                        JLabel errmess = new JLabel("Input Villages or Lengths was invalid.");
                        error.add(errmess);
                        error.add(close);
                        error.setVisible(true);
                        error.pack();
                    }

                }

                public BuildRoadAction(AddRoadListener ARL){
                    super();
                    this.ARL = ARL;
                }
            }
        }
        public static class RemoveRoadListener implements ActionListener{
            //ActionListener to Open Remove Road Dialog
            private Region R;
            private JTextField targetText;
            private JTextField sourceText;
            public void actionPerformed(ActionEvent arg0) {
                JFrame RemoveR = new JFrame();
                RemoveR.setLayout(new GridLayout(3,1));
                RemoveR.setTitle("Remove Road");
                JTextField targetText = new JTextField(3);
                this.targetText = targetText;
                JTextField sourceText = new JTextField(3);
                this.sourceText = sourceText;

                JLabel sourceL = new JLabel("Start Village Name:");
                JLabel targetL = new JLabel("End Village Name:");
                JPanel T = new JPanel();
                JPanel S = new JPanel();

                JButton remove = new JButton("Remove Road");
                remove.addActionListener(new RemoveRoadAction(this));
                T.add(targetL);	T.add(targetText);
                S.add(sourceL); S.add(sourceText);

                RemoveR.add(T);
                RemoveR.add(S);
                RemoveR.add(remove);

                RemoveR.setSize(200,150);
                RemoveR.setVisible(true);

            }
            public RemoveRoadListener(Region R){
                super();
                this.R = R;
            }
            public static class RemoveRoadAction implements ActionListener{
                //ActionListener to Attempt to Remove a Road
                private RemoveRoadListener RRL;

                public void actionPerformed(ActionEvent e) {
                    try {
                        Village Source = null;
                        Village Target = null;
                        for(Village v:this.RRL.R.Villages){ //Find Source Village
                            if(Integer.parseInt(this.RRL.sourceText.getText()) == v.getName()){
                                Source = v;
                                break;
                            }
                        }
                        for(Village v:this.RRL.R.Villages){ //Find Target Village
                            if(Integer.parseInt(this.RRL.targetText.getText()) == v.getName()){
                                Target = v;
                                break;
                            }
                        }

                        for (Road road:Source.getROutConnections()){//Find Road Object
                            if((road.getTarget().equals(Target))){
                                road.getSource().getROutConnections().remove(road);
                                road.getTarget().getRInConnections().remove(road);
                                this.RRL.R.Roads.remove(road); //Remove
                                this.RRL.R.GUI.main.remove(this.RRL.R.GUI.ADrawer); //Begin Redrawing Roads
                                this.RRL.R.GUI.ADrawer.Arrows = new ArrayList<Arrow>();
                                for (RoadIcon RI:this.RRL.R.GUI.RoadIcons){ //Find/Remove RoadIcon
                                    if (RI.R == road){
                                        this.RRL.R.GUI.RoadIcons.remove(RI);
                                        this.RRL.R.GUI.main.remove(RI.Icon);
                                        break;
                                    }
                                }
                                for (RoadIcon RI:this.RRL.R.GUI.RoadIcons){ //Redraw Roads
                                    int x1 = (int)RI.R.getSource().getX_Coord();
                                    int y1 = (int)RI.R.getSource().getY_Coord();
                                    int x2 = (int)RI.R.getTarget().getX_Coord();
                                    int y2 = (int)RI.R.getTarget().getY_Coord();
                                    this.RRL.R.GUI.ADrawer.Arrows.add(new Arrow(this.RRL.R.GUI,
                                            x1,y1,x2,y2));
                                }
                                this.RRL.R.GUI.main.add(this.RRL.R.GUI.ADrawer); //Redraw Roads

                                //Ensure main Renders properly (validate()) does not work
                                Dimension d = this.RRL.R.GUI.main.getSize();
                                this.RRL.R.GUI.main.setSize(d.width+1,d.height);
                                this.RRL.R.GUI.main.setSize(d);
                                break;
                            }
                        }
                    } catch (Exception E){ //Error Window
                        JFrame h = new JFrame();
                        h.setTitle("Error");
                        JPanel p = new JPanel();
                        p.add(new JLabel("Invalid Inputs:"));
                        p.add(new JLabel("Road could not be found"));
                        p.setBorder(BorderFactory.createRaisedBevelBorder());
                        h.add(p);
                        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                }
                public RemoveRoadAction(RemoveRoadListener R){
                    super();
                    this.RRL = R;
                }
            }
        }
        public static class EditVListener implements ActionListener{
            //ActionListener to Open Edit Village Dialog
            private Region R;
            private JTextField select;
            public void actionPerformed(ActionEvent arg0) {
                JFrame holder = new JFrame();
                holder.setLayout(new GridLayout(3,1));
                holder.setTitle("Edit Village Properties");
                JTextField VillageNum = new JTextField(4);
                this.select = VillageNum;
                JLabel L = new JLabel("Enter the Name of the Village");
                JLabel L2 =  new JLabel("you wish to edit:");
                L.setHorizontalAlignment(SwingConstants.CENTER);
                L2.setHorizontalAlignment(SwingConstants.CENTER);
                JPanel textPanel = new JPanel();
                textPanel.setLayout(new GridLayout(2,1));
                textPanel.setBorder(BorderFactory.createRaisedBevelBorder());
                textPanel.add(L);
                textPanel.add(L2);
                holder.add(textPanel);
                holder.add(VillageNum);
                JButton edit = new JButton("Edit");
                edit.addActionListener(new EVAction(this,holder));
                holder.add(edit);
                holder.setVisible(true);
                holder.pack();
            }
            public EditVListener(Region R){
                super();
                this.R = R;
            }
            public static class EVAction implements ActionListener{
                //ActionListener to Open Edit Village X Dialog
                private EditVListener EVL;
                private JFrame host;
                private JFrame window;
                private JTextField pop;
                private Village V;

                public void actionPerformed(ActionEvent e) {
                    this.host.dispose();
                    JFrame window = new JFrame();
                    this.window = window;
                    window.setLayout(new GridLayout(3,1));
                    for(Village v:this.EVL.R.Villages){
                        if(Integer.parseInt(this.EVL.select.getText()) == v.getName()){
                            this.V = v;
                            break;
                        }
                    }
                    window.setTitle("Edit Village "+V.getName());
                    JLabel size = new JLabel("Current Maximum Population is "+V.getMaxPop());
                    size.setHorizontalAlignment(SwingConstants.CENTER);
                    JTextField newpop = new JTextField(4);
                    JLabel editsize = new JLabel("Set Maximum Population: ");
                    editsize.setHorizontalAlignment(SwingConstants.CENTER);
                    this.pop = newpop;
                    JPanel holder = new JPanel();
                    holder.add(editsize);
                    holder.add(newpop);
                    window.add(size);
                    window.add(holder);
                    JButton change = new JButton("Save Changes");
                    window.add(change);
                    window.pack();
                    window.setVisible(true);
                    change.addActionListener(new SaveEditAction(this));

                }
                public EVAction (EditVListener EVL,JFrame old){
                    super();
                    this.host = old;
                    this.EVL = EVL;
                }
                public static class SaveEditAction implements ActionListener{
                    //ActionListener to Save Edits to Village Properties
                    private EVAction EVA;

                    public void actionPerformed(ActionEvent arg0) {
                        int newmaxpop = Integer.parseInt(EVA.pop.getText());
                        (this.EVA.V).setMaxPop(newmaxpop);
                        this.EVA.window.dispose();
                    }
                    public SaveEditAction(EVAction EVA){
                        super();
                        this.EVA = EVA;
                    }
                }

            }
        }
        public static class RemoveVAction implements ActionListener{
            //ActionListener to Remove a Village From the simulation
            private Region R;
            private JTextField select;

            public void actionPerformed(ActionEvent e) {
                JFrame holder = new JFrame();
                holder.setLayout(new GridLayout(3,1));
                holder.setTitle("Edit Village Properties");
                JTextField VillageNum = new JTextField(4);
                this.select = VillageNum;
                JLabel L = new JLabel("Enter the Name of the Village");
                JLabel L2 =  new JLabel("you wish to Remove:");
                L.setHorizontalAlignment(SwingConstants.CENTER);
                L2.setHorizontalAlignment(SwingConstants.CENTER);
                JPanel textPanel = new JPanel();
                textPanel.setLayout(new GridLayout(2,1));
                textPanel.setBorder(BorderFactory.createRaisedBevelBorder());
                textPanel.add(L);
                textPanel.add(L2);
                holder.add(textPanel);
                holder.add(VillageNum);
                JButton Remove = new JButton("Remove");
                Remove.addActionListener(new RVAction(this.R, this, holder));
                holder.add(Remove);
                holder.setVisible(true);
                holder.pack();
            }
            public RemoveVAction(Region R){
                super();
                this.R = R;
            }
            public static class RVAction implements ActionListener{
                //ActionListener to Open Remove Village Method Dialog
                private RemoveVAction RVA;
                private JFrame old;
                private Village V;
                private JFrame window;
                private Region R;

                public void actionPerformed(ActionEvent e) {
                    for(Village v:this.RVA.R.Villages){
                        if(Integer.parseInt(this.RVA.select.getText()) == v.getName()){
                            this.V = v;
                            break;
                        }
                    }
                    this.old.dispose();
                    JFrame choose = new JFrame();
                    this.window = choose;
                    choose.setLayout(new GridLayout(2,1));
                    choose.setTitle("Choose Remove Method");
                    JButton allroads = new JButton("Remove Village and its Connections");
                    JButton reroute = new JButton("Remove Village and reroute its Connections to preserve them");
                    choose.add(allroads);
                    allroads.addActionListener(new RemoveVAllRoads(this.R,this,this.V));
                    choose.add(reroute);
                    reroute.addActionListener(new RemoveVReroute(this.R,this,this.V));
                    choose.setVisible(true);
                    choose.pack();
                }
                public static class RemoveVReroute implements ActionListener{
                    private Village V;
                    private RVAction RVA;
                    private Region R;

                    public void actionPerformed(ActionEvent arg0) {
                        try{
                            if (R.getGnomes().size() != 0){
                                throw new Exception(); //Cannot Remove while Gnomes are pathfinding
                            }
                            this.RVA.window.dispose();
                            this.R.Villages.remove(this.V);
                            for(VillageIcon I:this.R.GUI.VillageIcons){ //Find+Remove Icons
                                if(I.V == this.V){
                                    this.R.GUI.main.remove(I.Icon);
                                    this.R.GUI.VillageIcons.remove(I);
                                    break;
                                }
                            }
                            //Reconnect Villages
                            for(Village Vin:this.V.getVInConnections()){
                                Road R = null;
                                Vin.getVOutConnections().remove(this.V);
                                for(Road road:this.V.getRInConnections()){
                                    if(road.getSource() == Vin){
                                        R = road;
                                        this.R.Roads.remove(road);
                                        for(RoadIcon RI: this.R.GUI.RoadIcons){
                                            if (RI.R == road){
                                                this.R.GUI.main.remove(RI.Icon);
                                                this.R.GUI.RoadIcons.remove(RI);
                                                break;
                                            }
                                        }
                                        road.getSource().getROutConnections().remove(road);
                                        break;
                                    }
                                }
                                int L1 = R.getLength();
                                int T1 = R.getToll();

                                for(Road roadout:this.V.getROutConnections()){
                                    Village Dest = roadout.getTarget();
                                    Dest.getVInConnections().remove(this.V);
                                    Dest.getRInConnections().remove(roadout);
                                    this.R.getRoads().remove(roadout);
                                    for(RoadIcon RI: this.R.GUI.RoadIcons){
                                        if (RI.R == roadout){
                                            this.R.GUI.main.remove(RI.Icon);
                                            this.R.GUI.RoadIcons.remove(RI);
                                            break;
                                        }
                                    }
                                    int LTotal = L1+roadout.getLength();
                                    int TTotal = T1+roadout.getToll();
                                    if (Vin!=Dest){
                                        Road New = new Road(this.R, Vin, Dest, LTotal, TTotal);
                                        this.R.getRoads().add(New);
                                        this.R.GUI.RoadIcons.add(new RoadIcon(New,this.R.GUI));
                                    }

                                }
                            }
                            //Redraw Roads
                            this.R.GUI.main.remove(this.R.GUI.ADrawer);
                            for(RoadIcon RI:this.R.GUI.RoadIcons){
                                this.R.GUI.ADrawer.Arrows.add(RI.arrow);
                            }
                            this.R.GUI.main.add(this.R.GUI.ADrawer);
                            this.R.GUI.main.setVisible(false);
                            this.R.GUI.main.validate();
                            this.R.GUI.main.setVisible(true);
                        } catch (Exception E){
                            JFrame error = new JFrame();
                            error.setLayout(new GridLayout(3, 1));
                            error.setTitle("Destruction Error");
                            JButton close = new JButton("Ok");
                            JLabel errmess = new JLabel("Cannot Remove Village While there");
                            JLabel errmess2 = new JLabel("are active Gnomes pathfinding.");
                            close.addActionListener(new closelistener(error));
                            error.add(errmess);
                            error.add(errmess2);
                            error.add(close);
                            error.setVisible(true);
                            error.pack();
                        }
                    }
                    public RemoveVReroute(Region R, RVAction RVA, Village V){
                        super();
                        this.R = R;
                        this.RVA = RVA;
                        this.V = V;
                    }
                    public static class closelistener implements ActionListener{
                        //Action Listener to Close the Window
                        private JFrame main;

                        public void actionPerformed(ActionEvent arg0) {
                            main.setVisible(false);
                            main.dispose();
                        }
                        public closelistener (JFrame main){
                            super();
                            this.main = main;
                        }
                    }
                }
                public static class RemoveVAllRoads implements ActionListener{
                    //ActionListener to Remove a Village with all its road connections
                    private Village V;
                    private RVAction RVA;
                    private Region R;

                    public void actionPerformed(ActionEvent e) {
                        try{
                            if (R.getGnomes().size() != 0){
                                throw new Exception(); //Cannot Remove while Gnomes are pathfinding
                            }
                            this.RVA.window.dispose();
                            this.R.Villages.remove(this.V);
                            for(VillageIcon I:this.R.GUI.VillageIcons){ //Find+Remove Icons
                                if(I.V == this.V){
                                    this.R.GUI.main.remove(I.Icon);
                                    this.R.GUI.VillageIcons.remove(I);
                                    break;
                                }
                            }
                            for(Road road:this.V.getROutConnections()){ //Find+Remove Roads and RoadIcons
                                road.getTarget().getRInConnections().remove(road);
                                road.getTarget().getVInConnections().remove(this.V);
                                for(RoadIcon RI: this.R.GUI.RoadIcons){
                                    if (RI.R == road){
                                        this.R.GUI.main.remove(RI.Icon);
                                        this.R.GUI.RoadIcons.remove(RI);
                                        break;
                                    }
                                }
                            }
                            for(Road road:this.V.getRInConnections()){ //Find+Remove Roads and RoadIcons
                                road.getSource().getROutConnections().remove(road);
                                road.getSource().getVOutConnections().remove(this.V);
                                for(RoadIcon RI: this.R.GUI.RoadIcons){
                                    if (RI.R == road){
                                        this.R.GUI.main.remove(RI.Icon);
                                        this.R.GUI.RoadIcons.remove(RI);
                                        break;
                                    }
                                }
                            }
                            //Redraw Roads
                            this.R.GUI.main.remove(this.R.GUI.ADrawer);
                            for(RoadIcon RI:this.R.GUI.RoadIcons){
                                this.R.GUI.ADrawer.Arrows.add(RI.arrow);
                            }
                            this.R.GUI.main.add(this.R.GUI.ADrawer);
                            this.R.GUI.main.setVisible(false);
                            this.R.GUI.main.validate();
                            this.R.GUI.main.setVisible(true);
                        } catch (Exception E){ //Attempt to Remove while Gnomes are active
                            JFrame error = new JFrame();
                            error.setLayout(new GridLayout(3, 1));
                            error.setTitle("Destruction Error");
                            JButton close = new JButton("Ok");
                            JLabel errmess = new JLabel("Cannot Remove Village While there");
                            JLabel errmess2 = new JLabel("are active Gnomes pathfinding.");
                            close.addActionListener(new closelistener(error));
                            error.add(errmess);
                            error.add(errmess2);
                            error.add(close);
                            error.setVisible(true);
                            error.pack();
                        }
                    }
                    public static class closelistener implements ActionListener{
                        //Action Listener to Close error window
                        private JFrame main;
                        public void actionPerformed(ActionEvent arg0) {
                            main.setVisible(false);
                            main.dispose();
                        }
                        public closelistener(JFrame main){
                            super();
                            this.main = main;
                        }
                    }
                    public RemoveVAllRoads(Region R, RVAction RVA,Village V){
                        super();
                        this.V = V;
                        this.RVA = RVA;
                        this.R = R;
                    }
                }
                public RVAction(Region R, RemoveVAction RVA,JFrame J){
                    super();
                    this.RVA = RVA;
                    this.old = J;
                    this.R = R;
                }
            }
        }
        public static class ViewGnomeListener extends Thread implements ActionListener{
            //Action Listener to Open Constantly Updating Gnome Info Window
            private Region R;
            private JFrame holder;
            private JPanel infoholder;
            private boolean go = true;

            public void actionPerformed(ActionEvent arg0) {
                this.go = true;
                JFrame holder = new JFrame();
                this.infoholder = new JPanel();
                this.holder = holder;
                holder.setTitle("Gnomes");
                JButton close = new JButton("Push to Close"); //Prevent from Updating
                infoholder.add(close);
                close.addActionListener(new closelistener(this,this.holder));
                holder.setLayout(new GridLayout(0,1));
                infoholder.setLayout(new GridLayout(0,1));
                for (Gnome G:this.R.getGnomes()){ //Add Gnome info to Window
                    JPanel Ghold = new JPanel();
                    Ghold.setBorder(BorderFactory.createRaisedBevelBorder());
                    ColoredJPanel nhold = new ColoredJPanel(Color.BLUE);
                    nhold.setBorder(BorderFactory.createRaisedBevelBorder());
                    JLabel n = new JLabel(G.name);
                    JLabel loc = new JLabel(G.getLocation().toString());
                    nhold.add(n);
                    Ghold.add(nhold);
                    Ghold.add(loc);
                    infoholder.add(Ghold);
                }
                holder.add(infoholder);
                holder.pack();
                holder.setVisible(true);
                this.R.GUI.setGnomeViewer(this);
                this.start(); //Start Updates

            }
            public static class closelistener implements ActionListener{
                //Action Listener to Close the Window
                private JFrame main;
                private ViewGnomeListener VGL;
                public void actionPerformed(ActionEvent arg0) {
                    main.setVisible(false);
                    this.VGL.R.GUI.GnomeViewer = null;
                    main.dispose();
                    this.VGL.go = false;
                }
                public closelistener(ViewGnomeListener VGL, JFrame main){
                    super();
                    this.VGL = VGL;
                    this.main = main;
                }
            }
            public void run(){ //Run Update Method every 2 sec
                while(this.go){
                    try {
                        sleep(2000);
                        this.update();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            public synchronized void update(){ //Update Info
                this.holder.remove(this.infoholder);
                this.infoholder = new JPanel();
                JButton close = new JButton("Push to Close"); //Prevent from Updating and Close it
                infoholder.add(close);
                close.addActionListener(new closelistener(this,this.holder));
                this.infoholder.setLayout(new GridLayout(0,1));
                for (Gnome G:this.R.getGnomes()){
                    JPanel Ghold = new JPanel();
                    Ghold.setBorder(BorderFactory.createRaisedBevelBorder());
                    ColoredJPanel nhold = new ColoredJPanel(Color.BLUE);
                    nhold.setBorder(BorderFactory.createRaisedBevelBorder());
                    JLabel n = new JLabel(G.name);
                    JLabel loc = new JLabel(G.getLocation().toString());
                    nhold.add(n);
                    Ghold.add(nhold);
                    Ghold.add(loc);
                    this.infoholder.add(Ghold);
                }
                this.holder.add(this.infoholder);
                this.holder.validate();
                this.holder.pack();
                this.holder.setVisible(true);
                this.holder.pack();
            }
            public ViewGnomeListener(Region R){
                super();
                this.R = R;
            }
        }
        public static class NewNormGnomeListener implements ActionListener{
            //Action Listener to Open Normal Create Gnome Dialog
            private Region R;
            private JTextField name;
            private JTextField vStart;
            private JTextField vDest;
            private JTextField Urgency;
            private JCheckBox box;

            public void actionPerformed(ActionEvent arg0) {
                JFrame holder = new JFrame();
                holder.setTitle("Create New Gnome");
                this.name = new JTextField(7);
                this.vStart = new JTextField(3);
                this.vDest = new JTextField(3);
                this.Urgency = new JTextField(3);
                JLabel nameL = new JLabel("Name: ");
                JLabel startL = new JLabel("Start Village: ");
                JLabel vDestL = new JLabel("Destination: ");
                JPanel nameP = new JPanel();
                JPanel vStartP = new JPanel();
                JPanel vDestP = new JPanel();
                JPanel UrgencyP = new JPanel();
                nameP.add(nameL);nameP.add(name);
                vStartP.add(startL);vStartP.add(vStart);
                vDestP.add(vDestL);vDestP.add(vDest);
                holder.add(nameP);
                holder.add(vStartP);
                holder.add(vDestP);
                JLabel runInfo = new JLabel("Start Gnome After Creation?");
                this.box = new JCheckBox();
                JPanel runholder = new JPanel();
                runholder.add(runInfo);
                runholder.add(box);
                holder.add(runholder);
                JButton build = new JButton("Create");
                build.addActionListener(new NNGAction(this));
                holder.add(build);
                holder.setLayout(new GridLayout(0,1));
                holder.setVisible(true);
                holder.pack();
            }
            public static class NNGAction implements ActionListener{
                //Action Listener to Attempt to create a Normal Pathing Gnome
                private NewNormGnomeListener NGL;
                public void actionPerformed(ActionEvent e) {
                    try{
                        String name = this.NGL.name.getText();
                        int start = Integer.parseInt(this.NGL.vStart.getText());
                        Village StartV = null;
                        for(Village V: this.NGL.R.getVillages()){
                            if(V.getName() == start){
                                StartV = V;
                                break;
                            }
                        }
                        int dest = Integer.parseInt(this.NGL.vDest.getText());
                        Village DestV = null;
                        for(Village V: this.NGL.R.getVillages()){
                            if(V.getName() == dest){
                                DestV = V;
                                break;
                            }
                        }
                        boolean go = this.NGL.box.isSelected();
                        Gnome G = new Gnome(this.NGL.R, name, StartV, DestV);
                        StartV.getLocalGnomes().add(G);
                        G.setLocation(StartV);
                        this.NGL.R.Gnomes.add(G);
                        if(go){
                            G.start();
                        }
                        JFrame success = new JFrame();
                        success.setLayout(new GridLayout(2,1));
                        JPanel hold = new JPanel();
                        hold.setBorder(BorderFactory.createRaisedBevelBorder());
                        JLabel L = new JLabel("Gnome Created Successfully");
                        L.setHorizontalAlignment(SwingConstants.CENTER);
                        JButton ok = new JButton("Close");
                        ok.addActionListener(new closelistener(success));
                        success.add(hold); hold.add(L);
                        success.add(ok);
                        success.pack();
                        success.setVisible(true);
                    } catch (Exception E){ //Show error Window
                        JFrame fail = new JFrame();
                        fail.setLayout(new GridLayout(2,1));
                        fail.setTitle("Error");
                        JPanel hold = new JPanel();
                        hold.setBorder(BorderFactory.createRaisedBevelBorder());
                        JLabel mes = new JLabel("Invalid Inputs");
                        mes.setHorizontalAlignment(SwingConstants.CENTER);
                        fail.add(hold); hold.add(mes);
                        JButton ok = new JButton("Close");
                        ok.addActionListener(new closelistener(fail));
                        fail.add(ok);
                        fail.pack();
                        fail.setVisible(true);
                    }
                }
                public NNGAction(NewNormGnomeListener NGL) {
                    super();
                    this.NGL = NGL;
                }
                public static class closelistener implements ActionListener{
                    //Action Listener to Close the Error Window
                    private JFrame main;
                    public void actionPerformed(ActionEvent arg0) {
                        main.setVisible(false);
                        main.dispose();
                    }
                    public closelistener(JFrame main){
                        super();
                        this.main = main;
                    }
                }
            }
            public NewNormGnomeListener(Region R){
                super();
                this.R = R;
            }
        }

                public static class closelistener implements ActionListener{
                    //Action Listener to close Error/success window
                    private JFrame main;
                    public void actionPerformed(ActionEvent arg0) {
                        main.setVisible(false);
                        main.dispose();
                    }
                    public closelistener(JFrame main){
                        super();
                        this.main = main;
                    }
                }
            }
        }
//
        public GUI(Region R){ //GUI Constructor
            this.setVillageIcons(new ArrayList<VillageIcon>());
            this.RoadIcons = new ArrayList<RoadIcon>();
            this.TravelIcons = new ArrayList<TravelAnimation>();
            this.R = R;
            JFrame main = new JFrame();
            main.setTitle("GnomenWald");

            //Build the Menus and add their Action Listeners
            JMenuBar menubar = new JMenuBar();

            //Village Menu
            JMenu Village_menu = new JMenu("Villages");
            JMenuItem Create = new JMenuItem("Create New Village");
            Create.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_V, ActionEvent.CTRL_MASK));
            Create.addActionListener(new CreateVAction(this.R));
            JMenuItem Remove = new JMenuItem("Remove Existing Village");
            Remove.addActionListener(new RemoveVAction(this.R));
            JMenuItem Edit = new JMenuItem("Edit Existing Village");
            Edit.addActionListener(new EditVListener(this.R));
            Village_menu.add(Create);
            Village_menu.add(Remove);
            Village_menu.add(Edit);
            menubar.add(Village_menu);

            //Road Menu
            JMenu Road_menu = new JMenu("Roads");
            JMenuItem CreateR = new JMenuItem("Create New Road");
            CreateR.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_V, ActionEvent.CTRL_MASK));
            CreateR.addActionListener(new AddRoadListener(this.R));
            JMenuItem RemoveR = new JMenuItem("Remove Existing Road");
            RemoveR.addActionListener(new RemoveRoadListener(this.R));
            JMenuItem EditR = new JMenuItem("Edit Existing Road");
            Road_menu.add(CreateR);
            Road_menu.add(RemoveR);
            Road_menu.add(EditR);
            menubar.add(Road_menu);

            //Gnome Menu
            JMenu Gnome_menu = new JMenu("Gnomes");
            JMenuItem ViewG = new JMenuItem("View Gnomes");
            ViewG.addActionListener(new ViewGnomeListener(this.R));
            JMenu CreateG = new JMenu("Create New Gnome");
            JMenuItem CreateNormG = new JMenuItem("Standard Gnome");
            CreateNormG.addActionListener(new NewNormGnomeListener(this.R));
            JMenuItem RemoveG = new JMenuItem("Remove Existing Gnome");
            JMenuItem StartG = new JMenuItem("Start Inactive Gnomes");
            CreateG.add(CreateNormG);
            Gnome_menu.add(ViewG);
            Gnome_menu.add(CreateG);
            Gnome_menu.add(RemoveG);
            Gnome_menu.add(StartG);
            menubar.add(Gnome_menu);

            main.setJMenuBar(menubar);
            main.setVisible(true); //Show GUI

            main.setSize(1200, 800);
            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setMain(main);
        }
        public static class VillageIcon{
            //Represents a Village in the GUI
            private int VillageID;
            private Village V;
            private JButton Icon;
            private VillageInfoWindow VInfoWindow;

            public VillageInfoWindow getVInfoW(){return this.VInfoWindow;}
            public Village getV() {return this.V;}
            private void setV(Village v) {this.V = v;}

            public VillageIcon(Village V,GUI G){
                synchronized(this){ //Ensure Villages are being modified elsewhere (ie. Rand Village Gen)
                    this.setV(V);
                    this.VillageID = V.getId();
                    this.Icon = new JButton();
                    JLabel L = new JLabel(""+V.getName(), JLabel.CENTER);
                    this.Icon.add(L);
                    L.setVisible(true);
                    G.main.add(this.Icon);
                    this.Icon.setBounds((int)V.getX_Coord(), (int)V.getY_Coord(), (int)(DEFAULTDIMENSION*2), (int)(DEFAULTDIMENSION*2));
                    this.Icon.setLayout(new GridLayout(1,1));
                    this.Icon.setMaximumSize(new Dimension((int)(DEFAULTDIMENSION*2),(int)(DEFAULTDIMENSION*2)));

                    this.Icon.addActionListener(new VillageListener(this)); //Opens VInfoWindow
                    this.Icon.setVisible(true);
                }
            }
            public static class VillageListener implements ActionListener{
                //Action Listener to Open Village Info Window
                @SuppressWarnings("unused")
                private boolean open; //Prevents Mulitple Copies opening
                private VillageIcon V;
                public void actionPerformed(ActionEvent e) {
                    this.V.VInfoWindow = new VillageInfoWindow(this,this.V);
                }
                public VillageListener(VillageIcon V){
                    super();
                    this.V = V;
                    this.open = false;
                }
            }
        }
        public static class VillageInfoWindow extends Thread{
            //GUI to show info about a Village
            private VillageIcon VIcon;
            private Village V;
            private JFrame holder;
            private JPanel GnomeList;

            public VillageInfoWindow(VillageListener VL, VillageIcon VIcon){//Constructor
                this.VIcon = VIcon;
                this.V = VIcon.getV();
                JFrame holder = new JFrame();

                holder.setTitle("Village "+(this.VIcon.VillageID+1));
                holder.setSize(400,200);
                holder.setLayout(new GridLayout(1,2));
                JMenuBar menubar = new JMenuBar();
                JMenu menu = new JMenu("Village Options");

                JMenuItem menuItem = new JMenuItem("Remove Village");
                menuItem.addActionListener(new RemoveVAction(this.V.getR()));
                menu.add(menuItem);

                menubar.add(menu);
                holder.setJMenuBar(menubar);
                holder.setVisible(true);
                JPanel GnomeList = new JPanel();
                this.GnomeList = GnomeList;
                JLabel header = new JLabel("Gnomes Here: \nMax Population: "+this.V.getMaxPop());
                JPanel headerH = new JPanel();
                headerH.add(header);
                GnomeList.add(headerH);
                headerH.setBorder(BorderFactory.createRaisedBevelBorder());
                if (!this.V.getLocalGnomes().isEmpty()){ //Add Info About Gnomes
                    for (Gnome G: this.V.getLocalGnomes()){
                        JLabel nametag = new JLabel(G.name);
                        nametag.setVisible(true);
                        JPanel nameH = new JPanel();
                        nameH.add(nametag);
                        nameH.setVisible(true);
                        GnomeList.add(nameH);
                    }
                } else {
                    JLabel empty = new JLabel("There are no Gnomes currently here");
                    GnomeList.add(empty);
                }
                GnomeList.setVisible(true);
                holder.add(GnomeList);
                holder.pack();
                this.holder = holder;
            }
            public void update(){ //Update Information about Local Gnomes
                JPanel GnomeListNew = new JPanel();
                JLabel header = new JLabel("Gnomes Here: \nMax Population: "+this.V.getMaxPop());
                JPanel headerH = new JPanel();
                headerH.add(header);
                GnomeListNew.add(headerH);
                headerH.setBorder(BorderFactory.createRaisedBevelBorder());
                if (!this.V.getLocalGnomes().isEmpty()){
                    for (Gnome G: this.V.getLocalGnomes()){
                        JLabel nametag = new JLabel(G.name);
                        nametag.setVisible(true);
                        JPanel nameH = new JPanel();
                        nameH.add(nametag);
                        nameH.setVisible(true);
                        GnomeListNew.add(nameH);
                    }
                } else {
                    JLabel empty = new JLabel("0 Gnomes");
                    GnomeListNew.add(empty);
                }
                GnomeListNew.setVisible(true);
                this.holder.remove(GnomeList);
                this.GnomeList = GnomeListNew;
                this.holder.add(GnomeList);
                this.holder.pack();
            }

        }
        public static class RoadIcon{
            //Class to Represent a Road in the GUI
            private Road R; //Road it represents
            private JButton Icon; //Icon to hold Length
            private Arrow arrow; //Visual in GUI.ADrawer

            public Road getR() {return this.R;}
            private void setR(Road r) {this.R = r;}

            public RoadIcon(Road R, GUI G){ //Constructor
                synchronized(this){
                    this.setR(R);
                    this.Icon = new JButton(R.getLength()+"");
                    int X = (int)((R.getSource().getX_Coord()+R.getTarget().getX_Coord())/2.0);
                    int Y = (int)((R.getSource().getY_Coord()+R.getTarget().getY_Coord())/2.0);
                    this.Icon.setBounds(X+DEFAULTDIMENSION/2,Y+DEFAULTDIMENSION/2, DEFAULTDIMENSION, DEFAULTDIMENSION);
                    //Make arrow icon
                    this.arrow = new Arrow(G,(int)(R.getSource().getX_Coord()),(int)(R.getSource().getY_Coord()),
                            (int)(R.getTarget().getX_Coord()),(int)(R.getTarget().getY_Coord()));
                    G.main.add(this.Icon);
                }
            }
        }
        public static class ColoredJPanel extends JPanel{
            //Colored JPanel Object for Visual Use
            Color color;
            public ColoredJPanel(Color color){
                this.color = color;
            }
            public void paintComponent(Graphics g){
                g.setColor(color);
                g.fillRect(0, 0, 400, 400);
            }
        }


        //CUREEENTLY WORKING ON
        public static class TravelAnimation extends Thread{
            //Animation to Represent a Traveling Gnome
            private ColoredJPanel GnomeIcon;
            private Gnome G;
            private Road R;
            private GUI Gui;
            private double duration;
            private double currentTime = 0;

            public TravelAnimation(GUI GUI, Gnome G, Road R){//Constructor
                synchronized(GUI.TravelIcons){
                    GUI.TravelIcons.add(this); //Prevent Concurrent Exceptions
                }
                this.G = G;
                this.R = R;
                this.Gui = GUI;
                this.duration = R.getLength()*1000+10000-(long)1000;
                this.GnomeIcon = new ColoredJPanel(Color.BLUE);
                this.GnomeIcon.setBorder(BorderFactory.createRaisedBevelBorder());
                this.GnomeIcon.setSize(Villagemap.GUI.DEFAULTDIMENSION/4,Villagemap.GUI.DEFAULTDIMENSION/4);

                this.GnomeIcon.setVisible(true);
            }
            public void run(){ //Thread run() method
                int xdist = (int) R.getTarget().getX_Coord()-(int)R.getSource().getX_Coord();
                int ydist = (int) R.getTarget().getY_Coord()-(int)R.getSource().getY_Coord();

                while(this.duration > this.currentTime){
                    if (!this.G.isPaused()){
                        this.GnomeIcon.setLocation((int)(R.getSource().getX_Coord()+GUI.DEFAULTDIMENSION*(2.7/4)+xdist/this.duration*this.currentTime),
                                (int)(R.getSource().getY_Coord()+GUI.DEFAULTDIMENSION*(2.7/4)+ydist/this.duration*this.currentTime));
                        try {
                            sleep(10); //pause motion
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        this.currentTime += 10;//increment time
                    }
                    Point d = this.GnomeIcon.getLocation();
                    this.GnomeIcon.setLocation(d); //Prevent Resize errors (Internal to OSX swing)
                }
                this.GnomeIcon.setVisible(false);
            }
        }


        //********CURRENTLY WORKING ON
        public static class Arrow {
            //Object to hold point information to draw arrows representing a road in the GUI
            private double phi;
            private int head_X;
            private int head_Y;
            private int tail_X;
            private int tail_Y;
            private int [] point1;
            private int [] point2;
            private int [] point3;

            public Arrow(GUI G, int x1, int y1, int x2, int y2) { //Constructor
            }

        }
        public void Initalize() throws InterruptedException{
            //Method to Intitalize the GUI after Region has been Constructed
            //Make Village Icons
            for (int i = 0; i < this.R.Villages.size(); i++){
                Village V = this.R.Villages.get(i);
                VillageIcon I = new VillageIcon(V,this);
                this.getVillageIcons().add(I);
            }
            //Make Road Icons
            for (int i = 0; i < this.R.Roads.size(); i++){
                Road r = this.R.Roads.get(i);
                RoadIcon I = new RoadIcon(r,this);
                this.RoadIcons.add(I);
            }

            JPanel b = new JPanel();

            this.main.add(b);
            this.main.remove(b);
            this.main.validate();
        }
    }
}
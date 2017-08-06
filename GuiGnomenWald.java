package gui_demos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.Timer;

import q.*;

/**
 * GUI for GnomenWald.
 */
public class GuiGnomenWald extends JComponent {
 
	private static final long serialVersionUID = 1L;
	public static final int WIDE = 640;
    public static final int HIGH = 480;
    public static final int RADIUS = 10;
    private static final Random rnd = new Random();
    private ControlPanel control = new ControlPanel();
    private List<GuiVillage> villages = new ArrayList<GuiVillage>();
    private List<GuiVillage> selected = new ArrayList<GuiVillage>();
    private Point mousePt = new Point(WIDE / 2, HIGH / 2);
    private Rectangle mouseRect = new Rectangle();
    private boolean selecting = false;
    
    
    public static void main(String[] args) throws Exception {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                JFrame f = new JFrame("GnomenWald");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                GuiGnomenWald gp = new GuiGnomenWald();
                f.add(gp.control, BorderLayout.NORTH);
                f.add(new JScrollPane(gp), BorderLayout.CENTER);
                f.pack();
                f.setLocationByPlatform(true);
                f.setVisible(true);
                
        		new Timer(200, new ActionListener() {			
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	gp.repaint();
                    }
                }).start();
            }
        });
    }

    public GuiGnomenWald() {
        this.setOpaque(true);
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseMotionHandler());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDE, HIGH);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(new Color(0x00f0f0f0));
        g.fillRect(0, 0, getWidth(), getHeight());
        for (Road road : GnomenWald.Gnomenwald.getAllRoads()) {
            DrawRoad.drawRoad((Graphics2D) g, road);
        }
        for (Village v : GnomenWald.Gnomenwald.getAllVillages()) {
            GuiVillage n = (GuiVillage) v;
            n.draw(g);
        }
        if (selecting) {
            g.setColor(Color.darkGray);
            g.drawRect(mouseRect.x, mouseRect.y,
                mouseRect.width, mouseRect.height);
        }
        List<Gnome> gnomes = Census.instance.getPopulation();
        for(int i = 0; i < gnomes.size(); i++) {
        	GuiGnome gnome = (GuiGnome) gnomes.get(i);
        	gnome.draw((Graphics2D)g);
        }
    }

    private class MouseHandler extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            selecting = false;
            mouseRect.setBounds(0, 0, 0, 0);
            if (e.isPopupTrigger()) {
                showPopup(e);
            }
            e.getComponent().repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            mousePt = e.getPoint();
            if (e.isShiftDown()) {
                GuiVillage.selectToggle(villages, mousePt);
            } else if (e.isPopupTrigger()) {
                GuiVillage.selectOne(villages, mousePt);
                showPopup(e);
            } else if (GuiVillage.selectOne(villages, mousePt)) {
                selecting = false;
                for (int i = 0; i < villages.size(); i++) {
                	GuiVillage village = villages.get(i);
                	if(village.isSelected()) {
                		System.out.println(village);
                	}
                }
            } else {
                GuiVillage.selectNone(villages);
                selecting = true;
            }
            e.getComponent().repaint();
        }

        private void showPopup(MouseEvent e) {
            control.popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    private class MouseMotionHandler extends MouseMotionAdapter {

        Point delta = new Point();

        @Override
        public void mouseDragged(MouseEvent e) {
            if (selecting) {
                mouseRect.setBounds(
                    Math.min(mousePt.x, e.getX()),
                    Math.min(mousePt.y, e.getY()),
                    Math.abs(mousePt.x - e.getX()),
                    Math.abs(mousePt.y - e.getY()));
                GuiVillage.selectRect(villages, mouseRect);
            } else {
                delta.setLocation(
                    e.getX() - mousePt.x,
                    e.getY() - mousePt.y);
                GuiVillage.updatePosition(villages, delta);
                mousePt = e.getPoint();
            }
            e.getComponent().repaint();
        }
    }

    public JToolBar getControlPanel() {
        return control;
    }

    private class ControlPanel extends JToolBar {

		private static final long serialVersionUID = 1L;
		private Action newNode = new NewNodeAction("New Village");
        private Action clearAll = new ClearAction("Clear");
        private Action connect = new ConnectAction("Create Roads");
        private Action loadMap = new LoadMapAction("Load Map");
        private Action findGnome = new FindGnomeAction("All Gnomes");

        private JPopupMenu popup = new JPopupMenu();
        
        ControlPanel() {
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            this.setBackground(Color.lightGray);

            this.add(new JButton(clearAll));
            this.add(new JButton(loadMap));
            this.add(new JButton(findGnome));

            popup.add(new JMenuItem(newNode));
            popup.add(new JMenuItem(connect));
            
            popup.add(new AbstractAction("New Gnome") {

				private static final long serialVersionUID = 1L;

				@Override
				public void actionPerformed(ActionEvent e) {
					GuiVillage.getSelected(villages, selected);
					if(selected.size() > 0){
						GuiVillage village = selected.get(0);
						if (village.getCapacity() == village.getGnomeCount()) {
							System.out.println("Capacity full, no gnome created");
							return;
						}
						try {
							GuiGnome gnome = new GuiGnome(village);
							System.out.println("Gnome " + gnome + " created!");
						} catch (VipStatusException e1) {
							e1.printStackTrace();
						}
					}
					
				}
            	
            });
            
            popup.add(new AbstractAction("Walk urgently to") {
				private static final long serialVersionUID = 1L;

				@Override
				public void actionPerformed(ActionEvent e) {
					GuiVillage.getSelected(villages, selected);
					if (selected.size() != 1) {
						return;	
					}
					Village dest = selected.get(0);
					List<Gnome> gnomes = Census.instance.getPopulation();
					for(int i = 0; i < gnomes.size(); i++) {
						Gnome temp = gnomes.get(i);
						if (temp.getCurrentLocation() instanceof Village) {
							temp.goToVillage(dest, 1);
						}
					}
					
				}
            	
            });
            
            popup.add(new AbstractAction("Walk lazily to") {
				private static final long serialVersionUID = 1L;

				@Override
				public void actionPerformed(ActionEvent e) {
					GuiVillage.getSelected(villages, selected);
					if (selected.size() != 1) {
						return;	
					}
					Village dest = selected.get(0);
					List<Gnome> gnomes = Census.instance.getPopulation();
					for(int i = 0; i < gnomes.size(); i++) {
						Gnome temp = gnomes.get(i);
						if (temp.getCurrentLocation() instanceof Village) {
							temp.goToVillage(dest, 0);
						}
					}
					
				}
            	
            });
            
            popup.add(new AbstractAction("Walk randomly") {
				private static final long serialVersionUID = 1L;

				@Override
				public void actionPerformed(ActionEvent e) {
					//GuiVillage.getSelected(villages, selected);
//					if(travelers.size() <= 0)
//						return;
					for (Gnome gnome : Census.instance.getPopulation())
						gnome.walkRandomly();
				}
            	
            });
            
            popup.add(new AbstractAction("Delete Village") {
				private static final long serialVersionUID = 1L;

				@Override
				public void actionPerformed(ActionEvent e) {
					GuiVillage.getSelected(villages, selected);
					if (selected.size() <= 0)
						return;
					GuiVillage village = selected.get(0);
					if (village.getGnomeCount() > 0) {
						System.out.println("Can't delete with inhabitants!");
						return;
					}
					GnomenWald.Gnomenwald.deleteVillage1(village);
				}
            	
            });
            
            popup.add(new AbstractAction("Delete Village and Reconnect") {
				private static final long serialVersionUID = 1L;

				@Override
				public void actionPerformed(ActionEvent e) {
					GuiVillage.getSelected(villages, selected);
					if (selected.size() <= 0)
						return;
					GuiVillage village = selected.get(0);
					if (village.getGnomeCount() > 0) {
						System.out.println("Can't delete with inhabitants!");
						return;
					}
					GnomenWald.Gnomenwald.deleteVillage2(village);
				}
            	
            }); 
        }
    }
    
    private class ClearAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public ClearAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
        	GnomenWald.Gnomenwald.clearAll();
            villages.clear();
            repaint();
        }
    }

    private class ConnectAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public ConnectAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
            GuiVillage.getSelected(villages, selected);
            GuiVillage src = GuiVillage.getSource();
            if(src == null) {
            	return;
            }
            if (selected.size() > 1) {
                for (int i = 0; i < selected.size(); ++i) {
                    GuiVillage to = selected.get(i);
                    if (!src.equals(to)) {
                    	Road road = GnomenWald.Gnomenwald.createRoad(src, to);
                        System.out.println("Road: " + road + " created!");
                    }
                }
            }
            repaint();
        }
    }
    
    private class NewNodeAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

		public NewNodeAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
            GuiVillage.selectNone(villages);
            Point p = mousePt.getLocation();
            GuiVillage village = new GuiVillage(p, new Color(rnd.nextInt()));
            GnomenWald.Gnomenwald.addVillage(village);
            System.out.println("Village " + village + " created!");

            village.setSelected(true);
            villages.add(village);
            repaint();
        }
    }
    
    public class FindGnomeAction extends AbstractAction{
    	
		private static final long serialVersionUID = 1L;
		public FindGnomeAction(String name) {
            super(name);
        }
    	public void actionPerformed(ActionEvent e) {
			List<Gnome> gnomes = Census.instance.getPopulation();
			if(gnomes.size() == 0) {
				System.out.println("No gnomes on map");
				return;	
			}
    		for (int i = 0; i < gnomes.size(); i++) {
    			Gnome temp = gnomes.get(i);
    			System.out.println(temp.getName());
    			for(int j = 0; j < temp.history().size(); j++) {
    				System.out.println(temp.history().get(j));
    			}
    		}
    	}
    	
//        public void actionPerformed(ActionEvent e) {
//        	if (searchById) {
//        		GuiGnome gnome = Census.instance.searchID(id);
//	        	if (gnome != null) {
//	        		System.out.println(gnome + " location:" + gnome.getCurrentLocation() +);
//	        		System.out.println("history:" + gnome.history());
//	        	} else { System.out.println("Gnome not found"); }
//        	}
//        	else if (searchByName) {
//        		LinkedList<GuiGnome> gnomes = Census.instance.searchByName(name);
//        		if (gnomes.size() > 0) {
//        			System.out.println("Gnomes with " + "'" + name + "'" + " name: " + gnomes);
//        		} else { System.out.println("Gnomes not found"); }
//        	}
//        	else if (searchByColor) {
//        		LinkedList<GuiGnome> gnomes = Census.instance.searchByColor(color);
//        		if (gnomes.size() > 0) {
//        			System.out.println("Gnomes with " + "'" + color + "'" + " color: " + gnomes);
//        		} else { System.out.println("Gnomes not found"); }
//        	}
//        	else if (searchByVIP) {
//        		LinkedList<GuiGnome> gnomes = Census.instance.searchByVIP(VIP);
//        		if (gnomes.size() > 0) {
//        			System.out.println("Gnomes with " + "'" + VIP + "'" + " VIP: " + gnomes);
//        		} else { System.out.println("Gnomes not found"); }
//        	} 	
//        }
    }

    public class LoadMapAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public LoadMapAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
        	
        	GnomenWald.Gnomenwald.start();
        	
        	GuiVillage a = new GuiVillage(new Point (rnd.nextInt(getWidth()), rnd.nextInt(getHeight())), new Color(rnd.nextInt()));
        	GuiVillage b = new GuiVillage(new Point (rnd.nextInt(getWidth()), rnd.nextInt(getHeight())), new Color(rnd.nextInt()));
        	GuiVillage c = new GuiVillage(new Point (rnd.nextInt(getWidth()), rnd.nextInt(getHeight())), new Color(rnd.nextInt()));
        	GuiVillage d = new GuiVillage(new Point (rnd.nextInt(getWidth()), rnd.nextInt(getHeight())), new Color(rnd.nextInt()));
        	GuiVillage f = new GuiVillage(new Point (rnd.nextInt(getWidth()), rnd.nextInt(getHeight())), new Color(rnd.nextInt()));
        	
       		villages.add(a);
            villages.add(b);
            villages.add(c);
            villages.add(d);
            villages.add(f);
            GnomenWald.Gnomenwald.addVillage(a);
            GnomenWald.Gnomenwald.addVillage(b);
            GnomenWald.Gnomenwald.addVillage(c);
            GnomenWald.Gnomenwald.addVillage(d);
            GnomenWald.Gnomenwald.addVillage(f);
            GnomenWald.Gnomenwald.createRoad(a,b);
            GnomenWald.Gnomenwald.createRoad(a,c);
            GnomenWald.Gnomenwald.createRoad(a,d);
            GnomenWald.Gnomenwald.createRoad(b,d);
            GnomenWald.Gnomenwald.createRoad(b,c);
            GnomenWald.Gnomenwald.createRoad(c,a);
            GnomenWald.Gnomenwald.createRoad(c,d);
            GnomenWald.Gnomenwald.createRoad(d,a);
            GnomenWald.Gnomenwald.createRoad(f,b);
            GnomenWald.Gnomenwald.createRoad(d,f);

            repaint();
        }
    }
}

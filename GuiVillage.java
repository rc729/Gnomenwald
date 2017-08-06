package gui_demos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import q.*;

/**
 * GuiVillage class that extends from Village. Helps draw villages on GUI.
 */
public class GuiVillage extends Village{
    private static final int SIZE = 10;
    private Color color;
    private boolean selected = false;
    private static GuiVillage source = null;
    private Rectangle b = new Rectangle();
    
    /**
     * Construct a new node.
     */
    public GuiVillage(Point p, Color color) {
    	super(p.x, p.y);
        this.color = color;
        setBoundary(b);
    }
   
    /**
     * Calculate this node's rectangular boundary.
     */
    private void setBoundary(Rectangle b) {
        b.setBounds(this.getXpos() - SIZE, this.getYpos() - SIZE, 2 * SIZE, 2 * SIZE);
    }
    public static GuiVillage getSource() {
    	return source;
    }
    /**
     * Draw this node.
     */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(b.x, b.y, b.width, b.height);
      
        if (selected) {
            g.setColor(Color.darkGray);
            g.drawRect(b.x, b.y, b.width, b.height);
        }
    }

    /**
     * Return this node's location.
     */
    public Point getLocation() {
        return new Point(this.getXpos(),this.getYpos());
    }

    /**
     * Return true if this node contains p.
     */
    public boolean contains(Point p) {
        return b.contains(p);
    }

    /**
     * Return true if this node is selected.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Mark this node as selected.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;        
    }

    /**
     * Collected all the selected nodes in list.
     */
    public static void getSelected(List<GuiVillage> list, List<GuiVillage> selected) {
        selected.clear();
        
        for (GuiVillage n : list) {
        	if (n.isSelected()) {
                selected.add(n);
            }
        }
    }

    /**
     * Select no nodes.
     */
    public static void selectNone(List<GuiVillage> list) {
        for (GuiVillage n : list) {
            n.setSelected(false);
        }
    }

    /**
     * Select a single node; return true if not already selected.
     */
    public static boolean selectOne(List<GuiVillage> list, Point p) {
        for (GuiVillage n : list) {
            if (n.contains(p)) {
                if (!n.isSelected()) {
                    GuiVillage.selectNone(list);
                    n.setSelected(true);
                    source = n;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Select each node in r.
     */
    public static void selectRect(List<GuiVillage> list, Rectangle r) {
        for (GuiVillage n : list) {
            n.setSelected(r.contains(n.getPoint()));
        }
    }

    /**
     * Toggle selected state of each node containing p.
     */
    public static void selectToggle(List<GuiVillage> list, Point p) {
        for (GuiVillage n : list) {
            if (n.contains(p)) {
                n.setSelected(!n.isSelected());
            }
        }
    }

    /**
     * Update each node's position by d (delta).
     */
    public static void updatePosition(List<GuiVillage> list, Point d) {
        for (GuiVillage n : list) {
            if (n.isSelected()) {
                n.setXpos(n.getXpos() + d.x);
                n.setYpos(n.getYpos() + d.y);
                n.setBoundary(n.b);
            }
        }
    }
}

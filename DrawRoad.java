package gui_demos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import q.*;

/**
 * Helper class to draw roads in the GUI.
 * @author Roger
 *
 */

public class DrawRoad{
	private final static int ARROW_SIZE = 13;
    private static AffineTransform tx = new AffineTransform();
    private static Polygon arrowHead = new Polygon();  
    static {
    	arrowHead.addPoint( 0,ARROW_SIZE);
        arrowHead.addPoint( -ARROW_SIZE, -ARROW_SIZE);
        arrowHead.addPoint( ARROW_SIZE,-ARROW_SIZE);
    }

    private static void drawArrowHead(Graphics2D g2d, Road road) {
    	Point p1 = road.getFrom().getPoint();
    	Point p2 = road.getTo().getPoint();
        tx.setToIdentity();
        double angle = Math.atan2(p2.y-p1.y, p2.x-p1.x);
        double d = Math.sqrt((p2.y-p1.y) * (p2.y-p1.y) + (p2.x-p1.x)*(p2.x-p1.x));
        double dx = ((p2.x-p1.x) * GuiGnomenWald.RADIUS / 10) / d;
        double dy = ((p2.y-p1.y) * GuiGnomenWald.RADIUS / 10) / d;
        int cx = (int) (p2.x - dx * 2 );
        int cy = (int) (p2.y - dy * 2);
        
    	tx.translate(cx, cy);
        tx.rotate((angle-Math.PI/2d));  

        Graphics2D g = (Graphics2D) g2d.create();
        g.setTransform(tx);   
        g.fill(arrowHead);
        g.dispose();
    }
    
    public static void drawRoad(Graphics2D g, Road road) {
    	Point p1 = road.getFrom().getPoint();
        Point p2 = road.getTo().getPoint();
        g.setColor(Color.darkGray);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
        drawArrowHead((Graphics2D)g , road);
        
    }
}



package gui_demos;

import java.awt.Color;
import java.awt.Graphics2D;

import q.Gnome;
import q.Village;
import q.VipStatusException;

/**
 * GuiGnome class that extends from Gnome. Helps draw gnomes on the GUI.
 * @author Roger
 *
 */

public class GuiGnome extends Gnome{	
	
	private final int GNOME_SIZE = 10;
	
	public GuiGnome(Village start) throws VipStatusException {
		super(start);
	}
	
	public void draw(Graphics2D g) {
		
		Color curColor = g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval((int) this.getXpos(), (int) this.getYpos(), GNOME_SIZE, GNOME_SIZE);
		g.setColor(curColor);
	}
}

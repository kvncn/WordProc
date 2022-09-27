import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Rectangle;

/**
 * Glyph
 */

/*
 * For now the glyph class only accepts strings (not 
 * really chars because we want backspace functionality 
 * here). Later on make character a subclass
 */
abstract class Glyph {
	private int x;
	private int y;
	
	public boolean intersects(int xp, int yp) {
		return (x == xp && y == yp);
	}
	
	public abstract void draw(PaintEvent e);
	
	public int getRow() {return 0;}
	
	// for row and column glyphs to insert others
	public void insert(Glyph g) {};

	public boolean isFull() {return false;};
	
	public boolean isEmpty() {return false;}
	
	public int size() {return 0;}

	public void remove() {}
	// kinda janky but it work, and helps the encapsulation
	// so maybe not really janky ? 
}

class Row extends Glyph {
	private List<Glyph> glyphList;
	private int x;
	private int y;
	
	public Row(int x, int y) {
		this.glyphList = new ArrayList<Glyph>();
		this.x = x;
		this.y = y;
	}
	
	// for every glyph inside, just draw it here!
	public void draw(PaintEvent e) {
		for (Glyph g: glyphList) {
			g.draw(e);
		}
	}
	
	public void insert(Glyph g) {
		glyphList.add(g);
	}
	
	// just to check if we can stop actually adding 
	// and move to new row, sets the limit of chars
	// per row
	public boolean isFull() {
		return glyphList.size() == 30;
	}
	
	// these are helpers to know when to start a new row
	public boolean isEmpty() {
		return glyphList.size() == 0;
	}
	
	public int size() {
		return glyphList.size();
	}
	
	public void remove() {
		if (glyphList.size() == 0) return;
		glyphList.remove(glyphList.size()-1);
	}
	
	public int getRow() {
		return y;
	}
	// End of row helpers
}

class Character extends Glyph {
	
	private String c;
	private int x;
	private int y;
	
	public Character(String c, int x, int y) {
		this.c = c;
		this.x = x;
		this.y = y;
	}
	
	public void draw(PaintEvent e) {
		e.gc.drawString(c, x, y);
	}
	
}

class RectangleGlyph extends Glyph {
	
	private int size;
	private int x;
	private int y;
	
	public RectangleGlyph(int size, int x, int y) {
		this.size = size;
		this.x = x;
		this.y = y;
		
	}
	
	public void draw(PaintEvent e) {
		e.gc.drawRectangle(new Rectangle(y, x+5, size, size));
	}
}
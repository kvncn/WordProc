import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Rectangle;

/**
 * Glyph
 */


public abstract class Glyph{
	protected int x;
	protected int y;
	protected Glyph parent;
	/**
	 * When created given x, y coordinate on
	 * the window. From rect/image x,y will
	 * represent the point from upper left 
	 * corner? and the construct of it will hold more info
	 * @param x
	 * @param y
	 */
	public Glyph(int x, int y, Glyph parent) {
		this.x = x;
		this.y = y;
		this.parent = parent;
	}
	//appearance
	//what is needed to draw?
	public abstract void draw(PaintEvent e);
	// book says return a rectangle with dimensions
	// which like ig, I would rather just return x and y
	// bounds more useful for row and rect ig
	public abstract Rectangle bounds();
	public int getX() {return x;}
	public int getY() {return y;}
	
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	
	//hit detection
	//need to override for rect
	public boolean intersects(int test_x, int test_y) {
		return (test_x == x && test_y == y);
	}
	
	public boolean isFull() {
		return false;
	}
	public boolean isEmpty() {
		return false;
	}
	//structure
	public void insert(Glyph g) {}; //row only
	public void remove() {};
}

class Character extends Glyph{
	private String c;
	private String font;
	private int fontSize;
	private int height;
	
	public Character(int x, int y, Glyph parent, String c){
		super(x,y, parent);
		this.c = c;
		font = "courier";
		fontSize = 24;
		height = 15; //TODO: need to test this in printing
	}

	public String getChar() {
		return c;
		}
	
	public void setChar(String c) {
		this.c = c;
		}

	@Override
	public void draw(PaintEvent e) {
		e.gc.drawString(c, x * 25, y * 40);	
	}
	
	
	public void changeFont(String font) {
		if(font.equalsIgnoreCase("new york times")) {
			this.font = font;
		}
	}
	
	
	public void changeSize(int size) {
		if(size == 20) {
			size = 20;
		}
	}

	@Override
	public Rectangle bounds() {
		return new Rectangle(x, y, c.length(), height);
	}
	
	//TODO: FIND ACTUAL "HEIGHT" OF COURIER
	public int getHeight () {
		if(font.equals("courier") && fontSize == 24) {
			return 10;
		}
		return 10;
	}
	
}

class GRectangle extends Glyph{
	protected int width;
	protected int height;
	public GRectangle(int x, int y, int width, int height, Glyph parent) {
		super(x,y, parent);
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(PaintEvent e) {
		e.gc.drawRectangle(x*25, y*40, x+width, y+height);
	}

	@Override
	public Rectangle bounds() {
		return new Rectangle(x,y, width, height);
	}
	
	@Override
	public boolean intersects(int test_x, int test_y) {
		return (x<=test_x && test_x<= x+width) && (y<=test_y && test_y<= x+height);
	}

}

class Cursor extends Glyph{
	protected int width;
	protected int height;
	public Cursor(int x, int y, Glyph parent) {
		super(x,y, parent);
	}

	@Override
	public void draw(PaintEvent e) {
		e.gc.drawRectangle(x * 25, y * 40, 25, 40);
	}

	@Override
	public Rectangle bounds() {
		return new Rectangle(x,y, width, height);
	}
	
	@Override
	public boolean intersects(int test_x, int test_y) {
		return (x<=test_x && test_x<= x+5) && (y<=test_y && test_y<= x+5);
	}

}

class Row extends Glyph{
	protected int x;
	protected int y;
	protected List<Glyph> children;
	protected int height;
	protected int width;
	protected final static int maxWidth = 30; //30 characters, TODO: GENERALIZE 
	/**
	 * 
	 * @param x coor
	 * @param y coor
	 * @param parent should always be column type
	 */
	public Row(int x, int y, Glyph parent) {
		super(x,y, parent);
		children = new ArrayList<>();
		width = 0;	// starts at zero, changes when things added/ subtracted
		height = 10; //TODO: FIND DEFAULT HEIGHT
	}

	// needs to be moved to compositor? because need to handle changing height
	@Override
	public void draw(PaintEvent e) {
		for(int i = 0; i < children.size(); i++) {
			children.get(i).draw(e);
		}
	}

	@Override
	public Rectangle bounds() {
		//put 1 rn for place holders but
		//width = all children added up
		//height = 1 if only strings otherwise height
		//of heightest glyph (i.e. a rectangle)
		return new Rectangle(x,y, width,height);
	}
	
	// similar to Rectangle Bounds() need to find height
	// and width
	public boolean intersects(int test_x, int test_y) {
		return (x<= test_x && test_x <= width) && (y<=test_y && test_y<= x+height);
	}

	public void insert(Glyph g) {
		children.add(g);
		width += g.bounds().width;
		if(g.bounds().height > height) {
			height = g.bounds().height;
		}
	}

	//just need to remove last glyph
	public void remove() {
		if(!this.isEmpty()) {	
			Glyph g = children.remove(children.size()-1);
			width -= g.bounds().width;
			height = findMaxHeight();
			
		}
	}
	
	private int findMaxHeight() {
		int max = 0;
		for(int i = 0; i < children.size(); i++) {
			if(children.get(i).bounds().height > max) {
				max = children.get(i).bounds().height;
			}
		}
		return max;
	}

	public Glyph getChild(int i) {
		return children.get(i);
	}
	
	
	public Glyph Parent() {
		return parent;
	}
	
	public boolean isEmpty() {
		return children.size() == 0;
	}
	
	@Override
	public boolean isFull() {
		return width == maxWidth;
	}

	public int getRow() {
		return x;
	}
}

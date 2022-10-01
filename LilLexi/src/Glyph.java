import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

/**
 * Glyph
 */


public abstract class Glyph{
	protected int x;
	protected int y;
	int prevWidth;
	protected int height;
	protected int width;
	protected int maxHeight;
	
	protected Glyph parent;
	/**
	 * When created given x, y coordinate on
	 * the window. From rect/image x,y will
	 * represent the point from upper left 
	 * corner? and the construct of it will hold more info
	 * @param x
	 * @param y
	 */
	public Glyph(int x, int y) {
		this.x = x;
		this.y = y;
		maxHeight = 1;
		width = 0;
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
	public void setMaxHeight(int h) {
		maxHeight = h;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public abstract void checkMe(SpellingCheckingVisitor checker);
	
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
	private boolean incorrectSpelling;
	
	public Character(int x, int y, String c){
		super(x,y);
		this.c = c;
		//courier is 20 pixel per char
		font = "courier";
		fontSize = 24;
		height = 40; //TODO: need to test this in printing
		width = 25; //25 pixels; max line of just char = 30
		incorrectSpelling = false;
		
	}
	
	public void setSpelling(boolean spelling) {
		incorrectSpelling = spelling;
	}
	
	public boolean getSpelling() {
		return incorrectSpelling;
	}

	public String getChar() {
		return c;
		}
	
	public void setChar(String c) {
		this.c = c;
		}

	@Override
	public void draw(PaintEvent e) {
		
		Font newFont = new Font(e.display, font, fontSize, SWT.BOLD );
		e.gc.setFont(newFont);
		e.gc.drawString(c, x +10, y * (maxHeight+1));	
	}	
	
	public void changeFont(String font) {
		if(font.equalsIgnoreCase("Fixed")) {
			this.font = font;
		}
		//courier at 24 is 20 pix per char
		if(font.equalsIgnoreCase("Courier")) {
			this.font = font;
			if(fontSize == 24) {
				width = 20;
			}
		}
	}
	
	
	public void changeSize(int size) {
		if(size == 20) {
			fontSize = 20;
		}
		if(size == 24) {
			fontSize = 20;
		}
	}

	@Override
	public Rectangle bounds() {
		//length = amount of char times width of each char
		return new Rectangle(x, y, width, height);
	}
	
	//TODO: FIND ACTUAL "HEIGHT" OF COURIER
	public int getHeight () {
		if(font.equals("courier") && fontSize == 24) {
			return 10;
		}
		return 10;
	}

	@Override
	public void checkMe(SpellingCheckingVisitor checker) {
		
		checker.visitCharacter(this);
		
	}
	
}

class GRectangle extends Glyph{
	public GRectangle(int x, int y, int width, int height) {
		super(x,y);
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(PaintEvent e) {
		e.gc.drawRectangle(x+5, y*51, width, height);
		//System.out.println("drawing rect at " + (x*25) + "," + (y*4) + " with width and height" + (width) + " " + (height) );
	}

	@Override
	public Rectangle bounds() {
		return new Rectangle(x,y, width, height);
	}
	
	@Override
	public boolean intersects(int test_x, int test_y) {
		return (x<=test_x && test_x<= x+width) && (y<=test_y && test_y<= x+height);
	}

	@Override
	public void checkMe(SpellingCheckingVisitor checker) {
		checker.vistRectangle(this);
	}
	

}

//class Image extends Glyph{
//	protected int width;
//	protected int height;
//	public Image(int x, int y) {
//		super(x,y);
//		this.width = width;
//		this.height = height;
//	}
//
//	@Override
//	public void draw(PaintEvent e) {
//		e.gc.drawRectangle(x*25, y*40, width, height);
//		
//		
//	}
//
//	@Override
//	public Rectangle bounds() {
//		return new Rectangle(x,y, width, height);
//	}
//	
//	@Override
//	public boolean intersects(int test_x, int test_y) {
//		return (x<=test_x && test_x<= x+width) && (y<=test_y && test_y<= x+height);
//	}
//
//}


class Cursor extends Glyph{
	protected int width;
	protected int height;
	public Cursor(int x, int y) {
		super(x,y);
		height = 40;
		width = 25;
	}

	@Override
	public void draw(PaintEvent e) {
		System.out.println("max height is " + maxHeight);
		e.gc.drawRectangle(x +5, y * 51, width, height);
	}

	@Override
	public Rectangle bounds() {
		return new Rectangle(x,y, width, height);
	}
	
	@Override
	public boolean intersects(int test_x, int test_y) {
		return (x<=test_x && test_x<= x+5) && (y<=test_y && test_y<= x+5);
	}

	@Override
	public void checkMe(SpellingCheckingVisitor checker) {
		// TODO Auto-generated method stub
		checker.vistCursor(this);
		
	}

}

class Row extends Glyph{
	protected int x;
	protected int y;
	protected List<Glyph> children;
	protected final static int maxWidth = 600; //600 pixels (canvas is 800 by 800 by want to count for bo) 

	/**
	 * 
	 * @param x coor
	 * @param y coor
	 * @param parent should always be column type
	 */
	public Row(int x, int y) {
		super(x,y);
		children = new ArrayList<>();
		width = 0;	// starts at zero, changes when things added/ subtracted
		height = 40; //TODO: FIND DEFAULT HEIGHT
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
		g.setX(width);

		//System.out.println("CUR WIDTH IS " +width);
		width += g.bounds().width;
		//System.out.println("this obj height is " + g.bounds().height);
		if(g.bounds().height > maxHeight) {
			height = g.bounds().height;
			for(Glyph child : children) {
				child.setMaxHeight(height);
			}
		}
	}

	//just need to remove last glyph
	public void remove() {
		if(!this.isEmpty()) {	
			Glyph g = children.remove(children.size()-1);
			width -= g.bounds().width;
			height = findMaxHeight();
			maxHeight = height;
			for(Glyph child : children) {
				child.setMaxHeight(height);
			}
			
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

	public int getCurWidth() {
		return width;
	}
	public int getLastWidth() {
		return children.get(children.size()-1).bounds().width;
	}

	@Override
	public void checkMe(SpellingCheckingVisitor checker) {
		checker.vistRow(this);
		
	}
}
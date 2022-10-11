import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

/**
 * @author Kate Nixon
 * 
 * CSC 335 - David Claveau 
 * 
 * Program: Glyph.java
 * 
 * Abstract class: Glyph.java
 * Classes that Implement Glyph: Row, Character, GRectangle, GImage, Cursor
 * 
 * Overall Project: LilLexi
 * 
 * 
 * The purpose of this class is to represent Glyph objects in a text editor.
 * 
 * Row holds the glyph in the row of the document which can be Character, GRectangle, GImage, Cursor
 * 
 */
public abstract class Glyph{
	protected int x;
	protected int y;
	protected int height;
	protected int width;
	protected int maxHeight;
	protected Glyph parent;
	/**
	 * When created given x, y coordinate on
	 * the window. From rect/image x,y will
	 * represent the point from upper left 
	 * corner
	 * @param x
	 * @param y
	 */
	public Glyph(int x, int y) {
		this.x = x;
		this.y = y;
		maxHeight = 1;
		width = 0;
	}
	
	/**
	 * each glyph has the ability to draw itself
	 * @param e, PaintEvent from LilLexiUI
	 */
	public abstract void draw(PaintEvent e);

	/**
	 * Create Rectangle object with (x,y, width, height) of Glyph
	 * @return Rectangle object with bounds of Glyph
	 */
	public abstract Rectangle bounds();
	
	/**
	 * x coor of Glyph on doc
	 * @return int x
	 */
	public int getX() {return x;}
	
	/**
	 * y coor of Glyph on doc
	 * @return int y
	 */
	public int getY() {return y;}
	
	/**
	 * set x coor on doc
	 * @param int x
	 */
	public void setX(int x) {this.x = x;}
	
	/**
	 * set y coor on doc
	 * @param int y
	 */
	public void setY(int y) {this.y = y;}
	
	/**
	 * set max height on doc
	 * @param h
	 */
	public void setMaxHeight(int h) {maxHeight = h;}
	
	/**
	 * set height of glyph
	 * @param height, int
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * set width of glyph
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * use this method to enact spelling checking on Glyph
	 * @param checker
	 */
	public abstract void checkMe(SpellingCheckingVisitor checker);
	
	/**
	 * hit detection. used to tell if glyph is clicked on
	 * @param test_x, x coor clicked
	 * @param test_y, y coor clicked
	 * @return boolean value if clicked
	 */
	public boolean intersects(int test_x, int test_y) {
		return (test_x == x && test_y == y);
	}
	
	/**
	 * if glyph isFull, used for row
	 * @return
	 */
	public boolean isFull() {
		return false;
	}
	
	/**
	 * if glyph isEmpty, used for row
	 * @return
	 */
	public boolean isEmpty() {
		return false;
	}
	
	/**
	 * insert glyph into row
	 * @param Glyph g
	 */
	public void insert(Glyph g) {}; 
	
	/**
	 * remove glyph
	 */
	public void remove() {};
}

/**
 * 
 * @author Kate Nixon
 * 
 * Class: Character.java
 * 
 * Character extends abstract Glyph
 * 
 * Special characteristics:
 * 	-font
 *  -font size
 *  -incorrectSpelling
 *  
 * These special characteristics change how the
 * Character is draw on the document. 
 * Font and font size are self explanatory. 
 * If the character has incorrectSpelling, then
 * it is drawn red. If it is spelling correctly,
 * it is drawn blue
 */
class Character extends Glyph{
	private String c;
	private String font;
	private int fontSize;
	private boolean incorrectSpelling;
	
	/**
	 * Constructor for Character.
	 * Takes in x, y coor along with String c
	 * that represent the character.
	 * Default font is courier and default fontsize
	 * is 24. It is assumed to be spelled correctly
	 * @param x
	 * @param y
	 * @param c
	 */
	public Character(int x, int y, String c){
		super(x,y);
		this.c = c;
		//courier is 20 pixel per char
		font = "courier";
		fontSize = 24;
		height = 40; 
		width = 25; //25 pixels; max line of just char = 30
		incorrectSpelling = false;
		
	}
	
	/**
	 * Set incorrectSpelling to true
	 */
	public void setMisspelled() {
		incorrectSpelling = true;
	}
	
	/**
	 * Set incorrectSpelling to false
	 */
	public void setCorrectlySpelleed() {
		incorrectSpelling = false;
	}
	
	/**
	 * get whether or not the character is mispelled
	 * @return boolean
	 */
	public boolean getMisspelled() {
		return incorrectSpelling;
	}

	/**
	 * @return String c, represents character
	 */
	public String getChar() {
		return c;
	}
	
	/**
	 * set character string c to another
	 * @param c
	 */
	public void setChar(String c) {
		this.c = c;
	}

	/**
	 * Draws the Character
	 * If misspelled, drawn red; otherwise, blue.
	 * Draws based on current font size and font
	 * @param e, PaintEvent from LilLexi UI 
	 */
	@Override
	public void draw(PaintEvent e) {
		Font newFont = new Font(e.display, font, fontSize, SWT.BOLD );
		if(incorrectSpelling) {
			e.gc.setForeground(e.display.getSystemColor(SWT.COLOR_RED)); 
		}else {
			e.gc.setForeground(e.display.getSystemColor(SWT.COLOR_BLUE));
		}
		e.gc.setFont(newFont);
		e.gc.drawString(c, x +10, y * (maxHeight+1));	
	}	
	
	/**
	 * Changes font to param
	 * @param font
	 */
	public void changeFont(String font) {
		if(font.equalsIgnoreCase("Fixed")) {
			this.font = font;
		}
		if(font.equalsIgnoreCase("Courier")) {
			this.font = font;
		}
	}
	
	/**
	 * changes font size to size
	 * @param size
	 */
	public void changeSize(int size) {
		fontSize = size;
	}

	/**
	 * Creates Rectangle object that repesents bounds
	 * of character
	 */
	@Override
	public Rectangle bounds() {
		//length = amount of char times width of each char
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * get height based on font and fontsize
	 * @return int of height
	 */
	public int getHeight () {
		if(font.equals("courier") && fontSize == 24) {
			return 20;
		}else if(font.equals("courier") && fontSize == 14) {
			return 10;
		}else if(font.equals("fixed") && fontSize == 24) {
			return 18;
		}else {
			return 10;
		}
	}

	/**
	 * Uses the SpellingCheckingVistor to check if the character
	 * if part of a misspelled word
	 */
	@Override
	public void checkMe(SpellingCheckingVisitor checker) {
		checker.visitCharacter(this);
	}
}

/**
 * 
 * @author Kate Nixon
 * 
 * CSC 335- David
 * 
 * Overall Program: LilLexi 
 * 
 * Program: GRectangle.java
 * Implements abstract class Glyph.
 * 
 * Creates a rectangle object in Doc
 *
 */
class GRectangle extends Glyph{
	/**
	 * Takes in (x,y) coor along with width and height
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public GRectangle(int x, int y, int width, int height) {
		super(x,y);
		this.width = width;
		this.height = height;
	}

	/**
	 * Draws Rectangle based on x, y coor and width and height
	 * @param e, PaintEvent from UI
	 */
	@Override
	public void draw(PaintEvent e) {
		e.gc.drawRectangle(x+5, y*(maxHeight+1), width, height);
	}

	/**
	 * bounds of rectangle
	 */
	@Override
	public Rectangle bounds() {
		return new Rectangle(x,y, width, height);
	}
	
	/**
	 * hit detection
	 * @param test_x, test_y are x,y coors that were clicked
	 */
	@Override
	public boolean intersects(int test_x, int test_y) {
		return (x<=test_x && test_x<= x+width) && (y<=test_y && test_y<= x+height);
	}

	/**
	 * Checks spelling. Is stub.
	 */
	@Override
	public void checkMe(SpellingCheckingVisitor checker) {
		checker.visitRectangle(this);
	}
}

/**
 * 
 * @author Kate Nixon
 *
 * Program: GImage.java
 * Implements abstract class Glyph.
 * 
 * Creates a image object in Doc
 */
class GImage extends Glyph {
	protected int width;
	protected int height;
	protected Display display;
	protected Image img;
	
	/**
	 * (x,y) coor on doc. Takes in display
	 * to create an image (duck.jpg)
	 * Can be made generalized in future
	 * @param x
	 * @param y
	 * @param disp
	 */
	public GImage(int x, int y, Display disp) {
		super(x,y);
		width = 100;
		height = 100;
		display = disp;
		img = new Image(display, "duck.jpg");
	}

	/**
	 * Draws image
	 */
	@Override
	public void draw(PaintEvent e) {
		e.gc.drawImage(img, x, y);
	}

	/**
	 * bounds of image
	 */
	@Override
	public Rectangle bounds() {
		return new Rectangle(x,y, width, height);
	}
	
	/**
	 * hit detection
	 */
	@Override
	public boolean intersects(int test_x, int test_y) {
		return (x<=test_x && test_x<= x+width) && (y<=test_y && test_y<= x+height);
	}

	/**
	 * visits image. stub for vistor
	 */
	@Override
	public void checkMe(SpellingCheckingVisitor checker) {
		checker.visitImage(this);
	}
}

/**
 * 
 * @author Kate Nixon
 * Program: Cursor.java
 * Implements abstract class Glyph.
 * 
 * Creates a Cursor object in Doc
 * Represents where user is typing
 */
class Cursor extends Glyph{
	protected int width;
	protected int height;
	
	/**
	 * Constructor takes in x,y coor on doc
	 * Cursor has set height and width
	 * @param x
	 * @param y
	 */
	public Cursor(int x, int y) {
		super(x,y);
		height = 40;
		width = 25;
	}

	/**
	 * Draws cursor as a rectangle
	 */
	@Override
	public void draw(PaintEvent e) {
		e.gc.drawRectangle(x +5, y * (maxHeight +1), width, height);
	}

	/**
	 * bounds of cursor
	 */
	@Override
	public Rectangle bounds() {
		return new Rectangle(x,y, width, height);
	}
	
	/**
	 * hit detection.
	 * test_x, test_y represent where user clicked
	 */
	@Override
	public boolean intersects(int test_x, int test_y) {
		return (x<=test_x && test_x<= x+5) && (y<=test_y && test_y<= x+5);
	}

	/**
	 * visits cursor in spellingChecking vistor
	 */
	@Override
	public void checkMe(SpellingCheckingVisitor checker) {
		checker.visitCursor(this);
	}

}

/**
 * 
 * @author Kate Nixon
 *
 * Program: Row.java
 * Implements abstract class Glyph.
 * 
 * Creates a Row object in Doc
 * 
 * Column contains List of Row Objects.
 * Row objects contain list of other glyphs, 
 * ie characters, GImages, GRectangle, and the
 * Cursor
 */
class Row extends Glyph{
	protected int x;
	protected int y;
	protected List<Glyph> children;
	protected final static int maxWidth = 600; //600 pixels (canvas is 800 by 800 by want to count for bo) 

	/**
	 * 
	 * @param x coor
	 * @param y coor
	 */
	public Row(int x, int y) {
		super(x,y);
		//Glyphs in the row
		children = new ArrayList<>();
		width = 0;	// starts at zero, changes when things added/ subtracted
		height = 40; 
	}

	/**
	 * Uses polymorphism to draw all the glyphs in the row
	 */
	@Override
	public void draw(PaintEvent e) {
		for(int i = 0; i < children.size(); i++) {
			children.get(i).draw(e);
		}
	}

	/**
	 * bounds of the row
	 */
	@Override
	public Rectangle bounds() {
		return new Rectangle(x,y, width,height);
	}
	
	/**
	 * hit detection for row
	 */
	public boolean intersects(int test_x, int test_y) {
		return (x<= test_x && test_x <= width) && (y<=test_y && test_y<= x+height);
	}

	/**
	 * insert glyph into row
	 */
	public void insert(Glyph g) {
		children.add(g);
		g.setX(width);

		width += g.bounds().width;
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
	
	/**
	 * max height from tallest child glyph
	 * @return
	 */
	private int findMaxHeight() {
		int max = 0;
		for(int i = 0; i < children.size(); i++) {
			if(children.get(i).bounds().height > max) {
				max = children.get(i).bounds().height;
			}
		}
		return max;
	}

	/**
	 * get glyph at index i
	 * @param i
	 * @return
	 */
	public Glyph getChild(int i) {
		return children.get(i);
	}
	
	/**
	 * if row has no children
	 */
	public boolean isEmpty() {
		return children.size() == 0;
	}
	
	/**
	 * if row is full
	 */
	@Override
	public boolean isFull() {
		return width == maxWidth;
	}

	/**
	 * get the row number (ie the x val)
	 * @return
	 */
	public int getRow() {
		return x;
	}

	/**
	 * get cur width
	 * @return
	 */
	public int getCurWidth() {
		return width;
	}
	/**
	 * get the last child's width
	 * @return
	 */
	public int getLastWidth() {
		return children.get(children.size()-1).bounds().width;
	}

	/**
	 * Visit row in SpellingChecking Vistor
	 */
	@Override
	public void checkMe(SpellingCheckingVisitor checker) {
		checker.vistRow(this);
		
	}
}

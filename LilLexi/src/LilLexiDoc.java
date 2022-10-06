/*
* AUTHOR: Kevin Nisterenko
* FILE: LilLexiDoc.java
* ASSIGNMENT: A2 - LilLexi
* COURSE: CSc 335; Fall 2022
* PURPOSE: This class is a concrete implementation of the 
* Composition class. It sets up the basic functionality of 
* a LilLexi document, such as adding, removing, undoing and 
* redoing glyphs at a cursor. The produced composition is then
* passed to the Compositor class, which composes it into 
* the actual document structure. 
*
* There are no inputs for this specific file. 
*/

import java.util.List;

import org.eclipse.swt.widgets.Display;

class LilLexiDoc extends Composition {
	private String currentFont;
	private int currentSize;
	private Glyph last; // for undoing/redoing
	private boolean removed; // for undoing/redoing
	
	/**
	 * Calls the super constructor for the basic functionality,
	 * also sets the default font and size for the document. 
	 */
	public LilLexiDoc() {
		super();
		// this is just going to be our list of rows for now
		// cause we only have one column
		currentFont = "Courier";
		currentSize = 24;
		removed = false;
	}
	
	
	/**
	 * Sets the LilLexi UI so it can call the update method
	 * to redraw the canvas. 
	 * 
	 * @param ui, LilLexiUI object, has the current UI used 
	 * to display the document
	 */
	public void setUI(LilLexiUI ui) {this.ui = ui;}

	/**
	 * Add a char to the document based on the passed 
	 * string. It sets it to the latest cursor position and
	 * redraws the screen to reflect the addition of the
	 * character. 
	 * 
	 * @param c, String representing the character to add to the
	 * document
	 */
	public void add(String c) {
		Glyph charac = new Character(cursor * 25, cursor * 40, c);
		glyphs.add(cursor, charac); // add it to the glyphs
		Character cur = (Character) glyphs.get(cursor);
		// check if any modifications are needed for the correct 
		// screen drawing
		cur.checkMe(spellCheck);
		cur.changeFont(currentFont);
		cur.changeSize(currentSize);
		cursor++; // move the cursor forward
		removed = false;
		// now we update the canvas to reflect the addition
		comp = compositor.compose(glyphs);
		ui.updateUI();
	}
	
	/**
	 * This method updates the font type of all character in the
	 * current document.
	 * 
	 * @param font, String representing the font to be used in
	 * the document
	 */
	public void changeFont(String font) {
		// for every character, update the font to the 
		// passed one
		for(Glyph g : glyphs) {
			if(g instanceof Character) {
				((Character) g).changeFont(font);
			}
		}
		// reset font so that new added character can
		// have the correct font
		currentFont = font;
		comp = compositor.compose(glyphs);
		ui.updateUI();
	}
	
	/**
	 * This method updates the font size of all character in the
	 * current document.
	 * 
	 * @param size, integer representing the size to be used in
	 * the document
	 */
	public void changeFontSize(int size) {
		// for every character, update the size to the 
		// passed one
		for(Glyph g : glyphs) {
			if(g instanceof Character) {
				((Character) g).changeSize(size);
			}
		}
		// reset size so that new added character can
		// have the correct size
		currentSize = size;
		comp = compositor.compose(glyphs);
		ui.updateUI(); 
	}
	
	/**
	 * Add a rectangle to the document based on the passed 
	 * size. It sets it to the latest cursor position and
	 * redraws the screen to reflect the addition of the
	 * glyph.
	 * 
	 * @param rectSize, int representing the size of the rectangle to 
	 * add to the screen
	 */
	public void add(int rectSize) {
		Glyph rect = new GRectangle(cursor * 25, cursor * 40, rectSize, rectSize);
		glyphs.add(cursor, rect); // add it to the glyphs
		cursor++; // move the cursor forward
		removed = false;
		// now we update the canvas to reflect the addition
		comp = compositor.compose(glyphs);
		ui.updateUI();
	}
	
	/**
	 * Add a glyph to the document based on the passed 
	 * size. It sets it to the latest cursor position and
	 * redraws the screen to reflect the addition of the
	 * glyph. Used to add any type of glyph for the redo 
	 * method.
	 * 
	 * @param g, Glyph object to be added to the last position, 
	 * used for the redo
	 */
	private void add(Glyph g) {
		glyphs.add(cursor, g);
		cursor++;
		comp = compositor.compose(glyphs);
		ui.updateUI();
	}
	
	/**
	 * Add an image to the document based on the passed 
	 * size. It sets it to the latest cursor position and
	 * redraws the screen to reflect the addition of the
	 * glyph.
	 * 
	 * @param display, Display object so that the UI can add the
	 * image to the canvas
	 */
	public void add(Display display) {
		Glyph img = new GImage(cursor * 25, cursor * 40, display);
		glyphs.add(cursor, img); // add it to the glyphs
		cursor++; // move cursor forward
		// now we update the canvas to reflect the addition
		comp = compositor.compose(glyphs);
		ui.updateUI();
	}
	
	/**
	 * For backspace, remove last glyph from document if there
	 * is actually something to be removed, and update the cursor 
	 * and last removed object.
	 */
	public void remove() {
		// only cursor, so we don't remove anything
		if (cursor == 0) {
			return;
		}
		else {
			// removes what came before cursor
			last = glyphs.get(cursor - 1);
			glyphs.remove(cursor-1);
			cursor--; // move cursor back since a glyph was removed
			removed = true;
			comp = compositor.compose(glyphs);
			ui.updateUI();
		}
	}
	
	/**
	 * This method checks if we can undo something 
	 * (canvas not empty), and if so, removes the last added
	 * object.
	 */
	public void undo() {
		if (glyphs.size() == 1) {
			return;
		}
		this.remove();
	}	
	
	/**
	 * This method checks if a redo is possible 
	 * (last operation was a remove), if so, it 
	 * re-adds the last glyph to the screen.
	 */
	public void redo() {
		if (glyphs.size() != 1 && removed) {
			return;
		}
		this.add(last);
	}
	/**
	 * Gets glyphs list (this is like the document itself
	 * we can iterate, build strings until we hit a space, then 
	 * just add a marker to each string ("s!") for example, that 
	 * will allows us to display wrongly spelled words 
	 * 
	 * @return comp, the composition of the glyphs with the
	 * correct row setup for canvas drawing
	 */
	public List<Glyph> getGlyphs(){return comp;}
}
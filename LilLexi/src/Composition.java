/*
* AUTHOR: Kevin Nisterenko
* FILE: Composition.java
* ASSIGNMENT: A2 - LilLexi
* COURSE: CSc 335; Fall 2022
* PURPOSE: This class is an abstract representation of a document
* composition, it sets a basic constructor that the concrete subclasses 
* (in this case LilLexiDoc) can call. 
*
* There are no inputs for this specific file. 
*/
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Display;

public abstract class Composition {
	protected int cursor; // sets the cursor location
	protected List<Glyph> glyphs; // the basic list of glyphs
	protected Compositor compositor; // we have a compositor to call
	protected LilLexiUI ui; // ui object present to be able to update the ui
	protected List<Glyph> comp; // represents the updated composition of glyphs
	protected SpellingCheckingVisitor spellCheck; // spellchecker for words
	
	/**
	 * Sets the default/empty attributes that would be common to every concrete 
	 * implementation of Composition.
	 */
	public Composition() {
		glyphs = new ArrayList<Glyph>();
		cursor = 0;
		glyphs.add(new Cursor(0, 0));
		compositor = new Compositor();
		comp = compositor.compose(glyphs);
		spellCheck = new SpellingCheckingVisitor();
	}
	
	/**
	 * This method sets the LilLexi ui based on the 
	 * passed UI object.
	 * 
	 * @param ui, LilLexiUI object representing the
	 * ui we wish to use
	 */
	public abstract void setUI(LilLexiUI ui);
	
	/**
	 * Add a char to the document based on the passed 
	 * string. 
	 * 
	 * @param c, String representing the character to add to the
	 * document
	 */
	public abstract void add(String c);
	
	/**
	 * This method updates the font type of all character in the
	 * current document.
	 * 
	 * @param font, String representing the font to be used in
	 * the document
	 */
	public abstract void changeFont(String font);
	
	/**
	 * This method updates the font size of all character in the
	 * current document.
	 * 
	 * @param size, integer representing the size to be used in
	 * the document
	 */
	public abstract void changeFontSize(int size);
	
	/**
	 * Add a rectangle to the document based on the passed 
	 * size. 
	 * 
	 * @param rectSize, int representing the size of the rectangle to 
	 * add to the screen
	 */
	public abstract void add(int rectSize);
	
	/**
	 * Add an image to the document based on the passed 
	 * size.
	 * 
	 * @param display, Display object so that the UI can add the
	 * image to the canvas
	 */
	public abstract void add(Display display);
	
	/**
	 * Remove last glyph from document if there
	 * is actually something to be removed, and update the cursor 
	 * and last removed object.
	 */
	public abstract void remove();
	
	/**
	 * This method checks if we can undo something 
	 * (canvas not empty), and if so, removes the last added
	 * object.
	 */
	public abstract void undo();
	
	/**
	 * This method checks if a redo is possible 
	 * (last operation was a remove), if so, it 
	 * re-adds the last glyph to the screen.
	 */
	public abstract void redo();
	
	/**
	 * This method returns the compositor modified 
	 * list of glyphs with the correct row and column
	 * setup.
	 * 
	 * @return List<Glyph> representing the document
	 * composition.
	 */
	public abstract List<Glyph> getGlyphs();
}


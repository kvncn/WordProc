/*
* AUTHOR: Kevin Nisterenko
* FILE: LilLexiControl.java
* ASSIGNMENT: A2 - LilLexi
* COURSE: CSc 335; Fall 2022
* PURPOSE: This class serves as the controller (in a species of
* MVC) for the LexiUI, making the necessary changes to the model 
* (LilLexiDoc). Based on Claveau's version.
*
* There are no inputs for this specific file. 
*/

import org.eclipse.swt.widgets.Display;

/**
 * Controller
 */
public class LilLexiControl {
	
	private LilLexiDoc currentDoc;

	/**
	 * LilLexiControl takes a document (model)
	 * so it can make the changes to it, based on 
	 * UI events.
	 */
	public LilLexiControl(LilLexiDoc doc) {
		this.currentDoc = doc;
	}
	
	/**
	 * Add a char to the document based on the passed 
	 * string. 
	 * 
	 * @param c, String representing the character to add to the
	 * document
	 */
	void add(String c) {	
		currentDoc.add(c);
	}
	
	/**
	 * This method updates the font type of all character in the
	 * current document.
	 * 
	 * @param font, String representing the font to be used in
	 * the document
	 */
	void changeFont(String font) {
		currentDoc.changeFont(font);
	}
	
	/**
	 * This method updates the font size of all character in the
	 * current document.
	 * 
	 * @param size, integer representing the size to be used in
	 * the document
	 */
	void changeFontSize(int size) {
		currentDoc.changeFontSize(size);
	}
	
	/**
	 * Add a rectangle to the document based on the passed 
	 * size. 
	 * 
	 * @param rectSize, int representing the size of the rectangle to 
	 * add to the screen
	 */
	void add(int size) {	
		currentDoc.add(size);
	}	
	
	/**
	 * Add an image to the document based on the passed 
	 * size.
	 * 
	 * @param display, Display object so that the UI can add the
	 * image to the canvas
	 */
	public void add(Display display) {
		currentDoc.add(display);
	}
	
	/**
	 * Remove last glyph from document if there
	 * is actually something to be removed, and update the cursor 
	 * and last removed object.
	 */
	void remove() {
		currentDoc.remove();
	}
	
	/**
	 * This method checks if we can undo something 
	 * (canvas not empty), and if so, removes the last added
	 * object.
	 */
	void undo() {
		currentDoc.undo();
	}
	
	/**
	 * This method checks if a redo is possible 
	 * (last operation was a remove), if so, it 
	 * re-adds the last glyph to the screen.
	 */
	public void redo() {
		currentDoc.redo();
		
	}	
	
	/**
	 * When the user quits the editor, finish running the
	 * program.
	 */
	void quitEditor() { 
		System.exit(0); 
	}
}
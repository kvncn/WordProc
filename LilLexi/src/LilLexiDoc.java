/**
 * Lil Lexi Document Model
 * 
 * Compositor
 * 
 */
import java.util.List;

import java.util.ArrayList;

/**
 * LilLexiDoc
 */
public class LilLexiDoc {
	private LilLexiUI ui;
	private List<Glyph> glyphs;
	
	/**
	 * Ctor
	 */
	public LilLexiDoc() {
		// this is just going to be our list of rows for now
		// cause we only have one column
		glyphs = new ArrayList<Glyph>(); // if we want more columns, we can work it out here
		glyphs.add(new Row(0, 0));
	}
	
	/**
	 * setUI
	 */
	public void setUI(LilLexiUI ui) {this.ui = ui;}

	/**
	 * add a char to the document (for now just a 
	 * reg glyph), the plan is to have another 
	 * add if we want an image or whatever. 
	 */
	public void add(String c) {
		// always add to latest row
		this.checkRow();
		Glyph currRow = glyphs.get(glyphs.size()-1);
		currRow.insert(new Character(c, currRow.size()* 25, currRow.getRow() * 40));
		ui.updateUI();
	}
	
	public void add(int rect_size) {
		// always add to latest row
		this.checkRow();
		Glyph currRow = glyphs.get(glyphs.size()-1);
		// add the rectangle to the row
		currRow.insert(new RectangleGlyph(20, currRow.size()* 25, currRow.getRow() * 40));
		ui.updateUI();
	}
	
	/* 
	 * For backspace, remove last glyph from document
	 */
	public void remove() {
		// if we only have a single row, don't do anything
		if (glyphs.size() == 1) {
			glyphs.get(0).remove();
			return;
		} 
		// check if row empty, if yes, we move back to upper row
		Glyph currRow = glyphs.get(glyphs.size()-1);
		if (currRow.isEmpty()) {
			glyphs.remove(glyphs.size()-1);
			return;
		}
		// remove from this row
		currRow.remove();
	}
	
	/**
	 * gets glyphs list (this is kinda like the document itself
	 * we can iterate, build strings until we hit a space, then 
	 * just add a marker to each string ("s!") for example, that 
	 * will allows us to display wrongly spelled words differently
	 */
	public List<Glyph> getGlyphs(){return glyphs;}
	
	private void checkRow() {
		if (glyphs.get(glyphs.size()-1).isFull()) {
			System.out.println("NEW ROW");
			glyphs.add(new Row(0, glyphs.size()));
		}
	}
}






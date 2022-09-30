import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

class LilLexiDoc extends Compositor {
	private LilLexiUI ui;
	private int cursor;
	private List<Glyph> glyphs;
	private Composition composition;
	private List<Glyph> comp;
	
	/**
	 * Ctor
	 */
	public LilLexiDoc() {
		// this is just going to be our list of rows for now
		// cause we only have one column
		glyphs = new ArrayList<Glyph>(); // if we want more columns, we can work it out here
		cursor = 0;
		glyphs.add(new Cursor(0, 0));
		composition = new Composition();
		comp = composition.compose(glyphs);
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
		//default is courier 24 so default will always be x*25, and y *40
		glyphs.add(cursor, new Character(cursor * 25, cursor * 40, c));
		cursor++;
		//glyphs.add(cursor, new Cursor(cursor, cursor, cursor));
		comp = composition.compose(glyphs);
		ui.updateUI();
	}
	
	public void add(int rectSize) {
		//default is courier 24 so default will always be x*25, and y *40
		glyphs.add(cursor, new GRectangle(cursor * 25, cursor * 40, rectSize, rectSize));
		cursor++;
		//glyphs.add(cursor, new Cursor(cursor, cursor, cursor));
		comp = composition.compose(glyphs);
		ui.updateUI();
	}
	
//	public void add() {
//		glyphs.add(cursor, new Image(cursor * 25, cursor * 40));
//		cursor++;
//		//glyphs.add(cursor, new Cursor(cursor, cursor, cursor));
//		comp = composition.compose(glyphs);
//		ui.updateUI();
//	}
	
	/* 
	 * For backspace, remove last glyph from document
	 */
	public void remove() {
		// only cursor
		if (cursor == 0) {
			return;
		}
		else {
			// removes what came before cursor!
			glyphs.remove(cursor-1);
			cursor--;
			//glyphs.add(cursor, new Cursor(cursor, cursor * 25, cursor * 40));
			comp = composition.compose(glyphs);
			ui.updateUI();
		}
	}
	
	/**
	 * gets glyphs list (this is kinda like the document itself
	 * we can iterate, build strings until we hit a space, then 
	 * just add a marker to each string ("s!") for example, that 
	 * will allows us to display wrongly spelled words differently
	 */
	public List<Glyph> getGlyphs(){return comp;}
}
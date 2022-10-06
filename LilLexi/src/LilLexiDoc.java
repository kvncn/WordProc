import java.util.List;

import org.eclipse.swt.widgets.Display;



class LilLexiDoc extends Composition {
	private String currentFont;
	private int currentSize;
	private Glyph last;
	private boolean removed;
	
	/**
	 * Ctor
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
		Glyph charac = new Character(cursor * 25, cursor * 40, c);
		glyphs.add(cursor, charac);
		last = charac;
		Character cur = (Character) glyphs.get(cursor);
		cur.checkMe(spellCheck);
		cur.changeFont(currentFont);
		cur.changeSize(currentSize);
		cursor++;
		removed = false;
		//glyphs.add(cursor, new Cursor(cursor, cursor, cursor));
		comp = compositor.compose(glyphs);
		ui.updateUI();
	}
	
	public void changeFont(String font) {
		for(Glyph g : glyphs) {
			if(g instanceof Character) {
				((Character) g).changeFont(font);
			}
		}
		currentFont = font;
		comp = compositor.compose(glyphs);
		ui.updateUI();
	}
	
	public void changeFontSize(int size) {
		for(Glyph g : glyphs) {
			if(g instanceof Character) {
				((Character) g).changeSize(size);;
			}
		}
		currentSize = size;
		comp = compositor.compose(glyphs);
		ui.updateUI(); 
	}
	
	public void add(int rectSize) {
		//default is courier 24 so default will always be x*25, and y *40
		Glyph rect = new GRectangle(cursor * 25, cursor * 40, rectSize, rectSize);
		glyphs.add(cursor, rect);
		last = rect;
		cursor++;
		removed = false;
		//glyphs.add(cursor, new Cursor(cursor, cursor, cursor));
		comp = compositor.compose(glyphs);
		ui.updateUI();
	}
	
	public void add(Glyph g) {
		glyphs.add(cursor, g);
		cursor++;
		//glyphs.add(cursor, new Cursor(cursor, cursor, cursor));
		comp = compositor.compose(glyphs);
		ui.updateUI();
	}
	
	public void add(Display display) {
		Glyph img = new GImage(cursor * 25, cursor * 40, display);
		glyphs.add(cursor, img);
		cursor++;
		last = img;
		//glyphs.add(cursor, new Cursor(cursor, cursor, cursor));
		comp = compositor.compose(glyphs);
		ui.updateUI();
	}
	
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
			comp = compositor.compose(glyphs);
			ui.updateUI();
		}
	}
	
	public void undo() {
		if (glyphs.size() == 1) {
			return;
		}
		this.remove();
	}	
	
	public void redo() {
		if (glyphs.size() == 1 || !removed) {
			return;
		}
		this.add(last);
	}
	/**
	 * gets glyphs list (this is kinda like the document itself
	 * we can iterate, build strings until we hit a space, then 
	 * just add a marker to each string ("s!") for example, that 
	 * will allows us to display wrongly spelled words differently
	 */
	public List<Glyph> getGlyphs(){return comp;}
}
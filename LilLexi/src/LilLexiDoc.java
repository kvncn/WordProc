import java.util.List;

class LilLexiDoc extends Composition {
	private String currentFont;
	private int currentSize;
	
	/**
	 * Ctor
	 */
	public LilLexiDoc() {
		super();
		// this is just going to be our list of rows for now
		// cause we only have one column
		currentFont = "Courier";
		currentSize = 24;
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
		Character cur = (Character) glyphs.get(cursor);
		cur.changeFont(currentFont);
		cur.changeSize(currentSize);
		cursor++;
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
		ui.updateUI();
	}
	
	public void changeFontSize(int size) {
		for(Glyph g : glyphs) {
			if(g instanceof Character) {
				((Character) g).changeSize(size);;
			}
		}
		currentSize = size;
		ui.updateUI();
	}
	
	public void add(int rectSize) {
		//default is courier 24 so default will always be x*25, and y *40
		glyphs.add(cursor, new GRectangle(cursor * 25, cursor * 40, rectSize, rectSize));
		cursor++;
		//glyphs.add(cursor, new Cursor(cursor, cursor, cursor));
		comp = compositor.compose(glyphs);
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
			comp = compositor.compose(glyphs);
			ui.updateUI();
		}
	}
	
	// For some reason history not workinbg
	public void undo() {
		if (glyphs.size() == 1) {
			return;
		}
		this.remove();
	}	
	/**
	 * gets glyphs list (this is kinda like the document itself
	 * we can iterate, build strings until we hit a space, then 
	 * just add a marker to each string ("s!") for example, that 
	 * will allows us to display wrongly spelled words differently
	 */
	public List<Glyph> getGlyphs(){return comp;}
}
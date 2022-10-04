/**
 * Lil Lexi Document Model
 * 
 * Compositor
 * 
 */
import java.util.ArrayList;
import java.util.List;

public abstract class Composition {
	protected int cursor;
	protected List<Glyph> glyphs;
	protected Compositor compositor;
	protected LilLexiUI ui;
	protected List<Glyph> comp;
	protected SpellingCheckingVisitor spellCheck;
	
	public Composition() {
		// this is just going to be our list of rows for now
		// cause we only have one column
		glyphs = new ArrayList<Glyph>(); // if we want more columns, we can work it out here
		cursor = 0;
		glyphs.add(new Cursor(0, 0));
		compositor = new Compositor();
		comp = compositor.compose(glyphs);
		spellCheck = new SpellingCheckingVisitor();
	}
	
	public abstract void setUI(LilLexiUI ui);
	
	public abstract void add(String c);
	
	public abstract void add(int rectSize);
	
	public abstract void remove();
	
	public abstract void undo();
	
	public abstract void redo();
	
	public abstract List<Glyph> getGlyphs();
}


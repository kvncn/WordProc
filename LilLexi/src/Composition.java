/**
 * @author Kate Nixon
 * 
 * Class: Composition
 * This is the abstract class implemented by LilLexiDoc
 * 
 * Composition contains the index of cursor,
 * a List<Glyph> objects, reference to the ui,
 * and spellCheck.
 * 
 * All needed to compose a document
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
	
	/**
	 * Constructor for composition. Creates List<Glyph>
	 * Sets the cursor's index to being the first element
	 * of the Glyphs (other method increment it)
	 * Composes the Glyphs using the compositor
	 * Initiates spellcheck for the document
	 */
	public Composition() {
		// this is just going to be our list of rows for now
		// cause David said that we only need one column
		glyphs = new ArrayList<Glyph>(); // if we want more columns, we can work it out here
		cursor = 0;
		glyphs.add(new Cursor(0, 0));
		compositor = new Compositor();
		comp = compositor.compose(glyphs);
		spellCheck = new SpellingCheckingVisitor();
	}
	
	/**
	 * Sets the ui
	 * @param ui from Lil Lexi
	 */
	public abstract void setUI(LilLexiUI ui);
	
	/**
	 * Creates Character Glyph using c and adds it to the
	 * List<Glyph> and uses compose to arrange the glyph
	 * @param c, string of character
	 */
	public abstract void add(String c);
	
	/**
	 * Creates GRectangle Glyph using rectangle size and adds it to the
	 * List<Glyph> and uses compose to arrange the glyph
	 * @param size
	 */
	public abstract void add(int rectSize);
	
	public abstract void remove();
	
	public abstract void undo();
	
	public abstract void redo();
	
	public abstract List<Glyph> getGlyphs();
}
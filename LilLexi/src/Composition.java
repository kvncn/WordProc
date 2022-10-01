/**
 * Lil Lexi Document Model
 * 
 * Compositor
 * 
 */
import java.util.List;


import java.util.List;

import java.util.ArrayList;

public abstract class Composition {
	private int cursor;
	private List<Glyph> glyphs;
	
	public abstract void setUI(LilLexiUI ui);
	
	public abstract void add(String c);
	
	public abstract void add(int rectSize);
	
	public abstract void remove();
	
	public abstract List<Glyph> getGlyphs();
}


/**
 * Lil Lexi Document Model
 * 
 */
import java.util.List;
import java.util.ArrayList;

/**
 * LilLexiDoc
 */
public class LilLexiDoc 
{
	private LilLexiUI ui;
	private List<Glyph> glyphs;
	
	/**
	 * Ctor
	 */
	public LilLexiDoc() 
	{
		glyphs = new ArrayList<Glyph>();
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
	public void add(String c) 
	{
		// if we have backspace, pop the last thing off
		if (c.equals("BS")) {
			if (glyphs.size() == 0) return;
			glyphs.remove(glyphs.size()-1);
		} else {
			glyphs.add(new Glyph(c));
		}
		ui.updateUI();
	}
	
	/**
	 * gets glyphs list (this is kinda like the document itself
	 * we can iterate, build strings until we hit a space, then 
	 * just add a marker to each string ("s!") for example, that 
	 * will allows us to display wrongly spelled words differently
	 */
	public List<Glyph> getGlyphs(){return glyphs;}
}






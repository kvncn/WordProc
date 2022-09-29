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
	// says glyphs but really list of rows
	private List<Glyph> glyphs;
	private int height;
	/**
	 * Ctor
	 */
	public LilLexiDoc() 
	{
		//width and height of doc
		glyphs = new ArrayList<Glyph>();
		//null should be replaced with column? maybe?
		glyphs.add(new Row(0,0, null));
	}
	
	/**
	 * setUI
	 */
	public void setUI(LilLexiUI ui) {this.ui = ui;}

	/**
	 * add a char to the document
	 */
	public void add(String c) {
		//if full, new row
		if(glyphs.get(glyphs.size()-1).isFull()) {
			glyphs.add(new Row(0,glyphs.size()+1, null));
		}
		Glyph cur = glyphs.get(glyphs.size()-1);
		//string starts at the width (i.e. before "I was ", "here"
		//would start at length-1. May need to double check if width
		//is implemented correctly
		//y is equal to what? the row number times the constant height?
		cur.insert(new Character(cur.bounds().width*25, (glyphs.size()-1)*40, cur, c));
		ui.updateUI();
	}
	
	public void add(int x, int y) {
		if(glyphs.get(glyphs.size()-1).isFull()) {
			glyphs.add(new Row(0,glyphs.size()+1, null));
		}
		Glyph cur = glyphs.get(glyphs.size()-1);
		//set width and height standard for right now
		cur.insert(new GRectangle(cur.bounds().width, (glyphs.size()-1)*5, 10, 5, cur));
		ui.updateUI();
	}
	
	public void remove() {
		Glyph cur = glyphs.get(glyphs.size()-1);
		//if empty first row, stay empty first row
		if(glyphs.size() == 1 && cur.isEmpty()) {
			return;
		//if not first row and is empty, remove row
		}else if(cur.isEmpty()){ 
			glyphs.remove(cur);
		}else {
			//
			cur.remove();
		}
		
	}
	
	/**
	 * gets glyphs list (this is kinda like the document itself
	 * we can iterate, build strings until we hit a space, then 
	 * just add a marker to each string ("s!") for example, that 
	 * will allows us to display wrongly spelled words differently
	 */
	public List<Glyph> getGlyphs(){return glyphs;}
}
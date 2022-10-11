import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Kate Nixon
 * 
 * Course: CSC 335 - David Claveau
 * 
 * Program: Compositor.java
 * Part of Overall Project: Lil Lexi
 * 
 * Compositor takes a list<Glyph> from LilLexiDoc
 * and formats the glyphs into rows on the document.
 * 
 */
public class Compositor {
	
	private List<Glyph> column;
	
	/**
	 * No args constructor for compositor
	 * Creates the one column in Lil Lexi
	 * (David mentioned that we would only
	 * work with one column)
	 */
	public Compositor() {
		column = new ArrayList<Glyph>();
	}
	
	/**
	 * Given a list<Glyph> compose them into
	 * rows based on the max width of the document
	 * @param List<Glyph>glyphs
	 * @return column, the list of rows
	 */
	public List<Glyph> compose(List<Glyph> glyphs) {
		int x = -1;
		int y = -1;
		int maxWidth = 700;
		int rowWidth = 0;
		column = new ArrayList<Glyph>();
		for (Glyph g : glyphs) {
			rowWidth += g.bounds().width;
			if (x == -1 || rowWidth > maxWidth) {
				x = 0;
				rowWidth = g.bounds().width;
				y++;
				Glyph newRow = new Row(x, y);
				
				g.setY(y);
				newRow.insert(g);
				column.add(newRow);
			} else {
				g.setY(y);
				Row curRow = (Row) column.get(column.size()-1);
				curRow.insert(g);
				x++;
			}
		}
	
		return column;
	}
}


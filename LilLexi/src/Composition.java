import java.util.ArrayList;
import java.util.List;

public class Composition {
	
	private List<Glyph> column;
	private LilLexiUI ui;
	
	public Composition() {
		column = new ArrayList<Glyph>();
	}
	
	public void setUI(LilLexiUI ui) {
		this.ui = ui;
	}
	
	public List<Glyph> compose(List<Glyph> glyphs) {
		int x = -1;
		int y = -1;
		System.out.println(glyphs);
		column = new ArrayList<Glyph>();
		for (Glyph g : glyphs) {
			// wrap when we hit limit (change depending on width I guess)
			// for now just 30 glyphs per thingie
			if (x == -1 || x >= 30) {
				x = 0;
				y++;
				System.out.println("NEW ROW");
				Glyph newRow = new Row(x, y);
				g.set(x, y);
				newRow.insert(g);
				column.add(newRow);
			} else {
				g.set(x, y);
				column.get(column.size()-1).insert(g);
				x++;
			}
			System.out.println("We are at " + x + " what row?? " + y);
		}
		
		return column;
	}
}

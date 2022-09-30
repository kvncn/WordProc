import java.util.ArrayList;
import java.util.List;
// Comment
public class Composition {
	
	private List<Glyph> column;
	
	public Composition() {
		column = new ArrayList<Glyph>();
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
				Glyph newRow = new Row(x, y, null);
				g.setX(x);
				g.setY(y);
				newRow.insert(g);
				column.add(newRow);
			} else {
				g.setX(x);
				g.setY(y);
				column.get(column.size()-1).insert(g);
				x++;
			}
			System.out.println("We are at " + x + " what row?? " + y);
		}
		
		return column;
	}
}

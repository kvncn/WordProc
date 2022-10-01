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
		int maxWidth = 700;
		int rowWidth = 0;
		//System.out.println(glyphs);
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
				//g.setX(x);
				g.setY(y);
				Row curRow = (Row) column.get(column.size()-1);
				curRow.insert(g);
				x++;
			}
			//System.out.println("We are at " + x + " what row?? " + y);
		}
		
		return column;
	}
}
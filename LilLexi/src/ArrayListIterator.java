import java.util.List;
/**
 * 
 * @author Kate Nixon
 * 
 * Course: CSC 335 - David Claveau
 * 
 * Program: ArrayListIterator.java
 * Part of Overall Project: Lil Lexi
 * 
 * ArrayListIterator extends the abstract class Iterator
 * This allows Lil Lexi to iterate through the Glyphs without
 * caring if the glyphs are stored in an arrayLinked, linkedList,
 * etc.
 */
public class ArrayListIterator extends Iterator {
	protected List<Glyph> list;
	protected int pointer;
	
	/**
	 * Constructor. Takes in a ArrayList of Glyphs
	 * Sets pointer to index 0
	 * @param list
	 */
	public ArrayListIterator(List<Glyph> list) {
		this.list = list;
		pointer = 0;
	}

	/**
	 * sets pointer to first index, i.e. index 0
	 */
	@Override
	public void First() {
		pointer = 0;
	}

	/**
	 * sets pointer to next index
	 */
	@Override
	public void Next() {
		pointer++;
		
	}

	/**
	 * returns whether or not the list pointer
	 * points beyond the current list
	 */
	@Override
	public boolean isDone() {
		return pointer >= list.size();
	}

	/**
	 * dereferences the iterator to return the glyph it points to
	 */
	@Override
	public Glyph getCurrent() {
		System.out.println(list.toString() + " index " + pointer);
		if(pointer < list.size()) {
			return list.get(pointer);
		}
		return null;
	}
}

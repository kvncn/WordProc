import java.util.List;

public class ArrayListIterator extends Iterator {
	protected List<Glyph> list;
	protected int pointer;
	
	public ArrayListIterator(List<Glyph> list) {
		this.list = list;
		pointer = 0;
	}

	/**
	 * sets pointer to first index
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
	 * returns wheteher or not the list pointer
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
		if(pointer < list.size()) {
			return list.get(pointer);
		}
		return null;
	}

	

}

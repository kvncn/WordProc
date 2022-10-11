/**
 * 
 * @author Kate Nixon
 * 
 * CSC 335 - David
 * 
 * Program: Iterator.java
 * Overall Program: LilLexi.java
 * 
 * Abstract class of Iterator. Implemented by ArrayListIterator
 * 
 * Used to Iterate through any data structure
 *
 */
public abstract class Iterator {
	
	/**
	 * sets pointer to first object
	 */
	public abstract void First();
	
	/**
	 * sets pointer to next object
	 */
	public abstract void Next();
	
	/**
	 * returns boolean value if there are no
	 * more objects to iterate through
	 * @return
	 */
	public abstract boolean isDone();
	
	/**
	 * dereferences the iterator to return the glyph it points to
	 */
	public abstract Glyph getCurrent();
}

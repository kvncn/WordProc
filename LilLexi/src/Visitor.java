/**
 * 
 * @author Kate Nixon
 * 
 * CSC 335 - David
 * 
 * Program: Visitor.java
 * Overall Project: LilLexi
 * 
 * This is the abstract Visitor class.
 * This class implements the Visitor pattern
 * to visit each of the different Glyphs to enact
 * different operation on them.
 * 
 * For this project's purpose, the concrete implementation
 * of this Visitor will be the SpellingCheckingVisitor
 * which main purpose is to see if the characters are spelled
 * correctly
 *
 */

public abstract class Visitor {
	/**
	 * Visits Character
	 * @param c
	 */
	public abstract void visitCharacter(Character c);
	
	/**
	 * Visits Row
	 * @param r
	 */
	public abstract void vistRow(Row r);
	
	/**
	 * Visits GImage
	 * @param i
	 */
	public abstract void visitImage(GImage i);
	
	/**
	 * Visits GRectangle
	 * @param rect
	 */
	public abstract void visitRectangle(GRectangle rect);
	
	/**
	 * Visits Cursor
	 * @param cursor
	 */
	public abstract void visitCursor(Cursor cursor);
}

import org.eclipse.swt.graphics.Image;

public abstract class Visitor {
	public abstract void visitCharacter(Character c);
	public abstract void vistRow(Row r);
	public abstract void visitImage(Image i);
	public abstract void vistRectangle(GRectangle rect);
	public abstract void vistCursor(Cursor cursor);
}

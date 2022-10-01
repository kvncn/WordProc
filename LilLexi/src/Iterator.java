
public abstract class Iterator {
	
	public abstract void First();
	
	public abstract void Next();
	
	public abstract boolean isDone();
	
	public abstract Glyph getCurrent();
	
	public abstract void insert(Glyph g);
}

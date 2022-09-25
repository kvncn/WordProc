/**
 * Glyph
 */

/*
 * For now the glyph class only accepts strings (not 
 * really chars because we want backspace functionality 
 * here). Later on make character a subclass
 */
class Glyph 
{
	private String c;
	
	public Glyph(String c) 
	{
		this.c = c;
	}

	public String getChar() {return c;}
	public void setChar(String c) {this.c = c;}
}
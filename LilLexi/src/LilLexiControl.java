
/**
 * Controller
 */
public class LilLexiControl 
{
	private LilLexiDoc currentDoc;

	/**
	 * LilLexiControl
	 */
	public LilLexiControl(LilLexiDoc doc)
	{
		this.currentDoc = doc;
	}
	
	/**
	 * adds the string to the document
	 */
	void add(String c) 
	{	
		currentDoc.add(c);
	}
	
	void changeFont(String font) {
		currentDoc.changeFont(font);
	}
	
	void changeFontSize(int size) {
		currentDoc.changeFontSize(size);
	}
	
	/**
	 * adds the string to the document
	 */
	void add(int size) 
	{	
		currentDoc.add(size);
	}	
	
	/*
	 * Backspace
	 */
	void remove() {
		currentDoc.remove();
	}
	
	void undo() {
		currentDoc.undo();
	}
	
	/**
	 * quitEditor  user quits
	 */
	void  quitEditor() 
	{ 
		System.exit(0); 
	}	
}
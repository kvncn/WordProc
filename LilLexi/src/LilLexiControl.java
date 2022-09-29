/**
 * Controller
 */
public class LilLexiControl 
{
	private LilLexiDoc currentDoc;

	/**
	 * LilLexiControl
	 */
	public LilLexiControl( LilLexiDoc doc )
	{
		this.currentDoc = doc;
	}
	
	void remove() {
		currentDoc.remove();
	}
	
	/**
	 * adds the string to the document
	 */
	void add( String c ) 
	{	
		currentDoc.add(c);
	}	

	/**
	 * quitEditor  user quits
	 */
	void  quitEditor() 
	{ 
		System.exit(0); 
	}	
}
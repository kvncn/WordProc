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







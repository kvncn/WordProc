/*
* AUTHOR: Kevin Nisterenko
* FILE: LilLexi.java
* ASSIGNMENT: A2 - LilLexi
* COURSE: CSc 335; Fall 2022
* PURPOSE: This class sets up the application by initializing the
* appropriate objects in the static main class for LilLexi. Based
* on Claveau's version.
*
* There are no inputs for this specific file. 
*/

public class LilLexi
{
	static private LilLexiDoc currentDoc = null;

	public static void main(String args[])
	{
		// Singleton so that we only have one Document 
		if (currentDoc == null)
			currentDoc = new LilLexiDoc();
		
		// set up the UI and link it to the document
		LilLexiUI lexiUI = new LilLexiUI();
		lexiUI.setCurrentDoc( currentDoc );
		currentDoc.setUI(lexiUI);
		
		// set the control to the the UI
		LilLexiControl lexiControl = new LilLexiControl( currentDoc );
		lexiUI.setController( lexiControl );
		
		// start the application and its window
		lexiUI.start();
	} 
}
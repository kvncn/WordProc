/*
* AUTHOR: Kevin Nisterenko
* FILE: CanvasPaintListener.java
* ASSIGNMENT: A2 - LilLexi
* COURSE: CSc 335; Fall 2022
* PURPOSE: This class is an implementation of the PaintListener 
* class, it is slightly modified from the one given by the instructor.
* The use of this class is to draw the LilLexi window, particularly the glyphs 
* on the canvas. 
*
* There are no inputs for this specific file. 
*/

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * repaints the canvas
 * 
 */
public class CanvasPaintListener implements PaintListener 
{
    private Shell shell;
    private Display display;
    private LilLexiDoc currentDoc;
    
    /*
	 * Constructor for the CanvasPaintListener, sets the attributes based
	 * on the image list of the cards and the shell to then redraw the board
	 * when the appropriate method is called.
	 * 
	 * @param sh, Shell object representing the open GUI shell
	 * @param imList, List of Image objects containing the images of the 
	 * cards in the appropriate order
	 */
    public CanvasPaintListener(Shell sh, Display disp, LilLexiDoc doc)
    {
    	shell = sh; display = disp; currentDoc = doc;
    }
    
    /*
	 * This method takes a paint event (redraw and update) to redraw the 
	 * board on the screen. It iterated through the composition of glyphs 
	 * and draws them. 
	 * 
	 * @param event, PaintEvent object representing an event from the system
	 */
	public void paintControl(PaintEvent e) 
	{
		Rectangle rect = shell.getClientArea();
		e.gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE)); 
        e.gc.fillRectangle(rect.x, rect.y, rect.width, rect.height);
        e.gc.setForeground(display.getSystemColor(SWT.COLOR_BLUE)); 
 
		List<Glyph> glyphs = currentDoc.getGlyphs();
		for (Glyph g: glyphs)
		{
			// Only need to call the draw method from a glyph, worry not about which type of glyph it is!
			g.draw(e); 
		}
	}
}  
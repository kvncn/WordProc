/*
* AUTHOR: Kevin Nisterenko
* FILE: LilLexiUI.java
* ASSIGNMENT: A2 - LilLexi
* COURSE: CSc 335; Fall 2022
* PURPOSE: This class serves as the view (in an MVC idea) of the
* LilLexi application. Here is where the UI and shell are setup, as well
* as the listeners for any and all events that the user will interact with
* in the application (scrolling, commands, basic keyboard typing). It is
* a modified version of Claveau's version.
*
* There are no inputs for this specific file. 
*/

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.GridLayout;


/**
 * LilLexiUI
 * 
 */
public class LilLexiUI
{
	private LilLexiDoc currentDoc;
	private LilLexiControl lexiControl;
	private Display display;
	private Shell shell;
	private Canvas canvas;	
	
	/**
	 * Constructor for the UI of LilLexi, starts the basic layout and
	 * shell. 
	 */
	public LilLexiUI() 
	{
		//---- create the window and the shell
		Display.setAppName("Lil Lexi");
		display = new Display();  
		shell = new Shell(display);
	    shell.setText("Lil Lexi");
		shell.setSize(900,900);
		shell.setLayout(new GridLayout());	
	}
		
	/**
	 * Setups and starts the UI for LilLexi, has the basic listeners
	 * and commands asked in class, as well as the scrollbar decorator. 
	 */
	public void start() {	
		//---- create widgets for the interface
	    Composite upperComp = new Composite(shell, SWT.NO_FOCUS);
	    //Composite lowerComp = new Composite(shell, SWT.NO_FOCUS);
	    
	    //---- canvas for the document
		canvas = new Canvas(upperComp, SWT.V_SCROLL);
		canvas.setSize(800,800);
		
		// canvas paint listener, repaints canvas after redraw called
		canvas.addPaintListener(new CanvasPaintListener(shell, display, currentDoc));	
		
		
        // This key Listener is used to get a character from the keyboard and
		// then call the appropriate control additon (or removal if backspace)
        canvas.addKeyListener(new KeyAdapter()
		{	
			public void keyPressed(KeyEvent e)
			{
				String string = "";
				
				// backspace
				if(e.keyCode == SWT.BS)
				{
					lexiControl.remove();	
				}
				
				// just add a space to the string
				if (e.keyCode == SWT.SPACE) {
					string += " ";
				}
				
				// adds the character to the string
				if (e.keyCode != SWT.BS) {
					string += "" + e.character;
				}
				
				// if the string is not empty, add the glyph/char
				if(!string.equals("")) {
					System.out.println("ADDING!!!!");
					lexiControl.add(string);
				}
			}
		});
        
        // This is a decorator/embellishment
        // Since it is already encapsulated by SWT, no need to
        // setup a new abstract class 
        // DAVID GAVE THE OKAY FOR THIS ON OCT 4th
        Point origin = new Point(0,0);
        ScrollBar vBar = canvas.getVerticalBar();
        // adds a listener to the vertical bar, so we can scroll and 
        // change its position
        vBar.addListener(SWT.Selection, e -> {
        	int vSelection = vBar.getSelection();
    		int destY = -vSelection + origin.y;
    		canvas.scroll (0, destY, 0, 0, 800, 800, false);
    		origin.y = -vSelection;
        });
        
        // adds a resize listener to the canvas so we can shift it
        // based on the vertical bar's position
        canvas.addListener (SWT.Resize,  e -> {
    		Rectangle rect = canvas.getClientArea();
    		Rectangle client = canvas.getClientArea();
  
    		vBar.setMaximum (rect.height);
    		vBar.setThumb (Math.min (rect.height, client.height));
    		int vPage = rect.height - client.height;
    		int vSelection = vBar.getSelection ();
    
    		// Resets the vertical bar
    		if (vSelection >= vPage) {
    			if (vPage <= 0) vSelection = 0;
    			origin.y = -vSelection;
    		}
    	});
		
		
		// sets the commands for the menu, all choices are commands, as this
        // works to encapsulate their functionality
		//---- main menu
		Menu menuBar, fileMenu, insertMenu, fontMenu;
		MenuItem fileMenuHeader, insertMenuHeader, fontMenuHeader, fileExitItem;
		MenuItem fileUndoItem, fileRedoItem;
		MenuItem insertImageItem, insertRectItem;
		MenuItem fontSize14Item, fontSize24Item, fontCourierItem, fontFixedItem;

		menuBar = new Menu(shell, SWT.BAR);
		
		// File menu setup
		fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuHeader.setText("File");
		fileMenu = new Menu(shell, SWT.DROP_DOWN);
		fileMenuHeader.setMenu(fileMenu);
		
		// Exit, undo and redo widget setup
	    fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileExitItem.setText("Exit");
	    fileUndoItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileUndoItem.setText("Undo");
	    fileRedoItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileRedoItem.setText("Redo");
	    
	    // Insert menu setup
		insertMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		insertMenuHeader.setText("Insert");
		insertMenu = new Menu(shell, SWT.DROP_DOWN);
		insertMenuHeader.setMenu(insertMenu);
		
		// Font menu setup
		fontMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fontMenuHeader.setText("Font");
		fontMenu = new Menu(shell, SWT.DROP_DOWN);
		fontMenuHeader.setMenu(fontMenu);
		
		// font changer widgets' setup
		fontCourierItem = new MenuItem(fontMenu, SWT.PUSH);
		fontCourierItem.setText("Font: Courier");
		fontFixedItem = new MenuItem(fontMenu, SWT.PUSH);
		fontFixedItem.setText("Font: Fixed");
		fontSize14Item = new MenuItem(fontMenu, SWT.PUSH);
		fontSize14Item.setText("Font Size: 14");
		fontSize24Item = new MenuItem(fontMenu, SWT.PUSH);
		fontSize24Item.setText("Font Size: 24");
		
		// Insert an image setup
	    insertImageItem = new MenuItem(insertMenu, SWT.PUSH);
	    insertImageItem.setText("Image");
	    insertRectItem = new MenuItem(insertMenu, SWT.PUSH);
	    insertRectItem.setText("Rectangle");

	    // Font and Size Commands
	    fontCourierItem.addSelectionListener(new FontChanger("Courier", lexiControl));
	    
	    fontFixedItem.addSelectionListener(new FontChanger("Fixed", lexiControl));
	    
	    fontSize14Item.addSelectionListener(new SizeChanger(14, lexiControl));
	    
	    fontSize24Item.addSelectionListener(new SizeChanger(24, lexiControl));
	    
	    // Closes the file with the command
	    fileExitItem.addSelectionListener(new ExitCommand(shell, display, lexiControl));
	    
	    // Adds a rectangle to the lexi file
	    insertRectItem.addSelectionListener(new RectCommand(50, lexiControl));
	    
	    // Adds an image to the lexi file
	    insertImageItem.addSelectionListener(new ImageCommand(display, lexiControl));
	    
	    fileUndoItem.addSelectionListener(new UndoCommand(lexiControl));
	    
	    fileRedoItem.addSelectionListener(new RedoCommand(lexiControl));

        MenuItem[] mi = menuBar.getItems();
        mi[0].addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event){
            	System.out.println("About");
            }
        });
	    
        // Sets the menu to the shell
	    shell.setMenuBar(menuBar);
	      
		//---- event loop
		shell.open();
		while( !shell.isDisposed())
			if(!display.readAndDispatch()){}
		display.dispose();
	} 

	/**
	 * Calls the canvas redraw method to update the UI to any changes
	 * made to the document or to it.
	 */
	public void updateUI() {
		System.out.println("updateUI");
		canvas.redraw();
	}
	
	/**
	 * Sets the current LilLexiDoc (Model) of the application.
	 * 
	 * @param cd, LilLexiDoc object representing current model 
	 * of the document.
	 */
	public void setCurrentDoc(LilLexiDoc cd) { currentDoc = cd; }
	
	/**
	 * Sets the current LilLexiControl (Controller) of the application.
	 * 
	 * @param lc, LilLexiControl object representing current controller 
	 * of the document.
	 */
	public void setController(LilLexiControl lc) { lexiControl = lc; }
}
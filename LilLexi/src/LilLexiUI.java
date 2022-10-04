/**
 * UI for Lil Lexi
 * 
 */
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Font;
import java.util.List;

// Just so it pushes

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
	 * Ctor
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
	 * start the editor
	 */
	public void start()
	{	
		//---- create widgets for the interface
	    Composite upperComp = new Composite(shell, SWT.NO_FOCUS);
	    //Composite lowerComp = new Composite(shell, SWT.NO_FOCUS);
	    
	    //---- canvas for the document
		canvas = new Canvas(upperComp, SWT.NONE);
		canvas.setSize(800,800);
		
		
		// This is likely involved with the compositor/strategy pattern
		// canvas paint listener, repaints canvas after redraw called
		canvas.addPaintListener(new CanvasPaintListener(shell, display, currentDoc));	
		
		/*
        canvas.addMouseListener(new MouseListener() {
            public void mouseDown(MouseEvent e) {
            	System.out.println("mouseDown in canvas");
            } 
            public void mouseUp(MouseEvent e) {} 
            public void mouseDoubleClick(MouseEvent e) {} 
        });
        */
        // This gets the character from keyboard
        canvas.addKeyListener(new KeyAdapter()
		{	
			public void keyPressed(KeyEvent e)
			{
				String string = "";
				
				//check click together? maybe for undo idk
				//if ((e.stateMask & SWT.ALT) != 0) string += "" + e.keyCode;
				//if ((e.stateMask & SWT.CTRL) != 0) string += "" + e.keyCode;
				//if ((e.stateMask & SWT.SHIFT) != 0) string += "" + e.keyCode;
			
				// backspace
				if(e.keyCode == SWT.BS)
				{
					lexiControl.remove();	
					canvas.update();
					canvas.redraw();
				}
				
				/*
				if(e.keyCode == SWT.ESC)
				{
					string += "" + e.keyCode;
				}
				*/
				
				if (e.keyCode == SWT.SPACE) {
					string += " ";
				}
				
				if (e.keyCode != SWT.BS) {
					string += "" + e.character;
				}
				
				if(!string.equals("")) {
					System.out.println("ADDING!!!!");
					lexiControl.add(string);
				}
			}
		});
        
        // This is a decorator/embellishment
		Slider slider = new Slider(canvas, SWT.VERTICAL);
		Rectangle clientArea = canvas.getClientArea();
		slider.setBounds (clientArea.width - 40, clientArea.y + 10, 32, clientArea.height);
		slider.setMinimum(0);
		slider.setMaximum(clientArea.y);
		slider.addListener (SWT.Selection, event -> {
			String string = "SWT.NONE";
			switch (event.detail) {
				case SWT.DRAG: 
					{
						string = "SWT.DRAG"; 
						break;
					}
				case SWT.HOME: string = "SWT.HOME"; break;
				case SWT.END: string = "SWT.END"; break;
				case SWT.ARROW_DOWN: string = "SWT.ARROW_DOWN"; break;
				case SWT.ARROW_UP: string = "SWT.ARROW_UP"; break;
				case SWT.PAGE_DOWN: string = "SWT.PAGE_DOWN"; break;
				case SWT.PAGE_UP: string = "SWT.PAGE_UP"; break;
			}
			System.out.println ("Scroll detail -> " + string);
		});
		slider.setIncrement(10);
		
		
		// These will follow the command structure/pattern, they seem apt candidates for this
		//---- main menu
		Menu menuBar, fileMenu, insertMenu, fontMenu, helpMenu;
		MenuItem fileMenuHeader, insertMenuHeader, fontMenuHeader, helpMenuHeader, fileExitItem, fileSaveItem, helpGetHelpItem;
		MenuItem fileUndoItem, fileRedoItem;
		MenuItem insertImageItem, insertRectItem;
		MenuItem fontSize14Item, fontSize24Item, fontCourierItem, fontFixedItem;

		menuBar = new Menu(shell, SWT.BAR);
		
		fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuHeader.setText("File");
		fileMenu = new Menu(shell, SWT.DROP_DOWN);
		fileMenuHeader.setMenu(fileMenu);
		
	    fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileExitItem.setText("Exit");
	    fileUndoItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileUndoItem.setText("Undo");
	    fileRedoItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileRedoItem.setText("Redo");
	    
	    // Working
		insertMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		insertMenuHeader.setText("Insert");
		insertMenu = new Menu(shell, SWT.DROP_DOWN);
		insertMenuHeader.setMenu(insertMenu);
		
		//font
		fontMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fontMenuHeader.setText("Font");
		fontMenu = new Menu(shell, SWT.DROP_DOWN);
		fontMenuHeader.setMenu(fontMenu);
		
		fontCourierItem = new MenuItem(fontMenu, SWT.PUSH);
		fontCourierItem.setText("Font: Courier");
		fontFixedItem = new MenuItem(fontMenu, SWT.PUSH);
		fontFixedItem.setText("Font: Fixed");
		fontSize14Item = new MenuItem(fontMenu, SWT.PUSH);
		fontSize14Item.setText("Font Size: 14");
		fontSize24Item = new MenuItem(fontMenu, SWT.PUSH);
		fontSize24Item.setText("Font Size: 24");
		
		// TBD Functionality
	    insertImageItem = new MenuItem(insertMenu, SWT.PUSH);
	    insertImageItem.setText("Image");
	    insertRectItem = new MenuItem(insertMenu, SWT.PUSH);
	    insertRectItem.setText("Rectangle");
	    
	    // Useless
	    helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    helpMenuHeader.setText("Help");
	    helpMenu = new Menu(shell, SWT.DROP_DOWN);
	    helpMenuHeader.setMenu(helpMenu);

	    helpGetHelpItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpGetHelpItem.setText("Get Help");
	    
	    //font listeners
	    fontCourierItem.addSelectionListener(new FontChanger("Courier", lexiControl));
	    
	    fontFixedItem.addSelectionListener(new FontChanger("Fixed", lexiControl));
	    
	    fontSize14Item.addSelectionListener(new SizeChanger(14, lexiControl));
	    
	    fontSize14Item.addSelectionListener(new SizeChanger(24, lexiControl));
	    
	    // Closes the file
	    fileExitItem.addSelectionListener(new ExitCommand(shell, display, lexiControl));
	    
	    // Adds a rectangle to the lexi file
	    insertRectItem.addSelectionListener(new RectCommand(50, lexiControl));
	    
	    // Adds an image to the lexi file
	    insertImageItem.addSelectionListener(new ImageCommand(display, lexiControl));
	    
	    fileUndoItem.addSelectionListener(new UndoCommand(lexiControl));
	    
	    fileRedoItem.addSelectionListener(new RedoCommand(lexiControl));
	    
        //Menu systemMenu = Display.getDefault().getSystemMenu();
        MenuItem[] mi = menuBar.getItems();
        mi[0].addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event){
            	System.out.println("About");
            }
        });
	    
	    shell.setMenuBar(menuBar);
	      
		//---- event loop
		shell.open();
		while( !shell.isDisposed())
			if(!display.readAndDispatch()){}
		display.dispose();
	} 

	/**
	 * updateUI
	 */
	public void updateUI()
	{
		System.out.println("updateUI");
		canvas.redraw();
	}
	
	/**
	 * setCurrentDoc
	 */
	public void setCurrentDoc(LilLexiDoc cd) { currentDoc = cd; }
	/**
	 * setController
	 */
	public void setController(LilLexiControl lc) { lexiControl = lc; }
}
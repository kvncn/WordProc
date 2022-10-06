/*
* AUTHOR: Kevin Nisterenko
* FILE: Command.java
* ASSIGNMENT: A2 - LilLexi
* COURSE: CSc 335; Fall 2022
* PURPOSE: This file sets the abstract Command class and its concrete
* subclasses for this assignment. The Command is an implementation of
* the SWT SelectionListener for a seamless integration with the UI 
* framework used. 
*
* There are no inputs for this specific file. 
*/

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/** 
 * Because we are using SWT UI framework, command will implement
 * a listener so it can cause changes on the display in a more
 * seamless manner. 
 *
 */
public abstract class Command implements SelectionListener {
	protected LilLexiControl lexiControl;
	
	/**
	 * Base constructor for the Command superclass.
	 * 
	 * @param control, LilLexiControl object, so we can actively 
	 * use the commands to control the document
	 */
	public Command(LilLexiControl control) {
		this.lexiControl = control;
	}
	
	/**
	 * Overriding the selection events, will be different
	 * for each concrete subclass.
	 */
	
	@Override
	public abstract void widgetDefaultSelected(SelectionEvent arg0);
	
	@Override
	public abstract void widgetSelected(SelectionEvent arg0);
	
}

class FontChanger extends Command {
	
	private String font; 
	
	/**
	 * Calls the super constructor for the basic functionality,
	 * also takes in a string representing the new font for the
	 * document. 
	 * 
	 * @param font, String representing the new font of the 
	 * document
	 * @param control, LilLexiControl object, so we can actively 
	 * use the commands to control the document
	 */
	public FontChanger(String font, LilLexiControl control) {
		super(control);
		this.font = font;
	}

	/**
	 * This method calls the control's font changer based on the
	 * the font to be changed to (constructor passed). 
	 * 
	 * @param arg0, SelectionEvent representing a user selection
	 * of a widget
	 */
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		lexiControl.changeFont(font);
		
	}
	
	/**
	 * This method calls the control's font changer based on the
	 * the font to be changed to (constructor passed). 
	 * 
	 * @param arg0, SelectionEvent representing a user selection
	 * of a widget
	 */
	@Override
	public void widgetSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		lexiControl.changeFont(font);
	}
	
}

class SizeChanger extends Command {
	
	private int fontSize; 
	
	/**
	 * Calls the super constructor for the basic functionality,
	 * also takes in a string representing the new font size for the
	 * document. 
	 * 
	 * @param size, int representing the new font size
	 * @param control, LilLexiControl object, so we can actively 
	 * use the commands to control the document
	 */
	public SizeChanger(int size, LilLexiControl control) {
		super(control);
		fontSize = size;
	}

	/**
	 * This method calls the control's font size changer based on the
	 * the font size to be changed to (constructor passed). 
	 * 
	 * @param arg0, SelectionEvent representing a user selection
	 * of a widget
	 */
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		lexiControl.changeFontSize(fontSize);
		
	}
	
	/**
	 * This method calls the control's font size changer based on the
	 * the font size to be changed to (constructor passed). 
	 * 
	 * @param arg0, SelectionEvent representing a user selection
	 * of a widget
	 */
	@Override
	public void widgetSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		lexiControl.changeFontSize(fontSize);
	}
	
}

class ExitCommand extends Command {
	
	private Shell shell;
	private Display display;
	
	/**
	 * Calls the super constructor for the basic functionality,
	 * also takes in GUI specific objects to dispose the screen and
	 * close the application.
	 * 
	 * @param sh, Shell object used to call the dispose method to
	 * stop the GUI
	 * @param disp, Display object used to call the dispose method 
	 * to stop the GUI.
	 * @param control, LilLexiControl object, so we can actively 
	 * use the commands to control the document
	 */
	public ExitCommand(Shell sh, Display disp, LilLexiControl control) {
		super(control);
		shell = sh;
		display = disp;
	}
	
	/**
	 * This method exits the application and calls the control's
	 * quit method to stop the program.
	 * 
	 * @param arg0, SelectionEvent representing a user selection
	 * of a widget
	 */
	public void widgetSelected(SelectionEvent arg0) {
		shell.close();
		display.dispose();
		lexiControl.quitEditor();
		
	}
	
	/**
	 * This method exits the application and calls the control's
	 * quit method to stop the program.
	 * 
	 * @param arg0, SelectionEvent representing a user selection
	 * of a widget
	 */
	public void widgetDefaultSelected(SelectionEvent arg0) {
		shell.close();
		display.dispose();
		lexiControl.quitEditor();
	}
}

class RectCommand extends Command {
	
	private int size;
	
	/**
	 * Calls the super constructor for the basic functionality,
	 * also takes in an int to add a new rectangle to the document 
	 * with that int's size.
	 * 
	 * @param size, int representing the rectangle size
	 * @param control, LilLexiControl object, so we can actively 
	 * use the commands to control the document
	 */
	public RectCommand(int size, LilLexiControl control) {
		super(control);
		this.size = size;
	}
	
	/**
	 * This method calls the control's add method to add a
	 * rectangle based on the size (constructor passed). 
	 * 
	 * @param arg0, SelectionEvent representing a user selection
	 * of a widget
	 */
	public void widgetSelected(SelectionEvent arg0) {
		lexiControl.add(size);
	}
	
	/**
	 * This method calls the control's add method to add a
	 * rectangle based on the size (constructor passed). 
	 * 
	 * @param arg0, SelectionEvent representing a user selection
	 * of a widget
	 */
	public void widgetDefaultSelected(SelectionEvent arg0) {
		lexiControl.add(size);
	}
}

class ImageCommand extends Command {
	
	private Display display;
	
	/**
	 * Calls the super constructor for the basic functionality,
	 * also takes in a Display so that it can add an image to the
	 * screen.
	 * 
	 * @param disp, Display object so that the image can be added to 
	 * the screen
	 * @param control, LilLexiControl object, so we can actively 
	 * use the commands to control the document
	 */
	public ImageCommand(Display disp, LilLexiControl control) {
		super(control);
		display = disp;
	}
	
	/**
	 * This method calls the control's add method to add an image to 
	 * the canvas.
	 * 
	 * @param arg0, SelectionEvent representing a user selection
	 * of a widget
	 */
	public void widgetSelected(SelectionEvent arg0) {
		lexiControl.add(display);
	}
	
	/**
	 * This method calls the control's add method to add an image to 
	 * the canvas.
	 * 
	 * @param arg0, SelectionEvent representing a user selection
	 * of a widget
	 */
	public void widgetDefaultSelected(SelectionEvent arg0) {
		lexiControl.add(display);
	}
}

class UndoCommand extends Command {
	
	/**
	 * Calls the super constructor for the basic functionality,
	 * it is used to undo the latest command.
	 * 
	 * @param control, LilLexiControl object, so we can actively 
	 * use the commands to control the document
	 */
	public UndoCommand(LilLexiControl control) {
		super(control);
	}
	
	/**
	 * This method calls the control's undo method to undo 
	 * latest change to the document.
	 * 
	 * @param arg0, SelectionEvent representing a user selection
	 * of a widget
	 */
	public void widgetSelected(SelectionEvent arg0) {
		lexiControl.undo();
	}
	
	/**
	 * This method calls the control's undo method to undo 
	 * latest change to the document.
	 * 
	 * @param arg0, SelectionEvent representing a user selection
	 * of a widget
	 */
	public void widgetDefaultSelected(SelectionEvent arg0) {
		lexiControl.undo();
	}
}

class RedoCommand extends Command {
	
	/**
	 * Calls the super constructor for the basic functionality,
	 * it is used to undo the latest command.
	 * 
	 * @param control, LilLexiControl object, so we can actively 
	 * use the commands to control the document
	 */
	public RedoCommand(LilLexiControl control) {
		super(control);
	}
	
	/**
	 * This method calls the control's redo method to redo 
	 * the latest command after a removal.
	 * 
	 * @param arg0, SelectionEvent representing a user selection
	 * of a widget
	 */
	public void widgetSelected(SelectionEvent arg0) {
		lexiControl.redo();
	}
	
	/**
	 * This method calls the control's redo method to redo 
	 * the latest command after a removal.
	 * 
	 * @param arg0, SelectionEvent representing a user selection
	 * of a widget
	 */
	public void widgetDefaultSelected(SelectionEvent arg0) {
		lexiControl.redo();
	}
}
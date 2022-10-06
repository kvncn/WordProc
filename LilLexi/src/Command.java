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
	
	public Command(LilLexiControl control) {
		super();
		this.lexiControl = control;
	}
	
	@Override
	public abstract void widgetDefaultSelected(SelectionEvent arg0);
	
	@Override
	public abstract void widgetSelected(SelectionEvent arg0);
	
}

class FontChanger extends Command {
	
	private String font; 
	
	public FontChanger(String font, LilLexiControl control) {
		super(control);
		this.font = font;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		lexiControl.changeFont(font);
		
	}
	@Override
	public void widgetSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		lexiControl.changeFont(font);
	}
	
}

class SizeChanger extends Command {
	
	private int fontSize; 
	
	public SizeChanger(int size, LilLexiControl control) {
		super(control);
		fontSize = size;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		lexiControl.changeFontSize(fontSize);
		
	}
	@Override
	public void widgetSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		lexiControl.changeFontSize(fontSize);
	}
	
}

class ExitCommand extends Command {
	
	private Shell shell;
	private Display display;
	
	public ExitCommand(Shell sh, Display disp, LilLexiControl control) {
		super(control);
		shell = sh;
		display = disp;
	}
	public void widgetSelected(SelectionEvent event) {
		shell.close();
		display.dispose();
	}
	public void widgetDefaultSelected(SelectionEvent event) {
		shell.close();
		display.dispose();
	}
}

class RectCommand extends Command {
	private int size;
	
	public RectCommand(int size, LilLexiControl control) {
		super(control);
		this.size = size;
	}
	
	public void widgetSelected(SelectionEvent event) {
		lexiControl.add(size);
	}
	
	public void widgetDefaultSelected(SelectionEvent event) {
		lexiControl.add(size);
	}
}

class ImageCommand extends Command {
	private int size;
	private Display display;
	
	public ImageCommand(Display disp, LilLexiControl control) {
		super(control);
		display = disp;
	}
	
	public void widgetSelected(SelectionEvent event) {
		lexiControl.add(display);
	}
	
	public void widgetDefaultSelected(SelectionEvent event) {
		lexiControl.add(display);
	}
}

class UndoCommand extends Command {
	public UndoCommand(LilLexiControl control) {
		super(control);
	}
	
	public void widgetSelected(SelectionEvent event) {
		lexiControl.undo();
	}
	
	public void widgetDefaultSelected(SelectionEvent event) {
		lexiControl.undo();
	}
}

class RedoCommand extends Command {
	public RedoCommand(LilLexiControl control) {
		super(control);
	}
	
	public void widgetSelected(SelectionEvent event) {
		lexiControl.redo();
	}
	
	public void widgetDefaultSelected(SelectionEvent event) {
		lexiControl.redo();
	}
}
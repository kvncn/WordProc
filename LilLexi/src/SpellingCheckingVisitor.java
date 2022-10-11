import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.swt.graphics.Image;
/**
 * 
 * @author Kate Nixon
 * 
 * CSC 335 - David
 * 
 * Program: SpellingCheckingVisitor.java
 * Overall Project: LilLexi
 * 
 * This is the concrete instantiation of Visitor class.
 * This class implements the Visitor pattern
 * to visit each of the different Glyphs.
 * 
 * The main purpose is to see if the characters are spelled
 * correctly.
 * 
 * Other glyphs being visited will have nothing done to them
 *
 */
public class SpellingCheckingVisitor extends Visitor {
	
	protected String curWord;
	protected static List<Character> curLetters;
	protected Set<String> dict;
	
	/**
	 * Constructor sets up a String to contain the
	 * curWord, a list<Glyph> that are the curLetters,
	 * and a small dictionary
	 */
	public SpellingCheckingVisitor() {
		curWord = "";
		curLetters = new ArrayList<>();
		try {
			dict = getDict();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Visits the character c and adds the String to the curWord
	 * and the glyph to curLetters.
	 * If the current character is not alphabetic, then
	 * it will check if the word is recognized in the
	 * dictionary or not
	 */
	@Override
	public void visitCharacter(Character c) {
		//System.out.println("char is " +c.getChar());
		if(java.lang.Character.isAlphabetic(c.getChar().charAt(0))) {
			curWord += c.getChar();
			curLetters.add(c);
		}else {
			if(isMisspelled(curWord)) {
				setMisspelled();
			}
			curWord = "";
			curLetters.clear();
		}
		
	}
	/**
	 * returns whether the string is in the dictionary
	 * @param word
	 * @return
	 */
	private boolean isMisspelled(String word) {
		return !dict.contains(word.toLowerCase());
	}
	
	/**
	 * creates a dictionary based on dict2.txt
	 * a small dictionary of 39,000+ words
	 * @return
	 * @throws FileNotFoundException
	 */
	private static Set<String> getDict() throws FileNotFoundException {
        Set<String> dict = new HashSet<>();
        Scanner scanner = new Scanner(new File("dict2.txt"));
        while (scanner.hasNext()) {
            dict.add(scanner.nextLine().toLowerCase());
        }
        scanner.close();
        return dict;
	}
	
	/**
	 * set each character to misspelled
	 */
	private void setMisspelled() {
		for(Character c : curLetters) {
			c.setMisspelled();
		}
	}

	/**
	 * stub for Row
	 */
	@Override
	public void vistRow(Row r) {
			
	}
	
	/**
	 * stub for GImage
	 */
	@Override
	public void visitImage(GImage i) {
			
	}

	/**
	 * stub for GRectangle
	 */
	@Override
	public void visitRectangle(GRectangle rect) {
	}

	/**
	 * stub for Cursor
	 */
	@Override
	public void visitCursor(Cursor cursor) {
		
	}

}

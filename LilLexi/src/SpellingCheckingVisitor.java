import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.swt.graphics.Image;

public class SpellingCheckingVisitor extends Visitor {
	
	String curWord;
	protected static List<Character> curLetters;
	Set<String> dict;
	
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


	@Override
	public void visitCharacter(Character c) {
		System.out.println("char is " +c.getChar());
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
	
	private boolean isMisspelled(String word) {
		return !dict.contains(word.toLowerCase());
	}
	
	private static Set<String> getDict() throws FileNotFoundException {
        Set<String> dict = new HashSet<>();
        Scanner scanner = new Scanner(new File("dict2.txt"));
        while (scanner.hasNext()) {
            dict.add(scanner.nextLine().toLowerCase());
        }
        scanner.close();
        return dict;
	}
	
	private void setMisspelled() {
		System.out.println("curletters " + curLetters.size());
		for(Character c : curLetters) {
			System.out.println("flag");
			c.setMisspelled();
		}
	}

	@Override
	public void vistRow(Row r) {
		// TODO Auto-generated method stub
			
	}

	@Override
	public void visitImage(Image i) {
		// TODO Auto-generated method stub
			
	}

	@Override
	public void visitRectangle(GRectangle rect) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitCursor(Cursor cursor) {
		// TODO Auto-generated method stub
		
	}
		

}


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.swt.graphics.Image;

public class SpellingCheckingVisitor extends Visitor {

		private static Set<String> getDict() throws FileNotFoundException {
        Set<String> dict = new HashSet<>();
        Scanner scanner = new Scanner(new File("dict2.txt"));
        while (scanner.hasNext()) {
            dict.add(scanner.nextLine().toLowerCase());
        }
        scanner.close();
        return dict;
    }

		@Override
		public void visitCharacter(Character c) {
			// TODO Auto-generated method stub
			
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
		public void vistRectangle(GRectangle rect) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void vistCursor(Cursor cursor) {
			// TODO Auto-generated method stub
			
		}
		

}

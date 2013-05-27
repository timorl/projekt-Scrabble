package pl.edu.uj.tcs.matematycy2013;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import org.junit.*;
import static org.junit.Assert.*;

public class BagTest {

    @Test
    public void testBasic() throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("letters-set.txt");
        Bag bag = new Bag(is);

        assertEquals(8, bag.getSize());

        Letter[] letters = new Letter[2];
        letters[0] = new Letter('c', 2);
        letters[1] = new Letter('a', 1);
        bag.addLetters(letters);

        assertEquals(10, bag.getSize());
        //Not anymore
        //assertEquals(null, bag.getLetters(11));

        bag.getLetters(10);
        assertEquals(0, bag.getSize());

        letters = new Letter[1];
        letters[0] = new Letter('a', '1');
        bag.addLetters(letters);

        assertEquals(letters[0], bag.getLetters(1)[0]);
    }
}

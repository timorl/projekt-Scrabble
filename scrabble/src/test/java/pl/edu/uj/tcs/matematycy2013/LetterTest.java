package pl.edu.uj.tcs.matematycy2013;

import org.junit.*;
import static org.junit.Assert.*;

public class LetterTest {

    @Test
    public void testBasic() {
        Letter l = new Letter('a', 1);
        assertEquals('A', l.getChar() );
        assertEquals(1, l.getValue() );
        l.setChar('b');
        assertEquals('B', l.getChar() );
    }

    @Test
    public void testUnicode() {
        Letter l = new Letter('ą', 1);
        assertEquals('Ą', l.getChar() );
        l.setChar('悪');
        assertEquals('悪', l.getChar() );
        l.setChar('م');
        assertEquals('م', l.getChar() );
    }

}

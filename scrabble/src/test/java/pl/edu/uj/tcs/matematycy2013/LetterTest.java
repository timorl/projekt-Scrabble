package pl.edu.uj.tcs.matematycy2013;

import org.junit.*;
import static org.junit.Assert.*;

public class LetterTest {

    @Test
    public void testBasic() {
        Letter l = new Letter('a', 1);
        assertEquals('a', l.getChar() );
        assertEquals(1, l.getValue() );
        l.setChar('b');
        assertEquals('b', l.getChar() );
    }

    @Test
    public void testUnicode() {
        Letter l = new Letter('ą', 1);
        assertEquals('ą', l.getChar() );
        l.setChar('悪');
        assertEquals('悪', l.getChar() );
        l.setChar('م');
        assertEquals('م', l.getChar() );
    }

}

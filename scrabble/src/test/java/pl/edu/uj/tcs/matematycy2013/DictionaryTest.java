package pl.edu.uj.tcs.matematycy2013;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.InputStream;

public class DictionaryTest {

    @Test
    public void testBasic() {
        InputStream is = Dictionary.class.getResourceAsStream("words-pl.txt");
        Dictionary d = new Dictionary(is);
        assertEquals(true, d.checkWord("aa") );
        assertEquals(true, d.checkWord("pies") );
        assertEquals(true, d.checkWord("abdykacja") );
        assertEquals(true, d.checkWord("eufemizm") );
        assertEquals(true, d.checkWord("żywność") );
        assertEquals(true, d.checkWord("trzmiel") );
        assertEquals(false, d.checkWord("wyrąbisty") );
        assertEquals(false, d.checkWord("ultrawypas") );
        assertEquals(false, d.checkWord("exquisite") );
        assertEquals(false, d.checkWord("شر") );
        assertEquals(false, d.checkWord("Böse") );
        assertEquals(false, d.checkWord("悪") );
    }

}

package pl.edu.uj.tcs.matematycy2013;

import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.*;

import java.io.InputStream;

public class DictionaryTest {

    @Test
    public void testBasic() throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("words-pl.txt");
        Alphabet alphabet = new Alphabet();
        alphabet.addLetters(new char[]{'a','ą','b','c','ć','d','e','ę','f','g','h','i','j','k','l','ł','m','n','ń','o','ó','p','r','s','ś','t','u','w','y','z','ź','ż','v','x','q'});
        Dictionary d = new Dictionary(is,alphabet);
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
    
    @Test
    public void englishDictionaryTest() throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("words-en.txt");
        Alphabet alphabet = new Alphabet(Thread.currentThread().getContextClassLoader().getResourceAsStream("letters-en.txt"));
        Dictionary d = new Dictionary(is,alphabet);
        assertEquals(true, d.checkWord("bugs") );
        assertEquals(true, d.checkWord("threads") );
        assertEquals(true, d.checkWord("semaphores") );
        assertEquals(true, d.checkWord("programming") );
        assertEquals(true, d.checkWord("dictionary") );
        assertEquals(true, d.checkWord("efficiency") );
        assertEquals(false, d.checkWord("wyrąbisty") );
        assertEquals(false, d.checkWord("trzmiel") );
        assertEquals(true, d.checkWord("exquisite") );
        assertEquals(false, d.checkWord("شر") );
        assertEquals(false, d.checkWord("Böse") );
        assertEquals(false, d.checkWord("悪") );
        assertEquals(true, d.checkWord("pies") );
        assertEquals(false, d.checkWord("abdykacja") );
        assertEquals(false, d.checkWord("eufemizm") );
        assertEquals(false, d.checkWord("żywność") );
        assertEquals(true, d.checkWord("availability") );
        assertEquals(true, d.checkWord("integrity") );
        assertEquals(true, d.checkWord("confidentiality") );        
    }

}

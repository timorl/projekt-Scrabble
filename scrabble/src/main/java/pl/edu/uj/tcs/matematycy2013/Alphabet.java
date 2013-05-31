package pl.edu.uj.tcs.matematycy2013;

import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Alphabet {

    private LinkedList<Character> alphabet;

    public Alphabet() {
        alphabet = new LinkedList<Character>();
    }

    public Alphabet(InputStream is) throws IOException {
        alphabet = new LinkedList<Character>();
        LinkedList<Character> aLToAdd = new LinkedList<Character>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String line;
        while ( (line = reader.readLine()) != null ) {
            if(line.charAt(0) != '*') {
                aLToAdd.add( line.charAt(0) );
            }
        }
        char[] toAdd = new char[aLToAdd.size()];
        for ( int i = 0; i < toAdd.length; i++ ) {
            toAdd[i] = aLToAdd.get(i).charValue();
        }
        addLetters(toAdd);
    }

    public void addLetters(char[] letters) {
        for (int i = 0; i < letters.length; ++i) {
            Character newOne = new Character(letters[i]);
            if (alphabet.contains(newOne)) {
                continue;
            }
            alphabet.add(newOne);
        }
    }

    public Character[] getLetters() {
        Character[] result = new Character[alphabet.size()];
        int i = 0;
        for ( Character c : alphabet ) {
            result[i++] = new Character(c);
        }
        return result;
    }

    public int getCharacterLabel(char c) {
        return alphabet.indexOf(c);
    }

    public int getSize() {
        return alphabet.size();
    }
}

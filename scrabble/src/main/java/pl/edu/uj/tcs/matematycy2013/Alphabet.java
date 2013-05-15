package pl.edu.uj.tcs.matematycy2013;

import java.util.LinkedList;

public class Alphabet {

    private LinkedList<Character> alphabet;

    public Alphabet() {
        alphabet = new LinkedList<Character>();
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

    public int getCharacterLabel(char c) {
        return alphabet.indexOf(c);
    }

    public int getSize() {
        return alphabet.size();
    }
}
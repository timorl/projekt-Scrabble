package pl.edu.uj.tcs.matematycy2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

public class Bag {

    private LinkedList<Letter> bag;

    public Bag() {
        bag = new LinkedList<Letter>();
    }

    public Bag(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] t = line.split(" ");
            bag.add(new Letter(line.charAt(0), Integer.parseInt(t[1])));
        }

        reader.close();
    }

    public int getSize() {
        return bag.size();
    }

    public Letter[] getLetters(int amount) {
        if (amount > getSize()) {
            return null;
        }

        Letter[] letters = new Letter[amount];
        Collections.shuffle(bag);

        for (int i = 0; i < amount; ++i) {
            letters[i] = bag.pollLast();
        }

        return letters;
    }

    public void addLetters(Letter[] toAdd) {
        for (int i = 0; i < toAdd.length; ++i) {
            bag.add(toAdd[i]);
        }
    }
}

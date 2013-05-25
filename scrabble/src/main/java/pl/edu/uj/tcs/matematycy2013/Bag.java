package pl.edu.uj.tcs.matematycy2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Bag {

    private final LinkedList<Letter> bag;

    //temporary constructor
    public Bag() {
        bag = new LinkedList<Letter>();
        Random rand =new Random();
        for(int i=0;i<100;i++)
        	bag.add(new Letter((char)(rand.nextInt(24)+'A'),i%10));
    }

    public Bag(InputStream stream) throws IOException {
        bag = new LinkedList<Letter>();
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
        /*if (amount > getSize()) {
            return null;
        }*/

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

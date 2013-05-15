package pl.edu.uj.tcs.matematycy2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Dictionary {

    private Tree tree;

    public Dictionary(InputStream stream, Alphabet alphabet) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        tree = new Tree(alphabet);

        String word;
        while ((word = reader.readLine()) != null) {
            tree.insert(word);
        }

        reader.close();
    }

    public boolean checkWord(String toCheck) {
        return tree.search(toCheck);
    }
}

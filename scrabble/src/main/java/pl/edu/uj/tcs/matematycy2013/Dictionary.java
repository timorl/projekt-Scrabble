package pl.edu.uj.tcs.matematycy2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Dictionary {

    private Tree tree;

    //needed for testing only
    public Dictionary(){
    }

    public Dictionary(InputStream stream, Alphabet alphabet, Runnable afterLoaded) throws IOException  {
        tree = new Tree(alphabet);
        final InputStream fStream = stream;
        final Runnable fAfterLoaded = afterLoaded;
        new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    load(fStream);
                    fAfterLoaded.run();
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public Dictionary(InputStream stream, Alphabet alphabet) throws IOException {
        this(stream, alphabet, new Runnable() {
            @Override
            public void run(){}
        });
    }

    private void load(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        String word;
        while ((word = reader.readLine()) != null) {
            tree.insert(word);
        }
        reader.close();
    }

    public boolean checkWord(String toCheck) {
        if(tree == null){
            return true;
        }
        return tree.search(toCheck.toLowerCase());
    }
}

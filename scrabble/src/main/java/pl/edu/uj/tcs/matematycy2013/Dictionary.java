package pl.edu.uj.tcs.matematycy2013;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Dictionary {

    private Tree tree;

    //needed for testing only
    public Dictionary(){
    }

    public Dictionary(Config config, Alphabet alphabet) throws IOException  {

    	//false if deserialization succeeds
    	boolean createDictionaryTree=true;
    	try {
    		ObjectInputStream in = new ObjectInputStream(new FileInputStream("words-" + config.getDictionaryType() + ".in"));
        	try {
				tree=(Tree)in.readObject();
				createDictionaryTree=false;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				in.close();
			}
    	} catch(Exception e) {
    		System.out.println("not saved dictionaries");
    	}
    	if(createDictionaryTree) {
    		InputStream stream=config.getDictionaryStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            tree = new Tree(alphabet);

            String word;
            while ((word = reader.readLine()) != null) {
                tree.insert(word);
            }

            reader.close();
            ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("words-" + config.getDictionaryType() + ".in"));
            out.writeObject(tree);
            out.close();
    	}

    }

    public boolean checkWord(String toCheck) {
        if(tree == null){
            return true;
        }
        return tree.search(toCheck.toLowerCase());
    }
}

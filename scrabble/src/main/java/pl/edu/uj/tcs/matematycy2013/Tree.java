package pl.edu.uj.tcs.matematycy2013;

public class Tree {
    public int counter = 0;
    private class Node {

        private Node[] childs;
        private boolean accepted;

        private Node(int childsNumber) {
            ++counter;
            childs = new Node[childsNumber];
            accepted = false;
        }
    }
    private Node root;
    private Alphabet alphabet;

    public Tree(Alphabet alphabet) {
        this.alphabet = alphabet;
        root = new Node(alphabet.getSize());
    }

    public void insert(String word) {
        int index = 0;
        Node currentNode = root;

        while (index < word.length()) {
            int v = alphabet.getCharacterLabel(word.charAt(index));
            if (v == -1) {
                System.out.println(word.charAt(index));
            }
            if (currentNode.childs[v] != null) {
                currentNode = currentNode.childs[v];
            }
            else {
                Node newNode = new Node(alphabet.getSize());
                currentNode.childs[v] = newNode;
                currentNode = newNode;
            }
            ++index;
            if(index == word.length()){
                currentNode.accepted = true;
            }
        }
    }

    public boolean search(String word) {
        int index = 0;
        Node currentNode = root;

        while (index < word.length()) {
            int v = alphabet.getCharacterLabel(word.charAt(index));
            if (v == -1) {
                return false;
            }
            if (currentNode.childs[v] == null) {
                return false;
            }
            currentNode = currentNode.childs[v];
            ++index;
        }

        if (currentNode.accepted) {
            return true;
        }

        return false;
    }
}
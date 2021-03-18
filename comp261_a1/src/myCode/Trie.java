package myCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {

    /**
     * whether this node id a word or not
     */
    boolean isWord;

    /** the char that this node contains */
    String valueString;

    /** for getAll() use. */
    List<String> resultList;

    /** the childNode can be a lot */
    Map<String, Trie> letter_childrenNodes_map;

    /**
     * A constructor. It construct a new instance of Trie.
     *
     */
    public Trie() {
        letter_childrenNodes_map = new HashMap<String, Trie>();
        isWord = false;
        valueString = "";
        resultList = new ArrayList<String>();
    }

    /**
     * Description: <br/>
     * Add a word into the Trie. For this application, the word is the stop name.
     * 
     * @author Yun Zhou
     * @param stopName
     *            the word to add
     */
    public void add(String stopName) {
        Trie currentNode = this;// set the object who call this method to the current node
        // loop trough the word char by char
        for (char c : stopName.toCharArray()) {
            String character = String.valueOf(c);
            if (!currentNode.letter_childrenNodes_map.containsKey(character)) {
                // as it's not exist, so put it into the hashMap
                currentNode.letter_childrenNodes_map.put(character, new Trie());
            }
            valueString = character;// assign the value that this node has

            // move rootNode to the child corresponding to character
            currentNode = currentNode.letter_childrenNodes_map.get(character);
        }
        // this is the word as we just add it
        currentNode.setWord(true);

    }

    /**
     * Description: <br/>
     * Exactly search. For instance, only return iff the word is exactly the same.
     * 
     * @author Yun Zhou
     * @param word
     *            the word to search
     * @return the list of the String that match with the param word
     */
    public List<String> get(String word) {
        if (word.isEmpty()) {
            return null;
        }
        Trie currentNode = this;// set the object who call this method to the rootNode
        List<String> result = new ArrayList<String>();

        // loop trough the word char by char
        // if this char has not found, then return it
        for (char c : word.toCharArray()) {
            String character = String.valueOf(c);
            // this character is not exist in the database, so return null
            if (!currentNode.letter_childrenNodes_map.containsKey(character)) {
                return null;
            }
            result.add(character);
            // move rootNode to the child corresponding to character
            currentNode = currentNode.letter_childrenNodes_map.get(character);
        }

        return result;
    }

    /**
     * Description: <br/>
     * Trie search, which will return all posible results with the same prefix(i.e. String
     * word).
     * 
     * @author Yun Zhou
     * @param prefix_string
     *            the word to search
     * @return the list of all possible results
     */
    public List<String> getAll(String prefix_string) {
        if (prefix_string.isEmpty()) {
            return null;
        }
        // List<String> fullMatchList = get(prefix_string);
        // if (fullMatchList != null) {
        // if (fullMatchList.isEmpty()) {
        // return fullMatchList;
        // }
        // }

        Trie currentNode = this;// set the object who call this method to the rootNode

        // loop trough the word char by char
        // if this char has not found, then return it
        for (char c : prefix_string.toCharArray()) {
            String character = String.valueOf(c);
            if (!currentNode.letter_childrenNodes_map.containsKey(character)) {
                // this character is not exist in the database,
                // so, the prefix is not valid, return null
                return null;
            }
            // move the currentNode point next to the children, to check it's value
            currentNode = currentNode.letter_childrenNodes_map.get(character);
        }
        resultList = new ArrayList<String>();
        getAllFrom(currentNode, prefix_string, resultList);
        return resultList;
    }

    /***
     * Description: <br/>
     * A recursive method for adding all possible string into the list.
     * <p>
     * 
     * 
     * @author Yun Zhou
     * @param node
     *            the node to start
     * @param prefix_string
     *            the prefix string so far
     * @param resultList
     *            the list of all possible words
     */
    private void getAllFrom(Trie node, String prefix_string, List<String> resultList) {
        for (String letter : node.letter_childrenNodes_map.keySet()) {
            String word = prefix_string;
            word += letter;
            resultList.add(word.toString());
            // recursive to find all the possible string
            getAllFrom(node.letter_childrenNodes_map.get(letter), word.toString(), resultList);
        }
    }

    /**
     * Get the isWord.
     *
     * @return the isWord
     */
    public boolean isWord() {
        return isWord;
    }

    /**
     * Set the isWord.
     *
     * @param isWord
     *            the isWord to set
     */
    public void setWord(boolean isWord) {
        this.isWord = isWord;
    }

    // /**
    // * for marking whether this TrieNode is the root(i.e. no parent) and the leaf(i.e.
    // * no child)
    // */
    // boolean isRootNode, isLeafNode;
    // public void convertNodeToStop() {
    // if (!isStop) {
    // return;
    // }
    // String stop_id = getStopID(this, "");
    //
    // }
    //
    // private String getStopID(TrieNode trieNode, String word) {
    // if (isRootNode) {
    // return word;
    // }
    //
    // return null;
    // }

}

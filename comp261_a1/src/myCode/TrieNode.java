package myCode;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    /**
     * whether this node id a word or not
     */
    boolean isWord;

    /** the char that this node contains */
    char character;

    /** the childNode can be a lot */
    Map<String, TrieNode> childrenNodes;

    // /**
    // * for marking whether this TrieNode is the root(i.e. no parent) and the leaf(i.e.
    // no
    // * child)
    // */
    // boolean isRootNode, isLeafNode;

    public TrieNode() {
        childrenNodes = new HashMap<String, TrieNode>();

    }

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

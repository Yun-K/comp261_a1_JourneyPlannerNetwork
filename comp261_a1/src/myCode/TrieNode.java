package myCode;

import java.util.List;

public abstract class TrieNode {
    /** the char that this node contains */
    char character;

    /** the parent node is unique */
    protected TrieNode parentNode = null;

    /** the childNode can be a lot */
    List<TrieNode> childNode;

    /**
     * for marking whether this TrieNode is the root(i.e. no parent) and the leaf(i.e. no
     * child)
     */
    boolean isRootNode, isLeafNode;

    /**
     * from now on, the consisit of this Trie node and parents nodes is a real stop Object
     */
    boolean isStop;

    public TrieNode(char character, TrieNode parentNode, List<TrieNode> childNode) {
        this.character = character;
        this.parentNode = parentNode;
        this.childNode = childNode;
        this.isRootNode = false;
        if (parentNode == null) {
            this.isRootNode = true;
        }
        this.isLeafNode = false;
        if (childNode == null || childNode.isEmpty()) {
            this.isLeafNode = true;
        }

    }

    public void convertNodeToStop() {
        if (!isStop) {
            return;
        }
        String stop_id = getStopID(this, "");

    }

    private String getStopID(TrieNode trieNode, String word) {
        if (isRootNode) {
            return word;
        }

        return null;
    }

}

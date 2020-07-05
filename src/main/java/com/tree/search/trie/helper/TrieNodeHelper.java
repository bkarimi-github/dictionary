package com.tree.search.trie.helper;

import com.tree.search.trie.impl.TrieNode;

public class TrieNodeHelper {
    public static TrieNode findChildDelegateFor(TrieNode node, String word) {
        final String firstChar = word.substring(0, 1);
        TrieNode childDelegate = node.getChildren().stream().filter(cn -> cn.getName().startsWith(firstChar)).findFirst().orElse(null);
        return childDelegate;
    }

    public static String generateTabs(int numTabs){
        StringBuffer buf  = new StringBuffer("");
        for(int i = 0; i <= numTabs; i++)
        {
            buf.append("\t");
        }
        return buf.toString();
    }

    public static TrieNode createNode(String name, TrieNode parent)
    {
        TrieNode newNode = new TrieNode(name, parent);
        parent.getChildren().add(newNode);
        newNode.setWord(true);
        return newNode;
    }
}

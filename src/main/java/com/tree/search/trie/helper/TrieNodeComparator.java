package com.tree.search.trie.helper;

import com.tree.search.trie.impl.TrieNode;

import java.util.Comparator;

public class TrieNodeComparator implements Comparator<TrieNode> {
    @Override
    public int compare(TrieNode source, TrieNode target) {
        return source.getName().compareTo(target.getName());
    }
}

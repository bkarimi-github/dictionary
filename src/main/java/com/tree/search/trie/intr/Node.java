package com.tree.search.trie.intr;

public interface Node {

    public void insert(String word);

    public Node find(String word);

    public Node delete(String Node);
}

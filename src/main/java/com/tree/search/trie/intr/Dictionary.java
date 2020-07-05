package com.tree.search.trie.intr;

public interface Dictionary {
    public void insert(String word);

    public boolean delete(String word);

    public boolean find(String word);
}

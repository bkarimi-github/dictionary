package com.tree.search.trie.intr;

import java.io.File;

public interface Dictionary {
    public void insert(String word);

    public boolean delete(String word);

    public boolean find(String word);

    public void insert(File file);
}

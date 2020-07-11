package com.tree.search.trie.impl;

import com.tree.search.trie.main.Main;

import java.io.File;

public class FileImporterThread extends Thread {

    public FileImporterThread(String name)
    {
        super(name);
    }
    @Override
    public void run() {
        System.out.println("Running " + this.getName() + " with ID: " + this.getId());

        setPriority(Thread.MAX_PRIORITY);
        File file = new File(this.getClass().getClassLoader().getResource("words.txt").getFile());
        Trie.getInstance().insert(file);
        System.out.println("Finished Import for " + Trie.count + " words");
    }
}

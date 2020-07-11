package com.tree.search.trie.main;

import com.tree.search.trie.impl.FileImporterThread;
import com.tree.search.trie.impl.Trie;

import java.io.*;

public class Main {

    private static Trie TRIE_DICTIONARY = Trie.getInstance();

    public static void main(String[] args) throws IOException
    {
//        TRIE_DICTIONARY.insert("burhan");
//        TRIE_DICTIONARY.insert("burgan");
//        TRIE_DICTIONARY.insert("bur");
//        TRIE_DICTIONARY.insert("bu");
//        TRIE_DICTIONARY.insert("burgangan");
//        TRIE_DICTIONARY.insert("america");
//        TRIE_DICTIONARY.insert("africa");
//        TRIE_DICTIONARY.insert("aurangabad");
//        TRIE_DICTIONARY.insert("ahmedabad");
//        TRIE_DICTIONARY.insert("zimbabwe");
//        TRIE_DICTIONARY.insert("zaynab");
//        TRIE_DICTIONARY.insert("zebra");
//        TRIE_DICTIONARY.insert("elephant");
//
//        System.out.println(TRIE_DICTIONARY.print());
//
//
//        TRIE_DICTIONARY.delete("burhan");
//        TRIE_DICTIONARY.delete("burhan");
//        System.out.println(TRIE_DICTIONARY.print());

        Thread fileImporterThread = new FileImporterThread("FileImporterThread");
        fileImporterThread.start();

        System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getId());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true)
        {
            displayMenu(br);
        }

    }

    public static void displayMenu(BufferedReader bufferedReader) throws IOException
    {
        System.out.println("Options:\n1. Enter a Word\n2. Find a Word\n3. Delete a Word\n4. Delete All Words\n5. Print Tree\n6. Exit");
        String choice = bufferedReader.readLine();
        switch (choice){
            case "1":
                System.out.println("Enter a word to insert:");
                String wordToInsert = bufferedReader.readLine();
                TRIE_DICTIONARY.insert(wordToInsert);
                break;
            case "2":
                System.out.println("Enter a word to search for:");
                String wordToSearch = bufferedReader.readLine();
                boolean wordExists = TRIE_DICTIONARY.find(wordToSearch);
                if(wordExists)
                    System.out.println("Word Found");
                else
                    System.out.println("Word Not Found");
                break;
            case "3":
                System.out.println("Enter a word to delete:");

                String wordToDelete = bufferedReader.readLine();
                boolean deleted = TRIE_DICTIONARY.delete(wordToDelete);
                if(deleted)
                    System.out.println("Word Deleted");
                else
                    System.out.println("Deletion failed");
                break;
            case "4":
                boolean allDeleted = TRIE_DICTIONARY.deleteAll();
                if(allDeleted)
                    System.out.println("All Words Deleted");
                else
                    System.out.println("Deletion failed");
                break;
            case "5":
                System.out.println("Tree Structure:\n");
                System.out.println(TRIE_DICTIONARY.print());
                break;
            case "6":
                System.exit(0);
        }

    }
}

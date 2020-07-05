package com.tree.search.trie.impl;

import com.tree.search.trie.constants.TrieConstants;
import com.tree.search.trie.intr.Dictionary;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Trie implements Dictionary {

    public static TrieNode ROOT = new TrieNode(TrieConstants.ROOT_NODE_NAME, null);

    public void insert(String word)
    {
        ROOT.insert(word.toLowerCase().trim().replace(" ", "-"));
    }

    public String print()
    {
        return ROOT.print(0);
    }

    private void insertInner(TrieNode node, String word, int startPosition)
    {
//        // firstChar = char word[startPosition]
//        // find the child that has the name startsWith firstChar
//        final String sub = word.substring(startPosition, startPosition+1);
//        TrieNode child = node.getChildren().stream().filter(cn -> cn.getName().startsWith(sub)).findFirst().orElse(null);
//        if(child != null)
//        {
//            String wordSubString = word.substring(startPosition);
//            String nodeName = child.getName();
//
//            if(wordSubString.equals(nodeName))
//            {
//                // word already exists
//                child.setWord(true);
//                return;
//            }
//
//            int LOOP_INDEX = wordSubString.length() >= nodeName.length() ? word.substring(startPosition).length() : nodeName.length();
//
//            // get the matched length.
//            for(int i = 0; i < LOOP_INDEX; )
//            {
//                if(word.charAt(startPosition) == nodeName.charAt(i))
//                {
//                    if(startPosition == word.length() - 1)
//                    {
//                        splitInternal(child, i+1, true);
//                        return;
//                    }
//                    i++;
//                    startPosition++;
//                }
//                else
//                {
//                    TrieNode splittedNode = splitInternal(child, i, false);
//                    TrieNode newNode = TrieNode.create(word.substring(startPosition), splittedNode);
//                    newNode.setWord(true);
//                    return;
//                }
//            }
//
//            if(startPosition == word.length() - 1){
//                // word has been added
//                return;
//            }
//            else {
//                // call the recursive node
//                insertInner(child, word, startPosition);
//            }
//        }
//        else
//        {
//            TrieNode newNode = TrieNode.create(word.substring(startPosition), node);
//            newNode.setWord(true);
//            return;
//        }
    }

    public boolean delete(String word) {
        TrieNode deletedNode = ROOT.delete(word);
        if(deletedNode != null) {
            return true;
        }
        return false;
    }

    public boolean deleteAll()
    {
        ROOT.delete();
        return true;
    }

    public boolean find(String word) {
        TrieNode foundNode = ROOT.find(word);
        if(foundNode != null && foundNode.isWord()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return ROOT.toString();
    }
}

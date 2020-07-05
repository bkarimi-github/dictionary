package com.tree.search.trie.impl;

import com.tree.search.trie.constants.TrieConstants;
import com.tree.search.trie.helper.TrieNodeComparator;
import com.tree.search.trie.helper.TrieNodeHelper;
import com.tree.search.trie.intr.Node;
import org.apache.commons.collections.CollectionUtils;

import static com.tree.search.trie.helper.TrieNodeHelper.createNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrieNode implements Node {
    private String name;
    private String description;
    private TrieNode parent;
    private List<TrieNode> children;
    private boolean isWord;


    public TrieNode(String name)
    {
        this.name = name;
    }

    public TrieNode(String name, TrieNode parent)
    {
        this.name = name;
        this.parent = parent;
    }


    public void insert(String word)
    {
        if(this.isRootNode())
        {
            TrieNode childDelegate = TrieNodeHelper.findChildDelegateFor(this, word);
            if(childDelegate != null)
            {
                childDelegate.insert(word);
            }
            else
            {
                createNode(word, this);
            }
        }
        else
        {
            // if word is equal to the node name
            if(word.equals(this.name))
            {
                setWord(true);
                return;
            }

            // if word is contained within the node name
            else if(this.name.contains(word))
            {
                // split without creating node for word to be inserted
                split(word, true);
                return;
            }

            // if node name is contained within the word
            else if(word.contains(this.name))
            {
                // delegate the insertion to child delegate
                word = word.substring(name.length());
                TrieNode childDelegate = TrieNodeHelper.findChildDelegateFor(this, word);
                if(childDelegate != null)
                {
                    childDelegate.insert(word);
                }
                else
                {
                    createNode(word, this);
                }
            }

            // if word is partially contained within the node name
            // find out
            else{
                int LOOP_INDEX = word.length() >= name.length() ? name.length() : word.length();
                int matchedChars = 0;
                for(int i = 0; i < LOOP_INDEX; i++)
                {
                    if(word.charAt(i) == name.charAt(i))
                    {
                        ++matchedChars;
                    }
                    else
                    {
                        break;
                    }
                }
                String splitName = name.substring(0, matchedChars);
                TrieNode splittedNode = split(splitName, false);
                String remainingWord = word.substring(matchedChars);
                TrieNode remainingWordNode = createNode(remainingWord, splittedNode);
                return;
            }
        }
    }

    public TrieNode find(String word){
        TrieNode foundNode = null;
        if(this.isRootNode())
        {
            TrieNode childDelegate = TrieNodeHelper.findChildDelegateFor(this, word);
            if(childDelegate != null)
            {
                foundNode = childDelegate.find(word);
            }
        }
        else{
            // if word is equal to the node name
            if(word.equals(this.name))
            {
                if(this.isWord)
                {
                    return this;
                }
                else {
                    return null;
                }

            }
            else if(this.name.contains(word))
            {
                return null;
            }
            else if(word.contains(this.name)){
                word = word.substring(this.name.length());
                TrieNode childDelegate = TrieNodeHelper.findChildDelegateFor(this, word);
                if(childDelegate != null)
                {
                    foundNode = childDelegate.find(word);
                }
                else{
                    return null;
                }
            }
            else{
                return null;
            }
        }

        return foundNode;
    }

    public TrieNode delete(String word){
        return null;
    }

    private TrieNode split(String newName, boolean isNewNodeAWord)
    {
        TrieNode newNode = createNode(newName, this.parent);
        this.name = this.name.substring(newName.length());
        this.parent.getChildren().remove(this);
        newNode.getChildren().add(this);
        this.parent = newNode;
        newNode.setWord(isNewNodeAWord);
        return newNode;
    }


    public boolean isRootNode()
    {
            if(this.parent == null && this.name.equals(TrieConstants.ROOT_NODE_NAME))
            {
                return true;
            }
            return false;
    }

    public String getName() {
        return name;
    }

    public List<TrieNode> getChildren()
    {
        if(CollectionUtils.isEmpty(this.children))
        {
            this.children = new ArrayList<TrieNode>();
        }
        return children;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String print(final int numTabs) {
        StringBuffer buffer = new StringBuffer("\n"+ TrieNodeHelper.generateTabs(numTabs) +"[" + this.name + ", " + this.isWord + "]");
        if(CollectionUtils.isNotEmpty(children)) {
            buffer.append("->");
            buffer.append("\n"+ TrieNodeHelper.generateTabs(numTabs) +"{");
            children.stream().sorted(new TrieNodeComparator()).forEach(child -> buffer.append(child.print(numTabs+1)));
            buffer.append("\n" + TrieNodeHelper.generateTabs(numTabs) +"}");
        }
        return buffer.toString();
    }
}

package src.algorithm.trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearch2 {
    public List<String> findWords(char[][] board, String[] words) {
        Trie myTrie = buildTrie(words);
        Set<String> res = new HashSet<>();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                check(i,j,board,res,myTrie);
            }
        }
        return new ArrayList<String>(res);
    }

    public void check(int i,int j,char[][] board, Set<String> res, Trie root){
        if(i<0 || j<0 || i>=board.length || j>=board[0].length || board[i][j]=='#' || root.element[board[i][j] - 'a'] == null)
            return;
        if(root.element[board[i][j] - 'a'].word != null){
            res.add(root.element[board[i][j] - 'a'].word);
        }

        // System.out.println(board[i][j] - 'a');
        char c = board[i][j];
        board[i][j] = '#';
        check(i,j+1,board,res,root.element[c - 'a']);
        check(i+1,j,board,res,root.element[c - 'a']);
        check(i,j-1,board,res,root.element[c - 'a']);
        check(i-1,j,board,res,root.element[c - 'a']);
        board[i][j] = c;

    }

    public Trie buildTrie(String[] words){
        Trie root = new Trie();
        for(String word:words){
            Trie node = root;
            for(char c : word.toCharArray()){
                if(node.element[c-'a'] == null){
                    node.element[c-'a'] = new Trie();
                }
                node = node.element[c-'a'];
            }
            node.word = word;
        }
        return root;
    }
}

class Trie {
    public Trie[] element = new Trie[26];
    public String word = null;
}
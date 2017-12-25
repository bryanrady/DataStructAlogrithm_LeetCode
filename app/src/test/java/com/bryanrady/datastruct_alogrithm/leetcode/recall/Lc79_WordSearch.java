package com.bryanrady.datastruct_alogrithm.leetcode.recall;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc79_WordSearch {

    /**
     * 递归回溯05（二维平面上的回溯） - 单词搜索 - 中等 - 79

     给定一个2D屏幕和一个单词，寻找单词是否在这个网格中。

     单词可以从每个相邻格子中的字母组合而成，这个相邻只能是水平或者竖直方向的。

     比如给定：

     board =
     [
     ['A','B','C','E'],
     ['S','F','C','S'],
     ['A','D','E','E']
     ]

     单词 = "ABCCED", -> 返回 true,
     单词 = "SEE",    -> 返回 true,
     单词 = "ABCB",   -> 返回 false.
     */

    public boolean exist(char[][] board, String word) {
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(dfs(board,word,i,j,0))
                    return true;
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, String word, int i, int j, int k){
        if(i<0 || j<0 || i>=board.length || j>=board[0].length){
            return false;
        }

        if(board[i][j] == word.charAt(k)){
            char temp = board[i][j];
            board[i][j]='#';
            if(k==word.length()-1){
                return true;
            }else if(dfs(board, word, i-1, j, k+1)
                    ||dfs(board, word, i+1, j, k+1)
                    ||dfs(board, word, i, j-1, k+1)
                    ||dfs(board, word, i, j+1, k+1)){
                return true;
            }
            board[i][j]=temp;
        }

        return false;
    }
}

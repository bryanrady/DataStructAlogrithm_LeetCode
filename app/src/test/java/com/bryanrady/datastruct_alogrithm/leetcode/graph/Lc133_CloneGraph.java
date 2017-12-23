package com.bryanrady.datastruct_alogrithm.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc133_CloneGraph {

    /**
     * 图论06 - 拷贝图 - 中等 - 133

     拷贝无向图。图中的节点包含一个`label`(标签)和它的`neighbors`(相连节点)列表。

     **无向图的OJ序列化表示法：**
     节点具有唯一的标签。
     使用`#`来分割每个节点，使用`,`来分割标签和相连节点列表。

     比如一个序列：`{0,1,2#1,2#2,2}`
     这个序列表示的图共有3个节点，因为可以看到`#`把它分割了成3个部分。
     1. 第一个节点的标签为`0`，与它相连的节点的标签为`1`和`2`。
     2. 第一个节点标签为1，与`2`相连。
     3. 第三个节点标签为3，余它自身相连，意味着这条边是自环边(self-cycle)。

     最后，这个图看起来是这样的：

     ```
         1
        / \
      /   \
     0 --- 2
         / \
        \_/
     ```
     */

    /**
     * Definition for undirected graph.
     * class UndirectedGraphNode {
     *     int label;
     *     List<UndirectedGraphNode> neighbors;
     *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
     * };
     */

    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) {
            return null;
        }
        return helper(node, new HashMap<Integer, UndirectedGraphNode>());
    }
    public UndirectedGraphNode helper(UndirectedGraphNode node, HashMap<Integer, UndirectedGraphNode> map) {
        if(map.containsKey(node.label)) {
            return map.get(node.label);
        }
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(node.label, clone);
        for(int i = 0; i < node.neighbors.size(); i++) {
            clone.neighbors.add(helper(node.neighbors.get(i), map));
        }
        return clone;
    }
}


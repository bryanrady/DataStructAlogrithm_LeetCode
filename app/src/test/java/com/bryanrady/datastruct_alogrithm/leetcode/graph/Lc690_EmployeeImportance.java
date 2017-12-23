package com.bryanrady.datastruct_alogrithm.leetcode.graph;

import java.util.List;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc690_EmployeeImportance {

    /**
     * 图论04 - 职员重要度 - 简单 - 690

     给出职员(employee)信息结构体，其中包括职员id，职员重要度(importance value)和他所有的**直接**下属们的id。

     比如，职员1是职员2的领导，职员2是职员3的领导。他们的重要度分别为15、10和5。
     职员1的数据结构可以表示为[1,15,[2]]，职员2可以表示为[2,10,[3]]，职员3可以表示为[3,5,[]]。
     注意职员3也是职员1的下属，只不过不是直接下属。

     现在给出一家公司内的职员信息，和一个其中职员的id。你要做的是返回这个职员以及他所有下属的重要度之和。

     比如：
     Input: [[1,5,[2,3]], [2,3,[]], [3,3,[]]],1
     Output: 11

     注意：
     1.每个职员最多有一个直接领导。
     2.这家公司的员工总数不超过2000人。
     */

    // Employee info
    class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    }

    public int getImportance(List<Employee> employees, int id) {
        return 0;
    }
}

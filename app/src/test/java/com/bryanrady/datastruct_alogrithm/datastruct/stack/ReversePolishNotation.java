package com.bryanrady.datastruct_alogrithm.datastruct.stack;

import org.junit.Test;

import java.util.LinkedList;

/**
 * Created by bryanrady on 2017/12/22.
 *
 *  栈的应用  —— 逆波兰表达式(中缀表达式转变成后缀表达式)
 */

public class ReversePolishNotation {


    /**
     * 中缀表达式转变成后缀表达式的规则：
     *              数字输出，运算符进栈，括号匹配出栈，栈顶优先级低出栈。
     */

    /**
     *
     *  后缀表达式本身的实现算法（中缀表达式转为后缀表达式）：
     *      （1）初始化一个堆栈。
     *      （2）顺序读入中缀表达式，读到的字符为数字时将其输出，接着读下一个字符，遇见符号就入栈；
     *                  符号入栈过程中，要比较准备入栈的符号和栈顶操作符的优先级。
     *      （3）当遇见括号形成匹配关系的时候，就把在括号匹配内的符号出栈。
     *
     *  后缀表达式求值算法（逆波兰表达式求值）
     *
     *      （1）初始化一个堆栈；
     *      （2）定义两个操作数opt和opt2;
     *      （3）如果对后缀表达式进行遍历，当遇到操作符的时候就从栈顶中取出两个元素以操作符进行四则运算，并将运算后得出的结果进行入栈。
     *          否则就是遇到操作数字，就直接入栈。
     *      （4）最后返回在栈中的结果stack.pop（）。
     */

    @Test
    public void test(){
        char[] chars = reversePolish("(2 + 1) * 3");
        System.out.println();
        int result = evalRPN(chars);
        System.out.print(result);
    }

    //用来做操作符入栈的栈
    private static Stack<Character> stack = new Stack<>();

    private static LinkedList<Character> list = new LinkedList<>();


    /**
     * 根据后缀表达式计算结果
     * @param tokens
     * @return
     */
    public static int evalRPN(char[] tokens) {
        Stack<Integer> stack = new Stack();
        int opt1 = 0;   //定义两个操作数
        int opt2 = 0;
        for(char token:tokens){
            if(isOperator(token)){      //如果是运算符
                opt1 = stack.pop();
                opt2 = stack.pop();

                //进行运算
                switch (token){
                    case '+':
                        opt2 += opt1;
                        break;
                    case '-':
                        opt2 -= opt1;
                        break;
                    case '*':
                        opt2 *= opt1;
                        break;
                    case '/':
                        opt2 /= opt1;
                        break;
                }
                //将运算结果入栈
                stack.push(opt2);
            }else{          //证明是操作数,直接入栈
                if(token!=' '){
                    stack.push(Integer.parseInt(String.valueOf(token)));
                }
            }
        }
        return stack.pop();
    }

    /**
     * 传入一个四则运算式（中缀表达式）
     * @param expression
     * @return  返回一个队列 后面装有后缀表达式
     */
    public static char[] reversePolish(String expression){
        if(expression == null){
            return null;
        }
        char[] chars = expression.toCharArray();
        for(char s : chars) {
            if (isOperator(s)) {
                if (stack.isEmpty()) {  //如果栈之前是空的，直接把操作符入栈
                    stack.push(s);
                } else {
                    //如果读入的操作符为非")"且优先级比栈顶元素的优先级高或一样，则将操作符压入栈
                    if (priority(stack.peek()) <= priority(s) && s != ')') {
                        stack.push(s);

                    } else if (priority(stack.peek()) > priority(s) && s != ')') {
                        while (stack.size() != 0 && stack.peek()!= '(') {
                            char operator = stack.pop();
                            list.offer(operator);
                        }
                        stack.push(s);

                    } else if (s == ')') {
                        while (stack.peek() != '(') {
                            char operator = stack.pop();
                            list.offer(operator);
                        }
                        //while循环执行结束后，还要弹出"("
                        stack.pop();
                    }
                }

            } else {    //不是操作符的话直接加到队列中
                list.offer(s);
            }
        }

        //把剩下的操作符也添加到队列中
        while (!stack.isEmpty()) {
            char operator = stack.pop();
            list.offer(operator);
        }

        char[] c = new char[list.size()];
        int i = 0;
        while(!list.isEmpty()){
            c[i] = list.poll();
            System.out.print(c[i]);
            i++;
        }
        return c;
    }

    /**
     * 判断是不是操作符
     * @param oper
     * @return
     */
    private static boolean isOperator(char oper){
        if (oper == ('+') || oper==('-') || oper==('*')
                || oper==('/') || oper==('(') || oper== (')')) {
            return true;
        }
        return false;
    }

    /**
     * 计算操作符的优先级
     * @param s
     * @return
     */
    private static int priority(char s){
        switch (s) {
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case ('/'):
                return 2;
            case '(':
                return 3;
            case ')':
                return 3;
            default :
                return 0;
        }
    }

}

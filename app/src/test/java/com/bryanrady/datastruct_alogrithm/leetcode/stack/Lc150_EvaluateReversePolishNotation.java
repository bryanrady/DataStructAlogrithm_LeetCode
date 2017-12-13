package com.bryanrady.datastruct_alogrithm.leetcode.stack;

import java.util.Stack;

/**
 * Created by bryanrady on 2017/12/13.
 */

public class Lc150_EvaluateReversePolishNotation {

    /**
     * 栈02 - 逆波兰表达式求值 - 中等 - 150
     给定一个数组，表示一个逆波兰表达式，求其值。
     运算类型只有+、-、*、/。
     比如：
     ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
     ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
     */

    /**
     * 实现逆波兰表达式求值  14ms
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack();
        int opt1 = 0;   //定义两个操作数
        int opt2 = 0;
        for(String token:tokens){
            if("+".equals(token) || "-".equals(token)
                    || "*".equals(token) || "/".equals(token)){      //说明是运算符
                opt1 = stack.pop();
                opt2 = stack.pop();

                //进行运算
                switch (token.charAt(0)){
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
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }


    /**
     * 9ms leetcode上的
     */
    private Stack<Integer> stack = new Stack<>();

    public int evalRPN1(String[] tokens) {
        for(int i = 0;i < tokens.length;i++){
            String token = tokens[i];
            operation(token);
        }
        return stack.pop();
    }

    private void operation(String token){
        int num1;
        int num2;
        switch (token){
            case "+":
                num1 = stack.pop();
                num2 = stack.pop();
                stack.push(num2 + num1);
                break;
            case "-":
                num1 = stack.pop();
                num2 = stack.pop();
                stack.push(num2 - num1);
                break;
            case "*":
                num1 = stack.pop();
                num2 = stack.pop();
                stack.push(num2 * num1);
                break;
            case "/":
                num1 = stack.pop();
                num2 = stack.pop();
                stack.push(num2 / num1);
                break;
            default:
                stack.push(Integer.valueOf(token));
                break;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rootfindingmethods;

/**
 *
 * @author Suzy
 */

import java.util.Stack;

public class InfixPostfix {
    
    public double ExVariable(String equation, String variableValue) {
        return evaluateExpression(equation, Double.parseDouble(variableValue));
    }
    
    private double evaluateExpression(String equation, double variableValue) {
        String expression = equation.replaceAll("x", String.valueOf(variableValue));
        String postfixExpression = infixToPostfix(expression);
        return evaluatePostfix(postfixExpression);
    }
    
    private String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> ops = new Stack<>();
        
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            
            if (Character.isDigit(c) || c == '.') {
                postfix.append(c);
            } else if (c == 'x') {
                postfix.append('x'); // Keep 'x' as it is
            } else if (isOperator(c)) {
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(c)) {
                    postfix.append(ops.pop());
                }
                ops.push(c);
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (ops.peek() != '(') {
                    postfix.append(ops.pop());
                }
                ops.pop(); // Remove '(' from stack
            }
        }
        
        while (!ops.isEmpty()) {
            postfix.append(ops.pop());
        }
        
        return postfix.toString();
    }
    
    private double evaluatePostfix(String postfix) {
        Stack<Double> values = new Stack<>();
        
        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);
            
            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < postfix.length() && (Character.isDigit(postfix.charAt(i)) || postfix.charAt(i) == '.')) {
                    sb.append(postfix.charAt(i++));
                }
                values.push(Double.parseDouble(sb.toString()));
                i--;
            } else if (c == 'x') {
                values.push((double) 'x'); // Keep 'x' as it is
            } else if (isOperator(c)) {
                double val2 = values.pop();
                double val1 = values.pop();
                values.push(applyOp(c, val1, val2));
            }
        }
        
        return values.pop();
    }
    
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }
    
    private int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0; // Parentheses
        }
    }
    
    private double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new ArithmeticException("Cannot divide by zero");
                return a / b;
            case '^':
                return Math.pow(a, b);
            default:
                return 0;
        }
    }
    
    public double differentiate(String equation, double variableValue) {
        String derivativeExpression = differentiateExpression(equation);
        return evaluateExpression(derivativeExpression, variableValue);
    }
    
    private String differentiateExpression(String equation) {
        StringBuilder derivative = new StringBuilder();
        char[] tokens = equation.toCharArray();
        
        for (int i = 0; i < tokens.length; i++) {
            char c = tokens[i];
            
            if (c == 'x') {
                // If 'x' is found, add the derivative term to the StringBuilder
                derivative.append("1");
            } else if (isOperator(c)) {
                // For operators, simply append them to the StringBuilder
                derivative.append(c);
            } else if (c == '^') {
                // If '^' is found, append '^' and the next character which represents the power
                derivative.append("^").append(tokens[i + 1]);
                i++; // Increment i to skip the next character since it's already added
            } else {
                // For other characters, simply append them to the StringBuilder
                derivative.append(c);
            }
            
            // If the next character exists and it's 'x', add '*' to represent multiplication
            if (i < tokens.length - 1 && tokens[i + 1] == 'x') {
                derivative.append("*");
            }
        }
        
        return derivative.toString();
    }
}
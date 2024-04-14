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
import java.util.List;
import java.util.ArrayList;


public class InfixPostfix {
    
    public double ExVariable(String equation, String variableValue) {
        return evaluateExpression(equation, Double.parseDouble(variableValue));
    }
    
    private double evaluateExpression(String equation, double variableValue) 
    {
        List<Object> infix = parseEqn(equation);
        replaceXWithValue(infix, variableValue);
        List<Object> postfixExpression = infixToPostfix(infix);
        return evaluatePostfix(postfixExpression);
    }


    public static List<Object> parseEqn(String eqn) 
    {
        List<Object> infix = new ArrayList<>();
        String[] expression = eqn.split("\\s+");
        for (String token : expression) {
            infix.add(token);
        }
        return infix;
    }

    public static void replaceXWithValue(List<Object> list, double value) 
    {
        for (int i = 0; i < list.size(); i++) 
        {
            Object obj = list.get(i);
            if (obj instanceof String) 
            {
                String str = (String) obj;
                str = str.replaceAll("x", String.valueOf(value));
                list.set(i, (Object)str);
            }
        }
    }
    
    public static List<Object> infixToPostfix(List<Object> infix) 
    {
        List<Object> postfix = new ArrayList<>();
        Stack<Object> ops = new Stack<>();

        for (Object token : infix) {
            if (token instanceof String) {
                String c = (String) token;
                if (Character.isDigit(c.charAt(0))) {
                    postfix.add(c);
                } else if (isOperator(c.charAt(0))) {
                    while (!ops.isEmpty() && precedence((char) ops.peek()) >= precedence(c.charAt(0))) {
                        postfix.add(ops.pop());
                    }
                    ops.push(c.charAt(0));
                } else if (c.equals("(")) {
                    ops.push(c.charAt(0));
                } else if (c.equals(")")) {
                    while (!ops.isEmpty() && (char) ops.peek() != '(') {
                        postfix.add(ops.pop());
                    }
                    ops.pop();
                }
            }
        }

        while (!ops.isEmpty()) {
            postfix.add(ops.pop());
        }

        return postfix;
    }


    public double evaluatePostfix(List<Object> postfix) 
    {
        Stack<Double> values = new Stack<>();

        for (Object token : postfix) 
        {
            if (token instanceof String) 
            {
                String str = (String) token;

                if (Character.isDigit(str.charAt(0))) 
                {
                    values.push(Double.parseDouble(str));
                } 
                else if (isOperator(str.charAt(0))) 
                {
                    double val2 = values.pop();
                    double val1 = values.pop();
                    values.push(applyOp(str.charAt(0), val1, val2));
                }
            }
        }

        if (values.isEmpty()) {
            throw new IllegalArgumentException("Invalid postfix expression");
        }

        return values.pop();
    }

    
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }
    
    private static int precedence(char op) {
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
    
    private static double applyOp(char op, double b, double a) {
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
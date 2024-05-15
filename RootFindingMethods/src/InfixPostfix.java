/*
    * To change this license header, choose License Headers in Project Properties.
    * To change this template file, choose Tools | Templates
    * and open the template in the editor.
    */
    package rootfindingmethods;


    import java.util.ArrayList;
    import java.util.List;
    import java.util.Stack;

   public class InfixPostfix {

    public double evaluateExpression(String equation, double variableValue) {
        List<Object> infix = parseEqn(equation);
        replaceXWithValue(infix, variableValue);
        List<Object> postfixExpression = InfixToPostfix(infix);
        return evaluatePostfix(postfixExpression);
    }

    public static List<Object> parseEqn(String eqn) {
    List<Object> infix = new ArrayList<>();
    String[] expression = eqn.split("\\s+");
    for (String token : expression) {
        infix.add(token);
    }
    return infix;
}


    public static void replaceXWithValue(List<Object> list, double value) {
        if (value < 0){
            value = Math.abs(value);
            List<Object> newlist = new ArrayList<>();
             for (int i = 0; i < list.size(); i++) {
                Object obj = list.get(i);
                if (obj.equals("x")) {
                    newlist.add("(");
                    newlist.add(String.valueOf(0));
                    newlist.add("-");
                    newlist.add(String.valueOf(value));
                    newlist.add(")");
                }
                else{
                    newlist.add(obj);
                }
            }
            list.clear();
            list.addAll(newlist);

        }
        else{
            for (int i = 0; i < list.size(); i++) {
                Object obj = list.get(i);
                if (obj instanceof String) {
                    String str = (String) obj;
                    str = str.replaceAll("x", String.valueOf(value));
                    list.set(i, str);
                }
            }
        }
    }



    public static List<Object> InfixToPostfix(List<Object> infix) {
        List<Object> postfix = new ArrayList<>();
        Stack<Character> ops = new Stack<>();

        for (Object token : infix) {
            if (token instanceof String) {
                String c = (String) token;

                if (Character.isDigit(c.charAt(0)) || c.matches("^\\d*\\.\\d+$")) {
                    postfix.add(c); // Add numeric literals to postfix directly
                } else if (isOperator(c.charAt(0))) {
                    while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(c.charAt(0))) {
                        postfix.add(ops.pop());
                    }
                    ops.push(c.charAt(0));
                } else if (c.equals("(")) {
                    ops.push('(');
                } else if (c.equals(")")) {
                    while (!ops.isEmpty() && ops.peek() != '(') {
                        postfix.add(ops.pop());
                    }
                    if (!ops.isEmpty() && ops.peek() == '(') {
                        ops.pop(); // Remove the '(' from stack
                    } else {
                        throw new IllegalArgumentException("Mismatched parentheses in expression");
                    }
                }
            }
        }

        // Pop remaining operators from the stack to postfix
        while (!ops.isEmpty()) {
            postfix.add(ops.pop());
        }

        return postfix;
    }

    public double evaluatePostfix(List<Object> postfix) 
    {
        Stack<Double> values = new Stack<>();
        for (int i = 0; i < postfix.size(); i++) {
            if (tryParseDouble(postfix.get(i))) {
                values.push(Double.parseDouble(postfix.get(i).toString()));
            } 
            else 
            {

                char c = postfix.get(i).toString().charAt(0);
                double b = values.pop();
                double a = values.pop();
                values.push(applyOp(c, a, b));
            }
        }
            return values.pop(); // Return the final result
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
            return 0; // Parentheses have lowest precedence
    }
}

    private static double applyOp(char op, double a, double b) {
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
                throw new IllegalArgumentException("Unsupported operator");
        }
    }

    public static boolean tryParseDouble(Object obj) {
        if (obj == null) {
            return false; // Cannot parse null object
        }

        try {
            if (obj instanceof Number) {
                // If the object is already a Number (e.g., Integer, Double), parse it directly
                Double.parseDouble(obj.toString());
                return true;
            } else {
                // Attempt to parse the object's string representation to double
                String str = obj.toString().trim();
                if (str.isEmpty()) {
                    return false; // Empty string cannot be parsed
                }
                Double.parseDouble(str);
                return true;
            }
        } catch (NumberFormatException e) {
            // Parsing failed
            return false;
        }
    }
}
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
public class Secant {
    private double a, b, xnew; // [a,b], xi+1= xnew
    private double[] fx = new double[50];
    private int itr;
    public String eq;

    int flag = 3, i;

    public Secant(double a, double b, int itr) {
        this.a = a;
        this.b = b;
        this.itr = itr;
        flag = 1;
    }

    
    public void setEquation(String equation) {
        this.eq = equation;
    }

    public void secantMethod() {
        InfixPostfix ob = new InfixPostfix();

        if (flag == 1) {
            fx[0] = ob.evaluateExpression(eq, a);
            fx[1] = ob.evaluateExpression(eq, b);

            xnew = b - ((fx[1] - fx[0]) / (fx[1] * (b - a)));
            fx[2] = ob.evaluateExpression(eq, xnew);

            System.out.printf("iteration 1: f(x) = %.10f     root= [%f,%f]%n", fx[2], a, b);

            for (i = 2; i < itr - 1; i++) {
                a = b; // b becomes xi-1 so new a
                b = xnew; // b becomes xi so new b

                xnew = b - ((fx[i] * (b - a)) / (fx[i] - fx[i - 1]));
                fx[i + 1] = ob.evaluateExpression(eq, (xnew)); // 3

                System.out.printf("iteration %d: f(x) = %.10f     root= [%f,%f]%n", i, fx[i + 1], a, b);
                if (fx[i] == 0)
                    break;
            }
        // } else if (flag == 2) {
        //     fx[0] = ob.evaluateExpression(eq, (a));
        //     fx[1] = ob.evaluateExpression(eq, (b));

        //     xnew = b - ((fx[1] * (b - a)) / (fx[1] - fx[0]));
        //     fx[2] = ob.evaluateExpression(eq, (xnew));

        //     System.out.printf("iteration 1: f(x) = %.10f     root= [%f,%f]%n", fx[2], a, b);

        //     for (i = 2; i < itr - 1; i++) {
        //         a = b; // b becomes xi-1 so new a
        //         b = xnew; // b becomes xi so new b

        //         xnew = b - ((fx[i] * (b - a)) / (fx[i] - fx[i - 1]));
        //         fx[i + 1] = ob.evaluateExpression(eq, (xnew)); // 3
        //         double error = Math.abs(fx[i] - fx[i - 1]);
        //         System.out.printf("iteration %d: f(x) = %.10f     root= [%f,%f] ea=%f%n", i, fx[i + 1], a, b, error);

                
        //     }
        }
    }
}

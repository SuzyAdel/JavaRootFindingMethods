/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rootfindingmethods;

/*/**
 * @author Suzy
 */
public class False {
    private double a, b,c, Ea;
    private double[] fx = new double[50];
    private double root1; // +ve
    private double root2; // -ve
    private int itr;
    public String eq;

    public False(double a, double b, double Ea) {
        this.a = a;
        this.b = b;
        this.Ea = Ea;
        this.itr = 50; // Maximum iterations
    }

    public void setEquation(String equation) {
        this.eq = equation;
    }

    public void falseMethod() {
        InfixPostfix ob = new InfixPostfix();
        double fa, fb, fc;
        fa = ob.evaluateExpression(eq, a);
        fb = ob.evaluateExpression(eq, b);

        // Checking if fa * fb < 0
        if (fa * fb >= 0) {
            System.out.println("Cannot calculate: fa * fb >= 0");
            return;
        }
        
        for (int i = 0; i < itr; i++) {
            c = (a * fb - b * fa) / (fb - fa);
            fc = ob.evaluateExpression(eq, c);

            if (fc == 0.0 || calculateError(c) == false) {
                // Found the root or reached desired accuracy
                root1 = root2 = c;
                break;
            } else {
            }

            if (fa * fc < 0) {
                // Root lies between a and c
                b = c;
                root1 = c;
            } else {
                // Root lies between c and b
                a = c;
                root2 = c;
            }

            System.out.printf("iteration %d: f(x) = %.10f     root= [%f,%f]%n", i + 1, fc, root1, root2);

            // Storing the value of f(c) for iteration 'i' in the array
            fx[i] = fc;

            if (i >= 1 && Math.abs(fx[i] - fx[i - 1]) < Ea) {
                // If the absolute difference between f(c) of this iteration and the previous iteration is less than Ea
                // Then, it is considered the root, and the loop is terminated
                root1 = root2 = c;
                break;
            }
        }

        System.out.printf("Approximate roots: [%f,%f]%n", root1, root2);
    }

    private boolean calculateError(double root) {
    double error = Math.abs(root - (a * fx[1] - b * fx[0]) / (fx[1] - fx[0])); // Calculate absolute error
    if (error > Ea)
        return true; // continue iterating since error is greater than accepted error
    else
        return false; // end iteration
}

}
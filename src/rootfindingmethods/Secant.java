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
import java.util.Scanner;

public class Secant {
    private double a, b, Ea;
    private double root1; // +ve
    private double root2; // -ve
    public String eq;
    private int itr;

    public Secant(double a, double b, double Ea, int itr) {
        this.a = a;
        this.b = b;
        this.Ea = Ea;
        this.itr = itr;
    }

    public void setEquation(String equation) {
        this.eq = equation;
    }

    public void secantMethod() {
        InfixPostfix ob = new InfixPostfix();
        double fa = ob.evaluateExpression(eq, a);
        double fb = ob.evaluateExpression(eq, b);

        for (int i = 0; i < itr; i++) {
            double c = b - (fb * (b - a)) / (fb - fa);
            double fc = ob.evaluateExpression(eq, c);

            if (fc == 0.0 || calculateError(c) == -1) {
                // Found the root or reached desired accuracy
                root1 = root2 = c;
                break;
            }

            a = b;
            fa = fb;
            b = c;
            fb = fc;

            System.out.printf("iteration %d: f(x) = %.10f     root= [%f,%f]%n", i + 1, fc, root1, root2);
        }

        System.out.printf("Approximate roots: [%f,%f]%n", root1, root2);
    }

   private int calculateError(double root) {
    double error = Math.abs(root - b); // Calculate absolute error
    if (error > Ea)
        return -1; // end iteration
    else
        return 1; // continue iterating since error is greater than accepted error
}
}
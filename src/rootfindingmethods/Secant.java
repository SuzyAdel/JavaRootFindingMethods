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
    private double c;
    private int itr ,flag=3;

   public Secant(double a, double b, int itr) {
    this.a = a;
    this.b = b;
    this.itr = itr;
    this.Ea=0.10;
    flag = 1;
}

public Secant(double a, double b, double Ea) {
    this.a = a;
    this.b = b;
    this.Ea = Ea;
    this.itr =50;
    flag = 2;
}

    

    public void setEquation(String equation) {
        this.eq = equation;
    }

    public void secantMethod() {
        InfixPostfix ob = new InfixPostfix();
        double fa = ob.evaluateExpression(eq, a);
        double fb = ob.evaluateExpression(eq, b);

        for (int i = 0; i < itr; i++) {
            c = b - (fb * (b - a)) / (fb - fa);
            double fc = ob.evaluateExpression(eq, c);

            if ((fc == 0.0 || calculateError(c) == -1 || b == c) && i > 0) {
                // Found the root or reached desired accuracy
//                root1 = root2 = c;
                break;
            }

            a = b;
            fa = fb;
            b = c;
            fb = fc;
            
//            if(flag ==1){
            System.out.printf("iteration %d: f(x) = %.10f     root= [%f]\n", i + 1, fc,c);
//            }
        }

        System.out.printf("Approximate root: [%f]", c);
    }

   private int calculateError(double root) {
    double error = (Math.abs((root - b)/root))*100;  // Calculate absolute error
    if (error > Ea)
        return -1; // end iteration
    else
        return 1; // continue iterating since error is greater than accepted error
   }
}

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
import java.io.PrintStream;
import java.util.Scanner;

public class Newtons {
    private double a,Ea, xnew;
    private int itr;
    public String eq;
    public String derivative;
    int flag = 3, i;

    Scanner scanner = new Scanner(System.in);

    public Newtons(double a, int itr) {
        this.a = a;
        this.itr = itr;
        this.Ea=0.10;       
        flag = 1;
    }

    public Newtons(double a,double Ea) {
        this.a = a;
        this.eq = eq;
        this.Ea=Ea;
        this.itr =50;
        flag = 2;
    }

    public void setEquation(String equation) {
        this.eq = equation;
    }

    public void newtonsMethod() {
        InfixPostfix ob = new InfixPostfix();
        Getderivative();
        System.out.println("equation: " + eq);

        if (flag == 1) {
            for (i = 0; i < itr; i++) {
                double fx = ob.evaluateExpression(eq, a);
                double fdx = ob.evaluateExpression(derivative, a);
                xnew = a - (fx / fdx);
                System.out.printf("iteration %d: f(x) = %.10f     root= [%f]\n", i + 1, fx, xnew);

                if (fx == 0 || a==0)
                    break;
                a = xnew;
            }
        } else if (flag == 2) {
            for (i = 0; i < itr; i++) {
                double fx = ob.evaluateExpression(eq, a);
                double fdx = ob.evaluateExpression(derivative, a);
                xnew = a - (fx / fdx);

                if (calculateError(xnew)) {
                    double error = Math.abs(xnew - a);

                    // Update 'a' for the next iteration
                    a = xnew;
                } else {
                   double error = Math.abs(xnew - a);
                    break;
                } 
//               if(flag==1)
               
                System.out.printf("iteration %d :     x = %.10f\n", i + 1, xnew);
               
            }
        }
        System.out.printf("Approximate roots: [%f]%n", xnew); 
    }


    private void Getderivative() {
        System.out.println("Enter f'(x) equation:");
        System.out.println("Add a space after each term");
        this.derivative = scanner.nextLine();
    }

   private boolean calculateError(double root) {
    double error = ( Math.abs( (root-a)/root) )*100; // Calculate absolute error
    if (error > Ea)
        return true; // continue iterating since error is greater than accepted error
    else
        return false; // end iteration
}
}
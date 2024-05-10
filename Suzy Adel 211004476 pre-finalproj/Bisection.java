/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rootfindingmethods;

//  @author Suzy

public class Bisection {
    private double a, b,x, Ea;
    private double root1; // +ve
    private double root2; // -ve
    public String eq;
    private int itr, flag=3;
    
    public Bisection(double a, double b , int itr) {
        this.a = a;
        this.b = b;
        this.itr =itr;
        this.Ea=0.10;
        flag= 1;
    }


    public Bisection(double a, double b, double Ea) {
        this.a = a;
        this.b = b;
        this.Ea = Ea;
        this.itr =50;
        flag =2;
    }

    public void setEquation(String equation) {
        this.eq = equation;
    }

    public void bisectionMethod() {
        InfixPostfix ob = new InfixPostfix();

        double fa = ob.evaluateExpression(eq, a);
        double fb = ob.evaluateExpression(eq, b);

        // Checking if fa * fb < 0
        if (fa * fb >= 0) {
            System.out.println("Cannot calculate: fa * fb >= 0");
            return;
        }

        for (int i = 0; i < itr; i++) {
             x = (a + b) / 2.0;
            double fx = ob.evaluateExpression(eq, x);
         
            if (fx == 0.0 || calculateError(x) == -1) {
                // Found the root or reached desired accuracy
//                root1 = root2 = x; makes it prit 2 of the same value so ulter in the itteration itself 
                break;
            }

            if (fx * fa < 0) {
                // Root lies between a and x
                b = x;
                root1 = x;
            } else {
                // Root lies between x and b
                a = x;
                root2 = x;
            }
//            if(flag==1){
                System.out.printf("iteration %d: f(x) = %.10f     root= [%f,%f]%n", i + 1, fx, root1, root2);
//            }
        }

                System.out.printf("Approximate root: [%f]", x);   
    }

    public int calculateError(double root) {
    double error = (Math.abs( (root - ((a + b) / 2.0) )/root) )*100; // Calculate absolute error
    if (error > Ea)
        return -1; // end iteration
    else
        return 1; // continue iterating since error is greater than accepted error
}
}
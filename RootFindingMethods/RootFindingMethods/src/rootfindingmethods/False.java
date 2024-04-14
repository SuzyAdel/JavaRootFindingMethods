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
    private double a, b, Ea;
    private double[] fx = new double[50];
    private double root1; // +ve
    private double root2; // -ve
    private int itr;
    public String eq;

    public False(double a, double b, int itr) {
        this.a = a;
        this.b = b;
        this.itr = itr;
    }

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

        for (int i = 0; i < itr; i++) {
            fx[i] = ob.ExVariable(eq, String.valueOf(a));
            if (fx[i] > 0) root1 = a;
            else root2 = a;
            
            double fxPrev = fx[i];
            fx[i + 1] = ob.ExVariable(eq, String.valueOf(b));
            if (fx[i + 1] > 0) root1 = b;
            else root2 = b;

            double xnew = b - ((fx[i + 1] * (b - a)) / (fx[i + 1] - fx[i]));
            double fxNew = ob.ExVariable(eq, String.valueOf(xnew));
            if (fxNew > 0) root1 = xnew;
            else root2 = xnew;

            if (fx[i] * fx[i + 1] >= 0) {
                System.out.println("Cannot calculate: fx[i] * fx[i+1] >= 0");
                return; // Exit method if condition not met
            }

            a = b; // b becomes xi-1 so new a
            b = xnew; // b becomes xi so new b

            System.out.printf("iteration %d: f(x) = %.10f     root= [%f,%f]%n", i + 1, fxNew, root1, root2);

            double error = Math.abs(fxNew - fxPrev);
            if (error <= Ea || fxNew == 0) {
                // Stop iterating if desired accuracy is reached or if a root is found
                break;
            }
        }
    }
}

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
public class Newtons {
    private double a, xnew, Ea;
    private int itr;
    public String eq;
    int flag = 3, i;

    public Newtons(double a, int itr, String eq) {
        this.a = a;
        this.itr = itr;
        this.eq = eq;
        flag = 1;
    }

    public Newtons(double a, double Ea, String eq) {
        this.a = a;
        this.Ea = Ea;
        this.eq = eq;
        flag = 2;
        itr = 50; // for loop stopping condition, let 50 be max
    }

    public void newtonsMethod() {
        InfixPostfix ob = new InfixPostfix();

        if (flag == 1) {
            for (i = 0; i < itr; i++) {
                double fx = ob.ExVariable(eq, String.valueOf(a));
                double fdx = ob.differentiate(eq, a);

                xnew = a - (fx / fdx);
                if (fx == 0)
                    break;
                System.out.printf("iteration %d :     x = %.10f\n", i, xnew);
                a = xnew;
            }
        } else if (flag == 2) {
            for (i = 0; i < itr; i++) {
                double fx = ob.ExVariable(eq, String.valueOf(a));
                double fdx = ob.differentiate(eq, a);

                xnew = a - (fx / fdx);

                if (i > 0) {
                    double error = Math.abs(xnew - a);
                    // Update 'a' for the next iteration
                    a = xnew;
                    if (error <= Ea) {
                        break;
                    }
                    System.out.printf(" iteration %d :     x = %.10f\n      ea=%f", i + 1, xnew, error);
                } else {
                    // Update 'a' for the next iteration
                    a = xnew;
                }
            }
        }
    }
}
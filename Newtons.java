/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rootfindingmethods;
import java.util.Scanner;

/**
 *
 * @author Suzy
 */
public class Newtons {
    private double a, xnew;
    private int itr;
    public String eq;
    public String derivative;
    int flag = 3, i;

    Scanner scanner =new Scanner (System.in);


    public Newtons(double a, int itr, String eq) {
        this.a = a;
        this.itr = itr;
        this.eq = eq;
        flag = 1;
    }

    public void newtonsMethod() {
        InfixPostfix ob = new InfixPostfix();
        Getderivative();

        if (flag == 1) {
            for (i = 0; i < itr; i++) {
                double fx = ob.evaluateExpression(eq, a);
                double fdx = ob.evaluateExpression(derivative, a);
                xnew = a - (fx / fdx);
                if (fx == 0)
                    break;
                System.out.printf("iteration %d :     x = %.10f\n", i+1, xnew);
                a = xnew;
            }
        // } else if (flag == 2) {
        //     for (i = 0; i < itr; i++) {
        //         double fx = ob.evaluateExpression(eq, a);
        //         double fdx = ob.differentiate(eq, a);

        //         xnew = a - (fx / fdx);

        //         if (i > 0) {
        //             double error = Math.abs(xnew - a);
        //             // Update 'a' for the next iteration
        //             a = xnew;
                    
        //             System.out.printf(" iteration %d :     x = %.10f\n      ea=%f", i + 1, xnew, error);
        //         } else {
        //             // Update 'a' for the next iteration
        //             a = xnew;
        //         }
            }
        }

    private void Getderivative()
    {
    
        System.out.println("Enter f'(x) equation:");
        System.out.println("Add a space after each term");
        this.derivative = scanner.nextLine();

    }  


    }

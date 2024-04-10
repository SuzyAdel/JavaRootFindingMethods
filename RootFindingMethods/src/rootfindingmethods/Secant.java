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
    private double a,b,xnew,Ea;//[a,b], xi+1= xnew 
    private double[] fx = new double[50];
    private int itr;
    String eq;
    
    int flag=3,i;

    public Secant(double a, double b, int itr) {
        this.a = a;
        this.b = b;
        this.itr = itr;
        flag=1;
    }

    public Secant(double a, double b, double Ea) {
        this.a = a;
        this.b = b;
        this.Ea = Ea;
        flag=2;
        itr=50;// for loop stoping condittion , let 50 be max 
    }
    
     public void secantMethod() {
        InfixPostfix ob = new InfixPostfix();

        if (flag == 1) {
            fx[0] = ob.ExVariable(eq, String.valueOf(a));
            fx[1] = ob.ExVariable(eq, String.valueOf(b));

            xnew = b - ((fx[1] - fx[0]) / (fx[1] * (b - a)));
            fx[2] = ob.ExVariable(eq, String.valueOf(xnew));
            
            a = b; // b becomes xi-1 so new a
            b = xnew; // b becomes xi so new b
            
            System.out.printf("iteration 1: f(x) = %.10f     root= [%f,%f]%n", fx[2], a, b);
            
            for (i = 2; i < itr - 1; i++) {
                
                xnew = b - ((fx[i] * (b - a)) / (fx[i] - fx[i - 1]));
                fx[i + 1] = ob.ExVariable(eq, String.valueOf(xnew)); // 3
                
                a = b; // b becomes xi-1 so new a
                b = xnew; // b becomes xi so new b
                
                System.out.printf("iteration %d: f(x) = %.10f     root= [%f,%f]%n", i, fx[i + 1], a, b);
                if (fx[i] == 0) 
                    break;
            }
        } else if (flag == 2) {
            fx[0] = ob.ExVariable(eq, String.valueOf(a));
            fx[1] = ob.ExVariable(eq, String.valueOf(b));

            xnew = b - ((fx[1] * (b - a)) / (fx[1] - fx[0]));
            fx[2] = ob.ExVariable(eq, String.valueOf(xnew));
            
            a = b; // b becomes xi-1 so new a
            b = xnew; // b becomes xi so new b
            
            System.out.printf("iteration 1: f(x) = %.10f     root= [%f,%f]%n", fx[2], a, b);
            
            for (i = 2; i < itr - 1; i++) {

                xnew = b - ((fx[i] * (b - a)) / (fx[i] - fx[i - 1]));
                fx[i + 1] = ob.ExVariable(eq, String.valueOf(xnew)); // 3
                
                a = b; // b becomes xi-1 so new a
                b = xnew; // b becomes xi so new b
                double error = Math.abs(fx[i] - fx[i - 1]);
                System.out.printf("iteration %d: f(x) = %.10f     root= [%f,%f] ea=%f%n", i, fx[i + 1], a, b, error);
                
                if (error > Ea || error == Ea) 
                    break;
                if (fx[i] == 0) 
                    break;
            }
        }
    }
}
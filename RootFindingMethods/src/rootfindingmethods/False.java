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
public class False {
     private double a,b,xnew,Ea;
    private double[] fx = new double[50];
    private int itr;
    double root1;//+ve
    double root2;//-ve
    String eq;
    
    int flag=3,i;

    public False(double a, double b, int itr) {
        this.a = a;
        this.b = b;
        this.itr = itr;
        flag=1;

    }

    public False(double a, double b, double Ea) {
        this.a = a;
        this.b = b;
        this.Ea = Ea;
        flag=2;
        itr=50;// for loop stoping condittion , let 50 be max
    }
    
    public void falseMethod() {
        InfixPostfix ob = new InfixPostfix();

        if (flag == 1) {
            fx[0] = ob.ExVariable(eq, String.valueOf(a));
             if (fx[0] > 0) root1 = a; else root2 = a;
            fx[1] = ob.ExVariable(eq, String.valueOf(b));
             if (fx[1] > 0) root1 = b; else root2 = b;
             
            xnew = b - ((fx[1] * (b - a)) / (fx[1] - fx[0]));
            fx[2] = ob.ExVariable(eq, String.valueOf(xnew));
                if (fx[2] > 0) root1 = xnew; else root2 = xnew;
                
            a = b; // b becomes xi-1 so new a
            b = xnew; // b becomes xi so new b
            
            System.out.printf("iteration 1: f(x) = %.10f     root= [%f,%f]%n", fx[2], root1, root2);
            
            for (i = 2; i < itr - 1; i++) {
                
                xnew = b - ((fx[i] * (b - a)) / (fx[i] - fx[i - 1]));
                fx[i + 1] = ob.ExVariable(eq, String.valueOf(xnew)); // 3
                   if (fx[i+1] > 0) root1 = xnew; else root2 = xnew;
                
                a = b; // b becomes xi-1 so new a
                b = xnew; // b becomes xi so new b
                
                System.out.printf("iteration %d: f(x) = %.10f     root= [%f,%f]%n", i, fx[i + 1], root1, root2);
                if (fx[i] == 0) 
                    break;
            }
        } else if (flag == 2) {
             fx[0] = ob.ExVariable(eq, String.valueOf(a));
             if (fx[0] > 0) root1 = a; else root2 = a;
            fx[1] = ob.ExVariable(eq, String.valueOf(b));
             if (fx[1] > 0) root1 = b; else root2 = b;
             
            xnew = b - ((fx[1] * (b - a)) / (fx[1] - fx[0]));
            fx[2] = ob.ExVariable(eq, String.valueOf(xnew));
                if (fx[2] > 0) root1 = xnew; else root2 = xnew;
                
            a = b; // b becomes xi-1 so new a
            b = xnew; // b becomes xi so new b
            
            System.out.printf("iteration 1: f(x) = %.10f     root= [%f,%f]%n", fx[2], root1, root2);
            
            for (i = 2; i < itr - 1; i++) {
                
                xnew = b - ((fx[i] * (b - a)) / (fx[i] - fx[i - 1]));
                fx[i + 1] = ob.ExVariable(eq, String.valueOf(xnew)); // 3
                   if (fx[i+1] > 0) root1 = xnew; else root2 = xnew;
                
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



package rootfindingmethods;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Suzy
 */
public class Bisection {
    private double a,b,fa,fb,x,Ea;
    private double[] fx = new double[50];
    private int itr;
    double root1;//+ve
    double root2;//-ve
    String eq;
    
    int flag=3,i;
    
    
    public Bisection(double a, double b, int itr){
        this.a = a;
        this.b = b;
        this.itr= itr ;
        flag=1;
    }

    public Bisection(double a, double b, double Ea) {
        this.a = a;
        this.b = b;
        this.Ea = Ea;
        flag=2;
        itr=50;// for loop stoping condittion , let 50 be max 
    }
    
     public void bisectionMethod() {
        InfixPostfix ob = new InfixPostfix();

        if (flag == 1) {
            for (i = 0; i < itr; i++) {
                fa = ob.ExVariable(eq, String.valueOf(a));
                if (fa > 0) root1 = a; else root2 = a;

                fb = ob.ExVariable(eq, String.valueOf(b));
                if (fb > 0) root1 = b; else root2 = b;

                    x = (root1 + root2) / 2.0;

                    fx[i] = ob.ExVariable(eq, String.valueOf(x));
                    if (fx[i] > 0) root1 = x; else root2 = x;
                    if (fx[i]==0) 
                            break;
                    System.out.printf("%d f(x) = %.10f     x = %.10f\n", i, fx[i], x);
                
            }
        } 
        else if (flag == 2) {
            fa = ob.ExVariable(eq, String.valueOf(a));
            if (fa > 0) root1 = a; else root2 = a;

            fb = ob.ExVariable(eq, String.valueOf(b));
            if (fb > 0) root1 = b; else root2 = b;

            for (i = 0; i < itr; i++) {
                x = (root1 + root2) / 2.0;

                fx[i] = ob.ExVariable(eq, String.valueOf(x));
                if (fx[i] > 0) root1 = x; else root2 = x;
                if (fx[i]==0) 
                            break;
                
                if (i > 1) {
                    double error = Math.abs(fx[i] - fx[i - 1]);
                    if (error > Ea || error == Ea) {
                        break;
                    }
                System.out.printf("%d f(x) = %.10f     x = %.10f\n      ea=%f", i+1,fx[i],x,error);

                }
            }
        }
        System.out.printf("[%f,%f]",root1,root2);
    }
}
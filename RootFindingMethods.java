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

public class RootFindingMethods {
    double a;
    double b;
    int itr,choice;
    double e; 
    String eq;
    
    Scanner scanner =new Scanner (System.in);
    
public void getChoice() {
        System.out.println(" Enter the Number of iterations");
        itr = scanner.nextInt();

            if (itr <0) {
                System.out.println("Enter the number of iterations: ");
                getChoice();
            }   
    }

public void GetEqn()
{
    System.out.println("Enter f(x) equation:");
    System.out.println("Add a space after each term");
    this.eq = scanner.nextLine();

}  

public void GetRoots(){
    
    System.out.print("enter 1st root");
    a=scanner.nextDouble();
    
    System.out.print("enter 2nd root");
    b=scanner.nextDouble();
}
        
    /**
 * @param args the command line arguments
 */

public static void main(String[] args) {
   RootFindingMethods rootFinder = new RootFindingMethods();
    rootFinder.GetEqn(); // Get equation
   
    rootFinder.getChoice(); // Get choice for stopping condition
   
    rootFinder.GetRoots(); // Get initial roots

    System.out.println(rootFinder.eq);

    // Bisection Method
    Bisection bisection = new Bisection(rootFinder.a, rootFinder.b, rootFinder.itr);
    bisection.eq = rootFinder.eq;
    System.out.println("\nBisection Method:");
    bisection.bisectionMethod();
    // False Position Method
    False falsePosition = new False(rootFinder.a, rootFinder.b, rootFinder.itr);
    falsePosition.eq = rootFinder.eq;
    System.out.println("\nFalse Position Method:");
    falsePosition.falseMethod();
    // Secant Method
    Secant secant = new Secant(rootFinder.a, rootFinder.b, rootFinder.itr);
    secant.eq = rootFinder.eq;
    System.out.println("\nSecant Method:");
    secant.secantMethod();
    // Newton's Method
    Newtons newton = new Newtons(rootFinder.a, rootFinder.itr, rootFinder.eq);
    System.out.println("\nNewton's Method:");
    newton.newtonsMethod();
    }
}

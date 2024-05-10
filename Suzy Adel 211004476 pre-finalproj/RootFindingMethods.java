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

    private static Object newtons;
    double a;
    double b;
    private static int choice;
    int itr;
    double e;
    double exact;
    String eq;

    Scanner scanner = new Scanner(System.in);

    public void getChoice() {
        System.out.println("Choose the stopping condition: \n 1: Number of iterations \n 2: Error");
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter the number of iterations: ");
                itr = scanner.nextInt();
                break;
            case 2:
                System.out.println("Enter the error limit: ");
                e = scanner.nextDouble();
                System.out.println("Enter the exact value: ");
                exact = scanner.nextDouble();
                break;
            default:
                System.out.println("Invalid choice. Please re-enter.");
                getChoice(); // Re-enter if choice is invalid
                break;
        }
    }

    public void GetEqn() {
        System.out.println("Enter f(x) equation:");
        System.out.println("Add a space after each term");
        this.eq = scanner.nextLine();
    }

    public void GetRoots() {
        System.out.print("enter 1st root: ");
        a = scanner.nextDouble();

        System.out.print("enter 2nd root: ");
        b = scanner.nextDouble();
    }

//    public int calculateError(double root, double e, double exact) {
//        double error = Math.abs(exact - root); // Calculate absolute error
//        if (error > e)
//            return 1; // continue iterating since error is greater than accepted error 
//        else
//            return -1; // end iteration
//    }

    public static void main(String[] args) {
        RootFindingMethods rootFinder = new RootFindingMethods();
        rootFinder.GetEqn(); // Get equation

        rootFinder.getChoice(); // Get choice for stopping condition

        rootFinder.GetRoots(); // Get initial roots

        System.out.println(rootFinder.eq);

        // Bisection Method
        if (choice == 1){
        Bisection bisection = new Bisection(rootFinder.a, rootFinder.b, rootFinder.itr);
        bisection.eq = rootFinder.eq;
        System.out.println("\n \nBisection Method:");
        bisection.bisectionMethod();

        }
        else if(choice==2){
        Bisection bisection = new Bisection(rootFinder.a, rootFinder.b, rootFinder.e);
        bisection.eq = rootFinder.eq;
        System.out.println("\n \nBisection Method:");
        bisection.bisectionMethod();
        }
      
        // False Position Method
        if (choice == 1){
        False falsePosition = new False(rootFinder.a, rootFinder.b, rootFinder.itr);
        falsePosition.eq = rootFinder.eq;
        System.out.println("\n \nFalse Position Method:");
        falsePosition.falseMethod();   
        }
        else if (choice ==2 ){
        False falsePosition = new False(rootFinder.a, rootFinder.b, rootFinder.e);
        falsePosition.eq = rootFinder.eq;
        System.out.println("\n \nFalse Position Method:");
        falsePosition.falseMethod();
        }
       

        // Secant Method
        if (choice ==1){
        Secant secant = new Secant(rootFinder.a, rootFinder.b,rootFinder.itr);
        secant.eq = rootFinder.eq;
        System.out.println("\n \nSecant Method:");
        secant.secantMethod();  
        }
        if (choice ==2){
        Secant secant = new Secant(rootFinder.a, rootFinder.b,rootFinder.e);
        secant.eq = rootFinder.eq;
        System.out.println("\n \nSecant Method:");
        secant.secantMethod();  
        }

        // Newton's Method
        if(choice==1){
        Newtons newton = new Newtons(rootFinder.a,rootFinder.itr);
        newton.eq = rootFinder.eq;
        System.out.println("\n \nNewton's Method:");
        newton.newtonsMethod();   
        }
        else if(choice==2)
        {
        Newtons newton = new Newtons(rootFinder.a,rootFinder.e);
        newton.eq = rootFinder.eq;
        System.out.println("\n \nNewton's Method:");
        newton.newtonsMethod();
        }
    }
}
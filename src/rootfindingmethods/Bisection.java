package rootfindingmethods;


public class Bisection {
    private double a, b, Ea;
    private double root1; // +ve
    private double root2; // -ve
    public String eq;
    private int itr;

    public Bisection(double a, double b, double Ea, int itr) {
        this.a = a;
        this.b = b;
        this.Ea = Ea;
        this.itr = itr;
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
            double x = (a + b) / 2.0;
            double fx = ob.evaluateExpression(eq, x);

            if (fx == 0.0 || calculateError(x) == -1) {
                // Found the root or reached desired accuracy
                root1 = root2 = x;
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

            System.out.printf("iteration %d: f(x) = %.10f     root= [%f,%f]%n", i + 1, fx, root1, root2);
        }

        System.out.printf("Approximate roots: [%f,%f]%n", root1, root2);
    }

    public int calculateError(double root) {
    double error = Math.abs(root - (a + b) / 2.0); // Calculate absolute error
    if (error > Ea)
        return -1; // end iteration
    else
        return 1; // continue iterating since error is greater than accepted error
}
}
# JavaRootFindingMethods

•	Root Finding Methods (April 2024) - Implemented Java project for root-finding algorithms like Secant and Newton's methods. Integrated infix-to-postfix expression evaluator and differentiation function for accurate root calculation from user-defined equations.

This repository contains a Java implementation of common root-finding methods used in numerical analysis. 

In many scientific and engineering problems, we encounter equations that cannot be solved analytically or algebraically. These equations may involve transcendental functions, complex relationships, or multiple variables, making it difficult to find exact solutions. However, finding the roots (or solutions) of these equations is crucial for understanding the behavior of systems or making predictions.

![exgraph](https://github.com/SuzyAdel/JavaRootFindingMethods/assets/128175020/ea680dbb-7bbe-4b59-8771-5fb95671fff2)

Writing a code to implement numerical finding methods for solving nonlinear equations offers benefits such as increased accuracy, efficiency, flexibility for adapting to different problems, visualization tools for understanding solution behavior, and automation for rapid experimentation and refinement of models.

![java](https://github.com/SuzyAdel/JavaRootFindingMethods/assets/128175020/893deef6-63db-4725-a20f-0fe491ce663e)


# Bisection Method:

![bisection](https://github.com/SuzyAdel/JavaRootFindingMethods/assets/128175020/7993f4bf-fa61-47d9-ba2a-fea942cccb23)

**The bisection method** is a straightforward numerical approach for finding the root of a function within a specified interval. By iteratively narrowing down the interval based on the sign change of the function, the method converges to the root with guaranteed accuracy. Its simplicity and reliability make it a popular choice for solving a wide range of equations in engineering, science, and finance due to ease in its formula.

The solution entails iterating under the condition (if (fa * fb >= 0)) and breaking the iteration once it reaches zero, or if (fx == 0.0 || calculateError(x) == -1), or if it reaches the error limit.

Refining the conditions improved its ability to execute simple calculations accurately, considering both the error limit and the number of iterations, enhancing its speed significantly.

# Secant Method:
 
![secant](https://github.com/SuzyAdel/JavaRootFindingMethods/assets/128175020/b861c351-b8aa-440e-8349-7c0728dc1a4b)

**The secant method** is a numerical technique for finding roots, particularly useful when the function's derivative is not readily available. It approximates the root by drawing a secant line between two points on the function curve and extrapolating where it intersects the x-axis. This iterative process continues until convergence to the root is achieved. Though not always as robust as the bisection method, its simplicity and efficiency make it valuable for quick root approximation in various fields like optimization and mathematical modeling.

After refining the stopping condition, a logical error was identified (root1 = root2 = c;), causing it to print the same root twice even though they were calculated separately.Addressing this issue ensured the printing of correct values and displayed the correct secant logical roots that were swappable.


# False Position Method:
 
![False-Position](https://github.com/SuzyAdel/JavaRootFindingMethods/assets/128175020/9d5c7196-1fbf-4e88-ad2f-ba1112013910)

**The false position method**, also known as the regula falsi method, is a numerical approach for finding roots of a function within a specified interval. It operates similarly to the bisection method but improves convergence by estimating the root based on linear interpolation between two points on the function curve. By selecting points that bound the root and iteratively refining the interval, the method gradually narrows down to the true root. While offering faster convergence than the bisection method, false position may exhibit slower convergence in some cases. However, its simplicity and ability to handle non-monotonic functions make it a valuable tool for root-finding tasks in engineering, science, and finance.

In the false position method, there was a mix of issues between the breaking condition and the repetitive root problem.Another issue arose from the printing output on each iteration, stemming from the order of the for loop and the if condition.

# Newton's Method:

![newtons](https://github.com/SuzyAdel/JavaRootFindingMethods/assets/128175020/6f142dff-374e-4220-b369-dda4ba2d716a)

**Newton's method**, named after Sir Isaac Newton, is a powerful numerical technique for finding roots of a function. It works by iteratively improving an initial guess of the root using the function's derivative. By approximating the function with a linear equation and finding where it intersects the x-axis, Newton's method rapidly converges to the root with quadratic convergence under favorable conditions. While highly efficient for well-behaved functions, Newton's method may fail or exhibit slow convergence for certain functions or poorly chosen initial guesses. Despite this limitation, its speed and effectiveness make it a popular choice for root-finding problems in various fields, including mathematics, engineering, and physics.

In Newton's method, there was a problem where "x new" was printed twice, and the derivative didn't function properly due to numerous sub-conditions of defentiation. Consequently, we modified it to prompt the user for replacement and execution readiness. Finaly fixed the print message to fit this method spesifically.

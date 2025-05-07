import java.rmi.*;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {
			String serverURL = "rmi://localhost/Server";
			ServerIntf serverIntf = (ServerIntf) Naming.lookup(serverURL);

			System.out.println("Enter first number");
			double num1 = sc.nextDouble();

			System.out.println("Enter second number");
			double num2 = sc.nextDouble();

			System.out.println("First number is: " + num1);
			System.out.println("Second number is: " + num2);

			System.out.println("Final Results");

			System.out.println("Addition is " + serverIntf.Addition(num1, num2));
			System.out.println("Subtraction is " + serverIntf.Subtraction (num1, num2));
			System.out.println("Multiplication is " + serverIntf.Multiplication(num1, num2));
			System.out.println("Division is " + serverIntf.Division(num1, num2));


		}catch(Exception e) {
			System.out.println("Exception Occured at Client" + e.getMessage());
		}

	}
}
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;      //Base class for exporting remote objects.


public class ServerImpl extends UnicastRemoteObject
	implements ServerIntf {
		
	public ServerImpl() throws RemoteException{

	}	
	
	public double Addition(double num1, double num2) throws RemoteException {
		return num1+num2;
	}

	public double Subtraction(double num1, double num2) throws RemoteException {
		return num1-num2;
	}

	public double Multiplication(double num1, double num2) throws RemoteException{
		return num1*num2;
	}

	public double Division(double num1, double num2) throws RemoteException {
        if (num2 == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return num1 / num2;
    }




}
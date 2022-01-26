package br.com.eliel.math;

public class SimpleMath {

	public Double sum(Double firstNumber, Double secondNumber) {		
		return firstNumber + secondNumber;		
	}
		
	public Double subtraction(Double firstNumber, Double secondNumber) {		
		return firstNumber - secondNumber;		
	}
		
	public Double division(Double firstNumber, Double secondNumber) {		
		return firstNumber / secondNumber;			
	}
		
	public Double multiplication(Double firstNumber, Double secondNumber) {
		return firstNumber * secondNumber;		
	}
		
	public Double average(Double firstNumber, Double secondNumber) {				
		return (firstNumber + secondNumber) / 2;		
	}
		
	public Double squareRoot(Double number) throws Exception {
		return (Double) Math.sqrt(number);		
	}
}

/**
 * 
 * @author Sepand Jahrominejad
 * 
 * CMSC204 - Project 2
 *
 */
public class Notation extends Object {
	public Notation() {
		
	}
	
	
	
	/**
	 * Checks the precedence of the operators
	 * @param c A char data type
	 * @return 1 if the operators are + and -
	 * @return 2 if the operators are * and /
	 * @return 0 if the character is not an operator
	 * 
	 */
	private static int precedence(char c) {
		if(c=='+' || c=='-')
			return 1;
		else if(c=='/' || c=='*')
			return 2;
		return 0;
	}
	
	
	
	
	/**
	 * Converts a String with infix notation into a string with postfix notation
	 * @param infix A string with infix notation
	 * @return A string with postfix notation
	 */
	public static String convertInfixToPostfix(String infix) {
		MyStack<Character> Pstack = new MyStack<Character>(infix.length());
		for(int i=0; i<infix.length(); i++) {
			if(infix.charAt(i)=='(')
				Pstack.push(infix.charAt(i));
			else if(infix.charAt(i)==')') {
				if(Pstack.isEmpty())
					throw new InvalidNotationFormatException();
				else
					Pstack.pop();
			}
		}
		if(!Pstack.isEmpty())
			throw new InvalidNotationFormatException();
		
		MyQueue<Character> queue = new MyQueue<Character>(infix.length());
		MyStack<Character> stack = new MyStack<Character>(infix.length());
		
		for(int i=0; i<infix.length(); i++) {
			char c = infix.charAt(i);
			
			if(Character.isDigit(c))
				queue.enqueue(c);
			
			else if(c=='(')
				stack.push(c);
			
			else if(c==')') {
				while(!stack.isEmpty() && 
						stack.top()!='(') {
					
					queue.enqueue(stack.pop());
				}
				stack.pop();
			}
			
			else {
				while(!stack.isEmpty() && 
						precedence(c)<=precedence(stack.top())) {
					
					queue.enqueue(c);
				}
				stack.push(c);
			}
				
		}
		return queue.toString();
		
	}
	
	
	
	/**
	 * Converts a string with postfix notation into a string with infix notation
	 * @param postfix A string with postfix notation
	 * @return A string with infix notation
	 * @throws InvalidNotationFormatException if the postfix notation entered is invalid
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {

		
		
		
		MyStack<String> stack = new MyStack<String>(postfix.length());
		
		for(int i=0; i<postfix.length(); i++) {
			if(Character.isDigit(postfix.charAt(i)))
				stack.push(Character.toString(postfix.charAt(i)));
			
			else if (!Character.isDigit(postfix.charAt(i))){
				if (stack.size() < 2)
					throw new InvalidNotationFormatException();
				
				String str1 = stack.pop();
				
				String str2 = stack.pop();
				
				stack.push("(" + str2 + Character.toString(postfix.charAt(i)) +
						str1 + ")");
				
			}
		}
		if(stack.size() > 1)
			throw new InvalidNotationFormatException();
		return stack.toString();
	}
	
	
	
	/**
	 * Calculates (evaluates) a postfix notation string
	 * @param postfixExpr
	 * @return A double
	 * @throws InvalidNotationFormatException if the postfix notation is invalid
	 */
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		
		
		
		MyStack<Double> stack = new MyStack<Double>(postfixExpr.length());
		
		for(int i=0; i<postfixExpr.length(); i++) {
			char c = postfixExpr.charAt(i);
			
			if(Character.isDigit(c))
				stack.push((double)Character.getNumericValue(c));
			
			else if(c == '+') {
				if (stack.size() < 2)
					throw new InvalidNotationFormatException();
				double num1 = stack.pop();
				double num2 = stack.pop();
				stack.push(num2+num1);
			}
			
			else if(c == '-') {
				if (stack.size() < 2)
					throw new InvalidNotationFormatException();
				double num1 = stack.pop();
				double num2 = stack.pop();
				stack.push(num2-num1);
			}
			
			else if(c == '/') {
				if (stack.size() < 2)
					throw new InvalidNotationFormatException();
				double num1 = stack.pop();
				double num2 = stack.pop();
				stack.push(num2/num1);
			}
			
			else if(c == '*') {
				if (stack.size() < 2)
					throw new InvalidNotationFormatException();
				double num1 = stack.pop();
				double num2 = stack.pop();
				stack.push(num2*num1);
			}
		}
		
		return stack.pop();
	}
	
	

}

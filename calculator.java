
import java.util.Stack; import java.util.Scanner;
public class calculator {
	
	//@data will be the Stack for the calculations.
	public static Stack<Integer> data = new Stack<Integer>();
	
	/*The main method will operate as the user interface. It will call the
	 * check methods in order to evaluate whether the user's input is correct
	 * and then convert it if it is.
	 * By default, the main method has no preconditions or postconditions.
	 * Invariants:@illegalOperators(String) evaluates to make sure the string only
	 * 								contains digits, operators, and/or 'x'.
	 * 			  @readyForConversion evaluates to check for other problems, including
	 * 								parentheses mismatch, */
	public static void main(String[] args) {
		Scanner ui = new Scanner(System.in);
		//This will be the user interface.
		while(true)
		{
			System.out.println("Enter infix expression: ('quit' to exit)");
			String usrin = ui.nextLine();
			if(usrin.toLowerCase().equals("quit"))
				break;
			//@illegalOperators(String) Checks for any characters that the calculator won't be able to 
			while(!illegalOperators(usrin.toLowerCase()))
			{
				System.out.println("Error in expression! Illegal operator encountered.");
				System.out.println("Enter infix expression: ('quit' to exit)");
				usrin = ui.nextLine();
			}
			//@readyForConversion(String) checks for errors(mismatched parentheses, decimals, & the ending operation)
			while(!readyForConversion(usrin))
			{	
				System.out.println("Enter infix expression: ('quit' to exit)");
				usrin = ui.nextLine();
			}
			//handles variables
			if(usrin.toLowerCase().contains("x"))
			{
				//will loop through and use the same equation until user quits
				while(true)
				{	
					postfixPrintout(infixToPostfix(usrin));
					System.out.println();
					translation = "";
					System.out.println("Enter value of variable x: ('q' to enter new "
							+ "expression)");
					String usrVar = ui.nextLine();
					if(usrVar.contains("q"))
						break;
					while(!Character.isDigit(usrVar.charAt(usrVar.length()-1)))
					{
						System.out.println("Please enter an integer value.");
						usrVar = ui.nextLine();
					}
					//@processString(String.replaceAll())  replaces the variable with value specified
					processString(usrin.toLowerCase().replaceAll("x", usrVar));
				}
			}
			//@processString(String) is the conversion from infix to postfix
			else
				processString(usrin);
		}	
		ui.close();
	}
	
	//Checks for illegal operators. This method uses a structured array of characters and then evaluates
	//a string, moving through the array index by index in a while loop.
	//Postconditions: The String input will be unmodified. True will return if it doesn't contain any,
	//                false if it does contain illegal characters.
	//Preconditions: User must input a String
	//Invariants: None, this method acts as an invariant.
	static boolean illegalOperators(String input)
	{
		//all illegal operators
		char[] illegalOperators = {'!','@','#','$','^','&','=',';',',','\'','"','{',
				'}','[',']','<','>','?','|','\\','a','q','w','e','r','t','y','u',
				'i','o','p','s','d','f','g','h','j','k','l','z','c','v','b','n','m'};
		int i = 0;
		while(i < illegalOperators.length)
		{
			if(input.contains("" + illegalOperators[i]))
				return false;
			i++;
		}
		return true;
	}
	//Checks for further errors in expression.
	//Postconditions: A boolean will be returned deeming whether the user's String can be converted from
	//				  infix to postfix.
	//Preconditions: A String must be passed, given by the user.
	/*Invariants: @parenthesesMatching(String) evaluates that there is an equal number of parentheses
				  @dupliateOperators(String) evaluates there aren't any sequences where two binary 
				  							 operators follow each other.*/		  
	static boolean readyForConversion(String input)
	{
		input = input.replaceAll(" ", "");
		for(int i = 0; i < input.length(); i++)
		{	
			if(i<input.length() -1 && Character.isDigit(input.charAt(i)) && input.charAt(i+1) == '(')
			{
				System.out.println("Error in expression! No operator between operand and the left parentheses.");
				return false;
			}
		}
		//Handles floating point numbers
		if(input.contains("."))
		{
			System.out.println("Error in expression! Expression cannot contain floating"
					+ " point numbers.");
			return false;
		}
		//Handles the expression ending in anything other than a space or a number
		else if(!Character.isDigit(input.charAt(input.length()-1)) && !(input.charAt(
				input.length()-1) == ' ') && !(input.charAt(input.length()-1) == ')') 
				&& !(input.toLowerCase().charAt(input.length()-1) == 'x'))
		{	
			System.out.println("Error in expression! Must end in a digit, closing "
					+ "parentheses, or variable.");
			return false;
		}
		//Handles mismatched parentheses
		else if(!parenthesesMatching(input))
			return false;
		else if(!duplicateOperators(input))
			return false;
		else
			return true;
	
	}
	
	/*The method duplicateOperators(String) will evaluate to make sure that there aren't any binary operators
	 * followed by another binary operator. It will trim the string of all spaces and evaluate if there are any
	 * Character Sequences in which there would be duplicate operators (i.e. '**','/%', etc.
	 * Postconditions: A boolean will be returned in order to determine whether the string should be evaluated further
	 * Preconditions: A string must be passed.
	 * Invariants: None, this operates as an invariant.
	 * */
	static boolean duplicateOperators(String input)
	{
		String removeSpaces = input.replaceAll(" ", "");
		if(removeSpaces.contains("**")||removeSpaces.contains("*/")||removeSpaces.contains("*%"))
		{
			System.out.println("Error in expression! The * operator cannot be followed by another *,%, or /.");
			return false;
		}
		else if(removeSpaces.contains("/*")||removeSpaces.contains("//")||removeSpaces.contains("/%"))
		{
			System.out.println("Error in expression! The / operator cannot be followed by another *,%, or /.");
			return false;
		}
		else if(removeSpaces.contains("%*")||removeSpaces.contains("%/")||removeSpaces.contains("%%"))
		{
			System.out.println("Error in expression! The % operator cannot be followed by another *,%, or /.");
			return false;
		}
		else if(removeSpaces.contains("-*")||removeSpaces.contains("-/")||removeSpaces.contains("-%"))
		{
			System.out.println("Error in expression! The - operator cannot be followed by another *,%, or /.");
			return false;
		}
		else if(removeSpaces.contains("+*")||removeSpaces.contains("+/")||removeSpaces.contains("+%"))
		{
			System.out.println("Error in expression! The + operator cannot be followed by another *,%, or /.");
			return false;
		}
		else if(removeSpaces.contains("(*")||removeSpaces.contains("(%")||removeSpaces.contains("(/"))
		{
			System.out.println("Error in expression! Cannot follow a parentheses with %,*, or /.");
			return false;
		}
		else
			return true;
	}
	
	/* Evaluates for equal pairs of parentheses. Using substrings, it will cut up the user's input smaller chunks
	 * based on the position of matching sets of parentheses(i.e. the first index of "(" and the last index of ")".
	 * If there is a mismatched amount of parentheses, it will throw and error expression to the user.
	 * Postconditions: A boolean will be returned. If false, an error expression will be given, if true the user's
	 * 					expression will be evaluated further.
	 * Preconditions: A string must be given.
	 * Invariants:None, this method operates as an invariant.*/
	static boolean parenthesesMatching(String input)
	{
		//if parentheses is present, evaluate, otherwise, return true.
		if(input.contains("(") || input.contains(")"))
		{	
			//if both are present, evaluate further, otherwise there is an uneven balance
			if(input.contains("(") && input.contains(")"))
			{
				//while both are present, cut done into substrings, removing matched pairs or parentheses
				while(input.contains("(") && input.contains(")"))
					input = input.substring(input.indexOf("(")+1, input.lastIndexOf(")"));
				//if after stripping the string of all pairs of parentheses, there are still any present
				//then there is a mismatched number of parentheses.
				if(input.contains("(") || input.contains(")"))
				{
					System.out.println("Error in expression. Mismatched parentheses.");
					return false;
				}
				//if none are present after stripping pairs of parentheses, then there is an even match
				else
					return true;
			}
			//if both aren't present, but one was, there's a mismatch
			else
				System.out.println("Error in expression. Mismatched parentheses.");
				return false;
		}
		else
			return true;
	}
	
	/*This calls the a_l_u (arithmetic logic unit) method within the program
	 *in order to evaluate the postfix and provide the user with the answer to
	 *their expression. This method calls for the print of the postfix, conversion
	 *to postfix, and prints out the results of the calculations. It converts the
	 *postfix to a String array, and adds each digit index to the Stack<Integer> data.
	 *Any operators call a_l_u to evaluate the top and second register of the stack. 
	 *Postconditions: Any String evaluated will return a value.
	 *Preconditions: A String must be passed via user input.
	 *Invariants: None, at this point all invariants to check for correctness must be passed.*/
	static void processString(String input)
	{
		String[] userPF = postfixPrintout(infixToPostfix(input));
		translation = "";
		System.out.println();
		for(int i = 0; i < userPF.length; i++)
		{
			if(Character.isDigit(userPF[i].charAt(userPF[i].length()-1)))
				data.push(Integer.parseInt(userPF[i]));
			else
				a_l_u(userPF[i]);
		}
		System.out.println("Evaluated Answer: " + data.pop() + "\n ");
	}
	/*This method will split the user's translated input into a String array and then
	 * print it out, one index at a time. The String array is created by splitting the 
	 * specified string at any " "(space).
	 *Postconditions: The user's postfix will be printed, a String[] will be returned.
	 *Preconditions: A postfix notation input must be passed through the method.
	 *Invariants:*/
	static String[] postfixPrintout(String input)
	{
		String[] bl = input.split(" ");
		String converted = "";
		System.out.print("Your postfix notation: ");
		for(int i = 0; i < bl.length; i++)
		{
			if(bl[i].equals(""))
				continue;
			converted += (bl[i] + " ");
			System.out.print(bl[i] + " ");
		}
		return converted.split(" ");
	}
	/*The method a_l_u(String) is the arithmetic logic unit of the class. It will be called
	 *when an operator is encountered in the postfix, or a unary minus. If a unary minus is 
	 *encountered, it will pop the top of the stack and push this value multiplied by negative
	 *one back onto the stack. If any other operator is encountered, it will evaluate this 
	 *arithmetic function of the left(second register of stack) and the right(top of stack)
	 *digits, and then push the evaluated answer back onto the stack.
	 *Postconditions: An evaluated answer will be pushed back onto the stack.
	 *Preconditions: A string operator must be passed.
	 *Invariants: None.*/
	private static void a_l_u(String theOp)
	{
		if(theOp.equals("_"))
			data.push(data.pop() * -1);
		else
		{
			int right = data.pop();
			int left = data.pop();
			switch(theOp){
			case "+":
				data.push(left + right);
				break;
			case "-":
				data.push(left - right);
				break;
			case "%":	
				data.push(left % right);
				break;
			case "/":
				data.push(left / right);
				break;
			case "*": 
				data.push(left * right);
				break;
			}
		}	
	}
	
	//class variable that can be edited anywhere.
	public static Stack<Character> postfix = new Stack<Character>();
	//class variable that can be edited anywhere.
	public static String translation = "";
	
	/* The method infixToPostfix is built to evaluate a string @infix and convert it to postfix.
	 * The first if statement is built to evaluate for unary "+". It will skip over this char in the
	 * translation if it is deemed a unary plus. The second if statement is built to evaluate the unary
	 * minus. If deemed a unary minus, it will skip the character, add the next digit or series of digits,
	 * and add an underscore, after the aforementioned digit. The switch(char) is the meat of the algorithm
	 * used to evaluate the translation from infix to postfix. Any time a digit is encountered, it will add
	 * it to the translation, if any operator('(',')','/','+','%','-','*') is encountered then it will evaluate
	 * the operator for precedence, and based on the specified precedence, it will be added to the postfix 
	 * notation. A parentheses will never be added to the postfix notation; it will only be used to evaluate 
	 * precedence of operators further, by evaluating the set of operators and digits within the parentheses.
	 * Postconditions: Will return an postfix translation of a correct infix expression
	 * Preconditions: String can only contain digits and binary/unary operators
	 * Invariants: @lastCharWasOp evaluates and is used to evaluate unary operators
	 * 	   		   @Character.isDigit(char) is used to check for digit or non digit
	 *  		   @operators handles the translation of operators and precedence
	 *  		   @parentheses handles the difference in precedence caused by parentheses*/	
	static String infixToPostfix(String infix)
	{
		boolean lastCharWasOp = false;
		infix = infix.replaceAll(" ", "");
		infix += " ";
		for(int i = 0; i < infix.length(); i++)
		{	
			if(infix.charAt(i) == '+' && (i == 0 || lastCharWasOp))
				continue;
			if(infix.charAt(i) == '-' && ((i == 0 && Character.isDigit(infix.charAt(i+1))) || lastCharWasOp))
			{
				translation += infix.charAt(++i);
				i++;
				while(Character.isDigit(infix.charAt(i)))	
					translation += infix.charAt(i++);
				translation += " _ ";			
			}
			char ats = infix.charAt(i);
			switch(ats)
			{
				case '+':case'-': 
					lastCharWasOp = true;
					operators(ats, 1);
					break;
				case '*':case'/':case'%':
					lastCharWasOp = true;
					operators(ats, 2);
					break;
				case '(':
					lastCharWasOp = true;
					postfix.push(ats);
					break;
				case ')':
					lastCharWasOp = false;
					parentheses(ats);
					break;
				default:
					lastCharWasOp = false;
					if(i < infix.length() - 1 && Character.isDigit(infix.charAt(i+1)))
						translation += ats;
					else
						translation += ats + " ";
					break;
			}
		}
		while(!postfix.empty())
			translation += postfix.pop() + " ";
		return translation;	
	}
	
	/*Using a while loop to evaluate whether the Stack is empty, it will evaluate
	* as long as the Stack has operators, or is flagged by a condition, then it will
	* push @theOp onto the stack after the loop breaks. The loop will break if there 
	* is a forward parentheses, otherwise it will evaluate the precedence of the 5
	* allowed operators (%,*,/,+,-).	*/
	//Postconditions: Will add to the translation the operators in an order based
	//				  on operator precedence.
	//Preconditions: Must be given a char and an int
	//Invariants: None, this acts as an invariant (non-boolean) to evaluate precedence
	static void operators(char theOp, int precedence)
	{
		while(!postfix.empty())
		{
			char top = postfix.pop();
			if(top == '('){
				postfix.push(top);
				break;
			}
			else
			{
				int tempPrec;
				if(top == '+' || top == '-') 
					tempPrec = 1;
				else
					tempPrec = 2;
				if(tempPrec < precedence)
				{
					postfix.push(top);
					break;
				}
				else
					translation += top + " ";
			}
		}
		postfix.push(theOp);
	}
	
	//Using a while loop to evaluate, *this also accounts for if the expression begins with a
	//parentheses*, the string, it will add the operators between parentheses based on the 
	//conditions of whether the evaluated char is a parentheses or not.
	//Postconditions: This will add the operators, which have already been evaluated for 
	//				  precedence, to the postfix translation until another ( or ) is encountered.
	//Preconditions: In conversion from infix to postfix, there must be a "(" present
	//Invariants: None
	static void parentheses(char theOp)
	{
		while(!postfix.empty())
		{
			char parenEval = postfix.pop();
			if(parenEval == '(')
				break;
			else if(parenEval == ')')
				break;
			else
				translation += parenEval + " ";
		}
	}
}	

//Team Deuce -- Kathy Lau and Joel Ye
//APCS2 pd10
//HW24 -- Schemin
//2016 - 04 - 01

/*****************************************************
 * class Scheme
 * Simulates a rudimentary Scheme interpreter
 *
 * ALGORITHM for EVALUATING A SCHEME EXPRESSION:
      1. Steal underpants.
      2. ...
      5. Profit!
 *
 * STACK OF CHOICE: LLStack by Team Deuce
 * b/c it seems the most efficient and nodes are great.
 ******************************************************/

public class SchemeSkele {

    /******************************************************
     * precond:  Assumes expr is a valid Scheme (prefix) expression,
     *           with whitespace separating all operators, parens, and
     *           integer operands.
     * postcond: Returns the simplified value of the expression, as a String
     * eg,
     *           evaluate( "( + 4 3 )" ) -> 7
     *	         evaluate( "( + 4 ( * 2 5 ) 3 )" ) -> 17
     ******************************************************/
    public static String evaluate( String expr )
    {
		Stack<String> stack = new LLStack<>();
		String temp = "";

    for (int i = 0; i < expr.length(); i++){

      //pushes openers, ops, and nums, until a closer is reached,

      if (expr.substring(i,i+1).equals(" ")){
        stack.push(temp); //push number
        if (stack.peek().equals("")) stack.pop(); //get rid of prev ''
    		temp = ""; //reset
    	    }

      //when a closer is reached,evaluate nested expression using unload

      else if (expr.substring(i,i+1).equals(")")){
  			Stack<String> tempStack = new LLStack<>();
  			while (isNumber(stack.peek()))
  				tempStack.push(stack.pop());
  			String op = stack.pop(); //Now we have an op
  			stack.pop(); //get rid of the extra open paren
  			String res = "";
  			if (op.equals("+")) res = unload(1, tempStack);
  			else if (op.equals("-")) res = unload(2, tempStack);
  			else if (op.equals("*")) res = unload(3, tempStack);
  			stack.push(res);
  		    }

      // add number val to temp to be pushed
      else
			   temp += expr.substring(i,i+1);
       }
  return stack.pop();
    }//end evaluate()

    /******************************************************
     * precond:  Assumes top of input stack is a number.
     * postcond: Performs op on nums until closing paren is seen thru peek().
     *           Returns the result of operating on sequence of operands.
     *           Ops: + is 1, - is 2, * is 3
     ******************************************************/
    public static String unload( int op, Stack<String> numbers )
    {
      int ret = Integer.parseInt(numbers.pop());
      while (isNumber(numbers.peek()))
		    {
          if (op == 1) ret += Integer.parseInt(numbers.pop());
		      else if (op == 2) ret -= Integer.parseInt(numbers.pop());
		      else ret *= Integer.parseInt(numbers.pop());
        }
	    //should stop once it's closing parens.
	     return Integer.toString(ret);
    }//end unload()



    //optional check-to-see-if-its-a-number helper fxn:
    public static boolean isNumber( String s ) {
        try {
	    Integer.parseInt(s);
	    return true;
	}
        catch( NumberFormatException e ) {
	    return false;
	}
    }



    //main method for testing
    public static void main( String[] args ) {

	//v~~~~~~~~~~~~~~MAKE MORE~~~~~~~~~~~~~~v
	String zoo1 = "( + 4 3 )";
	System.out.println(zoo1);
	System.out.println("zoo1 eval'd: " + evaluate(zoo1) );
	//...7

	String zoo2 = "( + 4 ( * 2 5 ) 3 )";
	System.out.println(zoo2);
	System.out.println("zoo2 eval'd: " + evaluate(zoo2) );
	//...17

	String zoo3 = "( + 4 ( * 2 5 ) 6 3 ( - 56 50 ) )";
	System.out.println(zoo3);
	System.out.println("zoo3 eval'd: " + evaluate(zoo3) );
	//...29

	String zoo4 = "( - 1 2 3 )";
	System.out.println(zoo4);
	System.out.println("zoo4 eval'd: " + evaluate(zoo4) );
	//...-4
    //      ^~~~~~~~~~~~~~~~AWESOME~~~~~~~~~~~~~~~^*/
    }//main

}//end class Scheme

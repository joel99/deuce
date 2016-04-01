

public class Scheme {


    public static String evaluate(String s){
	Stack stack = new LLStack<String>();
	String temp = "";
	for (int i = 0; i < s.length(); i++){
	    if (s.substring(i,i+1).equals(" ")){
		stack.push(temp);
		temp = "";
	    }
	    else 
		String temp += s.substring(i,i+1);
	}
	stack.push(temp);
	
    }

    public int compute(String s){
	
	
    }


}

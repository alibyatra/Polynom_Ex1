package Ex1;

public class ComplexFunction {
	
	private function left;
	private function right;
	private Operation OP;
	
	public ComplexFunction () {;}
	public ComplexFunction (String op, function left , function right) {

		if ( left != null ) {
			this.left = left;
		}
		if ( right != null ) {
			this.right = right;
		}
		switch(op.toUpperCase())
		{
		case "PLUS"  :OP = Operation.Plus; 	break;
		case "TIMES" :OP = Operation.Times; break;
		case "DIVID" :OP = Operation.Divid; break;
		case "MAX"   :OP = Operation.Max; 	break;
		case "MIN"   :OP = Operation.Min; 	break;
		case "COMP"  :OP = Operation.Comp; 	break;
		case "NONE"  :OP = Operation.None; 	break;
		default      :OP = Operation.Error;	break;
		}
	}

	public ComplexFunction (function left) {
		this.left = left;
		this.OP = Operation.None;
	}
	
	public double f(double x) {

		switch(OP.toString()) {

		case "Plus" : return this.left.f(x) + this.right.f(x);

		case "Times": return this.left.f(x) * this.right.f(x);

    	case "Divid": 
			if(this.right.f(x)!=0) {
				return this.left.f(x) / this.right.f(x);
			} else { throw new RuntimeException("Can't divide by 0");}

		case "Max"  : return Math.max(this.left.f(x),this.right.f(x));

		case "Min"  : return Math.min(this.left.f(x),this.right.f(x));

		case "Comp" : 
			if (this.right != null ) {
				return this.left.f(this.right.f(x));
			}
			return this.left.f(x);

		case "None" :
			return this.left.f(x);
		}	
		return 0;
	}

	static private Operation stringtoOperation(String s) 
	{
		s = s.toLowerCase();
		switch (s) 
		{

		case "plus":
			return Operation.Plus;

		case "mul":
			return Operation.Times;

		case "div":
			return Operation.Divid;

		case "max":
			return Operation.Max;

		case "min":
			return Operation.Min;

		case "comp":
			return Operation.Comp;

		default:
			throw new RuntimeException("Operation '"+s+"' unknown");
		}
	}
	
	public void plus(function R) 
	{
		if ( this.right != null ) 
		{	
			function L =  (function) new ComplexFunction(this.OP.toString(), this.left,this.right);
			this.left = L;
		}
		this.right = R;
		this.OP = Operation.Plus;
	}

	public void mul(function R) 
	{
		if ( this.right != null ) 
		{
			function L = (function) new ComplexFunction(this.OP.toString(), this.left,this.right);
			this.left = L;
		}
		this.right = R;
		this.OP = Operation.Times;
	}

	public void div(function R) 
	{
		if ( this.right != null ) 
		{
			function L = (function) new ComplexFunction(this.OP.toString(), this.left,this.right);
			this.left = L;
		}
		this.right = R;
		this.OP = Operation.Divid;
	}

	public void max(function R) 
	{
		if ( this.right != null ) 
		{
			function L = (function) new ComplexFunction(this.OP.toString(), this.left,this.right);
			this.left = L;
		}
		this.right = R;
		this.OP = Operation.Max;
	}

	public void min(function R) 
	{
		if ( this.right != null ) 
		{
			function L = (function) new ComplexFunction(this.OP.toString(), this.left,this.right);
			this.left = L;
		}
		this.right = R;
		this.OP = Operation.Min;
	}

	public void comp(function R) 
	{
		if ( this.right != null ) 
		{
			function L = (function) new ComplexFunction(this.OP.toString(), this.left,this.right);
			this.left = L;
		}
		this.right = R;
		this.OP = Operation.Comp;
	}
	
	public function copy() 
	{
		function left = this.left.copy();
		function right = this.right == null ? null : this.right.copy();
		return  (function) new ComplexFunction(this.OP.toString(), left, right);
	}
	
	public String toString() 
	{
		String ans="";
		String op ="";
		if(this.OP!=Operation.None) 
		{
			if (this.OP == Operation.Plus) 
			{
				ans+="plus";
			}
			if (this.OP == Operation.Times) 
			{
				ans+="mul";
			}
			if (this.OP == Operation.Divid) 
			{
				ans+="div";
			}
			if (this.OP == Operation.Max) 
			{
				ans+="max";
			}
			if (this.OP == Operation.Min) 
			{
				ans+="min";
			}
			if (this.OP == Operation.Comp) 
			{
				ans+="comp";
			}
			ans+="(";
		}
		if(this.left!=null) 
		{
			ans+=this.left;	
		}
		if(this.right!=null) 
		{
			ans+=",";
			ans+=this.right;
			ans+=")";
		}
		return ans;
	}
	
	public String clearSpaces (String s) 
	{
		String t="";
		for (int i=0; i<s.length(); i++) 
		{
			if (s.charAt(i)==' ') {continue;}
			t=""+t+s.charAt(i);
		}
		return t;
	}
	
	public function initFromString (String s) 
	{
		s=s.replaceAll(" ", "");
		if (s.charAt(s.length()-1) == ')')
		{
			Operation op;
			try 
			{
				op = stringtoOperation(s.substring(0, s.indexOf('(')));				
			} 
			catch (IndexOutOfBoundsException e) 
			{
				throw new RuntimeException("Eror, it's not contain '('");
			}
			int count = 0;
			int index = -1; 
			for (int i = s.indexOf('(')+1; i < s.length()-1 && index == -1; i++) 
			{
				if(s.charAt(i) == '(')
					count++;
				else if(s.charAt(i) == ')')
					count--;
				else if(s.charAt(i) == ',' && count == 0)
					index = i;
			}
			if(index == -1)
				throw new RuntimeException("Eror, can't find ',' in the correct place");
			
			function left = initFromString(s.substring(s.indexOf('('+1), index));
			function right = initFromString(s.substring(index+1, s.length()-1));
				
			return (function) new ComplexFunction(OP.toString(), left, right);
		} 
		else 
			return new Polynom(s);
	}
	
		private int splitPoint (String s , int i) 
	{
		int Split=0;
		int point=0;
		int opener=1;
		
		while(i != s.length()) 
		{
			if(s.charAt(i)=='(') 
			{
				opener++;
			}
			if(s.charAt(i)==',') 
			{
				point++;
			}
			if(point==opener && s.charAt(i) == ',') 
			{
				Split=i;
				return Split;
			}
			i++;
		}		
		return Split;
	}
		
	public function left() 
	{
		if (this.left==null) 
		{ 
			System.out.println("Is null, Should not be!");
		}
		return this.left;
	}

		public function right() 
		{
		return this.right;
		}

	public Operation getOp() 
	{
		return this.OP;
	}


}

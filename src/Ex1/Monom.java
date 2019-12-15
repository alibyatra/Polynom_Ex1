
package Ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom() {}
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	public Monom(String s) 
	{
		String coef=s.substring(0, s.indexOf('x'));
		String pow=s.substring(s.indexOf('^'));
		this._coefficient=Double.valueOf(coef);
		this._power=Integer.parseInt(pow);
	}
		public void add(Monom m) 
		{
			if (m.get_power()==this.get_power())
				this._coefficient=m.get_coefficient() + this.get_coefficient();
			else 
				throw new RuntimeException("EROR the both power are different");
		}
	

	public void multipy(Monom d) 
	{
		this._coefficient=this.get_coefficient()*d.get_coefficient();
		this._power=this.get_power()+d.get_power();
	}

	public String toString() {
		String ans = "";
		return ans;
	}

	// you may (always) add other methods.

	//****************** Private Methods and Data *****************


	private void set_coefficient(double a){
		this._coefficient = a;
	}
	public void set_coeff(double a)
	{
		this.set_coefficient(a);
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	public void set_pow(int p)
	{
		this.set_power(p);
		}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
	
	
	///////////////////////////////////////////////////////////////
	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		function monom = new Monom (s);
		return monom;
	}
	@Override
	public function copy() {
		// TODO Auto-generated method stub
		function MonomCopy=new Monom (this.toString());
		return MonomCopy;
	}


}

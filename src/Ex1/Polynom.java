package Ex1;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import sun.net.www.content.text.plain;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	LinkedList<Monom> monom=new LinkedList<Monom>();
	ArrayList<Monom> monom2 = new ArrayList<Monom>();
	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		;
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		List<Character> c= new ArrayList<Character>();
		for (int i = 0; i <s.length() ; i++)
		{
				if((s.charAt(i)=='+')||s.charAt(i)=='-')
					c.add(s.charAt(i));	
				}

			StringTokenizer str = new StringTokenizer(s,"+-");
			Iterator<Character> it= c.iterator();
			String is=""; 
			char b;
			if(str.countTokens()==c.size()) 
			{
				b = it.next();
				is=b+str.nextToken();
				Monom m= new Monom(is);
				this.monom.add(m);
			}
			else {
				is=str.nextToken();
				Monom m= new Monom(is);
				this.monom.add(m);
			}
			while(str.hasMoreTokens()){
				b = it.next();
				is=b+str.nextToken();
				Monom m= new Monom(is);
				this.monom.add(m);
			}
			monom.sort(Monom._Comp);
	}
	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		double y=0;
		for (int i = 0; i < monom.size(); i++)
			y+=monom.get(i).f(x);
		return y;

	}

	@Override
	public void add(Polynom_able p1) {
		// TODO Auto-generated method stub
		Monom m=new Monom();
		while(p1.iteretor().hasNext()) {
			m=p1.iteretor().next();
			for (int i = 0; i < monom.size(); i++) 
				if(m.get_power()==monom.get(i).get_power())
					monom.get(i).add(m);	
		}		
	}

	@Override
	public void add(Monom m1) {
		// TODO Auto-generated method stub
		for (int i = 0; i < monom.size(); i++) 
			if(m1.get_power()==monom.get(i).get_power())
				monom.get(i).add(m1);	
	}

	@Override
	public void substract(Polynom_able p1) {
		// TODO Auto-generated method stub
		Monom m=new Monom();
		while(p1.iteretor().hasNext()) {
			m=p1.iteretor().next();
			for (int i = 0; i < monom.size(); i++) 
				if(m.get_power()==monom.get(i).get_power())
					monom.get(i).set_coeff(monom.get(i).get_coefficient()-m.get_coefficient());
		}
	}

	@Override
	public void multiply(Polynom_able p1) {
		// TODO Auto-generated method stub
		Monom m=new Monom();
		while(p1.iteretor().hasNext()) {
			m=p1.iteretor().next();
			for (int i = 0; i < monom.size(); i++) 
				monom.get(i).multipy(m);	
		}
	}
	@Override
	public boolean equals(Object p1) {
		// TODO Auto-generated method stub-able
		if (p1==null || !(p1  instanceof Polynom_able )) {return false;}
		int mp=0;
		double np=0.0000000;
		boolean flag=false;
		Polynom_able P= (Polynom_able) p1;
		Iterator<Monom> itr=P.iteretor();
		Monom m=new Monom();
		while(itr.hasNext()) {
			m=itr.next();
			if(m.isZero() && itr.hasNext()) {
				m=itr.next();
			}
			
			mp=m.get_power();
			np=m.get_coefficient();
			for(int i=0;i<monom.size();i++) {
				if(mp==monom.get(i).get_power()) {
					if( (np - (Monom.EPSILON)) <=monom.get(i).get_coefficient() && monom.get(i).get_coefficient() <= ( np + (Monom.EPSILON)) ) { //svevat epsilon
						flag=true;
					}
				}
			}
					if(!flag) {
					return false;	
				}						
		}
		return true;
	}

	@Override
	public boolean isZero() {
		// TODO Auto-generated method stub
		for (int i = 0; i < monom.size(); i++) 
			if(!monom.get(i).isZero())
				return false;
		return true;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		double fun;
		if((this.f(x0)*this.f(x1)>=0)) 
			throw new RuntimeException("EROR the multiply should be smaller than 0");
		else  {
			while(x0<=x1) 
			{
				fun=this.f(x0);
				if(Math.abs(fun)<eps) 
					return x0;
				x0=x0+eps;
			}
		}
		return 0;
	}

	@Override
	public Polynom_able copy() {
		// TODO Auto-generated method stub
		Polynom_able p1= new Polynom();
		Monom m=new Monom();
		for (int i = 0; i < monom.size(); i++)
		{
			m= new Monom(monom.get(i).get_coefficient(), monom.get(i).get_power());
			p1.add(m);
		}
		return p1;		
	}

	@Override
	public Polynom_able derivative() {
		// TODO Auto-generated method stub
		Polynom_able P1=new Polynom();
		Monom m = new Monom();
		for (int i = 0; i < monom.size(); i++)
		{
			if (monom.get(i).get_power()==0)
				P1.add(Monom.ZERO);
			else
			{
				m = new Monom(monom.get(i).get_coefficient()*monom.get(i).get_power(), monom.get(i).get_power()-1);
				P1.add(m);
			}
		}
		return P1;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		double zone=0.0;
		if(x0>x1)
			throw new RuntimeException("ERR wrong intput ");
		else {
			while(x0<x1) 
			{
				if(this.f(x0)<0) 
					x0=x0+eps;
				else 
				{
					zone+=this.f(x0)*eps;
					x0=x0+eps;
				}
			}
		}
		return zone;
	}

	@Override
	public Iterator<Monom> iteretor() {
		// TODO Auto-generated method stub

		return monom.iterator();
	}
	@Override
	public void multiply(Monom m1) {
		// TODO Auto-generated method stub
		for (int i = 0; i < monom.size(); i++) 
			if(m1.get_power()==monom.get(i).get_power())
				monom.get(i).multipy(m1);	
	}
	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		function polynom= new Polynom(s);
		return polynom;
	}

}

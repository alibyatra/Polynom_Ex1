package Ex1Testing;

import static org.junit.Assert.*;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.*;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;

public class PolynomTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	

	@Test
	public void testPolynomString() {
		System.out.println("*******testPolynomString******");
		try {
		Polynom p=new Polynom("2x^3+2x^6+3x-4");
		Polynom p1=new Polynom("-x^2+3+6x-9");
		System.out.println(p);
		System.out.println(p1);
		//fail("Not yet implemented"); // TODO
		}
		catch (Exception e) {}
	}

	@Test
	public void testF() {
		/**
		 * the output is :
		 * *******testF******
		 *	F(15.5)= 25954.125
		 */
		System.out.println("*******testF******");
		try {
			Polynom p=new Polynom("7x^3+2x^0-8x+9+0");
			System.out.println("F(15.5)= "+p.f(15.5));			
		}catch (Exception e) {}
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAddPolynom_able() {
		//fail("Not yet implemented"); // TODO
		/**
		 * the output is:
		 * ********testAddPolynom_able*********
		*	p1= +8.0x^4+17.0x^1+2.0x^0+0.0x^0+4.0x^0
		*	polynom_able =+2.0x^4-12.0x^1+6.0x^0+0.0x^0
		*		p1+polynom_able= +10.0x^4+5.0x^1+8.0x^0+6.0x^0+10.0x^0	
		 *
		 */
		System.out.println("********testAddPolynom_able*********");
		try {
			Polynom p1=new Polynom("8x^4+2x^0+0+4+17x");
			System.out.println("p1= "+p1);
			Polynom_able pa=new Polynom("2x^4+6-12x+0");
			System.out.println("polynom_able ="+pa);
			p1.add(pa);
			System.out.println("p1+polynom_able= "+p1);
		}catch (Exception e) {}
	}

	@Test
	public void testAddMonom() {
		//fail("Not yet implemented"); // TODO
		/**
		 * the output is:
		 * *****testAddMonom******
		 * p1+(10x^3)=+10.0x^3+6.0x^2+5.0x^1-9.0x^0
			p1 +(2x^5)=+2.0x^5+10.0x^3+6.0x^2+5.0x^1-9.0x^0
			p1 +(2^0)=+2.0x^5+10.0x^3+6.0x^2+5.0x^1-7.0x^0
		 * 
		 */
		System.out.println("*****testAddMonom******");
		try {
			Polynom p1=new Polynom("5x+6x^2-9");
			Monom m=new Monom(10, 3);
			Monom m1=new Monom(2,5);
			Monom m2=new Monom(2, 0);
			p1.add(m);
			System.out.println("p1+(10x^3)="+p1);
			p1.add(m1);
			System.out.println("p1 +(2x^5)="+p1);
			p1.add(m2);
			System.out.println("p1 +(2^0)="+p1);
		}catch (Exception e) {}
	}

	@Test
	public void testSubstract() {
		//fail("Not yet implemented"); // TODO
		/**
		 * the output is:
		 */
		System.out.println("*****testSubstract******");
		try {
			Polynom p1=new Polynom("5x+6x^2-9");
			Polynom_able p2=new Polynom("3x^3");
			p1.substract(p2);
			System.out.println("p1-(3x^3)="+p1);
			p2=new Polynom("-3x^3+5x+6x^2-9");

			p1.substract(p2);
			System.out.println("p1 -(-3x^3+5x+6x^2-9)="+p1);

		}catch (Exception e) {}
	}

	@Test
	public void testMultiplyPolynom_able() {

		try {
			Polynom p1=new Polynom("0");
			Polynom p2=new Polynom("2x^4+2");
			Polynom p3=new Polynom("5x-9-x^2");
			p2.multiply(p3);
			p3.multiply(p1);
			
		}
		catch(Exception e) {}
	}

	

	@Test
	public void testIsZero()
	{/**the output is:
		******testIsZero*****
		*true
		*false
		*/
		System.out.println("*****testIsZero***** \n");
	try {
		Polynom p1=new Polynom("0");
		System.out.println(p1.isZero());
		Polynom p2=new Polynom("2x^0");
		System.out.println(p2.isZero());
		//fail("Not yet implemented");
		} // TODO
		catch (Exception e) {}
	}

	@Test
	public void testRoot() {
		try {
		Polynom p = new Polynom("-2+2x");
		double ans = p.root(0, 2, 0.0000001);
		double expected = 1;
		}

		catch(Exception e) {}
	}

	@Test
	public void testCopy() {
		/**
		 * the output is:
		 * ******testCopy*****
		*	true
		 */
		System.out.println("******testCopy*****");
		try {
		Polynom p1=new Polynom("3x^2-10+6x");
		Polynom p2=(Polynom) p1.copy();
		System.out.println(p1.toString().equals(p2.toString()));
        //fail("Not yet implemented");
		} // TODO
		catch (Exception e) {}
	}

	@Test
	public void testDerivative() {
	/**the output is 
	 * ****testDerivative***
		the derivative of this polynom +2.0x^3+10.0x^1-2.0x^0
 		is :+6.0x^2+10.0x^0
	 */
		try {
			System.out.println("****testDerivative*** \n");
		Polynom p1=new Polynom("2x^3+10x-2");
		System.out.println("the derivative of this polynom "+p1+"\n is :"+p1.derivative());
		//fail("Not yet implemented");
		} // TODO
		catch (Exception e) {}
	}
	@Test
	public void testToString() {
		// TODO
		/**
		 * the output is:
		 * 
		 */
	System.out.println("*********testToString*********");
	try {
	String s2="-9x+8+2.1x^3+0.0";
	System.out.println(s2);
	//fail("Not yet implemented");
	}
	catch (Exception e) {}
	}
	@Test
	public void testArea() {
		//fail("Not yet implemented"); // TODO
		/**
		 * the output is:
		 * *****testArea*****
		*	23.027224862804726
		 */
		System.out.println("*****testArea*****");
		try {
		Polynom p=new Polynom("2x^3+12x-5");
		double x1=0,x2=2,eps=0.00001;
		System.out.println(p.area(x1, x2, eps));
		}catch (Exception e) {}
	}

	@Test
	public void testIteretor() {
		//fail("Not yet implemented"); // TODO
		System.out.println("****testIteretor*****");

	}

	@Test
	public void testMultiplyMonom() {
		// TODO
		try {
			Polynom p1=new Polynom("25x+6-7");
			Monom m=new Monom("2x^2");
			Monom m1=new Monom("0");
			p1.multiply(m);
			p1.multiply(m1);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
package myMath;


import static org.junit.Assert.*;
import org.junit.*;


/**
 * A testing class using org.JUnit. <br>
 * Testing those classes: <br>
 * [1] Monom class <br>
 * -> [1.1] constructor and add <br>
 * -> [1.2] multiply<br>
 * -> [1.3] derivative <br>
 * -> [1.4] copy <br>
 * -> [1.5] function f(x) <br>
 * -> [1.6] string constructor <br>
 * -> [1.7] equals<br>
 * [2] Monom_Comperator class <br>
 * -> [2.1] compare(m1, m2) <br>
 * [3] Polynom class <br>
 * -> [3.1] add <br>s
 * -> [3.2] add polynom <br>
 * -> [3.3] substract <br>
 * -> [3.4] isZero() <br>
 * -> [3.5] equals <br>
 * -> [3.6] multiply <br>
 * -> [3.7] derivative <br>
 * -> [3.8] copy <br>
 * -> [3.9] function f(x) <br>
 * -> [3.10] Area <br>
 * -> [3.11] root <br>
 * -> [3.12] string constructor <br>
 * -> [3.13] toString of empty polynom <br>
 * @author neyney
 *
 */
public class Test {

	///////////////////
	////// Monom //////
	///////////////////

	/**
	 * [For: Monom] Testing  "Monom()" string constructor  with different scenarios. <br>
	 */
	@org.junit.Test
	public void testMonomConstructorString() {

		Monom m;

		// Single Monoms strings
		System.out.println("Testing: Creating polynom from string with single monom");

		m = new Monom("x");

		assertTrue("Should be x", m.get_coefficient() == 1 && m.get_power() == 1);

		m = new Monom("+x");

		assertTrue("Should be x", m.get_coefficient() == 1 && m.get_power() == 1);

		m = new Monom("x^1");

		assertTrue("Should be x", m.get_coefficient() == 1 && m.get_power() == 1);

		m = new Monom("5x^3");

		assertTrue("Should be 5x^3", m.get_coefficient() == 5 && m.get_power() == 3);

		m = new Monom("5.25x^434");

		assertTrue("Should be 5x^3", m.get_coefficient() == 5.25 && m.get_power() == 434);

		m = new Monom("-x");

		assertTrue("Should be -x", m.get_coefficient() == -1 && m.get_power() == 1);

		m = new Monom("-1*x");

		assertTrue("Should be -x", m.get_coefficient() == -1 && m.get_power() == 1);

		m = new Monom("-x^0");

		assertTrue("Should be -1", m.get_coefficient() == -1 && m.get_power() == 0);

		m = new Monom("5*x^0");

		assertTrue("Should be 5", m.get_coefficient() == 5 && m.get_power() == 0);

		m = new Monom(m.toString());

		assertTrue("Should be 5", m.get_coefficient() == 5 && m.get_power() == 0);

	}

	/**
	 * [For: Monom] Testing "Monom()" constructor and "Monom.add(Monom m1)" with different scenarios. <br>
	 * [stage 0/1]: create new monom. <br>
	 * [stage 1/1]: add monom to another monom" <br>
	 */
	@org.junit.Test
	public void testMonomAddToMonom() {

		System.out.println("Testing [stage 0/1]: create new monom");

		Monom m1 = new Monom(2,3);

		assertTrue("Should be 2x^3", m1.get_coefficient() == 2 && m1.get_power() == 3);

		System.out.println("Testing [stage 1/1]: add monom to another monom");

		Monom m2 = new Monom(4,3);

		m1.add(m2);

		assertTrue("Should be 6x^3", m1.get_coefficient() == 6 && m1.get_power() == 3);

		// SHOULD RETURN EXECEPTION
		m2 = new Monom(1,5);

		try {
			m1.add(m2);
			fail("Should return an EXCEPTION");
		} catch (Exception e) {

		}

		assertTrue("Should be 6x^3", m1.get_coefficient() == 6 && m1.get_power() == 3);

		// SHOULD IGNORE IT
		m2 = new Monom(0,5);
		Monom m3 = new Monom(0,0);
		Monom m4 = new Monom(0,1);

		m1.add(m2);
		m1.add(m3);
		m1.add(m4);

		assertTrue("Should be stay same 6x^3", m1.get_coefficient() == 6 && m1.get_power() == 3);


	}

	/**
	 * [For: Monom] Testing "Monom.multiply(Monom m1)" with different scenarios.
	 */
	@org.junit.Test
	public void testMonomMultiply() {

		System.out.println("Testing: test Monom Multiply with another monom");

		Monom m1 = new Monom(2,3);

		Monom m2 = new Monom(4,3);

		m1.multiply(m2);

		assertTrue("Should be 8x^6", m1.get_coefficient() == 8 && m1.get_power() == 6);


	}

	/**
	 * [For: Monom] "Monom.multiply(Monom m1)" with different scenarios. <br>
	 * [stage 0/1]: test Monom copy constructor <br>
	 * [stage 1/1]: test Monom copy is deep copy
	 */
	@org.junit.Test
	public void testMonomCopy() {

		System.out.println("Testing [stage 0/1]: test Monom copy constructor");

		Monom m1 = new Monom(2,3);

		Monom m2 = new Monom(m1);		

		assertTrue("Should be 2x^3", m2.get_coefficient() == 2 && m2.get_power() == 3);

		System.out.println("Testing [stage 1/1]: test Monom copy is deep copy");

		m2.set_power(2);

		assertTrue("Should be 2x^3", m1.get_coefficient() == 2 && m1.get_power() == 3);
		assertTrue("Should be 2x^2", m2.get_coefficient() == 2 && m2.get_power() == 2);


	}



	/**
	 * [For: Monom] Testing "Monom.derivative()" with different scenarios.
	 */
	@org.junit.Test
	public void testMonomDerivative() {

		System.out.println("Testing: test Monom derivative");

		Monom m1 = new Monom(2,3);

		Monom m2 = m1.derivative();		

		assertTrue("Should be 6x^2", m2.get_coefficient() == 6 && m2.get_power() == 2);

		m1.set_power(0);

		m2 = m1.derivative();		

		assertTrue("Should be 0", m2.get_coefficient() == 0 && m2.get_power() == 0);

	}

	/**
	 * [For: Monom] Testing "Monom.f(x)" with different scenarios.
	 */
	@org.junit.Test
	public void testMonomFunction() {

		System.out.println("Testing: test Monom function F(x)");

		Monom m1 = new Monom(2,3);

		double f = m1.f(3); // positive

		assertTrue("Should be f(3)=54", f == 54);

		f = m1.f(1.2); // non-integer

		assertTrue("Should be f(1.2)=3.456", f >= 3.455 && f <= 3.456);

		f = m1.f(0); // the zero

		assertTrue("Should be f(0)=0", f == 0);

		f = m1.f(-2); // negative

		assertTrue("Should be f(-2)=-16", f == -16);



	}


	/**
	 * [For: Monom] Testing "Monom.equals(Monom m1)" " with different scenarios.
	 */
	@org.junit.Test
	public void testMonomEquals() {

		System.out.println("Testing: Monom.Equals");

		// REGULAR MONOMS
		Monom m1 = new Monom("5x^3");
		Monom m2 = new Monom("2x^3");
		Monom m3 = new Monom(5, 3);

		assertTrue("Should be equal", m1.equals(m3));

		assertFalse("Should NOT be equal", m1.equals(m2));

		assertFalse("Should NOT be equal", m2.equals(m3));

		// ONLY NUMBERS
		m1 = new Monom("5.5x^0");
		m2 = new Monom("5.5x^3");
		m3 = new Monom(5.5, 0);
		Monom m4 = new Monom(3.25, 0);

		assertTrue("Should be equal", m1.equals(m3));

		assertFalse("Should NOT be equal", m1.equals(m2));

		assertFalse("Should NOT be equal", m2.equals(m3));

		assertFalse("Should NOT be equal", m2.equals(m4));

		assertFalse("Should NOT be equal", m3.equals(m4));

		// ZERO MONOMS
		m1 = new Monom("0x^3");
		m2 = new Monom("0x^0");
		m3 = new Monom(0, 5);

		assertTrue("Should be equal", m1.equals(m3));

		assertTrue("Should be equal", m1.equals(m2));

		assertTrue("Should be equal", m2.equals(m3));

	}


	//////////////////////////////
	////// Monom Comperator //////
	//////////////////////////////

	/**
	 * [For: Monom_Comperator] Testing Monom Comperator "Monom_Comperator.compare(Monom m1, Monom m2)" with different scenarios.
	 */
	@org.junit.Test
	public void testMonomComperator() {

		System.out.println("Testing: Monom comperator");

		Monom_Comperator mc = new Monom_Comperator();

		Monom m1 = new Monom(2,3);

		Monom m2 = new Monom(1,5);

		assertTrue("Should be > 0", mc.compare(m1, m2) > 0);

		m1 = new Monom(4,5);

		m2 = new Monom(4,5);

		assertTrue("Should be == 0", mc.compare(m1, m2) == 0);
	}

	///////////////////
	///// Polynom /////
	///////////////////

	/**
	 * [For: Polynom] Testing Polynom "Polynom()" constructor and "Polynom.add(Monom m1)"  with different scenarios. <br>
	 * [stage 0/4]: creates empty Polynom <br>
	 * [stage 1/4]: add a monom <br>
	 * [stage 2/4]: add a monom when there is an already a monom in the polynom, test if sorting <br>
	 * [stage 3/4]: add a monom, test if it combines two monoms with equal power degree <br>
	 * [stage 4/4]: check deep copy
	 */
	@org.junit.Test
	public void testAddMonomToPolynom() {

		Polynom p1 = new Polynom();

		System.out.println("Testing [stage 0/4]: creates empty Polynom");
		assertTrue("Should be empty", p1.getMonoms().size() == 0 );

		System.out.println("Testing [stage 1/4]: add a monom");
		p1.add(new Monom(3,3));
		assertTrue("Should be 3x^3", p1.getMonoms().get(0).get_coefficient() == 3 && p1.getMonoms().get(0).get_power() == 3 );

		System.out.println("Testing [stage 2/4]: add a monom when there is an already a monom in the polynom, test if sorting");
		p1.add(new Monom(2,5));
		assertTrue("Should be 2x^5", p1.getMonoms().get(0).get_coefficient() == 2 && p1.getMonoms().get(0).get_power() == 5 );
		assertTrue("Should be 3x^3", p1.getMonoms().get(1).get_coefficient() == 3 && p1.getMonoms().get(1).get_power() == 3 );

		System.out.println("Testing [stage 3/4]: add a monom, test if it combines two monoms with equal power degree");
		p1.add(new Monom(3,5));
		assertTrue("Should be 5x^5", p1.getMonoms().get(0).get_coefficient() == 5 && p1.getMonoms().get(0).get_power() == 5 );
		assertTrue("Should be 3x^3", p1.getMonoms().get(1).get_coefficient() == 3 && p1.getMonoms().get(1).get_power() == 3 );

		System.out.println("Testing [stage 4/4]: check deep copy");
		Monom m1 = new Monom(1,2);
		p1.add(m1);
		p1.add(new Monom(3,2));
		assertTrue("Should be 4x^2", p1.getMonoms().get(2).get_coefficient() == 4 && p1.getMonoms().get(2).get_power() == 2 );
		assertTrue("Should be 1x^2", m1.get_coefficient() == 1 && m1.get_power() == 2); //check deep copy
	}

	/**
	 * [For: Polynom] Testing Polynom "Polynom()" string constructor with different scenarios. <br>
	 * [stage 0/1]: Creating polynom from string with multiple monoms <br>
	 * [stage 1/1]: Creating polynom from string with single monom
	 */
	@org.junit.Test
	public void testPolynomConstructorString() {

		// Multiple Monoms strings
		System.out.println("Testing [stage 0/1]: Creating polynom from string with multiple monoms");

		Polynom p = new Polynom("15*x^3 + 12x-x+x^3 +3*x-6x+2x^2 +7x-3x^19+9*x+25-12+2 + 15x^5 + 12x^3 -3x^2+11x^2-11x^11-x^2+6.7");

		Polynom p2 = new Polynom();
		p2.add(new Monom(15,3));
		p2.add(new Monom(12,1));
		p2.add(new Monom(-1,1));
		p2.add(new Monom(1,3));
		p2.add(new Monom(3,1));
		p2.add(new Monom(-6,1));
		p2.add(new Monom(2,2));
		p2.add(new Monom(7,1));
		p2.add(new Monom(-3,19));
		p2.add(new Monom(9,1));
		p2.add(new Monom(25,0));
		p2.add(new Monom(-12,0));
		p2.add(new Monom(2,0));
		p2.add(new Monom(15,5));
		p2.add(new Monom(12,3));
		p2.add(new Monom(-3,2));
		p2.add(new Monom(11,2));
		p2.add(new Monom(-11,11));
		p2.add(new Monom(-1,2));
		p2.add(new Monom(6.7,0));
		System.out.println(p);
		System.out.println(p2);
		assertTrue("Should be equal, the polynom generated manualy and by string", p.equals(p2));

		p = new Polynom("5.25*x^434 - 2.12x");

		p2 = new Polynom();
		p2.add(new Monom(5.25, 434));
		p2.add(new Monom(-2.12, 1));

		assertTrue("Should be equal, the polynom generated manualy and by string", p.equals(p2));

		// Test toString() to constructor
		p2 = new Polynom(p.toString());

		assertTrue("Should be equal, the polynom generated by string and by string with toString()", p.equals(p2));

		// Single Monoms strings
		System.out.println("Testing [stage 1/1]: Creating polynom from string with single monom");

		p = new Polynom("x");

		assertTrue("Should be x", p.getMonoms().get(0).get_coefficient() == 1 && p.getMonoms().get(0).get_power() == 1);

		p = new Polynom("+x");

		assertTrue("Should be x", p.getMonoms().get(0).get_coefficient() == 1 && p.getMonoms().get(0).get_power() == 1);

		p = new Polynom("x^1");

		assertTrue("Should be x", p.getMonoms().get(0).get_coefficient() == 1 && p.getMonoms().get(0).get_power() == 1);

		p = new Polynom("5*x");

		assertTrue("Should be 5x", p.getMonoms().get(0).get_coefficient() == 5 && p.getMonoms().get(0).get_power() == 1);

		p = new Polynom("5*x^3");

		assertTrue("Should be 5x^3", p.getMonoms().get(0).get_coefficient() == 5 && p.getMonoms().get(0).get_power() == 3);

		p = new Polynom("5.25*x^434");

		assertTrue("Should be 5x^3", p.getMonoms().get(0).get_coefficient() == 5.25 && p.getMonoms().get(0).get_power() == 434);

		p = new Polynom("-x");

		assertTrue("Should be -x", p.getMonoms().get(0).get_coefficient() == -1 && p.getMonoms().get(0).get_power() == 1);

		p = new Polynom("-1*x");

		assertTrue("Should be -x", p.getMonoms().get(0).get_coefficient() == -1 && p.getMonoms().get(0).get_power() == 1);

	}

	/**
	 * [For: Polynom] Testing Polynom  "Polynom.add(Polynom p1)"  with different scenarios.
	 */
	@org.junit.Test
	public void testAddPolynomToPolynom() {

		System.out.println("Testing: add another Polynom to this polynom");
		Polynom p1 = new Polynom();
		p1.add(new Monom(3,3));
		p1.add(new Monom(5,5));

		Polynom_able p2 = new Polynom();
		p2.add(new Monom(5,5));
		p2.add(new Monom(1,1));
		p2.add(new Monom(25,24));

		p1.add(p2);

		assertTrue("Should be 25x^24", p1.getMonoms().get(0).get_coefficient() == 25 && p1.getMonoms().get(0).get_power() == 24 );
		assertTrue("Should be 10x^5", p1.getMonoms().get(1).get_coefficient() == 10 && p1.getMonoms().get(1).get_power() == 5 );
		assertTrue("Should be 3x^3", p1.getMonoms().get(2).get_coefficient() == 3 && p1.getMonoms().get(2).get_power() == 3 );
		assertTrue("Should be x", p1.getMonoms().get(3).get_coefficient() == 1 && p1.getMonoms().get(3).get_power() == 1 );

		p1.add(p1);

		assertTrue("Should be 50x^243", p1.getMonoms().get(0).get_coefficient() == 50 && p1.getMonoms().get(0).get_power() == 24 );
		assertTrue("Should be 10x^5", p1.getMonoms().get(1).get_coefficient() == 20 && p1.getMonoms().get(1).get_power() == 5 );
		assertTrue("Should be 6x^3", p1.getMonoms().get(2).get_coefficient() == 6 && p1.getMonoms().get(2).get_power() == 3 );
		assertTrue("Should be 2x", p1.getMonoms().get(3).get_coefficient() == 2 && p1.getMonoms().get(3).get_power() == 1 );
	}

	/**
	 * [For: Polynom] Testing Polynom  "Polynom.substract(Polynom p1)"  with different scenarios. 
	 */
	@org.junit.Test
	public void testSubtractPolynomToPolynom() {

		System.out.println("Testing: subtract another Polynom to this polynom");
		Polynom p1 = new Polynom();
		p1.add(new Monom(3,3));
		p1.add(new Monom(5,5));

		Polynom_able p2 = new Polynom();
		p2.add(new Monom(3,5));
		p2.add(new Monom(-1,1));
		p2.add(new Monom(25,24));

		p1.substract(p2);

		assertTrue("Should be (-25)x^24", p1.getMonoms().get(0).get_coefficient() == -25 && p1.getMonoms().get(0).get_power() == 24 );
		assertTrue("Should be 2x^5", p1.getMonoms().get(1).get_coefficient() == 2 && p1.getMonoms().get(1).get_power() == 5 );
		assertTrue("Should be 3x^3", p1.getMonoms().get(2).get_coefficient() == 3 && p1.getMonoms().get(2).get_power() == 3 );
		assertTrue("Should be x", p1.getMonoms().get(3).get_coefficient() == 1 && p1.getMonoms().get(3).get_power() == 1 );
	}

	/**
	 * [For: Polynom] Testing Polynom  "Polynom.multiply(Polynom p1)" [Single monom] with different scenarios. 
	 */
	@org.junit.Test
	public void testMultiplyMonomToPolynom() {

		System.out.println("Testing: multiply another Polynom (with single monom) to this polynom");
		Polynom p1 = new Polynom();
		p1.add(new Monom(3,3));
		p1.add(new Monom(5,5));

		Polynom_able p2 = new Polynom();
		p2.add(new Monom(2,1));

		p1.multiply(p2);

		assertTrue("Should be 10x^6", p1.getMonoms().get(0).get_coefficient() == 10 && p1.getMonoms().get(0).get_power() == 6 );
		assertTrue("Should be 6x^4", p1.getMonoms().get(1).get_coefficient() == 6 && p1.getMonoms().get(1).get_power() == 4 );
	}

	/**
	 * [For: Polynom] Testing Polynom  "Polynom.add(Polynom p1)" [Multiple monoms] with different scenarios. 
	 */
	@org.junit.Test
	public void testMultiplyPolynomToPolynom() {

		System.out.println("Testing: multiply another Polynom (with multiple monoms) to this polynom");
		Polynom p1 = new Polynom();
		p1.add(new Monom(3,3));
		p1.add(new Monom(5,5));

		Polynom_able p2 = new Polynom();
		p2.add(new Monom(2,1));
		p2.add(new Monom(-1,3));

		p1.multiply(p2);

		assertTrue("Should be (-5)x^8", p1.getMonoms().get(0).get_coefficient() == -5 && p1.getMonoms().get(0).get_power() == 8 );
		assertTrue("Should be 7x^6", p1.getMonoms().get(1).get_coefficient() == 7 && p1.getMonoms().get(1).get_power() == 6 );
		assertTrue("Should be 6x^4", p1.getMonoms().get(2).get_coefficient() == 6 && p1.getMonoms().get(2).get_power() == 4 );
	}

	/**
	 * [For: Polynom] Testing Polynom "Polynom(Polynom p1)" constructor and "Polynom.copy(Polynom p1)"  with different scenarios. <br>
	 * [stage 0/3]: Copy Polynom <br>
	 * [stage 1/3]: if the copy of polynom is deep copy <br>
	 * [stage 2/3]: copy using copy constructor <br>
	 * [stage 3/3]: if the copy using copy constructor  of polynom is deep copy
	 */
	@org.junit.Test
	public void testCopyPolynom() {

		System.out.println("Testing [stage 0/3]: Copy Polynom");
		Polynom p1 = new Polynom();
		p1.add(new Monom(3,3));
		p1.add(new Monom(5,5));

		Polynom p2 = (Polynom) p1.copy();

		assertTrue("Should be 5x^5", p2.getMonoms().get(0).get_coefficient() == 5 && p2.getMonoms().get(0).get_power() == 5 );
		assertTrue("Should be 3x^3", p2.getMonoms().get(1).get_coefficient() == 3 && p2.getMonoms().get(1).get_power() == 3 );

		System.out.println("Testing [stage 1/3]: if the copy of polynom is deep copy");

		p2.add(new Monom(1,3));

		assertTrue("Should be 3x^3", p1.getMonoms().get(1).get_coefficient() == 3 && p1.getMonoms().get(1).get_power() == 3 );
		assertTrue("Should be 4x^3", p2.getMonoms().get(1).get_coefficient() == 4 && p2.getMonoms().get(1).get_power() == 3 );

		System.out.println("Testing [stage 2/3]: copy using copy constructor ");

		p2 = new Polynom(p1);

		assertTrue("Should be 5x^5", p2.getMonoms().get(0).get_coefficient() == 5 && p2.getMonoms().get(0).get_power() == 5 );
		assertTrue("Should be 3x^3", p2.getMonoms().get(1).get_coefficient() == 3 && p2.getMonoms().get(1).get_power() == 3 );

		System.out.println("Testing [stage 3/3]: if the copy using copy constructor  of polynom is deep copy");

		p2.add(new Monom(1,3));

		assertTrue("Should be 3x^3", p1.getMonoms().get(1).get_coefficient() == 3 && p1.getMonoms().get(1).get_power() == 3 );
		assertTrue("Should be 4x^3", p2.getMonoms().get(1).get_coefficient() == 4 && p2.getMonoms().get(1).get_power() == 3 );
	}

	/**
	 * [For: Polynom] Testing Polynom "Polynom.f(x)" with different scenarios.
	 */
	@org.junit.Test
	public void testPolynomFunction() {

		System.out.println("Testing: Polynom function f(x)");
		Polynom_able p1 = new Polynom();
		p1.add(new Monom(3,3));
		p1.add(new Monom(5,5));
		p1.add(new Monom(-1,0));

		double f = p1.f(3); //positive
		assertTrue("Should be f(3)=1295", f == 1295);

		f = p1.f(1.2); // non-integer
		assertTrue("Should be f(1.2)=16.6256", f == 16.6256);

		f = p1.f(0); // the zero
		assertTrue("Should be f(0)=-1", f == -1);

		f = p1.f(-3); // negative
		assertTrue("Should be f(-3)=-1297", f == -1297);

	}

	/**
	 * [For: Polynom] Testing Polynom "Polynom.equals(Polynom p1)"  with different scenarios.
	 */
	@org.junit.Test
	public void testPolynomEquals() {

		System.out.println("Testing: Polynom equals");
		Polynom p1 = new Polynom();
		p1.add(new Monom(3,3));
		p1.add(new Monom(5,5));
		p1.add(new Monom(-1,0));

		Polynom p2 = new Polynom();
		p2.add(new Monom(-1,0));
		p2.add(new Monom(2,5));
		p2.add(new Monom(3,3));
		p2.add(new Monom(3,5));

		assertTrue("Should be equal", p1.equals(p2)); //should return true

		p2.add(new Monom(1,0));

		assertTrue("Should be not equal", !p1.equals(p2)); //should return false
	}

	/**
	 * [For: Polynom] Testing Polynom "Polynom.isZero()" with different scenarios. 
	 */
	@org.junit.Test
	public void testPolynomZero() {

		System.out.println("Testing: Polynom isZero");

		Polynom p1 = new Polynom();

		assertTrue("Should be zero = true", p1.isZero()); // should return true

		p1.add(new Monom(0,3));
		p1.add(new Monom(0,5));

		assertTrue("Should be zero = true", p1.isZero()); // should return true

		p1.add(new Monom(1,5));

		assertTrue("Should not be zero", !p1.isZero()); // should return false

		p1.substract(new Monom(1,5));

		assertTrue("Should be zero = true", p1.isZero()); // should return true

	}

	/**
	 * [For: Polynom] Testing Polynom "Polynom.derivative()" with different scenarios. 
	 */
	@org.junit.Test
	public void testPolynomDerivative() {

		System.out.println("Testing: Derivative of Polynom");

		Polynom_able p1 = new Polynom();

		p1.add(new Monom(2,3)); // 2x^3
		p1.add(new Monom(1,2)); // x^2
		p1.add(new Monom(5,1)); // 5x
		p1.add(new Monom(3,0)); // 3

		Polynom d = (Polynom) p1.derivative();

		Polynom p2 = new Polynom();

		p2.add(new Monom(6,2)); // 6x^2
		p2.add(new Monom(2,1)); // 2x
		p2.add(new Monom(5,0)); // 5
		p2.add(new Monom(0,0)); // 0

		assertTrue("Should be equal", d.equals(p2));

	}

	/**
	 * [For: Polynom] Testing Polynom "Polynom.area(x0, x1, eps)" with different scenarios. 
	 */
	@org.junit.Test
	public void testPolynomArea() {

		System.out.println("Testing: Polynom Area");
		Polynom p1 = new Polynom();
		p1.add(new Monom(1,2)); //x^2
		p1.add(new Monom(1,1)); //x
		p1.add(new Monom(-1,0)); //-1

		// Have positive f(x)
		//assertTrue("Should be area = 47.83333+-1", Math.abs(p1.area(-2, 5, 1) - 47.83333) < 1);
		//assertTrue("Should be area = 47.83333+-0.1", Math.abs(p1.area(-2, 5, 0.1) - 47.83333) < 0.1);
		//assertTrue("Should be area = 47.83333+-0.005", Math.abs(p1.area(-2, 5, 0.005) - 47.83333) < 0.005);
		//assertTrue("Should be area = 47.83333+-0.000005", Math.abs(p1.area(-2, 5, 0.000005) - 47.83333) < 0.000005);

		Polynom p2 = new Polynom();
		p2.add(new Monom(1,1)); //x

		// Have negative f(x)
		//assertTrue("Should be area = -12.5+-1", Math.abs(p2.area(-5, 0, 1) - (-12.5)) < 1);
		//assertTrue("Should be area = -12.5+-0.1", Math.abs(p2.area(-5, 0, 0.1) - (-12.5)) < 0.1);
		//assertTrue("Should be area = -12.5+-0.005", Math.abs(p2.area(-5, 0, 0.005) - (-12.5)) < 0.005);
		//assertTrue("Should be area = -12.5+-0.000005", Math.abs(p2.area(-5, 0, 0.000005) - (-12.5)) < 0.000005);
	}

	/**
	 * [For: Polynom] Testing Polynom "Polynom.root(x0, x1, eps)" with different scenarios. 
	 */
	@org.junit.Test
	public void testPolynomRoot() {

		System.out.println("Testing: Polynom Root");
		Polynom p1 = new Polynom();
		p1.add(new Monom(1,2)); //x^2
		p1.add(new Monom(1,1)); //x
		p1.add(new Monom(-1,0)); //-1

		// this polynom has 2 roots
		// first root = 0.61803

		assertTrue("Should be root = 0.61803+-1", Math.abs(p1.root(-0.1, 5, 1) - 0.6180339870043099) < 1);
		assertTrue("Should be root = 0.61803+-0.1", Math.abs(p1.root(-0.1, 5, 0.1) - 0.6180339870043099) < 0.1);
		assertTrue("Should be root = 0.61803+-0.005", Math.abs(p1.root(-0.1, 5, 0.005) - 0.6180339870043099) < 0.005);
		assertTrue("Should be root = 0.61803+-0.000005", Math.abs(p1.root(-0.1, 5, 0.000005) - 0.6180339870043099) < 0.000005);

		// second root = -1.61803
		assertTrue("Should be root = -1.61803+-1", Math.abs(p1.root(-35, -1, 1) + (1.6180339870043099)) < 1);
		assertTrue("Should be root = -1.61803+-0.000005", Math.abs(p1.root(-35, -1, 0.000005) + (1.6180339870043099)) < 0.000005);

		Polynom p2 = new Polynom();
		p2.add(new Monom(1,1)); //x

		// root is zero, 0
		assertTrue("Should be root = 0+-1", Math.abs(p2.root(-10, 7, 1) - 0) < 1);
		assertTrue("Should be root = 0+-0.1", Math.abs(p2.root(-10, 7, 0.1) - 0) < 0.1);
		assertTrue("Should be root = 0+-0.005", Math.abs(p2.root(-10, 7, 0.005) - 0) < 0.005);
		assertTrue("Should be root = 0+-0.000005", Math.abs(p2.root(-10, 7, 0.000005) - 0) < 0.000005);

		Polynom p3 = new Polynom();
		p3.add(new Monom(1,3)); //x^3
		p3.add(new Monom(-1,0)); //-1

		assertTrue("Should be root = 1+-1", Math.abs(p3.root(-10, 7, 1) - 1) < 1);
		assertTrue("Should be root = 1+-0.001", Math.abs(p3.root(-10, 7, 0.001) - 1) < 0.001);

		Polynom p4 = new Polynom("x^2-7x+12");


		assertTrue("Should be root = 1+-0.001", Math.abs(p4.root(-2, 9, 0.001) - 3) < 0.001);

		Polynom p5 = new Polynom("x^3-9x^2+26x-24");

		assertTrue("Should be root = 2+-0.001", Math.abs(p5.root(-9, 10, 0.001) - 2) < 0.001);

		Polynom p6 = new Polynom("-x^2+x+12");

		assertTrue("Should be root = 4+-0.001", Math.abs(p6.root(-4, 6, 0.001) - 4) < 0.001);
		assertTrue("Should be root = 4+-0.001", Math.abs(p6.root(0, 6, 0.001) - 4) < 0.001);
		assertTrue("Should be root = -3+-0.001", Math.abs(p6.root(-4, 0, 0.001) - (-3)) < 0.001);
	}
	
	/**
	 * [For: Polynom] Testing Polynom "Polynom.toString()" with different scenarios. 
	 */
	@org.junit.Test
	public void testPolynomToString() {
		
		System.out.println("Testing: Polynom toString while empty should return 0");

		
		Polynom p = new Polynom();
		p.add(new Monom(0,5));
		
		assertTrue(p.toString().equals("0"));
	}


}

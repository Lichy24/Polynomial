
package myMath;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{

	/**
	 * Constructs new monom with given a and b values
	 * @param a = [ coefficient ]
	 * @param b = [ power ]
	 */
	public Monom(double a, int b){
		if(b < 0) throw new RuntimeException("Monom's power cannot be negative, only non-negative integer.");
		this.set_coefficient(a);
		this.set_power(b);
	}
	/**
	 * [Copy Constructor] creates a new monom from given another monom replicate.
	 * @param ot
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	// ***************** add your code below **********************

	/**
	 * [String Constructor] creates a new monom from given String
	 * @param str
	 */
	public Monom(String str) {
		int x_ind, op_ind, po_ind, power;
		double coeff;
		
		try {
		
		str = str.replaceAll(" ", "");
		str = str.replaceAll("\\*", "");
		str = str.replaceAll("X", "x");
		
		x_ind = str.indexOf('x');
		po_ind = str.indexOf('^');
		
		if(str.charAt(0) == '+' || str.charAt(0) == '-')
			op_ind = 0;
		else
			op_ind = -1;

		int next_op_ind = Math.min(str.indexOf('+', op_ind+1), str.indexOf('-', op_ind+1));
		if( next_op_ind == -1)
			next_op_ind = str.length();

		if(x_ind > 0 && op_ind+1 != x_ind) //if there is coeff
			coeff = Double.parseDouble(str.substring(op_ind+1, x_ind));
		else if(x_ind == -1) //if there is only coeff without x , just a number
			coeff = Double.parseDouble(str.substring(op_ind+1, next_op_ind));
		else //if empty coeff
			coeff = 1;

		if(op_ind != -1 && str.charAt(op_ind) == '-') //if its negative
			coeff *= -1;

		if(po_ind == x_ind+1) //if x has a power
			power = Integer.parseInt(str.substring(po_ind+1, next_op_ind));
		else if(x_ind != -1) //if x without power (1)
			power = 1;
		else // if there is no x at all, only number
			power = 0;
		
		this._coefficient = coeff;
		this._power = power;
		
		} catch (Exception e) {
			throw e;
		}
		

		

	}
	
	public boolean equals(Monom m1) {
		
		//if same reference then strue
		if(this == m1)
			return true;
		
		//if null then false
		if(m1 == null)
			return false;
		
		// generate random equallity number to test on f(r)
		double r = Math.random()*100;

		// check equallity between two function's values
		if(f(r) != m1.f(r))
			return false;
		
		// if returned the same values in function then just test the coeff and power.
		return ((get_coefficient() == 0 && m1.get_coefficient() == 0) ||
				(get_coefficient() == m1.get_coefficient()) && (get_power() == m1.get_power()));
	}


	/**
	 * Compute a new Monom which is the derivative of this Monom
	 * @return [type: Monom] a Monom object with the values of the derivative of the Monom
	 */
	public Monom derivative() {
		if(this.get_power() > 0)
			return new Monom(this.get_coefficient() * this.get_power(), this.get_power()-1);
		else
			return new Monom(this.get_coefficient() * this.get_power(), 0);
	}

	/**
	 * adding another Monom to this Monom if they both have the same power degree
	 * @param  m [type: Monom] as the other monom.
	 */
	public void add(Monom m) {
		//if null then stop
		if(m == null)
			throw new RuntimeException("The Mononmyou are trying to add is NULL");
		
		//if has no effect then just abort
		if(m.get_coefficient() == 0)
			return; // abort - as it does nothin'
		
		if(this.get_power() == m.get_power())
			this.set_coefficient(this.get_coefficient() + m.get_coefficient());
		else
			throw new RuntimeException("The Monom you are trying to add have different POWER degree, aborting");
	}

	/**
	 * Multiplying another Monom with this Monom and generates a new Monom and returns it.
	 * @param m
	 * @return [type: Monom] m, as the new Monom generated from the multiplication.
	 */
	public void multiply(Monom m) {
		//if null then stop
		if(m == null)
			return;
		
		set_coefficient(this.get_coefficient() * m.get_coefficient());
		set_power(this.get_power() + m.get_power());
	}

	/**
	 * get this Monom's coefficient
	 * @return [type: double] Monom->coefficient
	 */
	public double get_coefficient() {
		return _coefficient;
	}

	/**
	 * get this Monom's power
	 * @return [type: double] Monom->power
	 */
	public int get_power() {
		return _power;
	}

	@Override
	public double f(double x) {
		return this.get_coefficient()*(Math.pow(x, this.get_power()));
	} 

	@Override
	public String toString() {
		return this._coefficient + "x^" + this._power;
	}

	/**
	 * set this Monom's coefficient with new value
	 * @param a [type: double] as the new coefficient value.
	 */
	public void set_coefficient(double a){
		this._coefficient = a;
	}

	/**
	 * set this Monom's power with new value
	 * @param p [type: int] as the new power value.
	 */
	public void set_power(int p) {
		this._power = p;
	}

	//****************** Private Methods and Data *****************


	private double _coefficient; 
	private int _power;

}

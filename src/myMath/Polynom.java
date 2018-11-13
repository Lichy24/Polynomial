package myMath;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import myMath.Monom;
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

	// ********** add your code below ***********

	private ArrayList<Monom> monoms = new ArrayList<Monom>();

	/**
	 * [Constructor] Create new Empty Polynom object
	 */
	public Polynom() {

	}
	/**
	 * [Constructor] Create new Polynom from given String
	 */
	/**
	 * [Constructor] Create new Polynom from given String
	 */
	public Polynom(String str) {

		//if null then stop
		if(str == null)
			return;

		//if empty then stop
		if(str.isEmpty())
			return;

		try {

			str = str.replaceAll(" ", ""); // delete spaces
			str = str.replaceAll("\\*", ""); // remove '*'
			
			
			while(!str.isEmpty()) {
				//get indexes of arithmetic operators
				int pl_ind = str.indexOf('+'); 
				int mi_ind = str.indexOf('-');

				int op_ind; // the closest arithmetic operator

				//the first operator
				if(str.charAt(0) == '+' || str.charAt(0) == '-')
					op_ind = 0;
				else
					op_ind = -1;

				// find the next operator.
				pl_ind = str.indexOf('+', op_ind+1);
				mi_ind = str.indexOf('-', op_ind+1);

				int next_op_ind; // the next operator

				if(pl_ind == -1 && mi_ind == -1)
					next_op_ind = str.length();
				else if(pl_ind == -1 || mi_ind == -1)
					next_op_ind = Math.max(pl_ind, mi_ind);
				else
					next_op_ind = Math.min(pl_ind, mi_ind);



				add(new Monom(str.substring(0, next_op_ind)));

				str = str.substring(next_op_ind);
			}
		} 
		catch (Exception e) {
			throw e;
		}

	}
	
	/*public Polynom(String s){//long code but simple used regex to do string validation and manipulation
        String[] strings; // array of string for each mono
        String curr_str,coefficient_str,power_str,monom;// variables
        double coefficient;
        int power;

        s = s.replaceAll("\\s","");//remove all type of break-space(\t,\n,white space) from polynomial
        monom = "\\d*(\\.\\d*)?((?<=\\d)((\\*?)(?<=\\*)([xX])|([xX]?))|([xX]?)(?<=[xX])((\\^\\d+)?))?";
        Pattern pattern = Pattern.compile("^[+-]?"+monom+"([+-]"+monom+")*$");//same requests but after first monomial must have +/- sign.
        Matcher matcher = pattern.matcher(s);//checks if match regex

        if (matcher.matches()){//check result
            s = s.replaceAll("\\*","");//remove multiply symbols from polynomial
            s = s.replaceAll("-","+-");// add common symbol to split polynomial.
            s = s.toLowerCase();//lowercase all X to x so it will be synchronized
            if (s.startsWith("+"))//if first character is plus it will add first index of spilt to be empty.
                s = s.replaceFirst("\\+","");

            strings = s.split("\\+");
            for (int i = 0; i < strings.length; i++) {//loop for each monomial
                curr_str = strings[i];
                if (curr_str.compareTo("") != 0 && curr_str.compareTo("-") != 0) {
                    if (curr_str.contains("x")) {//if has x then default
                        coefficient_str = curr_str.substring(0, curr_str.indexOf('x'));//take coefficient
                        coefficient_str = shorts(coefficient_str);//check for short such as (,-)
                        if (curr_str.contains("^")) {//exponent by...
                            power_str = curr_str.substring(curr_str.indexOf('^') + 1);
                        } else//if no exponent(ax) then 1
                            power_str = "1";
                    } else {
                        coefficient_str = shorts(curr_str);
                        power_str = "0";
                    }
                    coefficient = Double.parseDouble(coefficient_str);//Double parser
                    power = Integer.parseInt(power_str);//Integer parser
                    add(new Monom(coefficient, power));//add Monomial
                }
            }

        }
    }
	
	   private String shorts(String s){//fix missing values for the parse
	         if (s.contains(".")) {
	             if (s.startsWith("."))
	                 return 0 + s;
	             if (s.endsWith("."))
	                 return  s + 0;
	             return s;
	         }
	         switch (s){
	             case "": return "1";
	             case "-": return "-1";
	         }
	         return s;
	    }*/

	/**
	 * [Constructor] Create new Polynom object from given monoms
	 */
	public Polynom(Monom...ms) {
		for(Monom m : ms)
			add(m);
	}

	/**
	 * Copy Constructor which receives another polynom and makes a copy of it.
	 * @param p
	 */
	public Polynom(Polynom p) {

		//if null then stop
		if(p == null)
			return;

		//get iterator
		Iterator<Monom> iter = p.iteretor();

		while(iter.hasNext()) {
			// get monom
			Monom m = iter.next();

			// add it to the arraylist monoms
			this.monoms.add(new Monom(m));
		}

	}

	@Override
	public double f(double x) {
		double sum = 0;

		Iterator<Monom> iter = iteretor();

		while(iter.hasNext()) {
			sum += iter.next().f(x);
		}

		return sum;
	}

	/**
	 * get the reference / pointer to this polynom's ArrayList.
	 * @return this Polynom's ArrayList<Monom> container of Monoms.
	 */
	public ArrayList<Monom> getMonoms() {
		return this.monoms;
	}

	@Override
	public void add(Polynom_able p1) {

		//if null then stop
		if(p1 == null)
			return;

		// get iterator
		Iterator<Monom> iter = p1.iteretor();

		while(iter.hasNext()){
			// get the element
			Monom m = iter.next();

			//add it to the Polynom
			this.add(m);
		}

	}


	@Override
	public void add(Monom m1) {

		//if null then stop
		if(m1 == null)
			return;

		// if the monom m1 has no effect at all then just stop
		if(m1.get_coefficient() == 0)
			return; // stop

		// if already have that degree then just combine it
		int power = m1.get_power();

		Iterator<Monom> iter = iteretor();

		while(iter.hasNext()) 
		{
			Monom m = iter.next();

			if(m.get_power() == power) {
				m.add(m1);

				// check if the monom has been reset to zero
				if(m.get_coefficient() == 0)
					iter.remove();

				return; // stop;
			}

		}


		// if the list dont have the monom with the correct power then add it
		monoms.add(new Monom(m1));

		// sort it by Power degree
		// using single iteration of InsertionSort as its the fastest on already-almost-sorted arrays.
		fixData();

	}

	/**
	 * sort the single new item added to the list using insertion sort algorithm.
	 */
	private void fixData() {
		// sort it by Power degree
		// using single iteration of InsertionSort as its the fastest on already-almost-sorted arrays.
		Monom m;
		int virtualSize = monoms.size()-1; 
		Monom_Comperator mc = new Monom_Comperator();

		Monom selectedElem = monoms.get(virtualSize);
		for(int j = virtualSize-1 ; j >= 0 ; j--) 
		{
			m = monoms.get(j);
			if(mc.compare(selectedElem, m) < 0)
			{
				monoms.set((j+1), m);
			}  else {
				monoms.set(j+1, selectedElem);
				break;
			}

			if(j == 0)
			{
				monoms.set(j, selectedElem);
				break;
			}

		}
	}

	/**
	 * Subtract a monom (m1) from this polynom.
	 * @param m1
	 */
	public void substract(Monom m1) {

		//if null then stop
		if(m1 == null)
			return;

		//use "minus"/"negative value" as way of subtracting
		m1 = new Monom(
				-m1.get_coefficient(),
				m1.get_power());

		this.add(m1);

	}

	@Override
	public void substract(Polynom_able p1) {

		//if null then stop
		if(p1 == null)
			return;

		// get iterator
		Iterator<Monom> iter = p1.iteretor();

		while(iter.hasNext()){
			// get the element
			Monom m = iter.next();

			//sub it to the Polynom
			substract(m);
		}

	}

	/**
	 * Multiply this polynom with a single monom
	 * @param m1 as single Monom
	 */
	public void multiply(Monom m1) {
		Polynom p = new Polynom();
		p.add(m1);
		
		multiply(p);
	}
	
	@Override
	public void multiply(Polynom_able p1) { 

		//if null then stop
		if(p1 == null)
			return;

		// create new array List
		ArrayList<Monom> t = (ArrayList<Monom>) this.monoms.clone();; //shallow copy
		this.monoms.clear();

		// get iterator
		Iterator<Monom> iterp1 = p1.iteretor(); 
		Iterator<Monom> iter;

		// for each monom in the p1 polynom
		while(iterp1.hasNext()){
			// get the element
			Monom m1 = iterp1.next();

			// get this temp arraylist collection iterator
			iter = t.iterator();

			//multiply each monom in the current polynom
			while(iter.hasNext()) {
				Monom m = new Monom(iter.next()); //deep copy
				m.multiply(m1);
				add(m);
			}
		}
	}

	/** NOTE: Assumes both polynoms are sorted and have no duplications */
	@Override
	public boolean equals(Polynom_able p1) {  

		// check if they have the same reference
		if(this == p1)
			return true;

		// check if its null
		if(p1 == null)
			return false;

		//get iterator for this polynom
		Iterator<Monom> iter = iteretor();

		//get iterator of the other polynom
		Iterator<Monom> iterp1 = p1.iteretor();

		// check for each monom if its a and b are the same	
		while(iter.hasNext() && iterp1.hasNext()) {
			Monom m = iter.next();
			Monom m1 = iterp1.next();

			if(!m.equals(m1))
				return false;
		}

		//if there are some monoms left in one of them then they are not equal
		if(iter.hasNext() || iterp1.hasNext())
			return false;

		return true;
	}

	@Override
	public boolean isZero() {

		if(monoms.isEmpty())
			return true;

		Iterator<Monom> iter = iteretor();

		while(iter.hasNext()){
			Monom m = iter.next();

			if(m.get_coefficient() != 0) //if coeff is zero, then monom is zero
				return false;
		}

		return true; 

	}

	/**
	 * Computes log2 of "bits" via logic operations as its the fastest way, works on integers only and have error margin. <br>
	 * see: https://stackoverflow.com/questions/3305059/how-do-you-calculate-log-base-2-in-java-for-integers
	 * @param bits [the number to calculate]
	 * @return log2(bits)
	 */
	private static int binlog( int bits ) // returns 0 for bits=0
	{
		int log = 0;
		if( ( bits & 0xffff0000 ) != 0 ) { bits >>>= 16; log = 16; }
		if( bits >= 256 ) { bits >>>= 8; log += 8; }
		if( bits >= 16  ) { bits >>>= 4; log += 4; }
		if( bits >= 4   ) { bits >>>= 2; log += 2; }
		return log + ( bits >>> 1 );
	}


	@Override
	public double root(double x0, double x1, double eps) { 
		double mid = (x0+x1)/2, result = f(mid), t0 = x0, t1 = x1;
		x0 = (f(x0) < f(x1))? t0 : t1; // the negative value would be on x0
		x1 = (f(x0) < f(x1))? t1 : t0; // the positive value would be on x1
		int times = binlog((int)((x1-x0)/eps))+1; //see: https://www.youtube.com/watch?v=MlP_W-obuNg

		if(f(x0) == 0)
			return x0;
		if(f(x1) == 0)
			return x1;
		
		for(int i =0; i < times ; i++) { 
			mid = (x0+x1)/2;
			result = f(mid);
			
			if(result == 0)
				break;

			if(Math.abs(result) < eps)
				return mid;
				
			if(result < 0) //if [f(x) < 0] - is negative
				x0 = mid;
			else
				x1 = mid;
		}

		return mid;
	}

	@Override
	public Polynom_able copy() {
		return new Polynom(this);
	}

	@Override
	public Polynom_able derivative() {

		//create a new Polynom
		Polynom p = new Polynom();

		//get iterator
		Iterator<Monom> iter = iteretor();

		// add to the New polynom the derivative of each monom
		while(iter.hasNext()) {
			Monom m = iter.next();

			// check if the monom has been reset to zero
			if(m.get_coefficient() != 0)
				p.add(m.derivative());

		}

		return p;
	}


	/**
	 * calculating the area above x-axis of integral between x0 and x1, with number of parts to divide the area !! <br>
	 * above x-axis.
	 * @param x0 - the left border
	 * @param x1 - the right border
	 * @param parts - the amount of parts to divide the area
	 */
	public double areaTimes(double x0, double x1, int parts) {
		int n = parts;
		double result = 0, interval = (x1-x0)/n;

		// calculate area for each rectangle with witdh of "Interval = (x1-x0)/n"
		for(int i = 0 ; i < n ; i++) {
			result += f(x0+interval*i)*interval; // sum / sigma;
		}

		return result;
	}
	
	/**
	 * calculating the area above x-axis of integral between x0 and x1, with MAX ERROR RATE of epsilon !!
	 * @param x0 - the left border
	 * @param x1 - the right border
	 * @param eps - the MAX ERROR RATE
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		int n = Math.abs((int)(((x1-x0)/eps)*(f(x1)-f(x0)))) + 1;// n = amount of intervals, n >= [(x1-x0)*(f(x1)-f(x0))]/eps
		double result = 0, interval = (x1-x0)/n;

		// calculate area for each rectangle with witdh of "Interval = (x1-x0)/n"
		for(int i = 0 ; i < n ; i++) {
			result += f(x0+interval*i)*interval; // sum / sigma;
		}

		return result;
	}

	/**
	 * calculate the area between the x-axis and the area above the function in the negative y-axis.
	 * @param x0 the range beginning
	 * @param x1 the range ending
	 * @param eps the max APPROXIMATE error rate.
	 * @return
	 */
	public double areaNegativeX(double x0, double x1, double eps) {
		int n = Math.abs((int)(((x1-x0)/eps)*(f(x1)-f(x0)))) + 1;
		double result = 0, interval = (x1-x0)/n, f;
		

		// calculate area for each rectangle with width of 
		for(int i = 0 ; i < n ; i++) {
			f = f(x0+interval*i);
			if(f < 0)
				result += f*interval; // sum / sigma;
		}

		return -result;
	}
	
	/**
	 * Opens up a Window panel with a drawing with the Polynom's graph representation. along with critical points.
	 * @param x0
	 * @param x1
	 */
	public void draw(double x0, double x1) {
		double size = x1-x0; // range
		double dist = 36; // the precision, how many dots per x' unit
		int n = (int) (size*dist); // total points to generate
		
		double maxy = 0; // max y value
		double[] x = new double[n+1];
		double[] y = new double[n+1];
		for (int i = 0; i <= n; i++) { // plot data.
			x[i] = (double)(x0+i/dist);
			y[i] = f(x[i]);
			
			
			if(y[i] > maxy)
				maxy = y[i];
		}

		// rescale the coordinate system
		StdDraw.setCanvasSize(1200, 800); // size of window
		StdDraw.setXscale(x0, x1); // graph X range
		StdDraw.setYscale(-maxy, maxy); // graph Y range

		StdDraw.setPenRadius(0.005);

		// plot the approximation to the function
	
		
		for (int i = 0; i < n; i++) {
			
			StdDraw.line(x[i], y[i], x[i+1], y[i+1]); // connect the dots with lines.

			
			if(i>0 && i<n-1)
			{
				boolean min = ((y[i] < y[i-1]) && (y[i] < y[i+1]));
				boolean max = ((y[i] > y[i-1]) && (y[i] > y[i+1]));
				if(min || max) // if critical point than color it
				{
					StdDraw.setPenRadius(0.01);
					StdDraw.setPenColor(Color.red);
					
					StdDraw.circle(x[i], y[i], 0.02);
					if(max)
						StdDraw.text(x[i], y[i]+0.5, "("+new DecimalFormat("#########0.0#").format(x[i])+
								","+new DecimalFormat("#########0.0#").format(y[i])+")");
					else
						StdDraw.text(x[i], y[i]-0.5, "("+new DecimalFormat("#########0.0#").format(x[i])+
								","+new DecimalFormat("#########0.0#").format(y[i])+")");
					
					StdDraw.setPenColor(Color.black);
					StdDraw.setPenRadius(0.005);
				}
					
			}

		}
		
		// area GET THE NEGATIVE PARTS OF THE FUNCTION
		String area = ""+areaNegativeX(x0, x1, 0.001);
		StdDraw.text(x0+2, maxy-5, "Area under X-axis: "+area);
		
		StdDraw.setPenRadius(0.002);

		//draw axises
		StdDraw.line(0, -maxy, 0, maxy); //y axis
		StdDraw.line(-size, 0, size, 0); //x axis

		int d = (int) Math.ceil(maxy/10);
		
		for(int i =0 ; i< size ;i+=1) {
			StdDraw.text(i, 0.2, String.valueOf(i));
			StdDraw.text(-i, 0.2, String.valueOf(-i));
		}
		
		for(int i = 0 ; i< maxy; i+=d)
		{
			StdDraw.text(-0.1, i, String.valueOf(i));
			StdDraw.text(-0.1, -i, String.valueOf(-i));
		}
		
	}

	@Override
	public Iterator<Monom> iteretor() {
		return this.monoms.iterator();
	}

	@Override
	public String toString() {
		
		if(isZero())
			return "0";
		
		Monom m;

		StringBuilder sb = new StringBuilder();

		Iterator<Monom> iter = iteretor();

		if(iter.hasNext())
			sb.append(iter.next());

		while(iter.hasNext()) {

			//Get monom
			m = iter.next();
			String s = m.toString();

			//if it has a negative value then use the already exist '-' and add spaces, else then '+'
			if(m.get_coefficient() < 0)
			{sb.append(" "); s=s.replace("-", "- ");}
			else
				sb.append(" + ");

			// if it has power of 0 or 1 then just dont write them
			if(m.get_power() == 1) // if '3x^1' then just write x
				s = s.substring(0, s.indexOf('^'));
			else if(m.get_power() == 0) // if '3x^0'then just write 3
				s = s.substring(0, s.indexOf('^')-1);


			sb.append(s);
		}
		return sb.toString();
	}
}

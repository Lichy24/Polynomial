package myMath;

import java.util.Comparator;

/**
 * Monom Comperator used to compare between two monoms and determine which comes first in the order of the array.
 * @author neyney
 *
 */
public class Monom_Comperator implements Comparator<Monom> {

	// ******** add your code below *********
	
	@Override
	public int compare(Monom m1, Monom m2) {
		return m2.get_power() - m1.get_power();
	}

	

}

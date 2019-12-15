package Ex1;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	public Monom_Comperator() {;}
	public int compare(Monom o1, Monom o2) {
		int dp = o2.get_power() - o1.get_power();
		return dp;
	}
	public int Compare(Monom o1,Monom o2)
	{
		if(o1.get_power()<o2.get_power())
			return -1;
		if(o1.get_power()>o2.get_power())
			return 1;
		return 0;		
	}
	// ******** add your code below *********

}

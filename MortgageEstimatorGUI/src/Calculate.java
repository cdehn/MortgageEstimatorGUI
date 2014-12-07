public class Calculate {

	public static double eighteenPercent(double annualIncome){
		double eighteen = (annualIncome/12) * (0.18);
		return eighteen;
	}
	
	public static double thirtysixPercent(double annualIncome, double totalDebt){
		double thirtysix = (((annualIncome / 12) * (0.36)) - totalDebt);
		return thirtysix;
	}

	public static double minimum(double eighteen, double thirtysix){
		double minimum = Math.min(eighteen, thirtysix);
		return minimum;
	}
	
	public static double pv(double r, double n, double minimum, double f, boolean t) {
        double retval = 0;
        if (r == 0) {
            retval = -1*((n*minimum)+f);
        }
        else {
        	double r1 = r + 1;
	            retval =(( ( 1 - Math.pow(r1, n) ) / r ) * (t ? r1 : 1)  * minimum - f) / Math.pow(r1, n);
	    }
	        return retval;
	}   
}
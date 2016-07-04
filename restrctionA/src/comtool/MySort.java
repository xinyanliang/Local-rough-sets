package comtool;

import java.util.Comparator;

public class MySort implements Comparator<MyList>{
	
	@Override
	public int compare(MyList o1, MyList o2) {
		
		double flag=o1.v-o2.v;
		
		if(flag>0)
			return 1;
		else if(flag<0)
			return -1;
		else
			return 0;
	}
	
}

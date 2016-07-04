package comtool;

import java.util.Comparator;

/**
 * 功能：使得MyList<k,v>可以按照v的大小排序
 * 如：（2，8.4）与（4，1.2）排序后为（4，1.2），（2，8.4）
 * @author 梁新彦
 *
 */
public class MySort implements Comparator<MyList>{

	@Override
	public int compare(MyList o1, MyList o2) {
		// TODO Auto-generated method stub
		double flag=o1.v-o2.v;
		
		if(flag>0)
			return 1;
		else if(flag<0)
			return -1;
		else
			return 0;
	}	
}

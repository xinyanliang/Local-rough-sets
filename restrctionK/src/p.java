import java.util.ArrayList;
import java.util.Collections;

import comtool.MyList;
import comtool.MySort;


public class p {

	public static void main(String[] args) {
		MySort so=new MySort();
		ArrayList<MyList> a=new ArrayList<MyList>();
		MyList m=new MyList(0,0);
		a.add(m);
		m=new MyList(1,2.0);
		a.add(m);
		m=new MyList(2,2.449489742783178);
		a.add(m);
		m=new MyList(3,2.449489742783178);
		a.add(m);
		m=new MyList(4,2.23606797749979);
		a.add(m);
		Collections.sort(a, so);
		
		for(MyList b:a){
			System.out.println(b.k+" "+b.v);
		}
	}

}

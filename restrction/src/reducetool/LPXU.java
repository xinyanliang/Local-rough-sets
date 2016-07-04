package reducetool;

import java.util.ArrayList;

import comtool.EqualClassPara;
import comtool.ListTool;
import config.Config;
/**
 * @status		ok
 * @verify		2014-07-15
 * @author 		xinyan
 */
public class LPXU {
	
	public static  void main(String[] argu) {
		Config.degree=0.5;
		Config.conAttrNum=3;
		Config.desAttrNum=0;
		Config.objNum=4;
		String a[][]={{"1","0","1","tag"},
					{"1","1","1","tag"},
					{"1","0","1","tag"},
					{"1","1","0","tag"}};
		
		ArrayList<ArrayList<Integer>> Pi=new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> b=new ArrayList<Integer>();
		b.add(0);Pi.add(b);
		b=new ArrayList<Integer>();
		b.add(0);b.add(1);Pi.add(b);
		b=new ArrayList<Integer>();
		b.add(0);b.add(1);b.add(2);Pi.add(b);
		ArrayList<Integer> X=new ArrayList<Integer>();
        ListTool.ini(X, 2);
		ArrayList<Integer> U=new ArrayList<Integer>();
		ListTool.ini(U, 4);
		//System.out.println(piXiUi(Pi,X,U,a));
		piXiUi(Pi,X,U,a);
		EqualClassPara data =new EqualClassPara(b, X,U, a);
		System.out.println(LD.DX(data, Config.degree));
	}
	// 计算Xi关于Ui的Pi正域
	public static ArrayList<Integer> piXiUi(ArrayList<ArrayList<Integer>> Pi,ArrayList<Integer> X,
											ArrayList<Integer> U,String [][] matrix){
		ArrayList<Integer> Xk=new ArrayList<Integer>();
		ArrayList<Integer> Uk=new ArrayList<Integer>();
		
		ArrayList<Integer> PXU=new ArrayList<Integer>();	//PXU存放X关于U的Pi正域
		ArrayList<Integer> RiXiUi=new ArrayList<Integer>(); //RiXiUi存放Xi在关系Ri上关于Ui的的下近似
		ArrayList<Integer> XRi= new ArrayList<Integer>();
		Xk.addAll(X);Uk.addAll(U);
		EqualClassPara data;
		EqualClassPara data1=new EqualClassPara(X, X, U, matrix);
		ArrayList<ArrayList<Integer>> p= new ArrayList<ArrayList<Integer>>();
		for(ArrayList<Integer> e :Pi){
			data=new EqualClassPara(e, Xk, Uk, matrix);
			RiXiUi=LD.DX(data,Config.degree);
			PXU.addAll(RiXiUi);
			//更新Xk
			Xk.removeAll(RiXiUi);
			//更新Uk 2014-07-06将Uk改为U
			//data1.setListR(e);
			p=EquaClass.equalClass(new EqualClassPara(e,X,U,matrix));
			for(ArrayList<Integer> item:p){
				XRi.addAll(item);
			}
			XRi=ListTool.distict(XRi);
			Uk.clear();
			Uk.addAll(XRi);
//			Uk.removeAll(XRi);	
			XRi.clear();
		}
		PXU=ListTool.distict(PXU);
		return PXU;
	}
}

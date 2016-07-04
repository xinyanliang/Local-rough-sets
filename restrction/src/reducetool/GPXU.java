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
public class GPXU {
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
		for(ArrayList<Integer> e :Pi){
			data=new EqualClassPara(e, Xk, Uk, matrix);
			RiXiUi=GD.DX(data,Config.degree);
			PXU.addAll(RiXiUi);
			//更新Xk
			Xk.removeAll(RiXiUi);
			//更新Uk 2014-07-06将Uk改为U
			data1.setListR(e);
			for(ArrayList<Integer> item:EquaClass.equalClass(data1)){
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

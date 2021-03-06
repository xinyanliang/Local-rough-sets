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
public class LD {//ArrayList<Integer> equlD,
	public static ArrayList<Integer> DCD(EqualClassPara data,
			ArrayList<ArrayList<Integer>> equaD){
		ArrayList<Integer> pos=new ArrayList<Integer>();  //存储正域
		EqualClassPara data1 =new EqualClassPara(data.R,data.U,data.matrix);
		//计算X*U上的等价类
		ArrayList<ArrayList<Integer>> equlR=new ArrayList<ArrayList<Integer>>();	
		for(ArrayList<Integer> e:equaD){
			data1.setListX(e);
			equlR=EquaClass.equalClass(data1);//计算X*U上的等价类
			pos.addAll(DX(data1,Config.degree,equlR));
		}
		pos=ListTool.distict(pos);
		return pos;		
	}
	//计算X在U*U上的近似
	public static ArrayList<Integer> DX(EqualClassPara data,
										double degree,
										ArrayList<ArrayList<Integer>> equlR){
		
		ArrayList<Integer> pos=new ArrayList<Integer>();//存储正域
		ArrayList<Integer> X =new ArrayList<Integer>();
		X.addAll(data.X);
		double newd=0;
		for(ArrayList<Integer> e:equlR){
			X.retainAll(e);
			newd=(double)(X.size())/e.size();
			if(newd>=degree){
				pos.addAll(X);
			}
			X.clear();
			X.addAll(data.X);
		}
		return pos;	
	}
	
	public static ArrayList<Integer> DX(EqualClassPara data,
			double degree){
		ArrayList<ArrayList<Integer>> EqualityClassR=new ArrayList<ArrayList<Integer>>();
		EqualityClassR=EquaClass.equalClass(data);
		ArrayList<Integer> pos=new ArrayList<Integer>();//存储正域
		ArrayList<Integer> X =new ArrayList<Integer>();
		X.addAll(data.X);
		double newd=0;
		for(ArrayList<Integer> e:EqualityClassR){
			X.retainAll(e);
			newd=(double)(X.size())/e.size();
			if(newd>=degree){
				pos.addAll(X);
			}
			X.clear();
			X.addAll(data.X);
		}
		return pos;	
	}
}

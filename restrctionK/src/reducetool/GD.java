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
public class GD {//ArrayList<Integer> equlD,
	public static ArrayList<Integer> DCD(EqualClassPara data,
			ArrayList<ArrayList<Integer>> equaD){
		//计算U*U上的等价类
		ArrayList<ArrayList<Integer>> equlR=new ArrayList<ArrayList<Integer>>();
		equlR= NearR.nearR(new EqualClassPara(data.R,data.U,data.U,data.matrix));
		ArrayList<Integer> pos=new ArrayList<Integer>();  //存储正域
		EqualClassPara data1 =new EqualClassPara(data.R,data.U,data.matrix);
		
		for(ArrayList<Integer> e:equaD){
			data1.setListX(e);
			pos.addAll(DX(data1,equlR));
		}
		pos=ListTool.distict(pos);
		return pos;		
	}
	//计算X在U*U上的近似
	public static ArrayList<Integer> DX(EqualClassPara data,
			ArrayList<ArrayList<Integer>> equlR){
		ArrayList<Integer> pos=new ArrayList<Integer>();//存储正域
		ArrayList<Integer> X =new ArrayList<Integer>();
		X.addAll(data.X);
		double newd=0;
		for(ArrayList<Integer> e:equlR){
			X.retainAll(e);
			newd=(double)(X.size())/e.size();
			if(newd>=Config.degree){
				//pos.add(e.get(0));
				pos.addAll(X);
			}
			X.clear();
			X.addAll(data.X);
		}
		pos=ListTool.distict(pos);
		return pos;	
	}
	
	public static ArrayList<Integer> DX(EqualClassPara data,
			double degree){
		ArrayList<ArrayList<Integer>> EqualityClassR=new ArrayList<ArrayList<Integer>>();
		EqualityClassR=NearR.nearR(new EqualClassPara(data.R,data.U,data.U,data.matrix));
		ArrayList<Integer> pos=new ArrayList<Integer>();//存储正域
		ArrayList<Integer> X =new ArrayList<Integer>();
		X.addAll(data.X);
		double newd=0;
		for(ArrayList<Integer> e:EqualityClassR){
			X.retainAll(e);
			newd=(double)(X.size())/e.size();
			if(newd>=degree){
				//pos.add(e.get(0));
				pos.addAll(X);
			}
			X.clear();
			X.addAll(data.X);
		}
		pos=ListTool.distict(pos);
		return pos;		
	}
}

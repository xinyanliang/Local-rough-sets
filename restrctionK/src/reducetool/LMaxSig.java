package reducetool;

import java.util.ArrayList;

import comtool.EqualClassPara;
import config.Config;
/**
 * @status		ok
 * @verify		2014-07-15
 * @author 		xinyan
 */
public class LMaxSig {
	//data(listC,listD,listU,String[][][])
	//partU表示U中标记对象集合
	public static ArrayList<Integer> maxInSigD(EqualClassPara data,
			ArrayList<ArrayList<Integer>> equaD){
		//存储内部重要的对应的属性下标
		ArrayList<Integer> redtemp=new ArrayList<Integer>();
		ArrayList<Integer> listR_a=new ArrayList<Integer>();
		listR_a.addAll(data.R);
		//存放red，red_a生成等价类
		ArrayList<Integer> list1=new ArrayList<Integer>();
		EqualClassPara data2=new EqualClassPara(listR_a,data.U,data.U, data.matrix);
		list1.addAll(LD.DCD(data2,equaD));
		ArrayList<Integer> list2=new ArrayList<Integer>();
		double max=-Double.MAX_VALUE,maxTemp=0;
		Integer temp=0;
		EqualClassPara data1=new EqualClassPara(listR_a,data.U,data.U, data.matrix);
		for(Integer item:data.R){
			listR_a.remove(item);
			data1.setListR(listR_a);
			list2.clear();
			list2.addAll(LD.DCD(data1,equaD));
			maxTemp=(double)(list1.size()-list2.size())/data.U.size();//？？？？？？？？？？？？？？data.U为partU还是data.U
			if(maxTemp>max){ max=maxTemp;temp=item;}	
			listR_a.clear();
			listR_a.addAll(data.R);		
		}
		redtemp.add(temp);
		return redtemp;	
	}
	
	//data(red,listD,listU,String[][][])
	public static ArrayList<Integer> maxOutSigD(EqualClassPara data,ArrayList<Integer> C,
			ArrayList<ArrayList<Integer>> equaD){
		//存储内部重要的对应的属性下标
		ArrayList<Integer> redtemp=new ArrayList<Integer>();
		redtemp.addAll(data.R);
		//c_red为C去掉red剩余元素
		ArrayList<Integer> c_red =new ArrayList<Integer>();
		c_red.addAll(C);
		c_red.removeAll(redtemp);
		//reda为red并a
		ArrayList<Integer> reda=new ArrayList<Integer>();
		reda.addAll(redtemp);
		//存放red，red_a生成等价类
		ArrayList<Integer> list1=new ArrayList<Integer>();
		EqualClassPara data2=new EqualClassPara(data.R,data.U,data.U, data.matrix);
		list1.addAll(LD.DCD(data2,equaD));
		
		ArrayList<Integer> list2=new ArrayList<Integer>();
		double max=-Double.MAX_VALUE,maxTemp=0;
		Integer temp=c_red.get(0);
		EqualClassPara data1=new EqualClassPara(reda,data.X,data.U,data.matrix);
		for(Integer item:c_red){
			reda.add(item);
			data1.setListR(reda);	
			list2.clear();
			list2.addAll(LD.DCD(data1,equaD));
			maxTemp=(double)(list2.size()-list1.size())/data.U.size();
			if(maxTemp>max){ max=maxTemp;temp=item;}	
			reda.clear();
			reda.addAll(data.R);		
		}
		redtemp.add(temp);
		return redtemp;	
	}
	
	public static ArrayList<Integer> maxInSig(EqualClassPara data){
		//存储内部重要的对应的属性下标
		ArrayList<Integer> redtemp=new ArrayList<Integer>();
		ArrayList<Integer> listR_a=new ArrayList<Integer>();
		listR_a.addAll(data.R);
		//存放red，red_a生成等价类
		ArrayList<Integer> list1=LD.DX(data,Config.degree);
		ArrayList<Integer> list2=new ArrayList<Integer>();
		double max=-Double.MAX_VALUE,maxTemp=0;
		Integer temp=0;
		EqualClassPara data1=new EqualClassPara(listR_a,data.X,data.U, data.matrix);
		for(Integer item:data.R){
			listR_a.remove(item);
			data1.setListR(listR_a);
			list2=LD.DX(data1,Config.degree);
			maxTemp=(double)(list1.size()-list2.size())/data.U.size();
			if(maxTemp>max){ max=maxTemp;temp=item;}	
			listR_a.clear();
			listR_a.addAll(data.R);
		}
		redtemp.add(temp);
		return redtemp;	
	}
	
	//外部重要度最大的属性   
	//data(red,X,U,String[][])
	public static ArrayList<Integer> maxOutSig(EqualClassPara data,ArrayList<Integer> C){
		//创造red的一个副本
		ArrayList<Integer> redtemp=new ArrayList<Integer>();
		redtemp.addAll(data.R);//data.R=red
		//c_red为C去掉red剩余元素
		ArrayList<Integer> c_red =new ArrayList<Integer>();
		c_red.addAll(C);
		c_red.removeAll(data.R);
		//reda为red并a
		ArrayList<Integer> reda=new ArrayList<Integer>();
		reda.addAll(redtemp);
		//存放red,reda生成等价类
		ArrayList<Integer> list1=LD.DX(data,Config.degree);
		ArrayList<Integer> list2=new ArrayList<Integer>();
		double max=-Double.MAX_VALUE,maxTemp=0;
		if(c_red.size()==0)
			return redtemp;
		Integer temp=c_red.get(0);
		EqualClassPara data1=new EqualClassPara(reda,data.X,data.U, data.matrix);
		for(Integer item:c_red){
			reda.add(item);
			data1.setListR(reda);
			list2=LD.DX(data1, Config.degree);
			maxTemp=(double)(list2.size()-list1.size())/data.U.size();
			if(maxTemp>max){ max=maxTemp;temp=item;}	
			reda.remove(item);
		}
		redtemp.add(temp);
		return redtemp;
	}

}

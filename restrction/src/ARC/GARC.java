package   ARC;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import reducetool.GD;
import reducetool.GMaxSig;
import reducetool.ReadCDFile;
import comtool.EqualClassPara;
import comtool.ListTool;
import config.Config;

public class GARC {
	public static void main(String []argu) {
		
		Config.degree=0.5;
		Config.FileCDName="data\\10.test";
		Config.u=100;
		System.out.println("实验startG...");
		garc();	
		
	}
	public static void garc(){
		String[][][] matrix = ReadCDFile.fileToArrayEquality(Config.FileCDName);
		ArrayList<Integer> listR =new ArrayList<Integer> ();
		ListTool.ini(listR, Config.conAttrNum);
		ArrayList<Integer> listU =new ArrayList<Integer> ();
		ListTool.ini(listU, Config.objNum);
		ArrayList<Integer> X= new ArrayList<Integer>();
		ListTool.ini(X, Config.u);
		//计时开始
		long startTime=0,stopTime=0;
		startTime=System.nanoTime();
		//1.
		ArrayList<Integer> red = new ArrayList<>();
		//2-3计算内部重要度
		EqualClassPara data =new EqualClassPara(listR, X,listU, matrix[0]);
		red=GMaxSig.maxInSig(data);
		//4初始化Pi
		ArrayList<ArrayList<Integer>> Pi = new ArrayList<ArrayList<Integer>>();
		Pi.add(red);
	    //5
	    int stopNew =GD.DX(new EqualClassPara(red,X,listU,matrix[0]), Config.degree).size();
	    int stop=GD.DX(new EqualClassPara(listR,X,listU,matrix[0]), Config.degree).size();
	    while(stopNew!=stop){
	    	red=GMaxSig.maxOutSig(new EqualClassPara(red,X,listU,matrix[0]),listR);
	    	Pi.add(red);
	    	stopNew=GD.DX(new EqualClassPara(red,X,listU, matrix[0]), Config.degree).size();
	    }
	    stopTime=System.nanoTime();
     	System.out.println(new DecimalFormat("#.0000").format((double)(stopTime-startTime)/1000000000)+" ");
		monotonity( Pi,data);
//		System.out.println(red);
//		Collections.sort(red);
		System.out.println("约简个数："+red.size());
//		System.out.println(red);
		
	}
	
	//计算单调性
	public static void monotonity(ArrayList<ArrayList<Integer>> Pi,EqualClassPara data){
	    double[] d =new double [Pi.size()];
	    int i=0;
	    EqualClassPara data1;
	    for(ArrayList<Integer> e:Pi){
	    	data1=new EqualClassPara(e, data.X, data.U, data.matrix);
	    	d[i++]=(double)GD.DX(data1, Config.degree).size()/data.X.size();
	    }
	    for(double dd:d){
	    	System.out.println(new DecimalFormat("#.0000").format(dd)+" ");
	    }
	    System.out.println("单调性结束");
	}
}

package ARD2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import reducetool.GD;
import reducetool.EquaClass;
import reducetool.GMaxSig;
import reducetool.ReadCDFile;
import comtool.EqualClassPara;
import comtool.ListTool;
import config.Config;

public class GARD {
	public static void main(String []argu) {
		System.out.println("startG...");
		Config.FileCDName="data\\10.test";
	    GARD g= new GARD();
		System.out.println("--------------------1.0");
		for(int i=1;i<11;i++){
			Config.degree=1;
			g.gard(i);
		}
		System.out.println("--------------------0.9");
		for(int i=1;i<11;i++){
			Config.degree=0.9;
			g.gard(i);	
		}
		System.out.println("--------------------0.7");
		for(int i=1;i<11;i++){
			Config.degree=0.7;
			g.gard(i);
		}
		System.out.println("--------------------0.5");
		for(int i=1;i<11;i++){
			Config.degree=0.5;
			g.gard(i);
		}

	}
	//全局
	public void gard(int b){
		int a=b;
		//class1为按决策属性每类的个数
		int []class1={1307,1342,1528};
		
		int strSize=0;
		for(int ii=0;ii<class1.length;ii++){
			strSize+=class1[ii]/(10/a);
		}
		Config.u=strSize;
		int [] str=new int[strSize];
		int count=0;
		for(int x1=0;x1<class1.length;x1++){
			int x2start=0;
			for(int i=0;i<x1;i++){
				x2start+=class1[i];
			}
			int y=class1[x1]/(10/a);
			y=y+x2start;
			for(int x2=x2start;x2<y;x2++){
				str[count++]=x2;
			}
		}
		//
		String[][][] matrix = ReadCDFile.fileToArrayEquality(Config.FileCDName);
		ArrayList<Integer> listR =new ArrayList<Integer> ();
		ListTool.ini(listR, Config.conAttrNum);
		ArrayList<Integer> listU =new ArrayList<Integer> ();
		ListTool.ini(listU, Config.objNum);
		ArrayList<Integer> partU = new ArrayList<Integer>();
		//ListTool.ini(partU, Config.u);
		ListTool.iniByArray(partU, str);
		
		ArrayList<Integer> listD =new ArrayList<Integer> ();
		ListTool.ini(listD, Config.desAttrNum);
		//计时开始
		long startTime=0,stopTime=0;
		startTime=System.nanoTime();
		ArrayList<Integer> red = new ArrayList<>();
		//2-3计算内部重要度
		ArrayList<ArrayList<Integer>> equD = new ArrayList<ArrayList<Integer>>();
	    equD=ListTool.distict2(EquaClass.equalClass(new EqualClassPara(listD,partU,partU,matrix[1])));
	    red=GMaxSig.maxInSigD(new EqualClassPara(listR,listD,listU,matrix[0]),equD); 
		//4初始化Pi
		ArrayList<ArrayList<Integer>> Pi = new ArrayList<ArrayList<Integer>>();
		Pi.add(red);
	    
	    //5初始化判断前半部分.POSred存放red关于D关于U的正域
		ArrayList<Integer> POSred=new ArrayList<Integer>(); 
		POSred.addAll(GD.DCD(new EqualClassPara(red,listU,listU,matrix[0]),equD));
		int stopNew=ListTool.distict(POSred).size();
    	//初始化判断前半部分.POSC存放C关于D关于U的正域
    	ArrayList<Integer> POSC=new ArrayList<Integer>();
    	POSC.addAll(GD.DCD(new EqualClassPara(listR,listU,listU,matrix[0]),equD));
    	int stop=ListTool.distict(POSC).size();
	    while(stopNew!=stop){
	    	red=GMaxSig.maxOutSigD(new EqualClassPara(red,listU,listU, matrix[0]),listR,equD);
	    	POSred.clear();
	    	POSred.addAll(GD.DCD(new EqualClassPara(red,listU,listU, matrix[0]), equD));
	    	stopNew=ListTool.distict(POSred).size();
	    	Pi.add(red); 
	    }
	    stopTime=System.nanoTime();
		//monotonity(Pi,new EqualClassPara(listR, partU,listU, matrix[0]),equD);
		//System.out.println(red);
		Collections.sort(red);
		System.out.print(red);
		System.out.print(new DecimalFormat("#.0000").format((double)(stopTime-startTime)/1000000000)+" ");
		System.out.println("\t约简个数："+red.size());
	}	
	
	//计算单调性
	public static void monotonity(ArrayList<ArrayList<Integer>> Pi,EqualClassPara data,
			ArrayList<ArrayList<Integer>> equaD){
	    double[] d =new double [Pi.size()];
	    int i=0;
	    EqualClassPara data1;
	    for(ArrayList<Integer> e:Pi){
			data1=new EqualClassPara(e,data.U, data.U, data.matrix);
	    	d[i++]=(double)GD.DCD(data1,equaD).size()/Config.u;
	    }
	    for(double dd:d){
	    	System.out.println(new DecimalFormat("#.0000").format(dd)+" ");
	    }
	    System.out.println("单调性结束");
	}
	
}

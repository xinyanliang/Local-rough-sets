package testARD;

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
		Config.FileCDName="data\\17.test";
		//Config.u=958;
	    GARD g= new GARD();
	    System.out.println("****************0.5");
	    Config.degree=0.5;
	    for(int i=6;i<11;i++){
	    	g.gard(i);
	    }
	}
	//b为所标记的数据百分比
	public void gard(int b){	
		int a=b;
		int []class1={5474,348};
		//int []class1={789,766,736,805,768,775,773,734,755,747,739,761,792,783,753,803,783,758,748,796,813,764,752,787,786,734};
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
		
		//====
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
		//锟斤拷时锟斤拷始
		long startTime=0,stopTime=0;
		startTime=System.nanoTime();
		ArrayList<Integer> red = new ArrayList<>();
		//2-3锟斤拷锟斤拷锟节诧拷锟斤拷要锟斤�?
		ArrayList<ArrayList<Integer>> equD = new ArrayList<ArrayList<Integer>>();
	    equD=ListTool.distict2(EquaClass.equalClass(new EqualClassPara(listD,partU,partU,matrix[1])));
	    red=GMaxSig.maxInSigD(new EqualClassPara(listR,listD,listU,matrix[0]),equD); 
		//4锟斤拷始锟斤拷Pi
		ArrayList<ArrayList<Integer>> Pi = new ArrayList<ArrayList<Integer>>();
		Pi.add(red);
	    
	    //5锟斤拷始锟斤拷锟叫讹拷前锟诫部锟斤拷.POSred锟斤拷锟絩ed锟斤拷锟斤拷D锟斤拷锟斤拷U锟斤拷锟斤拷锟斤�?
		ArrayList<Integer> POSred=new ArrayList<Integer>(); 
		POSred.addAll(GD.DCD(new EqualClassPara(red,listU,listU,matrix[0]),equD));
		int stopNew=ListTool.distict(POSred).size();
    	//锟斤拷始锟斤拷锟叫讹拷前锟诫部锟斤拷.POSC锟斤拷锟紺锟斤拷锟斤拷D锟斤拷锟斤拷U锟斤拷锟斤拷锟斤�?
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
		System.out.println("约减属�?�个�?"+red.size());
		System.out.print(red);
		System.out.println(new DecimalFormat("#.0000").format((double)(stopTime-startTime)/1000000000)+" ");
	}	
	
	//锟斤拷锟姐单锟斤拷锟斤拷
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
	    System.out.println("锟斤拷锟斤拷锟皆斤拷锟斤�?");
	}
	
}

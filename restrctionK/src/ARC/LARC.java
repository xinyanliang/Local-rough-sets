package   ARC;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import reducetool.EquaClass;
import reducetool.NearR;
import reducetool.LD;
import reducetool.LMaxSig;
import reducetool.NearR;
import reducetool.ReadCDFile;
import comtool.EqualClassPara;
import comtool.ListTool;
import config.Config;

public class LARC {
	public static void main(String []argu) {
		//Config.FileCDName="data\\5.test";
		Config.u=417;
		System.out.println("实验startL...");
		for(int i=1;i<11;i++){
			Config.degree=1;
			Config.FileCDName="data\\"+i+".test";
			if(i!=10)
				larc10();	
			else
				larc10();
			
		}
		System.out.println("----------------0.9");
		for(int i=1;i<11;i++){
			Config.degree=0.9;
			Config.FileCDName="data\\"+i+".test";
			if(i!=10)
				larc();	
			else
				larc10();
		}
		System.out.println("----------------0.7");
		for(int i=1;i<11;i++){
			Config.degree=0.7;
			Config.FileCDName="data\\"+i+".test";
			if(i!=10)
				larc();	
			else
				larc10();
		}
		System.out.println("----------------0.5");
		for(int i=1;i<11;i++){
			Config.degree=0.5;
			Config.FileCDName="data\\"+i+".test";
			if(i!=10)
				larc();	
			else
				larc10();
		}
		
	}
	public static void larc(){
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
		ArrayList<Integer> red = new ArrayList<>();
		EqualClassPara data =new EqualClassPara(listR, X,listU, matrix[0]);
		red=LMaxSig.maxInSig(data);
		ArrayList<ArrayList<Integer>> Pi = new ArrayList<ArrayList<Integer>>();
		Pi.add(red);
		ArrayList<Integer> Xi = new ArrayList<Integer>();
		Xi.addAll(X);
		ArrayList<Integer> Ui=new ArrayList<Integer>(); 
	    Ui.addAll(listU);
		ArrayList<Integer> PiXiUi=new ArrayList<Integer>(); 
	    ArrayList<Integer> equalClassXiUired=new ArrayList<Integer>();
	    ArrayList<Integer> CLXUired=new ArrayList<Integer>();
	    int stopNew =LD.DX(new EqualClassPara(red,X,Ui,matrix[0]),Config.degree).size();
	    int stop=LD.DX(new EqualClassPara(listR,X,Ui,matrix[0]),Config.degree).size();
	    //||Xi.size()==0
	    
	    while(stopNew<stop ){
	    	//5.1
	    //	PiXiUi=LPXU.piXiUi(Pi,Xi,listU,matrix[0]);
	    	//5.2更新Ui
	    	equalClassXiUired=ListTool.equalityClassU(
	    			NearR.nearR(new EqualClassPara(red,Xi,Ui, matrix[0])
	    			));
	    	CLXUired=LD.DX(new EqualClassPara(red,X,Ui,matrix[0]),1.0);
	    	equalClassXiUired.removeAll(CLXUired);
	    	Ui=equalClassXiUired;
	    	//5.3更新Xi
	    	//Xi.removeAll(PiXiUi);
	    	Xi.removeAll(CLXUired);
	    	//5.4
	    	red=LMaxSig.maxOutSig(new EqualClassPara(red,X,Ui,matrix[0]),listR);
	    	//5.5
	    	Pi.add(red);
	    	stopNew =LD.DX(new EqualClassPara(red,X,Ui,matrix[0]), Config.degree).size();
	    	stop=LD.DX(new EqualClassPara(listR,X,Ui,matrix[0]), Config.degree).size();	
	    }
	    stopTime=System.nanoTime();
		System.out.println(new DecimalFormat("#.0000").format((double)(stopTime-startTime)/1000000000)+" ");
	}
	//计算单调性
	public static void monotonity(ArrayList<ArrayList<Integer>> Pi,EqualClassPara data){
	    double[] d =new double [Pi.size()];
	    int i=0;
	    EqualClassPara data1;
	    for(ArrayList<Integer> e:Pi){
	    	data1=new EqualClassPara(e, data.X, data.U, data.matrix);
	    	//xin
		   // System.out.println(LD.DX(data1, Config.degree).size());	
	    	d[i++]=(double)LD.DX(data1, Config.degree).size()/data.X.size();
	    }
	    for(double dd:d){
	    	System.out.println(new DecimalFormat("#.0000").format(dd)+" ");
	    }
	    System.out.println("单调性结束");
	}
	public static void larc10(){
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
		ArrayList<Integer> red = new ArrayList<>();
		EqualClassPara data =new EqualClassPara(listR, X,listU, matrix[0]);
		red=LMaxSig.maxInSig(data);
		ArrayList<ArrayList<Integer>> Pi = new ArrayList<ArrayList<Integer>>();
		Pi.add(red);
		ArrayList<Integer> Xi = new ArrayList<Integer>();
		Xi.addAll(X);
		ArrayList<Integer> Ui=new ArrayList<Integer>(); 
	    Ui.addAll(listU);
		ArrayList<Integer> PiXiUi=new ArrayList<Integer>(); 
	    ArrayList<Integer> equalClassXiUired=new ArrayList<Integer>();
	    ArrayList<Integer> CLXUired=new ArrayList<Integer>();
	    int stopNew =LD.DX(new EqualClassPara(red,X,Ui,matrix[0]),Config.degree).size();
	    int stop=LD.DX(new EqualClassPara(listR,X,Ui,matrix[0]),Config.degree).size();
	    //||Xi.size()==0
	   // System.out.println(stopNew+" "+stop);
	    while(stopNew<stop ){
	    	//5.1
	    //	PiXiUi=LPXU.piXiUi(Pi,Xi,listU,matrix[0]);
	    	//5.2更新Ui
	    	equalClassXiUired=ListTool.equalityClassU(
	    			NearR.nearR(new EqualClassPara(red,Xi,Ui, matrix[0])
	    			));
	    	CLXUired=LD.DX(new EqualClassPara(red,X,Ui,matrix[0]),1.0);
	    	equalClassXiUired.removeAll(CLXUired);
	    	Ui=equalClassXiUired;
	    	//5.3更新Xi
	    	//Xi.removeAll(PiXiUi);
	    	Xi.removeAll(CLXUired);
	    	//5.4
	    	red=LMaxSig.maxOutSig(new EqualClassPara(red,X,Ui,matrix[0]),listR);
	    	//5.5
	    	Pi.add(red);
	    	stopNew =LD.DX(new EqualClassPara(red,X,Ui,matrix[0]), Config.degree).size();
	    	stop=LD.DX(new EqualClassPara(listR,X,Ui,matrix[0]), Config.degree).size();	
	    	 //System.out.println(stopNew+" "+stop);
	    }
	    stopTime=System.nanoTime();
		System.out.println(new DecimalFormat("#.0000").format((double)(stopTime-startTime)/1000000000)+" ");
	    monotonity( Pi,new EqualClassPara(listR,X,listU,matrix[0]));
		//System.out.println(red);
		 Collections.sort(red);
		System.out.println("约简个数："+red.size());
		System.out.println(red);
	}
}

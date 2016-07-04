package   ARC;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import reducetool.EquaClass;
import reducetool.LD;
import reducetool.LMaxSig;
import reducetool.LPXU;
import reducetool.ReadCDFile;
import comtool.EqualClassPara;
import comtool.ListTool;
import config.Config;

public class LARC {
	public static void main(String []argu) {
		Config.FileCDName="data\\14.test";
		Config.u=350;
		System.out.println("实验startL...");
		Config.degree=0.7;
		larc();
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
	    System.out.println(stopNew+" "+stop);
	    while(stopNew<stop ||Xi.size()==0){
	    	//5.1
	    //	PiXiUi=LPXU.piXiUi(Pi,Xi,listU,matrix[0]);
	    	//5.2更新Ui
	    	equalClassXiUired=ListTool.equalityClassU(
	    			EquaClass.equalClass(new EqualClassPara(red,Xi,Ui, matrix[0])
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
	    	 System.out.println(stopNew+" "+stop);
	    }
	    stopTime=System.nanoTime();
		System.out.println(new DecimalFormat("#.0000").format((double)(stopTime-startTime)/1000000000)+" ");
//		monotonity( Pi,new EqualClassPara(listR,X,listU,matrix[0]));
//		System.out.println(red);
//		Collections.sort(red);
//		System.out.println("约简个数："+red.size());
//		System.out.println(red);
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
}

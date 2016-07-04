package ARD;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import reducetool.EquaClass;
import reducetool.LD;
import reducetool.LMaxSig;
import reducetool.ReadCDFile;
import comtool.EqualClassPara;
import comtool.ListTool;
import config.Config;

public class LARD {
	public static void main(String []argu) {
		System.out.println("startL...");		
        Config.FileCDName="data\\10.test";
        Config.degree=0.5;
        Config.u=683;
	    lard();
	}
		
	public static void lard(){
        
		String[][][] matrix = ReadCDFile.fileToArrayEquality(Config.FileCDName);
		ArrayList<Integer> listR =new ArrayList<Integer> ();
		ListTool.ini(listR, Config.conAttrNum);
		ArrayList<Integer> listU =new ArrayList<Integer> ();
		ListTool.ini(listU, Config.objNum);
		ArrayList<Integer> listD =new ArrayList<Integer> ();
		ArrayList<Integer> partU = new ArrayList<Integer>();
		ListTool.ini(partU, Config.u);
		ListTool.ini(listD, Config.desAttrNum);
		//��ʱ��ʼ
		long startTime=0,stopTime=0;
		ArrayList<ArrayList<Integer>> equD = new ArrayList<ArrayList<Integer>>();
	    equD=ListTool.distict2(EquaClass.equalClass(new EqualClassPara(listD,partU,partU,matrix[1])));
		startTime=System.nanoTime();
		ArrayList<Integer> red = new ArrayList<>();
		red=LMaxSig.maxInSigD(new EqualClassPara(listR,listD,listU,matrix[0]),equD);
		//4��ʼ��Pi,Ui,Ui/D
		ArrayList<ArrayList<Integer>> Pi = new ArrayList<ArrayList<Integer>>();
		Pi.add(red);
		ArrayList<Integer> Ui=new ArrayList<Integer>();  //Ui����
	    Ui.addAll(listU);
	    ArrayList<ArrayList<Integer>> Xi = new ArrayList<ArrayList<Integer>>();
	    //XiΪpartU��D�ȼ����һ������
	    Xi=ListTool.distict2(EquaClass.equalClass(new EqualClassPara(listD,partU,partU,matrix[1])));
	    //5��ʼ���жϺ�벿��
    	ArrayList<ArrayList<Integer>> CLXUiredArray=new ArrayList<ArrayList<Integer>>();
    	ArrayList<Integer> equalClassXiUired=new ArrayList<Integer>();
    	int stopNew =LD.DCD(new EqualClassPara(red,Ui,matrix[0]),equD).size();
 	    int stop=LD.DCD(new EqualClassPara(listR,Ui,matrix[0]),equD).size();
	    while(stopNew<stop){
	    	
	    	equalClassXiUired.clear();
	    	//����U����partU��صĶ��󼯺�
	    	for(ArrayList<Integer> Xj: Xi){
	    		equalClassXiUired.addAll(ListTool.equalityClassU(EquaClass.equalClass(new EqualClassPara(red,Xj,Ui,matrix[0]))));
	    	}
	    	equalClassXiUired=ListTool.distict(equalClassXiUired);
	    	Ui.clear();Ui.addAll(equalClassXiUired);
	    	//����D(Xij)=1
	    	for(ArrayList<Integer> X: equD){
	    		CLXUiredArray.add(LD.DX(new EqualClassPara(red,X,Ui,matrix[0]), 1.0));
	    	}
	    	for(int i=0;i<Xi.size();i++){
	    		Ui.removeAll(CLXUiredArray.get(i));       //����Ui
	    		Xi.get(i).removeAll(CLXUiredArray.get(i));//����Xi
	    	}
	    	//5.4
	    	red=LMaxSig.maxOutSigD(new EqualClassPara(red,listD,Ui,matrix[0]),listR,equD);
	    	//5.5
	    	Pi.add(red);
	    	stopNew =LD.DCD(new EqualClassPara(red,Ui,matrix[0]),equD).size();
	 	    stop=LD.DCD(new EqualClassPara(listR,Ui,matrix[0]),equD).size();
	 	   //System.out.println(stopNew+" "+stop);
	    }
	    stopTime=System.nanoTime();
		//monotonity(Pi,new EqualClassPara(listR, partU,listU, matrix[0]),equD);
		//System.out.println(red);
		Collections.sort(red);
		System.out.println("Լ�������"+red.size());
		System.out.print(red);
		System.out.println(new DecimalFormat("#.0000").format((double)(stopTime-startTime)/1000000000)+" ");
	}
	
	//���㵥����
	public static void monotonity(ArrayList<ArrayList<Integer>> Pi,EqualClassPara data,
			ArrayList<ArrayList<Integer>> equaD){
	    double[] d =new double [Pi.size()];
	    int i=0;
	    EqualClassPara data1;
	    for(ArrayList<Integer> e:Pi){
			data1=new EqualClassPara(e,data.U, data.U, data.matrix);
	    	d[i++]=(double)LD.DCD(data1,equaD).size()/Config.u;
	    }
	    for(double dd:d){
	    	System.out.println(new DecimalFormat("#.0000").format(dd)+" ");
	    }
	    System.out.println("�����Խ���");
	}
	
}
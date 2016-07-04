package LAC;

import java.text.DecimalFormat;
import java.util.ArrayList;

import reducetool.GD;
import reducetool.LD;
import reducetool.ReadCDFile;
import comtool.EqualClassPara;
import comtool.ListTool;
import config.Config;

/*
 * 计算LLAC与GLAC
 *状态：ok
 */
public class LAC {
	
	public static void main(String []argu) {
		//实验1
		System.out.println("实验1start...");
		Config.u=100;
		Config.degree=1;
		Config.FileCDName="data\\6.test";
		System.out.println(LLAC());
		System.out.println("stop");
	}
	public static ArrayList<Integer> LLAC(){
		ArrayList<Integer> pos=new ArrayList<Integer>();
		String[][][] matrix = ReadCDFile.fileToArrayEquality(Config.FileCDName);
	    ArrayList<Integer> listR =new ArrayList<Integer> ();
		ListTool.ini(listR, Config.conAttrNum);
		ArrayList<Integer> listU =new ArrayList<Integer> ();
		ListTool.ini(listU, Config.objNum);
		ArrayList<Integer> listX =new ArrayList<Integer> ();
		ListTool.ini(listX, Config.u);
		long startTime=0,stopTime=0;
		startTime=System.nanoTime();
		EqualClassPara data=new EqualClassPara(listR,listX, listU, matrix[0]);
		pos=LD.DX(data,Config.degree);
		stopTime=System.nanoTime();
		System.out.println(new DecimalFormat("#.0000").format((double)(stopTime-startTime)/1000000000)+" ");
		return pos;
	}
	
	public static ArrayList<Integer> GLAC(){
		ArrayList<Integer> pos=new ArrayList<Integer>();
		String[][][] matrix = ReadCDFile.fileToArrayEquality(Config.FileCDName);
		ArrayList<Integer> listR =new ArrayList<Integer> ();
		ListTool.ini(listR, Config.conAttrNum);
		ArrayList<Integer> listU =new ArrayList<Integer> ();
		ListTool.ini(listU, Config.objNum);
		ArrayList<Integer> listX =new ArrayList<Integer> ();
		ListTool.ini(listX, Config.u);
		//注意EqualityClass.equalityClass第二个参数为listU时，求的是U上的等价类
		//当listX时是X上的等价类
		long startTime=0,stopTime=0;
		startTime=System.nanoTime();
		EqualClassPara data=new EqualClassPara(listR, listX, listU, matrix[0]);
		pos=GD.DX(data,Config.degree);
		stopTime=System.nanoTime();
		System.out.println(new DecimalFormat("#.0000").format((double)(stopTime-startTime)/1000000000)+" ");
		return pos;
	}
}

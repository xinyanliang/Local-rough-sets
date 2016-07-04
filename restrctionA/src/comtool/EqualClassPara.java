package comtool;

import java.util.ArrayList;
//×´Ì¬£ºok
public class EqualClassPara {
	public  ArrayList<Integer> U=new ArrayList<Integer>();
	public  ArrayList<Integer> R=new ArrayList<Integer>();
	public  ArrayList<Integer> X=new ArrayList<Integer>();
	public  ArrayList<Integer> D=new ArrayList<Integer>();
	public String [][] matrix;
	public String [][][] matrix3;
	public EqualClassPara(ArrayList<Integer> R,ArrayList<Integer> X,
			ArrayList<Integer> U, String[][] matrix) {
		super();
		this.R = R;
		this.X = X;
		this.U = U;
		this.matrix = matrix;
	}
	public EqualClassPara(ArrayList<Integer> R,ArrayList<Integer> D,
			ArrayList<Integer> U, String[][][] matrix3) {
		super();
		this.R = R;
		this.D = D;
		this.U = U;
		this.matrix3 = matrix3;
	}

	public EqualClassPara(ArrayList<Integer> R,ArrayList<Integer> U, String[][] matrix) {
		super();
		this.R = R;
		this.U = U;
		this.matrix = matrix;
	}
	public void setListX(ArrayList<Integer> X) {
		this.X = X;
	}
	
	public void setListR(ArrayList<Integer> R) {
		this.R = R;
	}
    
	public  void setListRXU(ArrayList<Integer> R,ArrayList<Integer> X,ArrayList<Integer> U) {
		this.X = X;
		this.R = R;
		this.U = U;
	}
}


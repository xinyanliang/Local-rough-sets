package reducetool;

import java.util.ArrayList;

import comtool.EqualClassPara;
import config.Config;

/**
 * @status ok
 * @verify 2014-07-15
 * @author xinyan
 */
public class EquaClass {
	/**
	 * 求listX在data.U中的等价类 data(data.R,lisrX,data.U,data.matrix[][])
	 */
	public static ArrayList<ArrayList<Integer>> equalClass(EqualClassPara data) {
		ArrayList<ArrayList<Integer>> listEqualityClass = new ArrayList<ArrayList<Integer>>();
		int i=0, j=0,k=0;
		int col=data.R.size();
		int irow = data.X.size();
		int jrow = data.U.size();
		int tagpos=data.matrix[0].length-1;
		int count=0;
		int equalityNum=-1;
		if(col!=0){
			for(i=0;i<irow;i++){
				if(data.matrix[data.X.get(i)][tagpos].equals(Config.tag)){
					equalityNum++;
					ArrayList<Integer> list =new ArrayList<Integer>();
					listEqualityClass.add(list);//没有一个不可区分数组，就给listEqualityClass加一个元素，防止listEqualityClass.get(equalityNum)访问出错
					listEqualityClass.get(equalityNum).add(data.X.get(i));
					for(j=0;j<jrow;j++){
						count=0;
						if(data.matrix[data.U.get(j)][tagpos].equals(Config.tag)&&
								(!data.U.get(j).equals(data.X.get(i)))){
							for(k=0;k<col;k++){
								if(data.matrix[data.X.get(i)][data.R.get(k)].
									equals(data.matrix[data.U.get(j)][data.R.get(k)])) count++;
								else break;
								if(count==(col)){
									listEqualityClass.get(equalityNum).add(data.U.get(j));
									data.matrix[data.U.get(j)][tagpos]="tagno";
								}
							}
						}else continue;
					}	
				}
			}
		}
		for(i=0;i<Config.objNum;i++){
			data.matrix[i][tagpos]=Config.tag; 
		}
		return listEqualityClass;
	}
}

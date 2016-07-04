package comtool;
//状态：ok
public class StringTool {
	
	//遍历一维数组
	public static void visitArray(String[][][]str){
		for(int i=0;i<str.length;i++){
			visitArray(str[i]);
		}
	}
	
	//遍历二维数组
	public static void visitArray(String[][]str){
		for(int i=0;i<str.length;i++){
			for(int j=0;j<str[i].length;j++){
				System.out.print(str[i][j]);
			}
			System.out.println();
		}
	}
	
	/**
	 * strD是否包含strC中的每个字符。如strD="1234" strC="124"返回true
	 * @param strC
	 * @param strD
	 */
	public static boolean contains(String strC,String strD){
		boolean flag=true;
		for(int i=0;i<strC.length();i++){
			if(!strD.contains(Character.toString(strC.charAt(i)))){
				flag=false;
				break;
			}
		}
		return flag;	
	}
	/**
	 * 计算strC与strD中相同字符个数
	 * @param strC
	 * @param strD
	 * @return
	 */
	public static int containsNum(String strC,String strD){
		int count=0;
		for(int i=0;i<strC.length();i++){
			if(strD.contains(Character.toString(strC.charAt(i)))){
				count++;
			}
		}
		return count;	
	}

	//给字符串去重如："12312"-->"123"
	public static String distict(String s){
		if (s == null)  
            return s;  
		boolean flag=true;
        StringBuffer sb = new StringBuffer();  
        int i = 0, j=0,len = s.length();  
        sb.append(s.charAt(0));
        for(i=1;i<len;i++){
        	for(j=0;j<i;j++){
        		if(s.charAt(j)==s.charAt(i)){
        			flag=false;
        			break;
        		}
        	}
        	if(flag)
        		sb.append(s.charAt(i));
        }
        return sb.toString();  
	}

}

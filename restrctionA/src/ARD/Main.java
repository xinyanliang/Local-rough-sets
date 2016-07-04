package ARD;

import config.Config;
/**
 * 
 * @author hello world
 *
 */
public class Main {

	public static void main(String[] args) {
		Config.u=105;
		float [] d={0.5f,0.7f,1};
		
		System.out.println("startL...");
		for(int j=0;j<1;j++){
			Config.degree=d[j];
			System.out.print("------------");
			System.out.println(d[j]);
			for(int i=10;i<11;i++){
				Config.FileCDName="data\\"+i+".test";
				if(i!=10){
					LARD.lard();
				}else{
					LARD.lard10();
				}	
			}
		}
		
		System.out.println("startG...");
		GARD g= new GARD();
		for(int j=0;j<1;j++){
			Config.degree=d[j];
			System.out.print("------------");
			System.out.println(d[j]);
			for(int i=10;i<11;i++){
				Config.FileCDName="data\\"+i+".test";
				if(i!=10){
					g.gard();  
				}else{
					g.gard10();
				}	
			}
		}

	}

}

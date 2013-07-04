import java.util.Arrays;

import net.sswilliam.java.ormlite.DBInstance;
import test.net.sswilliam.java.ormlite.materials.guid.Task;


public class TestMain {

	public static void main(String[] args) {
		String[] numbers = {"1","2","3","4","5","6","7","8"};
		int[] masks = {0,0,0,0,0,0,0,0};
		find(numbers, masks, 4);
		
	}
	public static void find(String[] source, int[] masks, int num){
		if(num == 0){
//			printArray(masks);
			String[] num1source = new String[4];
			String[] num2source = new String[4];
			int number1 = 0;
			int number2 = 0;
			for(int i = 0;i<masks.length;i++){
				if(masks[i]==0){
					num1source[number1] = source[i];
					number1 ++;
				}else{

					num2source[number2] = source[i];
					number2 ++;
				}
			}
			handleNumber1(num1source, num2source, new int[]{0,0,0,0}, "", 4);
			
		}else{
			for(int i = 0;i<source.length;i++){
				if(masks[i] == 0){
					masks[i] = 1;
					find(source, masks, num-1);
					masks[i] = 0;
				}
			}
		}
		
	}
	public static void handleNumber1(String[] source1, String[] source2, int[] mask, String finalOut, int level ){
		if(level == 0){
			int a = Integer.parseInt(finalOut);
			handleNumber2(a, source2, new int[]{0,0,0,0},"",4);
		}else{
			for(int i = 0;i<source1.length;i++){
				if(mask[i] == 0){
					
					finalOut += source1[i];
					mask[i] = 1;
					handleNumber1(source1, source2, mask, finalOut, level-1);
					mask[i] = 0;
					finalOut = finalOut.substring(0, finalOut.length()-1);
				}
			}
		}
		
	}
	public static void handleNumber2(int a, String[] source2, int[] mask, String finalOut, int level){
		if(level == 0){
			int b = Integer.parseInt(finalOut);
			if(b == 4*a){
				System.out.println(b+" "+a);
			}
		}else{
			for(int i = 0;i<source2.length;i++){
				if(mask[i] == 0){
					finalOut += source2[i];
					mask[i] = 1;
					handleNumber2(a, source2, mask, finalOut, level-1);
					mask[i] = 0;
					finalOut = finalOut.substring(0, finalOut.length()-1);
				}
			}
		}
	}
	public static  void printArray(int[] source){
		for(int i = 0;i<source.length;i++){
			System.out.print(source[i]+" ");
		}
		System.out.println();
	}
	
	
}

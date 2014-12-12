package code;

import java.util.Scanner;

public class TestC1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Scanner in2 = new Scanner(System.in);
		String in1=in2.nextLine();
		long a=System.nanoTime();
		for(int i=0;i< in1.toString().length();i++){
			if (in1.toString().charAt(i)>27)
				System.out.println("not an english word");
		}
//		while (!in2.hasNext("[a-z]+")){
//			System.out.println("mnpt eng;ish");
//		}
		long b= System.currentTimeMillis();
		long c = b-a;
		System.out.println(a+":"+b+"="+c);
		System.out.println("complete");
	}

}

package sjsu.Pham.cs146.project1.part1;

import java.util.Random;

public class test {

	public static Matrix generateMatrix(int size){
		double[][] a = new double[size][size];
		
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				double random = new Random().nextDouble() * 10;
				random = Math.round(random*100.0)/100.0;
				a[i][j] = random;
		
			}
		}
		
		Matrix A = new Matrix(a);
		return A;
	}
	
	
	public static void main(String[] args) {
		int[] size = {1024};
		
			
			System.out.printf("Size %d\n", size[0]);
			Matrix a = generateMatrix(size[0]);
			Matrix b = generateMatrix(size[0]);
			
			long normalStart = System.currentTimeMillis();
			Matrix productRegular = a.multiply(b);
			long normalEnd = System.currentTimeMillis();
			
			
			long strassenStart = System.currentTimeMillis();
			Matrix productStrassen = a.multiplyStrassen(b);
			long strassenEnd = System.currentTimeMillis();
			
			
			
			System.out.println("regular");
			System.out.println(normalStart);
			System.out.println(normalEnd);
		//	System.out.println(productRegular);
			
			
			
			System.out.println("Strassen");
			System.out.println(strassenStart);
			System.out.println(strassenEnd);
		//	System.out.println(productStrassen);
			
			
			System.out.println();	
	}
}

package sjsu.Pham.cs146.project1.part1;

import java.util.Arrays;

public class Matrix {
	double[][] matrix;
	
	public Matrix(double[][] array){
		matrix = array;
	}
	
	public Matrix(int n){
		matrix = new double[n][n];
	}
	
	public String toString(){
		return Arrays.deepToString(matrix);
	}
	
	public boolean equals(Matrix b){
		return Arrays.deepEquals(matrix, b.matrix);
	}
	

	public Matrix addMatrix(Matrix b){
		Matrix result = new Matrix(this.matrix.length);
		for(int i = 0; i < this.matrix.length; i++){
			for(int j = 0; j < this.matrix[i].length; j++){
				result.matrix[i][j] = this.matrix[i][j] + b.matrix[i][j];
			}
		}
		return result;
	}
	
	public Matrix subtractMatrix(Matrix b){
		Matrix result = new Matrix(this.matrix.length);
		for(int i = 0; i < this.matrix.length; i++){
			for(int j = 0; j < this.matrix[i].length; j++){
				result.matrix[i][j] = this.matrix[i][j] - b.matrix[i][j];
			}
		}
		return result;
	}
	
	public Matrix partition(int row, int col){
		Matrix a = new Matrix(matrix.length/2);
		for(int i = 0; i < matrix.length/2; i++){
			for(int j = 0; j < matrix.length/2; j++){
				a.matrix[i][j] = matrix[i+row][j+col];
			}
		}
		return a;
	}
	
	public void merge(Matrix part, int row, int col){
		for(int i = row; i < row+ part.matrix.length; i ++){
			for(int j = col; j < col+part.matrix.length; j++){
				this.matrix[i][j] = part.matrix[i-row][j-col];
			}
		}
	}
	
	public Matrix multiply(Matrix b){
		int n = b.matrix.length;
		Matrix c = new Matrix(n);
		for(int i = 0;i < n; i++){
			for(int j = 0; j < n; j++){
				c.matrix[i][j] = 0;
				for(int k = 0; k < n; k++){
					c.matrix[i][j] += this.matrix[i][k]*b.matrix[k][j];
				}
			}
		}
		return c;
	}
	
	public Matrix multiplyStrassen(Matrix b){
		int n = matrix.length;
		Matrix c = new Matrix(n);
		if(n==1)
			c.matrix[0][0] = this.matrix[0][0]*b.matrix[0][0];
		else{
			//partition A, B, C into 4 n/2 matrix
			Matrix A11 = this.partition(0,0);
			Matrix A12 = this.partition(0, n/2);
			Matrix A21 = this.partition(n/2, 0);
			Matrix A22 = this.partition(n/2, n/2);
			
			Matrix B11 = b.partition(0,0);
			Matrix B12 = b.partition(0, n/2);
			Matrix B21 = b.partition(n/2, 0);
			Matrix B22 = b.partition(n/2, n/2);
			
			//create extra 7 matrices M1->M7
			// M1 = (A11 + A22)(B11 + B22)
			Matrix M1 = (A11.addMatrix(A22)).multiplyStrassen((B11.addMatrix(B22)));
			
			//M2 = (A21 + A22)B11
			Matrix M2 = (A21.addMatrix(A22)).multiplyStrassen(B11);
			
			//M3 = A11(B12 - B22)
			Matrix M3 = (A11.multiplyStrassen(B12.subtractMatrix(B22)));
			
			//M4 = A22(B21 - B11)
			Matrix M4 = A22.multiplyStrassen((B21).subtractMatrix(B11));
			
			//M5 = (A11 + A12)B22
			Matrix M5 = (A11.addMatrix(A12)).multiplyStrassen(B22);
			
			//M6 = (A21 - A11)(B11 + B12)
			Matrix M6 = (A21.subtractMatrix(A11)).multiplyStrassen(B11.addMatrix(B12));
			
			//M7 = (A12 - A22)(B21 + B22)
			Matrix M7 = (A12.subtractMatrix(A22)).multiplyStrassen(B21.addMatrix(B22));
			
			Matrix C11 = M1.addMatrix(M4).subtractMatrix(M5).addMatrix(M7);
			Matrix C12 = M3.addMatrix(M5);
			Matrix C21 = M2.addMatrix(M4);
			Matrix C22 = M1.subtractMatrix(M2).addMatrix(M3).addMatrix(M6);
			
			//Merge 4 submatrices into 1
			c.merge(C11, 0, 0);
			c.merge(C12, 0, n/2);
			c.merge(C21, n/2, 0);
			c.merge(C22, n/2, n/2);
		}
		return c;
	}
	
	
	

}

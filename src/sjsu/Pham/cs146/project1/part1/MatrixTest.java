package sjsu.Pham.cs146.project1.part1;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;


public class MatrixTest {
	
	Matrix testMatrix1 = new Matrix(new double[][] {{2,3,4,5},{3,4,6,7},{3,4,12,14},{11,2,66,8}});
	Matrix testMatrix2 = new Matrix(new double[][] {{2,3,4,5},{3,4,6,7},{3,4,12,14},{11,2,66,8}});
	Matrix testMatrix3 = new Matrix(new double[][] {{1,2,3,6},{2,3,4,6},{2,21,22,34},{21,34,56,13}});
	
	@Test public void testEqual(){
		assertTrue(testMatrix1.equals(testMatrix2));
		assertFalse(testMatrix1.equals(testMatrix3));
		}
	
	@Test public void testAddMatrix(){
		Matrix expectedResult = new Matrix(new double[][]{{4,6,8,10},{6,8,12,14},{6,8,24,28},{22,4,132,16}});
		Matrix result = testMatrix1.addMatrix(testMatrix2);
		
		assertTrue(result.equals(expectedResult));
		
	}
	
	@Test public void testSubtractMatrix(){
		Matrix expectedResult = new Matrix(new double[][]{{1,1,1,-1},{1,1,2,1},{1,-17,-10,-20},{-10,-32,10,-5}});
		Matrix result = testMatrix2.subtractMatrix(testMatrix3);
		
		assertTrue(result.equals(expectedResult));
	}
	
	@Test public void testPartition(){
		Matrix a11 = testMatrix1.partition(0,0);
		Matrix a12 = testMatrix1.partition(0, 2);
		Matrix a21 = testMatrix1.partition(2, 0);
		Matrix a22 = testMatrix1.partition(2, 2);
		
		Matrix expect11 = new Matrix(new double[][]{{2,3},{3,4}});
		Matrix expect12 = new Matrix(new double[][]{{4,5},{6,7}});
		Matrix expect21 = new Matrix(new double[][]{{3,4},{11,2}});						
		Matrix expect22 = new Matrix(new double[][]{{12,14},{66,8}});
		
		assertTrue(a11.equals(expect11));
		assertTrue(a12.equals(expect12));
		assertTrue(a21.equals(expect21));
		assertTrue(a22.equals(expect22));
		
	}
	
	@Test public void testMerge(){
		Matrix a = new Matrix(4);
		a.merge(new Matrix(new double[][]{{2,3},{3,4}}), 0, 0);
		a.merge(new Matrix(new double[][]{{4,5},{6,7}}), 0, 2);
		a.merge(new Matrix(new double[][]{{3,4},{11,2}}), 2, 0);
		a.merge(new Matrix(new double[][]{{12,14},{66,8}}), 2, 2);
		
		assertTrue(testMatrix1.equals(a));
	}
	
	@Test public void testMultiply(){
		double[][] a = new double[][]{{121.0, 267.0, 386.0, 231.0},
									  {170.0, 382.0, 549.0, 337.0},
									  {329.0, 746.0, 1073.0, 632.0},
									  {315.0, 1686.0, 1941.0, 2426.0}};
		Matrix A = new Matrix(a);
		assertTrue(A.equals(testMatrix1.multiply(testMatrix3)));
	}
	
	@Test public void testMultiplyStrassen(){
		double[][] a = new double[][]{{121.0, 267.0, 386.0, 231.0},
										{170.0, 382.0, 549.0, 337.0},
										{329.0, 746.0, 1073.0, 632.0},
										{315.0, 1686.0, 1941.0, 2426.0}};
		Matrix A = new Matrix(a);
		assertTrue(A.equals(testMatrix1.multiplyStrassen(testMatrix3)));
	}	
}

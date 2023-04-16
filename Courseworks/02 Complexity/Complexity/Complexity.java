// Name-Lastname:	Thanapoom Phatthanaphan
// CWID:			20011296
// Section:			CS 570-PA
// Subject:			Homework 2

import java.lang.Math;

public class Complexity {

	public static void method1(int n) {
	// method with the time complexity of O(n^2)
		
		int count = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println("Operation " + count);
				count++;
			}
		}
	}
	
	public static void method2(int n) {
	// method with the time complexity of O(n^3)
		
		int count = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					System.out.println("Operation " + count);
					count++;
				}
			}
		}
	}
	
	public static void method3(int n) {
	// method with the time complexity of O(logn)
		
		int count = 1;
		for (int i = 1; i < n; i *= 2) {
			System.out.println("Operation " + count);
			count++;
		}
	}
	
	public static void method4(int n) {
	// method with the time complexity of O(nlogn)
		
		int count = 1;
		int length = n;
		for (int i = 0; i < length; i++) {
			for (int j = 1; j < length;) {
				System.out.println("Operation " + count);
				count++;
				length /= 2;
			}
			length = n;
		}
	}
	
	public static void method5(int n) {
	// method with the time complexity of O(log(logn))
		
		int count = 1;
		for (double i = 2; i < n; i = Math.pow(i, 2)) {
			System.out.println("Operation " + count);
			count++;
		}
	}
	
	public static void method6(int n) {
	// method with the time complexity of O(2^n)
		
		int count = 1;
		for (int i = 0; i < Math.pow(2,  n); i++) {
				System.out.println("Operation " + count);
				count++;
		}
	}
	
	public static void main(String[] args) {
		
		int n = 8;
		System.out.println("Time Complexity: O(n^2)");
		method1(n);
		
		System.out.println("\nTime Complexity: O(n^3)");
		method2(n);
		
		System.out.println("\nTime Complexity: O(logn)");
		method3(n);
		
		System.out.println("\nTime Complexity: O(nlogn)");
		method4(n);
		
		System.out.println("\nTime Complexity: O(loglogn)");
		method5(n);
		
		System.out.println("\nTime Complexity: O(2^n)");
		method6(n);
		
	}

}
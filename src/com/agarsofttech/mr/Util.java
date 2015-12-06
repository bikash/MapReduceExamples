package com.agarsofttech.mr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import solutions.IntegerSolution;
import solutions.Solution;

public class Util {
	public static final int N = 22;
	public static final float min_Fitness = (float) 0.020;
	public static final int ItemSise = 20000;
	public static final int Iteration = 5;

	
	
	static int[] String2Array(String k){
		String[] arr = k.split("->");
		String[] arr1 = null;
		//List<Integer> c = new ArrayList<Integer>();
		int[] chromosomes = new int[3];
		//System.out.println("String line " + k);
		if(arr.length>1){
			 chromosomes[0]= Integer.parseInt(arr[0].trim());
			 arr1 = arr[1].split(",");
		}
		for(int i=0;i<arr1.length-1;i++){
    		chromosomes[i+1]= Integer.parseInt(arr1[i].trim());
    	}
		int length = arr1.length-1;
		String[] lastval = arr1[length].split(" ");
    	
    	chromosomes[arr1.length] = Integer.parseInt(lastval[0].trim());
    	/*for(int i=0;i<chromosomes.length;i++){
    		System.out.println(chromosomes[i]);
    	}*/
		return chromosomes;
	}
	
	public static String ArraytoString(Solution k){
		String str="";
		//IntegerSolution bs1 = new IntegerSolution(null, k.length, k);
		for(int i=0;i<k.getNumberOfBits();i++){
			//System.out.println(" string " + k[i].getValue(i));
    		str = str + k.getValue(i) + " ";
    	}
		//System.out.println(" string " + str);
		return str;
	}
	
	
	

	
}

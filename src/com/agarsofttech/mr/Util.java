package com.agarsofttech.mr;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import operators.crossover.SinglePointCrossover;
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
	
	
	public static void writeToFile(String instr, String outstr) throws IOException{
		    String sCurrentLine;
		  	BufferedReader br = null;
			//br = new BufferedReader(new FileReader("data/phase1.txt"));
		  	br = new BufferedReader(new FileReader(instr));
			int[][] chromosome = new int[1000][3];
			int i =0;
			while ((sCurrentLine = br.readLine()) != null) {
					int[] c1 = Util.String2Array(sCurrentLine);
					chromosome[i]=c1;
					//System.out.println(sCurrentLine);
					i++;
			}
			SinglePointCrossover c = new SinglePointCrossover();
			//PrintWriter writer = new PrintWriter("data/input3.txt", "UTF-8");	
			PrintWriter writer = new PrintWriter(outstr, "UTF-8");	
			for (int j = 0; j < i; j=j+2) {
				int len1 = chromosome[j].length;
				int len2 = chromosome[j].length;
				IntegerSolution bs1 = new IntegerSolution(null, len1, chromosome[j]);
				IntegerSolution bs2 = new IntegerSolution(null, len2, chromosome[j+1]);
				Solution[] result = c.doCrossover(1, 0, bs1, bs2);
				//System.out.println("Total crossover: " + result[0]);
				//System.out.println("Total crossover: " + result[1]);
				String str1 = Util.ArraytoString(result[0]);
				String str2 = Util.ArraytoString(result[1]);
				writer.println(str1);
				writer.println(str2);
			}
			writer.close();
	}

	
}

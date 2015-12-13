package com.agarsofttech.mr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import operators.crossover.SinglePointCrossover;
import solutions.IntegerSolution;
import solutions.Solution;

public class Util {
	public static final int N = 22;
	public static final float min_Fitness = (float) 0.020;
	public static final int ItemSise = 20000;
	public static final int Iteration = 2;

	
	
	//convert String to array
	static int[] String2Array(String k){
		//System.out.println("String line " + k);
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
	
	//convert array to string 
	public static String ArraytoString(Solution k){
		String str="";
		for(int i=0;i<k.getNumberOfBits();i++){
    		str = str + k.getValue(i) + " ";
    	}
		//System.out.println(" string " + str);
		return str;
	}
	
	
	@SuppressWarnings("resource")
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
				String str1 = Util.ArraytoString(result[0]);
				String str2 = Util.ArraytoString(result[1]);
				writer.println(str1);
				writer.println(str2);
			}
			writer.close();
	}

	// convert string to crossover mutation
	public static String[] crossover(String[] instr) throws IOException{
	  
	  	String[] r = new String[2];
		int[][] chromosome = new int[1000][3];
		int i = 0;
		System.out.println("sixe chromo" + instr.length);
		for	( i=0; i<instr.length;i++){
				chromosome[i] = Util.String2Array(instr[i]);
				System.out.println("chorom" + chromosome[i]);
		}
		SinglePointCrossover c = new SinglePointCrossover();
		//PrintWriter writer = new PrintWriter("data/input3.txt", "UTF-8");	
		//PrintWriter writer = new PrintWriter(outstr, "UTF-8");	
		for (int j = 0; j < i; j=j+2) {
			int len1 = chromosome[j].length;
			int len2 = chromosome[j+1].length;
			IntegerSolution bs1 = new IntegerSolution(null, len1, chromosome[j]);
			IntegerSolution bs2 = new IntegerSolution(null, len2, chromosome[j+1]);
			Solution[] result = c.doCrossover(1, 0, bs1, bs2);
			//System.out.println("Total crossover: " + result[0]);
			//String str1 = Util.ArraytoString(result[0]);
			//String str2 = Util.ArraytoString(result[1]);
			//writer.println(str1);
			//writer.println(str2);
			
			r[0] =Util.ArraytoString(result[0]);
			r[1] =Util.ArraytoString(result[1]);
		}
		
		//writer.close();
		return r;
	}
	
		//convert String to get fitness value
		static float String2value(String k){
					String[] arr = k.split("->");
					String[] arr1 = null;
					if(arr.length>1)
						 arr1 = arr[1].split(" ");
					//System.out.println("\n Arr len  " + arr1.length+ " "+ arr1[arr1.length-1]);
					float fitness = Float.parseFloat(arr1[arr1.length-1].trim());
					return fitness;
				}
}

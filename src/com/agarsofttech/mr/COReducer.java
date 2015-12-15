
package com.agarsofttech.mr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import operators.crossover.SinglePointCrossover;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import solutions.IntegerSolution;
import solutions.Solution;

@SuppressWarnings("unused")
public class COReducer
       extends Reducer<FloatWritable,Text, Text,FloatWritable> {

    private final static FloatWritable fitness = new FloatWritable(1);
	private final float[] fits = new float[Util.ReducerArray];
   // private final  ArrayList<Float> myArray = new ArrayList<Float>();
	private final String[] k = new String[Util.ReducerArray];
	public void reduce(FloatWritable key, Iterable<Text> values,
                       Context context
                       ) throws IOException, InterruptedException {

	  int i = 0;
	  String[] c = new String[Util.ReducerArray];
	 
      for (Text val : values) {
    		  c[i]=val.toString();
    		  i++;
      }
      String[] c1 = new String[i];
      System.out.println("size " + i);
      System.arraycopy(c, 0, c1, 0, i);
      int[][] chromosome = new int[i][3];
      float[] fit = new float[i];
      for	( int j=0; j<i;j++){
			chromosome[j] = Util.String2Array(c1[j]);
			fit[j] = Util.String2value(c1[j]);
      }
      String[] r = new String[2];
      SinglePointCrossover cr1 = new SinglePointCrossover();
      //System.out.println("size c1  " + chromosome.length);
      for (int j = 0; j < chromosome.length-1; j=j+2) {
		int len1 = chromosome[j].length;
		int len2 = chromosome[j+1].length;
		IntegerSolution bs1 = new IntegerSolution(null, len1, chromosome[j]);
		IntegerSolution bs2 = new IntegerSolution(null, len2, chromosome[j+1]);
		Solution[] result = cr1.doCrossover(1, 0, bs1, bs2);
		//System.out.println("Total crossover: " + result[0]);
		
		fitness.set(fit[j]);
		context.write(new Text(ArraytoString(result[0])),fitness);
		fitness.set(fit[j+1]);
		context.write(new Text(ArraytoString(result[1])),fitness);
      }
    
    
    }
	//convert array to key value 
	public static String ArraytoString(Solution k){
		String str="";
		for(int i=0;i<k.getNumberOfBits();i++){
    		str = str + k.getValue(i) + " ";
    	}
		//System.out.println(" string " + str);
		return str;
	}
	
		
  }
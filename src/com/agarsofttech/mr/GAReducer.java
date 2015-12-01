package com.agarsofttech.mr;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class GAReducer
       extends Reducer<Text,PairOfFloatString,Text,FloatWritable> {

    private final static FloatWritable fitness = new FloatWritable(1);
	private final float[] fits = new float[50];
	private final String[] k = new String[50];
	public void reduce(Text key, Iterable<PairOfFloatString> values,
                       Context context
                       ) throws IOException, InterruptedException {

	  int i = 0;
      for (PairOfFloatString val : values) {
    		  fits[i] = calcFitness(val.getKey());
    		  //System.out.println("arr fit " +  fits[i]);
    		  k[i] = val.getValue();
    		  i++;
      }
   
      float[] arr = calcMaxFit(fits);
      for (int j = 0; j < i; j++) {
    	  fitness.set(arr[j]);
	      String rkey=rootKey(k[j]);
	      Text rootKey = new Text(rkey);
		  context.write(rootKey, fitness);
      	}
    
    }
	
	private String rootKey(String k){
		String[] arr = k.split(",");
    	String a = arr[0].substring(1, arr[0].length());
    	String str1 = "";
    	String sep = "";
    	for(int i=1;i<arr.length;i++){
    		if(i==1)
    			sep = "";
    		else
    			sep =",";
    		str1= str1+sep+arr[i].trim();
    	}
    	String str= a+" -> "+ str1;
    	str = str.substring(0, str.length()-1) +"   ";
    	
		return str;
	}
    private float calcFitness(float val) {
    	float sup = val/Util.N;
		return sup;
	}
    

    private float[] calcMaxFit(float[] fit) {
    	for(int j =0; j<fit.length;j++){
    		if(fit[j]>=Util.min_Fitness){
    			fit[j]=maxValue(fit);
    		}
        }
    	return fit;
	}
    private static float maxValue(float[] arr) {
    	float max = arr[0];
    	for (int ktr = 0; ktr < arr.length; ktr++) {
    		if (arr[ktr] > max) {
    			max = arr[ktr];
    		}
    	}
    	return max;
    }
	/*private boolean isRuleFit(float fit) {
		return fit>=Util.min_Fitness;
	}*/
  }
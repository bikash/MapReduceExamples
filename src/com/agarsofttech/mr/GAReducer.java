package com.agarsofttech.mr;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class GAReducer
       extends Reducer<Text,FloatWritable,Text,FloatWritable> {

    private final static FloatWritable fitness = new FloatWritable(1);

	public void reduce(Text key, Iterable<FloatWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      for (FloatWritable val : values) {
    		  float fit = calcFitness(val.get());
    		  fitness.set(fit);
    		  context.write(key, fitness);
      }
      for (FloatWritable val : values) {
    	  
      }
      
    }
    private float calcFitness(float val) {
    	float sup = val/Util.N;
		return sup;
	}
    
    private Text convertKey(Text key){
    	
		return key;
    	
    }
    private float calcMaxFit(float fit) {
    	
    	
		//return fit>=Util.min_Fitness;
    	return (float) 1.0;
	}
    
	private boolean isRuleFit(float fit) {
		return fit>=Util.min_Fitness;
	}
  }
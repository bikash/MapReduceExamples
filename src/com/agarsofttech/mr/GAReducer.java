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
    	 // if(isRuleFit(val.get()))
    	  //{
    		  float sup = calcFitness(val.get());
    		  //System.out.println("Fitness" + key + sup + " -> "+ val.get());
    		  fitness.set(sup);
    		  context.write(key, fitness);
    	  //}
      }
    }
    private float calcFitness(float val) {
    	float sup = val/Util.N;
		return sup;
	}
    
    
	private boolean isRuleFit(float fit) {
		return fit>=Util.min_Fitness;
	}
  }
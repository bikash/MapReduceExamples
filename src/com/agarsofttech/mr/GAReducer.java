package com.agarsofttech.mr;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class GAReducer
       extends Reducer<Text,IntWritable,Text,IntWritable> {

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      for (IntWritable val : values) {
    	  if(isRuleFit(val.get()))
    	  {
    		  context.write(key, val);
    	  }
      }
    }


	private boolean isRuleFit(int sum) {
		return sum>=Util.min_F;
	}
  }
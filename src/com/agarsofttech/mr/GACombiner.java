package com.agarsofttech.mr;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class GACombiner
       extends Reducer<Text,FloatWritable,Text,FloatWritable> {
    private FloatWritable result = new FloatWritable();

    public void reduce(Text key, Iterable<FloatWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
      for (FloatWritable val : values) {
        sum += val.get();
      }
      
      /*if(isRuleFit(sum))*/
    	  result.set(sum);
    	  KeyValue kv = new KeyValue(key, sum);
    	  //System.out.println("Key > sum" + key + sum);
    	  context.write(key, result);

    }
  }
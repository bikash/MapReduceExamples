package com.agarsofttech.mr;

import java.io.IOException;

//import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class GACombiner
       extends Reducer<Text,PairOfFloatString,Text,PairOfFloatString> {
    //private FloatWritable result = new FloatWritable();

    public void reduce(Text key, Iterable<PairOfFloatString> values,
                       Context context
                       ) throws IOException, InterruptedException {
      float sum = 0;
      for (PairOfFloatString val : values) {
    	  sum += val.getKey();
      }
      
      PairOfFloatString kv = new PairOfFloatString();
      String k = key.toString();
      kv.set(sum, k);
      System.out.println("Key > sum" + kv.toString());
      //result.set(sum);
      context.write(key, kv);

    }
  }
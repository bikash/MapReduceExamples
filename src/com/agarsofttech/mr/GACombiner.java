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
      Text BaseKey = new Text(getBaseKey(k));
      System.out.println("Key > " + BaseKey);
      //Convert string of [38, 39, 48] in to 38 as key
      //System.out.println("Key > sum" + kv.toString());
      //result.set(sum);
      context.write(BaseKey, kv);

    }
    
    public String getBaseKey(String k)
    {
    	//System.out.println("Key in > " + k);
    	String[] arr = k.split(",");
    	String a = arr[0].substring(1, arr[0].length());
    	//System.out.println("Key > " + arr[0] + "  "+a);
		return a;
    }
    
  }
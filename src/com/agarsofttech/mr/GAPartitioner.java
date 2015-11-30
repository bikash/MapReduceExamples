package com.agarsofttech.mr;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class GAPartitioner extends Reducer<Text,FloatWritable,Text,FloatWritable> {
	protected void reduce(Text key, Iterable<FloatWritable> values, Context context)
	throws IOException, InterruptedException {

		 KeyValue kv = new KeyValue(key, values);
		 //System.out.println("Key > sum" + key + sum);
		 //context.write(key, kv);
		
		
	}
}
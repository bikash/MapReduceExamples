package com.agarsofttech.mr;



import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import solutions.BinarySolution;
import solutions.Solution;
import operators.crossover.SinglePointCrossover;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
//import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.google.common.io.Files;


// Taken from https://hadoop.apache.org/docs/current/hadoop-mapreduce-client/hadoop-mapreduce-client-core/MapReduceTutorial.html#Source_Code
public class SPGATry1 {

public static void main(String[] args) throws Exception {
	
	// Get the current run time.  Not very accurate, but useful for 
	// some simple reporting.
	long startTime = System.currentTimeMillis();
	
    Configuration conf = new Configuration();
   /* Job job = Job.getInstance(conf,"asd");
    job.setJarByClass(SPGATry1.class);
    job.setMapperClass(GAMapper.class);
    job.setCombinerClass(GACombiner.class);
    job.setReducerClass(GAReducer.class);

    //job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(PairOfFloatString.class);
    
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(FloatWritable.class);
    //FileInputFormat.addInputPath(job, new Path(args[0]));
   //FileOutputFormat.setOutputPath(job, new Path(args[1]));
    FileInputFormat.addInputPath(job, new Path("data/input1.txt"));
    FileOutputFormat.setOutputPath(job, new Path("out"));
    System.exit(job.waitForCompletion(true) ? 0 : 1);*/
    
    String sCurrentLine;
    BufferedReader br = null;
	br = new BufferedReader(new FileReader("data/phase1.txt"));
	HashSet<List<Integer>> c = new HashSet<List<Integer>>();
	while ((sCurrentLine = br.readLine()) != null) {
		  c.add(Util.String2Array(sCurrentLine));
			//rule.add(c1);
			//System.out.println(sCurrentLine);
	}
	System.out.println(" size -->" + c.size());
	//for(int j=0;j<c.size();j++){
		for(List<Integer> c1:c){
			System.out.println("index0 -->" + c1.get(0) );
		}
	//}

	/*SinglePointCrossover c = new SinglePointCrossover();
	BinarySolution bs1 = new BinarySolution(null, 6, new String[] { 0, 0, 0, 1, 1, 1 });
	BinarySolution bs2 = new BinarySolution(null, 6, new int[] { 1, 1, 1, 0, 0, 0 });

	Solution[] result = c.doCrossover(1, 1, bs1, bs2);
	System.out.println("Total crossover: " + result[1]);*/
	//assertTrue(result[0].equals(new BinarySolution(null, 4, new int[]{ 0, 1, 1, 0})));
	//assertTrue(result[1].equals(new BinarySolution(null, 4, new int[]{ 1, 0, 0, 1})));		

	// Get the end time for the simulation.
	long endTime = System.currentTimeMillis();
	System.out.println("Total execution time: " + (endTime - startTime) + "ms");
	
  }
}
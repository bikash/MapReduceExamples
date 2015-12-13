package com.agarsofttech.mr;



import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;




import solutions.IntegerSolution;
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
@SuppressWarnings("unused")
public class SPGATry1 {

public static void main(String[] args) throws Exception {
	
	// Get the current run time.  Not very accurate, but useful for 
	// some simple reporting.
	long startTime = System.currentTimeMillis();
	
    //Configuration conf = new Configuration();
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
    
	
	// Get the end time for the simulation.
	long endTime = System.currentTimeMillis();
	System.out.println("Total execution time: " + (endTime - startTime) + "ms");
	

  }
		


}
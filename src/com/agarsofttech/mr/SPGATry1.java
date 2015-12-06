package com.agarsofttech.mr;



import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import solutions.BinarySolution;
import solutions.IntegerSolution;
import solutions.Solution;
import operators.crossover.SinglePointCrossover;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
//import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.ToolRunner;

import com.google.common.io.Files;


// Taken from https://hadoop.apache.org/docs/current/hadoop-mapreduce-client/hadoop-mapreduce-client-core/MapReduceTutorial.html#Source_Code
public class SPGATry1 {
	public static String rootDir = "/Users/bikash/repos/bigdata/MapReduceExamples";
	
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
    
    

	launch(2);
	
	 /*	Job job = Job.getInstance(conf,"asd");
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
	    FileInputFormat.addInputPath(job, new Path("data/input3.txt"));
	    FileOutputFormat.setOutputPath(job, new Path("out4"));
	    System.exit(job.waitForCompletion(true) ? 0 : 1);	*/
	
	// Get the end time for the simulation.
	long endTime = System.currentTimeMillis();
	System.out.println("Total execution time: " + (endTime - startTime) + "ms");
	
  }


static void launch(int iter) throws IOException {

	int it=0;
	for(int i =0;i<iter;i++) {
		
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf,"asd");
	    job.setJarByClass(SPGATry1.class);
	    job.setMapperClass(GAMapper.class);
	    job.setCombinerClass(GACombiner.class);
	    job.setReducerClass(GAReducer.class);

	    job.setMapOutputValueClass(PairOfFloatString.class);
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(FloatWritable.class);
		job.setSpeculativeExecution(true);
		//FileInputFormat.addInputPath(job, new Path("data/input3.txt"));
		//FileOutputFormat.setOutputPath(job, new Path("out4"));
		//System.exit(job.waitForCompletion(true) ? 0 : 1);	
	
		System.out.println("launching");

		Path tmpDir = new Path(rootDir);
		Path inDir = new Path(tmpDir, "iter" + it);
		Path outDir = new Path(tmpDir, "iter" + (it + 1));
		FileInputFormat.setInputPaths(job, inDir);
		FileOutputFormat.setOutputPath(job, outDir);

		FileSystem fileSys = null;
		try {
			fileSys = FileSystem.get(conf);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*try {
				fileSys.delete(tmpDir, true);
			} catch(IOException ie) {
				System.out.println("Exception while deleting");
				ie.printStackTrace();
			}*/
		System.out.println("Deleting dir");

		
		System.out.println("Starting Job");
		//long startTime = System.currentTimeMillis();

		//it++;
	}
}


/**
 * Launches all the tasks in order.
 */
public int run(String[] args) throws Exception {
	int iter = Integer.parseInt(args[0]);
	launch(iter);
	return 0;
}




}
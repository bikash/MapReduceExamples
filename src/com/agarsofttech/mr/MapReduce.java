package com.agarsofttech.mr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Iterator;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.CompressionType;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Partitioner;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.lib.IdentityReducer;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


@SuppressWarnings({ "deprecation", "unused" })
public class MapReduce {

	public static String rootDir = "/Users/bikash/repos/bigdata/MapReduceExamples";

	static void launch(String inputFile) throws IOException, ClassNotFoundException, InterruptedException {
		
		long startTime = System.currentTimeMillis();
	    int it=0;
		while(it<Util.Iteration) {
			
			 String infile;
			 String outStr;
	         outStr = "output_"+it;
	         infile="output_"+(it-1)+"/part-r-00000";
			 System.out.println("Starting Job iteration " + it);
			 Job job = new Job();
	         job.setJobName("Iterative Genetic Algorithm for calulating fitness");
	         job.setJarByClass(MapReduce.class);
	         if(it == 0){
		         job.setMapOutputValueClass(PairOfFloatString.class);
		         job.setOutputKeyClass(Text.class);
		         job.setOutputValueClass(FloatWritable.class);
		         job.setMapperClass(GAMapper.class);
	     	 	 job.setCombinerClass(GACombiner.class);
	     	 	 job.setReducerClass(GAReducer.class);	
		         infile = inputFile;
		         FileInputFormat.addInputPath(job, new Path(infile));
	             FileOutputFormat.setOutputPath(job, new Path(outStr));
	         }
	         else
	         {
	        	 job.setMapOutputValueClass(PairOfFloatString.class);
		         job.setOutputKeyClass(Text.class);
		         job.setOutputValueClass(FloatWritable.class);
		         job.setMapperClass(FTMapper.class);
	     	 	 job.setCombinerClass(FTCombiner.class);
	     	 	 job.setReducerClass(FTReducer.class);	
	     	 	 infile = "outputCo_"+(it-1)+"/part-r-00000";
	     	 	 FileInputFormat.addInputPath(job, new Path(infile));
	             FileOutputFormat.setOutputPath(job, new Path(outStr));
	         }
	        	 
	         
             if(job.waitForCompletion(true))
             {
	            	Job job1 = new Job();
	     	 		System.out.println(" Iterative Genetic Algorithm for calulating crossover " + it);
	     	 		job1.setJobName("Iterative Genetic Algorithm for calulating crossover");
	     	 		job1.setJarByClass(MapReduce.class);
	     	 		job1.setMapperClass(COMapper.class);
	     	 		job1.setReducerClass(COReducer.class);	
	     	 		job1.setMapOutputValueClass(Text.class);
	     	 		job1.setOutputKeyClass(FloatWritable.class);
	     	 		job1.setOutputValueClass(FloatWritable.class);
	     	 		FileInputFormat.addInputPath(job1, new Path(outStr));
        	 		FileOutputFormat.setOutputPath(job1, new Path("outputCo_"+(it)));
        	 		job1.waitForCompletion(true);
             }       
	         it++;
			}
			long endTime = System.currentTimeMillis();
			System.out.println("Total execution time: " + (endTime - startTime) + "ms");
			
}

	/**
	 * Launches all the tasks in order.
	 */

	public static void main(String[] argv) throws Exception {
		Configuration conf = new Configuration();
		String[] args = new GenericOptionsParser(conf, argv).getRemainingArgs();
		if (args.length < 1) {
			System.err.println("Usage: GeneticMR  <Input file>");
			System.exit(2);
		}
		launch(args[0]);

	}
}
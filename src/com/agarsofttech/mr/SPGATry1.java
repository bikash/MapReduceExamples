package com.agarsofttech.mr;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
//import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
// Taken from https://hadoop.apache.org/docs/current/hadoop-mapreduce-client/hadoop-mapreduce-client-core/MapReduceTutorial.html#Source_Code
public class SPGATry1 {

public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf,"asd");
    job.setJarByClass(SPGATry1.class);
    job.setMapperClass(GAMapper.class);
    job.setCombinerClass(GACombiner.class);

    //job.setReducerClass(GAReducer.class);
    //job.setMapperClass(MaxFitMapper.class);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(PairOfFloatString.class);
    
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(PairOfFloatString.class);
    //FileInputFormat.addInputPath(job, new Path(args[0]));
    //FileOutputFormat.setOutputPath(job, new Path(args[1]));
    FileInputFormat.addInputPath(job, new Path("data/input1.txt"));
    FileOutputFormat.setOutputPath(job, new Path("out1"));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
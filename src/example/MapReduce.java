package example;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class MapReduce {

	 // input hdfs://localhost:9000/trivago/hadoop/challenge/input/
	 // output hdfs://localhost:9000/out/
	
	public static void main (String [] args) throws Exception{
		//creating a JobConf object and assigning a job name for identification purposes
		JobClient client = new JobClient();
        JobConf conf = new JobConf(MapReduce.class);
        conf.setJobName("Text Processing..");

        //specify output Key and Value
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);

        //Providing the mapper and reducer class names
        conf.setMapperClass(TextMapper.class);
        conf.setReducerClass(TextReducer.class);
        

        //the hdfs input and output directory to be fetched from the command line
        FileInputFormat.addInputPath(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        client.setConf(conf);
        try{
        	JobClient.runJob(conf);
        }catch (Exception e){
        	e.printStackTrace();
        }
       
    }
	
	
}

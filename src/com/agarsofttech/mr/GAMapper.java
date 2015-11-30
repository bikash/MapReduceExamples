package com.agarsofttech.mr;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

@SuppressWarnings("unused")
public class GAMapper extends Mapper<Object, Text, Text, FloatWritable>{

    private final static FloatWritable one = new FloatWritable(1);

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

    	//try(
    		Scanner scanner = new Scanner(value.toString());
    		//) {
    		while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //System.out.println("line -> " + line);
                Set<String> chromosome = getChromosome(line);
                Set<List<Integer>> rules = Rule.getRules(chromosome);
                for(List<Integer> rule:rules){
                	//System.out.println("Rules " + rule.toString());
                	context.write(new Text(rule.toString()), one);
                }
            }
		//} catch (Exception e) {
			//e.printStackTrace();
		//}
    }

	private Set<String> getChromosome(String line) {
		
		return new HashSet<String>(Arrays.asList(line.split("\\s")));
	}
	
	
}
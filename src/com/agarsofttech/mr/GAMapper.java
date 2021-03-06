package com.agarsofttech.mr;

import java.io.IOException;
import java.io.PrintWriter;
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
public class GAMapper extends Mapper<Object, Text, Text, PairOfFloatString>{

    private final static FloatWritable one = new FloatWritable(1);
    private final static PairOfFloatString kv = new PairOfFloatString();
    @SuppressWarnings("resource")
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

    	//try(
    		Scanner scanner = new Scanner(value.toString());
    		//) {
    		PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
    		while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //System.out.println("line " + line);
                Set<String> chromosome = getChromosome(line);
                Set<List<Integer>> rules = Rule.getRules(chromosome);
                for(List<Integer> rule:rules){
                	 //System.out.println("Rules " + rule.toString());
                     String k = key.toString();
                     kv.set(1, k);
                     writer.println(rule.toString()+ " "+kv.toString());
                	context.write(new Text(rule.toString()), kv);
                }
            }
    		writer.close();
		//} catch (Exception e) {
			//e.printStackTrace();
		//}
    }

	private Set<String> getChromosome(String line) {
		
		return new HashSet<String>(Arrays.asList(line.split("\\s")));
	}
	
	
}
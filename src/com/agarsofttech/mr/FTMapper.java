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
public class FTMapper extends Mapper<Object, Text, Text, PairOfFloatString>{

    private final static FloatWritable one = new FloatWritable(1);
    private final static PairOfFloatString kv = new PairOfFloatString();
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

    	//try(
    		Scanner scanner = new Scanner(value.toString());
    		//) {
    		while (scanner.hasNextLine()) {
                String line = scanner.nextLine();                
                String s = getChromosomeGene(line);         
                Set<String> chromosome = getChromosome(s);
                Set<List<Integer>> rules = Rule.getRules(chromosome);
                for(List<Integer> rule:rules){
                	//System.out.println("Rules " + rule.toString());
                     String k = key.toString();
                     kv.set(1, k);
                	context.write(new Text(rule.toString()), kv);
                }
            }
		//} catch (Exception e) {
			//e.printStackTrace();
		//}
    }
  //get chromosome remove fitness value.
    private static String getChromosomeGene(String k) {
    	String[] arr = k.split("   ");
    	String str = "";
    	if(arr.length>1)
    		str = arr[0].trim();
    	else
    		str = Util.ChromosomeString(k);
    	return str;
    }
    
	private Set<String> getChromosome(String line) {
		return new HashSet<String>(Arrays.asList(line.split("\\s")));
	}
	
	
}
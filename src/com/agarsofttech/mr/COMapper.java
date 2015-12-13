
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
public class COMapper extends Mapper<Object, Text, FloatWritable, Text>{

    private final static FloatWritable one = new FloatWritable(1);
    //private final static PairOfFloatString kv = new PairOfFloatString();
	//private Scanner scanner;
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {	
    	    Scanner scanner = new Scanner(value.toString());
    		String[] c = new String[5];
    		String[] c1 = null;
    		int i = 0;
    		while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //System.out.println("line" + line);
                //Text t1 = new Text(line);
                context.write(one,new Text(line));
                c[i] = line;
                i++;
    		}

    }

	private Set<String> getChromosome(String line) {
		return new HashSet<String>(Arrays.asList(line.split("\\s")));
	}
	
	
}

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
public class COMapper extends Mapper<Object, Text, Text, PairOfFloatString>{

    private final static FloatWritable one = new FloatWritable(1);
    private final static PairOfFloatString kv = new PairOfFloatString();
	//private Scanner scanner;
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {	
    	    Scanner scanner = new Scanner(value.toString());
    		String[] c = new String[5];
    		String[] c1 = null;
    		int i = 0;
    		while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("line" + line);
                c[i] = line;
                i++;
    		}
    		System.out.println("size " + i);
    		System.out.println("size c" + c.length);
    		//System.arraycopy(c, 0, c1, 0, i);
    		/*String[] chro = Util.crossover(c);
    		for	( i=0; i<chro.length;i++){
                Set<String> chromosome = getChromosome(chro[i]);
                Set<List<Integer>> rules = Rule.getRules(chromosome);
                for(List<Integer> rule:rules){
                	 System.out.println("Rules " + rule.toString());
                     String k = key.toString();
                     kv.set(1, k);
                	context.write(new Text(rule.toString()), kv);
                }
            }*/
		//} catch (Exception e) {
			//e.printStackTrace();
		//}
    }

	private Set<String> getChromosome(String line) {
		return new HashSet<String>(Arrays.asList(line.split("\\s")));
	}
	
	
}
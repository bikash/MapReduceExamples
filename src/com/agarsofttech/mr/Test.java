package com.agarsofttech.mr;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner ;
			scanner = new Scanner(new FileReader(args[0]));
    		while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Set<String> chromosome = getChromosome(line);
                Set<List<Integer>> rules = Rule.getRules(chromosome);
                for(List<Integer> rule:rules){
                	Collections.sort(rule);
                	System.out.println(rule+" "+1);
                }
            }

	}
	
	private static Set<String> getChromosome(String line) {
		return new HashSet<String>(Arrays.asList(line.split("\\s")));
	}
}

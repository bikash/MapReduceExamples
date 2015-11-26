package com.agarsofttech.mr;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rule {

	public static Set<List<Integer>> getRules(Set<String> chromosome) {
		return randomRules(chromosome);
	}

	private static Set<List<Integer>> randomRules(Set<String> chromosome) {
		String[] array = chromosome.toArray(new String[0]);
		Set<List<Integer>> rules = new HashSet<List<Integer>>();
		for(int i=2;i<array.length;i++){
			for(int j=1;j<array.length;j++){
				for(int k=0;k<array.length;k++){
					if(i==j||j==k||j==k)
						continue;
					rules.add(createRule(array,i,j,k));
				}
			}
		}
		return rules;
	}

	private static List<Integer> createRule(String[] array,int... arr) {
		List<Integer> rule = new ArrayList<Integer>();
		for(int i=0;i<arr.length;i++) {
			rule.add(Integer.parseInt(array[arr[i]]));
		}
		Collections.sort(rule);
		return rule;
	}

}

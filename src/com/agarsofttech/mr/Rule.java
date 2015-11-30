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
		
		//System.out.println(" Array length" + array.length);
		for(int i=2;i<array.length;i++){
			for(int j=1;j<array.length;j++){
				for(int k=0;k<array.length;k++){
					if(i==j||j==k||j==k)
						continue;
					List<Integer> rule = createRule(array,i,j,k);
					if(rule.toString()!="[]")
						rules.add(createRule(array,i,j,k));
	
				}
			}
		}

		return rules;
	}

	private static List<Integer> createRule(String[] array,int... arr) {
		List<Integer> rule = new ArrayList<Integer>();
		//System.out.println(" size -->" + arr.length);
		for(int i=0;i<arr.length;i++) {
			//System.out.println(" Value i -->" + array[arr[i]]);
			rule.add(Integer.parseInt(array[arr[i]]));
		}
		//exclude duplicate tuples from the list
		Collections.sort(rule);
		rule=excludeDupRule(rule);
		Collections.sort(rule);
		return rule;
	}
	
	//exclude duplicate tuples from the list
	private static List<Integer> excludeDupRule(List<Integer> rules) {
		List<Integer> newList = new ArrayList<Integer>(rules.size());
		System.out.println(" size -->" + rules.size());
		int counter = 0;
		for(int j=0;j<rules.size();j++){
			//for (Integer myInt1 : myInt) {
				System.out.println(" index1 -->" + rules.get(1) + " index 2 "+ rules.get(2));
				if(rules.get(1) != rules.get(2))
				{
					newList.add(rules.get(j)); 
				}
				else {
					newList.remove(rules.get(j)); 
				}
			}
		return newList;
	}

}

package com.jay.coding;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DuplicateCharacter {

	public static void main(String[] args) {

		Map<Character, Integer> map = new HashMap<>();
		
		String input = "jayanth kuth";
		
		char ch[] = input.toCharArray();
		
		for(char c : ch)
		{
			if(map.containsKey(c))
			{
				map.put(c, map.get(c)+1);
			}
			else {
				map.put(c, 1);
			}
		}
		
		for(Map.Entry<Character, Integer> m: map.entrySet())
		{
			if(m.getValue() > 1) System.out.println(m.getKey());
		} 
	}

}

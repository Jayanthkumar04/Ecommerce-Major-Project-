package com.jay.coding;

public class SecondHighest {

	public static int second(int arr[])
	{
		int firstMax = first(arr);
		int max = Integer.MIN_VALUE;
		
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i] > max && arr[i] != firstMax) max=arr[i];
		}
		
		return max;
	}
	
	public static int first(int arr[])
	{
       int max = Integer.MIN_VALUE;
		
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i] > max) max=arr[i];
		}
		
		return max;
	}
	public static void main(String[] args) {
	
		int arr[] = {10,36,56,19,1};
		
		System.out.println("higest num is "+second(arr));
		
	}

}

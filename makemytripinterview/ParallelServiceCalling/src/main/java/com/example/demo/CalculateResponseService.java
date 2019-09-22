package com.example.demo;

public class CalculateResponseService {
	
	// Simulated the problem using threads.
	public static void main(String[] args) throws InterruptedException
	{
		CalculateResponseService cr = new CalculateResponseService();
		System.out.println(cr.calculateServiceTime());
	}
	
	public long calculateServiceTime() throws InterruptedException {
		int n = 5;
		Thread[] t = new Thread[n];

		for (int i1 = 0; i1 < 5; i1++) {
			t[i1] = new Thread(new S1ServiceCalling());
			t[i1].start();
		}

		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 5; i++) {
			t[i].join();
		}

		for(int j =0; j< 3 ; j++)
		{
			Thread th = new Thread(new S2ServiceCalling());
			th.start();
			th.join();
		}
		
		Thread[] tsec = new Thread[4];
		
		for (int i1 = 0; i1 < 4; i1++) {
			tsec[i1] = new Thread(new S1ServiceCalling());
			tsec[i1].start(); // starting S3 parallel service;
		}

		
		for (int i = 0; i < 4; i++) {
			tsec[i].join();
		}
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		return totalTime;
	}
}

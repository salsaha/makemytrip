package com.example.demo;

public class S3CallingService implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			// Need to call the required URL from here.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}

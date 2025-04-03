package bai6;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MultiThreadFileMerger {
	 public static void main(String[] args) {
	        String[] inputFiles = {"D:\\week4\\src\\bai6\\input_1.txt", "D:\\week4\\src\\bai6\\input_2.txt"};
	        String outputFile = "D:\\week4\\src\\bai6\\output.txt";

	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
	            
	            Thread[] threads = new Thread[inputFiles.length];
	            
	            for (int i = 0; i < inputFiles.length; i++) {
	                threads[i] = new FileReaderThread(inputFiles[i], writer);
	                threads[i].start();
	            }

	            for (Thread thread : threads) {
	                thread.join();
	            }

	            System.out.println("Đã tổng hợp nội dung vào file: " + outputFile);
	        } catch (IOException | InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}	

package bai3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountLinesInFile {
    public static void main(String[] args) {
        String filePath = "D:\\week4\\src\\bai3\\input.txt";  

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int lineCount = 0;

            while ((reader.readLine()) != null) {
                lineCount++;
            }

            System.out.println("Số dòng trong file: " + lineCount);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.company;

import java.util.Random;
import java.util.Scanner;

public class SquareMatrixTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the matrix row length ( in the range of [2,12] ) : ");
        int n = Integer.parseInt(scanner.nextLine());
        while(n < 2 || n > 12)
        {
            System.out.println("Please enter number between 2 and 12");
            n = Integer.parseInt(scanner.nextLine());
        }
        Random rand = new Random();
        int [][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(9);
            }
        }
        SquareMatrix m = new SquareMatrix(matrix);
        System.out.println(m.toString());
        System.out.println("Biggest sum : " + m.findMaxSum());
        m.printAll();
    }
}

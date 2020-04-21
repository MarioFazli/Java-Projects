package com.company;

public class SquareMatrix {
    private int [][] dataArray;     // private variable


    // setter and getter
    public void setDataArray(int rows) {
        if(rows >= 2 && rows <= 12)
        {
            this.dataArray = new int[rows][rows];
        }
        else
        {
            System.err.println("Please enter number for matrix length between 2 and 12!");
            System.exit(1);
        }
    }

    public int[][] getDataArray() {
        return dataArray;
    }

    // constructors
    public SquareMatrix()
    {
        setDataArray(2);
    }

    public SquareMatrix(int rows)
    {
        setDataArray(rows);
    }


    public SquareMatrix(int [][] dataArray)
    {
        int rows = dataArray.length;
        int cols = dataArray[0].length;
        setDataArray(rows);     // it's a square matrix so square root of the length is the numbers of the rows

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.dataArray[i][j] = dataArray[i][j];
            }
        }
    }
    // print matrix
    public String toString()
    {
        String row = "";
        for (int i = 0; i < dataArray.length; i++) {
            for (int j = 0; j < dataArray[0].length; j++) {
                row += dataArray[i][j] + " ";
            }
            row += "\n";
        }
        return row;
    }

    public int findMaxSum()
    {
        int row = dataArray.length;
        int subMatrixCount = (row-1); // every matrix contains (row-1)^2 square 2x2 sub matrix
        int col = 0;        // variable representing the columns in a matrix

        int sum = 0;
        int biggestSum = 0;
        // since it is pointless to loop (row-1)^2 times we can just loop row-1 times and get the whole column
        // for example we start with 2x2 sub matrix from the first column and we compare it to the next 2x2 sub matrix
        // from the same column until the column's end is reached then start with the next one
        while(subMatrixCount > 0)
        {
            for (int i = 0; i < row-1; i++) {
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        if(i + k < row && col + l < row)
                        {
                            sum += dataArray[i+k][col+l];
                            // on the second iteration we will get the next element i+1 without changing i
                        }
                    }

                }
                if(biggestSum < sum)
                {
                    biggestSum = sum;
                }
                sum = 0;
            }

            col++;  // start point of the column will increase after we have gone through the whole column
            subMatrixCount--;
        }
        return biggestSum;
    }

    public void printAll()
    {
        int row = dataArray.length;
        int subMatrixCount = (row-1); // every matrix contains (row-1)^2 square 2x2 sub matrix
        int col = 0;

        int sum = 0;
        int biggestSum = findMaxSum();
        while(subMatrixCount > 0)
        {
            for (int i = 0; i < row-1; i++) {
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        if(i + k < row && col + l < row)
                        {
                            sum += dataArray[i+k][col+l];
                        }
                    }

                }
                if(biggestSum == sum)
                {
                    System.out.printf("[%d,%d]",i,col);
                }
                sum = 0;
            }

            col++;
            subMatrixCount--;
        }
    }

}

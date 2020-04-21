package com.example;

public class RouteCipher {
    private int key;        // private variable to indicate the number of columns in the matrix

    public void setKey(int key) {
        if(key != 0)
        {
            this.key = key;
        }
        else
        {
            // key == 0 so we tell the caller that this is an invalid input and exit the program so he can enter a valid input
            System.err.println("You entered 0 as a key! Key cannot be of 0 value! Please enter valid input!");
            System.exit(1);
        }
    } // end of setKey()

    public int getKey() {
        return key;
    }

    public RouteCipher(int key) {
        setKey(key);
    }

    public String encrypt(String plainText) {
        char[] plaintextArr = returnUsableArr(plainText);

        int absoluteValue = Math.abs(getKey());

        int rows = (int) Math.ceil(plaintextArr.length / (absoluteValue * 1.0));         // Math.ceil(double a) => to convert int to double it should be multiplied by 1.0;
        char[][] matrix = new char[rows][absoluteValue];        // the grid in which the input will be inserted

        int textIndex = -1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < absoluteValue; j++) {
                // if the input's length is smaller than the matrix it is filled with 'X'
                textIndex++;
                if (textIndex >= plaintextArr.length) {
                    matrix[i][j] = 'X';
                } else {
                    matrix[i][j] = plaintextArr[textIndex];
                }

            }
        }
        textIndex = 0;                  // reusing the index
        plaintextArr = new char[absoluteValue * rows]; // the length of the encrypted array is the same as the length of the matrix

        if (getKey() > 0) {
            // if key > 0 we start from the upper left corner of the rectangle matrix

            int rowStart=0,colStart=0,rowEnd=rows-1,colEnd=absoluteValue-1; // variables indicating the start and end
            int i;

            // essentially saying that we should keep looping until the smaller variable (rows or absolute value) reaches
            // half of it's value -> rows/2 or absoluteValue/2 (if the number is odd it will be (rows/2)+1)
            while(rowStart<=rowEnd && colStart<= colEnd)
            {
                // going down a column
                for(i=rowStart;i<=rowEnd;i++)
                {
                    plaintextArr[textIndex++] = matrix[i][colStart];
                }



                // going horizontally right
                for(i=colStart+1 ;i<=colEnd;i++)
                {
                    plaintextArr[textIndex++] = matrix[rowEnd][i];
                }


                if(colStart < colEnd)
                {
                    // going up a column
                    for(i=rowEnd-1;i>=rowStart;i--)
                    {
                        plaintextArr[textIndex++] =matrix[i][colEnd];
                    }

                }

                if(rowStart < rowEnd)
                {
                    // going horizontally left
                    for(i=colEnd-1;i>colStart;i--)
                    {
                        plaintextArr[textIndex++] =matrix[rowStart][i];
                    }

                }

                rowEnd--;
                colEnd--;
                colStart++;
                rowStart++;
            } // end of while loop

        } // end of if()

        else
        {
            // if key < 0 we start from the lowest right corner of the rectangle matrix

            int rowStart= rows-1,colStart= absoluteValue-1,rowEnd= 0,colEnd= 0; // variables indicating the start and end
            int i;


            while(rowStart>=rowEnd && colStart>= colEnd)
            {
                // going up a column
                for(i=rowStart;i>=rowEnd;i--)
                {
                    plaintextArr[textIndex++] =matrix[i][colStart];
                }

                // going horizontally left
                for(i=colStart-1;i>= colEnd;i--)
                {
                    plaintextArr[textIndex++] =matrix[rowEnd][i];
                }

                if(colEnd < colStart)
                {
                    // going down a column
                    for(i=rowEnd + 1;i<= rowStart;i++)
                    {
                        plaintextArr[textIndex++] = matrix[i][colEnd];
                    }
                }

                if(rowEnd < colStart)
                {
                    // going horizontally right
                    for(i=colEnd + 1;i<= colStart -1;i++)
                    {
                        plaintextArr[textIndex++] = matrix[rowStart][i];
                    }
                }

                rowStart--;
                colStart--;
                rowEnd++;
                colEnd++;
            } // end of while loop
        }
        return new String(plaintextArr);
    }// end of encrypt


    public String decrypt(String encryptedText) {
        char[] plaintextArr = returnUsableArr(encryptedText);
        int absoluteValue = Math.abs(getKey());
        int rows = (int) Math.ceil(plaintextArr.length / (absoluteValue * 1.0));// Math.ceil(double a) -> *1.0
        char[][] matrix = new char[rows][absoluteValue];

        int j = 0, k = 0, l = 0, m = 0;                                     // index indicators
        int jCounter = 0, kCounter = 0, lCounter = 0, mCounter = 0;         // using counters for the same reason
        int textIndex = 0;
                                /*the idea of decryption is to enter the symbols from the encrypted string the same way
                                 it was encrypted. For that we need the key */
        if (getKey() > 0) {
            // if key > 0 the program should start entering symbols from the upper left corner
            int rowStart=0,colStart=0,rowEnd=rows-1,colEnd=absoluteValue-1; // variables indicating the start and end
            int i;

            // essentially saying that we should keep looping until the smaller variable (rows or absolute value) reaches
            // half of it's value -> rows/2 or absoluteValue/2 (if the number is odd it will be (rows/2)+1)
            while(rowStart<=rowEnd && colStart<= colEnd)
            {
                // going down a column
                for(i=rowStart;i<=rowEnd;i++)
                {
                     matrix[i][colStart] = plaintextArr[textIndex++];
                }



                // going horizontally right
                for(i=colStart+1 ;i<=colEnd;i++)
                {
                    matrix[rowEnd][i] = plaintextArr[textIndex++] ;
                }

                // if it is the final cycle and if there are no more columns it should not go in the loop
                if(colStart < colEnd)
                {
                    // going up a column
                    for(i=rowEnd-1;i>=rowStart;i--)
                    {
                        matrix[i][colEnd] =  plaintextArr[textIndex++];
                    }

                }
                // if it is the final cycle and if there are no more rows to read it should not go in the loop
                if(rowStart < rowEnd)
                {
                    // going horizontally left
                    for(i=colEnd-1;i>colStart;i--)
                    {
                         matrix[rowStart][i] = plaintextArr[textIndex++];
                    }

                }

                rowEnd--;
                colEnd--;
                colStart++;
                rowStart++;
            } // end of while loop

        } else if (getKey() < 0) {
            // if key < 0 the program should start entering symbols from the lower right corner

            // this is essentially the same as the above but reversed - since we start from the lower right corner
            // rowStart starts from there and ends in the beginning so they are reversed
            int rowStart= rows-1,colStart= absoluteValue-1,rowEnd= 0,colEnd= 0; // variables indicating the start and end
            int i;

            while(rowStart>=rowEnd && colStart>= colEnd)
            {
                // going up a column starting from lower right corner
                for(i=rowStart;i>=rowEnd;i--)
                {
                     matrix[i][colStart] = plaintextArr[textIndex++];
                }

                // going horizontally left
                for(i=colStart-1;i>= colEnd;i--)
                {
                     matrix[rowEnd][i] = plaintextArr[textIndex++];
                }
                // if it is the final cycle and if there are no more columns it should not go in the loop
                if(colEnd < colStart)
                {
                    // going down a column
                    for(i=rowEnd + 1;i<= rowStart;i++)
                    {
                         matrix[i][colEnd] = plaintextArr[textIndex++];
                    }
                }
                // if it is the final cycle and if there are no more rows to read it should not go in the loop
                if(rowEnd < colStart)
                {
                    // going horizontally right
                    for(i=colEnd + 1;i<= colStart -1;i++)
                    {
                       matrix[rowStart][i] = plaintextArr[textIndex++];
                    }
                }

                rowStart--;
                colStart--;
                rowEnd++;
                colEnd++;
            } // end of while loop
        }

        int countX = 0;
        int index = 0;
        char[] decrypted = new char[(rows * absoluteValue)-countX];      // string to return the decrypted message

        for (int i = 0; i < rows; i++) {
            for (int n = 0; n < absoluteValue - countX; n++) {
                if(i == rows-1)
                {
                    while(matrix[i][absoluteValue-1-countX] == 'X')
                    {
                        // this won't work in cases such as the last word being SIX or others ending with X
                        countX++;
                    }
                }
                decrypted[index++] = matrix[i][n];
            }
        }
        return new String(decrypted);
    }


    private char[] returnUsableArr(String text)
    {
        final int lengthOfPlainText = text.length();
        // method that returns a char[] with all of the inputted string
        // elements that are letters (without any unnecessary symbols)
        char[] textArr = text.toCharArray();    // converting the String into char[]
        char[] newArr = new char[textArr.length]; // making a copy of the array which will hold the elements without unnecessary symbols like , or spaces
        int counter = 0;                            // acts as a index helper
        for (char elem : textArr) {
            if (elem >= 'a' && elem <= 'z' || elem >= 'A' && elem <= 'Z') {
                newArr[counter++] = elem;
            }
        }
        textArr = new char[counter];            // reusing the first array in order to create an array that does not contain empty indexes
        for (int i = 0; i < counter; i++) {
            textArr[i] = newArr[i];
        }
        return textArr;
    }// end of returnUsableArr()

    // to have toString method i should have private variables encryptedText and decryptedText
    // otherwise I could not print them because I don't know what the caller will give as an argument
    // that is why I will print that in the main method in RouteCipherTest class
}

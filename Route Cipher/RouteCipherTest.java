package com.example;

public class RouteCipherTest {
    public static void main(String[] args) {
        String text = "ABORT THE MISSION, YOU HAVE BEEN SPOTTED";
        int[] key = new int[] {2,3,4,5,6,7,8,9};
        for (int i = 0; i < key.length; i++) {
            RouteCipher test = new RouteCipher(key[i]);
            System.out.printf("Encryption of %s using key = %d. \n Encrypted Text : %s \n\n",text,test.getKey(), test.encrypt(text));
            System.out.printf(" Decryption of %s using key = %d \n Decrypted text : %s \n\n",test.encrypt(text),test.getKey(), test.decrypt(test.encrypt(text)));
        }
    }
}

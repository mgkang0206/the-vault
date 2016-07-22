package com.genassembly.dotdashdot.crackmysafe2;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by Mauve3 on 7/22/16.
 */
public class MyGuesser {

    char[] alphabet = new char[52];
    private SaltHolder salts;

    public MyGuesser(SaltHolder salts){
        this.salts = salts;
        for(int i = 65; i < (65+26); i++){
            alphabet[i-65] = (char) i;
        }

        for(int i = 97; i < (97+26); i++){
            alphabet[i-97+26] = (char) i;
        }
    }

    public String guess(int[] tumbler){
        StringBuilder guess = new StringBuilder();
        for (int i = 0; i < tumbler.length; i++) {
            guess.append(alphabet[tumbler[i]]);
        }
        return guess.toString();
    }

    public int[] rotate(int[] tumbler){
        int i = tumbler.length - 1;
        while (true){
            tumbler[i] ++;
            if (tumbler[i] > alphabet.length - 1) {
                tumbler[i] -= alphabet.length;
                i--;
            } else {
                break;
            }
        }
        return tumbler;
    }

    public String hashPassword(int tag, String guess){
        System.out.printf("Length: " + salts.getLength());
        return salts.printPassword(tag, guess);
    }

    public void printAlphabet(){
        for(int i = 0; i < alphabet.length; i++){
            System.out.print(alphabet[i]);
        }
        System.out.println();
    }
}

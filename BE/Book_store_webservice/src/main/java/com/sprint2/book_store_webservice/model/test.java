package com.sprint2.book_store_webservice.model;

public class test {
    public static void main(String[] args) {
        countCharacter("aaabfhccndhe");
    }

    public static void countCharacter(String string){
        while (string.length() != 0){
            String character = String.valueOf(string.charAt(0));
            if (string.length() != 0){
                int count = 0;
                for (int j = 0; j < string.length(); j++) {
                    if (character.equals(String.valueOf(string.charAt(j)))){
                        count++;
                    }
                }
                string = string.replace(character,"");
                System.out.println(character + ": " + count);
            }
        }
    }
}

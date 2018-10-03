package com.example.lib;

public class MyClass {

    static enum En {
        AA;

    };

    public static void main(String[] args) {

        En a = En.AA;
//
        System.out.println(En.values()[0].name());

    }

}

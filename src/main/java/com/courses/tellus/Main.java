package com.courses.tellus;

import com.courses.tellus.airoport.Airoport;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        Airoport airoport = new Airoport(reader);
        airoport.start();
    }
}

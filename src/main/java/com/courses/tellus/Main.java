package main.java.com.courses.tellus;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        Airoport airoport = new Airoport(reader);
        airoport.start();
    }
}

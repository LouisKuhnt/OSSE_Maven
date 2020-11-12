package de.hfu;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Hello World!");
            System.out.println("Eingabe eines Wortes!");
            String eingabe = scanner.nextLine();

            for (char c : eingabe.toCharArray()) {
                if (Character.isLowerCase(c)) {
                    System.out.println(Character.toUpperCase(c));
                } else {
                    System.out.println(c);
                }
            }
        }
    }
}

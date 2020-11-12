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
        	//Erstellt Scanner, um die Konsole auszulesen
            Scanner scanner = new Scanner(System.in);
            System.out.println("Hello World!");
            System.out.println("Eingabe eines Wortes!");
            //Holt Daten aus der Konsole und schreibt Sie in eine Variable
            String eingabe = scanner.nextLine();

            //Durchläuft den String um jeden Charakter
            for (char c : eingabe.toCharArray()) {
            	//Wandelt um in Grossbuchstaben, wenn nötig
                if (Character.isLowerCase(c)) {
                    System.out.println(Character.toUpperCase(c));
                } else {
                    System.out.println(c);
                }
            }
        }
    }
}

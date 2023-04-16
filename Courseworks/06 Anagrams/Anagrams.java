/* Thanapoom Phatthanaphan
 * CS 570-PA
 * Homework 6
 * 11 Apr 2023
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class Anagrams {

    final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
    Map<Character,Integer> letterTable;
    Map<Long, ArrayList<String>> anagramTable;

    // Constructor
    public Anagrams() {

        buildLetterTable();
        anagramTable = new HashMap<Long, ArrayList<String>>();
    }

    // This method is invoked by the constructor for the class Anagrams and build the hash table letterTable
    private void buildLetterTable() {

        letterTable = new HashMap<Character, Integer>();
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < alpha.length(); i++) {
            letterTable.put(alpha.charAt(i), primes[i]);
        }
    }

    /* This method computes the hash code of the string s passed as argument,
     * and should add this word to the hash table anagramTable.
     */
    private void addWord(String s) {
        Long hashCode = myHashCode(s);
        if(anagramTable.containsKey(hashCode)) {
            anagramTable.get(hashCode).add(s);
        }
        else {
            ArrayList<String> anagramList = new ArrayList<String>();
            anagramList.add(s);
            anagramTable.put(hashCode, anagramList);
        }
    }

    /* This method computes hash code for given string s, using the fundamental theorem of arithmetic),
    * also called the unique factorization theorem or the unique-prime-factorization theorem, 
    * states that every integer greater than 0 either is prime itself or 
    * is the product of a unique combination of prime numbers, each to some power.
    */
    private Long myHashCode(String s) {
        long keyOfString = 1;
        for (int i = 0; i < s.length(); i++) {
            keyOfString *= letterTable.get(s.charAt(i));
        }
        return keyOfString;
    }

    // The method receives the name of a text file containing words, one per line, and builds the hash table anagramTable.
    private void processFile(String s) throws IOException { 
        FileInputStream fstream = new FileInputStream(s);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream)); 
        String strLine;
        while ((strLine = br.readLine()) != null) { 
            this.addWord(strLine);
        }
        br.close ();
    }

    // This method returns the entries in the anagramTable that have the largest number of anagrams.
    private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {
        ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
        int maxSize = 0;

        // find the maximum size of max anagram
        for (Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()) {
            if (entry.getValue().size() > maxSize) {
                maxSize = entry.getValue().size();
            }
        }

        // add all entries with max anagrams into maxEntries
        for (Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()) {
            if (entry.getValue().size() == maxSize) {
                maxEntries.add(entry);
            }
        }
        return maxEntries;
    }

    public static void main(String[] args) {
        Anagrams a = new Anagrams ();
        final long startTime = System.nanoTime ();
        try {
            a.processFile("words_alpha.txt"); 
        } 
        catch(IOException e1) {
            e1.printStackTrace ();
        }
        ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
        final long estimatedTime = System.nanoTime() - startTime;
        final double seconds = ((double) estimatedTime / 1000000000);

        // print the elapsed time
        System.out.println("Elapsed Time : " + seconds);

        // print all keys of max anagrams in the hashmap
        System.out.println("Key of max anagrams : ");
        for (int i = 0; i < maxEntries.size(); i++) {
            System.out.println(maxEntries.get(i).getKey());
        }

        // print all lists of max anagrams in the hashmap 
        System.out.println("List of max anagrams : ");
        for (int i = 0; i < maxEntries.size(); i++) {
            System.out.println(maxEntries.get(i).getValue());
        }

        // print the number of values in the list of max anagrams
        System.out.println("Length of list of max anagrams : " + maxEntries.get(0).getValue().size());
    }
}
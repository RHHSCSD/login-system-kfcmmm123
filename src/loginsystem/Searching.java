/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author alan
 */
public class Searching {

    /**
     *
     * @param target
     * @param arr
     * @return
     */
    public static int binarySearch(String target, String[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int result = target.compareTo(arr[mid]);

            // Check if the target is present at mid
            if (result == 0) {
                return mid; // Target found
            }

            // If the target is greater, ignore the left half
            if (result > 0) {
                left = mid + 1;
            }
            // If the target is smaller, ignore the right half
            else {
                right = mid - 1;
            }
        }

        // Target not present in the array
        return -1;
    }

    /**
     *
     * @param term
     * @param list
     * @return
     * @throws FileNotFoundException
     */
    public static int seqSearch(String term, String[] list) throws FileNotFoundException{
        //loop through each term in the list
        for (int i = 0; i < list.length; i++){
            if (list[i].equals(term)){
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param args
     * @throws NoSuchAlgorithmException
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException {
        String[] list = new String[3930];
        File f = new File("dictionary.txt");
        try (Scanner sc = new Scanner(f)) {
            int index = 0;
            while (sc.hasNextLine()) {
                String word = sc.nextLine().trim();
                list[index] = word;
                index++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            throw e; 
        }
        String target = "date";
        int result = binarySearch(target, list);
        int result2 = seqSearch(target, list);

        if (result == -1) {
            System.out.println(target + " not found in the list.");
        } else {
            System.out.println(target + " found at index " + result);
        }    
    }    

}

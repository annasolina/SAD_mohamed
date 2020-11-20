/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Segon;

import java.io.*;

/**
 *
 * @author Anna
 */

public class TestReadLine {
    public static void main(String[] args) {
        BufferedReader in = new EditableBufferedReader(new InputStreamReader(System.in));
        String string = null;
        try {
            string = in.readLine(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nInput is: " + string);
    }
}  


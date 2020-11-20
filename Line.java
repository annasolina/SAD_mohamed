/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Segon;

import java.io.*;
import static java.lang.Boolean.FALSE;
import java.util.Observable;

/**
 *
 * @author Anna_Mohamed
 */
public class Line extends Observable{
    public static int maxLength = 1200;

    public static final int SEC_RIGHT = 2001;
    public static final int SEC_LEFT = 2002;
    public static final int SEC_HOME = 2003;
    public static final int SEC_FIN = 2004;
    public static final int SEC_INSERT = 2005;
    public static final int SEC_DELETE = 2006;
    public static final int SEC_CHARACTER = 2007;

    private StringBuffer buffer;
    private int pos;
    private boolean insert = false;
    private boolean home = true;

    
    public Line(){
        this.buffer = new StringBuffer(this.maxLength);
        this.pos = 0;
 
    }
   
     public boolean isIns() { //if true, inserts. If false no esborra
        return insert;
    }
        

    public int getPos(){
        return this.pos;
    }

    public void setPos(int posicio){
        if (posicio == 0)
            this.home = true;
        this.pos = posicio;
    }

    public String getLine(){
        return this.buffer.toString();
    }

    public void add(char c){
        if(this.insert){
	    if(pos < buffer.length()){
            	this.buffer.deleteCharAt(this.pos); 	
		}	  
        }
	this.buffer.insert(this.pos, c);
	this.pos++;
        this.setChanged();
        this.notifyObservers(SEC_CHARACTER);
        
    }
    
    public void right(){
        if(pos < buffer.length()){
            pos++;
        }
        this.setChanged();
        this.notifyObservers(SEC_RIGHT);
    }
    
    public void left(){
        if(pos > 0){
            pos--;
        }
        if (this.pos == 0){
            this.home = true;
        }
        this.setChanged();
        this.notifyObservers(SEC_LEFT);
    }
    
    
    public void home(){
        pos = 0;
        this.setChanged();
        this.notifyObservers(SEC_HOME);
    }
    
    public void end(){
        pos = buffer.length();
        this.setChanged();
        this.notifyObservers(SEC_FIN);        
    }
    
    public void supr(){
        if(pos < buffer.length()){
            buffer.deleteCharAt(pos);
        }
        this.setChanged();
        this.notifyObservers(SEC_DELETE);
    }
    
    public void bksp(){
        if(pos > 0){
            pos--;
            buffer.deleteCharAt(pos);
        }
        this.setChanged();
        this.notifyObservers(Segon.EditableBufferedReader.BACKSPACE);
    }

   public void commuteInsert(){
        //this.insert = !this.insert;
        //ho podriem fer així, pero no s'enten del tot. 
        //es pot posar així més endevant
        this.insert = !this.insert;
        this.setChanged();
        this.notifyObservers(SEC_INSERT);
    }
    
     public char getchar(int s){
        return (char)s;
    } 
  
    public String getDisplayString() {
        StringBuilder displayString = new StringBuilder();
        displayString.append((char) 27);
        displayString.append("[2K");
        displayString.append('\r');
        displayString.append(this.buffer.toString());
        displayString.append(' ');
        displayString.append("\033[" + (1 + this.buffer.length() - pos) + "D");
        return displayString.toString();
    }
}

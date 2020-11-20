/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Segon;

import Primer.Line;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
/**
 *
 * @author Anna
 */
public class EditableBufferedReader extends BufferedReader{
   static final int ESCAPE = 27;
    static final int EDICIO = 91;
    static final int RIGHT = 67;
    static final int LEFT = 68;
    static final int HOME = 72;
    static final int END = 70;
    static final int INSERT = 50;
    static final int DELETE = 51;
    static final int BACKSPACE = 127;
    static final int TILDE = 126;
    static final int ENTER = 13; // Es necessita per Readline per acabar de llegir la line
    
    private Line l;
    //constructor
    public EditableBufferedReader(Reader r){
        super(r);
        this.l = new Line();        
    }

 
    //Mètodes
    public void setRaw(){
        //Primero debo utilizar el comando  'stty -echo raw' en la terminal
        //para que se ejecute en un terminal utilizo bin/sh
        //Read commands from the command_string operand
        //     instead of from the standard input.  Special
        //     parameter 0 will be set from the command_name op‐
        //     erand and the positional parameters ($1, $2, etc.)
        //     set from the remaining argument operands.
	try{
        String[] cadena = {"/bin/sh", "-c", "stty raw </dev/tty"};
        //confirmar que se ha podido pasar!
            Runtime.getRuntime().exec(cadena).waitFor();
        }catch(Exception e){
                System.out.println("Not put Terminal in raw mode");
        }
            
    }
    
    public void unsetRaw(){
        //passa la consola de mode raw a mode cooked.
        //lo mismo que el setraw
	try{
        String[] cadena = {"/bin/sh", "-c", "stty sane </dev/tty"};
        //confirmar que se ha podido pasar!
            Runtime.getRuntime().exec(cadena).waitFor();
        }catch(Exception e){
            System.out.println("Not put Terminal in cooked mode");
        }
    }
    
    
    
    public int read() throws IOException{
        this.setRaw();    
        int sim = super.read();	
    
        try{
	    
		    if(sim == Segon.EditableBufferedReader.ESCAPE){
			sim = super.read();
			if(sim == Segon.EditableBufferedReader.EDICIO){
			    sim = super.read();
			    switch (sim){
				case Segon.EditableBufferedReader.RIGHT:
				    sim = Segon.EditableBufferedReader.RIGHT;
				    break;
				case Segon.EditableBufferedReader.LEFT:
				    sim = Segon.EditableBufferedReader.LEFT;
				    break;
				case Segon.EditableBufferedReader.HOME:
				    sim = Segon.EditableBufferedReader.HOME;
				    break;
				case Segon.EditableBufferedReader.END:
				    sim = Segon.EditableBufferedReader.END;
				    break;
				case Segon.EditableBufferedReader.INSERT:
				    sim = this.read();
				    if (sim == Segon.EditableBufferedReader.TILDE)
				        sim = Segon.EditableBufferedReader.INSERT;
				    else
				        sim = -1;
				    break;
				case Segon.EditableBufferedReader.DELETE:
				    sim = this.read();
				    if (sim == Segon.EditableBufferedReader.TILDE)
				        sim = Segon.EditableBufferedReader.DELETE;
				    else
				        sim = -1;
				    break;
				case Segon.EditableBufferedReader.BACKSPACE:
				    sim = Segon.EditableBufferedReader.BACKSPACE;
				    break;
				
			    }  
			   
			}else{
			    sim =  Segon.EditableBufferedReader.ESCAPE;
			}         
		    }
	    }catch (Exception e) {

	    } finally {
	    this.unsetRaw();
	}
	
        return sim;
    }
    
    @Override
    public String readLine() throws IOException{
        
	
        int i = -1;
        
	try{
		i=this.read();
        
		while(i!=Segon.EditableBufferedReader.ENTER){       
		    		
		    switch(i)
		    {       
		        case Segon.EditableBufferedReader.LEFT:
		            this.l.left();
		            break;
		            
		        case Segon.EditableBufferedReader.RIGHT:
		            this.l.right();
		            break;
		            
		        case Segon.EditableBufferedReader.END:
		            this.l.end();
		            break;
		            
		        case Segon.EditableBufferedReader.HOME:
		            this.l.home();
		            break;
		            
		        case Segon.EditableBufferedReader.INSERT:
		            this.l.commuteInsert();
		            break;
		            
		        case Segon.EditableBufferedReader.DELETE:
		            this.l.supr();
		            break;
		            
		        case Segon.EditableBufferedReader.BACKSPACE:
		            this.l.bksp();
		            break;
		            
		        default:
		            this.l.add((char)i);
			    
		    }	      
//		    System.out.print(l.getchar(i));	
		    System.out.print(l.getDisplayString());
		    i=this.read();	
		   
		}
        }catch (IOException e) {
            e.printStackTrace();
        } 

        return this.l.getLine();
    } 
}

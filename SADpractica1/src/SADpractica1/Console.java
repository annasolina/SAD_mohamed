/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SADpractica1;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Anna
 */
/*
public class Console implements Observer{
    
    private Line l;
    
    public Console(Line l){
        this.l = l;
    }
    
    public void left(){ //move cursor one pos left
        System.out.print((char)27);
        System.out.print((char)91);
        System.out.print((char)68);
    }

    private void left(int i){ //move cursor i pos left
        for (int j = 0; j < i; j++) {
            this.left();
        }
    }
    
    public void right(){ //move cursor one pos right
        System.out.print((char)27);
        System.out.print((char)91);
        System.out.print((char)67);
    }
   
    private void right(int i){ //move cursor i pos right
        for (int j = 0; j < i; j++) {
            this.right();
        }
    }
    
    public void supr() {
        System.out.print("\033[P");
    }

    public void bksp() {
        
            //moure esquerra
            System.out.print((char)27);
            System.out.print((char)91);
            System.out.print((char)68);
            //eliminar i moure caracters
            System.out.print("\033[P");
            //actualitzar el buffer        
    }
    
}
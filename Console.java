/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Segon;

import java.util.Observable;
import java.util.Observer;
/**
 *
 * @author Anna
 */
public class Console implements Observer{
    private Line l;
    
    public Console(Line l){
        this.l = l;
    }
    public void update(Observable obs, Object obj){
        int var = (int)obj;
        switch(var){
            case Segon.Line.SEC_CHARACTER:
                boolean aux = this.l.isIns();
                //Falten coses
            break;
            
            case Segon.Line.SEC_RIGHT:
                System.out.print("\033[C");
            break;
            
            case Segon.Line.SEC_LEFT:
                System.out.print("\033[D");
            break;
            
            case Segon.Line.SEC_HOME:
                int pos = l.getPos() + 1;
                this.updateCursor(this.l.getPos());
                //System.out.print(Keys.ANSI_HOME);
                
            break;
            
            case Segon.Line.SEC_FIN:
                this.updateCursor(this.l.getPos());
                System.out.print("\033[F");
            break;
            
            case Segon.EditableBufferedReader.BACKSPACE:
                System.out.print("\b");
            break;
            
            case Segon.Line.SEC_DELETE:
                System.out.print("\033[P");
            break;
            
            // case Keys.ENTER:
            //     this.unsetRaw();
            // break;
            
            case Segon.Line.SEC_INSERT:
                System.out.print("\033[2~");
            break;
            default:
            break;
        }
    }
    public void updateCursor(int cursorPos){
        int aux = cursorPos + 1;
        System.out.print("\033["+aux+"G");
    }
}

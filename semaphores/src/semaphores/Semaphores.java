/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaphores;

import java.util.concurrent.Semaphore;

/**
 *
 * @author I Sidhartha Kumar
 */
public class Semaphores {
    int n;
    static Semaphore[] semaphore;
    
    Semaphores(int num){
        n=num;
        semaphore=new Semaphore[n];
        for(Semaphore s:semaphore){
            s=new Semaphore(1);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Semaphores s=new Semaphores(3);
        s.semaphore[0].acquire();
        System.out.println(s.semaphore[2]);
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testqueue;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author I Sidhartha Kumar
 */
public class TestQueue {
    Queue<Integer> queue;
    TestQueue(){
       queue =new LinkedList<>();
    }
    
    void addElement(int a){
        queue.add(a);
        System.out.println("Element "+a+" added.");
    }
    
    void popElement(){
       System.out.println("Head is "+queue.poll()); 
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner text=new Scanner(System.in);
        System.out.println("Enter element to add");
        int x=text.nextInt();
        TestQueue q1=new TestQueue();
        q1.addElement(x);
        System.out.println("Enter element to add");
        int x1=text.nextInt();
        q1.addElement(x1);
        q1.popElement();
        q1.popElement();
        
    }
    
}

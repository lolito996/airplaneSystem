package model;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {
    public Queue caseSetup1(){
        Queue<String,?> queue = new Queue<>();
        return queue;
    }
    public Queue caseSetup2(){
        Queue<String,String> queue = new Queue<>();
        queue.enqueue("Object","key");
        return queue;
    }
    //Test for empty Queue
    @Test
    public void testIsEmpty(){
        boolean flag = false;
        Queue<String,?> queue = caseSetup1();
        if(queue.isEmpty()){
            flag = true;
        }
        assertTrue(flag);
    }
    @Test
    public void testIsNotEmpty(){
        boolean flag = false;
        Queue<String,String> queue = caseSetup1();
        queue.enqueue("Element","key");
        if(!queue.isEmpty()){
            flag = true;
        }
        assertTrue(flag);
    }
    @Test
    public void testDequeueElement(){
        boolean flag = false;
        Queue<String,String> queue = caseSetup2();
        queue.dequeue();
        if(queue.isEmpty()){
            flag = true;
        }
        assertTrue(flag);
    }
    @Test
    public void testEnqueueElement(){
        boolean flag = false;
        Queue<String,String> queue = caseSetup2();
        queue.enqueue("ObjectB","keyB");
        try{
            String message1 = queue.dequeue();
            String message2 = queue.dequeue();
            if(message1.equals("Object") && message2.equals("ObjectB")){
                flag = true;
            }
        }catch(Exception ex){
            flag = false;
        }
        assertTrue(flag);
    }

}
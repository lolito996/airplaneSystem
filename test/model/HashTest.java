package model;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashTest {
    private HashTable caseSetup1(){
        HashTable<String,Integer> table = new HashTable<>();
        return table;
    }
    private HashTable caseSetup2(){
        HashTable<?,?> table = new HashTable<>();
        return table;
    }
    private HashTable caseSetup3(){
        HashTable<String,String> table = new HashTable<>();
        table.add("Object1","1");
        table.add("Object1","2");
        table.add("Object1","3");
        return table;
    }
    //Test for inserting an element.
    @Test
    public void testInsertion(){
        boolean flag = false;
        HashTable<String,Integer> table = caseSetup1();
        table.add("2",2);
        if(table.search(27).equals("27")){
            flag = true;
        }
        assertTrue(flag);
    }
    //Test of inserting different type of elements in the table.
    @Test
    public void testGenericTable(){
        boolean flag = true;
        HashTable table = caseSetup2();
        try{
            table.add(0.24,"String");
            table.add(false,2);
            table.add("String",true);
        }catch(Exception ex){
            flag = false;
        }
        assertTrue(flag);
    }
    //test to search a non-existent element in the table.
    @Test
    public void testNotFoundElement(){
        boolean flag = true;
        HashTable table = caseSetup3();
        String str = (String)table.search("Not_Existent_Key");
        if(str != null){
            flag = false;
        }
        assertTrue(flag);
    }
    @Test
    public void testFoundElement(){
        boolean flag = false;
        HashTable table = caseSetup3();
        String str1 = (String)table.search("1");
        String str2 = (String)table.search("2");
        String str3 = (String)table.search("3");
        if(str1.equals("Element1") && str2.equals("Element2") && str3.equals("Element3")){
            flag = true;
        }
        assertTrue(flag);
    }

}
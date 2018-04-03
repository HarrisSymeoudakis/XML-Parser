package gr.epp.delme;

/**
 *
 * @author Administrator
 */
public class Main {

    public Main() {
        MyButton myButton1 = new MyButton();
        
        myButton1.addListener( new MyJList()  );
        myButton1.addListener( new MyXYZ() );
        myButton1.addListener( new MyXYZ() );
        myButton1.addListener( new MyJList()  );
        myButton1.addListener( new MyXYZ() );
        myButton1.addListener( new MyJList()  );
        myButton1.addListener( new MyXYZ() );
        myButton1.addListener( new MyXYZ() );
        
        myButton1.addListener( new MyXYZZZZZ() );
        
    }
    public static void main(String[] args) {
        new Main();
    }
    
}

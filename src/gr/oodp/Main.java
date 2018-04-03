package gr.oodp;

/**
 *
 * @author Administrator
 */
public class Main {

    public static void main(String[] args) {
        MyWindow1 mw1 = new MyWindow1();//t0
        MyWindow2 mw2 = new MyWindow2();
        MyWindow3 mw3 = new MyWindow3();

        mw1.addStatusListener(mw2);
        mw1.addStatusListener(mw3);
        
        mw2.addStatusListener(mw1);
        mw2.addStatusListener(mw3);
        
        mw3.addStatusListener(mw1);
        mw3.addStatusListener(mw2);
    }

}

package gr.epp.delme;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class MyButton {
    private ArrayList<CommonButtonListenerAPI> listeners;

    public MyButton() {
        listeners = new ArrayList<CommonButtonListenerAPI>();
    }
    
    public void addListener(CommonButtonListenerAPI commonButtonAPIListener){
        listeners.add(commonButtonAPIListener);
    }
    
    /**
     * 
     * fireEventType1();
     * 
     */
    private void fireEventType1(){
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).actionType1Performed();
        }
    }
    private void fireEventType2(){}
    private void fireEventType3(){}
    private void fireEventType4(){}
}

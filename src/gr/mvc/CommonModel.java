package gr.mvc;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class CommonModel {
    
    private boolean isPressed;
    private ArrayList<StatusListener> listeners;
    
    public CommonModel() {
        listeners = new ArrayList<StatusListener>();
    }
    
    public void setPressed(StatusListener evtsrc, boolean isPressed) {
        this.isPressed = isPressed;
        for (int i = 0; i < listeners.size(); i++) {
            if(listeners.get(i)!= evtsrc){
                listeners.get(i).statusChangedEvent(isPressed);
            }
        }
    }
    
    public boolean isPressed() {
        return isPressed;
    }
    
    public void addView(StatusListener statusListener) {
        statusListener.setModel(this);
        listeners.add(statusListener);
    }
    
    public void removeView(StatusListener statusListener) {
        statusListener.setModel(null);
        listeners.remove(statusListener);
    }
    
}

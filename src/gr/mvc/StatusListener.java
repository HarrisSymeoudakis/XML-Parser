package gr.mvc;

/**
 *
 * @author Administrator
 */
public interface StatusListener {

    public void statusChangedEvent(boolean selected);

    public void setModel(CommonModel commonModel);

}

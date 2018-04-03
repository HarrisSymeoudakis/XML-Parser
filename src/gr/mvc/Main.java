package gr.mvc;

/**
 *
 * @author Administrator
 */
public class Main {

    public static void main(String[] args) {
        CommonModel commonModel = new CommonModel();

        View1 view1 = new View1();
        View2 view2 = new View2();
        View3 view3 = new View3();
        View4 view4 = new View4();
        View5 view5 = new View5();
        View6 view6 = new View6();

        commonModel.addView(view1);
        commonModel.addView(view2);
        commonModel.addView(view3);
        commonModel.addView(view4);
        commonModel.addView(view5);
        
        commonModel.addView(view6);
    }

}

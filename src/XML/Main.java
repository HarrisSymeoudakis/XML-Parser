/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Vaggelis
 */
public class Main {

    private static Object rand;
    private static JFrame frame;
    private static Class frameComp;

    public static void main(String[] args) {

        try {
            // UI Document
            File inputFile = new File("ui.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            // AbstractContainers Document
            inputFile = new File("AbstractContainer.widget.xml");
            DocumentBuilderFactory dbFactory1 = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder1 = dbFactory1.newDocumentBuilder();
            Document doc1 = dBuilder1.parse(inputFile);
            doc.getDocumentElement().normalize();
            // AbstractButtons Document
            inputFile = new File("AbstractButton.widget.xml");
            DocumentBuilderFactory dbFactory2 = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder2 = dbFactory2.newDocumentBuilder();
            Document doc2 = dBuilder2.parse(inputFile);
            doc.getDocumentElement().normalize();
            //
            NodeList AbstractContainers = doc.getElementsByTagName("abstractContainer");
            NodeList AbstractButtons = doc.getElementsByTagName("abstractButton");
            NodeList uiInstances = doc.getElementsByTagName("instance");
            NodeList uiProperties = doc.getElementsByTagName("property");
            NodeList containerMethods = doc1.getElementsByTagName("utilized");
            NodeList buttonMethods = doc2.getElementsByTagName("utilized");
            NodeList containmentProps = doc1.getElementsByTagName("containmentProperty");

            JPanel rootPanel = new JPanel();
            rootPanel.setBackground(Color.RED);
            JPanel tempPanel = new JPanel();
            JButton tempButton = new JButton();
            XPath searchPath = XPathFactory.newInstance().newXPath();
            Node searchNode, tempNode, tempNode1, tempNode2, tempNode3;
            String st, str, str2, tempSt, tempValue, classPath, methodName, methodType = null, containmentType, containmentID;

            for (int i = 0; i < AbstractContainers.getLength(); i++) {
                // Find the container in WidgetResources
                st = AbstractContainers.item(i).getAttributes().getNamedItem("id").getNodeValue();
//                searchNode = (Node) searchPath.compile("/uiModel/widgetResourceModel/widgetResource[@cioId='" + st + "']").evaluate(doc, XPathConstants.NODE);
                for (int j = 0; j < uiInstances.getLength(); j++) {
                    if (uiInstances.item(j).getParentNode().getAttributes().getNamedItem("cioId").getNodeValue().equals(st)) {

                        //Find the instance of the above container in abstractContainers.xml
                        tempSt = uiInstances.item(j).getAttributes().getNamedItem("wslInstId").getNodeValue();
                        tempNode = (Node) searchPath.compile("/widget/instances/instance[@id='" + tempSt + "']").evaluate(doc1, XPathConstants.NODE);

                        //Find the class of the above instance in abstractContrainers.xml
                        tempSt = tempNode.getAttributes().getNamedItem("classId").getNodeValue();
                        tempNode = (Node) searchPath.compile("//class[@id='" + tempSt + "']").evaluate(doc1, XPathConstants.NODE);

                        //Extract the class name
                        classPath = tempNode.getAttributes().getNamedItem("path").getNodeValue();
                        String comp_s = classPath;
                        Class comp = Class.forName(comp_s);
                        Object obj = comp.newInstance();
                        Random rand = new Random();

                        if (classPath.equals("javax.swing.JFrame")) {
                            frame = (JFrame) obj;
                            frame.setLayout(new GridLayout());
                            frame.setSize(500, 500);

                            //Find the Containment property for the Frame
                            str = uiInstances.item(j).getAttributes().getNamedItem("wslInstId").getNodeValue();
                            for (int ww = 0; ww < containmentProps.getLength(); ww++) {
                                if (containmentProps.item(ww).getParentNode().getParentNode().getAttributes().getNamedItem("id").getNodeValue().equals(str)) {
                                    str2 = containmentProps.item(ww).getAttributes().getNamedItem("id").getNodeValue();
                                    tempNode3 = (Node) searchPath.compile("//mappingProperty[@id='" + str2 + "']").evaluate(doc1, XPathConstants.NODE);
                                    for (int jj = 0; jj < containerMethods.getLength(); jj++) {
                                        if (containerMethods.item(jj).getParentNode().getAttributes().getNamedItem("id").getNodeValue().equals(tempNode3.getAttributes().getNamedItem("id").getNodeValue())) {
                                            containmentID = containerMethods.item(jj).getAttributes().getNamedItem("partId").getNodeValue();
                                            tempNode2 = (Node) searchPath.compile("//method[@id='" + containmentID + "']").evaluate(doc1, XPathConstants.NODE);
                                            if (tempNode2.getAttributes().getNamedItem("type").getNodeValue().equals("adder")) {
                                                methodType = tempNode2.getAttributes().getNamedItem("name").getNodeValue();
                                            }
                                            frameComp = comp;
                                        }
                                    }
                                }
                            }

                            //Find the properties of the frame
                            for (int k = 0; k < uiProperties.getLength(); k++) {
                                try {
                                    tempValue = uiProperties.item(k).getAttributes().getNamedItem("value").getNodeValue();
                                    //Execute each property
                                    if (uiProperties.item(k).getParentNode().getAttributes().getNamedItem("wslInstId").getNodeValue().equals(uiInstances.item(j).getAttributes().getNamedItem("wslInstId").getNodeValue())) {
//                                        System.out.println(tempValue);
                                        tempNode = (Node) searchPath.compile("//mappingProperty[@id='" + uiProperties.item(k).getAttributes().getNamedItem("wslPropId").getNodeValue() + "']").evaluate(doc1, XPathConstants.NODE);
                                        tempNode2 = (Node) searchPath.compile("//property[@id='" + uiProperties.item(k).getAttributes().getNamedItem("wslPropId").getNodeValue() + "']").evaluate(doc1, XPathConstants.NODE);
                                        for (int l = 0; l < containerMethods.getLength(); l++) {
                                            if (containerMethods.item(l).getParentNode().getAttributes().getNamedItem("id").getNodeValue().equals(tempNode.getAttributes().getNamedItem("id").getNodeValue())) {
                                                if (containerMethods.item(l).getAttributes().getNamedItem("in").getNodeValue().equals("method")) {
                                                    methodName = containerMethods.item(l).getAttributes().getNamedItem("partId").getNodeValue();
                                                    tempNode1 = (Node) searchPath.compile("//method[@id='" + methodName + "']").evaluate(doc1, XPathConstants.NODE);
                                                    methodName = tempNode1.getAttributes().getNamedItem("name").getNodeValue();
                                                    if (tempNode1.getAttributes().getNamedItem("type").getNodeValue().equals("mutator")) {
                                                        if (tempNode2.getAttributes().getNamedItem("type").getNodeValue().equals("string")) {
                                                            if (containerMethods.item(l).getAttributes().getNamedItem("asType").getNodeValue().equals("argument")) {
                                                                Method aMethod = comp.getMethod(methodName, String.class);
                                                                aMethod.invoke(frame, tempValue);
                                                            } else {
                                                                Method aMethod = comp.getMethod(methodName);
                                                                System.out.println(aMethod.invoke(frame));
                                                            }
                                                        } else if (tempNode2.getAttributes().getNamedItem("type").getNodeValue().equals("int") && tempNode2.getAttributes().getNamedItem("name").getNodeValue().equals("defaultCloseOperation")) {
                                                            if (containerMethods.item(l).getAttributes().getNamedItem("asType").getNodeValue().equals("argument")) {
                                                                int option = Integer.parseInt(tempValue);
                                                                frame.setDefaultCloseOperation(option);
                                                            } else {
                                                                Method aMethod = comp.getMethod(methodName);
                                                                System.out.println(aMethod.invoke(frame));
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                }
                            }
                        } else {
                            tempPanel = (JPanel) obj;
                            rootPanel.setLayout(new FlowLayout());
                            rootPanel.setBackground(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
                            rootPanel.setPreferredSize(new Dimension(100 * (i + 1), 100 * (i + 1)));

                            //Find the panel buttons
                            for (int k = 0; k < AbstractButtons.getLength(); k++) {
                                if (AbstractButtons.item(k).getParentNode().getAttributes().getNamedItem("id").getNodeValue().equals(st)) {
                                    for (int l = 0; l < uiInstances.getLength(); l++) {
                                        if (uiInstances.item(l).getParentNode().getAttributes().getNamedItem("cioId").getNodeValue().equals(AbstractButtons.item(k).getAttributes().getNamedItem("id").getNodeValue())) {

                                            //Find the instance of the above container in abstractButtons.xml
                                            tempSt = uiInstances.item(l).getAttributes().getNamedItem("wslInstId").getNodeValue();
                                            tempNode = (Node) searchPath.compile("/widget/instances/instance[@id='" + tempSt + "']").evaluate(doc2, XPathConstants.NODE);

                                            //Find the class of the above instance in abstractButtons.xml
                                            tempSt = tempNode.getAttributes().getNamedItem("classId").getNodeValue();
                                            tempNode = (Node) searchPath.compile("//class[@id='" + tempSt + "']").evaluate(doc2, XPathConstants.NODE);

                                            //Extract the class name
                                            classPath = tempNode.getAttributes().getNamedItem("path").getNodeValue();
                                            comp_s = classPath;
                                            Class comp2 = Class.forName(comp_s);
                                            obj = comp2.newInstance();
                                            tempButton = (JButton) obj;

                                            for (int u = 0; u < uiProperties.getLength(); u++) {
                                                if (uiProperties.item(u).getParentNode().getNodeName().equals("widgetResource")) {
                                                    if (uiProperties.item(u).getParentNode().getAttributes().getNamedItem("cioId").getNodeValue().equals(AbstractButtons.item(k).getAttributes().getNamedItem("id").getNodeValue())) {
                                                        tempValue = uiProperties.item(u).getAttributes().getNamedItem("value").getNodeValue();
                                                        tempSt = uiProperties.item(u).getAttributes().getNamedItem("id").getNodeValue();
                                                        tempNode = (Node) searchPath.compile("/widget/instances/instance[@name='SwingRectangleButton']//mappingProperty[@id='" + tempSt + "']").evaluate(doc2, XPathConstants.NODE);
                                                        for (int jj = 0; jj < buttonMethods.getLength(); jj++) {
                                                            if (buttonMethods.item(jj).getParentNode().getAttributes().getNamedItem("id").getNodeValue().equals(tempNode.getAttributes().getNamedItem("id").getNodeValue()) && buttonMethods.item(jj).getAttributes().getNamedItem("classId").getNodeValue().equals("inst_1_cl_1")) {
                                                                if (buttonMethods.item(jj).getAttributes().getNamedItem("in").getNodeValue().equals("method")) {
                                                                    methodName = buttonMethods.item(jj).getAttributes().getNamedItem("partId").getNodeValue();
                                                                    tempNode1 = (Node) searchPath.compile("//method[@id='" + methodName + "']").evaluate(doc2, XPathConstants.NODE);
                                                                    methodName = tempNode1.getAttributes().getNamedItem("name").getNodeValue();
                                                                    if (tempNode1.getAttributes().getNamedItem("type").getNodeValue().equals("mutator")) {
                                                                        if (buttonMethods.item(jj).getAttributes().getNamedItem("asType").getNodeValue().equals("argument")) {
                                                                            Method aMethod = comp2.getMethod(methodName, String.class);
                                                                            aMethod.invoke(tempButton, tempValue);
                                                                        } else {
                                                                            Method aMethod = comp2.getMethod(methodName);
                                                                            System.out.println(aMethod.invoke(tempButton));
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            rootPanel.add(tempButton);
                                        }
                                    }
                                }
                            }
                            //Add panels to panels------------------------------------------------------------
                            str = uiInstances.item(j).getAttributes().getNamedItem("wslInstId").getNodeValue();
                            for (int ww = 0; ww < containmentProps.getLength(); ww++) {
                                if (containmentProps.item(ww).getParentNode().getParentNode().getAttributes().getNamedItem("id").getNodeValue().equals(str)) {
                                    str2 = containmentProps.item(ww).getAttributes().getNamedItem("id").getNodeValue();
                                    tempNode3 = (Node) searchPath.compile("//mappingProperty[@id='" + str2 + "']").evaluate(doc1, XPathConstants.NODE);
                                    for (int jj = 0; jj < containerMethods.getLength(); jj++) {
                                        if (containerMethods.item(jj).getParentNode().getAttributes().getNamedItem("id").getNodeValue().equals(tempNode3.getAttributes().getNamedItem("id").getNodeValue())) {
                                            containmentType = containerMethods.item(jj).getAttributes().getNamedItem("asType").getNodeValue();
                                            containmentID = containerMethods.item(jj).getAttributes().getNamedItem("partId").getNodeValue();
                                            tempNode2 = (Node) searchPath.compile("//method[@id='" + containmentID + "']").evaluate(doc1, XPathConstants.NODE);
                                            methodName = tempNode2.getAttributes().getNamedItem("name").getNodeValue();
                                            if (tempNode2.getAttributes().getNamedItem("type").getNodeValue().equals("adder")) {
                                                if (containmentType.equals("argument")) {
                                                    Method aMethod = comp.getMethod(methodName, Component.class);
                                                    aMethod.invoke(tempPanel, rootPanel);
                                                    rootPanel = tempPanel;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //Add panels to frame-----------------------------------------------------------------------------------------
            Method aMethod = frameComp.getMethod(methodType, Component.class);
            aMethod.invoke(frame, rootPanel);
            rootPanel = tempPanel;
            frame.setVisible(true);
            frame.pack();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

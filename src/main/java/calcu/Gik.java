package calcu;

import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;

public class Gik extends JFrame {
    private JPanel buttons;
    private JPanel corners;
    private JTextArea text;
    private JLabel label;
    private OurGUI gui;

    /** Creating a calculator gui
     *  and setting functions
     */
    public Gik() {
        this.setTitle("Calcu");
        this.setSize(500, 500);
        this.setMinimumSize(new Dimension(400, 350));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        gui = new OurGUI(new Logic(this));
        gui.panels();
        this.add(corners, BorderLayout.SOUTH);
        this.add(gui.getPanel(), BorderLayout.NORTH);
        gui.buttons();

        //callers of size refreshing method
        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                refreshScale();
            }
            @Override
            public void componentMoved(ComponentEvent e) {
            }
            @Override
            public void componentShown(ComponentEvent e) {
            }
            @Override
            public void componentHidden(ComponentEvent e) {
            }
        });
        this.addWindowStateListener(e -> refreshScale());

        this.setVisible(true);
    }

    /** Refreshes sizes of gui
     *  and fonts
     */
    private void refreshScale(){
        int height = this.getHeight()/4;
        int mina = Math.min(this.getWidth(), this.getHeight());
        gui.refreshButtons(mina);
        text.setFont(new Font("Arial", Font.PLAIN, mina/14));
        label.setFont(new Font("Arial", Font.PLAIN, mina/13));
        gui.getPanel().setPreferredSize(new Dimension(0, height-39));
        corners.setPreferredSize(new Dimension(0, height*3));
    }

//    @Deprecated
//    public void updateText(double num){
//        int in = (int) num;
//        if((in-num) == 0) text.setText(Integer.toString(in));
//        else text.setText(Double.toString(num));
//    }
    public void updateText(BigDecimal num){
        if(num.toString().length() > 21)
            text.setText(Double.toString(num.doubleValue()));
        else text.setText(num.toString());
    }
    public void updateText(String words){
        text.setText(words);
    }
    public void updateLabel(Operation operation){
        switch (operation) {
            case NULL -> label.setText(" ");
            case SUMMATION -> label.setText("+");
            case SUBTRACTION -> label.setText("-");
            case MULTIPLICATION -> label.setText("*");
            case DIVISION -> label.setText("/");
        }
    }

    public JPanel getPanel() {
        return gui.getPanel();
    }


    public JPanel getButtons() {
        return buttons;
    }

    public void setButtons(JPanel buttons) {
        this.buttons = buttons;
    }

    public JPanel getCorners() {
        return corners;
    }

    public void setCorners(JPanel corners) {
        this.corners = corners;
    }

    public JTextArea getText() {
        return text;
    }

    public void setText(JTextArea text) {
        this.text = text;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public OurGUI getGui() {
        return gui;
    }

    public void setGui(OurGUI gui) {
        this.gui = gui;
    }
}

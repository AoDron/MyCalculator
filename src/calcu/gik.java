package calcu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;

class gik extends JFrame {
    static JPanel panel;
    static JPanel buttons;
    static JPanel corners;
    static JTextArea text;
    static JLabel label;

    /** Creating a calculator gui
     *  and setting functions
     */
    gik() {
        this.setTitle("Calcu");
        this.setSize(500, 500);
        this.setMinimumSize(new Dimension(400, 350));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        OurGUI.panels();
        this.add(corners, BorderLayout.SOUTH);
        this.add(panel, BorderLayout.NORTH);
        OurGUI.buttons();

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
        OurGUI.refreshBttons(mina);
        text.setFont(new Font("Arial", Font.PLAIN, mina/14));
        label.setFont(new Font("Arial", Font.PLAIN, mina/13));
        panel.setPreferredSize(new Dimension(0, height-39));
        corners.setPreferredSize(new Dimension(0, height*3));
    }

    @Deprecated
    static void updateText(double num){
        int in = (int) num;
        if((in-num) == 0) text.setText(Integer.toString(in));
        else text.setText(Double.toString(num));
    }
    static void updateText(BigDecimal num){
        if(num.toString().length() > 21)
            text.setText(Double.toString(num.doubleValue()));
        else text.setText(num.toString());
    }
    static void updateText(String words){
        text.setText(words);
    }
    static void updateLabel(Operation operation){
        switch (operation) {
            case NULL -> label.setText(" ");
            case SUMMATION -> label.setText("+");
            case SUBTRACTION -> label.setText("-");
            case MULTIPLICATION -> label.setText("*");
            case DIVISION -> label.setText("/");
        }
    }

}

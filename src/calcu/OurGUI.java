package calcu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static calcu.gik.*;

public class OurGUI{

    static JButton b1 = new JButton("1");
    static JButton b2 = new JButton("2");
    static JButton b3 = new JButton("3");
    static JButton b4 = new JButton("4");
    static JButton b5 = new JButton("5");
    static JButton b6 = new JButton("6");
    static JButton b7 = new JButton("7");
    static JButton b8 = new JButton("8");
    static JButton b9 = new JButton("9");
    static JButton b0 = new JButton("0");
    static JButton bSign = new JButton("+/-");
    static JButton bDot = new JButton(".");
    static JButton bDiv = new JButton("/");
    static JButton bMult = new JButton("*");
    static JButton bSub = new JButton("-");
    static JButton bSum = new JButton("+");
    static JButton bBackSpace = new JButton("<-");
    static JButton bC = new JButton("C");
    static JButton bCE = new JButton("CE");
    static JButton bEq = new JButton("=");


    static void panels(){
        panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(0, 200));

        text = new JTextArea("0", 1, 10);
        text.setEditable(false);
        text.setPreferredSize(new Dimension(100000, 10));
        text.setFont(new Font("Arial", Font.PLAIN, 38));
        text.setMargin(new Insets(10, 10, 10, 10));
        //text.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        panel.add(text);

        label = new JLabel(" ");
        label.setFont(new Font("Arial", Font.PLAIN, 38));
        panel.add(label, BorderLayout.EAST);

        buttons = new JPanel();
        buttons.setBackground(Color.darkGray);
        buttons.setPreferredSize(new Dimension(0, 0));
        buttons.setLayout(new GridLayout(4, 5,5,5));

        corners = new JPanel();
        corners.setBackground(Color.blue);
        corners.setPreferredSize(new Dimension(0, 400));
        corners.setLayout(new BorderLayout(0, 0));
        corners.add(buttons, BorderLayout.CENTER);

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();

        p1.setBackground(Color.darkGray);
        p2.setBackground(Color.darkGray);
        p3.setBackground(Color.darkGray);
        p4.setBackground(Color.darkGray);

        p1.setPreferredSize(new Dimension(5, 5));
        p2.setPreferredSize(new Dimension(5, 5));
        p3.setPreferredSize(new Dimension(5, 5));
        p4.setPreferredSize(new Dimension(5, 5));

        corners.add(p1, BorderLayout.NORTH);
        corners.add(p2, BorderLayout.EAST);
        corners.add(p3, BorderLayout.WEST);
        corners.add(p4, BorderLayout.SOUTH);

        text.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case '1' -> b1.doClick(50);
                    case '2' -> b2.doClick(50);
                    case '3' -> b3.doClick(50);
                    case '4' -> b4.doClick(50);
                    case '5' -> b5.doClick(50);
                    case '6' -> b6.doClick(50);
                    case '7' -> b7.doClick(50);
                    case '8' -> b8.doClick(50);
                    case '9' -> b9.doClick(50);
                    case '0' -> b0.doClick(50);
                    case '+' -> bSum.doClick(50);
                    case '-' -> bSub.doClick(50);
                    case '*' -> bMult.doClick(50);
                    case '/' -> bDiv.doClick(50);
                    case '=' -> bEq.doClick(50);
                    case '.' -> bDot.doClick(50);
                    case '\\' -> bSign.doClick(50);
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case 10 -> bEq.doClick();
                    case 8 -> {
                        bBackSpace.doClick();
                        e.consume();
                    }
                    case 127 -> {
                        bCE.doClick();
                        e.consume();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

    }

    static void buttons(){
        refreshBttons(500);

        b1.setFocusable(false);
        b2.setFocusable(false);
        b3.setFocusable(false);
        b4.setFocusable(false);
        b5.setFocusable(false);
        b6.setFocusable(false);
        b7.setFocusable(false);
        b8.setFocusable(false);
        b9.setFocusable(false);
        b0.setFocusable(false);
        bSign.setFocusable(false);
        bDot.setFocusable(false);
        bDiv.setFocusable(false);
        bMult.setFocusable(false);
        bSub.setFocusable(false);
        bSum.setFocusable(false);
        bBackSpace.setFocusable(false);
        bC.setFocusable(false);
        bCE.setFocusable(false);
        bEq.setFocusable(false);

        //row 1
        buttons.add(b7);
        buttons.add(b8);
        buttons.add(b9);
        buttons.add(bDiv);
        buttons.add(bBackSpace);
        //row2
        buttons.add(b4);
        buttons.add(b5);
        buttons.add(b6);
        buttons.add(bMult);
        buttons.add(bC);
        //row3
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(bSub);
        buttons.add(bCE);
        //row4
        buttons.add(bSign);
        buttons.add(b0);
        buttons.add(bDot);
        buttons.add(bSum);
        buttons.add(bEq);

        b1.addActionListener(e -> Logic.inputNumber(1));
        b2.addActionListener(e -> Logic.inputNumber(2));
        b3.addActionListener(e -> Logic.inputNumber(3));
        b4.addActionListener(e -> Logic.inputNumber(4));
        b5.addActionListener(e -> Logic.inputNumber(5));
        b6.addActionListener(e -> Logic.inputNumber(6));
        b7.addActionListener(e -> Logic.inputNumber(7));
        b8.addActionListener(e -> Logic.inputNumber(8));
        b9.addActionListener(e -> Logic.inputNumber(9));
        b0.addActionListener(e -> Logic.inputNumber(0));

        bSign.addActionListener(e -> Logic.changeSign());
        bDot.addActionListener(e -> Logic.createDot());

        bSum.addActionListener(e -> Logic.registerOperation(Operation.SUMMATION));
        bSub.addActionListener(e -> Logic.registerOperation(Operation.SUBTRACTION));
        bMult.addActionListener(e -> Logic.registerOperation(Operation.MULTIPLICATION));
        bDiv.addActionListener(e -> Logic.registerOperation(Operation.DIVISION));
        bEq.addActionListener(e -> Logic.equals());

        bC.addActionListener(e -> Logic.clear(false));
        bCE.addActionListener(e -> Logic.clearEnter());
        bBackSpace.addActionListener(e -> Logic.backSpace());
    }
    static void refreshBttons(int lengh){
        int fontSize = lengh/15;
        b1.setFont(new Font("Arial", Font.PLAIN, fontSize));
        b2.setFont(new Font("Arial", Font.PLAIN, fontSize));
        b3.setFont(new Font("Arial", Font.PLAIN, fontSize));
        b4.setFont(new Font("Arial", Font.PLAIN, fontSize));
        b5.setFont(new Font("Arial", Font.PLAIN, fontSize));
        b6.setFont(new Font("Arial", Font.PLAIN, fontSize));
        b7.setFont(new Font("Arial", Font.PLAIN, fontSize));
        b8.setFont(new Font("Arial", Font.PLAIN, fontSize));
        b9.setFont(new Font("Arial", Font.PLAIN, fontSize));
        b0.setFont(new Font("Arial", Font.PLAIN, fontSize));
        bSign.setFont(new Font("Arial", Font.PLAIN, fontSize));
        bDot.setFont(new Font("Arial", Font.PLAIN, fontSize));
        bDiv.setFont(new Font("Arial", Font.PLAIN, fontSize));
        bMult.setFont(new Font("Arial", Font.PLAIN, fontSize));
        bSub.setFont(new Font("Arial", Font.PLAIN, fontSize));
        bSum.setFont(new Font("Arial", Font.PLAIN, fontSize));
        bBackSpace.setFont(new Font("Arial", Font.PLAIN, fontSize));
        bC.setFont(new Font("Arial", Font.PLAIN, fontSize));
        bCE.setFont(new Font("Arial", Font.PLAIN, fontSize));
        bEq.setFont(new Font("Arial", Font.PLAIN, fontSize));
    }

}

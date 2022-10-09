package calcu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class OurGUI{

    private final JButton b1 = new JButton("1");
    private final JButton b2 = new JButton("2");
    private final JButton b3 = new JButton("3");
    private final JButton b4 = new JButton("4");
    private final JButton b5 = new JButton("5");
    private final JButton b6 = new JButton("6");
    private final JButton b7 = new JButton("7");
    private final JButton b8 = new JButton("8");
    private final JButton b9 = new JButton("9");
    private final JButton b0 = new JButton("0");
    private final JButton bSign = new JButton("+/-");
    private final JButton bDot = new JButton(".");
    private final JButton bDiv = new JButton("/");
    private final JButton bMult = new JButton("*");
    private final JButton bSub = new JButton("-");
    private final JButton bSum = new JButton("+");
    private final JButton bBackSpace = new JButton("<-");
    private final JButton bC = new JButton("C");
    private final JButton bCE = new JButton("CE");
    private final JButton bEq = new JButton("=");
    private final Logic logic;
    private final Gik gik;
    private JPanel panel;
    public OurGUI(Logic logic) {
        this.logic = logic;
        gik = logic.getGik();
    }

    void panels(){
        panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(0, 200));
        gik.setText(new JTextArea("0", 1, 10));
        panel.add(gik.getText());
        gik.getText().setEditable(false);
        gik.getText().setPreferredSize(new Dimension(100000, 10));
        gik.getText().setFont(new Font("Arial", Font.PLAIN, 38));
        gik.getText().setMargin(new Insets(10, 10, 10, 10));

        gik.setLabel(new JLabel(" "));
        gik.getLabel().setFont(new Font("Arial", Font.PLAIN, 38));
        gik.getPanel().add(gik.getLabel(), BorderLayout.EAST);

        gik.setButtons(new JPanel());
        gik.getButtons().setBackground(Color.darkGray);
        gik.getButtons().setPreferredSize(new Dimension(0, 0));
        gik.getButtons().setLayout(new GridLayout(4, 5,5,5));

        gik.setCorners(new JPanel());
        gik.getCorners().setBackground(Color.blue);
        gik.getCorners().setPreferredSize(new Dimension(0, 400));
        gik.getCorners().setLayout(new BorderLayout(0, 0));
        gik.getCorners().add(gik.getButtons(), BorderLayout.CENTER);

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

        gik.getCorners().add(p1, BorderLayout.NORTH);
        gik.getCorners().add(p2, BorderLayout.EAST);
        gik.getCorners().add(p3, BorderLayout.WEST);
        gik.getCorners().add(p4, BorderLayout.SOUTH);

        gik.getText().addKeyListener(new KeyListener() {
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

    public void buttons(){
        refreshButtons(500);

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
        for (var jButton : Arrays.asList(bBackSpace, bC, bCE, bEq)) {
            jButton.setFocusable(false);
        }

        gik.getButtons().add(b7);
        gik.getButtons().add(b8);
        gik.getButtons().add(b9);
        gik.getButtons().add(bDiv);
        gik.getButtons().add(bBackSpace);
        //row2
        gik.getButtons().add(b4);
        gik.getButtons().add(b5);
        gik.getButtons().add(b6);
        gik.getButtons().add(bMult);
        gik.getButtons().add(bC);
        //row3
        gik.getButtons().add(b1);
        gik.getButtons().add(b2);
        gik.getButtons().add(b3);
        gik.getButtons().add(bSub);
        gik.getButtons().add(bCE);
        //row4
        gik.getButtons().add(bSign);
        gik.getButtons().add(b0);
        gik.getButtons().add(bDot);
        gik.getButtons().add(bSum);
        gik.getButtons().add(bEq);

        b1.addActionListener(e -> logic.inputNumber(1));
        b2.addActionListener(e -> logic.inputNumber(2));
        b3.addActionListener(e -> logic.inputNumber(3));
        b4.addActionListener(e -> logic.inputNumber(4));
        b5.addActionListener(e -> logic.inputNumber(5));
        b6.addActionListener(e -> logic.inputNumber(6));
        b7.addActionListener(e -> logic.inputNumber(7));
        b8.addActionListener(e -> logic.inputNumber(8));
        b9.addActionListener(e -> logic.inputNumber(9));
        b0.addActionListener(e -> logic.inputNumber(0));

        bSign.addActionListener(e -> logic.changeSign());
        bDot.addActionListener(e -> logic.createDot());

        bSum.addActionListener(e -> logic.registerOperation(Operation.SUMMATION));
        bSub.addActionListener(e -> logic.registerOperation(Operation.SUBTRACTION));
        bMult.addActionListener(e -> logic.registerOperation(Operation.MULTIPLICATION));
        bDiv.addActionListener(e -> logic.registerOperation(Operation.DIVISION));
        bEq.addActionListener(e -> logic.equals());

        bC.addActionListener(e -> logic.clear(false));
        bCE.addActionListener(e -> logic.clearEnter());
        bBackSpace.addActionListener(e -> logic.backSpace());
    }
    public void refreshButtons(int lengh){
        int fontSize = lengh/15;
        setFont0(fontSize, b1, b2, b3, b4, b5, b6, b7, b8, b9, b0);
        setFont0(fontSize, bSign, bDot, bDiv, bMult, bSub, bSum, bBackSpace, bC, bCE, bEq);
    }

    private void setFont0(int fontSize, JButton b1, JButton b2, JButton b3, JButton b4, JButton b5, JButton b6, JButton b7, JButton b8, JButton b9, JButton b0) {
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
    }

    public JPanel getPanel() {
        return panel;
    }
}

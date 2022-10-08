package calcu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class OurGUI{

    private JButton b1 = new JButton("1");
    private JButton b2 = new JButton("2");
    private JButton b3 = new JButton("3");
    private JButton b4 = new JButton("4");
    private JButton b5 = new JButton("5");
    private JButton b6 = new JButton("6");
    private JButton b7 = new JButton("7");
    private JButton b8 = new JButton("8");
    private JButton b9 = new JButton("9");
    private JButton b0 = new JButton("0");
    private JButton bSign = new JButton("+/-");
    private JButton bDot = new JButton(".");
    private JButton bDiv = new JButton("/");
    private JButton bMult = new JButton("*");
    private JButton bSub = new JButton("-");
    private JButton bSum = new JButton("+");
    private JButton bBackSpace = new JButton("<-");
    private JButton bC = new JButton("C");
    private JButton bCE = new JButton("CE");
    private JButton bEq = new JButton("=");
    private Logic logic;
    private Gik gik;
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

    public JButton getB1() {
        return b1;
    }

    public void setB1(JButton b1) {
        this.b1 = b1;
    }

    public JButton getB2() {
        return b2;
    }

    public void setB2(JButton b2) {
        this.b2 = b2;
    }

    public JButton getB3() {
        return b3;
    }

    public void setB3(JButton b3) {
        this.b3 = b3;
    }

    public JButton getB4() {
        return b4;
    }

    public void setB4(JButton b4) {
        this.b4 = b4;
    }

    public JButton getB5() {
        return b5;
    }

    public void setB5(JButton b5) {
        this.b5 = b5;
    }

    public JButton getB6() {
        return b6;
    }

    public void setB6(JButton b6) {
        this.b6 = b6;
    }

    public JButton getB7() {
        return b7;
    }

    public void setB7(JButton b7) {
        this.b7 = b7;
    }

    public JButton getB8() {
        return b8;
    }

    public void setB8(JButton b8) {
        this.b8 = b8;
    }

    public JButton getB9() {
        return b9;
    }

    public void setB9(JButton b9) {
        this.b9 = b9;
    }

    public JButton getB0() {
        return b0;
    }

    public void setB0(JButton b0) {
        this.b0 = b0;
    }

    public JButton getbSign() {
        return bSign;
    }

    public void setbSign(JButton bSign) {
        this.bSign = bSign;
    }

    public JButton getbDot() {
        return bDot;
    }

    public void setbDot(JButton bDot) {
        this.bDot = bDot;
    }

    public JButton getbDiv() {
        return bDiv;
    }

    public void setbDiv(JButton bDiv) {
        this.bDiv = bDiv;
    }

    public JButton getbMult() {
        return bMult;
    }

    public void setbMult(JButton bMult) {
        this.bMult = bMult;
    }

    public JButton getbSub() {
        return bSub;
    }

    public void setbSub(JButton bSub) {
        this.bSub = bSub;
    }

    public JButton getbSum() {
        return bSum;
    }

    public void setbSum(JButton bSum) {
        this.bSum = bSum;
    }

    public JButton getbBackSpace() {
        return bBackSpace;
    }

    public void setbBackSpace(JButton bBackSpace) {
        this.bBackSpace = bBackSpace;
    }

    public JButton getbC() {
        return bC;
    }

    public void setbC(JButton bC) {
        this.bC = bC;
    }

    public JButton getbCE() {
        return bCE;
    }

    public void setbCE(JButton bCE) {
        this.bCE = bCE;
    }

    public JButton getbEq() {
        return bEq;
    }

    public void setbEq(JButton bEq) {
        this.bEq = bEq;
    }

    public Logic getLogic() {
        return logic;
    }

    public void setLogic(Logic logic) {
        this.logic = logic;
    }

    public Gik getGik() {
        return gik;
    }

    public void setGik(Gik gik) {
        this.gik = gik;
    }

    public JPanel getPanel() {
        return panel;
    }
}

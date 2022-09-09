package calcu;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Logic {

    private static BigDecimal centralNumber = new BigDecimal(0);
    private static BigDecimal firstNumber = new BigDecimal(0);
    private static byte length = 0;
    private static int dotAmount = 1;
    private static byte dotLength = 0;
    private static Operation currentOperation = Operation.NULL;
    private static boolean isNegative = false;
    private static boolean isDouble = false;
    private static boolean isOperated = false;
    private static boolean operatedJustNow = false;
    private static boolean computedJustNow = false;
    private static List<String> operationList = new ArrayList<>();

    /** Number input implementation
     *
     * @param num
     * Enters number "num".
     * Allowed in range 0-9
     */
    static void inputNumber(int num){
        if(operatedJustNow){
            clearEnter();
            isOperated = true;
            operatedJustNow = false;
        }
        if(computedJustNow){
            clear(false);
        }
        if(length < 20 && dotLength < 9 && (centralNumber.intValue() != 0 || num != 0 || isDouble)) {
            length++;
            if (isDouble) {
                dotAmount *= 10;
                dotLength++;
                if(isNegative) centralNumber = centralNumber.subtract(new BigDecimal((double) num / dotAmount));
                else centralNumber = centralNumber.add(new BigDecimal((double) num / dotAmount));
                centralNumber = centralNumber.setScale(dotLength, RoundingMode.HALF_UP);
            } else {
                centralNumber = centralNumber.multiply(new BigDecimal(10));
                if(isNegative) centralNumber = centralNumber.subtract(new BigDecimal(num));
                else centralNumber = centralNumber.add(new BigDecimal(num));
            }
            gik.updateText(centralNumber);
        }
    }

    /**
     * Changes sign of entered number
     */
    static void changeSign(){
        isNegative = !isNegative;
        if(computedJustNow){
            firstNumber = firstNumber.negate();
            gik.updateText(firstNumber);
        }else {
            centralNumber = centralNumber.negate();
            gik.updateText(centralNumber);
            if(isDouble && dotLength == 0) gik.updateText(centralNumber + ".");
        }
    }

    /**
     * Makes number fractional
     */
    static void createDot() {
        if(operatedJustNow){
            clearEnter();
            isOperated = true;
            operatedJustNow = false;
        }
        if(!isDouble){
            isDouble = true;
            if(centralNumber.intValue() == 0) length++;
            gik.updateText(centralNumber + ".");
        }
    }

    /**
     * Registers entered operation in memory and resets entered number
     * @param operation
     * Number or name of required operation
     */
    static void registerOperation(Operation operation){
        if(isOperated) {
            equals();
            isOperated = false;
        }
        currentOperation = operation;
        gik.updateLabel(currentOperation);
        if(computedJustNow) computedJustNow = false;
        else firstNumber = centralNumber;
        operatedJustNow = true;
    }

    /**
     * Performing a calculation
     */
    static void equals() {
        if (centralNumber.equals(new BigDecimal(0)) && currentOperation == Operation.DIVISION) {
            clear(false);
            gik.updateText("fuck you");
        } else {
            String operation;
            switch(currentOperation) {
                case NULL:
                    firstNumber = centralNumber;
                    break;
                case SUMMATION:
                    operation = firstNumber.floatValue() +" + " + centralNumber.floatValue();
                    firstNumber = firstNumber.add(centralNumber);
                    operation += " = " + firstNumber.floatValue();
                    operationList.add(operation);
                    break;
                case SUBTRACTION:
                    operation = firstNumber.floatValue() +" - " + centralNumber.floatValue();
                    firstNumber = firstNumber.subtract(centralNumber);
                    operation += " = " + firstNumber.floatValue();
                    operationList.add(operation);
                    break;
                case MULTIPLICATION:
                    operation = firstNumber.floatValue() +" * " + centralNumber.floatValue();
                    firstNumber = firstNumber.multiply(centralNumber);
                    operation += " = " + firstNumber.floatValue();
                    operationList.add(operation);
                    break;
                case DIVISION:
                    try {
                        operation = firstNumber.floatValue() +" / " + centralNumber.floatValue();
                        firstNumber = firstNumber.divide(centralNumber);
                        operation += " = " + firstNumber.floatValue();
                        operationList.add(operation);
                        break;
                    } catch (ArithmeticException e) {
                        operation = firstNumber.floatValue() +" / " + centralNumber.floatValue();
                        firstNumber = firstNumber.divide(centralNumber, 9, RoundingMode.HALF_UP);
                        operation += " = " + firstNumber.floatValue();
                        operationList.add(operation);
                    }
                    break;
            }
            System.out.println(operationList);
            gik.updateText(firstNumber);
            isOperated = false;
            computedJustNow = true;
            }
    }

    /**
     * Resets calculator and entered numbers.
     * In other words, resets everything
     *
     * @param saveCO
     * Default: "false"
     * if called with "true" saves entered operation and number in storage (experimental function, unused)
     */
    static void clear(boolean saveCO){
        if(!saveCO) currentOperation = Operation.NULL;
        if(!saveCO) firstNumber = new BigDecimal(0);
        gik.updateLabel(currentOperation);
        centralNumber = new BigDecimal(0);
        dotAmount = 1;
        dotLength = 0;
        length = 0;
        isDouble = false;
        isNegative = false;
        isOperated = false;
        operatedJustNow = false;
        computedJustNow = false;
        gik.updateText("0");
    }

    /**
     *  Resets entered number.
     *  After performing a calculation resets everything
     */
    static void clearEnter(){
        if(computedJustNow) clear(false);
        else {
            centralNumber = new BigDecimal(0);
            isNegative = false;
            isDouble = false;
            dotAmount = 1;
            dotLength = 0;
            length = 0;
            gik.updateText("0");
        }
    }

    /**
     *  Undoes last entered number or placed dot
     */
    static void backSpace(){
        StringBuilder sb = new StringBuilder(gik.text.getText());
        if (sb.toString().equals("0"))
            return;
        if (isDouble && dotLength > 0) {
            dotAmount /= 10;
            dotLength--;
        }
        if (!sb.toString().equals("0") && sb.length() != 1) {
            sb.deleteCharAt(sb.toString().length()-1);
        } else if (sb.toString().length() ==1 && !sb.toString().equals("0")) {
            sb.setLength(0);
            sb.append("0");
        }
        centralNumber = BigDecimal.valueOf(Double.parseDouble(sb.toString()));
        gik.updateText(sb.toString());
        length--;
    }
}

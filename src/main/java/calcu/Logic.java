package calcu;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Data
public class Logic {

    private BigDecimal centralNumber = new BigDecimal(0);
    private BigDecimal firstNumber = new BigDecimal(0);
    private byte length = 0;
    private int dotAmount = 1;
    private byte dotLength = 0;
    private Operation currentOperation = Operation.NULL;
    private boolean isNegative = false;
    private boolean isDouble = false;
    private boolean isOperated = false;
    private boolean operatedJustNow = false;
    private boolean computedJustNow = false;

    private final Gik gik;
    public Logic(Gik gik) {
        this.gik = gik;
    }
    /**
     * Number input implementation
     *
     * @param num Enters number "num".
     *            Allowed in range 0-9
     */
    public void inputNumber(int num){
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
    public void changeSign(){
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
    public void createDot() {
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
    public void registerOperation(Operation operation){
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
    public void equals() {
        if (centralNumber.equals(new BigDecimal(0)) && currentOperation == Operation.DIVISION) {
            clear(false);
            gik.updateText("/ by zero");
        } else {
            switch(currentOperation) {
                case NULL:
                    firstNumber = centralNumber;
                    break;
                case SUMMATION:
                    firstNumber = firstNumber.add(centralNumber);
                    break;
                case SUBTRACTION:
                    firstNumber = firstNumber.subtract(centralNumber);
                    break;
                case MULTIPLICATION:
                    firstNumber = firstNumber.multiply(centralNumber);
                    break;
                case DIVISION:
                    try {
                        firstNumber = firstNumber.divide(centralNumber);
                    } catch (ArithmeticException e) {
                        firstNumber = firstNumber.divide(centralNumber, 9, RoundingMode.HALF_UP);
                    }
                    break;
            }
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
    public void clear(boolean saveCO){
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
    public void clearEnter(){
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
    public void backSpace(){
        if(length > 0) {
            length--;
            if (isDouble) {
                if(dotLength > 0) {
                    dotAmount /= 10;
                    dotLength--;
                    centralNumber = centralNumber.setScale(dotLength, RoundingMode.DOWN);
                } else {
                    isDouble = false;
                    length++;
                }
            } else centralNumber = centralNumber.divideToIntegralValue(new BigDecimal(10));
            if (!computedJustNow) gik.updateText(centralNumber);
            if (isDouble && dotLength == 0) gik.updateText(centralNumber + ".");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Logic logic = (Logic) o;
        return length == logic.length && dotAmount == logic.dotAmount && dotLength == logic.dotLength && isNegative == logic.isNegative && isDouble == logic.isDouble && isOperated == logic.isOperated && operatedJustNow == logic.operatedJustNow && computedJustNow == logic.computedJustNow && currentOperation == logic.currentOperation && Objects.equals(gik, logic.gik);
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, dotAmount, dotLength, currentOperation, isNegative, isDouble, isOperated, operatedJustNow, computedJustNow, gik);
    }

    public BigDecimal getCentralNumber() {
        return centralNumber;
    }

    public void setCentralNumber(BigDecimal centralNumber) {
        this.centralNumber = centralNumber;
    }

    public BigDecimal getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(BigDecimal firstNumber) {
        this.firstNumber = firstNumber;
    }

    public byte getLength() {
        return length;
    }

    public void setLength(byte length) {
        this.length = length;
    }

    public int getDotAmount() {
        return dotAmount;
    }

    public void setDotAmount(int dotAmount) {
        this.dotAmount = dotAmount;
    }

    public byte getDotLength() {
        return dotLength;
    }

    public void setDotLength(byte dotLength) {
        this.dotLength = dotLength;
    }

    public Operation getCurrentOperation() {
        return currentOperation;
    }

    public void setCurrentOperation(Operation currentOperation) {
        this.currentOperation = currentOperation;
    }

    public boolean isNegative() {
        return isNegative;
    }

    public void setNegative(boolean negative) {
        isNegative = negative;
    }

    public boolean isDouble() {
        return isDouble;
    }

    public void setDouble(boolean aDouble) {
        isDouble = aDouble;
    }

    public boolean isOperated() {
        return isOperated;
    }

    public void setOperated(boolean operated) {
        isOperated = operated;
    }

    public boolean isOperatedJustNow() {
        return operatedJustNow;
    }

    public void setOperatedJustNow(boolean operatedJustNow) {
        this.operatedJustNow = operatedJustNow;
    }

    public boolean isComputedJustNow() {
        return computedJustNow;
    }

    public void setComputedJustNow(boolean computedJustNow) {
        this.computedJustNow = computedJustNow;
    }

    public Gik getGik() {
        return gik;
    }
}

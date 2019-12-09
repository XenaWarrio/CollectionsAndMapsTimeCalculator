package dx.queen.newcalculationandmaps.testcalculator;

import dx.queen.newcalculationandmaps.dto.task.TaskData;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculatorImpl;


public class TestCalculator extends TimeCalculatorImpl {
    public final long TIME = 3;

    @Override
    public void execAndSetupTime(TaskData td) {
        super.execAndSetupTime(td);
        td.setTime(TIME);
    }
}
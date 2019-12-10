package dx.queen.newcalculationandmaps.model.calculator;

import dx.queen.newcalculationandmaps.dto.task.TaskData;


public class TestCalculator extends TimeCalculatorImpl {
    public final long TIME = 3;

    @Override
    public void execAndSetupTime(TaskData td) {
        super.execAndSetupTime(td);
        td.setTime(TIME);
    }
}
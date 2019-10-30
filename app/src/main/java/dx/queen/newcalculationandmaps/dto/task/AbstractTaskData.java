package dx.queen.newcalculationandmaps.dto.task;

import java.util.List;
import java.util.Map;

import dx.queen.newcalculationandmaps.dto.CalculationResult;

public abstract class AbstractTaskData implements TaskData {
    private final int labelResId;
    private double time ;

    AbstractTaskData(int labelResId) {
        this.labelResId = labelResId;
    }

    @Override
    public List<Integer> getList() {
        return null;
    }

    @Override
    public Map<Integer, Integer> getMap() {
        return null;
    }

    @Override
    public int getLabelResId() {
        return labelResId;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public CalculationResult getResult() {
        return new CalculationResult(labelResId, time);
    }
}

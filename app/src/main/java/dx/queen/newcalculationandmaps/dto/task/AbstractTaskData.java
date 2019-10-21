package dx.queen.newcalculationandmaps.dto.task;

import java.util.List;
import java.util.Map;

import dx.queen.newcalculationandmaps.dto.CalculationResult;

public abstract class AbstractTaskData implements TaskData {
    private final int labelResId;
    private String tag;
    private double time = -1;

    AbstractTaskData(int labelResId, String tag) {
        this.labelResId = labelResId;
        this.tag = tag;
    }

    @Override
    public List<Integer> getList() {
        return null;
    }

    @Override
    public Map<Integer, Integer> getMap() {
        return null;
    }

    public String getTag() {
        return tag;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public CalculationResult getResult() {
        return new CalculationResult(labelResId, tag, time);
    }
}

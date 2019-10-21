package dx.queen.newcalculationandmaps.dto;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TaskData {
    public final List<Integer> collection;
    public final int labelResId;
    public final Map<Integer, Integer> map;
    public String tag;
    private double time = -1;

    public TaskData(List<Integer> collection, int labelResId, String tag) {
        this.collection = collection;
        map = null;
        this.labelResId = labelResId;
        this.tag = tag;
    }

    public TaskData(Map<Integer, Integer> map, int labelResId, String tag) {
        this.map = map;
        this.collection = null;
        this.labelResId = labelResId;
        this.tag = tag;
    }

    public void fill(int elements) {
        if (collection == null) {
            // TODO ksenia: fill map
        } else {
            collection.addAll(Collections.nCopies(elements, 1));
        }
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

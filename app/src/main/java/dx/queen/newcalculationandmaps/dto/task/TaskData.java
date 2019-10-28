package dx.queen.newcalculationandmaps.dto.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import dx.queen.newcalculationandmaps.dto.CalculationResult;

public interface TaskData {
    CalculationResult getResult();

    int getLabelResId();

    void setTime(double time);

    void fill(int elements);

    List<Integer> getList();


    Map<Integer, Integer> getMap();
}

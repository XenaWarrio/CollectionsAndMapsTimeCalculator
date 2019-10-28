package dx.queen.newcalculationandmaps.dto.task;

import java.util.List;
import java.util.Map;

import dx.queen.newcalculationandmaps.dto.CalculationResult;

public interface TaskData {
    CalculationResult getResult();

    int getRes();

    void setTime(double time);

    void fill(int elements);

    List<Integer> getList();

    Map<Integer, Integer> getMap();
}

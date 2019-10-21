package dx.queen.newcalculationandmaps.model.supplier;

import java.util.ArrayList;
import java.util.List;

import dx.queen.newcalculationandmaps.dto.CalculationResult;
import dx.queen.newcalculationandmaps.dto.TaskData;

public class MapsTasksSupplier implements TaskSupplier {

    @Override
    public int getCollectionCount() {
        return 2;
    }

    @Override
    public List<TaskData> getTasks() {
        final List<TaskData> tasks = new ArrayList<>(21);
        // TODO ksenia: fill
        return tasks;
    }

    @Override
    public List<CalculationResult> getInitialResults() {
        final List<CalculationResult> results = new ArrayList<>(21);
        for (TaskData td : getTasks()) {
            results.add(td.getResult());
        }
        return results;
    }
}

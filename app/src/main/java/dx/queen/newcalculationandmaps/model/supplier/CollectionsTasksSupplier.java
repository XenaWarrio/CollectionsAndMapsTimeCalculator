package dx.queen.newcalculationandmaps.model.supplier;

import java.util.ArrayList;
import java.util.List;

import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.CalculationResult;
import dx.queen.newcalculationandmaps.dto.Tags;
import dx.queen.newcalculationandmaps.dto.TaskData;

public class CollectionsTasksSupplier implements TaskSupplier {
    @Override
    public int getCollectionCount() {
        return 3;
    }

    @Override
    public List<TaskData> getTasks() {
        final List<TaskData> tasks = new ArrayList<>(21);
        tasks.add(new TaskData(new ArrayList<>(), R.string.add_to_array_list, Tags.ADD_TO_START_ARRAY_LIST));
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

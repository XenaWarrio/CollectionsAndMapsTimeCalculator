package dx.queen.newcalculationandmaps.model.supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.CalculationResult;
import dx.queen.newcalculationandmaps.dto.task.MapTaskData;
import dx.queen.newcalculationandmaps.dto.task.TaskData;

public class MapsTasksSupplier implements TaskSupplier {

    @Override
    public int getCollectionCount() {
        return 2;
    }

    @Override
    public List<TaskData> getTasks() {
        final List<TaskData> tasks = new ArrayList<>(6);
        tasks.add(new MapTaskData(new HashMap<Integer, Integer>(), R.string.add_to_hashmap));
        tasks.add(new MapTaskData(new TreeMap<Integer, Integer>(), R.string.add_to_treemapmap));

        tasks.add(new MapTaskData(new HashMap<Integer, Integer>(), R.string.search_hashmap));
        tasks.add(new MapTaskData(new HashMap<Integer, Integer>(), R.string.search_treemap));

        tasks.add(new MapTaskData(new HashMap<Integer, Integer>(), R.string.remove_hashmap));
        tasks.add(new MapTaskData(new HashMap<Integer, Integer>(), R.string.remove_treemap));

        return tasks;
    }

    @Override
    public List<CalculationResult> getInitialResults() {
        final List<CalculationResult> results = new ArrayList<>(6);
        for (TaskData td : getTasks()) {
            results.add(td.getResult());
        }
        return results;
    }
}

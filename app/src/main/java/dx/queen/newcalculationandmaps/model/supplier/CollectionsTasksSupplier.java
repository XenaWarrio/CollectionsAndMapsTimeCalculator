package dx.queen.newcalculationandmaps.model.supplier;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.CalculationResult;
import dx.queen.newcalculationandmaps.dto.task.ListTaskData;
import dx.queen.newcalculationandmaps.dto.task.TaskData;

public class CollectionsTasksSupplier implements TaskSupplier {
    @Override
    public int getCollectionCount() {
        return 3;
    }

    @Override
    public List<TaskData> getTasks() {
        final List<TaskData> tasks = new ArrayList<>(21);
        tasks.add(new ListTaskData(new ArrayList<>(), R.string.add_to_start_array_list));
        tasks.add(new ListTaskData(new ArrayList<>(), R.string.add_to_middle_array_list));
        tasks.add(new ListTaskData(new ArrayList<>(), R.string.add_to_end_array_list));
        tasks.add(new ListTaskData(new ArrayList<>(), R.string.remove_start_array_list));
        tasks.add(new ListTaskData(new ArrayList<>(), R.string.remove_middle_array_list));
        tasks.add(new ListTaskData(new ArrayList<>(), R.string.remove_end_array_list));
        tasks.add(new ListTaskData(new ArrayList<>(), R.string.search_array));
        tasks.add(new ListTaskData(new LinkedList<>(), R.string.add_to_start_linked_list));
        tasks.add(new ListTaskData(new LinkedList<>(), R.string.add_to_middle_linked_list));
        tasks.add(new ListTaskData(new LinkedList<>(), R.string.add_to_end_linked_list));
        tasks.add(new ListTaskData(new LinkedList<>(), R.string.remove_start_linked_list));
        tasks.add(new ListTaskData(new LinkedList<>(), R.string.remove_middle_linked_list));
        tasks.add(new ListTaskData(new LinkedList<>(), R.string.remove_end_linked_list));
        tasks.add(new ListTaskData(new LinkedList<>(), R.string.search_linked));
        tasks.add(new ListTaskData(new CopyOnWriteArrayList<>(), R.string.add_to_start_caw));
        tasks.add(new ListTaskData(new CopyOnWriteArrayList<>(), R.string.add_to_middle_caw));
        tasks.add(new ListTaskData(new CopyOnWriteArrayList<>(), R.string.add_to_end_caw));
        tasks.add(new ListTaskData(new CopyOnWriteArrayList<>(), R.string.remove_start_caw));
        tasks.add(new ListTaskData(new CopyOnWriteArrayList<>(), R.string.remove_middle_caw));
        tasks.add(new ListTaskData(new CopyOnWriteArrayList<>(), R.string.remove_end_caw));
        tasks.add(new ListTaskData(new CopyOnWriteArrayList<>(), R.string.search_caw));
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

package dx.queen.newcalculationandmaps.model.supplier;

import java.util.List;

import dx.queen.newcalculationandmaps.dto.CalculationResult;
import dx.queen.newcalculationandmaps.dto.task.TaskData;

public interface TaskSupplier {
    int getCollectionCount();

    List<TaskData> getTasks();

    List<CalculationResult> getInitialResults();
}

package dx.queen.newcalculationandmaps.model.supplier;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.CalculationResult;
import dx.queen.newcalculationandmaps.dto.task.MapTaskData;
import dx.queen.newcalculationandmaps.dto.task.TaskData;

@RunWith(MockitoJUnitRunner.class)
public class MapsTasksSupplierTest {
    private MapsTasksSupplier mapsTasksSupplier= new MapsTasksSupplier();

    @Test
    public void getCollectionCount() {
        int size = mapsTasksSupplier.getCollectionCount();
        Assert.assertEquals(size, 2);
    }


    @Test
    public void getTasks() {
        final List<TaskData> tasks = mapsTasksSupplier.getTasks();
        Assert.assertEquals(tasks.size(), 6);

        final MapTaskData task = new MapTaskData(new HashMap<Integer, Integer>(), R.string.search_hashmap);
        Assert.assertEquals(tasks.get(2).getLabelResId(), task.getLabelResId());

    }

    @Test
    public void getInitialResults() {
        final List<CalculationResult> listForCheck = new ArrayList<>(6);
        final List<CalculationResult> results = mapsTasksSupplier.getInitialResults();
        Assert.assertNotNull(results);

        for(TaskData td : mapsTasksSupplier.getTasks()){
            listForCheck.add(td.getResult());
        }

        Assert.assertEquals(listForCheck.get(3).labelResId, results.get(3).labelResId);

    }
}
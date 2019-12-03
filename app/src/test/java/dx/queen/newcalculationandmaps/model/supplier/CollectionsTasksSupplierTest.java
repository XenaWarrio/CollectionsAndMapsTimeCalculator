package dx.queen.newcalculationandmaps.model.supplier;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.CalculationResult;
import dx.queen.newcalculationandmaps.dto.task.ListTaskData;
import dx.queen.newcalculationandmaps.dto.task.TaskData;

@RunWith(MockitoJUnitRunner.class)
public class CollectionsTasksSupplierTest {
    private CollectionsTasksSupplier collectionsTasksSupplier = new CollectionsTasksSupplier();


    @Test
    public void getCollectionCount() {
        int size = collectionsTasksSupplier.getCollectionCount();
        Assert.assertEquals(size, 3);
    }


    @Test
    public void getTasks() {

        final List<TaskData> tasks = collectionsTasksSupplier.getTasks();
        Assert.assertEquals(tasks.size(), 21);

        final ListTaskData task = new ListTaskData(new ArrayList<Integer>(), R.string.add_to_end_array_list);
        Assert.assertEquals(tasks.get(2).getLabelResId(), task.getLabelResId());
    }



    @Test
    public void getInitialResults() {
        final List<CalculationResult> listForCheck = new ArrayList<>(21);
        final List<CalculationResult> results = collectionsTasksSupplier.getInitialResults();
        Assert.assertNotNull(results);

        for(TaskData td : collectionsTasksSupplier.getTasks()){
            listForCheck.add(td.getResult());
        }

        Assert.assertEquals(listForCheck.get(3).labelResId, results.get(3).labelResId);

    }
}
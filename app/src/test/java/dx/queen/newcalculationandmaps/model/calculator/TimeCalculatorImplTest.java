package dx.queen.newcalculationandmaps.model.calculator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.task.TaskData;

@RunWith(MockitoJUnitRunner.class)
public class TimeCalculatorImplTest {

    @Mock
    TaskData taskData;

    TimeCalculatorImpl timeCalculator;


    @Test
    public void execAndSetupTime() {
        timeCalculator = new TimeCalculatorImpl();

        List<Integer> listForCheck = new ArrayList<>();
        Map<Integer,Integer> mapForCheck = new HashMap<>();
        ArgumentCaptor<Double> captor = ArgumentCaptor.forClass(Double.class);

        Mockito.when(taskData.getList()).thenReturn(listForCheck);
        int listBefore = listForCheck.size();

        Mockito.when(taskData.getMap()).thenReturn(mapForCheck);
        int mapBefore = mapForCheck.size();

        Mockito.when(taskData.getLabelResId()).thenReturn(R.string.add_to_start_array_list);
        timeCalculator.execAndSetupTime(taskData);
        Assert.assertNotEquals(listBefore, taskData.getList().size());

        Mockito.when(taskData.getLabelResId()).thenReturn(R.string.add_to_middle_array_list);
        timeCalculator.execAndSetupTime(taskData);
        Assert.assertEquals(listBefore + 2, taskData.getList().size());

        Mockito.when(taskData.getLabelResId()).thenReturn(R.string.add_to_end_array_list);
        timeCalculator.execAndSetupTime(taskData);
        Assert.assertTrue(taskData.getList().contains(0));

        Mockito.when(taskData.getLabelResId()).thenReturn(R.string.remove_start_array_list);
        timeCalculator.execAndSetupTime(taskData);
        Assert.assertEquals(taskData.getList().size(), listForCheck.size());

        Mockito.when(taskData.getLabelResId()).thenReturn(R.string.remove_middle_array_list);
        timeCalculator.execAndSetupTime(taskData);
        Assert.assertNotEquals(taskData.getList().size(), listBefore);

        Mockito.when(taskData.getLabelResId()).thenReturn(R.string.search_array);
        timeCalculator.execAndSetupTime(taskData);
        Assert.assertEquals(taskData.getList().get(taskData.getList().size()/2), listForCheck.get(listForCheck.size() / 2));

        Mockito.when(taskData.getLabelResId()).thenReturn(R.string.add_to_hashmap);
        timeCalculator.execAndSetupTime(taskData);
        Assert.assertNotEquals(mapBefore, taskData.getMap().size()+1);

        Mockito.when(taskData.getLabelResId()).thenReturn(R.string.remove_hashmap);
        timeCalculator.execAndSetupTime(taskData);
        Assert.assertEquals(mapBefore, taskData.getMap().size());

        Mockito.when(taskData.getLabelResId()).thenReturn(R.string.search_hashmap);
        timeCalculator.execAndSetupTime(taskData);
        Assert.assertEquals(mapForCheck.get(mapBefore/2), taskData.getMap().get(taskData.getMap().size() / 2));

        Mockito.verify(taskData, Mockito.times(9)).getLabelResId();
        Mockito.verify(taskData, Mockito.times(13)).getList();
        Mockito.verify(taskData, Mockito.times(9)).setTime(captor.capture());
        Mockito.verify(taskData, Mockito.times(7)).getMap();

        Mockito.verifyNoMoreInteractions(taskData);







    }
}
package dx.queen.newcalculationandmaps.dto.task;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;
import java.util.List;

import dx.queen.newcalculationandmaps.R;

@RunWith(MockitoJUnitRunner.class)
public class ListTaskDataTest {

    private final int COUNT_OF_ELEMENTS = 6;

    @Mock
    List<Integer> collectionArray;

    ListTaskData taskData;


    @Before
    public void setUp() {
        taskData = new ListTaskData(collectionArray, R.string.add_to_end_array_list);
    }

    @Test
    public void fill() {

        ArgumentCaptor<Collection<Integer>> argumentCaptor = ArgumentCaptor.forClass(List.class);


        taskData.fill(COUNT_OF_ELEMENTS);

        Mockito.verify(collectionArray).addAll(argumentCaptor.capture());
        Assert.assertEquals(argumentCaptor.getValue().size(), COUNT_OF_ELEMENTS);
        Mockito.verifyNoMoreInteractions(collectionArray);


    }
}
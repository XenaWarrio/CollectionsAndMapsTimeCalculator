package dx.queen.newcalculationandmaps.dto.task;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

import dx.queen.newcalculationandmaps.R;

@RunWith(MockitoJUnitRunner.class)
public class MapTaskDataTest {

    private final int COUNT_OF_ELEMENTS = 6;

    @Mock
    Map<Integer, Integer> map;

    private MapTaskData taskData;

    @Before
    public void setUp() {
        taskData = new MapTaskData(map, R.string.add_to_treemapmap);
    }

    @Test
    public void fill() {
        final ArgumentCaptor<Integer> keyCaptor = ArgumentCaptor.forClass(Integer.class);
        final ArgumentCaptor<Integer> valueCaptor = ArgumentCaptor.forClass(Integer.class);

        taskData.fill(COUNT_OF_ELEMENTS);

        Mockito.verify(map, Mockito.times(6)).put(keyCaptor.capture(),valueCaptor.capture());

        Assert.assertEquals(keyCaptor.getAllValues().size() ,COUNT_OF_ELEMENTS);

        Mockito.verifyNoMoreInteractions(map);
    }
}
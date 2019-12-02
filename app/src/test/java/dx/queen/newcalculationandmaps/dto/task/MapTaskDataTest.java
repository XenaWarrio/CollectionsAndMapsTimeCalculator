package dx.queen.newcalculationandmaps.dto.task;

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
        final ArgumentCaptor<Map<Integer, Integer>> argumentCaptor = ArgumentCaptor.forClass(Map.class);

        taskData.fill(COUNT_OF_ELEMENTS);

        // DO NOT CALL MOCK'S METHOD IN TESTS!!!!! read and remove
        // map.putAll(argumentCaptor.capture());

        Mockito.verifyNoMoreInteractions(map);
    }
}
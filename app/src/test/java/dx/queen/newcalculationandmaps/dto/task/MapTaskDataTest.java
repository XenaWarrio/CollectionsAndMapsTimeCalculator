package dx.queen.newcalculationandmaps.dto.task;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

import dx.queen.newcalculationandmaps.R;

@RunWith(MockitoJUnitRunner.class)
public class MapTaskDataTest {

    private final int COUNT_OF_ELEMENTS = 6;

    @Mock
    Map<Integer,Integer> map;

    MapTaskData taskData;



    @Before
    public void setUp(){
        taskData = new MapTaskData(map, R.string.add_to_treemapmap);


    }


    @Test
    public void fill() {
        ArgumentCaptor<Map<Integer,Integer>> argumentCaptor = ArgumentCaptor.forClass(Map.class);
        taskData.fill(COUNT_OF_ELEMENTS);

        map.putAll(argumentCaptor.capture());

        for (int i = 0; i < COUNT_OF_ELEMENTS; i++) {
            map.put(i, 3);
            map.putAll(argumentCaptor.capture());

        }
        int mapSize = argumentCaptor.getValue().size();
        Assert.assertEquals(mapSize, COUNT_OF_ELEMENTS);







    }
}
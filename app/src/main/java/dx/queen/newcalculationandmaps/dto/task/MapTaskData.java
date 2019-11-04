package dx.queen.newcalculationandmaps.dto.task;

import java.util.Map;

public class MapTaskData extends AbstractTaskData {
    private final Map<Integer, Integer> map;

    public MapTaskData(Map<Integer, Integer> map, int labelResId) {
        super(labelResId);
        this.map = map;
    }

    public void fill(int elements) {
       for(int i = 0; i < elements; i++){
           map.put(i,3);
       }
    }

    @Override
    public Map<Integer, Integer> getMap() {
        return map;
    }
}

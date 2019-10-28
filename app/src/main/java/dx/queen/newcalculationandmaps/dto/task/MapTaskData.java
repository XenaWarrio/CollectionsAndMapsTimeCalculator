package dx.queen.newcalculationandmaps.dto.task;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapTaskData extends AbstractTaskData {
    private final Map<Integer, Integer> map;

    public MapTaskData(HashMap<Integer, Integer> map, int labelResId) {
        super(labelResId);
        this.map = map;
    }
   public MapTaskData(TreeMap<Integer, Integer> map, int labelResId) {
        super(labelResId);
        this.map = map;
    }

    public void fill(int elements) {
        // TODO: fill map
    }

    @Override
    public Map<Integer, Integer> getMap() {
        return map;
    }
}

package dx.queen.newcalculationandmaps.model;

import java.util.HashMap;
import java.util.TreeMap;

@Deprecated
public class FillMaps {
    int elements;
    private TreeMap<Integer, Integer> treemap;
    private HashMap<Integer, Integer> hashMap;

    public FillMaps(int elements) {
        this.elements = elements;

    }


    public TreeMap<Integer, Integer> fillTreeMap() {
        treemap = new TreeMap<>();
        int i = 0;
        for (int y = 0; y <= elements; y++) {
            treemap.put(i, y);
            i++;
        }
        return treemap;
    }

    public HashMap<Integer, Integer> fillHashMap() {
        hashMap = new HashMap<>();
        int i = 0;
        for (int y = 0; y <= elements; y++) {
            hashMap.put(i, y);
            i++;
        }
        return hashMap;
    }
}


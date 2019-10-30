package dx.queen.newcalculationandmaps.dto.task;

import java.util.Collections;
import java.util.List;

public class ListTaskData extends AbstractTaskData {
    private final List<Integer> collection;

    public ListTaskData(List<Integer> collection, int labelResId) {
        super(labelResId);
        this.collection = collection;
    }



    public void fill(int elements) {
        collection.addAll(Collections.nCopies(elements, 1));
    }

    @Override
    public List<Integer> getList() {
        return collection;
    }
}

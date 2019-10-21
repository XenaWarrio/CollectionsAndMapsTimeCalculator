package dx.queen.newcalculationandmaps.model.calculator;

import java.util.List;

import dx.queen.newcalculationandmaps.dto.Tags;
import dx.queen.newcalculationandmaps.dto.TaskData;

public class TimeCalculatorImpl implements TimeCalculator {

    @Override
    public void execAndSetupTime(TaskData td) {
        switch (td.getTag()) {
            case Tags.ADD_TO_START_ARRAY_LIST: {
                final long start = System.nanoTime();
                addToStartList(td.collection);
                td.setTime((System.nanoTime() - start) / 1000D);
            }
            break;

            // TODO: fill + measure time
        }
    }

    private void addToStartList(List<Integer> collection) {
        collection.add(0, 1);
    }
}

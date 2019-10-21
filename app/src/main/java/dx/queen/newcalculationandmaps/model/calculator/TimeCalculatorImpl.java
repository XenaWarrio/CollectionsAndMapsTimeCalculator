package dx.queen.newcalculationandmaps.model.calculator;

import java.util.List;

import dx.queen.newcalculationandmaps.dto.Tags;
import dx.queen.newcalculationandmaps.dto.TaskData;

public class TimeCalculatorImpl implements TimeCalculator {

    @Override
    public void execAndSetupTime(TaskData td) {
        switch (td.getTag()) {
            case Tags.ADD_TO_START_ARRAY_LIST: {
                addToStartList(td.collection);
            }
            break;

            // TODO ksenia: fill
        }
    }

    private void addToStartList(List<Integer> collection) {
        collection.add(0, 1);
    }
}

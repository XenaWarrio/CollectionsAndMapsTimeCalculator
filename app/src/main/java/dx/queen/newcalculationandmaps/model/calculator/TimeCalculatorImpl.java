package dx.queen.newcalculationandmaps.model.calculator;

import java.util.List;

import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.task.TaskData;

public class TimeCalculatorImpl implements TimeCalculator {

    @Override
    public void execAndSetupTime(TaskData td) {
        switch (td.getRes()) {
            case R.string.add_to_start_array_list:
                final long start = System.nanoTime();
                addToStartList(td.getList());
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.add_to_middle_array_list:
                break;
            case R.string.add_to_end_array_list:
                break;
            case R.string.remove_start_array_list:
                break;
            case R.string.remove_middle_array_list:
                break;
            case R.string.remove_end_array_list:
                break;
            case R.string.search_array:
                break;
            case R.string.add_to_start_linked_list:
                break;
            case R.string.add_to_middle_linked_list:
                break;
            case R.string.add_to_end_linked_list:
                break;
            case R.string.remove_start_linked_list:
                break;
            case R.string.remove_middle_linked_list:
                break;
            case R.string.remove_end_linked_list:
                break;
            case R.string.search_linked:
                break;
            case R.string.add_to_start_caw:
                break;
            case R.string.add_to_end_caw:
                break;
            case R.string.remove_start_caw:
                break;
            case R.string.remove_middle_caw:
                break;
            case R.string.remove_end_caw:
                break;
            case R.string.search_caw:
                break;


            // TODO: fill + measure time
        }
    }

    private void addToStartList(List<Integer> collection) {
        collection.add(0, 1);
    }
}

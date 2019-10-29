package dx.queen.newcalculationandmaps.model.calculator;

import java.util.List;

import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.task.TaskData;

public class TimeCalculatorImpl implements TimeCalculator {

    @Override
    public void execAndSetupTime(TaskData td) {
        final long start = System.nanoTime();
        switch (td.getLabelResId()) {
            case R.string.add_to_start_caw:
            case R.string.add_to_start_linked_list:
            case R.string.add_to_start_array_list:
                td.getList().add(0);
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.add_to_middle_caw:
            case R.string.add_to_middle_linked_list:
            case R.string.add_to_middle_array_list:
                final List<Integer> list = td.getList();
                td.getList().add(list.size() / 2, 0);
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.add_to_end_caw:
            case R.string.add_to_end_linked_list:
            case R.string.add_to_end_array_list:
                // todo fill
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.remove_start_caw:
            case R.string.remove_start_linked_list:
            case R.string.remove_start_array_list:
                // todo fill
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.remove_middle_caw:
            case R.string.remove_middle_linked_list:
            case R.string.remove_middle_array_list:
                // todo fill
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.remove_end_caw:
            case R.string.remove_end_linked_list:
            case R.string.remove_end_array_list:
                // todo fill
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.search_caw:
            case R.string.search_linked:
            case R.string.search_array:
                // todo fill
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.add_to_treemapmap:
            case R.string.add_to_hashmap:
                // todo fill
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.remove_treemap:
            case R.string.remove_hashmap:
                // todo fill
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.search_treemap:
            case R.string.search_hashmap:
                // todo fill
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
        }
    }
}



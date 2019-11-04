package dx.queen.newcalculationandmaps.model.calculator;

import java.util.List;
import java.util.Map;

import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.task.TaskData;

public class TimeCalculatorImpl implements TimeCalculator {


    @Override
    public void execAndSetupTime(TaskData td) {
        final long start = System.nanoTime();
        switch (td.getLabelResId()) {
            // unite cases by action
            case R.string.add_to_start_caw:
            case R.string.add_to_start_array_list:
            case R.string.add_to_start_linked_list:
                add(td.getList());
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.add_to_middle_caw:
            case R.string.add_to_middle_linked_list:
            case R.string.add_to_middle_array_list:
                addiMiddle(td.getList());
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.add_to_end_caw:
            case R.string.add_to_end_linked_list:
            case R.string.add_to_end_array_list:
                addEnd(td.getList());
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;


            case R.string.remove_start_caw:
            case R.string.remove_start_linked_list:
            case R.string.remove_start_array_list:
                removeStart(td.getList());
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;


            case R.string.remove_middle_caw:
            case R.string.remove_middle_linked_list:
            case R.string.remove_middle_array_list:


                removeMiddle(td.getList());
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.remove_end_caw:
            case R.string.remove_end_linked_list:
            case R.string.remove_end_array_list:


                removeEndList(td.getList());
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;


            case R.string.search_caw:
            case R.string.search_linked:
            case R.string.search_array:


                searchElement(td.getList());
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;


            case R.string.add_to_treemapmap:
            case R.string.add_to_hashmap:

                addMap(td.getMap());
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;


            case R.string.remove_treemap:
            case R.string.remove_hashmap:

                removeMap(td.getMap());
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;


            case R.string.search_treemap:
            case R.string.search_hashmap:

                searchMap(td.getMap());
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;


        }
    }

    private synchronized void add(List<Integer> list) {
        list.add(0, 0);
    }

    private synchronized void addiMiddle(List<Integer> list) {
        list.add(list.size() / 2, 0);
    }

    private synchronized void addEnd(List<Integer> list) {
        list.add(0);
    }

    private synchronized void removeStart(List<Integer> list) {
        list.remove(0);
    }

    private synchronized void removeMiddle(List<Integer> list) {
        list.remove(list.size() / 2);
    }

    private synchronized void removeEndList(List<Integer> list) {
        list.remove(0);
    }

    private synchronized void searchElement(List<Integer> list) {
        list.get(list.indexOf(0));

    }


    private synchronized void addMap(Map<Integer, Integer> map) {
        map.put(0, 0);
    }

    private synchronized void searchMap(Map<Integer, Integer> map) {
        map.get(0);
    }


    private synchronized void removeMap(Map<Integer, Integer> map) {
        map.remove(0);
    }
}



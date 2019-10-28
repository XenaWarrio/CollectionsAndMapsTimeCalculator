package dx.queen.newcalculationandmaps.model.calculator;

import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.TasksCollection;
import dx.queen.newcalculationandmaps.dto.TasksMap;
import dx.queen.newcalculationandmaps.dto.task.TaskData;

public class TimeCalculatorImpl implements TimeCalculator {
    TasksCollection task;
    TasksMap tasksMap;

    @Override
    public void execAndSetupTime(TaskData td) {
        final long start = System.nanoTime();
        switch (td.getRes()) {
            case R.string.add_to_start_array_list:
                task.addingStartList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.add_to_middle_array_list:
                task.addingMiddleList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.add_to_end_array_list:
                task.addingEndList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.remove_start_array_list:
                task.removeStartList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.remove_middle_array_list:
                task.removeMiddleList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.remove_end_array_list:
                task.removeEndList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.search_array:
                task.searchElementList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.add_to_start_linked_list:
                task.addingStartLinkedList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.add_to_middle_linked_list:
                task.addingMiddleLinkedList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.add_to_end_linked_list:
                task.addingEndLinkedList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.remove_start_linked_list:
                task.removeStartLinkedList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.remove_middle_linked_list:
                task.removeEndLinkedList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.remove_end_linked_list:
                task.removeEndLinkedList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.search_linked:
                task.searchElementLinkedList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.add_to_start_caw:
                task.addingStartCopyOnWriteArrayList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.add_to_middle_caw:
                task.addingMiddleCopyOnWriteArrayList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.add_to_end_caw:
                task.addingEndCopyOnWriteArrayList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.remove_start_caw:
                task.removeStartCopyOnWriteArrayList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.remove_middle_caw:
                task.removeMiddleCopyOnWriteArrayList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.remove_end_caw:
                task.removeEndCopyOnWriteArrayList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.search_caw:
                task.searchElementCopyOnWriteArrayList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.add_to_hashmap:
                tasksMap.addingHashMap();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.remove_hashmap:
                tasksMap.removeHashMap();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.search_hashmap:
                tasksMap.searchHashMap();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.add_to_treemapmap:
                tasksMap.addingTreeMap();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.remove_treemap:
                tasksMap.removeTreeMap();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.search_treemap:
                tasksMap.searchTreeMap();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;


            // TODO: fill + measure time
        }
    }
//
//    private void addToStartList(List<Integer> collection) {
//        collection.add(0, 1);
//    }
}

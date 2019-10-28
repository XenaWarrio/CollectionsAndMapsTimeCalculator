package dx.queen.newcalculationandmaps.model.calculator;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.TasksCollection;
import dx.queen.newcalculationandmaps.dto.TasksMap;
import dx.queen.newcalculationandmaps.dto.task.TaskData;

public class TimeCalculatorImpl implements TimeCalculator {
    Map<Integer, Integer> treemap;
    Map<Integer, Integer> hashMap;
    List<Integer> arrayList;
    List<Integer> linkedList;
    List<Integer> copyOnWriteArrayList;
    int element = 5;
    int key = 254;
    int value = 23;

    @Override
    public void execAndSetupTime(TaskData td) {
        final long start = System.nanoTime();
        arrayList= new ArrayList<>();
        arrayList = td.getList();
        linkedList = new LinkedList<>();
        linkedList = td.getList();
        copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList = td.getList();
        hashMap = new HashMap<>();
        hashMap = td.getMap();
        treemap = new TreeMap<>();
        treemap = td.getMap();
        switch (td.getLabelResId()) {
            case R.string.add_to_start_array_list:
                addingStartList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.add_to_middle_array_list:
                addingMiddleList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.add_to_end_array_list:
                addingEndList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.remove_start_array_list:
                removeStartList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.remove_middle_array_list:
                removeMiddleList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.remove_end_array_list:
                removeEndList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.search_array:
                searchElementList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.add_to_start_linked_list:
                addingStartLinkedList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.add_to_middle_linked_list:
                addingMiddleLinkedList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.add_to_end_linked_list:
                addingEndLinkedList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.remove_start_linked_list:
                removeStartLinkedList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.remove_middle_linked_list:
                removeEndLinkedList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.remove_end_linked_list:
                removeEndLinkedList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.search_linked:
                searchElementLinkedList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.add_to_start_caw:
                addingStartCopyOnWriteArrayList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.add_to_middle_caw:
                addingMiddleCopyOnWriteArrayList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.add_to_end_caw:
                addingEndCopyOnWriteArrayList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.remove_start_caw:
                removeStartCopyOnWriteArrayList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.remove_middle_caw:
                removeMiddleCopyOnWriteArrayList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.remove_end_caw:
                removeEndCopyOnWriteArrayList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;
            case R.string.search_caw:
                searchElementCopyOnWriteArrayList();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.add_to_hashmap:
                addingHashMap();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.remove_hashmap:
                removeHashMap();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.search_hashmap:
                searchHashMap();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.add_to_treemapmap:
                addingTreeMap();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.remove_treemap:
                removeTreeMap();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;

            case R.string.search_treemap:
                searchTreeMap();
                td.setTime((System.nanoTime() - start) / 1_000_000D);
                break;


            // TODO: fill + measure time
        }
    }



    public  synchronized void addingStartList() {
        arrayList.add(0, element);
    }

    public synchronized void addingMiddleList() {
        arrayList.add(arrayList.size() / 2, element);
    }

    public synchronized void addingEndList() {
        arrayList.add(element);
    }

    public synchronized void removeStartList() {
        arrayList.subList(0, 0).clear();
    }

    public synchronized void removeMiddleList() {
        arrayList.subList(arrayList.size() / 2, element / 2).clear();
    }

    public synchronized void removeEndList() {
        arrayList.remove(element);
    }

    public synchronized void searchElementList() {
        arrayList.get(arrayList.indexOf(element));

    }


    public synchronized void addingStartLinkedList() {
        linkedList.addFirst(element);
    }

    public synchronized void addingMiddleLinkedList() {
        linkedList.add(linkedList.size() / 2, element);
    }

    public synchronized void addingEndLinkedList() {
        linkedList.addLast(element);
    }

    public synchronized void removeStartLinkedList() {
        linkedList.removeFirst();
    }

    public synchronized void removeMiddleLinkedList() {
        linkedList.remove(linkedList.size() / 2);

    }

    public synchronized void removeEndLinkedList() {
        linkedList.removeLast();
    }

    public synchronized void searchElementLinkedList() {
        linkedList.get(linkedList.indexOf(element));
    }


    public void addingStartCopyOnWriteArrayList() {
        copyOnWriteArrayList.add(0, element);
    }

    public void addingMiddleCopyOnWriteArrayList() {
        copyOnWriteArrayList.add(copyOnWriteArrayList.size() / 2, element);
    }

    public void addingEndCopyOnWriteArrayList() {
        copyOnWriteArrayList.add(element);
    }

    public void removeStartCopyOnWriteArrayList() {
        copyOnWriteArrayList.subList(0, 0).clear();
    }

    public void removeMiddleCopyOnWriteArrayList() {
        copyOnWriteArrayList.subList(copyOnWriteArrayList.size() / 2, copyOnWriteArrayList.size() / 2).clear();
    }

    public void removeEndCopyOnWriteArrayList() {
        copyOnWriteArrayList.remove(element);
    }

    public void searchElementCopyOnWriteArrayList() {
        copyOnWriteArrayList.get(copyOnWriteArrayList.indexOf(element));

    }





    public synchronized void addingTreeMap() {
        treemap.put(key, value);
    }

    public synchronized void searchTreeMap() {
        treemap.get(key);
    }


    public synchronized void removeTreeMap() {
        treemap.remove(key);
    }

    public synchronized void addingHashMap() {
        hashMap.put(key, value);
    }

    public synchronized void searchHashMap() {
        hashMap.get(key);
    }


    public synchronized void removeHashMap() {
        hashMap.remove(key);
    }


}



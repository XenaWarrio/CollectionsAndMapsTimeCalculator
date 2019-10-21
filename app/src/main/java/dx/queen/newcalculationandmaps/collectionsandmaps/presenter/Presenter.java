package dx.queen.newcalculationandmaps.collectionsandmaps.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import dx.queen.collectionsandmaps.MainInterface;
import dx.queen.collectionsandmaps.dto.TasksCollection;
import dx.queen.collectionsandmaps.dto.TasksMap;
import dx.queen.collectionsandmaps.model.CalculateTime;
import dx.queen.collectionsandmaps.model.FillCollection;
import dx.queen.collectionsandmaps.model.FillMaps;
import dx.queen.collectionsandmaps.model.NamesListCollection;
import dx.queen.collectionsandmaps.model.NamesListMaps;

public class Presenter implements MainInterface.Presenter {

    List<String> namesCollection;
    FillCollection fillCollection;
    ExecutorService executor;
    TasksCollection tasksCollection;
    CalculateTime time;
    FillMaps fillMaps;
    TasksMap taskMap;
    List<String> namesMaps;
    int countOfElement;
    int countOfthreads;
    private List<String> listforCollection;
    private List<String> listforMaps;
    private ArrayList<Integer> arrayList;
    private LinkedList<Integer> linkedList;
    private CopyOnWriteArrayList<Integer> copyOnWriteArrayList;
    private TreeMap<Integer, Integer> treeMap;
    private HashMap<Integer, Integer> hashMap;
    private MainInterface.View view;

    public Presenter(MainInterface.View view) {
        this.view = view;
    }


    @Override
    public void buttonWasClicked(String elements, String threads, String mode) {
        countOfElement = Integer.valueOf(elements);
        countOfthreads = Integer.valueOf(threads);
        fillCollection = new FillCollection(countOfElement);
        fillMaps = new FillMaps(countOfElement);
        switch (mode) {
            case "collection":
                executingCollection(countOfthreads);
                break;
            case "maps":
                executingMaps(countOfthreads);
                break;
        }
    }

    private List<String> executingCollection(int threads) {
        namesCollection = NamesListCollection.fillNamesList();
        arrayList = fillCollection.fillArrayList();
        linkedList = fillCollection.fillLinkedList();
        copyOnWriteArrayList = fillCollection.fillCopyOnWriteList();

        executor = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < 21; i++) {
            if (i < 7) {
                tasksCollection = new TasksCollection(arrayList, i);
                time.startCalculation();
                executor.execute(tasksCollection);
                listforCollection.add(namesCollection.get(i) + " " + time.stopCalculation());
            }
            if (7 > i & i < 14) {
                tasksCollection = new TasksCollection(linkedList, i);
                time.startCalculation();
                executor.execute(tasksCollection);
                listforCollection.add(namesCollection.get(i) + " " + time.stopCalculation());

            }
            if (i > 14) {
                tasksCollection = new TasksCollection(copyOnWriteArrayList, i);
                time.startCalculation();
                executor.execute(tasksCollection);
                listforCollection.add(namesCollection.get(i) + " " + time.stopCalculation());
            }
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Log.d("RRR", e.toString());
        }
        return listforCollection;
    }

    private List<String> executingMaps(int threads) {
        namesMaps = NamesListMaps.fillNamesList();
        executor = Executors.newFixedThreadPool(threads);
        treeMap = fillMaps.fillTreeMap();
        hashMap = fillMaps.fillHashMap();

        for (int i = 0; i < 6; i++) {
            if (i < 3) {
                taskMap = new TasksMap(treeMap, i);
                time.startCalculation();
                executor.execute(taskMap);
                listforMaps.add(namesMaps.get(i) + " " + time.stopCalculation());
            }
            if (i >= 3) {
                taskMap = new TasksMap(hashMap, i);
                time.startCalculation();
                executor.execute(taskMap);
                listforMaps.add(namesMaps.get(i) + " " + time.stopCalculation());
            }
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Log.d("RRR", e.toString());
        }

        return listforMaps;
    }

}


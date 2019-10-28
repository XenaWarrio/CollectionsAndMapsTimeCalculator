package dx.queen.newcalculationandmaps.dto;

import android.util.Log;

import java.util.HashMap;
import java.util.TreeMap;

public class TasksMap implements Runnable {

    TreeMap<Integer, Integer> treemap;
    HashMap<Integer, Integer> hashMap;
    int key = 254;
    int value = 23;
    int num;

//    public TasksMap(TreeMap<Integer, Integer> treemap, int num) {
//        this.treemap = treemap;
//        this.num = num;
//    }
//
//    public TasksMap(HashMap<Integer, Integer> hashMap, int num) {
//        this.num = num;
//        this.hashMap = hashMap;
//    }


    @Override
    public void run() {
        switch (num) {
            case 0:
                addingTreeMap();
                Log.d("RRR", "case 0");

                break;
            case 1:
                searchTreeMap();
                Log.d("RRR", "case1");

                break;
            case 2:
                removeTreeMap();
                Log.d("RRR", "case2");

                break;
            case 3:
                addingHashMap();
                Log.d("RRR", "case3");

                break;
            case 4:
                searchHashMap();
                Log.d("RRR", "case4");

                break;
            case 5:
                removeHashMap();
                Log.d("RRR", "case5");

        }

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


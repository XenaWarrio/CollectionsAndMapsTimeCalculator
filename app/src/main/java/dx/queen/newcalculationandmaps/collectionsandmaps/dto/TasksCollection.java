package dx.queen.newcalculationandmaps.collectionsandmaps.dto;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

public class TasksCollection implements Runnable {
    ArrayList<Integer> arrayList;
    LinkedList<Integer> linkedList;
    CopyOnWriteArrayList<Integer> copyOnWriteArrayList;
    int element = 5;
    int num;

    public TasksCollection(ArrayList<Integer> arrayList, int num) {
        this.num = num;
        this.arrayList = arrayList;
    }
    public TasksCollection(LinkedList<Integer> linkedList, int num) {
        this.num = num;
        this.linkedList = linkedList;
    }
    public TasksCollection(CopyOnWriteArrayList<Integer> copyOnWriteArrayList, int num) {
        this.num = num;
        this.copyOnWriteArrayList = copyOnWriteArrayList;
    }


    @Override
    public void run() {
        try {
            switch (num) {
                case 0:
                    addingStartList();
                    Log.d("RRR", "case 0");

                    break;
                case 1:
                    addingMiddleList();
                    Log.d("RRR", "case1");

                    break;
                case 2:
                    addingEndList();
                    Log.d("RRR", "case2");

                    break;
                case 3:
                    removeStartList();
                    Log.d("RRR", "case3");

                    break;
                case 4:
                    removeMiddleList();
                    Log.d("RRR", "case4");

                    break;
                case 5:
                    removeEndList();
                    Log.d("RRR", "case5");

                    break;
                case 6:
                    removeEndList();
                    Log.d("RRR", "case6");

                    break;
                case 7:
                    searchElementList();
                    Log.d("RRR", "case7");

                    break;
               case 8:
                    addingStartLinkedList();
                    Log.d("RRR", "case8");
                    break;
               case 9:
                    addingMiddleLinkedList();
                    Log.d("RRR", "case9");
                    break;
               case 10:
                    addingEndLinkedList();
                    Log.d("RRR", "case10");

                    break;
               case 11:
                    removeStartLinkedList();
                    Log.d("RRR", "cas11");

                    break;
               case 12:
                    removeMiddleLinkedList();
                    Log.d("RRR", "case12");

                    break;
               case 13:
                    removeEndLinkedList();
                    Log.d("RRR", "case13");

                    break;
               case 14:
                    searchElementLinkedList();
                    Log.d("RRR", "case14");

                    break;
               case 15:
                    addingStartCopyOnWriteArrayList();
                    Log.d("RRR", "case15");

                    break;
               case 16:
                    addingMiddleCopyOnWriteArrayList();
                    Log.d("RRR", "case16");

                    break;
               case 17:
                    addingEndCopyOnWriteArrayList();
                    Log.d("RRR", "case17");

                    break;
               case 18:
                    removeStartCopyOnWriteArrayList();
                    Log.d("RRR", "case18");

                    break;
               case 19:
                   removeMiddleCopyOnWriteArrayList();
                    Log.d("RRR", "case19");

                    break;
               case 20:
                    removeEndCopyOnWriteArrayList();
                    Log.d("RRR", "case20");

                    break;
               case 21:
                    searchElementCopyOnWriteArrayList();
                    Log.d("RRR", "case21");

                    break;
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private synchronized void addingStartList() {
        arrayList.add(0, element);
    }

    private synchronized void addingMiddleList() {
        arrayList.add(arrayList.size() / 2, element);
    }

    private synchronized void addingEndList() {
        arrayList.add(element);
    }

    private synchronized void removeStartList() {
        arrayList.subList(0, 0).clear();
    }

    private synchronized void removeMiddleList() {
        arrayList.subList(arrayList.size() / 2, element / 2).clear();
    }

    private synchronized void removeEndList() {
        arrayList.remove(element);
    }

    private synchronized void searchElementList() {
        arrayList.get(arrayList.indexOf(element));

    }


    private synchronized void addingStartLinkedList() {
        linkedList.addFirst(element);
    }

    private synchronized void addingMiddleLinkedList() {
        linkedList.add(linkedList.size() / 2, element);
    }

    private synchronized void addingEndLinkedList() {
        linkedList.addLast(element);
    }

    private synchronized void removeStartLinkedList() {
        linkedList.removeFirst();
    }

    private synchronized void removeMiddleLinkedList() {
        linkedList.remove(linkedList.size() / 2);

    }

    private synchronized void removeEndLinkedList() {
        linkedList.removeLast();
    }

    private synchronized void searchElementLinkedList() {
        linkedList.get(linkedList.indexOf(element));
    }


    private void addingStartCopyOnWriteArrayList() {
        copyOnWriteArrayList.add(0, element);
    }

    private void addingMiddleCopyOnWriteArrayList() {
        copyOnWriteArrayList.add(copyOnWriteArrayList.size()/ 2, element);
    }

    private void addingEndCopyOnWriteArrayList() {
        copyOnWriteArrayList.add(element);
    }

    private void removeStartCopyOnWriteArrayList() {
        copyOnWriteArrayList.subList(0, 0).clear();
    }

    private void removeMiddleCopyOnWriteArrayList() {
        copyOnWriteArrayList.subList(copyOnWriteArrayList.size() / 2, copyOnWriteArrayList.size() / 2).clear();
    }

    private void removeEndCopyOnWriteArrayList() {
        copyOnWriteArrayList.remove(element);
    }

    private void searchElementCopyOnWriteArrayList() {
        copyOnWriteArrayList.get(copyOnWriteArrayList.indexOf(element));

    }


}

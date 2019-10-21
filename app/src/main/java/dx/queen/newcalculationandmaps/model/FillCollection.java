package dx.queen.newcalculationandmaps.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

@Deprecated
public class FillCollection {

    int elements;
    private ArrayList<Integer> arrayList;
    private LinkedList<Integer> linkedList;
    private CopyOnWriteArrayList<Integer> copyOnWriteArrayList;

    public FillCollection(int elements) {
        this.elements = elements;
        arrayList = new ArrayList<>();
        linkedList = new LinkedList<>();
        copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    }


    public ArrayList<Integer> fillArrayList() {
        arrayList = new ArrayList<>();
        for (int y = 0; y <= elements; y++) {
            arrayList.add(y);
        }
        return arrayList;
    }

    public LinkedList<Integer> fillLinkedList() {
        linkedList = new LinkedList<>();
        for (int y = 0; y <= elements; y++) {
            linkedList.add(y);
        }
        return linkedList;

    }

    public CopyOnWriteArrayList<Integer> fillCopyOnWriteList() {
        copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        for (int y = 0; y <= elements; y++) {
            copyOnWriteArrayList.add(y);
        }
        return copyOnWriteArrayList;
    }

}

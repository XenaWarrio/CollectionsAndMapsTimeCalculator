package dx.queen.newcalculationandmaps.collectionsandmaps.model;

import java.util.List;

public class NamesListMaps {
    public static List<String> names;

    public static List<String> fillNamesList() {
        names.add("Adding to TreeMap : ");
        names.add("Search by key in TreeMap : ");
        names.add("Removing in TreeMap : ");
        names.add("Adding to HashMap : ");
        names.add("Search by key in HashMap : ");
        names.add("Removing in HashMap : ");
        return names;

    }
}

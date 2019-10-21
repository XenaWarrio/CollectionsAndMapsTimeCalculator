package dx.queen.newcalculationandmaps.collectionsandmaps;

import java.util.ArrayList;
import java.util.List;

import dx.queen.collectionsandmaps.adapterstuff.CalculationResult;

public interface MainInterface {

    interface View {
            void setItems(List<CalculationResult> results);
        }


    interface Presenter{
        void buttonWasClicked(String elements, String treads, String mode);
    }

    interface RepositoryRowView {

        void setTitle(String title);

        void setStarCount(int starCount);
    }

    interface Model{
        ArrayList<String>executingCollection(int elements, int threads);
        ArrayList<String>executingMaps(int elements, int threads);

    }
}

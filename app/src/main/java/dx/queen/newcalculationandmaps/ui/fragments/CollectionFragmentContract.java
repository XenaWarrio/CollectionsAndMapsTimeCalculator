package dx.queen.newcalculationandmaps.ui.fragments;

import java.util.List;

import dx.queen.newcalculationandmaps.dto.CalculationResult;
import dx.queen.newcalculationandmaps.mvp.MvpContract;

public interface CollectionFragmentContract {

    interface View extends MvpContract.View {
        void setItems(List<CalculationResult> results);

        void setupResult(CalculationResult result);

        void showProgress();
    }


    interface Presenter extends MvpContract.Presenter<View> {

        int getCollectionsCount();

        void getInitialResults();

        void startCalculation(String elements, String threads);

        void stopCalculation(boolean showMsg);
    }

    interface RepositoryRowView {

        void setTitle(String title);

        void setStarCount(int starCount);
    }

    interface Model {
        List<String> executingCollection(int elements, int threads);

        List<String> executingMaps(int elements, int threads);

    }
}

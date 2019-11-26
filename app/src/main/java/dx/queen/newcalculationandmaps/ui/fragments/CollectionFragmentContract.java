package dx.queen.newcalculationandmaps.ui.fragments;

import java.util.List;

import dx.queen.newcalculationandmaps.dto.CalculationResult;
import dx.queen.newcalculationandmaps.mvp.MvpContract;

public interface CollectionFragmentContract {

    interface View extends MvpContract.View {
        List<CalculationResult> getItems();

        void setItems(List<CalculationResult> results);

        void setupResult(CalculationResult result);

        void showProgress(boolean show);

        void setError(int number);

        void calculationStopped();

        void setThreadsError(String error);

        void setElemntsError(String error);

        void btnToStart();

        String getString(Integer strResId);
    }


    interface Presenter extends MvpContract.Presenter<View> {

        int getCollectionsCount();

        void getInitialResults();

        void startCalculation(String elements, String threads);

        void stopCalculation(boolean showMsg);
    }
}

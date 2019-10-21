package dx.queen.newcalculationandmaps.ui.fragments;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.TaskData;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculator;
import dx.queen.newcalculationandmaps.model.supplier.TaskSupplier;
import dx.queen.newcalculationandmaps.mvp.AbstractPresenter;

public class CollectionsPresenter extends AbstractPresenter<CollectionFragmentContract.View> implements CollectionFragmentContract.Presenter {

    private final TaskSupplier tasksSupplier;
    private final TimeCalculator calculator;
    private ExecutorService executorPool;

    public CollectionsPresenter(TaskSupplier tasksSupplier, TimeCalculator calculator) {
        this.tasksSupplier = tasksSupplier;
        this.calculator = calculator;
    }

    @Override
    public int getCollectionsCount() {
        return tasksSupplier.getCollectionCount();
    }

    @Override
    public void getInitialResults() {
        if (view != null) {
            view.setItems(tasksSupplier.getInitialResults());
        }
    }

    @Override
    public void startCalculation(String elements, String threads) {
        // TODO ksenia: validate inputs
        final int threadsInt = Integer.parseInt(threads);
        final int elementsInt = Integer.parseInt(threads);


        final List<TaskData> taskDatas = tasksSupplier.getTasks();
        stopCalculation(false); // false means stop pool, but don't update ui

        view.showProgress();
        executorPool = Executors.newFixedThreadPool(threadsInt);
        for (TaskData td : new ArrayList<>(taskDatas)) {
            executorPool.submit(() -> {
                td.fill(elementsInt);
                calculator.execAndSetupTime(td);

                taskDatas.remove(td);
                if (view != null) {
                    view.setupResult(td.getResult());
                }

                if (taskDatas.isEmpty()) {
                    if (view != null) {
                        view.showToast(R.string.calculation_finished);
                    }
                    stopCalculation(false);
                }
            });
        }
    }

    @Override
    public void stopCalculation(boolean showMsg) {
        if (executorPool == null) return;
        executorPool.shutdownNow();
        executorPool = null;

        if (view != null) {
            view.calculationStopped();
            if (showMsg) {
                view.showToast(R.string.calculation_stopped);
            }
        }
    }
}


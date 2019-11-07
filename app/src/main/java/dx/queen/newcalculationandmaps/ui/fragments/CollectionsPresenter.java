package dx.queen.newcalculationandmaps.ui.fragments;


import android.content.res.Resources;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.task.TaskData;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculator;
import dx.queen.newcalculationandmaps.model.supplier.TaskSupplier;
import dx.queen.newcalculationandmaps.mvp.AbstractPresenter;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CollectionsPresenter extends AbstractPresenter<CollectionFragmentContract.View> implements CollectionFragmentContract.Presenter {

    private final TaskSupplier tasksSupplier;
    private final TimeCalculator calculator;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

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
        if (threads.isEmpty()) {
            view.setThreadsError(view.getString(R.string.threads_empty));
            Resources.getSystem().getString(R.string.threads_empty);
        } else if ("0".equals(threads)) {
            view.setThreadsError(view.getString(R.string.threads_zero));
        } else {
            view.setThreadsError(null);
        }
        if (threads.isEmpty()) {
            view.setElemntsError(view.getString(R.string.elements_empty));
        } else if ("0".equals(threads)) {
            view.setElemntsError(view.getString(R.string.threads_zero));
        } else {
            view.setElemntsError(null);
        }

        final int threadsInt = Integer.valueOf(threads);
        final int elementsInt = Integer.valueOf(threads);
        final List<TaskData> taskDatas = tasksSupplier.getTasks();
        final ExecutorService executorPool = Executors.newFixedThreadPool(threadsInt);
        view.showProgress(true);
        Log.d("Erroro", "startCalculation");

        stopCalculation(false);
        Log.d("Erroro", "startCalculation STOPCALCULATION");
        // false means stop pool, but don't update ui

        Observable.fromIterable(taskDatas)
                .subscribeOn(Schedulers.from(executorPool))
                .observeOn(AndroidSchedulers.mainThread())
                .map(taskData -> {
                    Log.d("Erroro", "startCalculation RX");

                    //Thread.sleep(1000);
                    taskData.fill(elementsInt);
                    calculator.execAndSetupTime(taskData);
                    return taskData.getResult();
                })
                .subscribe(calculationResult -> {
                    if (view != null) view.setupResult(calculationResult);
                });
    }

    @Override
    public void stopCalculation(boolean showMsg) {
        Log.d("Erroro",  "STOPCALCULATION METHOD");

        if (compositeDisposable.size() != 0) {
            Log.d("Erroro",  "compositeDisposable size is " + compositeDisposable.size());

            compositeDisposable.clear();
        }else {
            return;
        }
        Log.d("Erroro", "stopCalculationFirstLine");
        if (view != null) {
            Log.d("Erroro", "stopCalculationViewNotNull");

            view.showProgress(false);
            Log.d("Erroro", "stopCalculationShowProgress");

            view.calculationStopped();
            Log.d("Erroro", "stopCalculationCalculationStop");

            if (showMsg) {
                view.showToast(R.string.calculation_stopped);
                Log.d("Erroro", "stopCalculationShowToast"); //maybe here should be showprogress , should change on true

            }
        }

    }
}


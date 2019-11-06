package dx.queen.newcalculationandmaps.ui.fragments;


import android.content.res.Resources;

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
import io.reactivex.schedulers.Schedulers;

public class CollectionsPresenter extends AbstractPresenter<CollectionFragmentContract.View> implements CollectionFragmentContract.Presenter {

    private final TaskSupplier tasksSupplier;
    private final TimeCalculator calculator;

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

        view.showProgress(true);

        final int threadsInt = Integer.valueOf(threads);
        final int elementsInt = Integer.valueOf(threads);
        // final CompositeDisposable disposable = new CompositeDisposable();
        final List<TaskData> taskDatas = tasksSupplier.getTasks();
        final ExecutorService executorPool = Executors.newFixedThreadPool(threadsInt);
        stopCalculation(false); // false means stop pool, but don't update ui

        Observable.fromIterable(taskDatas)
                .subscribeOn(Schedulers.from(executorPool))
                .observeOn(AndroidSchedulers.mainThread())
                .map(taskData -> {
                    taskData.fill(elementsInt);
                    calculator.execAndSetupTime(taskData);
                    return taskData.getResult();
                })
                .subscribe(calculationResult -> {
                    if (view != null) view.setupResult(calculationResult);
                });

//        view.showProgress(true);
//        executorPool = Executors.newFixedThreadPool(threadsInt);
//        for (TaskData td : new ArrayList<>(taskDatas)) {
//            executorPool.submit(() -> {
//                td.fill(elementsInt);
//                calculator.execAndSetupTime(td);
//
//                taskDatas.remove(td);
//                if (view != null) {
//                    view.setupResult(td.getResult());
//                }
//
//                if (taskDatas.isEmpty()) {
//                    if (view != null) {
//                        view.showToast(R.string.calculation_finished);
//                    }
//                    stopCalculation(false);
//                }
//            });
//        }
    }

    @Override
    public void stopCalculation(boolean showMsg) {
        // if (executorPool == null) return;
        // executorPool.shutdownNow();
        // executorPool = null;

        if (view != null) {
            view.calculationStopped();
            if (showMsg) {
                view.showToast(R.string.calculation_stopped);
            }
        }
    }
}


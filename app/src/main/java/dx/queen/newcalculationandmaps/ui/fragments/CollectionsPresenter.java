package dx.queen.newcalculationandmaps.ui.fragments;


import java.util.List;
import java.util.concurrent.Executors;

import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.TextUtils;
import dx.queen.newcalculationandmaps.dto.task.TaskData;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculator;
import dx.queen.newcalculationandmaps.model.supplier.TaskSupplier;
import dx.queen.newcalculationandmaps.mvp.AbstractPresenter;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
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
        boolean flag = true;
        if (TextUtils.isEmpty(threads)) {
            view.setThreadsError(view.getString(R.string.threads_empty));
            flag = false;
        } else if ("0".equals(threads)) {
            view.setThreadsError(view.getString(R.string.threads_zero));
            flag = false;

        }
        else{
            view.setThreadsError(null);
        }

        if (TextUtils.isEmpty(elements)) {
            view.setElemntsError(view.getString(R.string.elements_empty));
            flag = false;

        } else if ("0".equals(elements)) {
            view.setElemntsError(view.getString(R.string.elements_zero));
            flag = false;

        }else {
            view.setElemntsError(null);
        }

        if (!flag) {

            final int threadsInt = Integer.valueOf(threads);
            final int elementsInt = Integer.valueOf(threads);
            final List<TaskData> taskDatas = tasksSupplier.getTasks();
            Scheduler schedulers = Schedulers.from(Executors.newFixedThreadPool(threadsInt));
            view.showProgress(true);

            stopCalculation(false);
            // false means stop pool, but don't update ui

            compositeDisposable.add(Observable.fromIterable(taskDatas)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(schedulers)
                    .doOnSubscribe(v -> {
                        if (view != null) view.showProgress(true);
                    })
                    .doFinally(() -> {
                        stopCalculation(true);
                    })
                    .map(taskData -> {
                        Thread.sleep(300);
                        taskData.fill(elementsInt);
                        calculator.execAndSetupTime(taskData);
                        return taskData.getResult();
                    })
                    .subscribe(calculationResult -> {
                        if (view != null) view.setupResult(calculationResult);
                    }));

            view.btnToStart();
        }
    }


    @Override
    public void stopCalculation(boolean showMsg) {

        if (compositeDisposable.size() != 0) {

            compositeDisposable.clear();
        } else {
            return;
        }
        if (view != null) {

            view.showProgress(false);

            view.calculationStopped();

            if (showMsg) {
                view.showToast(R.string.calculation_stopped);
            }
        }

    }


}


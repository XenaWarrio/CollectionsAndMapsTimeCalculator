package dx.queen.newcalculationandmaps.ui.fragments;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import dx.queen.newcalculationandmaps.dto.task.TaskData;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculator;
import dx.queen.newcalculationandmaps.model.supplier.TaskSupplier;
import dx.queen.newcalculationandmaps.mvp.AbstractPresenter;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;

import static android.hardware.camera2.params.BlackLevelPattern.COUNT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class CollectionsPresenterTest extends AbstractPresenter<CollectionFragmentContract.View> {
    private static final String TAG = "tag";
    String elements = "4";
    String threads = "6";

    @Mock
     TaskSupplier tasksSupplier;

    @Mock
     TimeCalculator calculator;

    @Mock
     CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Mock
    CollectionFragmentContract.View view;

    CollectionsPresenter presenter;


    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                (Callable<Scheduler> schedulerCallable) -> Schedulers.trampoline());
    }


    @Test
    public void getCollectionsCount() {
        assertSame(tasksSupplier.getCollectionCount(),Integer.valueOf(elements));
    }

    @Test
    public void getInitialResults() {
        assertNotNull(view);
        view.setItems(tasksSupplier.getInitialResults());
        assertEquals(tasksSupplier.getInitialResults(),view.getItems());
    }

    @Test
    public void startCalculation() {


        assertEquals(elements, String.valueOf(4) );
        assertEquals(threads, String.valueOf(6) );
        assertTrue(!elements.isEmpty() && !threads.isEmpty());
        RuntimeException exception = new RuntimeException("Validation Error");
        doThrow(exception).when(presenter).startCalculation("0","0");
        doThrow(exception).when(presenter).startCalculation(null,null);
        doThrow(exception).when(presenter).startCalculation(" "," ");

         int threadsInt = Integer.valueOf(threads);
         int elementsInt = Integer.valueOf(threads);
         int tasksCount = 2;
         List<TaskData> taskDatas = getFakesTasks(elementsInt);




        Mockito.when(tasksSupplier.getTasks()).thenReturn(taskDatas);


        presenter.startCalculation(String.format("%d", elements), String.format("%d", threads));

        verify(view,  Mockito.times(0)).setError(anyInt());
        verify(view).showProgress(true);
        verify(tasksSupplier).getTasks();
// here is problem because multithreading!!
        for (TaskData task : taskDatas) {
            verify(task).fill(COUNT);
        }


        verify(calculator, times(getFakesTasks(tasksCount).size())).execAndSetupTime(any());
        verify(view, times(getFakesTasks(tasksCount).size())).setupResult(any());
        verify(view).calculationStopped();
        verifyNoMoreInteractions(view);
        TestScheduler testScheduler = new TestScheduler();

        TaskData taskData = taskDatas.get(1);



         Mockito.doReturn(Single.just(taskData))
                 .when(taskData)
                 .fill(elementsInt);

         Mockito.doReturn(Single.just(taskData))
                 .when(taskData)
                 .getResult();

         Mockito.doReturn(testScheduler)
                 .when(calculator)
                 .execAndSetupTime(taskData);

         Mockito.doReturn(testScheduler)
                 .when(Observable.fromIterable(taskDatas))
                 .doFinally(this::stopCalculation);


        TestObserver<TaskData> testTaskDatas = Observable.fromIterable(taskDatas)
                .subscribeOn(testScheduler)
                .observeOn(testScheduler)
                .test();

        testTaskDatas.assertNotTerminated()
                .assertNoErrors();


        testScheduler.advanceTimeBy(300, TimeUnit.SECONDS);

        testTaskDatas.dispose();



    }

    @Test
    public void stopCalculation() {
    }

    List<TaskData> getFakesTasks(int count){
        List<TaskData> taskData = new ArrayList<>(count);
       return taskData;
    }
}
package dx.queen.newcalculationandmaps.ui.fragments;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import dx.queen.newcalculationandmaps.dto.task.TaskData;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculator;
import dx.queen.newcalculationandmaps.model.supplier.TaskSupplier;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;

public class CollectionsPresenterTest {
    private static final String TAG = "tag";

    @Mock
     TaskSupplier tasksSupplier;

    @Mock
     TimeCalculator calculator;

    @Mock
     CompositeDisposable compositeDisposable = new CompositeDisposable();

    CollectionsPresenter presenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
         presenter = spy(new CollectionsPresenter(tasksSupplier,calculator));
    }




    @Test
    public void getCollectionsCount() {
    }

    @Test
    public void getInitialResults() {
    }

    @Test
    public void startCalculation() {
        String elements = "4";
        String threads = "6";

        assertEquals(elements, String.valueOf(4) );
        assertEquals(threads, String.valueOf(6) );
        assertTrue(!elements.isEmpty() && !threads.isEmpty());
        RuntimeException exception = new RuntimeException("Validation Error");
        doThrow(exception).when(presenter).startCalculation("0","0");
        doThrow(exception).when(presenter).startCalculation(null,null);
        doThrow(exception).when(presenter).startCalculation(" "," ");

         int threadsInt = Integer.valueOf(threads);
         int elementsInt = Integer.valueOf(threads);
         List<TaskData> taskDatas = tasksSupplier.getTasks();
         ExecutorService executorPool = Executors.newFixedThreadPool(threadsInt);
        TestScheduler testScheduler = new TestScheduler();

        TaskData taskData = taskDatas.get(1);

         Mockito.doReturn(testScheduler)
                 .when(presenter)
                 .getSchedulerIo();

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
}
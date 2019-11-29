package dx.queen.newcalculationandmaps.ui.fragments;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.task.TaskData;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculator;
import dx.queen.newcalculationandmaps.model.supplier.TaskSupplier;
import dx.queen.newcalculationandmaps.mvp.AbstractPresenter;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.TestSubscriber;

import static android.hardware.camera2.params.BlackLevelPattern.COUNT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CollectionsPresenterTest extends AbstractPresenter<CollectionFragmentContract.View> {
    public static final int THREADS_INT = 4;
    public static final int ELEMENTS_INT = 6;
    public static final int TASK_COUNT = 2;

    @Mock
    TaskSupplier tasksSupplier;

    @Mock
    TimeCalculator calculator;

    @Mock
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Mock
    CollectionFragmentContract.View view;


    @Mock
    ExecutorService executorPool;

    CollectionsPresenter presenter;

    @Before
    public void setUp(){
        presenter = new CollectionsPresenter(tasksSupplier,calculator);

    }


    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                (Callable<Scheduler> schedulerCallable) -> Schedulers.trampoline());

    }

    @Test
    public void presenterAndViewNotNull(){
        assertNotNull(presenter);
        assertNotNull(view);
        verifyNoMore();

    }

    @Test
    public void testEmptyThreads(){
        presenter.startCalculation("5", " ");
        Mockito.verify(view).setThreadsError(view.getString(R.string.threads_empty));
        verifyNoMore();

    }

    @Test
    public void testEmptyElements(){
        presenter.startCalculation(" ", "5");
        Mockito.verify(view).setElemntsError(view.getString(R.string.elements_empty));
        verifyNoMore();

    }

    @Test
    public void testZeroThreads(){
        presenter.startCalculation("5", "0");
        Mockito.verify(view).setThreadsError(view.getString(R.string.threads_zero));
        verifyNoMore();

    }

    @Test
    public void testZeroElements(){
        presenter.startCalculation("0", "5");
        Mockito.verify(view).setElemntsError(view.getString(R.string.elements_zero));
        verifyNoMore();

    }

    @Test
    public void testNullThreads(){
        presenter.startCalculation("5", "");
        Mockito.verify(view).setThreadsError("null");
        verifyNoMore();

    }

    @Test
    public void testNullElements(){
        presenter.startCalculation("", "5");
        Mockito.verify(view).setElemntsError("null");
        verifyNoMore();

    }

    @Test
    public void startCalculation(){
        List<TaskData> taskDatas = getFakesTasks(ELEMENTS_INT);
        assertNotEquals(taskDatas, null);

        for (TaskData task : taskDatas) {
            verify(task).fill(COUNT);
        }

        Mockito.when(tasksSupplier.getTasks()).thenReturn(taskDatas);
        Mockito.verify(view).showProgress(true);

        executorPool = Executors.newFixedThreadPool(ELEMENTS_INT);
        Mockito.verify(presenter).stopCalculation(false);

        Observable<TaskData> observable = Observable.fromIterable(taskDatas);

        TestSubscriber<TaskData> subscriber = new TestSubscriber<>();
        subscriber.assertSubscribed();

        observable.subscribe((Observer<? super TaskData>) subscriber);

        observable.delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .observeOn(Schedulers.from(executorPool))
                .doOnSubscribe(v ->{
                    assertNotNull(view);
                    Mockito.verify(view).showProgress(true);
                })
                .doFinally(()->{
                    Mockito.verify(presenter).stopCalculation(true);
                })
                .map(taskData1 -> {
                    wait(300);
                    Mockito.verify(taskData1).fill(ELEMENTS_INT);
                    Mockito.verify(calculator).execAndSetupTime(taskData1);
                    assertNotNull(taskData1.getResult());
                    return taskData1.getResult();
                })
                .subscribe(calculationResult -> {
                    assertNotNull(view);
                    Mockito.verify(view).setupResult(calculationResult);
                });

        subscriber.assertValues(taskDatas.get(1));
        subscriber.assertComplete();

        Mockito.verify(view).btnToStart();

        verifyNoMore();


    }



    @Test
    public void getCollectionsCount() {
        Mockito.when(tasksSupplier.getCollectionCount()).thenReturn(TASK_COUNT);
        assertTrue(presenter.getCollectionsCount() == TASK_COUNT);

        Mockito.verify(tasksSupplier).getCollectionCount();
        assertEquals(presenter.getCollectionsCount(), ELEMENTS_INT);
        verifyNoMore();


    }


    @Test
    public void getInitialResults() {
        assertNotNull(view);
        Mockito.verify(view).setItems(tasksSupplier.getInitialResults());
        assertEquals(view.getItems() , tasksSupplier.getInitialResults());
        verifyNoMore();
    }



    @Test
    public void stopCalculation() {
    }

    List<TaskData> getFakesTasks(int count) {
        List<TaskData> tasksData = new ArrayList<>(count);

        for (int i = 0; i <= count; i++) {
            tasksData.add(Mockito.mock(TaskData.class));

        }
        return tasksData;
    }

    public void verifyNoMore(){
        Mockito.verifyNoMoreInteractions(presenter);
        Mockito.verifyNoMoreInteractions(calculator);
        Mockito.verifyNoMoreInteractions(tasksSupplier);
        Mockito.verifyNoMoreInteractions(view);
        Mockito.verifyNoMoreInteractions(executorPool);
        Mockito.verifyNoMoreInteractions(compositeDisposable);

    }
}
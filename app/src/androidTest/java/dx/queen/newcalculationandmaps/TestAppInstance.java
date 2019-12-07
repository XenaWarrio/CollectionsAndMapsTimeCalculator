package dx.queen.newcalculationandmaps;

import android.app.Application;

import dx.queen.newcalculationandmaps.model.AppComponent;
import dx.queen.newcalculationandmaps.model.DaggerAppComponent;

public class TestAppInstance extends Application {

    private static TestAppInstance instance;
    private TestAppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder().testAppModule(new TestAppModule(this)).build();
    }

    public TestAppComponent getAppComponent() {
        return appComponent;
    }

    public static TestAppInstance getInstance() {
        return instance;
    }
}

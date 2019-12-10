package dx.queen.newcalculationandmaps;

import android.app.Application;

import dx.queen.newcalculationandmaps.model.AppComponent;
import dx.queen.newcalculationandmaps.model.AppModule;
import dx.queen.newcalculationandmaps.model.DaggerAppComponent;

public class AppInstance extends Application {

    private static AppInstance instance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static AppInstance getInstance() {
        return instance;
    }

    public void setAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }
}

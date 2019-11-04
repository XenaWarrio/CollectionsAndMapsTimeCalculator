package dx.queen.newcalculationandmaps.ui.fragments;

import android.app.Application;


public class ApplicationDI extends Application {

    private static ApplicationDI instance;
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
    public static ApplicationDI getInstance() {
        return instance;
    }

}

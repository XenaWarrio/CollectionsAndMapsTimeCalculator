package dx.queen.newcalculationandmaps.model;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculator;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculatorImpl;
import dx.queen.newcalculationandmaps.model.supplier.CollectionsTasksSupplier;
import dx.queen.newcalculationandmaps.model.supplier.MapsTasksSupplier;
import dx.queen.newcalculationandmaps.model.supplier.TaskSupplier;

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context getApp() {
        return context;
    }

    @Provides
    public TimeCalculator provideTimeCalculator() {
        return new TimeCalculatorImpl();

    }

    @Named("Maps")
    @Provides
    public TaskSupplier provideMapSupplier() {
        return new MapsTasksSupplier();

    }

    @Named("Collections")
    @Provides
    public TaskSupplier provideCollectionsSupplier() {
        return new CollectionsTasksSupplier();

    }


}

package dx.queen.newcalculationandmaps;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculator;
import dx.queen.newcalculationandmaps.model.supplier.CollectionsTasksSupplier;
import dx.queen.newcalculationandmaps.model.supplier.MapsTasksSupplier;
import dx.queen.newcalculationandmaps.model.supplier.TaskSupplier;

@Module
public class TestAppModule {
    private final Context context;

    public TestAppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context getApp() {
        return context;
    }

    @Provides
    public TimeCalculator provideTimeCalculator() {
        return new TestCalculator();

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

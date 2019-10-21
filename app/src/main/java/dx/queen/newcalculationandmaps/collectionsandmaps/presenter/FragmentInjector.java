package dx.queen.newcalculationandmaps.collectionsandmaps.presenter;

import dx.queen.collectionsandmaps.MainInterface;

public class FragmentInjector {
    public static Presenter createPresenter(MainInterface.View view){
        return new Presenter(view);
    }
}

package dx.queen.newcalculationandmaps.ui.fragments;

import dagger.Component;

@Component(modules = {FragmentModule.class})
public interface FragmentComponent {
    void injectPresenter(CollectionsFragment view);
}

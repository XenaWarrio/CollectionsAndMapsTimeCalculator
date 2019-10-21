package dx.queen.newcalculationandmaps.collectionsandmaps;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import dx.queen.collectionsandmaps.dto.Modes;

public class FragmentsAdapter extends FragmentStatePagerAdapter {

    public FragmentsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return CollectionsFragment.newInstance(Modes.COLLECTIONS);


            case 1:
                return CollectionsFragment.newInstance(Modes.MAPS);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Collections";

            case 1:
                return "Maps";

             default:
                 return null;
        }
    }
}

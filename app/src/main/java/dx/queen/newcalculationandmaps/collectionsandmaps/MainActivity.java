package dx.queen.newcalculationandmaps.collectionsandmaps;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import dx.queen.collectionsandmaps.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.view_pager);
        FragmentsAdapter fragmentsAdapter = new FragmentsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentsAdapter);


        TabLayout tablayout = findViewById(R.id.tab);
        tablayout.setupWithViewPager(viewPager);

    }
}

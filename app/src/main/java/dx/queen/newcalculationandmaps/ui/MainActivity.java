package dx.queen.newcalculationandmaps.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import dx.queen.newcalculationandmaps.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager viewPager = findViewById(R.id.view_pager);
        final FragmentsAdapter fragmentsAdapter = new FragmentsAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(fragmentsAdapter);


        final TabLayout tablayout = findViewById(R.id.tab);
        tablayout.setupWithViewPager(viewPager);

    }

}

package com.xdroid.animation.sample;

import java.util.ArrayList;
import java.util.List;

import com.xdroid.animation.interpolate.EaseBackInInterpolator;
import com.xdroid.animation.interpolate.EaseBackInOutInterpolator;
import com.xdroid.animation.interpolate.EaseBackOutInterpolator;
import com.xdroid.animation.interpolate.EaseBounceInInterpolator;
import com.xdroid.animation.interpolate.EaseBounceInOutInterpolator;
import com.xdroid.animation.interpolate.EaseBounceOutInterpolator;
import com.xdroid.animation.interpolate.EaseCircularInInterpolator;
import com.xdroid.animation.interpolate.EaseCircularInOutInterpolator;
import com.xdroid.animation.interpolate.EaseCircularOutInterpolator;
import com.xdroid.animation.interpolate.EaseCubicInInterpolator;
import com.xdroid.animation.interpolate.EaseCubicInOutInterpolator;
import com.xdroid.animation.interpolate.EaseCubicOutInterpolator;
import com.xdroid.animation.interpolate.EaseElasticInInterpolator;
import com.xdroid.animation.interpolate.EaseElasticInOutInterpolator;
import com.xdroid.animation.interpolate.EaseElasticOutInterpolator;
import com.xdroid.animation.interpolate.EaseExponentialInInterpolator;
import com.xdroid.animation.interpolate.EaseExponentialInOutInterpolator;
import com.xdroid.animation.interpolate.EaseExponentialOutInterpolator;
import com.xdroid.animation.interpolate.EaseQuadInInterpolator;
import com.xdroid.animation.interpolate.EaseQuadInOutInterpolator;
import com.xdroid.animation.interpolate.EaseQuadOutInterpolator;
import com.xdroid.animation.interpolate.EaseQuartInInterpolator;
import com.xdroid.animation.interpolate.EaseQuartInOutInterpolator;
import com.xdroid.animation.interpolate.EaseQuartOutInterpolator;
import com.xdroid.animation.interpolate.EaseQuintInInterpolator;
import com.xdroid.animation.interpolate.EaseQuintInOutInterpolator;
import com.xdroid.animation.interpolate.EaseQuintOutInterpolator;
import com.xdroid.animation.interpolate.EaseSineInInterpolator;
import com.xdroid.animation.interpolate.EaseSineInOutInterpolator;
import com.xdroid.animation.interpolate.EaseSineOutInterpolator;
import com.xdroid.animation.sample.interpolate.EaseAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.ListView;

public class InterpolateActivity extends Activity {
    private static final long DURATION = 1200;

    private ListView listView;
    private EaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolate);

        init();
        listView = (ListView) findViewById(R.id.list);
        adapter = new EaseAdapter(this, nameList, interpolatorList, DURATION);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectIndex(position);
            }
        });
    }


    private List<String> nameList = new ArrayList<String>();
    private List<Interpolator> interpolatorList = new ArrayList<Interpolator>();
    private void init() {
        interpolatorList.add(new EaseBackInInterpolator());
        interpolatorList.add(new EaseBackOutInterpolator());
        interpolatorList.add(new EaseBackInOutInterpolator());

        interpolatorList.add(new EaseBounceInInterpolator());
        interpolatorList.add(new EaseBounceOutInterpolator());
        interpolatorList.add(new EaseBounceInOutInterpolator());

        interpolatorList.add(new EaseCircularInInterpolator());
        interpolatorList.add(new EaseCircularOutInterpolator());
        interpolatorList.add(new EaseCircularInOutInterpolator());

        interpolatorList.add(new EaseCubicInInterpolator());
        interpolatorList.add(new EaseCubicOutInterpolator());
        interpolatorList.add(new EaseCubicInOutInterpolator());

        interpolatorList.add(new EaseElasticInInterpolator(DURATION));
        interpolatorList.add(new EaseElasticOutInterpolator(DURATION));
        interpolatorList.add(new EaseElasticInOutInterpolator(DURATION));

        interpolatorList.add(new EaseExponentialInInterpolator());
        interpolatorList.add(new EaseExponentialOutInterpolator());
        interpolatorList.add(new EaseExponentialInOutInterpolator());

        interpolatorList.add(new EaseQuadInInterpolator());
        interpolatorList.add(new EaseQuadOutInterpolator());
        interpolatorList.add(new EaseQuadInOutInterpolator());

        interpolatorList.add(new EaseQuartInInterpolator());
        interpolatorList.add(new EaseQuartOutInterpolator());
        interpolatorList.add(new EaseQuartInOutInterpolator());

        interpolatorList.add(new EaseQuintInInterpolator());
        interpolatorList.add(new EaseQuintOutInterpolator());
        interpolatorList.add(new EaseQuintInOutInterpolator());

        interpolatorList.add(new EaseSineInInterpolator());
        interpolatorList.add(new EaseSineOutInterpolator());
        interpolatorList.add(new EaseSineInOutInterpolator());

        for (Interpolator interpolator : interpolatorList) {
            nameList.add(interpolator.getClass().getSimpleName().replace("Interpolator", ""));
        }
    }

}

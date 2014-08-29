package com.psaravan.flexiimageview.demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.psaravan.flexiimageview.lib.View.FlexiImageView;


public class MainActivity extends Activity {

    private FlexiImageView mFlexiImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFlexiImageView = (FlexiImageView) findViewById(R.id.flexiImageView);
        mFlexiImageView.setImageResource(R.drawable.test);
        mFlexiImageView.setShape(FlexiImageView.SHAPE_EQUILATERAL_TRIANGLE)
                       .setShadow(true, 50.0f, 0.0f, 10.0f, Color.BLACK)
                       .draw();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

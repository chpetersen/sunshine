package com.example.cpe.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

  public final String TAG = MainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (savedInstanceState == null) {

      getSupportFragmentManager().beginTransaction()
          .add(R.id.container, new ForecastFragment())
          .commit();
    }
    Log.v(TAG, "onCreate");
  }

  @Override
  protected void onPause() {
    super.onPause();
    Log.v(TAG, "onPause");
  }

  @Override
  protected void onStop() {
    super.onStop();
    Log.v(TAG, "onStop");
  }

  @Override
  protected void onStart() {
    super.onStart();
    Log.v(TAG, "onStart");
  }

  @Override
  protected void onResume() {
    super.onResume();
    Log.v(TAG, "onResume");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.v(TAG, "onDestroy");
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
      startActivity(intent);
    } else if (id == R.id.action_show_map) {
      SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
      String location = sharedPreferences.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));

      Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
          .appendQueryParameter("q", location)
          .build();

      showMap(geoLocation);
    }

    return super.onOptionsItemSelected(item);
  }

  public void showMap(Uri geoLocation) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(geoLocation);
    if (intent.resolveActivity(getPackageManager()) != null) {
      startActivity(intent);
    }
  }

}

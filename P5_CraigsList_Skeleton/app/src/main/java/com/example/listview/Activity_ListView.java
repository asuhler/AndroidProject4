package com.example.listview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.ListActivity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Activity_ListView extends AppCompatActivity {


	ListView my_listview;
	SharedPreferences myPreference;
	SharedPreferences.OnSharedPreferenceChangeListener listener;
    protected ArrayAdapter adapter;
    JSONHelper help;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myPreference = PreferenceManager.getDefaultSharedPreferences(this);
		PreferenceManager.setDefaultValues(this, R.xml.preferences, false);




		// Change title to indicate sort by
		setTitle("Sort by:");

		//listview that you will operate on
		my_listview = (ListView)findViewById(R.id.lv);


		//toolbar
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		android.support.v7.app.ActionBar actionBar = getSupportActionBar();

		setupSimpleSpinner();

		//set the listview onclick listener
		setupListViewOnClickListener();

        downloadJsonList();


        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {

            @Override
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                //Put code here to start list repopulation again using updated server preference

                downloadJsonList();
            }
        };

        myPreference.registerOnSharedPreferenceChangeListener(listener);

		//TODO call a thread to get the JSON list of bikes
		//TODO when it returns it should process this data with bindData


	}

    /**
     * Called to start the process of retreiving data from server.
     * Checks for connection, if there is connection, download JSON string data.
     * Async onPost returns the JSON data and calls JSON parse
     */
    private void downloadJsonList(){

        ConnectivityCheck test = new ConnectivityCheck();
        if(test.isNetworkReachableAlertUserIfNot(this)){
            Toast.makeText(Activity_ListView.this, "I have internet", Toast.LENGTH_SHORT).show();
            //download JSON
            String address = myPreference.getString("PREF_SERVER","") + "bikes.json";
            DownloadTask download = new DownloadTask(this);
            try{
                download.execute(address);

            }catch(Exception e){
                Log.e("LOG", "YOU FAILED with an error in the call to Download.execute(address) in downloadJsonList()");
                e.printStackTrace();
            }

        }else{
            Toast.makeText(Activity_ListView.this, "I do NOT have internet", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Parses the JSON using the JSON Helper, is called by the DownloadTask onPostExecute
     * @param in String input returned from DownloadTask. Contains all JSON from download.
     */
    public void parseJSON(String in){
        Log.e("LOG", in);
        help = new JSONHelper();
        List<BikeData> data = help.parseAll(in);
        /*for(int i=0; i<data.size(); i++){
            Log.e("LOG", data.get(i).toString());
        }*/

    }

	private void setupListViewOnClickListener() {
		//TODO you want to call my_listviews setOnItemClickListener with a new instance of android.widget.AdapterView.OnItemClickListener() {
	}

	/**
	 * Takes the string of bikes, parses it using JSONHelper
	 * Sets the adapter with this list using a custom row layout and an instance of the CustomAdapter
	 * binds the adapter to the Listview using setAdapter
	 *
	 * @param JSONString  complete string of all bikes
	 */
	private void bindData(String JSONString) {

	}

	Spinner spinner;
	/**
	 * create a data adapter to fill above spinner with choices(Company,Location and Price),
	 * bind it to the spinner
	 * Also create a OnItemSelectedListener for this spinner so
	 * when a user clicks the spinner the list of bikes is resorted according to selection
	 * dontforget to bind the listener to the spinner with setOnItemSelectedListener!
	 */
	private void setupSimpleSpinner() {

        spinner = (Spinner)findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.sortable_fields,R.layout.spinner_item);

        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public static final int SELECTED_ITEM = 0;

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long rowid) {
                //Toast.makeText(Activity_ListView.this, "pos: " + pos, Toast.LENGTH_SHORT).show();
                switch (pos) {
                    case 0:
                        //Toast.makeText(Activity_ListView.this, "THIS WORKED", Toast.LENGTH_SHORT).show();
                    case 1:
                        //Toast.makeText(Activity_ListView.this, "THIS WORKED TOO", Toast.LENGTH_SHORT).show();
                    case 2:

                    case 3:


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		switch (id) {
			case R.id.action_settings:
				Intent myIntent = new Intent(this, activityPreference.class);
				startActivity(myIntent);
				return true;

            case R.id.reset:
                doReset();
                return true;

		}

		//all else fails let super handle it
		return super.onOptionsItemSelected(item);
	}

    private void doReset() {
        downloadJsonList();
    }
}

package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file1.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
    private ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
	private ArrayAdapter<Tweet> adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		Button clearButton = (Button) findViewById(R.id.clear);

		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();

				Tweet tweet = new Tweet(text);

				tweetList.add(tweet);

                adapter.notifyDataSetChanged();

				//saveInFile(text, new Date(System.currentTimeMillis()));
				//finish();

                saveInFile();

			}
		});
		clearButton.setOnClickListener(new View.OnClickListener(){

		    public void onClick(View v) {
		        setResult(RESULT_OK);

		        adapter.clear();
		        adapter.notifyDataSetChanged();

            }
        });
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		loadFromFile();

		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweetList);

		oldTweetsList.setAdapter(adapter);
	}

	private void loadFromFile() {
        //ArrayList<String> tweets = new ArrayList<String>();


        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type listtype = new TypeToken<ArrayList<Tweet>>(){}.getType();

            tweetList = gson.fromJson(in, listtype);

            //String line = in.readLine();


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	private void saveInFile() {
		try {
			//FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);

			//BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            FileWriter fos = new FileWriter(FILENAME);

			Gson gson = new Gson();

			gson.toJson(tweetList, fos);


            // fos.write(new String(date.toString() + " | " + text.getBytes());
			fos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
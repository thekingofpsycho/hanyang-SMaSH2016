package info.androidhive.customlistviewvolley;

import info.androidhive.customlistviewvolley.adater.CustomListAdapter;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Music;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

public class MainActivity extends Activity {
	// Log tag
	private static final String TAG = MainActivity.class.getSimpleName();

	// Musics json url
	private static final String url = "http://bookmark.ddns.net/json/test.json";
	private ProgressDialog pDialog;
	private List<Music> musicList = new ArrayList<Music>();
	private ListView listView;
	private CustomListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.list);
		adapter = new CustomListAdapter(this, musicList);
		listView.setAdapter(adapter);

		pDialog = new ProgressDialog(this);
		// Showing progress dialog before making http request
		pDialog.setMessage("Loading...");
		pDialog.show();

		// changing action bar color
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#1b1b1b")));

		// Creating volley request obj
		JsonArrayRequest musicReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						//response=new String(response.getBytes("8859_1"),"utf-8");
						Log.d(TAG, response.toString());
						Log.d("qqqqqq","1111");
						hidePDialog();

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i);
								Music music = new Music();
								music.setTitle(obj.getString("title"));

								music.setThumbnailUrl(obj.getString("image"));
								music.setRank((obj.getInt("ranking")));
								music.setArtist(obj.getString("artist"));

								// Genre is json array
								JSONArray genreArry = obj.getJSONArray("genre");
								ArrayList<String> genre = new ArrayList<String>();
								for (int j = 0; j < genreArry.length(); j++) {
									genre.add((String) genreArry.get(j));
								}
								music.setGenre(genre);

								// adding music to musics array
								musicList.add(music);

							} catch (JSONException e) {
								e.printStackTrace();
							}

						}

						// notifying list adapter about data changes
						// so that it renders the list view with updated data
						adapter.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});


		// Adding request to request queue


		AppController.getInstance().addToRequestQueue(musicReq);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
									int position, long id) {
				Intent intent = new Intent(MainActivity.this,
						ImageActivity.class);

				Music m = (Music) listView.getItemAtPosition(position);
				intent.putExtra("url", m.getThumbnailUrl());
				intent.putExtra("artist", m.getArtist());
				intent.putExtra("title", m.getTitle());
				startActivity(intent);

			}
		});

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hidePDialog();
	}

	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

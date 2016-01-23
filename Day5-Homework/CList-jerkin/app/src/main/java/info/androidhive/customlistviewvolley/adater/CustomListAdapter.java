package info.androidhive.customlistviewvolley.adater;

import info.androidhive.customlistviewvolley.R;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;
import info.androidhive.customlistviewvolley.model.Music;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class CustomListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Music> musicItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<Music> musicItems) {
		this.activity = activity;
		this.musicItems = musicItems;
	}

	@Override
	public int getCount() {
		return musicItems.size();
	}

	@Override
	public Object getItem(int location) {
		return musicItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);

		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView rating = (TextView) convertView.findViewById(R.id.rank);
		TextView genre = (TextView) convertView.findViewById(R.id.genre);
		TextView year = (TextView) convertView.findViewById(R.id.Artist);

		// getting music data for the row
		Music m = musicItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

		// title
		title.setText(m.getTitle());

		// rating
		rating.setText("("+String.valueOf(m.getRank())+")");

		// genre
		String genreStr = "";
		for (String str : m.getGenre()) {
			genreStr += str + ", ";
		}
		genreStr = genreStr.length() > 0 ? genreStr.substring(0,
				genreStr.length() - 2) : genreStr;
		genre.setText(genreStr);

		// release year
		year.setText(String.valueOf(m.getArtist()));

		return convertView;
	}

}

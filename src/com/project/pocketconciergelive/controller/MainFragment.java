package com.project.pocketconciergelive.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.project.pocketconciergelive.R;

public class MainFragment extends Fragment {

	AutoCompleteTextView autoCompleteTextView;
	ListView listView;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_main, container, false);
		String[] array = getResources().getStringArray(R.array.location);
		autoCompleteTextView = (AutoCompleteTextView) v
				.findViewById(R.id.location);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				getActivity(), R.layout.location_dropdown, array);
		autoCompleteTextView.setAdapter(arrayAdapter);
		autoCompleteTextView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				TextView textview = (TextView) view;
				inflateHotelList(textview.getText().toString());
			}
		});
		return v;
	}

	private void inflateHotelList(String s) {
		if (s.equals("Foggy Bottom Metro Station")) {
			listView = (ListView) getView().findViewById(R.id.hotellist);
			int[] imageIds = new int[] { R.drawable.photo, R.drawable.photo1,
					R.drawable.photo2, R.drawable.photo3, R.drawable.photo4,
					R.drawable.photo5 };
			String[] hotelName = new String[] { "Courtyard Washington",
					"The Melrose Georgetown", "Georgetown Suites", "The River Inn", "GWU Inn", "Residence Inn" };
			String[] hotelNote = new String[] {
					"Excellent Value for Your Money", "Best Service Ever",
					"Suits with Kitchen", "Free Wife near Subway", "You Are the Best","Welcome, My Customers" };

			String[] hotelAddress = new String[] {
					"515 20th St NW Washington, DC 20006",
					"2430 Pennsylvania Ave NW Washington, DC 20037",
					"1111 30th St NW Washington, DC 20007",
					"824 New Hampshire Ave NW Washington, DC 20037",
					"924 25th St NW Washington, DC 20037",
					"801 New Hampshire Ave NW Washington, DC 20037"
			};
			// Map<String, Object>
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < hotelName.length; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("imageIds", imageIds[i]);
				map.put("hotelName", hotelName[i]);
				map.put("hotelNote", hotelNote[i]);
				map.put("hotelAddress", hotelAddress[i]);
				listMap.add(map);
			}
			SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),
					listMap, R.layout.hotellist_item, new String[] {
							"imageIds", "hotelName", "hotelNote",
							"hotelAddress" }, new int[] { R.id.hotelImage,
							R.id.hotelName, R.id.hotelNote, R.id.hotelAddress });
			listView.setAdapter(simpleAdapter);
		}
	}
}

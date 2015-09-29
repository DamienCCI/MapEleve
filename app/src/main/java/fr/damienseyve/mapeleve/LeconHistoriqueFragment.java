package fr.damienseyve.mapeleve;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LeconHistoriqueFragment extends Fragment implements OnMapReadyCallback {

    MapView gMapView;
    List<LatLng> mCapturedLocations;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_lecon_historique, container, false);

        gMapView = (MapView) layout.findViewById(R.id.mapViewLeconHistorique);
        gMapView.onCreate(savedInstanceState);
        gMapView.getMapAsync(this);

        // Initialise la carte.
        MapsInitializer.initialize(this.getActivity());

        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_lecon_historique, menu);
        NavigationDrawerActivity.actionBar.setTitle("Historique");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        LatLng belfort = new LatLng(47.6396740, 6.8638490);

        // Bouton pour repositionner la camera sur la position
        map.setMyLocationEnabled(true);

        // Vue normale
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Mouvement de camera a l'ouverture
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(belfort, 13));

        try {
            mCapturedLocations = loadGpxData(Xml.newPullParser(),
                    getResources().openRawResource(R.raw.gpx_data));

            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            PolylineOptions polyline = new PolylineOptions();

            for (LatLng ll : mCapturedLocations) {
                com.google.android.gms.maps.model.LatLng mapPoint =
                        new com.google.android.gms.maps.model.LatLng(ll.latitude, ll.longitude);
                builder.include(mapPoint);
                polyline.add(mapPoint);
            }

            map.addPolyline(polyline.color(Color.RED));
            map.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 0));
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

    }

    private List<LatLng> loadGpxData(XmlPullParser parser, InputStream gpxIn)
            throws XmlPullParserException, IOException {
        List<LatLng> latLngs = new ArrayList<>();   // List<> as we need subList for paging later
        parser.setInput(gpxIn, null);
        parser.nextTag();

        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            if (parser.getName().equals("wpt")) {
                // Save the discovered lat/lon attributes in each <wpt>
                latLngs.add(new LatLng(
                        Double.valueOf(parser.getAttributeValue(null, "lat")),
                        Double.valueOf(parser.getAttributeValue(null, "lon"))));
            }
            // Otherwise, skip irrelevant data
        }

        return latLngs;
    }


    @Override
    public void onResume() {
        gMapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        gMapView.onLowMemory();
        super.onLowMemory();
    }
}
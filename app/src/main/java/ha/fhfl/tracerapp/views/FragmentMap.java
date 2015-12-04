package ha.fhfl.tracerapp.views;


import android.app.Fragment;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

import ha.fhfl.tracerapp.MainActivity;
import ha.fhfl.tracerapp.models.Model;
import ha.fhfl.tracerapp.R;

public class FragmentMap extends Fragment {

    private static final String TAG = "fhflFragmentMap";

    private MapView mapView;
    protected LocationManager locationManager;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView():  ");
        return inflater.inflate(R.layout.fragment_map, container, false);
    }



    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart():  ");

        mapView =  (MapView) getView().findViewById(R.id.mapView);

        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.setUseDataConnection(true);
        mapView.setTileSource(TileSourceFactory.MAPQUESTOSM);

        IMapController mapViewController = mapView.getController();
        mapViewController.setZoom(15);
        mapViewController.setCenter(new GeoPoint(54.7866382,9.4350427));


        onUpDate();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void onUpDate() {
        Log.d(TAG, "onUpDate():  ");


        Model model = ((MainActivity)getActivity()).getModel();	 // Zugriff aufs Model
        Log.d(TAG, "onUpDate():  Model getLocation() != null: " + (model.getLocation() != null));

        if(model.getLocation() != null){
            updateMapLocation(new GeoPoint(model.getLocation().getLatitude(), model.getLocation().getLongitude()));
        }



    }

    private void updateMapLocation(GeoPoint geoPoint){
        Log.d(TAG, "updateMapLocation():  ");
        ItemizedOverlay<OverlayItem> locationOverlay;
        ResourceProxy resourceProxy = new DefaultResourceProxyImpl(getActivity().getApplicationContext());

        if(geoPoint != null){
            ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
            items.add(new OverlayItem("Here", "SampleDescription", geoPoint));

            locationOverlay = new ItemizedIconOverlay<OverlayItem>(items,
                    new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                        @Override
                        public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                            return true;
                        }
                        @Override
                        public boolean onItemLongPress(final int index, final OverlayItem item) {
                            return false;
                        }
                    }, resourceProxy);



            mapView.getOverlays().clear();
            mapView.getOverlays().add(locationOverlay);
            mapView.getController().setCenter(geoPoint);
            mapView.invalidate();
        } else {
            mapView.getOverlays().clear();
            mapView.invalidate();
        }

    }
}

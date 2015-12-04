package ha.fhfl.tracerapp.views;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ha.fhfl.tracerapp.MainActivity;
import ha.fhfl.tracerapp.models.Model;
import ha.fhfl.tracerapp.R;

public class FragmentSpeed extends Fragment {
    private static final String TAG = "fhflFragmentSpeed";

    private TextView speedTextView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView():  ");
        return inflater.inflate(R.layout.fragment_speed, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart():  ");

        speedTextView = (TextView) getView().findViewById(R.id.speedTextView);

        onUpDate();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void onUpDate() {
        Log.d(TAG, "onUpDate():  ");

        Model model = ((MainActivity) getActivity()).getModel();    // Zugriff aufs Model
        Log.d(TAG, "onUpDate():  Model getLocation() != null: " + (model.getLocation() != null));

        if(model.getLocation() != null){
            speedTextView.setText(String.format("KM/H : %.2f", model.getLocation().getSpeed()));
        }
    }
}
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

public class FragmentCourse extends Fragment {

    private static final String TAG = "fhflFragmentCourse";

    private TextView courseTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView():  ");

        return inflater.inflate(R.layout.fragment_course, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart():  ");

        courseTextView = (TextView) getView().findViewById(R.id.courseTextView);

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

        if (model.getLocation() != null) {
            float bearing = model.getLocation().getBearing();

            courseTextView.setText(String.format("Course : %.4f", bearing));
        }
    }

}
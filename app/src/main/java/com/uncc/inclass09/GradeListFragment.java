package com.uncc.inclass09;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GradeListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GradeListFragment extends Fragment {

    GradeListFragmentListener mListener;
    private final String TAG = "GradeListFragment";


    public GradeListFragment() {
        // Required empty public constructor
    }

    interface GradeListFragmentListener{
        void AddCourseFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (GradeListFragmentListener) context;
        Log.d(TAG, "onAttach: ");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment GradeListFragment.
     */

    public static GradeListFragment newInstance(String param1, String param2) {
        GradeListFragment fragment = new GradeListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    /**
     * the menu layout has the 'add/new' menu item
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    /**
     * react to the user tapping/selecting an options menu item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_quote:
                mListener.AddCourseFragment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grade_list, container, false);
    }
}
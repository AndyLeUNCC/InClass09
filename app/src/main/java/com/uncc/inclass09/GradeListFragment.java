package com.uncc.inclass09;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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

    public class GradeRecyclerViewAdapter extends RecyclerView.Adapter<GradeRecyclerViewAdapter.ViewForumHolder> {
        ArrayList<Grade> grades;
        Context context;
        public GradeRecyclerViewAdapter(Context context, ArrayList<Grade> grades){
            this.context = context;
            this.grades = grades;

        }

        @Override
        public ViewForumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_view_grade_list_item, parent, false);
            ViewForumHolder vh = new ViewForumHolder(view);
            return vh;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onBindViewHolder(@NonNull ViewForumHolder holder, int position) {
//            String user = userList.get(position);
//            String userid = userIdList.get(position);
//            holder.setupRowItem(userid,user);
        }

        @Override
        public int getItemCount() {
            //return userIdList.size();
            return 0;
        }

        public class ViewForumHolder extends RecyclerView.ViewHolder {
            String username;
            TextView txtName;
            String userId;


            public ViewForumHolder(View view) {
                super(view);
                //txtName = view.findViewById(R.id.txtNameofUser);



            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            public void setupRowItem( String userID,  String user){

                this.username = user;
                txtName.setText(username);
                userId = userID;

            }
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grade_list, container, false);
        return view;
    }
}
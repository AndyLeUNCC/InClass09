package com.uncc.inclass09;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Inclass09
 * File Name:GradeListFragment.java
 * Full Name of the student:
 * 1. Sai Kandimalla
 * 2. Andy Le
 */

public class GradeListFragment extends Fragment {

    GradeListFragmentListener mListener;
    RecyclerView recyclerGrades;
    LinearLayoutManager layoutManager;
    GradeRecyclerViewAdapter adapter;
    TextView txtGPAF, txtHoursF;
    AppDatabase db;
    float intTotalGPA;
    float intCredit;

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
        ArrayList<Grade> gradesList;
        Context context;
        public GradeRecyclerViewAdapter(Context context, ArrayList<Grade> grades){
            this.context = context;
            this.gradesList = grades;

        }

        public void removeItem(int position){
            gradesList.remove(position);
            txtGPAF.setText("");
            txtHoursF.setText("");
            intCredit = 0;
            intTotalGPA=0;
            notifyDataSetChanged();
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
              Grade grade = gradesList.get(position);
              int credit = grade.creditHours;
              if(grade.courseGrade == 'A'){
                  intTotalGPA+= 4 * credit;
              } else if(grade.courseGrade == 'B'){
                  intTotalGPA+= 3 * credit;
              } else if(grade.courseGrade == 'C'){
                  intTotalGPA+= 2 * credit;
              } else if(grade.courseGrade == 'D'){
                  intTotalGPA+= 1 * credit;
              }
              intCredit += grade.creditHours;
              if((position+1) == getItemCount()){
                  txtGPAF.setText((intTotalGPA/intCredit) + " GPA");
                  txtHoursF.setText(intCredit+ " Credit Hours");
              }
              holder.setupRowItem(grade, position);
        }

        @Override
        public int getItemCount() {
            return gradesList.size();
        }

        public class ViewForumHolder extends RecyclerView.ViewHolder {
            Grade grade;
            TextView txtGrade, txtNum, txtName, txtHours;
            ImageView imgDelete;
            int position;

            public ViewForumHolder(View view) {
                super(view);
                txtName = view.findViewById(R.id.txtName);
                txtNum = view.findViewById(R.id.txtCourseNum);
                txtGrade = view.findViewById(R.id.txtGradeLetter);
                txtHours = view.findViewById(R.id.txtCreditHours);
                imgDelete = view.findViewById(R.id.imageDelete);
                imgDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db.gradeDao().delete(grade);
                        removeItem(position);
                    }
                });


            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            public void setupRowItem(Grade grade, int position){
                this.position = position;
                this.grade = grade;
                txtName.setText(grade.courseName);
                txtNum.setText(grade.courseNumber);
                txtGrade.setText(grade.courseGrade + "");
                txtHours.setText(grade.creditHours + " Credit Hours");



            }
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_grade_list, container, false);
        getActivity().setTitle("Grade List");
        db = Room.databaseBuilder(getContext(), AppDatabase.class, "grade.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        txtGPAF = view.findViewById(R.id.txtGPA);
        txtHoursF = view.findViewById(R.id.txtHours);
        recyclerGrades = view.findViewById(R.id.recyclerCourseList);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerGrades.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerGrades.getContext(),
                layoutManager.getOrientation());
        recyclerGrades.addItemDecoration(dividerItemDecoration);
        ArrayList<Grade> grades = (ArrayList<Grade>) db.gradeDao().getAll();
        intTotalGPA = 0;
        intCredit = 0;
        adapter = new GradeRecyclerViewAdapter(getContext(), grades);
        recyclerGrades.setAdapter(adapter);

        return view;
    }
}
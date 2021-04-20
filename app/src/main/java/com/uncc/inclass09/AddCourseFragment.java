package com.uncc.inclass09;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.BackingStoreException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCourseFragment extends Fragment {
    Button btnSubmit;
    EditText edtCourseNum, edtCourseName, edtCreditHours;
    TextView txtCancel;
    RadioGroup rdgGrade;
    RadioButton rdbGrade;
    AppDatabase db;

    final String TAG = "AddCourseFragment";
    AddCourseFragmentListener mListener;

    public AddCourseFragment() {
        // Required empty public constructor
    }

    interface AddCourseFragmentListener{
        void BackFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (AddCourseFragmentListener) context;
        Log.d(TAG, "onAttach: ");
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment AddCourseFragment.
     */
    public static AddCourseFragment newInstance() {
        AddCourseFragment fragment = new AddCourseFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_course, container, false);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        edtCourseName = view.findViewById(R.id.editTextCourseName);
        edtCourseNum = view.findViewById(R.id.editTextCourseNumber);
        edtCreditHours = view.findViewById(R.id.editTextCreditHours);
        rdgGrade = view.findViewById(R.id.radioGroup);
        txtCancel = view.findViewById(R.id.textCancel);
        db = Room.databaseBuilder(getContext(), AppDatabase.class, "grade.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    rdbGrade = view.findViewById(rdgGrade.getCheckedRadioButtonId());
                    db.gradeDao().insertAll(new Grade(edtCourseName.getText().toString(), edtCourseNum.getText().toString() ,
                            Integer.parseInt(edtCreditHours.getText().toString()),rdbGrade.getText().charAt(0)));
                    mListener.BackFragment();
                }
            }
        });
        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.BackFragment();
            }
        });
        return view;
    }

    public boolean validate(){
        if(edtCreditHours.getText().toString().isEmpty()){
            Toast.makeText(getContext(), R.string.credit_empty,Toast.LENGTH_SHORT);
            return false;
        }
        if(edtCourseNum.getText().toString().isEmpty()){
            Toast.makeText(getContext(), R.string.num_empty,Toast.LENGTH_SHORT);
            return false;
        }
        if(edtCourseName.getText().toString().isEmpty()){
            Toast.makeText(getContext(), R.string.name_empty,Toast.LENGTH_SHORT);
            return false;
        }
        return true;
    }
}
package com.example.studentinfoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import java.util.List;

public class DisplayFragment extends Fragment {

    private StudentViewModel viewModel;
    private LinearLayout containerLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(StudentViewModel.class);
        containerLayout = view.findViewById(R.id.container_layout);

        viewModel.getStudentInfoList().observe(getViewLifecycleOwner(), this::updateUI);

        return view;
    }

    private void updateUI(List<StudentInfo> studentInfoList) {
        containerLayout.removeAllViews();
        if (studentInfoList.isEmpty()) {
            TextView emptyText = new TextView(requireContext());
            emptyText.setText("No student data available");
            containerLayout.addView(emptyText);
        } else {
            for (StudentInfo info : studentInfoList) {
                View studentView = getLayoutInflater().inflate(R.layout.item_student_info, containerLayout, false);
                TextView nameText = studentView.findViewById(R.id.text_name);
                TextView ageText = studentView.findViewById(R.id.text_age);
                TextView gradeText = studentView.findViewById(R.id.text_grade);
                TextView majorText = studentView.findViewById(R.id.text_major);

                nameText.setText("Name: " + info.getName());
                ageText.setText("Age: " + info.getAge());
                gradeText.setText("Grade: " + info.getGrade());
                majorText.setText("Major: " + (info.getMajor() != null ? info.getMajor() : "N/A"));

                containerLayout.addView(studentView);
            }
        }
    }
}
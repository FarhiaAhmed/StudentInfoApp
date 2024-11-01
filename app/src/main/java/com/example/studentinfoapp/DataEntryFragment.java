package com.example.studentinfoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class DataEntryFragment extends Fragment {

    private StudentViewModel viewModel;
    private TextInputEditText nameInput, ageInput, gradeInput, majorInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_entry, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(StudentViewModel.class);

        nameInput = view.findViewById(R.id.edit_name);
        ageInput = view.findViewById(R.id.edit_age);
        gradeInput = view.findViewById(R.id.edit_grade);
        majorInput = view.findViewById(R.id.edit_major);
        MaterialButton submitButton = view.findViewById(R.id.button_submit);
        MaterialButton clearButton = view.findViewById(R.id.button_clear);

        submitButton.setOnClickListener(v -> submitData());
        clearButton.setOnClickListener(v -> clearInputs());

        return view;
    }

    private void submitData() {
        String name = nameInput.getText().toString();
        String ageStr = ageInput.getText().toString();
        String gradeStr = gradeInput.getText().toString();
        String major = majorInput.getText().toString();

        if (validateInput(name, ageStr, gradeStr)) {
            int age = Integer.parseInt(ageStr);
            int grade = Integer.parseInt(gradeStr);
            viewModel.addStudentInfo(new StudentInfo(name, age, grade, major.isEmpty() ? null : major));
            clearInputs();
            Snackbar.make(requireView(), "Student information submitted successfully", Snackbar.LENGTH_SHORT).show();
        }
    }

    private boolean validateInput(String name, String ageStr, String gradeStr) {
        if (name.isEmpty()) {
            showError("Name cannot be empty");
            return false;
        }
        try {
            int age = Integer.parseInt(ageStr);
            if (age <= 0) {
                showError("Please enter a valid age");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Please enter a valid age");
            return false;
        }
        try {
            int grade = Integer.parseInt(gradeStr);
            if (grade < 0 || grade > 100) {
                showError("Please enter a valid grade (0-100)");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Please enter a valid grade");
            return false;
        }
        return true;
    }

    private void showError(String message) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show();
    }

    private void clearInputs() {
        nameInput.getText().clear();
        ageInput.getText().clear();
        gradeInput.getText().clear();
        majorInput.getText().clear();
    }
}
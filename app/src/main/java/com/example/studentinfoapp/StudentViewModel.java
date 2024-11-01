package com.example.studentinfoapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class StudentViewModel extends ViewModel {
    private final MutableLiveData<List<StudentInfo>> studentInfoList = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<StudentInfo>> getStudentInfoList() {
        return studentInfoList;
    }

    public void addStudentInfo(StudentInfo info) {
        List<StudentInfo> currentList = studentInfoList.getValue();
        if (currentList != null) {
            currentList.add(info);
            studentInfoList.setValue(currentList);
        }
    }

    public void clearStudentInfoList() {
        studentInfoList.setValue(new ArrayList<>());
    }
}
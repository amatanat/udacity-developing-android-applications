/*
* Copyright (C) 2016 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.todolist;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.android.todolist.data.TaskContract.TaskEntry;


public class AddTaskActivity extends AppCompatActivity {

    // Declare a member variable to keep track of a task's selected mPriority
    private int mPriority;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Initialize to highest mPriority by default (mPriority = 1)
        ((RadioButton) findViewById(R.id.radButton1)).setChecked(true);
        mPriority = 1;
    }


    /**
     * onClickAddTask is called when the "ADD" button is clicked.
     * It retrieves user input and inserts that new task data into the underlying database.
     */
    public void onClickAddTask(View view) {


        // Not yet implemented
        //  (6) Check if EditText is empty, if not retrieve input and store it in a ContentValues object
        EditText editTextTaskDes = (EditText) view.findViewById(R.id.editTextTaskDescription);

        String taskDescription = editTextTaskDes.getText().toString().trim();
        if (TextUtils.isEmpty(taskDescription)){
            return;
        }

        ContentValues values = new ContentValues();
        values.put(TaskEntry.COLUMN_DESCRIPTION, taskDescription);


        //  (7) Insert new task data via a ContentResolver
        Uri returnUri = getContentResolver().insert(TaskEntry.CONTENT_URI, values);

        //  (8) Display the URI that's returned with a Toast
        Toast.makeText(this, "Returned uri: " + returnUri.toString(), Toast.LENGTH_SHORT).show();
        // [Hint] Don't forget to call finish() to return to MainActivity after this insert is complete
        finish();

    }


    /**
     * onPrioritySelected is called whenever a priority button is clicked.
     * It changes the value of mPriority based on the selected button.
     */
    public void onPrioritySelected(View view) {
        if (((RadioButton) findViewById(R.id.radButton1)).isChecked()) {
            mPriority = 1;
        } else if (((RadioButton) findViewById(R.id.radButton2)).isChecked()) {
            mPriority = 2;
        } else if (((RadioButton) findViewById(R.id.radButton3)).isChecked()) {
            mPriority = 3;
        }
    }
}

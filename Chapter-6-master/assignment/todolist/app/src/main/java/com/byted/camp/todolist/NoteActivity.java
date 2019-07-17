package com.byted.camp.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.db.TodoContract;
import com.byted.camp.todolist.db.TodoDbHelper;

public class NoteActivity extends AppCompatActivity {

    private EditText editText;
    private Button addBtn;
    private SQLiteDatabase db;
    private TodoDbHelper todoDbHelper;
    private RadioGroup radioGroup;
    int priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle(R.string.take_a_note);
        todoDbHelper = new TodoDbHelper(this);
        db = todoDbHelper.getWritableDatabase();
        editText = findViewById(R.id.edit_text);
        editText.setFocusable(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(editText, 0);
        }
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonHigh:
                        priority = 3;
                        break;
                    case R.id.radioButtonNormal:
                        priority = 2;
                        break;
                    default:
                        priority = 1;
                }
            }
        });

        addBtn = findViewById(R.id.btn_add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence content = editText.getText();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(NoteActivity.this,
                            "No content to add", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean succeed = saveNote2Database(content.toString().trim());
                if (succeed) {
                    Toast.makeText(NoteActivity.this,
                            "Note added", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                } else {
                    Toast.makeText(NoteActivity.this,
                            "Error", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
       db.close();
        todoDbHelper.close();
        super.onDestroy();
    }

    private boolean saveNote2Database(String content) {
        // TODO 插入一条新数据，返回是否插入成功
        if(db ==null ||TextUtils.isEmpty(content)){
            return  false;
        }


       // TodoContract todoContract = new TodoContract();

        ContentValues values = new ContentValues();
        values.put(TodoContract.FeedEntry.COLUMN_CONTENT, content);
        values.put(TodoContract.FeedEntry.COLUMN_STATE, State.TODO.intValue);
        values.put(TodoContract.FeedEntry.COLUMN_DATE, System.currentTimeMillis());
        values.put(TodoContract.FeedEntry.COLUMN_PRIORITY,priority);
        long rowId = db.insert(TodoContract.FeedEntry.TABLE_NAME, null, values);
        return  rowId != -1;





    }
}

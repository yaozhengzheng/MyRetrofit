package com.example.myretrofit.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.myretrofit.R;
import com.example.myretrofit.adapter.Adapter;
import com.example.myretrofit.bean.Student;
import com.example.myretrofit.db.EncryptedDBHelper;
import com.example.myretrofit.db.PlainTextDBHelper;
import com.tencent.wcdb.Cursor;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 姚政诤
 */
public class WcdbActivity extends AppCompatActivity {

    private static final String TAG = "WCDB.EncryptDBSample";

    @Bind(R.id.list)
    ListView MListView;

    private SQLiteDatabase mDb;
    private SQLiteOpenHelper mDbHelper;
    private int mDbVersion;

    private Adapter mAdapter;
    private List<Student> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wcdb);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        mAdapter = new Adapter(list, WcdbActivity.this);
        MListView.setAdapter(mAdapter);
    }


    @OnClick({R.id.btn_init_plain, R.id.btn_init_encrypted, R.id.btn_insert, R.id.btn_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //在版本1中创建或打开数据库，然后刷新适配器
            case R.id.btn_init_plain:
                new AsyncTask<Void, Void, Cursor>() {
                    @Override
                    protected void onPreExecute() {
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected Cursor doInBackground(Void... voids) {
                        if (mDbHelper != null && mDb != null && mDb.isOpen()) {
                            mDbHelper.close();
                            mDbHelper = null;
                            mDb = null;
                        }

                        mDbHelper = new PlainTextDBHelper(WcdbActivity.this);
                        mDbHelper.setWriteAheadLoggingEnabled(true);
                        mDb = mDbHelper.getWritableDatabase();
                        mDbVersion = mDb.getVersion();
                        return mDb.rawQuery("SELECT rowid as _id,content, '???' as sender FROM message;", null);
                    }

                    @Override
                    protected void onPostExecute(Cursor cursor) {
                        list = getAllStudent(cursor);
                        mAdapter.changeCursor(list);
                    }
                }.execute();
                break;
            case R.id.btn_init_encrypted:
                //再版本2中创建或打开数据库，然后刷新适配器
                //如果纯文本数据库存在并加密，则不传送全部数据
                // 从纯文本数据库（再版本1中）的数据，然后升级它到 版本2
                // 有关数据传输和模式升级的详细信息，请参阅EncryptedDBHelper.java
                new AsyncTask<Void, Void, Cursor>() {
                    @Override
                    protected void onPreExecute() {
                        mAdapter.changeCursor(list);
                    }

                    @Override
                    protected Cursor doInBackground(Void... voids) {
                        if (mDbHelper != null && mDb != null && mDb.isOpen()) {
                            mDbHelper.close();
                            mDbHelper = null;
                            mDb = null;
                        }

                        String passphrase = "passphrase";
                        mDbHelper = new EncryptedDBHelper(WcdbActivity.this, passphrase);
                        mDbHelper.setWriteAheadLoggingEnabled(true);
                        mDb = mDbHelper.getWritableDatabase();
                        mDbVersion = mDb.getVersion();
                        return mDb.rawQuery("SELECT rowid as _id,content,sender FROM message;", null);
                    }

                    @Override
                    protected void onPostExecute(Cursor cursor) {
                        list = getAllStudent(cursor);
                        mAdapter.changeCursor(list);
                    }
                }.execute();
                break;
            case R.id.btn_insert:
                //将消息插入数据库。
                //要测试数据传输，初始化纯文本数据库，插入消息，
                //然后init加密数据库。
                final DateFormat DATE_FORMAT = SimpleDateFormat.getDateTimeInstance();
                new AsyncTask<Void, Void, Cursor>() {
                    @Override
                    protected void onPreExecute() {
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected Cursor doInBackground(Void... params) {
                        if (mDb == null || !mDb.isOpen())
                            return null;

                        String message = "Message inserted on " + DATE_FORMAT.format(new Date());

                        if (mDbVersion == 1) {
                            mDb.execSQL("INSERT INTO message VALUES (?);",
                                    new Object[]{"yyh"});
                            return mDb.rawQuery("SELECT rowid as _id, content, '???' as sender FROM message;",
                                    null);
                        } else {
                            mDb.execSQL("INSERT INTO message VALUES (?, ?);",
                                    new Object[]{"yyh", "男"});
                            return mDb.rawQuery("SELECT rowid as _id, content, sender FROM message;",
                                    null);
                        }
                    }

                    @Override
                    protected void onPostExecute(Cursor cursor) {
                        if (cursor == null)
                            return;
                        list = getAllStudent(cursor);
                        mAdapter.changeCursor(list);
                    }
                }.execute();
                break;
            case R.id.btn_delete:
                if (mDb == null || !mDb.isOpen()){
                    return  ;
                }
                mDb.execSQL("DELETE FROM message WHERE content"+"=?",
                        new Object[]{"yyh"});
                com.tencent.wcdb.Cursor cursor =  mDb.rawQuery("SELECT rowid as _id, content, sender FROM message;",
                        null);
                list = getAllStudent(cursor);
                mAdapter.changeCursor(list);
                break;
        }
    }

    public List<Student> getAllStudent(com.tencent.wcdb.Cursor cursor) {
        List<Student> pointList = new ArrayList<Student>();
        Student student;
        while (cursor.moveToNext()) {
            student = new Student();
            student.setName(cursor.getString(cursor
                    .getColumnIndex("content")));
            student.setSex(cursor.getString(cursor
                    .getColumnIndex("sender")));
            pointList.add(student);
        }
        return pointList;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

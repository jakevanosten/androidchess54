package com.example.jake.chessGame;

import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RecordsActivity extends AppCompatActivity {

    private File root;
    public ArrayList<File> recordFileList = new ArrayList<>();
    private LinearLayout view;
    //private List<String> recordNames;

    public String[] recordNames;


    //From listview example
    private ListView recordListView;
    private ArrayAdapter<String> listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        view = findViewById(R.id.RLView);
        recordListView = findViewById(R.id.recordList);


        root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        getRecordFile(root);

        for(int i = 0; i < recordFileList.size(); i++){
            TextView textView = new TextView(this);
            textView.setText(recordFileList.get(i).getName());
            System.out.println("recordFile name is: " + recordFileList.get(i).getName());

            if(recordFileList.get(i).isDirectory()){
                textView.setTextColor(Color.parseColor("#FF0000"));
            }
            view.addView(textView);
        }

        //TEST if the above fails
        //listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, recordNames);
        //recordListView.setAdapter(listAdapter);


        /*
        recordNames = new ArrayList<String>();
        String root_sd = Environment.getExternalStorageDirectory().toString();
        root = new File(root_sd, "/external_sd");

        File list[] = root.listFiles();

        for(int i = 0; i < list.length; i++){

            recordNames.add(list[i].getName());
        }

        ArrayAdapter<String> setListAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, recordNames);
        mkdirs
        */




    }

    public ArrayList<File> getRecordFile(File root) {

        //ArrayList<File> tmpArr = new ArrayList<>();

        File listFile[] =root.listFiles();



        if(listFile != null && listFile.length > 0){

            for (int i = 0; i < listFile.length; i++){

                if(listFile[i].isFile()){
                    if(listFile[i].getName().endsWith(".txt")){
                        recordFileList.add(listFile[i]);
                        System.out.println("filename: " + listFile[i].getName());
                        recordNames[i] = listFile[i].getName();
                    }
                }

            }


        }
        return recordFileList;
    }


    public boolean isExternalStorageReadable() {

        String state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            return true;
        }
        return false;

    }

/*
    private String readFromFile(Context context){
        String ret = "";

        File path = context.getExternalFilesDir(null);
        File dir = new File(path, "record0.txt");

        try{

                InputStream inputstream = context.openFileInput("record0.txt");

                if(inputstream != null){
                    InputStreamReader inputStreamReader = new InputStreamReader(inputstream);

                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    String receiveString = "";
                    StringBuilder stringBuilder = new StringBuilder();

                    while((receiveString = bufferedReader.readLine()) != null){

                        stringBuilder.append(receiveString);
                    }
                    inputstream.close();
                    ret = stringBuilder.toString();
                }

        }catch(Exception e){

            Log.e("login activity", "can not read file: " + e.toString());
        }

        return ret;
    }
*/



     /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        ListView listview = (ListView) findViewById(R.id.recordlist);

        ArrayList<String> recordFiles = GetFiles("/sdcard/recordedGames");

    }

    private ArrayList<String> GetFiles(String directory) {

        boolean isExternalStorageReadable = isExternalStorageReadable();

        if (isExternalStorageReadable == true) {

            ArrayList<String> records = new ArrayList<String>();

            File file = new File(directory);

            file.mkdirs();

            File[] recordArr = file.listFiles();

            if (recordArr.length == 0) {
                System.out.println("There are no files!! Sorry.");
                return null;
            } else {
                for (int i = 0; i < recordArr.length; i++) {

                    String recordName = recordArr[i].getName();
                    records.add(recordName);

                    System.out.println("Record Name: " + recordName);
                }
            }

            return records;

        } else {
            System.out.println("Err. Could not read from external storage");
            return null;
        }
    }

    */
}


package com.example.jake.chessGame;

import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class RecordsActivity extends AppCompatActivity {

    private File root;
    private ArrayList<File> recordFileList = new ArrayList<>();
    private LinearLayout view;


    //From listview example
    private ListView recordListView;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        view = (LinearLayout) findViewById(R.id.RLView);
        recordListView = (ListView) findViewById(R.id.recordList);



        //get the root path of directory
        root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        recordFileList = getRecordFile(root);

        for(int i = 0; i < recordFileList.size(); i++){
            TextView textView = new TextView(this);
            textView.setText(recordFileList.get(i).getName());
            System.out.println("recordFile name is: " + recordFileList.get(i).getName());

            if(recordFileList.get(i).isDirectory()){
                textView.setTextColor(Color.parseColor("#FF0000"));
            }
            view.addView(textView);
        }


        //listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, recordFileList);


        //recordFileList.get(i).getName()




    }

    public ArrayList<File> getRecordFile(File root) {

        ArrayList<File> tmpArr = new ArrayList<>();

        File listFile[] =root.listFiles();

        if(listFile != null && listFile.length > 0){

            for (int i = 0; i < listFile.length; i++){

                if(listFile[i].isFile()){
                    if(listFile[i].getName().endsWith(".txt")){
                        tmpArr.add(listFile[i]);
                        System.out.println("filename: " + listFile[i].getName());
                    }
                }

            }


        }
        return tmpArr;
    }


    public boolean isExternalStorageReadable() {

        String state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            return true;
        }
        return false;

    }



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


package com.example.ngm.philosophy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> data = new ArrayList<String>();

    private void ParseData() throws IOException, InterruptedException {
        InputStream is = getResources().openRawResource(R.raw.text0);
        BufferedReader bf = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        ReadData rd0 = new ReadData();
        rd0.setBf(bf);

        is = getResources().openRawResource(R.raw.text1);
        bf = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        ReadData rd1 = new ReadData();
        rd1.setBf(bf);

        is = getResources().openRawResource(R.raw.text2);
        bf = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        ReadData rd2 = new ReadData();
        rd2.setBf(bf);

        is = getResources().openRawResource(R.raw.text3);
        bf = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        ReadData rd3 = new ReadData();
        rd3.setBf(bf);

        is = getResources().openRawResource(R.raw.text4);
        bf = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        ReadData rd4 = new ReadData();
        rd4.setBf(bf);

        is = getResources().openRawResource(R.raw.text5);
        bf = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        ReadData rd5 = new ReadData();
        rd5.setBf(bf);

        rd0.run();
        rd1.run();
        rd2.run();
        rd3.run();
        rd4.run();
        rd5.run();

        rd0.join();
        rd1.join();
        rd2.join();
        rd3.join();
        rd4.join();
        rd5.join();

        data.addAll(rd0.getArray());
        data.addAll(rd1.getArray());
        data.addAll(rd2.getArray());
        data.addAll(rd3.getArray());
        data.addAll(rd4.getArray());
        data.addAll(rd5.getArray());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            this.ParseData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void searchMessage(View view) {
        EditText text = (EditText) findViewById(R.id.editText);
        String value = text.getText().toString();

        ArrayList<Integer> indexes = new ArrayList<Integer>();
        int i = 0;
        if (!value.isEmpty() && value != null) {
            for (String str : data) {
                if (str.contains(value))
                    indexes.add(i);
                i++;
            }

        }
        Intent intent = new Intent(this, ShowAnswersActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("data", data);
        bundle.putIntegerArrayList("indexes", indexes);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}

class ReadData extends Thread{
    private BufferedReader bf;
    private ArrayList<String> array = new ArrayList<String>();

    public void setBf(BufferedReader bf) {
        this.bf = bf;
    }

    public void run() {
        String str = "", temp = null;

        do {
            try {
                temp = bf.readLine();
            } catch (IOException e) {
                temp = null;
            }

            if (temp == null) {
                break;
            }

            if (temp.length() >= 6 && temp.substring(0, 6).contains("<Krak>")) {
                this.array.add(str.substring(0, str.length()-1));
                str = "";
            } else {
                str = str.concat(temp) + '\n';
            }
        } while (Boolean.TRUE);
    }

    public ArrayList<String> getArray() {
        return this.array;
    }
}

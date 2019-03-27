package com.example.a2praktiskaismape;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button poga;
    Button parietUzKarti;
    ListView listView;
    String[] datiParsed = new String[0];
    int length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final IegutDatus process = new IegutDatus();
        process.execute();

        parietUzKarti = findViewById(R.id.switch2);
        parietUzKarti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityMaps();
            }
        });

        poga = findViewById(R.id.button);
        listView = findViewById(R.id.listView);

        poga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datiParsed = process.returnStringArray();
                length = process.returnLength();
                CustomAdapter customAdapter = new CustomAdapter();
                listView.setAdapter(customAdapter);
            }
        });
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.list_item,null);

            TextView item = (TextView)convertView.findViewById(R.id.textView1);

            item.setText(datiParsed[position]);
            return convertView;
        }
    }

    public void openActivityMaps() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

}

package com.multiselect;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    int preSelectedIndex = -1;
    ArrayList<Integer> array_image = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = (ListView) findViewById(R.id.listview);

        array_image.add(R.drawable.uk);
        array_image.add(R.drawable.uae);
        final List<UserModel> users = new ArrayList<>();
        users.add(new UserModel(false, "English(UK)",array_image.get(0)));
        users.add(new UserModel(false, "العربية",array_image.get(1)));


        final CustomAdapter adapter = new CustomAdapter(this, users);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                UserModel model = users.get(i); //changed it to model because viewers will confused about it

                model.setSelected(true);

                users.set(i, model);

                if (preSelectedIndex > -1){

                    UserModel preRecord = users.get(preSelectedIndex);
                    preRecord.setSelected(false);

                    users.set(preSelectedIndex, preRecord);

                }

                preSelectedIndex = i;

                //now update adapter so we are going to make a update method in adapter
                //now declare adapter final to access in inner method

                adapter.updateRecords(users);
            }
        });

    }
}

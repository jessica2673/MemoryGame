package com.firstapp.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public String names[] = {"back1", "back2", "back3", "back4", "back5", "back6", "back7","back8","back9"};
    public int[] images = {R.drawable.back_card};
    public int[] hidden = { R.drawable.apple, R.drawable.grape, R.drawable.kiwi, R.drawable.banana, R.drawable.orange, R.drawable.grape, R.drawable.apple, R.drawable.kiwi, R.drawable.banana};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Random rand = new Random();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        for (int i=0; i<9; i++)
        {
            HashMap<String,String> hashMap=new HashMap<>();//create a hashmap to store the data in key value pair
            hashMap.put("name", names[i]);
            hashMap.put("image", images[0]+"");
            list.add(hashMap);//add the hashmap into arrayList
        }

        String[] from={"card", "image"};//string array
        int[] to={R.id.textView,R.id.imageView};//int array of views id's

        GridView gridView = findViewById(R.id.gridview);
        SimpleAdapter Adapter = new SimpleAdapter(this, list ,R.layout.card_item,from,to);
        gridView.setAdapter(Adapter);

        ArrayList<Integer> matchup = new ArrayList<>();
        matchup.add(0);
        matchup.add(1);
        matchup.add(2);
        matchup.add(3);
        matchup.add(4);
        matchup.add(5);
        matchup.add(6);
        matchup.add(7);
        matchup.add(8);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set an Intent to Another Activity
                Intent intent = new Intent(MainActivity.this, OpenCard.class);
                int i = 8;
                while(i > 0){
                    int x = rand.nextInt(9);
                    if(x < i) {
                        intent.putExtra("image", hidden[matchup.get(i)]); // put image data in Intent
                        matchup.remove(i);
                        i--;
                    }
                }
                startActivity(intent); // start Intent
            }
        });

    }
}

/*Intent intent = new Intent(MainActivity.this, OpenCard.class);
                int i = 8;
                while(i >= 0){
                    int x = rand.nextInt(9);
                    if(x <= i) {
                        intent.putExtra("image", hidden[i]); // put image data in Intent
                        matchup.remove(i);
                        i--;
                    }
                }
                startActivity(intent); // start Intent

 */


package thiago.castilho.com.thiagocastilho_comp304_003test1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    List<Model> modelItems;
    List<Stock> stockItems;
    String[] stocks;
    CheckBoxListAdapter adapter;
    Random rnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUi();
    }

    void setUi(){
        rnd = new Random();
        lv = (ListView) findViewById(R.id.lvCheckStocks);
        Resources res = getResources();
        stocks = res.getStringArray(R.array.stocks);

        stockItems = new ArrayList<>();

        for (int i = 0; i < stocks.length; i++){
            stockItems.add(new Stock(
                    i,
                    stocks[i],
                    Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)),
                    0));
        }

        adapter = new CheckBoxListAdapter(this,stockItems);
        lv.setAdapter(adapter);
    }

    void goToChart(View view){
        SharedPreferences pfrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = pfrefs.edit();

        Gson gson = new Gson();

        for(Stock stock : adapter.getSelectedItems()){
            editor.putString("STOCKS", gson.toJson(adapter.getSelectedItems()));
//            editor.putString(stock.getName(), stock.getName());
        }

        editor.commit();

        startActivity(new Intent(this, ChartActivity.class));
    }
}

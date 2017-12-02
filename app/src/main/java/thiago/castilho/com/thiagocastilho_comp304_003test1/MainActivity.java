package thiago.castilho.com.thiagocastilho_comp304_003test1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
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

public class MainActivity extends AppCompatActivity {
    ListView lv;
    List<Model> modelItems;
    String[] stocks;
    CheckBoxListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUi();
    }

    void setUi(){
        lv = (ListView) findViewById(R.id.lvCheckStocks);
        Resources res = getResources();
        stocks = res.getStringArray(R.array.stocks);

        modelItems = new ArrayList<>();

        for (int i = 0; i < stocks.length; i++){
            modelItems.add(new Model(stocks[i], 0, i));
        }

        adapter = new CheckBoxListAdapter(this,modelItems);
        lv.setAdapter(adapter);
    }

    void goToChart(View view){
        SharedPreferences pfrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = pfrefs.edit();

        for(Model stock : adapter.getSelectedItems()){
            editor.putString(stock.getName(), stock.getName());
        }

        editor.commit();

        startActivity(new Intent(this, ChartActivity.class));
    }
}

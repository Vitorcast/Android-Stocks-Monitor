package thiago.castilho.com.thiagocastilho_comp304_003test1;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    List<String> selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);



        getSelectedItems();
    }

    void getSelectedItems(){
        selectedItems = new ArrayList<>();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Resources res = getResources();

        for(String stock:res.getStringArray(R.array.stocks)){

            if (prefs.getString(stock,"") != ""){
                selectedItems.add(stock);
            }
        }
    }
}

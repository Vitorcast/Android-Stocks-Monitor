package thiago.castilho.com.thiagocastilho_comp304_003test1;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    SharedPreferences prefs;
    List<Stock> selectedItems;
    ListView lvStocksChosen;
    ImageView ivChartConainer;
    TextView tvCharts;
    Canvas canvas;
    Bitmap bmp;
    Paint p;
    Gson gson;

    int chartWidth = 800;
    int chartHeight = 600;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        init();
    }


    void init(){
        setUI();
        getSelectedItems();
        setStockValues();
        drawChart();
        drawBoundaries();
    }

    void setUI(){
        lvStocksChosen = findViewById(R.id.lvStocksChosen);
        ivChartConainer = findViewById(R.id.ivChartContainer);
        tvCharts = findViewById(R.id.tvCharts);
        bmp = Bitmap.createBitmap(chartWidth, chartHeight, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bmp);
        ivChartConainer.draw(canvas);
        p = new Paint();
    }

    void drawChart(){
        for(Stock stock: selectedItems){
            int initialX = 0;
            for(int i = 0; i < stock.getStockValues().size()-1; i++){
                drawLine(
                        initialX,
                        Math.abs(stock.getStockValues().get(i) - chartHeight),
                        initialX+100,
                        Math.abs(stock.getStockValues().get(i+1) - chartHeight),
                        stock.getColor());

                initialX += 100;
            }
        }
    }

    void drawBoundaries(){
        for(int i = 0; i <= chartHeight; i = i+100){

            p.setColor(getColor(android.R.color.black));
            p.setStrokeWidth(10);
            canvas.drawPoint(0,i,p);

            if (i == 0 ){
                p.setColor(getColor(android.R.color.black));
                p.setTextSize(50);
                canvas.drawText(String.valueOf(i), 0 + 15,(float) Math.abs(i - chartHeight), p);
            }

            if (i == chartHeight){
                p.setColor(getColor(android.R.color.black));
                p.setTextSize(50);
                canvas.drawText(String.valueOf(i), 0 + 15,(float) Math.abs(i - chartHeight) +50, p);
            }
        }

        for(int i = 0; i <= chartWidth ; i = i+100){
            p.setColor(getColor(android.R.color.black));
            p.setStrokeWidth(10);
            canvas.drawPoint(Math.abs(i-chartWidth), chartHeight, p);

            if (i == chartWidth){
                p.setColor(getColor(android.R.color.black));
                p.setTextSize(50);
                canvas.drawText(String.valueOf(i), (float)chartWidth - 100,chartHeight, p);
            }
        }
    }

    void setStockValues(){
        Resources res = getResources();
        for(Stock stock: selectedItems){
            String name = stock.getName();
            int id = res.getIdentifier(name.toLowerCase()+"StockValues", "array", getPackageName());
            for(String stockValue: res.getStringArray(id)){
                Integer value = Integer.parseInt(stockValue);
                stock.getStockValues().add(value);
            }
        }
    }



    private void drawLine(float x, float y, float xend, float yend, int color) {
        p.setColor(color);
        p.setStrokeWidth(10);
        canvas.drawLine(x, y, xend, yend, p);
        ivChartConainer.setImageBitmap(bmp);
    }

    void getSelectedItems(){
        selectedItems = new ArrayList<>();
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        gson = new Gson();
        String json = prefs.getString("STOCKS", "");
        selectedItems = gson.fromJson(json, new TypeToken<List<Stock>>(){}.getType());
    }
}

package thiago.castilho.com.thiagocastilho_comp304_003test1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thiag on 03/12/2017.
 */

public class Stock {
    private int Id;
    private String name;
    private int color;
    private int value;
    private List<Integer> stockValues;

    public List<Integer> getStockValues() {
        return stockValues;
    }

    public void setStockValues(List<Integer> stockValues) {
        this.stockValues = stockValues;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Stock(int id, String name, int color, int value) {
        Id = id;
        this.name = name;
        this.color = color;
        this.value = value;
        this.stockValues = new ArrayList<>();
    }
}

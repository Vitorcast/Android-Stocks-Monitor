package thiago.castilho.com.thiagocastilho_comp304_003test1;

/**
 * Created by thiag on 01/12/2017.
 */

public class Model{
    public Integer id;
    public String name;
    public int value; /* 0 -&gt; checkbox disable, 1 -&gt; checkbox enable */

    Model(String name, int value, int id){
        this.name = name;
        this.value = value;
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public int getValue(){
        return this.value;
    }
    public int getId() {return this.id;}

}

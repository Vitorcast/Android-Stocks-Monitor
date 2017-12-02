package thiago.castilho.com.thiagocastilho_comp304_003test1;


import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thiag on 01/12/2017.
 */

public class CheckBoxListAdapter extends BaseAdapter {
    private Context context;
    private List<Model> arrayList;
    private LayoutInflater inflater;
    private Integer selectedId = -1;
    private List<Integer> selectedIds ;
    private List<Model> selectedItems;

    public List<Model> getSelectedItems(){
        return selectedItems;
    }

    public CheckBoxListAdapter(Context context, List<Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(context);

        selectedItems = new ArrayList<>();
        selectedIds = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public Integer getSelectedId() {
        return selectedId;
    }

    public Model getSelectedItem(){
        if(selectedId != -1){
            for(Model model:arrayList){
                if(model.getId() == selectedId){
                    return model;
                }
            }
        }
        return null;
    }

    private class ViewHolder {
        private TextView tvItemView;
        private CheckBox cbItemView;
    }

    private void itemCheckChanged(View view){
        selectedId = (Integer)view.getTag();

        if(((CheckBox)view).isChecked()){
            selectedIds.add(getSelectedId());
            selectedItems.add(getSelectedItem());
        } else {
            selectedIds.remove(getSelectedId());
            selectedItems.remove(getSelectedItem());
        }
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.row, viewGroup, false);

            viewHolder.tvItemView = view.findViewById(R.id.tvItemView);
            viewHolder.cbItemView = view.findViewById(R.id.cbItemView);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.tvItemView.setText(((Model)arrayList.get(i)).getName());
        viewHolder.cbItemView.setChecked(((Model)arrayList.get(i)).getValue() > 0);

        viewHolder.tvItemView.setTag(((Model)arrayList.get(i)).getName());
        viewHolder.cbItemView.setTag(((Model)arrayList.get(i)).getId());

        viewHolder.cbItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCheckChanged(view);
            }
        });

        return view;
    }
}

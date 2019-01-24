package edu.zu.demo.graduation.bazzar.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import edu.zu.demo.graduation.bazzar.Adapter.ExpandableListAdapter;
import edu.zu.demo.graduation.bazzar.Model.Bus;
import edu.zu.demo.graduation.bazzar.Model.Items;
import edu.zu.demo.graduation.bazzar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemsFragment extends Fragment {


    ExpandableListView expandableListView;
    ArrayList<String> headerList;
    HashMap<String ,ArrayList<Items>> childHashMap=new HashMap<>() ;

    public ItemsFragment( ) {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View iteView=inflater.inflate(R.layout.fragment_items, container, false);
         expandableListView=iteView.findViewById(R.id.ItemFragment_expandableList);



         setupData();
        return iteView;
    }

    public  void setupData(){

        headerList=new ArrayList<>();
        headerList.add("Meal");
        headerList.add("Soup");
        headerList.add("Salat");

        ArrayList<Items> meal=new ArrayList<>();
        meal.add(new Items(1,1,"rise","rise , checkn",R.drawable.ic_star,2.5));
        meal.add(new Items(1,1,"rise","rise , checkn",R.drawable.ic_star,2.5));
        meal.add(new Items(1,1,"rise","rise , checkn",R.drawable.ic_star,2.5));
        meal.add(new Items(1,1,"rise","rise , checkn",R.drawable.ic_star,2.5));

        ArrayList<Items> Soup=new ArrayList<>();
        Soup.add(new Items(1,1,"rise","rise , checkn",R.drawable.ic_star,2.5));
        Soup.add(new Items(1,1,"rise","rise , checkn",R.drawable.ic_star,2.5));
        Soup.add(new Items(1,1,"rise","rise , checkn",R.drawable.ic_star,2.5));
        Soup.add(new Items(1,1,"rise","rise , checkn",R.drawable.ic_star,2.5));

        ArrayList<Items> Sweet=new ArrayList<>();
        Sweet.add(new Items(1,1,"rise","rise , checkn",R.drawable.ic_star,2.5));
        Sweet.add(new Items(1,1,"rise","rise , checkn",R.drawable.ic_star,2.5));
        Sweet.add(new Items(1,1,"rise","rise , checkn",R.drawable.ic_star,2.5));
        Sweet.add(new Items(1,1,"rise","rise , checkn",R.drawable.ic_star,2.5));

        childHashMap.put(headerList.get(0),meal);
        childHashMap.put(headerList.get(1),Soup);
        childHashMap.put(headerList.get(2),Sweet);
        ExpandableListAdapter adapter=new ExpandableListAdapter(headerList,childHashMap,getContext());
        expandableListView.setAdapter(adapter);

    }

}

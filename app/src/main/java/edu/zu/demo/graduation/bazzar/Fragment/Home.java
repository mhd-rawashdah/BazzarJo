package edu.zu.demo.graduation.bazzar.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcminarro.roundkornerlayout.RoundKornerLinearLayout;

import java.util.ArrayList;

import edu.zu.demo.graduation.bazzar.Model.Offer;
import edu.zu.demo.graduation.bazzar.Adapter.OffersAdapter;
import edu.zu.demo.graduation.bazzar.R;
import edu.zu.demo.graduation.bazzar.BasicActivity.StoresActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment implements View.OnClickListener {
    TextView showOfferTxt;
    RecyclerView recyclerViewOffers;
    private RoundKornerLinearLayout foodRoundBtn,monaRoundBtn,sweetRoundBtn,crochetRoundBtn,mackupRoundBtn,sewingRoundBtn;
    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewOffers=view.findViewById(R.id.HomeFragment_offersRecycleView);
        foodRoundBtn=view.findViewById(R.id.HomeFragment_foodRound);
        monaRoundBtn=view.findViewById(R.id.HomeFragment_monaHomeRound);
        sweetRoundBtn=view.findViewById(R.id.HomeFragment_sweetRound);
        crochetRoundBtn=view.findViewById(R.id.HomeFragment_crochetRound);
        mackupRoundBtn=view.findViewById(R.id.HomeFragment_mackupRound);
        sewingRoundBtn=view.findViewById(R.id.HomeFragment_sewingRound);
        showOfferTxt=view.findViewById(R.id.Home_txt_showOfers);

        foodRoundBtn.setOnClickListener(this);
        monaRoundBtn.setOnClickListener(this);
        sweetRoundBtn.setOnClickListener(this);
        crochetRoundBtn.setOnClickListener(this);
        mackupRoundBtn.setOnClickListener(this);
        sewingRoundBtn.setOnClickListener(this);
        showOfferTxt.setOnClickListener(this);


        ArrayList<Offer> offers=new ArrayList<>();
        offers.add(new Offer(1,1,"3.5","2.9",R.drawable.background_offers));
        offers.add(new Offer(1,1,"3.5","2.9",R.drawable.background_offers));
        offers.add(new Offer(1,1,"3.5","2.9",R.drawable.background_offers));
        offers.add(new Offer(1,1,"3.5","2.9",R.drawable.background_offers));
        offers.add(new Offer(1,1,"3.5","2.9",R.drawable.background_offers));
        offers.add(new Offer(1,1,"3.5","2.9",R.drawable.background_offers));
        OffersAdapter offersAdapter=new OffersAdapter(offers);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true);
        recyclerViewOffers.setLayoutManager(layoutManager);
        recyclerViewOffers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewOffers.setAdapter(offersAdapter);


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.HomeFragment_foodRound:
                Intent intent=new Intent(getContext(), StoresActivity.class);
                startActivity(intent);
                break;
            case R.id.HomeFragment_monaHomeRound:

                break;
            case R.id.HomeFragment_sweetRound:

                break;
            case R.id.HomeFragment_crochetRound:

                break;
            case R.id.HomeFragment_mackupRound:

                break;
            case  R.id.HomeFragment_sewingRound:

                break;
            case R.id.Home_txt_showOfers:
        }
    }
}

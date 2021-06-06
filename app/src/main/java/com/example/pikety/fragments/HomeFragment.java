package com.example.pikety.fragments;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pikety.MainActivity;
import com.example.pikety.api.model.Picket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.example.pikety.R;
import com.example.pikety.api.model.Worker;

import me.grishka.appkit.fragments.BaseRecyclerFragment;
import me.grishka.appkit.imageloader.ImageLoaderRecyclerAdapter;
import me.grishka.appkit.imageloader.ImageLoaderViewHolder;
import me.grishka.appkit.utils.BindableViewHolder;
import me.grishka.appkit.utils.V;

public class HomeFragment extends BaseRecyclerFragment<Picket> {

    private PicketAdapter adapter;

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        loadData();
        setHasOptionsMenu(false);
    }

    public HomeFragment() {super(20);}

    @Override
    protected void doLoadData(int offset, int count) {
        List<Picket> pickets = new ArrayList<Picket>(){
            {
                add(new Picket(){
                    {
                        setName("Picket1");
                        setCompanyId(1);
                        setCompanyName("Company1");
                        setDescription("SetDescription");
                        setLatitude(0D);
                        setLongitude(0D);
                        setPositionAddress("positionAddress");
                        setWorkers(new ArrayList<Worker>(){
                            {
                                add(new Worker(){
                                    {
                                        setId(1);
                                        setName("worker1");
                                    }
                                });
                                add(new Worker(){
                                    {
                                        setId(2);
                                        setName("worker2");
                                    }
                                });
                            }
                        });
                    }
                });
                add(new Picket(){
                    {
                        setName("Picket2");
                        setCompanyId(2);
                        setCompanyName("Company2");
                        setDescription("SetDescription");
                        setLatitude(1D);
                        setLongitude(1D);
                        setPositionAddress("positionAddress2");
                        setWorkers(new ArrayList<Worker>(){
                            {
                                add(new Worker(){
                                    {
                                        setId(1);
                                        setName("worker3");
                                    }
                                });
                                add(new Worker(){
                                    {
                                        setId(2);
                                        setName("worker4");
                                    }
                                });
                                add(new Worker(){
                                    {
                                        setId(3);
                                        setName("worker5");
                                    }
                                });
                            }
                        });
                    }
                });
            }
        };
        onDataLoaded(pickets, false);
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        if(adapter==null){
            adapter=new PicketAdapter();
            adapter.setHasStableIds(true);
        }
        return adapter;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        list.addItemDecoration(new RecyclerView.ItemDecoration(){
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state){
                outRect.bottom=outRect.top=V.dp(8);
                outRect.left=outRect.right=V.dp(16);
            }
        });
        getToolbar().setElevation(0);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        getToolbar().setElevation(0);
    }

    @Override
    public boolean wantsLightNavigationBar(){
        return true;
    }

    @Override
    public boolean wantsLightStatusBar(){
        return true;
    }



    private class PicketAdapter extends RecyclerView.Adapter<PicketViewHolder>  {

        @NonNull
        @Override
        public PicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            return new PicketViewHolder();
        }

        @Override
        public void onBindViewHolder(@NonNull PicketViewHolder holder, int position){
            holder.bind(data.get(position));
        }

        @Override
        public int getItemCount(){
            return data.size();
        }

        @Override
        public long getItemId(int position){
            return data.get(position).id;
        }
    }

    private class PicketViewHolder extends BindableViewHolder<Picket> implements View.OnClickListener {
        private TextView companyName, name, description, positionAddress, workers;

        public PicketViewHolder(){
            super(getActivity(), R.layout.piket_row);
            companyName=findViewById(R.id.company_name);
            name=findViewById(R.id.name);
            description=findViewById(R.id.description);
            positionAddress=findViewById(R.id.position_address);
            workers=findViewById(R.id.workers);
            itemView.setClipToOutline(true);
            itemView.setElevation(V.dp(2));
            itemView.setOnClickListener(this);
        }

        @Override
        public void onBind(Picket item){
            companyName.setText(item.companyName);
            name.setText(item.name);
            description.setText(item.description);
            positionAddress.setText(item.positionAddress);
            workers.setText(item.workers.stream().map(w-> w.name).collect(Collectors.joining("\n")));
        }

        @Override
        public void onClick(View view){
            //((MainActivity)getActivity()).joinChannel(item);
        }
    }
}


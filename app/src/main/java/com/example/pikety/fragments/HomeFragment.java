package com.example.pikety.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.pikety.PiketActivity;
import com.example.pikety.R;
import com.example.pikety.api.model.Picket;
import com.example.pikety.api.model.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment {
    private ListView listView;
    private List<Picket> pickets;
    //private PicketAdapter adapter;
    public HomeFragment() {super();}

    protected void loadData() {
        pickets = new ArrayList<Picket>(){
            {
                add(new Picket(){
                    {
                        setName("Picket1");
                        setCompanyId(1);
                        setCompanyName("Company1");
                        setDescription("SetDescription");
                        setLatitude(55.683625D);
                        setLongitude(37.574080D);
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
                        setLatitude(59.945933D);
                        setLongitude(30.320045D);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.home, container, false);
        listView = view.findViewById(R.id.listViewID);
        loadData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Picket item = pickets.get(position);
                Intent intent = new Intent(parent.getContext(), PiketActivity.class);
                intent.putExtra(Picket.class.getCanonicalName(), item);
                startActivity(intent);
            }
        });
        listView.setAdapter(new PicketAdapter(this.getContext(), pickets));
        return view;
    }

    private class PicketAdapter extends ArrayAdapter<Picket>{
        public PicketAdapter(Context context, List<Picket> pickets){
            super(context, R.layout.piket_row, pickets);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Picket picket = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.piket_row, null);
            }
            ((TextView) convertView.findViewById(R.id.name))
                    .setText(picket.name);
            ((TextView) convertView.findViewById(R.id.description))
                    .setText(picket.description);
            ((TextView) convertView.findViewById(R.id.position_address))
                    .setText(picket.positionAddress);
            ((TextView) convertView.findViewById(R.id.company_name))
                    .setText(picket.companyName);
            ((TextView) convertView.findViewById(R.id.workers))
                    .setText(picket.workers.stream().map(w-> w.name).collect(Collectors.joining("\n")));
            return convertView;
        }
    }


}


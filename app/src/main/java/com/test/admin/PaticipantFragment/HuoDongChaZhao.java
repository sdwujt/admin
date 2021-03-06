package com.test.admin.PaticipantFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.test.admin.Participant.ViewActivity;
import com.test.admin.R;
import com.test.admin.adapter.ActivityAdapter;
import com.test.admin.bean.AsActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.test.admin.bean.Parameters.staticObjectdId;

/**
 * Created by hc6 on 2016/11/16.
 */

public class HuoDongChaZhao extends Fragment {

    private ListView lv_activity;
    private ActivityAdapter myActivityAdapter;
    private List<AsActivity> asActivityList = new ArrayList<AsActivity>();

    public HuoDongChaZhao() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.com_activity_act_find, container, false);

        lv_activity = (ListView)view.findViewById(R.id.itemList);

        BmobQuery<AsActivity> bmobQuery = new BmobQuery<AsActivity>();
        bmobQuery.addQueryKeys("objectId,acTitle,acContent");
        bmobQuery.findObjects(new FindListener<AsActivity>() {
            @Override
            public void done(List<AsActivity> list, BmobException e) {

                if (e == null) {
                    asActivityList.addAll(list);

                    myActivityAdapter = new ActivityAdapter(getActivity(),asActivityList);

                    lv_activity.setAdapter(myActivityAdapter);
                }
            }
        });

        lv_activity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), ViewActivity.class));

                AsActivity asActivity  = asActivityList.get(position);
                staticObjectdId = asActivity.getObjectId().toString();
            }
        });
        return view;
    }

}
package com.kb.fragment;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.kb.fragment.CollectFragment.MyCollectFragment;
import com.kb.lifeknow.R;
import com.kb.mineactivity.ApplyStationmasterActivity;
import com.kb.mineactivity.AttentionActivity;
import com.kb.mineactivity.BasicsActivity;
import com.kb.mineactivity.LoginActivity;
import com.kb.mineactivity.MyFansActivity;
import com.kb.mineactivity.MyInfomationActivity;
import com.kb.mineactivity.MyOrdersActivity;
import com.kb.mineactivity.PlatformActivity;
import com.kb.utils.RoundImage;

public class MineFragment extends Fragment implements View.OnClickListener{

    private RelativeLayout re_login,re_basic,re_order,re_news,R_stationmaster,R_hongpager,R_vermicelli,R_follow,R_wallet,R_paltform;
    RoundImage img_head;
    View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.activity_mine_fragment,null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView(){
        re_login= (RelativeLayout) mView.findViewById(R.id.re_login);
        img_head= (RoundImage) mView.findViewById(R.id.profile_image);

        re_basic=(RelativeLayout) mView.findViewById(R.id.re_basic);
        re_order=(RelativeLayout)mView.findViewById(R.id.re_order);
        re_news= (RelativeLayout) mView.findViewById(R.id.re_news);
        R_stationmaster= (RelativeLayout) mView.findViewById(R.id.R_stationmaster);
        R_hongpager=(RelativeLayout) mView.findViewById(R.id.R_hongpager);
        R_vermicelli=(RelativeLayout) mView.findViewById(R.id.R_vermicelli);
        R_follow=(RelativeLayout) mView.findViewById(R.id.R_follow);
        R_wallet=(RelativeLayout) mView.findViewById(R.id.R_wallet);
        R_paltform=(RelativeLayout)mView.findViewById(R.id.R_paltform);




        re_login.setOnClickListener(this);
        img_head.setOnClickListener(this);

        re_basic.setOnClickListener(this);
        re_order.setOnClickListener(this);
        re_news.setOnClickListener(this);
        R_stationmaster.setOnClickListener(this);
        R_hongpager.setOnClickListener(this);
        R_follow.setOnClickListener(this);
        R_vermicelli.setOnClickListener(this);
        R_wallet.setOnClickListener(this);
        R_paltform.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.re_login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.profile_image:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.re_basic:
                startActivity(new Intent(getActivity(), BasicsActivity.class));
                break;
            case R.id.re_order:
                startActivity(new Intent(getActivity(), MyOrdersActivity.class));
                break;
            case R.id.re_news:
                startActivity(new Intent(getActivity(), MyInfomationActivity.class));
                break;
            case R.id.R_vermicelli:
                startActivity(new Intent(getActivity(), MyFansActivity.class));
                break;

            case R.id.R_follow:
//                FragmentManager fm = getActivity().getFragmentManager();
//                fm.beginTransaction().replace(R.layout.fragment_container,new MyCollectFragment()).commit();
                break;
            case R.id.R_wallet:
                startActivity(new Intent(getActivity(),AttentionActivity.class));
                break;
            case  R.id.R_paltform:
                startActivity(new Intent(getActivity(),PlatformActivity.class));
                break;

            case R.id.R_stationmaster:
                startActivity(new Intent(getActivity(),ApplyStationmasterActivity.class));
                break;

            case R.id.R_hongpager:

                break;
            default:
                break;

        }
    }
}

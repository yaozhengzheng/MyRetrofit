package com.example.myretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.myretrofit.activity.RecyclerViewActivity;
import com.example.myretrofit.bean.Message;
import com.example.myretrofit.network.ApiClient;
import com.example.myretrofit.network.ApiInterFace;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerController;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.nice_video_player)
    NiceVideoPlayer niceVideoPlayer;
    @Bind(R.id.btn_video_list)
    Button btnVideoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initVideo();
    }

    private void initVideo() {
        niceVideoPlayer.setPlayerType(NiceVideoPlayer.PLAYER_TYPE_IJK);
        niceVideoPlayer.setUp("http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-33-30.mp4", null);
        NiceVideoPlayerController controller = new NiceVideoPlayerController(this);
        controller.setTitle("办公室小野开番外了，居然在办公室开澡堂！老板还点赞？");
        controller.setImage("http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-30-43.jpg");
        niceVideoPlayer.setController(controller);
    }

    @Override
    protected void onStop() {
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
        super.onStop();
    }

    private void loadData() {
        ApiInterFace apiInterFace = ApiClient.getClient().create(ApiInterFace.class);
        Call<Message> call = apiInterFace.getInbox("1228b5a794dc8");
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
//                List<Message.ResultBean> resultList = response.body().getResult();
//                if (resultList != null) {
//                    for (int i = 0; i < resultList.size(); i++) {
//                        System.out.println("********" + resultList.get(i).getCid() + "," + resultList.get(i).getName());
//                    }
//                }
                System.out.println("-*-*-*-*-*-*-" + response.body().getResult());
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.btn_video_list)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
    }
}

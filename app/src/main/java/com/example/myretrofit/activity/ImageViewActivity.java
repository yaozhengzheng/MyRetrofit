package com.example.myretrofit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.myretrofit.R;
import com.sunfusheng.glideimageview.GlideImageLoader;
import com.sunfusheng.glideimageview.GlideImageView;
import com.sunfusheng.glideimageview.ShapeImageView;
import com.sunfusheng.glideimageview.progress.CircleProgressView;
import com.sunfusheng.glideimageview.progress.OnGlideImageViewListener;
import com.sunfusheng.glideimageview.progress.OnProgressListener;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.myretrofit.activity.ImageActivity.KEY_IMAGE_URL;
import static com.example.myretrofit.activity.ImageActivity.KEY_IMAGE_URL_THUMBNAIL;

public class ImageViewActivity extends AppCompatActivity {

    @Bind(R.id.image11)
    GlideImageView image11;
    @Bind(R.id.image12)
    GlideImageView image12;
    @Bind(R.id.image13)
    GlideImageView image13;
    @Bind(R.id.image14)
    GlideImageView image14;
    @Bind(R.id.image21)
    GlideImageView image21;
    @Bind(R.id.image22)
    GlideImageView image22;
    @Bind(R.id.image23)
    GlideImageView image23;
    @Bind(R.id.image24)
    GlideImageView image24;
    @Bind(R.id.image31)
    GlideImageView image31;
    @Bind(R.id.image32)
    GlideImageView image32;
    @Bind(R.id.image33)
    GlideImageView image33;
    @Bind(R.id.image34)
    GlideImageView image34;
    @Bind(R.id.image41)
    GlideImageView image41;
    @Bind(R.id.progressView1)
    CircleProgressView progressView1;
    @Bind(R.id.image42)
    GlideImageView image42;
    @Bind(R.id.progressView2)
    CircleProgressView progressView2;


    String url1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497688355699&di=ea69a930b82ce88561c635089995e124&imgtype=0&src=http%3A%2F%2Fcms-bucket.nosdn.127.net%2Ff84e566bcf654b3698363409fbd676ef20161119091503.jpg";
    String url2 = "http://img1.imgtn.bdimg.com/it/u=4027212837,1228313366&fm=23&gp=0.jpg";

    public static boolean isLoadAgain = false; // Just for fun when loading images!

    public static final String cat = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/screenshot/cat.jpg";
    public static final String cat_thumbnail = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/screenshot/cat_thumbnail.jpg";

    public static final String girl = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/screenshot/girl.jpg";
    public static final String girl_thumbnail = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/screenshot/girl_thumbnail.jpg";

    String gif1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496754078616&di=cc68338a66a36de619fa11d0c1b2e6f3&imgtype=0&src=http%3A%2F%2Fapp.576tv.com%2FUploads%2Foltz%2F201609%2F25%2F1474813626468299.gif";
    String gif2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497276275707&di=57c8c7917e91afc1bc86b1b57e743425&imgtype=0&src=http%3A%2F%2Fimg.haatoo.com%2Fpics%2F2016%2F05%2F14%2F9%2F4faf3f52b8e8315af7a469731dc7dce5.jpg";
    String gif3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497276379533&di=71435f66d66221eb36dab266deb9d6d2&imgtype=0&src=http%3A%2F%2Fatt.bbs.duowan.com%2Fforum%2F201608%2F02%2F190418bmy9zqm94qxlmqf4.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        ButterKnife.bind(this);
        isLoadAgain = new Random().nextInt(3) == 1;
        line1();
        line2();
        line3();
        line41();
        line42();
    }

    private void line1() {
        image11.loadImage(url1, R.color.placeholder_color).listener(new OnProgressListener() {
            @Override
            public void onProgress(String imageUrl, long bytesRead, long totalBytes, boolean isDone, GlideException exception) {
                Log.d("--->image11", "bytesRead: " + bytesRead + " totalBytes: " + totalBytes + " isDone: " + isDone);
            }
        });

        image12.setBorderWidth(3);
        image12.setBorderColor(R.color.transparent20);
        image12.loadCircleImage(url1, R.color.placeholder_color);

        image13.setRadius(15);
        image13.setBorderWidth(3);
        image13.setBorderColor(R.color.blue);
        image13.setPressedAlpha(0.3f);
        image13.setPressedColor(R.color.blue);
        image13.loadImage(url1, R.color.placeholder_color);

        image14.setShapeType(ShapeImageView.ShapeType.CIRCLE);
        image14.setBorderWidth(3);
        image14.setBorderColor(R.color.orange);
        image14.setPressedAlpha(0.2f);
        image14.setPressedColor(R.color.orange);
        image14.loadImage(url1, R.color.placeholder_color);
    }

    private void line2() {
        image21.loadImage(url2, R.color.placeholder_color);
        image22.loadImage(url2, R.color.placeholder_color);
        image23.loadImage(url2, R.color.placeholder_color);
        image24.loadImage(url2, R.color.placeholder_color);
    }

    private void line3() {
        image31.loadLocalImage(R.drawable.gif_robot_walk, R.mipmap.ic_launcher);

        image32.loadCircleImage(gif1, R.mipmap.ic_launcher).listener(new OnGlideImageViewListener() {
            @Override
            public void onProgress(int percent, boolean isDone, GlideException exception) {
                Log.d("--->image32", "percent: " + percent + " isDone: " + isDone);
            }
        });

        image33.loadImage(gif2, R.mipmap.ic_launcher);
        image34.loadImage(gif3, R.mipmap.ic_launcher);
    }

    private void line41() {
        image41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImageViewActivity.this, ImageActivity.class);
                intent.putExtra(KEY_IMAGE_URL, cat);
                intent.putExtra(KEY_IMAGE_URL_THUMBNAIL, cat_thumbnail);
                ActivityOptionsCompat compat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(ImageViewActivity.this, image41, getString(R.string.transitional_image));
                ActivityCompat.startActivity(ImageViewActivity.this, intent, compat.toBundle());
            }
        });

        RequestOptions requestOptions = image41.requestOptions(R.color.placeholder_color).centerCrop();
        if (isLoadAgain) {
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true);
        }

        // 第一种方式加载
        image41.load(cat_thumbnail, requestOptions).listener(new OnGlideImageViewListener() {
            @Override
            public void onProgress(int percent, boolean isDone, GlideException exception) {
                if (exception != null && !TextUtils.isEmpty(exception.getMessage())) {
                    Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                }
                progressView1.setProgress(percent);
                progressView1.setVisibility(isDone ? View.GONE : View.VISIBLE);
            }
        });
    }

    private void line42() {
        image42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImageViewActivity.this, ImageActivity.class);
                intent.putExtra(KEY_IMAGE_URL, girl);
                intent.putExtra(KEY_IMAGE_URL_THUMBNAIL, girl_thumbnail);
                ActivityOptionsCompat compat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(ImageViewActivity.this, image42, getString(R.string.transitional_image));
                ActivityCompat.startActivity(ImageViewActivity.this, intent, compat.toBundle());
            }
        });

        RequestOptions requestOptions = image42.requestOptions(R.color.placeholder_color).centerCrop();
        if (isLoadAgain) {
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true);
        }

        // 第二种方式加载：可以解锁更多功能
        GlideImageLoader imageLoader = image42.getImageLoader();
        imageLoader.setOnGlideImageViewListener(girl_thumbnail, new OnGlideImageViewListener() {
            @Override
            public void onProgress(int percent, boolean isDone, GlideException exception) {
                if (exception != null && !TextUtils.isEmpty(exception.getMessage())) {
                    Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                }
                progressView2.setProgress(percent);
                progressView2.setVisibility(isDone ? View.GONE : View.VISIBLE);
            }
        });
        imageLoader.requestBuilder(girl_thumbnail, requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image42);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_menu_app:
                Toast.makeText(this, "关于", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(this, AboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
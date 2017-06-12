package com.example.myretrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.myretrofit.R;
import com.lilei.springactionmenu.ActionMenu;
import com.lilei.springactionmenu.OnActionItemClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ActionMenuActivity extends AppCompatActivity {

    @Bind(R.id.actionMenu)
    ActionMenu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_menu);
        ButterKnife.bind(this);
        System.out.println("****************"+'I' + 'T');
        // add menu items
        actionMenu.addView(R.drawable.search, getItemColor(R.color.menuNormalInfo), getItemColor(R.color.menuPressInfo));
        actionMenu.addView(R.drawable.like, getItemColor(R.color.menuNormalRed), getItemColor(R.color.menuPressRed));
        actionMenu.addView(R.drawable.write);

        actionMenu.setItemClickListener(new OnActionItemClickListener() {
            @Override
            public void onItemClick(int i) {
                switch (i) {
                    case 0:
                        showMessage("主菜单");
                        break;
                    case 1:
                        showMessage("菜单1");
                        break;
                    case 2:
                        showMessage("菜单2");
                        break;
                    case 3:
                        showMessage("菜单3");
                        break;
                }
            }

            @Override
            public void onAnimationEnd(boolean b) {
            }
        });
    }

    private int getItemColor(int colorID) {
        return getResources().getColor(colorID);
    }

    private void showMessage(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
}

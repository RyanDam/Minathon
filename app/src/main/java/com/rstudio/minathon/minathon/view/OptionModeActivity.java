package com.rstudio.minathon.minathon.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.rstudio.minathon.minathon.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OptionModeActivity extends AppCompatActivity {
    @BindView(R.id.btnSingleMode)
    Button btnSingleMode;
    @BindView(R.id.btnTeamMode)
    Button btnTeamMode;
    @BindView(R.id.fabMenu)
    FloatingActionMenu fabMenu;
    private List<FloatingActionMenu> menus = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_mode);
        ButterKnife.bind(this);
        setClickButton();
//        controlFAB();
    }

    private void setClickButton(){
        btnSingleMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnTeamMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showActivity(JoinTeamActivity.class);
            }
        });
    }

    private void showActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    private void controlFAB(){
        ContextThemeWrapper context = new ContextThemeWrapper(this, R.style.MenuButtonsStyle);
        FloatingActionButton programFab2 = new FloatingActionButton(context);
        programFab2.setLabelText("Programmatically added button");
        programFab2.setImageResource(R.drawable.ic_edit);
        fabMenu.addMenuButton(programFab2);
        fabMenu.hideMenuButton(false);

        menus.add(fabMenu);
        fabMenu.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                String text;
                if (opened) {
                    text = "Menu opened";
                } else {
                    text = "Menu closed";
                }
                Toast.makeText(OptionModeActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

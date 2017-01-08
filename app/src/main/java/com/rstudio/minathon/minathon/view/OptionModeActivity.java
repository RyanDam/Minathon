package com.rstudio.minathon.minathon.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.rstudio.minathon.minathon.R;
import com.rstudio.minathon.minathon.view.presenter.TopRecord;
import com.rstudio.minathon.minathon.view.presenter.TopRecordAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OptionModeActivity extends AppCompatActivity {
    @BindView(R.id.btnSingleMode)
    FloatingActionButton btnSingleMode;
    @BindView(R.id.btnTeamMode)
    FloatingActionButton btnTeamMode;
    @BindView(R.id.btnJoinTeam)
    FloatingActionButton btnJoinTeam;
    @BindView(R.id.fabMenu)
    FloatingActionMenu fabMenu;
    @BindView(R.id.imvHeader)
    ImageView imvHeader;
    @BindView(R.id.rcvTopRecord)
    RecyclerView rcvTopRecord;

    private List<FloatingActionMenu> menus = new ArrayList<>();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_mode);
        mContext = this;
        ButterKnife.bind(this);
        setClickButton();
//        controlFAB();
        Glide.with(OptionModeActivity.this)
                .load(R.drawable.boxing)
                .crossFade()
                .into(new GlideDrawableImageViewTarget(imvHeader));
        setRecyclerView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setRecyclerView(){
        List<TopRecord> topRecordList = new ArrayList<>();
        topRecordList.add(new TopRecord("Focus for 86m", "1/1/2017"));
        topRecordList.add(new TopRecord("Focus for 66m", "1/1/2017"));
        topRecordList.add(new TopRecord("Focus for 54m", "1/1/2017"));
        topRecordList.add(new TopRecord("Focus for 53m", "1/1/2017"));
        topRecordList.add(new TopRecord("Focus for 52m", "1/1/2017"));
        topRecordList.add(new TopRecord("Focus for 51m", "1/1/2017"));
        topRecordList.add(new TopRecord("Focus for 30m", "1/1/2017"));

        TopRecordAdapter mAdapter = new TopRecordAdapter(topRecordList, this);
        rcvTopRecord.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rcvTopRecord.setLayoutManager(mLayoutManager);
        rcvTopRecord.setItemAnimator(new DefaultItemAnimator());
        rcvTopRecord.setAdapter(mAdapter);
    }

    private void setClickButton() {
        btnSingleMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showActivity(CreateSingleActivity.class);
            }
        });

        btnTeamMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showActivity(CreateTeamActivity.class);
            }
        });

        btnJoinTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configDialog();
            }
        });
    }

    private void showActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    private void controlFAB() {
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

    private void configDialog() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(mContext, R.style.AlertDialogStyle);
        builder.setTitle("Join a group");
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_join_team, null);
        final EditText edtName = (EditText) dialogView.findViewById(R.id.edtDialogName);
        final EditText edtGroupId = (EditText) dialogView.findViewById(R.id.edtDialogGroupId);
        builder.setView(dialogView);

        builder.setPositiveButton("Join", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // TODO
                Toast.makeText(getApplication(), edtName.getText().toString() + " | " +
                        edtGroupId.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", null);


        builder.show();
    }
}

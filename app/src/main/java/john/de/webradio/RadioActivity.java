package john.de.webradio;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class RadioActivity extends AppCompatActivity {

    private ListView listView;
    private RecyclerView recyclerView;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_radio);
        setContentView(R.layout.activity_stream_recycler);

//        listView = findViewById(R.id.list_menu);

//        ListAdapter listAdapter = new ArrayAdapter<>(getApplicationContext(),
//                android.R.layout.simple_list_item_1, listOptions);

        // List View
        /*
        ListAdapter listAdapter = new RadioAdapter(getApplicationContext(), RadioStation.getListOptions());
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            showStreamActivity(position);
        });
        */

        // Recycler View
        recyclerView = findViewById(R.id.recycler_view_radio);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RadioRecyclerAdapter(getApplicationContext(), RadioStation.getRadioChannels()));
    }

    @Override
    protected void onDestroy() {
        if (mp != null && this.isFinishing())
            mp.release();

        super.onDestroy();
    }

    private void showStreamActivity(int position) {
        Intent intent = new Intent(getApplicationContext(), ChannelActivity.class);
        intent.putExtra("POSITION", position);
        startActivity(intent);
    }

    private void selectProgram(int position) {
        prepareMediaPlayer(RadioStation.getRadioSource(position));
    }

    private void prepareMediaPlayer(String src) {
        try {
            listView.setEnabled(false);

            if (mp == null)
                mp = new MediaPlayer();
            else
                mp.reset();

            mp.setAudioAttributes(new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            );
            mp.setDataSource(src);
            mp.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
            mp.setOnPreparedListener(mp -> {
                listView.setEnabled(true);
                mp.start();
            });
            mp.prepareAsync();

        } catch (Exception e) {
            listView.setEnabled(true);
            e.printStackTrace();
        }
    }
}

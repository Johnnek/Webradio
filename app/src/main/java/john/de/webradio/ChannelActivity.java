package john.de.webradio;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ChannelActivity extends AppCompatActivity {

    private MediaPlayer mp;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int position = getIntent().getIntExtra("POSITION", 0);

        prepareMediaPlayer(RadioStation.getRadioSource(position));

        this.setTitle(RadioStation.getRadioName(position));

//        WebView webView = findViewById(R.id.web_view);
        webView = new WebView(getApplicationContext());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(RadioStation.getRadioUrl(position));

        setContentView(webView);
//        setContentView(R.layout.activity_stream);
    }

    @Override
    protected void onDestroy() {
        if (mp != null && this.isFinishing())
            mp.release();

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack() && webView != null) webView.goBack();
        else super.onBackPressed(); }

    private void prepareMediaPlayer(String src) {
        try {
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
                mp.start();
            });
            mp.prepareAsync();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "MediaPlayer failed to start. Try again.", Toast.LENGTH_LONG);
            e.printStackTrace();
        }
    }
}
package john.de.webradio;

import java.util.ArrayList;
import java.util.Arrays;

public class RadioStation {

    private static ArrayList<RadioChannel> radioChannels = new ArrayList<>(Arrays.asList(
            new RadioChannel("sunshine:Live",
                    "http://sunshinelive.hoerradar.de/sunshinelive-live-mp3-hq",
                    "http://www.sunshine-live.de/",
                    R.drawable.live_foreground),
            new RadioChannel("sunshine:House",
                    "http://sunshinelive.hoerradar.de/sunshinelive-house-mp3-hq",
                    "http://www.sunshine-live.de/",
                    R.drawable.house_foreground),
            new RadioChannel("sunshine:EDM",
                    "http://sunshinelive.hoerradar.de/sunshinelive-edm-mp3-hq",
                    "http://www.sunshine-live.de/",
                    R.drawable.edm_foreground),
            new RadioChannel("sunshine:90er",
                    "http://sunshinelive.hoerradar.de/sunshinelive-90er-mp3-hq",
                    "http://www.sunshine-live.de/",
                    R.drawable.ninety_foreground)
    ));

    public static ArrayList<String> getListOptions() {
        ArrayList<String> options = new ArrayList<>();
        radioChannels.stream().forEach(r -> options.add(r.getName()));
        return options;
    }

    public static String getRadioName(int pos) {
        return radioChannels.get(pos).getName();
    }

    public static String getRadioSource(int pos) {
        return radioChannels.get(pos).getSrc();
    }

    public static String getRadioUrl(int pos) {
        return radioChannels.get(pos).getUrl();

    }

    public static int getRadioImage(int pos) {
        return radioChannels.get(pos).getImage();
    }

    public static ArrayList<RadioChannel> getRadioChannels() {
        return radioChannels;
    }
}
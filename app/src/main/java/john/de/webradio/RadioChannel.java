package john.de.webradio;

public class RadioChannel {

    private String name;
    private String src;
    private String url;
    private int image;

    public RadioChannel(String name, String src, String url, int resource) {
        this.name = name;
        this.src = src;
        this.url = url;
        this.image = resource;
    }

    public String getName() {
        return name;
    }

    public String getSrc() {
        return src;
    }

    public String getUrl() {
        return url;
    }

    public int getImage() {
        return image;
    }
}

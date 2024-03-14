import java.io.Serializable;
import java.util.Comparator;

public class Album implements Comparable<Album>, Serializable{
    private final String title;
    private final String artist;
    private int totalTracks;
    private final AlbumType genre;

    public Album(String title, String artist, int totalTracks, AlbumType genre){
        this.artist = artist;
        this.totalTracks = totalTracks;
        this.title = title;
        this.genre = genre;
    }

    public int getTotalTracks() {
        return totalTracks;
    }
    public String getArtist() {
        return artist;
    }
    public String getTitle() {
        return title;
    }
    public AlbumType getGenre() {
        return genre;
    }
    public void setTotalTracks(int totalTracks) {
        this.totalTracks = totalTracks;
    }

    @Override
    public int compareTo(Album otherAlbum){
        return title.compareToIgnoreCase(otherAlbum.getTitle());
    }

    @Override
    public String toString() {
        return ": Title: " + title + ", Artist: " + artist + ", Track total: " + totalTracks + ", Category: ";
    }
}

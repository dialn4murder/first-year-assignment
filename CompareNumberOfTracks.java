import java.util.Comparator;

public class CompareNumberOfTracks implements Comparator<Album> {
    @Override
    public int compare(Album trackNum1, Album trackNum2) {
        return trackNum2.getTotalTracks() - trackNum1.getTotalTracks();
    }
}

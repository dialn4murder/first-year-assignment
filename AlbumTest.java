import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlbumTest {

    @Test
    void getTotalTracks() {
        Album album = new Album("testTitle", "testArtist", 10, AlbumType.HIP_HOP);
        assertEquals(album.getTotalTracks(),10);
    }

    @Test
    void getArtist() {
        Album album = new Album("testTitle", "testArtist", 10, AlbumType.HIP_HOP);
        assertEquals(album.getArtist(),"testArtist");
    }

    @Test
    void getTitle() {
        Album album = new Album("testTitle", "testArtist", 10, AlbumType.HIP_HOP);
        assertEquals(album.getTitle(),"testTitle");
    }

    @Test
    void getGenre() {
        Album album = new Album("testTitle", "testArtist", 10, AlbumType.HIP_HOP);
        assertEquals(album.getGenre(),AlbumType.HIP_HOP);
    }

    @Test
    void setTotalTracks() {
        Album album = new Album("testTitle", "testArtist", 10, AlbumType.HIP_HOP);
        album.setTotalTracks(30);
        assertEquals(album.getTotalTracks(), 30);
    }

    @Test
    void Album(){
        Album album = new Album("testTitle", "testArtist", 10, AlbumType.HIP_HOP);
        assertEquals(album.getTitle(), "testTitle");
        assertEquals(album.getGenre(),AlbumType.HIP_HOP);
        assertEquals(album.getArtist(),"testArtist");
        assertEquals(album.getTotalTracks(),10);
    }
}
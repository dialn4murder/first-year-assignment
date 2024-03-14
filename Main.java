import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Sets main loop
        boolean loop = true;
        // List of albums
        ArrayList<Album> listOfAlbums = loadFromFile();

        while(loop){

            //Gets choice from menu
            int choice = menu();

            switch (choice){
                case 1:
                    // Add albums
                    Album album = addAlbum();
                    listOfAlbums.add(album);
                    break;

                case 2:
                    // View albums
                    // Checks if the album list is empty
                    if (!listOfAlbums.isEmpty()) {
                        viewAlbums(listOfAlbums);
                    }
                    break;

                case 3:
                    // Removes item
                    // Checks if list is empty
                    if (!listOfAlbums.isEmpty()) {
                        removeAlbum(listOfAlbums);
                    }
                    break;


                case 4:
                    // Go to sort selection
                    // Checks if list is empty
                    if (!listOfAlbums.isEmpty()) {
                        listOfAlbums = sortSelection(listOfAlbums);
                        viewAlbums(listOfAlbums);
                    }
                    break;

                case 5:
                    // Edit track number
                    viewAlbums(listOfAlbums);
                    // Checks if list is empty
                    if (!listOfAlbums.isEmpty()) {
                        int albumNumber = chooseAlbum(listOfAlbums);
                        listOfAlbums = editTrackNumber(listOfAlbums, albumNumber);
                    }
                    break;


                case 6:
                    // Removes all albums based on the artist
                    if (!listOfAlbums.isEmpty()) {
                        removeAll(listOfAlbums);
                    }
                    break;

                case 7:
                    if (!listOfAlbums.isEmpty()) {
                        getTotalAppearancesOfArtist(listOfAlbums);
                    }
                    break;

                case 8:
                    if (!listOfAlbums.isEmpty()) {
                        viewSpecificArtist(listOfAlbums);
                    }
                    break;

                case 9:
                    if (!listOfAlbums.isEmpty()) {
                        modeOfTrackNumber(listOfAlbums);
                    }
                    break;

                case 10:
                    // Exits the program and saves to file
                    saveToFile(listOfAlbums);
                    loop = false;
                    break;

                default:
                    break;
            }
        }

    }

    public static int menu(){

        // Gets the number for the switch statement
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice < 1 || choice > 10) {
            System.out.println("\n[1] Add a new album");
            System.out.println("[2] View existing albums");
            System.out.println("[3] Remove an existing album");
            System.out.println("[4] Sort by title or track number");
            System.out.println("[5] Edit track number");
            System.out.println("[6] Removes all based on artist");
            System.out.println("[7] Get total instances of specific artist");
            System.out.println("[8] Views all albums by the specified artist");
            System.out.println("[9] Finds mode of the track number");
            System.out.println("[10] Exits the program");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.nextLine();
            }
        }
        return choice;
    }

    public static int sortMenu(){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice < 1 || choice > 2) {
            System.out.println("\n[1] to sort by title");
            System.out.println("[2] to sort by track number");
            if (scanner.hasNextInt()){
                choice = scanner.nextInt();
            } else{
                scanner.nextLine();
            }
        }

        return choice;

    }

    public static String getCategory(AlbumType category){
        // Formats the enum based on the category to look nicer
        switch (category){
            case HIP_HOP -> {
                return "Hip Hop";
            }
            case POP -> {
                return "Pop";
            }
            default -> {
                return "Rock";
            }
        }
    }

    public static Album addAlbum(){
        Scanner scanner = new Scanner(System.in);

        // Gets the title of album
        System.out.println("\nInput album title: ");
        String albumTitle = scanner.nextLine();

        // Gets the artist of the album
        String artist = getArtist();

        // Gets track number
        int totalTracks = getTrackNumber();

        // Gets the category of album
        AlbumType type = null;
        // While there is no album category
        while(type == null) {
            // Asks for the choice of album type
            System.out.println("Input the genre of the album:\nPop 1: \nHip-Hop 2: \nRock 3: ");
            // Makes sure that the input is an int
            if (scanner.hasNextInt()) {
                // Gets the user choice, if its not in the range the loop will carry on
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        type = AlbumType.POP;
                        break;
                    case 2:
                        type = AlbumType.HIP_HOP;
                        break;
                    case 3:
                        type = AlbumType.ROCK;
                        break;
                    default:
                        break;
                }
            }
            else {
                // Allows the user to input again
                scanner.nextLine();
            }
        }

        // Returns an album class with the input values
        return new Album(albumTitle,artist,totalTracks,type);
    }

    public static void removeAlbum(ArrayList<Album> listOfAlbums){

        // Views all albums
        viewAlbums(listOfAlbums);
        // Gets user choice on which album to remove
        int choice = chooseAlbum(listOfAlbums);
        // Gets current based on the choice
        Album currentAlbum = listOfAlbums.get(choice);
        // Removes the album from the array list
        listOfAlbums.remove(choice);
        // Gives user validation
        System.out.println("\n" + currentAlbum.getTitle() + " has been removed.");

    }

    public static void viewAlbums(ArrayList<Album> listOfAlbums){
        // Counter gets the album number
        int counter = 0;
        // Iterates for each album saved
        for (Album currentAlbum : listOfAlbums) {
            // Formats the enum into something pretty
            String category = getCategory(currentAlbum.getGenre());
            // Adds to the album number
            counter++;
            // Prints out all information about the specific album nicely
            System.out.println("\n" + counter + (currentAlbum) + category);
        }
    }

    public static ArrayList<Album> sortSelection(ArrayList<Album> listOfAlbums){
        int choice = sortMenu();
        switch (choice){
            case 1:
                // Sort by title
                Collections.sort(listOfAlbums);
                return listOfAlbums;
            case 2:
                // Sort by track number
                CompareNumberOfTracks compareNumberOfTracks = new CompareNumberOfTracks();
                listOfAlbums.sort(compareNumberOfTracks);
                break;
            default:
                break;

        }
        return listOfAlbums;
    }

    public static int chooseAlbum(ArrayList<Album> listOfAlbums){
        // Makes scanner and initalizes choice
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        // Makes sure choice will be between 1 and the last index
        while (choice > listOfAlbums.size() || choice < 1){
            System.out.println("\nEnter an album number: ");
            // Checks if the user inputted an int
            if (scanner.hasNextInt()){
                choice = scanner.nextInt();
            }
            // Makes sure the user can input again
            else{
                scanner.nextLine();
            }
        }
        // Returns -1 since index starts from 0
        return choice-1;
    }

    public static ArrayList<Album> editTrackNumber(ArrayList<Album> listOfAlbums, int albumNumber) {
        // Gets new track number
        int trackNumber = getTrackNumber();
        // Sets new track number
        listOfAlbums.get(albumNumber).setTotalTracks(trackNumber);
        // Returns new list
        return listOfAlbums;
    }

    public static int getTrackNumber(){
        // Initializes track number and makes scanner
        Scanner scanner = new Scanner(System.in);
        int trackNumber = 0;

        // Makes sure that the number has to be more than 0
        while (trackNumber < 1){
            System.out.println("\nEnter total amount of tracks: ");
            // Checks if the user input an int
            if (scanner.hasNextInt()){
                trackNumber = scanner.nextInt();
            }
            else{
                scanner.nextLine();
            }
        }
        return trackNumber;
    }

    public static void saveToFile(ArrayList<Album> listOfAlbums){
        try{
            // Makes output stream
            FileOutputStream fileOutputStream = new FileOutputStream("save.dat");
            // Makes object output stream
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            // Writes the list to the file
            objectOutputStream.writeObject(listOfAlbums);
        }catch (FileNotFoundException e){
            System.out.println("\nFile not found.");
        }catch (IOException e){
            System.out.println("\nCant create object output stream.");
        }
    }

    public static ArrayList<Album> loadFromFile(){
        try{
            FileInputStream fileInputStream = new FileInputStream("save.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            // Loads the files contents into the array list
            ArrayList<Album> listOfAlbums = (ArrayList<Album>) objectInputStream.readObject();
            // Return content
            return listOfAlbums;
        }catch (FileNotFoundException e){
            System.out.println("\nFile not found.");
        }catch (IOException e){
            System.out.println("\nCant create object input stream.");
        } catch (ClassNotFoundException e) {
            System.out.println("\nClass not found");
        }
        // Returns empty list if there is no content to load into the array
        return new ArrayList<Album>();
    }

    public static void removeAll(ArrayList<Album> listOfAlbums){

        // Gets artist name from user
        String artist = getArtist();
        int counter = 0;

        // Creates an iterator
        Iterator<Album> albumIterator = listOfAlbums.iterator();
        // Loops while theres a next in the array
        while(albumIterator.hasNext()){
            // Gets the current album
            Album currentAlbum = albumIterator.next();
            // Compares the current albums artist to user input
            if (artist.equals(currentAlbum.getArtist())){
                counter++;
                // Removes the album if true
                albumIterator.remove();
                // User validation
                System.out.println("\n" + currentAlbum.getTitle() + " by " + currentAlbum.getArtist() + " has been removed.\n");
            }
        }
        // User validation to show there are no artists by the user input
        if (counter == 0){
            System.out.println("\nThere are no albums by " + artist);
        }
    }

    public static String getArtist(){
        // Gets user input for artist
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nInput artist name: ");
        return scanner.nextLine();
    }

    public static void getTotalAppearancesOfArtist(ArrayList<Album> listOfAlbums){
        // Defines number of times an artist will appear
        int totalOccurrences = 0;
        // Gets the users input for which artist to check
        String choice = getArtist();

        for (Album album : listOfAlbums){
            // Gets current artist from the list
            String currentArtist = album.getArtist();
            // If the artist is the same as the user choice
            if (currentArtist.equals(choice)){
                // Adds one to indicate that
                totalOccurrences++;
            }
        }
        // Prints result
        System.out.println("\n" + choice + " appeared " + totalOccurrences + " times.");
    }

    public static void viewSpecificArtist(ArrayList<Album> listOfAlbums){
        // Defines a counter to show how many artists there are and to show if there is no artists by that name
        int counter = 0;
        // Gets user input
        String choice = getArtist();
        for (Album album: listOfAlbums){
            // Gets current artist
             String currentArtist = album.getArtist();
             // Compares choice to iteration
             if (currentArtist.equals(choice)){
                 // Adds to the counter
                 counter++;
                 // Outputs the album by that artist
                 System.out.println("\n" + counter + " " + album.getTitle() + " by " + currentArtist);
             }
        }
        // Gives user validation if the artist does not exist
        if (counter == 0){
            System.out.println("\n"+choice + " does not exist.");
        }
    }

    public static void modeOfTrackNumber(ArrayList<Album> listOfAlbums){

        // Sorts the list
        CompareNumberOfTracks compareNumberOfTracks = new CompareNumberOfTracks();
        listOfAlbums.sort(compareNumberOfTracks);
        // Defines the greatest and comparison ints
        int greatest = 0;
        int comparison = 0;
        // Used to get the mode of tracks
        Album biggestAlbum = null;

        for (int i = 0; i < listOfAlbums.size(); i++) {
            // Gets current album
            Album currentAlbum = listOfAlbums.get(i);
            for (Album nextAlbum : listOfAlbums) {
                // Compares each next album with the current album
                if (currentAlbum.getTotalTracks() == nextAlbum.getTotalTracks()) {
                    // Adds to the comparison
                    comparison++;
                }
                // Makes the greatest actually the biggest and the biggest album updated
                if (comparison > greatest) {
                    greatest = comparison;
                    biggestAlbum = currentAlbum;
                }
                // If there is a different total track make the comparison 0
                if (currentAlbum.getTotalTracks() != nextAlbum.getTotalTracks()) {
                    comparison = 0;
                }
            }
        }
        // Returns the data
        System.out.println("\nMode of all tracks is " + biggestAlbum.getTotalTracks());

    }

}

package org.qmbupt.grp105.UI;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.VBox;
import javafx.scene.media.*;
import javafx.util.Duration;

import java.io.File;
import java.util.*;

public class MediaSceneGenerator {
    private static final String MUSIC_FOLDER = "./data";
    private static final String MUSIC_FILE_EXTENSION = "fitness.mp4";
    private static MediaView mediaViewcopy;
    public static Button playCopy;

    private final Label currentlyPlaying = new Label();
    private final ProgressBar progress = new ProgressBar();
    private ChangeListener<Duration> progressChangeListener;

    public Scene createScene() {
        final StackPane layout = new StackPane();

        String path = "";
        // determine the source directory for the playlist

        File directory = new File(MUSIC_FOLDER);
        String absPath = directory.getAbsolutePath();

        final File dir = new File(absPath);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Cannot find media source directory: " + dir);
            Platform.exit();
            return null;
        }

        // create some media players.
        final List<MediaPlayer> players = new ArrayList<>();
        players.add(
                createPlayer(
                        normalizeFileURL(dir, MUSIC_FILE_EXTENSION)
                ));

        // create a view to show the mediaplayers.
        final MediaView mediaView = new MediaView(players.get(0));

        mediaViewcopy = mediaView;


        final Button play = new Button("Play");
        playCopy = play;

        // allow the user to skip a track.

        // allow the user to play or pause a track.
        play.setOnAction(actionEvent -> {
            if(mediaView.getMediaPlayer().getStatus().toString().equals("PAUSED") ||  mediaView.getMediaPlayer().getStatus().toString().equals("READY"))
            {
                mediaView.getMediaPlayer().play();
            }
        });

        final Button pause = new Button("Pause");
        pause.setOnAction(actionEvent -> {
            if(mediaView.getMediaPlayer().getStatus().toString().equals("PLAYING"))
            {
                mediaView.getMediaPlayer().pause();
            }
        });

        // display the name of the currently playing track.
        mediaView.mediaPlayerProperty().addListener(
                (observableValue, oldPlayer, newPlayer) -> setCurrentlyPlaying(newPlayer)
        );




        // start playing the first track.
        mediaView.setMediaPlayer(players.get(0));
        mediaView.getMediaPlayer().play();
        setCurrentlyPlaying(mediaView.getMediaPlayer());
        mediaView.setFitHeight(1024);
        mediaView.setFitWidth(768);

        // silly invisible button used as a template to get the actual preferred size of the Pause button.
        Button invisiblePause = new Button("Pause");
        invisiblePause.setVisible(false);
        play.prefHeightProperty().bind(invisiblePause.heightProperty());
        play.prefWidthProperty().bind(invisiblePause.widthProperty());

        // layout the scene.
        HBox controls = new HBox(10, play, pause, progress);
        controls.setAlignment(Pos.CENTER_LEFT);
        VBox mediaPanel = new VBox(10, currentlyPlaying, mediaView, controls);

        layout.setStyle("-fx-background-color: cornsilk; -fx-font-size: 20; -fx-padding: 20; -fx-alignment: center;");
        layout.getChildren().addAll(
                invisiblePause,
                mediaPanel
        );
        progress.setMaxWidth(600);
        HBox.setHgrow(progress, Priority.ALWAYS);

        mediaView.getMediaPlayer().pause();
        play.setText("Play");
        return new Scene(layout);
    }

    /**
     * sets the currently playing label to the label of the new media player and updates the progress monitor.
     */
    private void setCurrentlyPlaying(final MediaPlayer newPlayer) {
        progress.setProgress(0);
        progressChangeListener = (observableValue, oldValue, newValue) ->
                progress.setProgress(
                        1.0 * newPlayer.getCurrentTime().toMillis() / newPlayer.getTotalDuration().toMillis()
                );
        newPlayer.currentTimeProperty().addListener(progressChangeListener);

    }

    /**
     * @return a MediaPlayer for the given source which will report any errors it encounters
     */
    private MediaPlayer createPlayer(String aMediaSrc) {

        final MediaPlayer player = new MediaPlayer(new Media(aMediaSrc));
        player.setOnError(() -> System.out.println("Media error occurred: " + player.getError()));
        return player;
    }

    public static void setSome()
    {
        playCopy.setText("Play");
    }

    private String normalizeFileURL(File dir, String file) {
        return "file:///" + (dir + "\\" + file).replace("\\", "/").replaceAll(" ", "%20");
    }

    private String getUserFriendlyMediaName(MediaPlayer newPlayer) {
        String source = newPlayer.getMedia().getSource();

        source = source.substring(0, source.length() - MUSIC_FILE_EXTENSION.length());
        source = source.substring(source.lastIndexOf("/") + 1).replaceAll("%20", " ");

        return source;
    }
    public static MediaView getVideoView()
    {
        return mediaViewcopy;
    }
}


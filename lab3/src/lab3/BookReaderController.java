package lab3;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import textproc.*;
public class BookReaderController extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setTitle("BookReader");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		
		while(scan.hasNext()) {
			String word = scan.next().toLowerCase();
			stopwords.add(word);
		}
		
		scan.close();
		GeneralWordCounter tp = new GeneralWordCounter(stopwords);
		
		while(s.hasNext()) {
			String word = s.next().toLowerCase();
			tp.process(word);
		}
		s.close();
		
		ObservableList<Map.Entry<String, Integer>> names = FXCollections.observableArrayList(tp.getWords());
		 ListView<Map.Entry<String, Integer>> listView = new ListView<Map.Entry<String, Integer>>(names);
		 root.setCenter(listView);
		 
		 HBox hbox = new HBox();
		 HBox topbox = new HBox();
		 Button alpha = new Button("Alphabetic");
		 Button freq = new Button ("Frequency");
		 Button find = new Button ("Find");
		 Button file = new Button ("Open file");
		 Label fileLabel = new Label();
		 //V.1 trigger find button with enter key
		 find.setDefaultButton(true);
		 TextField text = new TextField();
		 //V.2 Resize textfield
		 hbox.setHgrow(text, Priority.ALWAYS);
		 hbox.getChildren().add(alpha);
		 hbox.getChildren().add(freq);
		 hbox.getChildren().add(text);
		 hbox.getChildren().add(find);
		 topbox.getChildren().add(file);
		 topbox.getChildren().add(fileLabel);
		 
		 root.setBottom(hbox);
		 root.setTop(topbox);
		 
		 alpha.setOnAction((event) -> {
			 System.out.println("test alpha");
			 names.sort(new AlphaComparator());
		 });
		 
		 freq.setOnAction((event) -> {
			 System.out.println("test freq");
			 names.sort(new WordCountComparator());
		 });
		 
		 find.setOnAction((event) -> {
			 //System.out.println(text.getText());
			 // V3 remove whitespace
			 String search = text.getText().toLowerCase().replaceAll("\\s", "");
			 boolean found = false;
			 for (Map.Entry<String, Integer> entry : names) {
				String key = entry.getKey();
				//System.out.println(search + " key: " + key);
				if(search.compareTo(key) == 0) {
					listView.scrollTo(names.indexOf(entry));
					//V.3 Highlight searched row
					listView.getSelectionModel().select(names.indexOf(entry));
					//System.out.println("test");
					found = true;
				}
			}
			
			 //V.5 Word not found
			if(!found) {
				Alert alert = new Alert(AlertType.INFORMATION,"Ordet förekommer inte");
				alert.showAndWait();
			}
			
		 });
		 
		 //Select file ------Not fully implemented
		 file.setOnAction((event) -> {
			 FileChooser fileChooser = new FileChooser();
			 fileChooser.setTitle("Open Resource File");
			 fileChooser.getExtensionFilters().addAll(
			         new ExtensionFilter("Text Files", "*.txt"),
			         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
			         new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
			         new ExtensionFilter("All Files", "*.*"));
			 File selectedFile = fileChooser.showOpenDialog(primaryStage);
			 fileLabel.setText(selectedFile.getName());
//			 if (selectedFile != null) {
//			    mainStage.display(selectedFile);
//			 }
		 });

		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

}

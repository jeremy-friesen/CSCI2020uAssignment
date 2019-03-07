package sample;

import javafx.application.Application;

import java.io.File;
import java.lang.*;
import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import static java.lang.Character.toUpperCase;

public class Histogram extends Application {
	private char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	private String[] alphabetStr = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

	private int[] occurrences = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
			15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};

	private File file = new File("text.txt");

	@Override
	public void start(Stage primaryStage) throws Exception{
		//readFile();
		GridPane pane = new GridPane();
		HBox hbox = new HBox();

		//Bar Chart
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);

		XYChart.Series series = new XYChart.Series();
		for(int i = 0; i < 26; i++){
			series.getData().add(new XYChart.Data(alphabetStr[i], occurrences[i]));
		}

		bc.getData().add(series);
		pane.add(bc,0,0);

		TextField textField = new TextField();
		Button button = new Button("View");
		button.setOnAction(e -> {
			try {
				file = new File(textField.getText());
				readFile();
				XYChart.Series s = new XYChart.Series();
				for(int i = 0; i < 26; i++){
					s.getData().add(new XYChart.Data(alphabetStr[i], occurrences[i]));
				}
				bc.getData().add(s);
			} catch(Exception err){
				System.out.println(err.getMessage());
			}
		});

		hbox.getChildren().addAll(textField, button);
		pane.add(hbox, 0, 1);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();
		readFile();
	}

	public void readFile() throws Exception {
		System.out.println("read file");
		if(file.exists()) {
			occurrences = new int[26];
			Scanner input = new Scanner(file);
			input.useDelimiter("");

			while (input.hasNext()) {
				char c = toUpperCase(input.next().charAt(0));
				for(int i = 0; i < 26; i++){
					if(alphabet[i] == c){
						occurrences[i]++;
						break;
					}
				}
			}
			input.close();
		} else {
			System.out.println("Error: file \"" + file.getName() + "\" does not exist.");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}


package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.util.Random;

import static java.lang.Math.abs;


public class DisplayCards extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        int[] used = new int[2];
        Random rand = new Random();

        GridPane pane = new GridPane();

        for(int i = 0; i < 3; i++) {
            int cardNumber;
            do{
                cardNumber = abs(rand.nextInt()) % 54 + 1;
           }while(cardNumber == used[0] || cardNumber == used[1]);
            ImageView img = new ImageView("Cards/" + cardNumber + ".png");
            pane.add(img, i, 0);
            if(used[0] == 0){
                used[0] = cardNumber;
            }else{
                used[1] = cardNumber;
            }
        }

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

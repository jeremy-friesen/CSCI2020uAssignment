// Jeremy Friesen
// 100649797
// investmentCalculator
// This program calculates the future value of an investment
// using the original investment, the # of years passed, and the rate of return

package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class investmentCalculator extends Application{
    public TextField tfInvestment = new TextField();
    public TextField tfYears = new TextField();
    public TextField tfRate = new TextField();
    public TextField tfFutureValue = new TextField();

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        // add TextFields for user to input values
        pane.add(new Label("Investment Amount"), 0, 0);
        pane.add(tfInvestment, 1, 0);
        pane.add(new Label("Years"), 0, 1);
        pane.add(tfYears, 1, 1);
        pane.add(new Label("Annual Interest Rate"), 0, 2);
        pane.add(tfRate, 1, 2);
        pane.add(new Label("Future Value"), 0, 3);

        // add futureValue field
        tfFutureValue.setEditable(false);
        pane.add(tfFutureValue, 1, 3);

        // add "Calculate" button
        Button btRegister = new Button("Calculate");
        pane.add(btRegister, 1, 4);
        GridPane.setHalignment(btRegister, HPos.LEFT);

        // assign ButtonHandler to button
        ButtonHandler handler1 = new ButtonHandler();
        btRegister.setOnAction(handler1);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 360, 190);
        primaryStage.setTitle("Exercise16_5");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            // get the value of each input field
            double investmentAmount = Double.valueOf(tfInvestment.getText());
            double monthlyInterestRate = Double.valueOf(tfRate.getText()) / 12.0 / 100.0;
            double years = Double.valueOf(tfYears.getText());
            // calculate future value
            double futureValue = investmentAmount * Math.pow(1 + monthlyInterestRate, years * 12.0);
            // display future value
            tfFutureValue.setText(String.valueOf(Math.round(futureValue*100.0)/100.0));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

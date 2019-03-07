// Jeremy Friesen
// 100649797
// CircleTriangle
// This program displays 3 points on the perimeter of a circle, connected to form a triangle
// The user may click and drag to reposition these points, reforming the triangle

package sample;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;

import static java.lang.Math.*;

public class CircleTriangle extends Application{
	@Override
	public void start(Stage stage){
		Pane pane = new Pane();

		// Create circle
		Circle circle = new Circle();
		circle.setCenterX(120);
		circle.setCenterY(120);
		circle.setRadius(100);
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.WHITE);

		// Initialize triangle points
		Circle[] points = new Circle[3];
		for(int i = 0; i < 3; i++){
			points[i] = new Circle();
			points[i].setFill(Color.RED);
			points[i].setStroke(Color.BLACK);
			points[i].setRadius(5);
		}
		points[0].setCenterX(20);
		points[0].setCenterY(120);
		points[1].setCenterX(120);
		points[1].setCenterY(20);
		points[2].setCenterX(220);
		points[2].setCenterY(120);

		// Initialize triangle edges
		Line[] lines = new Line[3];
		lines[0] = new Line(points[0].getCenterX(), points[0].getCenterY(),
							points[1].getCenterX(), points[1].getCenterY());
		lines[1] = new Line(points[1].getCenterX(), points[1].getCenterY(),
							points[2].getCenterX(), points[2].getCenterY());
		lines[2] = new Line(points[2].getCenterX(), points[2].getCenterY(),
							points[0].getCenterX(), points[0].getCenterY());

		// Initialize triangle angle text
		Text[] angles = new Text[3];
		angles[0] = new Text(points[0].getCenterX() + 10, points[0].getCenterY() + 10, "");
		angles[1] = new Text(points[1].getCenterX() + 10, points[1].getCenterY() + 10, "");
		angles[2] = new Text(points[2].getCenterX() + 10, points[2].getCenterY() + 10, "");
		angles[0].setText(String.valueOf(calculateAngle(lines[0], lines[2], lines[1])));
		angles[1].setText(String.valueOf(calculateAngle(lines[1], lines[0], lines[2])));
		angles[2].setText(String.valueOf(calculateAngle(lines[2], lines[1], lines[0])));

		// When the user drags the mouse, update the point and set text for each angle
		points[0].setOnMouseDragged(e -> {
			updatePoint(points[0], circle, e, lines[0], lines[2], lines[1], angles[0]);
			angles[1].setText(String.valueOf(calculateAngle(lines[1], lines[0], lines[2])));
			angles[2].setText(String.valueOf(calculateAngle(lines[2], lines[1], lines[0])));
		});
		points[1].setOnMouseDragged(e -> {
			updatePoint(points[1], circle, e, lines[1], lines[0], lines[2], angles[1]);
			angles[0].setText(String.valueOf(calculateAngle(lines[0], lines[2], lines[1])));
			angles[2].setText(String.valueOf(calculateAngle(lines[2], lines[1], lines[0])));
		});
		points[2].setOnMouseDragged(e -> {
			updatePoint(points[2], circle, e, lines[2], lines[1], lines[0], angles[2]);
			angles[1].setText(String.valueOf(calculateAngle(lines[1], lines[0], lines[2])));
			angles[0].setText(String.valueOf(calculateAngle(lines[0], lines[2], lines[1])));
		});

		// Add all objects to pane
		pane.getChildren().addAll(circle, lines[0], lines[1], lines[2],
										points[0], points[1], points[2],
										angles[0], angles[1], angles[2]);
		Scene scene = new Scene(pane, 240, 240);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	// Bind point to the perimeter of the circle
	private void followCircle(Circle point, Circle circle, MouseEvent e){
		Point2D circleCenter = new Point2D(circle.getCenterX(), circle.getCenterY());
		Point2D mousePos = new Point2D(e.getX(), e.getY());
		Point2D newPoint = mousePos.subtract(circleCenter).normalize().multiply(circle.getRadius()).add(circleCenter);

		point.setCenterX(newPoint.getX());
		point.setCenterY(newPoint.getY());
	}

	// update the point location, angle text and angle text position
	private void updatePoint(Circle point, Circle circle, MouseEvent e, Line lineA, Line lineB, Line lineC, Text angle){
		followCircle(point, circle, e);
		lineA.setStartX(point.getCenterX());
		lineA.setStartY(point.getCenterY());
		lineB.setEndX(point.getCenterX());
		lineB.setEndY(point.getCenterY());

		if(point.getCenterX() < circle.getCenterX()){
			angle.setX(point.getCenterX() + 10);
		} else {
			angle.setX(point.getCenterX() - 10);
		}
		if(point.getCenterY() < circle.getCenterY()){
			angle.setY(point.getCenterY() + 10);
		} else {
			angle.setY(point.getCenterY() - 10);
		}

		angle.setText(String.valueOf(calculateAngle(lineA, lineB, lineC)));
	}

	// calculate the angle of the vertex
	private int calculateAngle(Line lineA, Line lineB, Line lineC){
		double a = lineLength(lineA);
		double b = lineLength(lineB);
		double c = lineLength(lineC);
		return (int)Math.round(Math.toDegrees(acos((c * c - b * b - a * a) / (-2 * b * a))));
	}

	// calculate the length of a given javafx.scene.shape.Line
	private double lineLength(Line line){
		double xDist = line.getStartX() - line.getEndX();
		double yDist = line.getStartY() - line.getEndY();
		return sqrt(xDist*xDist + yDist*yDist);
	}
}

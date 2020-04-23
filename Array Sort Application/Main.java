package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {
    private int[] array;
    private TextArea input;
    private TextField ascendingChoice;
    private TextArea output;

    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, 700, 300);
        GridPane pane = new GridPane();
        root.getChildren().add(pane);

        AnchorPane.setRightAnchor(pane, 8.0);
        AnchorPane.setLeftAnchor(pane, 8.0);
        AnchorPane.setTopAnchor(pane, 8.0);
        AnchorPane.setBottomAnchor(pane, 8.0);

        // first line
        Label inputArr = new Label("The unsorted array: ");
        inputArr.setPrefWidth(250);
        input = new TextArea();
        input.setPrefColumnCount(20);
        // second line
        Label ascending = new Label("Ascending order [Yy]? ");
        ascendingChoice = new TextField();
        ascendingChoice.setPrefColumnCount(20);
        // third line
        Label sortedArr = new Label("The sorted array: ");
        output = new TextArea();
        output.setEditable(false);      // text area should only display information
        // button sort and quit
        Button sortBtn = new Button("Sort");
        sortBtn.setPrefWidth(80);
        sortBtn.setOnAction(event -> onSort());
        Button quitBtn = new Button("Quit");
        quitBtn.setPrefWidth(80);
        quitBtn.setOnAction(event -> System.exit(0));
        // set up the grid pane
        pane.setHgap(0);
        pane.setVgap(40.0);
        pane.add(inputArr,0,0);
        pane.add(input,1,0);
        pane.add(ascending,0,1);
        pane.add(ascendingChoice,1,1);
        pane.add(sortedArr,0,2);
        pane.add(output,1,2);
        pane.add(sortBtn,0,4);
        pane.add(quitBtn,1,4);

        primaryStage.setTitle("Sort");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    private void onSort()
    {
        String [] stringArr = input.getText().split("\\s");
        array = new int[stringArr.length];
        for (int i = 0; i < stringArr.length ; i++) {
            try
            {
                array[i] = Integer.parseInt(stringArr[i]);
            }
            catch (NumberFormatException e)
            {
                System.err.println("Wrong input! Please enter numbers only!");
                System.exit(1);
            }
        }

        SortArray sortArray = new SortArray(array);

        if(ascendingChoice.getText().matches("[y|Y]"))
        {
            sortArray.sort(true);
        }
        else if( ascendingChoice.getText().matches("[n|N]"))
        {
            sortArray.sort(false);
        }
        else
        {
             System.err.println("Wrong input! Answers can be y,Y,n or N");
             System.exit(1);
        }

        StringBuilder out = new StringBuilder();
        out.append("[");
        for (int i = 0; i < array.length; i++) {
            out.append(array[i]);
            if(i < array.length-1)
            {
                out.append(", ");
            }
        }
        out.append("]");
        output.setText(out.toString());
    }

    public static void main(String[] args) { launch(args); }
}

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Let the user pick a file.
        FileChooser fileChooser = new FileChooser();
        File inputFile = fileChooser.showOpenDialog(primaryStage);

        // Scanner and String to store all file lines
        Scanner fileReader = new Scanner(inputFile);
        String allFileLines = "";

        while(fileReader.hasNext())
        {
             allFileLines = allFileLines + fileReader.nextLine();
        }

        // Close First Scanner
        fileReader.close();

        // List for ALL words
        List<String> tokenizedStringList = Arrays.asList(allFileLines.toLowerCase().split(" "));

        // Sort List
        Collections.sort(tokenizedStringList);
        System.out.println(tokenizedStringList); // Console Check = GOOD!

        // Set for the unique words
        Set<String> uniqueWordsSet = new TreeSet<>(tokenizedStringList);

        // Iterators
        Iterator<String> tokenizedStringListIterator = tokenizedStringList.iterator();
        Iterator<String> uniqueWordsSetIterator = uniqueWordsSet.iterator();

        // Map for unique word and how many time it appears
        Map<String, Integer> appearanceData = new HashMap<String, Integer>();
//        Iterator mapIterator = appearanceData.iterator();
        List<Integer> numberOfOccurences = new ArrayList<>();

        while(uniqueWordsSetIterator.hasNext())
        {
            String str = uniqueWordsSetIterator.next();
            int accumulator = 0;
            for (String s : tokenizedStringList)
            {
                if (str.equalsIgnoreCase(s))
                {
                    accumulator++;
                }
            }
            numberOfOccurences.add(accumulator);
            //appearanceData.put(str, accumulator);
        }


        // Main menu Top Description
        Text topDescription = new Text("Word Frequency Counter Software");
        topDescription.setFont(new Font("Verdana", 20));
        topDescription.setFill(Color.BLACK);

        // Labels & TextField controls
        Label thankYouLabel = new Label("Thank you for choosing your file.");



        // Button & Event Handling
        Button countButton = new Button("Count!");
        countButton.setOnAction(e->
        {
            try{
                File outputFile = new File("C:\\dev\\saveFilesHere\\WFQ-sorted.txt");
                FileWriter fw= new FileWriter(outputFile, true);
                PrintWriter pw = new PrintWriter(fw);

                pw.println("      FILE DATE      ");
                pw.println("---------------------");
                pw.println(uniqueWordsSet);
                pw.println(numberOfOccurences);
                pw.println(" ");
                pw.println(" ");
                pw.println("---------------------");

                pw.close();

                // Updated screen once complete
                Label completedLabel = new Label("Complete! You can close the program.");
                VBox completedVBox = new VBox(10, completedLabel);
                completedVBox.setAlignment(Pos.CENTER);
                completedVBox.setPadding(new Insets(10));
                Scene completedScene = new Scene(completedVBox, 500, 500);
                primaryStage.setScene(completedScene);

            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

        });

        // Main container
        VBox mainContainer = new VBox(10, topDescription, thankYouLabel, countButton);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(10));

        // Scene
        Scene scene = new Scene(mainContainer, 500, 500);

        // Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("WFS WINDOW");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

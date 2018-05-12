package yahtzee.ui;

import static javafx.geometry.Pos.CENTER;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.Scene;

import yahtzee.domain.YahtzeeFacade;

import yahtzee.ui.events.GameBoardEvent;
import static yahtzee.ui.events.GameBoardEvent.ROLL;

/**
 * This class defines the main window the players interact with.
 *
 * The objects of this class show a particular player a game board, and an
 * indication of which player's turn it is.
 */
public class GameBoard extends Stage {

    private static final double spacing = 5;

    private final YahtzeeFacade model;

    private Label diceLabel;
    private Label footer;
    private VBox mainPane;
    private HBox thrownDicePane;
    private HBox diceAsidePane;

    public GameBoard(YahtzeeFacade model) {
        super();

        this.model = model;
        this.setScene(createScene());
    }

    /**
     * Update the name displayed as current player.
     */
    public void updateCurrentPlayer() {
        footer.setText(model.getCurrentPlayerName() + " is playing.");
    }

    /**
     * Update the dice shown to the user.
     *
     * @param dice List of integers representing the values of the dice.
     */
    public void updateDice(List<Integer> dice) {
        diceLabel.setText(formatDiceString(dice));
    }

    private Scene createScene() {
        VBox rootPane = new VBox(spacing);
        rootPane.setAlignment(CENTER);

        rootPane.getChildren().add(createHeaderPane());
        rootPane.getChildren().add(createMainPane());
        rootPane.getChildren().add(createFooterPane(model.getCurrentPlayerName()));

        return new Scene(rootPane);
    }

    private Pane createHeaderPane() {
        HBox headerPane = new HBox(spacing);
        headerPane.setAlignment(CENTER);

        headerPane.getChildren().add(new Label("Yahtzee"));

        return headerPane;
    }

    private Pane createMainPane() {
        mainPane = new VBox(spacing);
        mainPane.setPrefSize(500, 500);

        diceLabel = new Label("You haven't rolled any dice yet.");
        mainPane.getChildren().add(diceLabel);
        Button roll = new Button("roll remaining dice.");

        roll.setOnAction(event -> this.fireEvent(new GameBoardEvent(event, this, ROLL)));

        mainPane.getChildren().add(roll);

        return mainPane;
    }

    private Pane createFooterPane(String firstPlayer) {
        HBox footerPane = new HBox(spacing);
        footerPane.setAlignment(CENTER);

        footer = new Label(firstPlayer + " may start the game.");
        footerPane.getChildren().add(footer);

        return footerPane;
    }

    private String formatDiceString(List<Integer> dice) {
        String str = " ";

        for (Integer die : dice) {
            str += die + " ";
        }

        return str;
    }

    /**
     Update method to refresh the main pane after each throw. This is to add new Lists & the disable option
     */

    public void updateView() {
        mainPane.getChildren().clear();
        thrownDicePane = new HBox();
        thrownDicePane.setSpacing(10);
        Label thrownDiceLabel = new Label("Thrown dice:");
        thrownDicePane.getChildren().add(thrownDiceLabel);
        mainPane.getChildren().add(thrownDicePane);
        Label chosenDiceLabel = new Label("Dice aside:");
        mainPane.getChildren().add(chosenDiceLabel);
        Button roll = new Button("roll remaining dice.");

        if(model.mayRoll()) {
            roll.setDisable(false);
        }
        else {
            roll.setDisable(true);
        }
        roll.setOnAction(event -> this.fireEvent(new GameBoardEvent(event, this, ROLL)));
        mainPane.getChildren().add(roll);
        drawDice("chosenDice");
        drawDice("thrownDice");

    }

    public void drawDice(String diceType) {

        List<Integer> dice = new ArrayList<>();
        if (diceType.equals("chosenDice")) {
            dice = model.getCurrentPlayer().getTurn().getDicesAside();
            System.out.println(dice);
        } else if (diceType.equals("thrownDice")) {
            dice = model.getCurrentPlayer().getTurn().getDicesThrown();
            System.out.println(dice);
        }

        for (int i = 0; i < dice.size(); i++) {
            int tileId = dice.get(i);
            Button dieButton = new Button(tileId+ "");
            dieButton.setPrefSize(20, 20);
            dieButton.setId("" + i + "");
// Ik denk dat hier ook zo'n fire event ga moeten komen @ Raf.
// had het even geprobeerd, maar kon er niet volledig aan uit
//            if (diceType.equals("thrownDice")) {
//                dieButton.setOnAction((event) -> model.chooseDice(Integer.parseInt(dieButton.getId())));
//            }

            thrownDicePane.getChildren().add(dieButton);
        }
    }
}
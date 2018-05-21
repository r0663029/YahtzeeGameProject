package yahtzee.ui;

import static javafx.collections.FXCollections.observableArrayList;
import javafx.event.Event;
import javafx.scene.layout.VBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;

import yahtzee.domain.YahtzeeFacade;

import yahtzee.ui.events.ChooseCategoryEvent;

public class ChooseCategoryPane extends VBox {
    private static final int spacing = 5;

    private final YahtzeeFacade model;

    private ComboBox<String> categoriesComboBox;
    private Button confirmCategoryButton;

    public ChooseCategoryPane(YahtzeeFacade model) {
	super(spacing);

	this.model = model;

	categoriesComboBox =
	    new ComboBox<>(observableArrayList(model.getCategoryListStrings()));
	categoriesComboBox.getSelectionModel().selectFirst();

	confirmCategoryButton = new Button("Confirm category");
	confirmCategoryButton.setOnAction(this::chooseCategoryHandler);
	confirmCategoryButton.setDisable(true);

	this.getChildren().add(categoriesComboBox);
	this.getChildren().add(confirmCategoryButton);
    }

    public void update() {
    }

    public void activate() {
	confirmCategoryButton.setDisable(false);
    }

    public void deactivate() {
	categoriesComboBox
	    .setItems(observableArrayList(model.getCategoryListStrings()));
	confirmCategoryButton.setDisable(true);
    }

    private void chooseCategoryHandler(Event event) {
	String selection = (String) categoriesComboBox.getValue();
	fireEvent(new ChooseCategoryEvent(selection));
    }
}

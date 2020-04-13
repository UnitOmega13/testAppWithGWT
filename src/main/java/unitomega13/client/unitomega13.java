package unitomega13.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class unitomega13 implements EntryPoint {

    private int numberOfElements;
    private Random random = new Random();
    private Grid grid;
    private boolean pushButton = false;
    private List<Integer> numbersList = new ArrayList<>();
    private VerticalPanel mainPanel = new VerticalPanel();
    private VerticalPanel buttonPanel = new VerticalPanel();
    private HorizontalPanel numbers = new HorizontalPanel();
    private TextBox newSymbolTextBox = new TextBox();
    private Button start = new Button("Enter");
    private Button sort = new Button("Sort");
    private Button reset = new Button("Reset");

    public void onModuleLoad() {
        mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        mainPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        buttonPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
        Label lbl = new Label("How many numbers to display?");
        lbl.getAutoHorizontalAlignment();
        mainPanel.add(lbl);
        mainPanel.add(newSymbolTextBox);
        mainPanel.add(start);
        start.addStyleName("gwt-Blue-Button");
        start.setSize("50px", "28px");
        sort.addStyleName("gwt-Blue-Button");
        sort.setSize("50px", "28px");
        reset.addStyleName("gwt-Blue-Button");
        reset.setSize("50px", "28px");

        RootPanel.get("frame1").add(mainPanel);
        RootPanel.get("frame1").add(mainPanel);
        newSymbolTextBox.setFocus(true);

        reset.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Reset();
            }
        });

        sort.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                pushButton = !pushButton;
                Sort();
            }
        });

        start.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                numberOfElements = Integer.parseInt(newSymbolTextBox.getText());
                if (numberOfElements <= 1000) {
                    RootPanel.get("frame1").remove(mainPanel);
                    numbers.add(grid = new Grid(10, (numberOfElements / 10) + 2));
                    buttonPanel.add(sort);
                    buttonPanel.add(reset);
                    numbers.add(buttonPanel);

                    RootPanel.get("frame1").add(numbers);
                    changeFrame(numberOfElements);
                }else if (numberOfElements <= 0 || numberOfElements > 1000){
                    Window.alert("Please input number from 1 to 1000");
                }
            }
        });
    }

    private void GenerateValues(int maxValue){
        int min = 1;
        int max = 30;
        int diff = max - min;
        numbersList.add(random.nextInt(diff + 1));
        for (int i = 0; i <= maxValue -2; i++) {
            numbersList.add(random.nextInt(1000));
        }
        pushButton = false;
    }

    private void changeFrame(int numberOfElements){
        grid.setWidth("280px");
        if (numbersList.isEmpty()){
            GenerateValues(numberOfElements);
        }
        int index;
        for (index = 0; index <= numberOfElements -1; index++){
            int columnNumber = (index / 10) + 1;
            int rowNumber;
            if (columnNumber > 0){
                rowNumber = index - ((columnNumber-1) * 10);
            }else {
                rowNumber = index;
            }
            int number = numbersList.get(index);
            Button button;
            grid.setWidget(rowNumber, columnNumber, button = new Button(String.valueOf(number),
                    (ClickHandler) event -> {
                if (number <= 30) {
                    grid.clear();
                    numbersList.clear();
                    GenerateValues(number);
                    changeFrame(number);
                } else {
                    Window.alert("Please select a value smaller or equal to 30.");
                }
            }));
            button.addStyleName("gwt-Green-Button");
            button.setSize("50px", "28px");
        }
    }

    private void Sort(){
        sort(numbersList,0,numbersList.size()-1);
        if (!pushButton){
            Collections.reverse(numbersList);
        }
        grid.clear();
        changeFrame(numbersList.size());
    }

    private void sort(List<Integer> list, int from, int to) {
        if (from < to) {
            int left = from + 1;
            int right = to;
            int pivotValue = list.get(from);
            while (left <= right) {
                while (left <= to && pivotValue >= list.get(left)) {
                    left++;
                }
                while (right > from && pivotValue < list.get(right)) {
                    right--;
                }
                if (left < right) {
                    Collections.swap(list, left, right);
                }
            }
            Collections.swap(list, from, left - 1);
            sort(list, from, right - 1);
            sort(list, right + 1, to);
        }
    }

    private void Reset(){
        newSymbolTextBox.setText("");
        RootPanel.get("frame1").remove(numbers);
        numbers.clear();
        numbersList.clear();
        RootPanel.get("frame1").add(mainPanel);
    }
}
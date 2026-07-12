package com.vilhag.javaui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;


public class CalcController {
    private String value = "";
    private double savedValue = 0.0;
    private String operator = "";

    @FXML
    private Label resultLabel;

    @FXML
    private void numberPressed(ActionEvent event) {
        Button button = (Button) event.getSource();
        if (button.getText().equals(".") && value.isEmpty() || value.length() > 14) {
            return;
        }
        value += button.getText();
        resultLabel.setText(value);
    }
    private void clear() {
        value = "";
        resultLabel.setText("");
    }
    @FXML
    private void fullClear() {
        value = "";
        resultLabel.setText("");
        operator = "";
        savedValue = 0.0;
    }
    @FXML
    private void invert(ActionEvent event) {
        if (value.contains("-")) {
            value = value.replace("-", "");
        } else {
            value = "-" + value;
        }
        resultLabel.setText(value);
    }
    public double getValue() {
        if (value.isEmpty() || value.equals("-")) {
            return 0;
        }
        try {
            return Double.parseDouble(value);
        }
        catch (NumberFormatException e) {
            throw new NumberFormatException("Parse error! " + value);
        }
    }
    @FXML
    private void squareRoot(ActionEvent event) {
        if (value.isEmpty()) {
            return;
        }
        if (value.contains("-")) {
            resultLabel.setText("Invalid");
            value = "";
        }
        double result = Math.sqrt(getValue());
        value = (result % 1 == 0) ? String.valueOf((long)result) : String.valueOf(result);
        resultLabel.setText(value);
    }
    @FXML
    private void operator(ActionEvent event) {
        Button button = (Button) event.getSource();
        if (value.isEmpty()) {
            return;
        }
        savedValue = getValue();
        operator = button.getText();
        System.out.println(operator);
        clear();
    }
    @FXML
    private void equals(ActionEvent event) {
        double currentValue = getValue();
        double result = switch (operator) {
            case "+" -> savedValue + currentValue;
            case "-" -> savedValue - currentValue;
            case "×" -> savedValue * currentValue;
            case "÷" -> savedValue / currentValue;
            default -> currentValue;
        };

        System.out.println(savedValue);
        System.out.println(getValue());
        System.out.println(result);
        System.out.println(value);

        value = (result % 1 == 0) ? String.valueOf((long)result) : String.valueOf(result);
        resultLabel.setText(value);
        operator = "";
        savedValue = 0.0;
    }
    @FXML
    private void backspace() {
        if (!value.isEmpty()) {
            value = value.substring(0, value.length() - 1);
            resultLabel.setText(value);
        }
    }
}

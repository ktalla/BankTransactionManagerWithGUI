package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HelloController {
    @FXML
    private RadioButton radioButton1;

    @FXML
    private RadioButton radioButton2;

    @FXML
    private RadioButton radioButton3;

    @FXML
    private RadioButton radioButton4;

    @FXML
    private RadioButton radioButton11;

    @FXML
    private RadioButton radioButton22;

    @FXML
    private RadioButton radioButton33;

    @FXML
    private RadioButton radioButton44;

    @FXML
    private RadioButton radioButton111;

    @FXML
    private RadioButton radioButton222;

    @FXML
    private RadioButton radioButton333;

    @FXML
    private RadioButton radioButton444;

    @FXML
    private RadioButton nb;

    @FXML
    private RadioButton camden;

    @FXML
    private RadioButton newark;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField fname1;

    @FXML
    private TextField lname1;

    @FXML
    private DatePicker dob1;

    @FXML
    private TextField fname2;

    @FXML
    private TextField lname2;

    @FXML
    private DatePicker dob2;
    @FXML
    private ToggleGroup toggleGroup1;
    @FXML
    private ToggleGroup toggleGroup2;

    @FXML
    private VBox secondButtonBox;

    @FXML
    private CheckBox thirdButtonBox;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private ToggleGroup campusGroup;
    @FXML
    private TextArea outputArea;

    @FXML
    private TextArea outputArea2;

    @FXML
    private TextArea outputArea3;

    @FXML
    private TextArea outputArea4;

    @FXML
    private TextField initialDeposit;

    @FXML
    private TextField amount;
    private AccountDatabase accountDatabase;

    @FXML
    public void initialize() {
        accountDatabase = new AccountDatabase();
        toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(this.toggleGroup);
        radioButton2.setToggleGroup(this.toggleGroup);
        radioButton3.setToggleGroup(this.toggleGroup);
        radioButton4.setToggleGroup(this.toggleGroup);

        campusGroup = new ToggleGroup();
        camden.setToggleGroup(campusGroup);
        nb.setToggleGroup(campusGroup);
        newark.setToggleGroup(campusGroup);

        toggleGroup1 = new ToggleGroup();
        radioButton11.setToggleGroup(toggleGroup1);
        radioButton22.setToggleGroup(toggleGroup1);
        radioButton33.setToggleGroup(toggleGroup1);
        radioButton44.setToggleGroup(toggleGroup1);

        toggleGroup2 = new ToggleGroup();
        radioButton111.setToggleGroup(toggleGroup2);
        radioButton222.setToggleGroup(toggleGroup2);
        radioButton333.setToggleGroup(toggleGroup2);
        radioButton444.setToggleGroup(toggleGroup2);
    }

    @FXML
    public void handleRadioButtonSelection(ActionEvent event) {
        if (toggleGroup.getSelectedToggle() == null || toggleGroup.getSelectedToggle() == radioButton1 || toggleGroup.getSelectedToggle() == radioButton4) {
            // No radio button is selected, radiobutton1 is selected, or radiobutton4 is selected, hide both button boxes
            secondButtonBox.setDisable(true);
            secondButtonBox.setManaged(false);
            thirdButtonBox.setDisable(true);
            thirdButtonBox.setManaged(false);
        } else if (toggleGroup.getSelectedToggle() == radioButton2) {
            // radioButton2 is selected, show secondButtonBox, hide thirdButtonBox
            secondButtonBox.setDisable(false);
            secondButtonBox.setManaged(true);
            thirdButtonBox.setDisable(true);
            thirdButtonBox.setManaged(false);
        } else if (toggleGroup.getSelectedToggle() == radioButton3) {
            // radioButton3 is selected, hide secondButtonBox, show thirdButtonBox
            secondButtonBox.setDisable(true);
            secondButtonBox.setManaged(false);
            thirdButtonBox.setDisable(false);
            thirdButtonBox.setManaged(true);
        }
    }

    @FXML
    public void handlePrintAccountsClick(ActionEvent event){
        accountDatabase.printSorted();
    }

    @FXML
    public void handleInterestAndFeesClick(ActionEvent event){
        accountDatabase.printFeesAndInterests();
    }

    @FXML
    public void handleUpdateAccountsClick(ActionEvent event){
        accountDatabase.printUpdatedBalances();
    }

    @FXML
    public void handleCloseClick(ActionEvent event){
        if(fname1.getText().isEmpty() || lname1.getText().isEmpty() || dob1.toString().isEmpty() || toggleGroup1.getSelectedToggle() ==null){
            createAlert("Missing Data", "Missing data for closing an account.", "Please enter missing data required to close an account.");
        }
        else {
            RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
            String accountType = selectedRadioButton.getText();
            String accountTypeCode;
            switch(accountType) {
                case "Checking":
                    accountTypeCode = "C";
                    break;
                case "Savings":
                    accountTypeCode = "S";
                    break;
                case "College Checking":
                    accountTypeCode = "CC";
                    break;
                case "Money Market":
                    accountTypeCode = "MM";
                    break;
                default:
                    accountTypeCode = "";
            }
            String [] inputData = new String[] {accountTypeCode, fname1.getText(), lname1.getText(), Date.fromStringUI(dob1.getValue().toString())};
            String error = handleCommandC(inputData);
            if(!error.isEmpty()) {
                createAlert("Closing Account Error", "There was an error while closing your account.", error);
            }
        }
    }

    @FXML
    public void handleClearClick(ActionEvent event){
        fname.clear();
        lname.clear();
        dob.setValue(null);
        initialDeposit.clear();
        toggleGroup.selectToggle(null);
        campusGroup.selectToggle(null);
        secondButtonBox.setDisable(true);
        thirdButtonBox.setSelected(false);
        thirdButtonBox.setDisable(true);
    }
    @FXML
    public void handleClearClick2(ActionEvent event){
        fname1.clear();
        lname1.clear();
        dob1.setValue(null);
        toggleGroup1.selectToggle(null);
    }

    @FXML
    public void handleClearClick3(ActionEvent event){
        fname2.clear();
        lname2.clear();
        dob2.setValue(null);
        amount.clear();
        toggleGroup2.selectToggle(null);
    }

    private void createAlert(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    private void handleOpenClick(ActionEvent event){
        if(fname.getText().isEmpty() || lname.getText().isEmpty() || dob.toString().isEmpty() || toggleGroup.getSelectedToggle() ==null || initialDeposit.getText().isEmpty()){
            createAlert("Missing Data", "Missing data for opening an account.", "Please enter missing data required to open an account.");
        }
        else{
            RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
            String accountType = selectedRadioButton.getText();
            System.out.println(accountType);
                String accountTypeCode;
                switch(accountType) {
                    case "Checking":
                        accountTypeCode = "C";
                        break;
                    case "Savings":
                        accountTypeCode = "S";
                        break;
                    case "College Checking":
                        accountTypeCode = "CC";
                        break;
                    case "Money Market":
                        accountTypeCode = "MM";
                        break;
                    default:
                        accountTypeCode = "";
                }
                String [] inputData = new String[] {accountTypeCode, fname.getText(), lname.getText(), Date.fromStringUI(dob.getValue().toString()), initialDeposit.getText(), ""};
                if(accountTypeCode.equals("CC")){
                    if(campusGroup.getSelectedToggle()!=null) {
                        String campus = ((RadioButton) campusGroup.getSelectedToggle()).getText();
                        inputData[5] = String.valueOf(Campus.fromString(campus).getCode());
                    }
                    else{
                        createAlert("Missing Data", "Missing data for opening an account.", "Please enter missing data required to open an account.");
                        return;
                    }
                }
                 if(accountTypeCode.equals("S")) {
                     if (thirdButtonBox.isSelected())
                         inputData[5] = "1";
                     else
                         inputData[5] = "0";
                 }
                String error = handleCommandO(inputData);
                if(!error.isEmpty()) {
                    createAlert("Opening Account Error","There was an error while creating your account.", error);
                }
            }
        }
        @FXML
        private void handleDepositorWithdrawClick(ActionEvent event) {
            if (fname2.getText().isEmpty() || lname2.getText().isEmpty() || dob2.toString().isEmpty() || toggleGroup2.getSelectedToggle() == null || amount.getText().isEmpty()) {
                createAlert("Missing Data", "Missing data for account deposit or withdrawal.", "Please enter missing data required to deposit into or withdraw from an account.");
            } else {
                RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
                String accountType = selectedRadioButton.getText();
                String accountTypeCode;
                switch (accountType) {
                    case "Checking":
                        accountTypeCode = "C";
                        break;
                    case "Savings":
                        accountTypeCode = "S";
                        break;
                    case "College Checking":
                        accountTypeCode = "CC";
                        break;
                    case "Money Market":
                        accountTypeCode = "MM";
                        break;
                    default:
                        accountTypeCode = "";
                }
                String [] inputData = new String[] {accountTypeCode, fname2.getText(), lname2.getText(), Date.fromStringUI(dob2.getValue().toString()), amount.getText()};

                if (((Button)event.getSource()).getText().equals("Deposit")){
                    String error = handleCommandD(inputData);
                    if(!error.isEmpty()) {
                        createAlert("Account Deposit Error", "There was an error while depositing into your account.", error);
                    }
                }
                else{
                    String error = handleCommandW(inputData);
                    if(!error.isEmpty()) {
                        createAlert("Account Withdraw Error", "There was an error while withdrawing from your account.", error);
                    }
                }
            }
        }

    private String inputCheckForO(String[] inputData) {
        try {
            if ((inputData[0].equals("CC") || inputData[0].equals("S")) && inputData.length < 6) { //CollegeChecking and Savings requires 7 pieces of information
                return "Missing Data for opening an account.";
            } else if (inputData.length < 5) {
                return "Missing Data for opening an account."; //other accounts require 6 pieces of information
            } else {
                String accountType = inputData[0];
                Date date = Date.fromString(inputData[3]);
                double balance = Double.parseDouble(inputData[4]); //check if 0 or negative
                if (balance <= 0) {
                    return "Initial deposit cannot be 0 or negative.";
                }
                //System.out.println(accountType); //why is this not printing?
                if (!date.isValid()) {
                    return "DOB invalid: " + date + " not a valid calendar date!";
                }
                if (!date.isPresentorFutureDate()) {
                    return "DOB invalid: " + date + " cannot be today or a future day.";
                }
                if (date.getAge() < 16) {
                    return "DOB invalid: " + date + " under 16.";
                }
                if (accountType.equals("CC") && (Integer.parseInt(inputData[5]) < 0 || Integer.parseInt(inputData[5]) > 2))
                    return "Invalid campus code.";
                if (accountType.equals("S") && (Integer.parseInt(inputData[5]) < 0 || Integer.parseInt(inputData[5]) > 1))
                    return "Invalid loyalty code.";
                if(accountType.equals("MM") && balance<2000)
                    return "Minimum of $2000 to open a Money Market account.";
                if(accountType.equals("CC") && date.getAge()<24)
                    return "DOB invalid: " + date + " over 24.";
                return "";
            }
        } catch (NumberFormatException e) {
            return "Not a valid amount.";
        }
    }


    private String openCheckingAccount(Profile profile, double balance) {
        Checking checkingAcc = new Checking(profile, balance);
        Account account = createDummyAccount("CC", profile, 0);
        if (!(accountDatabase.contains(account)) && accountDatabase.open(checkingAcc)) {
            return profile.toString() + "(C) opened.";
        } else {
            return profile.toString() + "(C) is already in the database.";
        }
    }

    private String openCCAccount(Profile profile, double balance, int campusCode, Date dob) {
        if (dob.getAge() < 24) {
            Campus campus = Campus.fromCode(campusCode);
            CollegeChecking ccAccount = new CollegeChecking(profile, balance, campus);
            Account account = createDummyAccount("C", profile, 0);
            if (!(accountDatabase.contains(account)) && accountDatabase.open(ccAccount)) {
                return profile.toString() + "(CC) opened.";
            } else {
                return profile.toString() + "(CC) is already in the database."; //already is a c acc and ur trynna make a cc
            }
        } else {
            return "DOB invalid: " + dob + " over 24.";
        }
    }

    private String openSavingsAccount(Profile profile, double balance, int loyaltyCode) {
        boolean loyalty; //try catch here
        if (loyaltyCode == 1) {
            loyalty = true;
        } else {
            loyalty = false;
        }
        Savings savingsAcc = new Savings(profile, balance, loyalty);
        if (accountDatabase.open(savingsAcc)) {
            return profile.toString() + "(S) opened.";
        } else {
            return profile.toString() + "(S) is already in the database.";
        }
    }

    private String openMMAccount(Profile profile, double balance) {
        if (balance >= 2000) {
            MoneyMarket moneyMarket = new MoneyMarket(profile, balance, 0);
            if (accountDatabase.open(moneyMarket)) {
                return profile.toString() + "(MM) opened.";
            } else {
                return profile.toString() + "(MM) is already in the database.";
            }
        } else {
            return "Minimum of $2000 to open a Money Market account.";
        }
    }

    private String handleCommandO(String[] inputData) {
        String error = inputCheckForO(inputData);
        if (error.isEmpty()) {
            String accountType = inputData[0];
            Date dob = Date.fromString(inputData[3]);
            double balance = Double.parseDouble(inputData[4]);
            Profile profile = new Profile(inputData[1], inputData[2], dob);
            switch (accountType) {
                case "C":
                    outputArea.appendText(openCheckingAccount(profile, balance)+ "\n");
                    break;
                case "CC":
                    int campusCode = Integer.parseInt(inputData[5]);
                    outputArea.appendText(openCCAccount(profile, balance, campusCode, dob)+ "\n");
                    break;
                case "S":
                    int loyaltyCode = Integer.parseInt(inputData[5]);
                    outputArea.appendText(openSavingsAccount(profile, balance, loyaltyCode)+ "\n");
                    break;
                case "MM":
                    outputArea.appendText(openMMAccount(profile, balance)+ "\n");
                    break;
            }
        }
        return error;
    }

    private String handleCommandW(String[] inputData) {
        String error = inputCheckForW(inputData);
        if(error.isEmpty()) {
            String accountType = inputData[0];
            Date dob = Date.fromString(inputData[3]);
            Profile profile = new Profile(inputData[1], inputData[2], dob);
            double withdrawalAmount = Double.parseDouble(inputData[4]);
            Account account = createDummyAccount(accountType, profile, withdrawalAmount);
            if (accountDatabase.contains(account)) {
                double currentBalance = accountDatabase.getBalance(account); // Assuming you have a method to get the account balance
                if (withdrawalAmount > currentBalance) {
                    outputArea3.appendText(profile.toString() + "(" + accountType + ") Withdraw - insufficient funds.");
                } else {
                    accountDatabase.withdraw(account);
                    outputArea3.appendText(profile.toString() + "(" + accountType + ") Withdraw - balance updated.");
                }
            } else {
                outputArea3.appendText(profile.toString() + "(" + accountType + ") is not in the database.");
            }
        }
        return error;
    }

    private Account createDummyAccount(String accountType, Profile profile, double amount) {
        switch (accountType) {
            case "C":
                return new Checking(profile, amount);
            case "CC":
                return new CollegeChecking(profile, amount, null);
            case "S":
                return new Savings(profile, amount, true);
            case "MM":
                return new MoneyMarket(profile, amount, 0);
            default:
                return null;
        }
    }

    private String inputCheckForD(String[] inputData){
        try {
            if (inputData.length < 4) {
                return "Missing data for depositing into account.";
            }
            double amount = Double.parseDouble(inputData[5]);
            if (amount <= 0) {
                return "Deposit - amount cannot be 0 or negative.";
            }
        }
        catch(NumberFormatException e){
            return "Not a valid amount.";
        }
        return "";
    }

    private String inputCheckForW(String[] inputData){
        try {
            if (inputData.length < 4) {
                return "Missing data for withdrawing from an account.";
            }
            double amount = Double.parseDouble(inputData[5]);
            if (amount <= 0) {
                return "Withdraw - amount cannot be 0 or negative.";
            }
        }
        catch(NumberFormatException e){
            return "Not a valid amount.";
        }
        return "";
    }
    private String handleCommandD(String[] inputData) {
        String error = inputCheckForD(inputData);
        if(error.isEmpty()){
            String accountType = inputData[1];
            Date dob = Date.fromString(inputData[4]);
            Profile profile = new Profile(inputData[2], inputData[3], dob);
            double amount = Double.parseDouble(inputData[5]);
            Account account = createDummyAccount(accountType, profile, amount);
            if (accountDatabase.contains(account)) {
                accountDatabase.deposit(account);
                outputArea3.appendText(profile.toString()+"(" + accountType + ") Deposit - balance updated.");
            } else {
                outputArea3.appendText(profile.toString() + "(" + accountType + ") is not in the database.");
            }
        }
        return error;
        }

    private String inputCheckForC(String [] inputData){
            if (inputData.length < 4) {
                return ("Missing data for closing an account.");
            }
            Date dob = Date.fromString(inputData[3]);

            if (!dob.isValid()) {
                return "DOB invalid: " + dob + " not a valid calendar date!";
            }
            if (!dob.isPresentorFutureDate()) {
                return "DOB invalid: " + dob + " cannot be today or a future day.";
            }
            if (dob.getAge() < 16) {
                return "DOB invalid: " + dob + " under 16.";
            }
            return "";
    }

    private String handleCommandC(String[] inputData) {
        String error = inputCheckForC(inputData);
        if(error.isEmpty())
        {
            String accountType = inputData[0];
            String firstName = inputData[1];
            String lastName = inputData[2];
            Date dob = Date.fromString(inputData[3]);
            Profile profile = new Profile(firstName, lastName, dob);
            // Find the account in the database
            Account account = createDummyAccount(accountType, profile, 0);
            if (accountDatabase.close(account)) {
                 outputArea2.appendText(profile.toString() + " (" + accountType + ") has been closed. \n");
            }
            else
                outputArea2.appendText(profile.toString() + " (" + accountType + ") not in the database. \n");
        }
        return error;
    }

    @FXML
    void importFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Source File for the Import");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File sourceFile = chooser.showOpenDialog(stage); //get the reference of the source file
        try {
            Scanner scanner = new Scanner(sourceFile);
            outputArea4.appendText("Accounts loaded");
            while(scanner.hasNextLine()){
                String input = scanner.nextLine();
                String[] inputData = input.split("\\s+");
                handleCommandO(inputData);
            }
        }
        catch(FileNotFoundException e){
            createAlert("File Not Found","File not found for opening an account.", "Please select the correct file." );
        }
    }
}




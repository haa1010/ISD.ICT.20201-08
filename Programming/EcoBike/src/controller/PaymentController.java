package controller;

import java.io.IOException;
import java.sql.SQLException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.Hashtable;
import java.util.Map;

import updateDB.RentBike;
import updateDB.ReturnBike;
import common.exception.InvalidCardException;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.invoice.Invoice;
import entity.transaction.Card;
import entity.transaction.TransactionInfo;
import javafx.stage.Stage;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;


/**
 * This class controls the flow of the payment process in our EcoBikeRental project
 *
 * @author Tran Thi Hang, Duong Thi Hue,Pham Nhat Linh
 * @version 1.0
 * <p>
 * created_at: 01/12/2020
 * <p>
 * project_name: EcoBike Rental (EBR)
 * <p>
 * teacher_name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class_name: TT.CNTT ICT 02 K62
 * <p>
 * helpers: Teaching Assistants and other team members
 */

public class PaymentController extends TransactionController {

    /**
     * Represent the card used for payment
     */
    private Card card;

    /**
     * Represent the Interbank subsystem
     */
    private InterbankInterface interbank;
    private double amount;
    private String content;

    /**
     * Validate the input date which should be in the format "mm/yy", and then
     * return a {@link java.lang.String String} representing the date in the
     * required format "mmyy" .
     *
     * @param date - the {@link java.lang.String String} represents the input date
     * @return {@link java.lang.String String} - date representation of the required
     * format
     * @throws InvalidCardException - if the string does not represent a valid date
     *                              in the expected format
     */


    /**
     * Pay order, and then return the result with a message.
     *
     * @param amount         - the amount to pay
     * @param contents       - the transaction contents
     * @param cardNumber     - the card number
     * @param cardHolderName - the card holder name
     * @param expirationDate - the expiration date in the format "mm/yy"
     * @param securityCode   - the cvv/cvc code of the credit card
     * @return {@link java.util.Map Map} represent the payment result with a
     * message.
     */
    public Map<String, String> payOrder(int amount, String contents, String cardNumber, String cardHolderName,
                                        String expirationDate, String securityCode) {
        Map<String, String> result = new Hashtable<String, String>();
        result.put("RESULT", "PAYMENT FAILED!");
        try {
            this.card = new Card(cardNumber, cardHolderName, securityCode,
                    expirationDate);

            this.interbank = new InterbankSubsystem();
            TransactionInfo transaction = interbank.payOrder(card, amount, contents);

            result.put("RESULT", "PAYMENT SUCCESSFUL!");
            result.put("MESSAGE", "You have succesffully paid the order!");
        } catch (PaymentException | UnrecognizedException ex) {
            result.put("MESSAGE", ex.getMessage());
        }
        return result;
    }

    /**
     * This method validates Cardholder's name
     *
     * @param name
     * @return boolean
     */

    public boolean validateName(String name) {
        try {
            name = name.trim();
            return ((!name.equals("")) && (name.matches("^[ A-Za-z0-9]+$")));
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * This method validate Security Code/cvvCode
     *
     * @param number
     * @return boolean
     */
    public boolean validateNumberField(String number) {

        try {
            number = number.trim();
            Integer.parseInt(number);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }


    public boolean validateExpirationDate(String date) throws InvalidCardException {
        try {
            if (date == null) return false;
            date = date.trim();
            String regex = "^[0-9]{4}$";
            if (!date.matches(regex)) return false;
            String cardMonth = date.substring(0, 2);
            String cardYear = "20" + date.substring(2);
            Date today = new Date();
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
            cal.setTime(today);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            return Integer.parseInt(cardYear) > year || (Integer.parseInt(cardYear) == year && Integer.parseInt(cardMonth) >= month);
        } catch (Exception e) {
            throw new InvalidCardException("Invalid expiration date");
        }
    }

    /**
     * This method validate cardCode
     *
     * @param number
     * @return boolean
     */
    public boolean validateCardCode(String number) {
        try {
            number = number.trim();
            return ((!number.equals("")) && (number.matches("^[_0-9A-Za-z]*$")));
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    /**
     * validate card
     *
     * @param cardNumber
     * @param holderName
     * @param securityCode
     * @param expirationDate
     * @throws Exception
     */
    public void validateCardInfo(String cardNumber, String holderName, String securityCode, String expirationDate) throws Exception {
        if (!validateExpirationDate(expirationDate)) throw new InvalidCardException("Invalid expirationDate");
        if (!this.validateName(holderName))
            throw new InvalidCardException("Invalid Owner Name");
        else if (!this.validateNumberField(securityCode))
            throw new InvalidCardException("Wrong format cvvCode");
        else if (!this.validateCardCode(cardNumber))
            throw new InvalidCardException("Wrong format code number");
    }

    public boolean checkInvoice(Invoice invoice, String content) {
        return invoice.getContents().contains(content);
    }

    /**
     * base on transaction result , process rent or return bike
     *
     * @param transactionResult
     * @param invoice
     * @param card
     * @param stage
     * @param homeScreenHandler
     * @param prev
     * @throws IOException
     * @throws SQLException
     */
    public void proceedTransactionResult(TransactionInfo transactionResult, Invoice invoice, Card card, Stage stage, HomeScreenHandler homeScreenHandler, BaseScreenHandler prev) throws IOException, SQLException {
        if (!transactionResult.getErrorCode().equals("00")) {
            displayTransactionError(transactionResult.getErrorCode(), stage, homeScreenHandler, prev);
        } else {
            if (invoice.getContents().contains("deposit")) {
                moveToTransactionResult(invoice, transactionResult, card, stage, new RentBike());
            } else {
                moveToTransactionResult(invoice, transactionResult, card, stage, new ReturnBike());
            }
        }
    }

    /**
     * process pay order request
     *
     * @param cardNumber
     * @param holderName
     * @param securityCode
     * @param expirationDate
     * @param invoice
     * @param stage
     * @param homeScreenHandler
     * @param prev
     * @throws Exception
     */
    public void processPayRequest(String cardNumber, String holderName, String securityCode, String expirationDate, Invoice invoice, Stage stage, HomeScreenHandler homeScreenHandler, BaseScreenHandler prev) throws Exception {

        validateCard(cardNumber, holderName, securityCode, expirationDate);
        Card card = createCard(cardNumber, holderName, securityCode, expirationDate);
        TransactionInfo transactionResult = submitToPay(invoice, card);
        proceedTransactionResult(transactionResult, invoice, card, stage, homeScreenHandler, prev);

    }

}
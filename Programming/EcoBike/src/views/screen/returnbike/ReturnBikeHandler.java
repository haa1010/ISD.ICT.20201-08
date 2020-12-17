package views.screen.returnbike;

import controller.BaseController;
import controller.PaymentController;
import controller.ReturnBikeController;
import entity.bike.Bike;
import entity.bike.StandardElectricBike;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.station.Station;
import entity.transaction.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.bike.BikeScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.payment.PaymentScreenHandler;
import views.screen.payment.TransactionErrorScreenHandler;

import java.io.IOException;
import java.time.LocalTime;
import java.util.logging.Logger;

public class ReturnBikeHandler extends BaseScreenHandler {

    @FXML
    private Text numberPlate;
    @FXML
    private Text barcode;
    @FXML
    private Text type;
    @FXML
    private Text battery;
    @FXML
    private Text remainingTime;
    @FXML
    private Text batteryLabel;
    @FXML
    private Text remainingTimeLabel;
    @FXML
    private Text deposit;
    @FXML
    private Text rentedTime;
    @FXML
    private Text total;
    @FXML
    private Text station;

    @FXML
    private ImageView bikeImage;

    @FXML
    private TextField owner;
    @FXML
    private TextField cardCode;

    @FXML
    private TextField dateExpired;
    @FXML
    private TextField cvvCode;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button submitBtn;
    @FXML
    private Button editBtn;

    private Bike bike;
    private Station s;
    private Card card;

    private static Logger LOGGER = Utils.getLogger(ReturnBikeHandler.class.getName());

    public ReturnBikeHandler(Stage stage, String screenPath, BaseController bController, Bike bike, Station station) throws IOException {
        super(stage, screenPath);
        setBController(bController);
        this.bike = bike;
        this.s = station;

        setBikeInfo();
        card = getBController().getCard();
        setCardInfo();
    }

    public ReturnBikeController getBController() {
        return (ReturnBikeController) super.getBController();
    }

    private void setBikeInfo() {
        numberPlate.setText(bike.getLicensePlate());
        barcode.setText(bike.getBarcode());
        type.setText(bike.getType());
        station.setText(s.getName());
        int deposit1 = (int)(bike.getValue() * 0.4);
        deposit.setText(Utils.getCurrencyFormat(deposit1));
        // set image from url
        String imageSource = bike.getUrlImage();
        boolean backgroundLoading = true;

        Image image = new Image(imageSource, backgroundLoading);
        bikeImage.setImage(image);

        if (bike instanceof StandardElectricBike) {
            int batteryPercentage = ((StandardElectricBike) bike).getBatteryPercentage();
            battery.setText(batteryPercentage + " %");
            remainingTime.setText(Utils.convertTime(((StandardElectricBike) bike).getRemainingTime()));
        } else {
            batteryLabel.setVisible(false);
            battery.setVisible(false);
            remainingTimeLabel.setVisible(false);
            remainingTime.setVisible(false);
        }
    }

    @FXML
    void moveToPaymentScreen(MouseEvent event) throws IOException {
        Order order = new Order(bike, LocalTime.now());
        Invoice invoice = new Invoice(order);
        BaseScreenHandler payment = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN_PATH, invoice);
        payment.setBController(new PaymentController());
        payment.setPreviousScreen(this);
        payment.setHomeScreenHandler(homeScreenHandler);
        payment.setScreenTitle("Payment Screen when Return Bike");
        payment.show();
    }

    private void setCardInfo() {
        owner.setText(card.getName());
        cardCode.setText(card.getCardCode());
        dateExpired.setText(card.getDateExpired());
        cvvCode.setText("******");
    }

    @FXML
    void submitReturnBike (MouseEvent event) throws IOException {

        // call API if success display invoice screen

        // else error then display transaction error screen


        String errorCode = String.valueOf(3);
        String errorMessage;
        errorMessage = Configs.errorCodes.get(errorCode);

        Order order = new Order(bike, LocalTime.now());
        Invoice invoice = new Invoice(order);

        TransactionErrorScreenHandler tes  = new TransactionErrorScreenHandler(this.stage, Configs.TRANSACTION_ERROR_SCREEN_PATH, errorMessage, invoice);
        tes.setPreviousScreen(this);
        tes.setBController( new ReturnBikeController());
        tes.setHomeScreenHandler(homeScreenHandler);
        tes.setScreenTitle("Transaction Error Screen");
        tes.show();
    }

    @FXML
    void backToDockSelection (MouseEvent event) throws IOException {
        SelectDockToReturnBikeScreenHandler d = new SelectDockToReturnBikeScreenHandler(stage, Configs.SELECT_DOCK_TO_RETURN_BIKE_PATH,  bike);
        d.show();
    }

    @FXML
    void backToHome(MouseEvent event) throws IOException {
        HomeScreenHandler homeScreenHandler = new HomeScreenHandler(this.stage, Configs.HOME_SCREEN_PATH);
//        HomeScreenHandler.setHomeScreenHandler(homeScreenHandler);
//        HomeScreenHandler.setBController(new ViewCartController());
//        HomeScreenHandler.requestToViewCart(this.getPreviousScreen());
    }
}

package views.screen.home;

<<<<<<< HEAD:Programming/EcoBike/src/views/screen/home/HomeScreenHandler.java
<<<<<<< HEAD:UnitTest/Programming/EcoBike/src/views/screen/home/HomeScreenHandler.java
import controller.HomeController;
import entity.station.Station;
=======
>>>>>>> 94d61a88b8307c822c0d51173e7606ea27907525:Programming/EcoBike/src/views/screen/home/HomeScreenHandler.java
=======
>>>>>>> 59cc1ba62c9b9a2a3c59945b4f8d66e522a09cb0:Programming/EcoBike/src/views/screen/home/HomeScreenHandler.java
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class HomeScreenHandler extends BaseScreenHandler implements Initializable{

    //public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());

    @FXML
    private Label numMediaInCart;

    @FXML
    private ImageView aimsImage;

    @FXML
    private ImageView cartImage;

    @FXML
    private VBox vboxMedia1;

    @FXML
    private VBox vboxMedia2;

    @FXML
    private VBox vboxMedia3;

    @FXML
    private HBox hboxMedia;

    public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

//    @FXML
//    private SplitMenuButton splitMenuBtnSearch;
//
//    private List homeItems;
//
//    public HomeScreenHandler(Stage stage, String screenPath) throws IOException{
//        super(stage, screenPath);
//    }
//
//    public Label getNumMediaCartLabel(){
//        return this.numMediaInCart;
//    }
<<<<<<< HEAD:Programming/EcoBike/src/views/screen/home/HomeScreenHandler.java
<<<<<<< HEAD:UnitTest/Programming/EcoBike/src/views/screen/home/HomeScreenHandler.java

    public HomeController getBController() {
        return (HomeController) super.getBController();
    }

    @Override
    public void show() {
//        numMediaInCart.setText(String.valueOf(Cart.getCart().getListMedia().size()) + " media");
//        super.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setBController(new HomeController());
        try{
            List medium = getBController().getAllStations();
            this.homeItems = new ArrayList();
            for (Object object : medium) {
                Station station = (Station)object;
=======
=======
>>>>>>> 59cc1ba62c9b9a2a3c59945b4f8d66e522a09cb0:Programming/EcoBike/src/views/screen/home/HomeScreenHandler.java
//
//    public HomeController getBController() {
//        return (HomeController) super.getBController();
//    }
//
//    @Override
//    public void show() {
////        numMediaInCart.setText(String.valueOf(Cart.getCart().getListMedia().size()) + " media");
////        super.show();
//    }
//
//    @Override
//    public void initialize(URL arg0, ResourceBundle arg1) {
//        setBController(new HomeController());
//        try{
//            List medium = getBController().getAllMedia();
//            this.homeItems = new ArrayList<>();
//            for (Object object : medium) {
//                Media media = (Media)object;
>>>>>>> 94d61a88b8307c822c0d51173e7606ea27907525:Programming/EcoBike/src/views/screen/home/HomeScreenHandler.java
//                MediaHandler m1 = new MediaHandler(Configs.HOME_MEDIA_PATH, media, this);
                this.homeItems.add(station);
            }
        }catch (SQLException e){
            LOGGER.info("Errors occured: " + e.getMessage());
            e.printStackTrace();
        }
        
            
//        aimsImage.setOnMouseClicked(e -> {
//            addMediaHome(this.homeItems);
//        });
//
//        cartImage.setOnMouseClicked(e -> {
//            CartScreenHandler cartScreen;
//            try {
//                LOGGER.info("User clicked to view cart");
//                cartScreen = new CartScreenHandler(this.stage, Configs.CART_SCREEN_PATH);
//                cartScreen.setHomeScreenHandler(this);
//                cartScreen.setBController(new ViewCartController());
//                cartScreen.requestToViewCart(this);
//            } catch (IOException | SQLException e1) {
//                throw new ViewCartException(Arrays.toString(e1.getStackTrace()).replaceAll(", ", "\n"));
//            }
//        });
<<<<<<< HEAD:Programming/EcoBike/src/views/screen/home/HomeScreenHandler.java
<<<<<<< HEAD:UnitTest/Programming/EcoBike/src/views/screen/home/HomeScreenHandler.java
        addMediaHome(this.homeItems);
//        addMenuItem(0, "Book", splitMenuBtnSearch);
//        addMenuItem(1, "DVD", splitMenuBtnSearch);
//        addMenuItem(2, "CD", splitMenuBtnSearch);
    }
=======
=======
>>>>>>> 59cc1ba62c9b9a2a3c59945b4f8d66e522a09cb0:Programming/EcoBike/src/views/screen/home/HomeScreenHandler.java
//        addMediaHome(this.homeItems);
//        addMenuItem(0, "Book", splitMenuBtnSearch);
//        addMenuItem(1, "DVD", splitMenuBtnSearch);
//        addMenuItem(2, "CD", splitMenuBtnSearch);
  //  }
<<<<<<< HEAD:Programming/EcoBike/src/views/screen/home/HomeScreenHandler.java
>>>>>>> 94d61a88b8307c822c0d51173e7606ea27907525:Programming/EcoBike/src/views/screen/home/HomeScreenHandler.java
=======
>>>>>>> 59cc1ba62c9b9a2a3c59945b4f8d66e522a09cb0:Programming/EcoBike/src/views/screen/home/HomeScreenHandler.java

    public void setImage(){

    }

    public void addMediaHome(List items){
//        ArrayList mediaItems = (ArrayList)((ArrayList) items).clone();
//        hboxMedia.getChildren().forEach(node -> {
//            VBox vBox = (VBox) node;
//            vBox.getChildren().clear();
//        });
//        while(!mediaItems.isEmpty()){
//            hboxMedia.getChildren().forEach(node -> {
//                int vid = hboxMedia.getChildren().indexOf(node);
//                VBox vBox = (VBox) node;
//                while(vBox.getChildren().size()<3 && !mediaItems.isEmpty()){
//                    MediaHandler media = (MediaHandler) mediaItems.get(0);
//                    vBox.getChildren().add(media.getContent());
//                    mediaItems.remove(media);
//                }
//            });
//            return;
//        }
    }

    private void addMenuItem(int position, String text, MenuButton menuButton){
//        MenuItem menuItem = new MenuItem();
//        Label label = new Label();
//        label.prefWidthProperty().bind(menuButton.widthProperty().subtract(31));
//        label.setText(text);
//        label.setTextAlignment(TextAlignment.RIGHT);
//        menuItem.setGraphic(label);
//        menuItem.setOnAction(e -> {
//            // empty home media
//            hboxMedia.getChildren().forEach(node -> {
//                VBox vBox = (VBox) node;
//                vBox.getChildren().clear();
//            });
//
//            // filter only media with the choosen category
//            List filteredItems = new ArrayList<>();
//            homeItems.forEach(me -> {
//                MediaHandler media = (MediaHandler) me;
//                if (media.getMedia().getTitle().toLowerCase().startsWith(text.toLowerCase())){
//                    filteredItems.add(media);
//                }
//            });
//
//            // fill out the home with filted media as category
//            addMediaHome(filteredItems);
//        });
//        menuButton.getItems().add(position, menuItem);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

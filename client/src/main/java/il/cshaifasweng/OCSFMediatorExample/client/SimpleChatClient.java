package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.LogInEntities.Customers.RegisteredCustomer;
import il.cshaifasweng.ParkingLotEntities.ParkingLot;
import il.cshaifasweng.customerCatalogEntities.OnlineOrder;
import il.cshaifasweng.customerCatalogEntities.Subscription;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Stack;

import lombok.Getter;
import lombok.Setter;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

@Getter
@Setter
public class SimpleChatClient extends Application {

    private static Scene scene;
    private static SimpleClient client;
    private static Object user;
    private static RegisteredCustomer currentCustomerDetails;
    private static OnlineOrder currentOnlineOrder;
    private static Subscription currentSubscription;
    private static Integer RequestBetweenScreens=currentClientScreenRequest.NONE.ordinal();
    private static Stack<String> screenHistory = new Stack<String>();
    private static Integer userID;
    private static ParkingLot currentKioskID;
    public static void setCurrentKioskID(ParkingLot kioskID){
        currentKioskID=kioskID;
    }
    public static ParkingLot getCurrentKioskID(){
        return currentKioskID;
    }
    public static int getUserID() {
        return userID;
    }
    public static void  setUserID(Integer userID){
        SimpleChatClient.userID=userID;
    }
    public static String peekScreen(){
        return screenHistory.peek();
    }
    public static RegisteredCustomer getRegisteredCustomerDetails(){
        return currentCustomerDetails;
    }
    public static void setRegisteredCustomerDetails(RegisteredCustomer Details){
        currentCustomerDetails=Details;
    }
    public static void addScreen(String screenName){
        screenHistory.push(screenName);
    }
    public static String getPreviousScreen(){
        return screenHistory.pop();
    }
    public static int getCurrentRequest() {
        return RequestBetweenScreens;
    }
    public static void setCurrentRequest(Integer currentRequest) {
        RequestBetweenScreens = currentRequest;
    }

    public static Subscription getCurrentSubscription() {
            return currentSubscription;
        }

    public static void setCurrentSubscription(Subscription currentSubscription) {
        SimpleChatClient.currentSubscription = currentSubscription;
        currentOnlineOrder =null;
}
    public static OnlineOrder getCurrentOrder() {
        return currentOnlineOrder;
    }


    public static void setCurrentOrder(OnlineOrder currentOnlineOrder) {
        SimpleChatClient.currentOnlineOrder = currentOnlineOrder;
        currentSubscription =null;
    }
    public  static Scene getScene(){
        return scene;
    }

    @Override
    public void start(Stage stage) throws IOException {
    	EventBus.getDefault().register(this);
    	client = SimpleClient.getClient();
    	client.openConnection();
        scene = new Scene(loadFXML("KioskSetupPage"), 1080, 720);
        stage.setScene(scene);
        stage.show();
    }


    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleChatClient.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static Object getUser(){
        return user;
    }

    public static void setUser(Object user){
        SimpleChatClient.user = user;
    }

    @Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
    	EventBus.getDefault().unregister(this);
		super.stop();
	}


    @Subscribe
    public void onMessageEvent(MessageEvent message) {

    }


	public static void main(String[] args) {
        launch();
    }

}
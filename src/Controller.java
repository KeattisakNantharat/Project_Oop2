package application;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.LinkedList;


import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Controller  {
	//หน้าล็อกอิน
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private Parent root;
	@FXML
	private Label login;
	@FXML
	private TextField UsernameField;
	@FXML
	private PasswordField PasswordField;
	
	//หน้ายูเซอร์1
	private List<Activity> activity = DataActivity.getInstance().getActivity();
	@FXML
	private File Urlpic;
	@FXML
	private ImageView myImage;
	@FXML
	private TextField UserScene1;
	@FXML
	private TextField UserScene2;
	@FXML
	private TextField UserScene3;
	@FXML
	private Label ErrorText;
//	@FXML
//	private ComboBox ComboBoxUserScene1;
	@FXML
	private Pane paneAdminScene1;
	
	//หน้ายูเซอร์2
	
	
	
	
	
	//หน้าแอดมิน1
	@FXML
	private ImageView picAdmin;
	@FXML
	private TextField AdminScene1;
	@FXML
	private TextField AdminScene2;
	@FXML
	private TextField AdminScene3;
	@FXML
	private Button AdminSceneBottion;
	@FXML
	private Label AdminText;
	 	
	
	
//	@FXML
//	public void Select(ActionEvent e) throws IOException {
//		String s = ComboBoxUserScene1.getSelectionModel().getSelectedItem().toString();
//		if (s == "LOGIN") {
//			Parent root = FXMLLoader.load(getClass().getResource("loginScene.fxml"));
//			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
//			scene = new Scene(root);
//			stage.setScene(scene);
//			stage.show();
//		}else if (s == "LOGOUT") {
//			
//		}
//	}
	
	
	
//	
//	@Override
//	public void initialize(URL url,ResourceBundle rb) {
//		ObservableList<String> list = FXCollections.observableArrayList("LOGIN","LOGOUT");
//		ComboBoxUserScene1.setItems(list);
//		
//	}
	
	
	
	
	
	//ปุ่มกดอัพโหลดรูป
	@FXML
	public void buttonUpload(ActionEvent e) {
		Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		 FileChooser fileChooser = new FileChooser();
		 fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
		 File selectedFile = fileChooser.showOpenDialog(primaryStage);
         
		 if (selectedFile != null) {
             try {
                 // คัดลอกไฟล์รูปภาพไปยังโฟลเดอร์ที่ต้องการ
                 File destinationFile = new File("Picture" + selectedFile.getName());
                 copyFile(selectedFile, destinationFile);
                 // โหลดและแสดงรูปที่อัพโหลด
                 Image image = new Image(new FileInputStream(destinationFile));
                 myImage.setImage(image);
                 Urlpic = destinationFile;
                 
             } catch (IOException ex) {
                 ex.printStackTrace();
             }
	 }
	}
    private void copyFile(File source, File destination) throws IOException {
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(destination)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }
    }
    
    
    //ปุ่มกดส่งข้อมูลหน้ายูเซอร์1
    @FXML
    public void sendButton(ActionEvent e) {
    	if (Urlpic != null && !UserScene1.getText().isEmpty() && !UserScene2.getText().isEmpty() && !UserScene3.getText().isEmpty()) {
    	activity.add(new Activity(Urlpic,UserScene1.getText(),UserScene2.getText(),UserScene3.getText()));
        Image image = new Image(getClass().getResourceAsStream("AnswerPic.png"));
        myImage.setImage(image);
        UserScene1.clear();
        UserScene2.clear();
        UserScene3.clear();
        ErrorText.setText("");
    	}else {
    		ErrorText.setText("กรุณากรอกข้อมูลให้ครบทุกช่อง");
    	}
    }
    
    @FXML
    public void refreshButton() throws FileNotFoundException {
            Activity latestActivity = activity.get(0);
            Image image = new Image(new FileInputStream(latestActivity.getUrlPic()));
            picAdmin.setImage(image);
            AdminScene1.setText(latestActivity.getInfoActivity());
            AdminScene2.setText(latestActivity.getInfoOther());
            AdminScene3.setText(latestActivity.getInfoPlace());
    }
    
    int i = 1;
    @FXML
    public void sendAdminScene() throws FileNotFoundException {
    	try {
	    	Activity e = activity.get(i);
	    	Image image = new Image(new FileInputStream(e.getUrlPic()));
	    	picAdmin.setImage(image);
	    	AdminScene1.setText(e.getInfoActivity());
	        AdminScene2.setText(e.getInfoOther());
	        AdminScene3.setText(e.getInfoPlace());
	        i++;
    	}catch(Exception e) {
            Image image = new Image(getClass().getResourceAsStream("AnswerPic.png"));
            picAdmin.setImage(image);
            AdminScene1.clear();
            AdminScene2.clear();
            AdminScene3.clear();
            AdminText.setText("คุณได้ตรวจสอบทั้งหมดแล้ว");
            BackgroundFill backgroundFill = new BackgroundFill(Color.web("#3E3E3E"), null, null);
            Background background = new Background(backgroundFill);
            paneAdminScene1.setBackground(background);
    	}
    }
    
    
    
	//ปุ่มล๊อคอินไปยังหน้าUserหรือAdmin
	@FXML
	public void next(ActionEvent e) throws IOException {
		try{
			String username = UsernameField.getText();
//			String password = PasswordField.getText();

		if ( username.equals("User")){
			Parent root = FXMLLoader.load(getClass().getResource("UserScene1.fxml"));
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}else if (username.equals("Admin")){
			Parent root = FXMLLoader.load(getClass().getResource("AdminScene1.fxml"));
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}catch(Exception E) {
		System.out.println(E);
	}	
	}
	//ปุ้มกลับไปยังหน้าล๊อกอิน
	@FXML
	public void nextLogin(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/loginScene.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
    //เปิดหน้ายูเซอร์1
    @FXML
    public void nextUserScene1(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("UserScene1.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    //เปิดหน้ายูเชอร์2
    @FXML
    public void nextUserScene2(ActionEvent e) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("UserScene2.fxml"));
    	stage = (Stage)((Node)e.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    
    //เปิดหน้าแอดมิน1
    @FXML
    public void nextAdminScene1(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AdminScene1.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    //เปิดหน้าแอดมิน2
    @FXML
    public void nextAdminScene2(ActionEvent e) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("AdminScene2.fxml"));
    	stage = (Stage)((Node)e.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
  //เปิดหน้าแอดมิน2
    @FXML
    public void nextAdminScene3(ActionEvent e) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("AdminScene3.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
}


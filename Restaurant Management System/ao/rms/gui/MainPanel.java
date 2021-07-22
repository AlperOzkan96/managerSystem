package ao.rms.gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import ao.rms.employee.Cook;
import ao.rms.employee.Employee;
import ao.rms.employee.Manager;
import ao.rms.employee.Server;
import ao.rms.food.Beverage;
import ao.rms.food.Food;
import ao.rms.food.Hamburger;
import ao.rms.food.Menu;
import ao.rms.restaurant.Restaurant;
import ao.rms.restaurant.Table;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPanel extends Application {

	Label lblID, lblPassword;
	TextField txtID, txtPassword;
	Button btnEnter, btnQuit;
	Restaurant sns;
	Stage mainStage;
	
	ObservableList<Employee> empList;
	ObservableList<Food> menuList;
	ObservableList<Table> tableList;
	
	public MainPanel() {
		sns = ao.rms.restaurant.test.initRestaurant();
		
		lblID = new Label("ID:");
		lblPassword = new Label("Password:");
	
		txtID = new TextField();
		txtPassword = new TextField();
		
		btnEnter = new Button("Enter");
		btnQuit = new Button("Quit");
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		primaryStage.setTitle("Restaurant Management System");
		primaryStage.setWidth(300);
		primaryStage.setHeight(175);
		
		GridPane gpMain = new GridPane();
		gpMain.setPadding(new Insets(15));
		gpMain.setVgap(15);
		gpMain.setHgap(15);
		
		HBox hbButtons = new HBox();
		hbButtons.getChildren().addAll(btnEnter, btnQuit);
		hbButtons.setSpacing(20);
		hbButtons.setAlignment(Pos.BASELINE_RIGHT);
		
		gpMain.add(lblID, 0, 0);
		gpMain.add(txtID, 1, 0);
		
		gpMain.add(lblPassword, 0, 1);
		gpMain.add(txtPassword, 1, 1);
		
		gpMain.add(hbButtons, 1, 2);
		
		
		Scene scene = new Scene(gpMain);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void init() {
		btnQuit.setOnAction(ae -> Platform.exit());
		btnEnter.setOnAction(ae -> validatePassword(sns));
	}
	
	private void validatePassword(Restaurant sns) {
		
		boolean isLoginValid = false;
		String ID = txtID.getText();
		String password = txtPassword.getText();
		for(Employee emp: sns.getEmployees()) {
			if(emp.getPassword().equals(password) && emp.getID().equals(ID)) {
				isLoginValid = true;
				mainStage.close();
				if(emp instanceof Manager) {
					managerPanel((Manager)emp);
				}
				else if(emp instanceof Server) {
					serverPanel((Server)emp);
				}
				else if(emp instanceof Cook) {
					cookPanel();
				}
			}
		}
		
		if(!(isLoginValid)) {
			Alert alertLogin = new Alert(AlertType.ERROR);
			alertLogin.setTitle("ALERT");
			alertLogin.setHeaderText("LOGIN ERROR!");
			alertLogin.setContentText("The login information you have entered is wrong. Please try again!");
			alertLogin.showAndWait();
		}
	
	}

	private void managerPanel(Manager emp) {
		Stage managerStage = new Stage();
		managerStage.setWidth(350);
		managerStage.setHeight(175);
		managerStage.setTitle("MANAGER PANEL");
		
		Button btnEmpPanel = new Button("Employee Panel");
		Button btnMenuPanel = new Button("Menu Panel");
		Button btnFinancialPanel = new Button("Financial Panel");
		Button btnClose = new Button("Close");
		btnClose.setOnAction(ae -> {
			managerStage.close();
			mainStage.show();
		});
		
		Label managerInfo = new Label("Logged in by " + emp.getName() + " " + emp.getSurname());
		managerInfo.setAlignment(Pos.BOTTOM_LEFT);
		GridPane gpManager = new GridPane();
		gpManager.add(btnEmpPanel, 0, 0);
		gpManager.add(btnMenuPanel, 1, 0);
		gpManager.add(btnFinancialPanel, 2, 0);
		gpManager.add(btnClose, 2, 2);
		gpManager.add(managerInfo, 0, 2, 2, 1);
		gpManager.setPadding(new Insets(15));
		gpManager.setVgap(15);
		gpManager.setHgap(15);
		
		btnEmpPanel.setOnAction(ae -> empPanel(emp));
		btnMenuPanel.setOnAction(ae -> menuPanel(emp));
		
		Scene managerScene = new Scene(gpManager);
		managerStage.setScene(managerScene);
		managerStage.show();
	}

	private void empPanel(Manager emp) {
		Stage employeeStage = new Stage();
		employeeStage.setWidth(650);
		employeeStage.setHeight(475);
		employeeStage.setTitle("Employee Panel");
		
		GridPane gpEmployeeStage = new GridPane();
		
		Button btnHire = new Button("Hire Employee");
		btnHire.setOnAction(ae -> empPanelHireEmployee(emp));
		Button btnFire = new Button("Fire Employee");
	
		Button btnClose = new Button("Close");
		btnClose.setOnAction(ae -> employeeStage.close());
		btnFire.setDisable(true);
		
		TableView<Employee> tvEmployeeStage = new TableView<Employee>();
		tvEmployeeStage.getSelectionModel().selectedItemProperty().addListener(ae -> {
			if(!(tvEmployeeStage.getSelectionModel().getSelectedItem() instanceof Manager)) {
				btnFire.setDisable(false);
			}
			else {
				btnFire.setDisable(true);
			}
		});
		
		empList = FXCollections.observableArrayList();
	    
	    for(Employee e: sns.getEmployees())
	    	empList.add(e);
		
		TableColumn colFirstName = new TableColumn("First Name");
		colFirstName.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
		colFirstName.setMinWidth(100);
	    TableColumn colLastName = new TableColumn("Last Name");
	    colLastName.setCellValueFactory(new PropertyValueFactory<Employee, String>("surname"));
	    colLastName.setMinWidth(100);
	    TableColumn colTitle = new TableColumn("Title");
	    colTitle.setCellValueFactory(new PropertyValueFactory<Employee, String>("title"));
	    colTitle.setMinWidth(100);
	    TableColumn colSalary = new TableColumn("Salary");
	    colSalary.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
	    colSalary.setMinWidth(100);
	    TableColumn colSupervisor = new TableColumn("Supervisor");
	    colSupervisor.setCellValueFactory(new PropertyValueFactory<Employee, String>("supervisorName"));
	    colSupervisor.setMinWidth(100);
	    
	    tvEmployeeStage.setItems(empList);
	    tvEmployeeStage.getColumns().addAll(colFirstName, colLastName, colTitle, colSalary, colSupervisor);

		gpEmployeeStage.add(btnHire, 0, 0);
		gpEmployeeStage.add(btnFire, 0, 1);
		gpEmployeeStage.add(btnClose, 0, 3);
		
		gpEmployeeStage.add(tvEmployeeStage, 1, 0, 1, 3);
		gpEmployeeStage.setPadding(new Insets(15));
		gpEmployeeStage.setHgap(15);
		gpEmployeeStage.setVgap(15);

		btnFire.setOnAction(ae -> {
			int empIndex = sns.getEmployees().indexOf(tvEmployeeStage.getSelectionModel().getSelectedItem());
			empList.remove(empIndex);
			sns.getEmployees().remove(empIndex);
	
		});
		
		Scene employeScene = new Scene(gpEmployeeStage);
		employeeStage.setScene(employeScene);
		employeeStage.show();
		
	}

	private void menuPanel(Manager emp) {
		Stage menuStage = new Stage();
		menuStage.setWidth(1250);
		menuStage.setHeight(475);
		menuStage.setTitle("Menu Panel");
		
		GridPane gpMenuStage = new GridPane();
		
		Button btnAll = new Button("Display All Items");
		Button btnBurger = new Button("Display Burgers Only");
		Button btnBeverage = new Button("Display Beverages Only");
		Button btnModifyItem = new Button("Modify Item");
		btnModifyItem.setDisable(true);
		Button btnAddItem = new Button("Add Item");
		btnAddItem.setOnAction(ae -> menuPanelAddItem());
		Button btnRmvItem = new Button("Remove Item");
		btnRmvItem.setDisable(true);
		Button btnClose = new Button("Close");
		btnClose.setOnAction(ae -> menuStage.close());
		
		TableView<Food> tvFood = new TableView<Food>();
		
		menuList = FXCollections.observableArrayList();
	    
	    for(Food f: sns.getMenu().getFood())
	    	menuList.add(f);
	    
		TableColumn<Food, String> colName = new TableColumn<Food, String>("Food Name");
		colName.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));
		colName.setMinWidth(225);
		  
		TableColumn<Food, String> colFoodType = new TableColumn<Food, String>("Food Type");
		colFoodType.setCellValueFactory(new PropertyValueFactory<Food, String>("foodType"));
		colFoodType.setMinWidth(100);
		
		TableColumn<Food, Double> colPrice = new TableColumn<Food, Double>("Food Price");
		colPrice.setCellValueFactory(new PropertyValueFactory<Food, Double>("price"));
		colPrice.setMinWidth(75);
		  
		TableColumn<Food, String> colIngredients = new TableColumn<Food, String>("Food Ingredients");
		colIngredients.setCellValueFactory(new PropertyValueFactory<Food, String>("ingredientList"));
		colIngredients.setMinWidth(650);

	    tvFood.setItems(menuList);
	    tvFood.getColumns().addAll(colName, colFoodType,colPrice, colIngredients);
	    
	    gpMenuStage.add(btnAll, 0, 0);
	    gpMenuStage.add(btnBurger, 0, 1);
	    gpMenuStage.add(btnBeverage, 0, 2);
	    gpMenuStage.add(btnModifyItem, 0, 3);
	    gpMenuStage.add(btnAddItem, 0, 4);
	    gpMenuStage.add(btnRmvItem, 0, 5);
	    gpMenuStage.add(btnClose, 0, 7);
		
	    gpMenuStage.add(tvFood, 1, 0, 1, 7);
	    gpMenuStage.setPadding(new Insets(15));
	    gpMenuStage.setHgap(15);
	    gpMenuStage.setVgap(15);
	    
	    tvFood.getSelectionModel().selectedItemProperty().addListener(ae -> {
			if((tvFood.getSelectionModel().getSelectedItem() != null)) {
				btnModifyItem.setDisable(false);
				btnRmvItem.setDisable(false);
			}
			else {
				btnModifyItem.setDisable(true);
				btnRmvItem.setDisable(true);
			}
		});
	    btnBeverage.setOnAction(ae -> {
	    	menuList.clear();
	    	for(Food f: sns.getMenu().getFood())
	    		if(f instanceof Beverage) {
	    			menuList.add(f);
	    		}
	    	tvFood.setItems(menuList);
	    	
	    });
	    
	    btnBurger.setOnAction(ae -> {
	    	menuList.clear();
	    	for(Food f: sns.getMenu().getFood())
	    		if(f instanceof Hamburger) {
	    			menuList.add(f);
	    		}
	    	tvFood.setItems(menuList);
	    });
	    
	    btnAll.setOnAction(ae -> {
	    	menuList.clear();
	    	for(Food f: sns.getMenu().getFood())
	    		menuList.add(f);
	    	tvFood.setItems(menuList);
	    });
	    
	    btnModifyItem.setOnAction(ae -> menuPanelModifyItem(emp, tvFood.getSelectionModel().getSelectedItem(), btnAll));
	    
	    btnRmvItem.setOnAction(ae -> {
	    	int foodIndex = sns.getMenu().getFood().indexOf(tvFood.getSelectionModel().getSelectedItem());
//	    	String foodName = tvFood.getSelectionModel().getSelectedItem().getName();
//	    	System.out.println(foodName);
	    	menuList.remove(foodIndex);
	    	sns.getMenu().getFood().remove(foodIndex);
	    });
	    
		Scene menuScene = new Scene(gpMenuStage);
		menuStage.setScene(menuScene);
		menuStage.show();

	}

	private void menuPanelModifyItem(Manager emp, Food tvFoodEntry, Button btnAll) {
		Stage modifyItemStage = new Stage();
		modifyItemStage.setTitle("Modify Item");
		GridPane gpModifyItem = new GridPane();
		
		gpModifyItem.setVgap(15);
		gpModifyItem.setHgap(15);
		gpModifyItem.setPadding(new Insets(15));
		
		Label lblName = new Label("Food Name:");
		TextField txtName = new TextField(tvFoodEntry.getName());
		gpModifyItem.add(lblName, 0, 0);
		gpModifyItem.add(txtName, 1, 0);
		
		Label lblPrice = new Label("Price:");
		TextField txtPrice = new TextField(tvFoodEntry.getPrice() + "");
		gpModifyItem.add(lblPrice, 0, 1);
		gpModifyItem.add(txtPrice, 1, 1);
		
		int nextRow = 3;
		
		Button btnCancel = new Button("Cancel");
		btnCancel.setOnAction(ae -> modifyItemStage.close());
		Button btnModify = new Button("Modify");
		
		HBox modifyButtons = new HBox();
		modifyButtons.getChildren().addAll(btnModify, btnCancel);
		modifyButtons.setSpacing(15);
		modifyButtons.setAlignment(Pos.BASELINE_RIGHT);
		
		
		if(tvFoodEntry instanceof Hamburger) {
			Label lblIngredients = new Label("Ingredients:");
			VBox[] vbModifyItem = new VBox[Hamburger.getAllIngredients().size()];
			HBox ingredientList = new HBox();
			ingredientList.setSpacing(15);
			for(int i = 0; i < Hamburger.getAllIngredients().size(); i++) {
				vbModifyItem[i] = new VBox();
				vbModifyItem[i].setSpacing(10);
				vbModifyItem[i].getChildren().add(new Label(Hamburger.ingredientHeaders[i]));
				
				for(int j = 0; j < Hamburger.getAllIngredients().get(i).size(); j++) {

					vbModifyItem[i].getChildren().add(new CheckBox(Hamburger.getAllIngredients().get(i).get(j)));
//					
				}
				
				for(int k = 0; k < ((Hamburger)tvFoodEntry).getIngredients().get(i).size(); k++) {
					for(int l = 0; l < Hamburger.getAllIngredients().get(i).size(); l++) {
						if(((Hamburger)tvFoodEntry).getIngredients().get(i).get(k).equals(Hamburger.getAllIngredients().get(i).get(l))) {
//							System.out.println(((Hamburger)tvFoodEntry).getIngredients().get(i).get(k));
							((CheckBox) (vbModifyItem[i].getChildren().get(l + 1))).setSelected(true);
						}
					}	
				}
				
				ingredientList.getChildren().add(vbModifyItem[i]);
			}
			gpModifyItem.add(lblIngredients, 0, 2);
			gpModifyItem.add(ingredientList, 0, 3, 3, 1);
			nextRow = 4;
			
			btnModify.setOnAction(ae -> {
				Hamburger selectedBurger = ((Hamburger)tvFoodEntry);
				selectedBurger.setName(txtName.getText());
				selectedBurger.setPrice(Double.parseDouble(txtPrice.getText()));
				for(int i = 0; i < Hamburger.getAllIngredients().size(); i++) {
					for(int j = 0; j < Hamburger.getAllIngredients().get(i).size(); j++) {
						CheckBox cbSelectedItem = ((CheckBox) (vbModifyItem[i].getChildren().get(j + 1)));
						if(cbSelectedItem.isSelected() && !selectedBurger.getIngredients().get(i).contains(cbSelectedItem.getText())) {
							System.out.println(cbSelectedItem.getText());
							selectedBurger.getIngredients().get(i).add(new String(cbSelectedItem.getText()));
							selectedBurger.updateIngredientList();
							
						}
						
						else if(!cbSelectedItem.isSelected() && selectedBurger.getIngredients().get(i).contains(cbSelectedItem.getText())) {
							int selectedIngrIndex = selectedBurger.getIngredients().get(i).indexOf(cbSelectedItem.getText());
							selectedBurger.getIngredients().get(i).remove(selectedIngrIndex);
							selectedBurger.updateIngredientList();
						}
					}
				}
				modifyItemStage.close();
				btnAll.fire();
			});
			
		}
		
		gpModifyItem.add(modifyButtons, 2, nextRow);
		
		Scene modifyItemScene = new Scene(gpModifyItem);
		modifyItemStage.setScene(modifyItemScene);
		modifyItemStage.show();
	}

	private void menuPanelAddItem() {
		Stage addItemStage = new Stage();
		addItemStage.setTitle("Add New Item");
		GridPane gpAddItem = new GridPane();
		
		gpAddItem.setVgap(15);
		gpAddItem.setHgap(15);
		gpAddItem.setPadding(new Insets(15));
		
		Label lblName = new Label("Food Name:");
		TextField txtName = new TextField();
		gpAddItem.add(lblName, 0, 0);
		gpAddItem.add(txtName, 1, 0);
		
		Label lblPrice = new Label("Price:");
		TextField txtPrice = new TextField();
		gpAddItem.add(lblPrice, 0, 1);
		gpAddItem.add(txtPrice, 1, 1);
		
		Label lblFoodType = new Label("Food Type:");
		ToggleGroup rbGroup = new ToggleGroup();
		
		RadioButton rbBurger = new RadioButton("Hamburger");
		rbBurger.setToggleGroup(rbGroup);

		RadioButton rbMilkshake = new RadioButton("Milkshake");
		rbMilkshake.setSelected(true);
		rbMilkshake.setToggleGroup(rbGroup);
		
		HBox radioButtons = new HBox();
		radioButtons.setSpacing(15);
		radioButtons.getChildren().add(rbBurger);
		radioButtons.getChildren().add(rbMilkshake);
			
		gpAddItem.add(lblFoodType, 0, 2);
		gpAddItem.add(radioButtons, 1, 2);
		
		if(((RadioButton)rbGroup.getSelectedToggle()).getText().equals("Hamburger")) {
			Label lblIngredients = new Label("Ingredients:");
			VBox[] vbAddItem = new VBox[Hamburger.getAllIngredients().size()];
			HBox ingredientList = new HBox();
			ingredientList.setSpacing(15);
			for(int i = 0; i < Hamburger.getAllIngredients().size(); i++) {
				vbAddItem[i] = new VBox();
				vbAddItem[i].setSpacing(10);
				vbAddItem[i].getChildren().add(new Label(Hamburger.ingredientHeaders[i]));
				
				for(int j = 0; j < Hamburger.getAllIngredients().get(i).size(); j++) {

					vbAddItem[i].getChildren().add(new CheckBox(Hamburger.getAllIngredients().get(i).get(j)));
//					
				}
				ingredientList.getChildren().add(vbAddItem[i]);
			}
			gpAddItem.add(lblIngredients, 0, 3);
			gpAddItem.add(ingredientList, 0, 4, 3, 1);
			
		}
		
		Scene addItemScene = new Scene(gpAddItem);
		addItemStage.setScene(addItemScene);
		addItemStage.show();
		
	}
	
	private void empPanelHireEmployee(Manager emp) {

		Stage empHireStage = new Stage();
		empHireStage.setTitle("New Employee Information");
		GridPane gpHirePane = new GridPane();
		
		gpHirePane.setVgap(15);
		gpHirePane.setHgap(15);
		gpHirePane.setPadding(new Insets(15));
		
		Label lblName = new Label("Name:");
		TextField txtName = new TextField();
		
		Label lblSurname = new Label("Surname:");
		TextField txtSurname = new TextField();
		
		Label lblTitle = new Label("Title:");
		ToggleGroup rbGroup = new ToggleGroup();
		RadioButton rbServer = new RadioButton("Server");
		rbServer.setToggleGroup(rbGroup);
		rbServer.setSelected(true);

		RadioButton rbCook = new RadioButton("Cook");
		rbCook.setToggleGroup(rbGroup);
		
		RadioButton rbManager = new RadioButton("Manager");
		rbManager.setToggleGroup(rbGroup);
		
		Label lblSalary = new Label("Salary:");
		TextField txtSalary = new TextField();
		
		HBox hireButtons = new HBox();
		Button btnAdd = new Button("Add");
		btnAdd.setOnAction(ae -> {
			if(((RadioButton)rbGroup.getSelectedToggle()).getText().equals("Server")) {
				sns.getEmployees().add(new Server(txtName.getText(), txtSurname.getText(), Double.parseDouble(txtSalary.getText()), sns, emp));
				
			}
			else if(((RadioButton)rbGroup.getSelectedToggle()).getText().equals("Cook")) {
				sns.getEmployees().add(new Cook(txtName.getText(), txtSurname.getText(), Double.parseDouble(txtSalary.getText()), sns, emp));
				
			}
			else {
				sns.getEmployees().add(new Manager(txtName.getText(), txtSurname.getText(), Double.parseDouble(txtSalary.getText()), sns));
			}
			
			empList.add(sns.getEmployees().get(sns.getEmployees().size() - 1));
			empHireStage.close();
		});
		
		Button btnCancel = new Button("Cancel");
		btnCancel.setOnAction(ae -> empHireStage.close());
		
		hireButtons.getChildren().addAll(btnAdd, btnCancel);
		hireButtons.setSpacing(15);
		hireButtons.setAlignment(Pos.BASELINE_RIGHT);
		
		gpHirePane.add(lblName, 0, 0);
		gpHirePane.add(txtName, 1, 0);
		
		gpHirePane.add(lblSurname, 0, 1);
		gpHirePane.add(txtSurname, 1, 1);
		
		gpHirePane.add(lblTitle, 0, 2);
		
		HBox radioButtons = new HBox();
		radioButtons.setSpacing(15);
		radioButtons.getChildren().add(rbServer);
		radioButtons.getChildren().add(rbCook);
		radioButtons.getChildren().add(rbManager);
		gpHirePane.add(radioButtons, 1, 2);

		
		gpHirePane.add(lblSalary, 0, 3);
		gpHirePane.add(txtSalary, 1, 3);
		
		gpHirePane.add(hireButtons, 1, 4);
		
		Scene hireScene = new Scene(gpHirePane);
		empHireStage.setScene(hireScene);
		empHireStage.show();
		
	}

	private void cookPanel() {
		// TODO Auto-generated method stub
		
	}

	private void serverPanel(Server emp) {
		
		Stage serverStage = new Stage();
		serverStage.setWidth(350);
		serverStage.setHeight(175);
		serverStage.setTitle("SERVER PANEL");
		
		Button btnOrderPanel = new Button("Order Panel");
		Button btnPaymentPanel = new Button("Payment Panel");
		Button btnTableLogs = new Button("Table Logs");
		Button btnClose = new Button("Close");
		btnClose.setOnAction(ae -> {
			serverStage.close();
			mainStage.show();
		});
		
		Label serverInfo = new Label("Logged in by " + emp.getName() + " " + emp.getSurname());
		serverInfo.setAlignment(Pos.BOTTOM_LEFT);
		GridPane gpManager = new GridPane();
		gpManager.add(btnOrderPanel, 0, 0);
		gpManager.add(btnPaymentPanel, 1, 0);
		gpManager.add(btnTableLogs, 2, 0);
		gpManager.add(btnClose, 2, 2);
		gpManager.add(serverInfo, 0, 2, 2, 1);
		gpManager.setPadding(new Insets(15));
		gpManager.setVgap(15);
		gpManager.setHgap(15);
		
		btnOrderPanel.setOnAction(ae -> orderPanel(emp));
//		btnPaymentPanel.setOnAction(ae -> menuPanel(emp));
//		btnTableLogs.setOnAction(ae -> tableLogs(emp));
		
		Scene managerScene = new Scene(gpManager);
		serverStage.setScene(managerScene);
		serverStage.show();
		
	}

	private void orderPanel(Server emp) {
		Stage orderStage = new Stage();
		orderStage.setWidth(650);
		orderStage.setHeight(475);
		orderStage.setTitle("Order Panel");
		
		GridPane gpOrderStage = new GridPane();
		
		Button btnChooseTable = new Button("Choose Table");
		btnChooseTable.setDisable(true);
		btnChooseTable.setOnAction(ae -> orderPanelChooseTable(emp));
	
		Button btnClose = new Button("Close");
		btnClose.setOnAction(ae -> orderStage.close());
		
		TableView<Table> tvOrderStage = new TableView<Table>();
		tvOrderStage.getSelectionModel().selectedItemProperty().addListener(ae -> {
//			if(!(tvOrderStage.getSelectionModel().getSelectedItem() instanceof Manager)) {
//				btnFire.setDisable(false);
//			}
//			else {
//				btnFire.setDisable(true);
//			}
		});
		
		tableList = FXCollections.observableArrayList();
	    
	    for(Table t: sns.getTables())
	    	tableList.add(t);
		
		TableColumn colTableNo = new TableColumn("Table No");
		colTableNo.setCellValueFactory(new PropertyValueFactory<Table, Integer>("tableNo"));
		colTableNo.setMinWidth(100);
		
	    TableColumn colTableCapacity = new TableColumn("Table Capacity");
	    colTableCapacity.setCellValueFactory(new PropertyValueFactory<Table, Integer>("tableCapacity"));
	    colTableCapacity.setMinWidth(100);
	    
	    TableColumn colTableStatus = new TableColumn("Table Status");
	    colTableStatus.setCellValueFactory(new PropertyValueFactory<Table, Boolean>("tableOccupied"));
	    colTableStatus.setMinWidth(100);
	    
	    TableColumn colTableServer = new TableColumn("Table Server");
	    colTableServer.setCellValueFactory(new PropertyValueFactory<Table, Server>("server"));
	    colTableServer.setMinWidth(100);
	    
	    TableColumn colTableBill = new TableColumn("Table Bill");
	    colTableBill.setCellValueFactory(new PropertyValueFactory<Employee, String>("bill"));
	    colTableBill.setMinWidth(100);
	    
	    tvOrderStage.setItems(tableList);
	    tvOrderStage.getColumns().addAll(colTableNo, colTableCapacity, colTableStatus, colTableServer, colTableBill);

//	    tvOrderStage.add(btnHire, 0, 0);
//	    tvOrderStage.add(btnFire, 0, 1);
//		gpEmployeeStage.add(btnClose, 0, 3);
//		
//		gpEmployeeStage.add(tvEmployeeStage, 1, 0, 1, 3);
//		gpEmployeeStage.setPadding(new Insets(15));
//		gpEmployeeStage.setHgap(15);
//		gpEmployeeStage.setVgap(15);
//
//		btnFire.setOnAction(ae -> {
//			int empIndex = sns.getEmployees().indexOf(tvEmployeeStage.getSelectionModel().getSelectedItem());
//			empList.remove(empIndex);
//			sns.getEmployees().remove(empIndex);
//	
//		});
//		
//		Scene orderScene = new Scene(gpEmployeeStage);
//		orderStage.setScene(orderScene);
//		orderStage.show();
	}

	private Object orderPanelChooseTable(Server emp) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		launch();
	}
	
}

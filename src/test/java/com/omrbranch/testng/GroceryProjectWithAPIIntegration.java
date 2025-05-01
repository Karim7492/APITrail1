package com.omrbranch.testng;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.omrbranch.baseclass.BaseClass;
import com.omrbranch.pojo.cart.ClearCart_Output_Pojo;
import com.omrbranch.pojo.login.PostmanBasicAuthLogin_Output_Pojo;
import com.omrbranch.pojo.order.CancelOrder_Input_Pojo;
import com.omrbranch.pojo.order.CancelOrder_Output_Pojo;
import com.omrbranch.pojo.order.GetAllOrders_Output_Pojo;
import com.omrbranch.pojo.order.GetOrderData;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class GroceryProjectWithAPIIntegration extends BaseClass{
	
	WebDriver driver;
	String logToken;
	String orderIdText;
	String orderNoText;
	String orderNoNewText;
	
	@Test(priority = 1)
	public void login() {
		addHeader("accept", "application/json");

		addBasicAuth("abdulsensor@ymail.com", "Karim@123");

		Response response = addRequest("POST", "https://omrbranch.com/api/postmanBasicAuthLogin");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		assertEquals(statusCode, 200, "Status Code");

		PostmanBasicAuthLogin_Output_Pojo postmanBasicAuthLogin_Output_Pojo = response
				.as(PostmanBasicAuthLogin_Output_Pojo.class);

		String first_name = postmanBasicAuthLogin_Output_Pojo.getData().getFirst_name();
		System.out.println(first_name);

		logToken = postmanBasicAuthLogin_Output_Pojo.getData().getLogtoken();
		System.out.println(logToken);
		assertEquals(first_name, "Abdul Karim", "Verify PostmanBasic Authetication First Name");

	}
	@Test(priority = 2)
	public void clearCart() {
		
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logToken);

		List<Header> listHeader = new ArrayList<Header>();
		listHeader.add(h1);
		listHeader.add(h2);

		Headers headers = new Headers(listHeader);
		addHeader(headers);
		
		Response response = addRequest("GET", "https://omrbranch.com/api/clearCart");
		
		ClearCart_Output_Pojo clearCart_Output_Pojo = response.as(ClearCart_Output_Pojo.class);
		String clearCartMessage = clearCart_Output_Pojo.getMessage();
		System.out.println(clearCartMessage);
		

	}
	
	@Test(priority = 0)
	public void placingOrderUsingWebUI() throws InterruptedException {

		
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
		driver.navigate().to("https://omrbranch.com/");
		driver.manage().window().maximize();
		WebElement txtEmail = driver.findElement(By.id("email"));
		txtEmail.sendKeys("abdulsensor@ymail.com");
		WebElement txtPass = driver.findElement(By.id("pass"));
		txtPass.sendKeys("Karim@123");
		WebElement btnLogin = driver.findElement(By.xpath("//button[text()='Login']"));
		btnLogin.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebElement txtWelcome = driver.findElement(By.xpath("//a[contains(text(),'Welcome')]"));
		String txtFirstName = txtWelcome.getText();
		System.out.println(txtFirstName);
		WebElement txtSearch = driver.findElement(By.id("search"));
		txtSearch.click();
		txtSearch.sendKeys("nuts");
		WebElement btnSearch = driver.findElement(By.xpath("//button[@data-testid='searchbtn']"));
		btnSearch.click();
		List<WebElement> txtProdName = driver.findElements(By.xpath("//h5[@data-testid='productname']"));
		List<WebElement> txtNewPrice1 = driver.findElements(By.xpath("//span[@class='newPrice font18 color20']"));
		for(int i=0;i<txtProdName.size();i++) 
		{
			String productName = txtProdName.get(i).getText();
			String productPrice = txtNewPrice1.get(i).getText();
			System.out.println(productName + " : "+ productPrice);
		}
		WebElement btnAdd = driver.findElement(By.xpath("(//a[text()='Add'])[1]"));
		btnAdd.click();
		WebElement btnAddVariants = driver.findElement(By.xpath("(//button[text()='Add'])[1]"));
		btnAddVariants.click();
		
		Thread.sleep(1000);
		WebElement btnGoToCart = driver.findElement(By.xpath("//div[contains(@class,\"check\")]//a[contains(text(),'Go')]"));
		btnGoToCart.click();
		Thread.sleep(2000);
		WebElement btnAddress = driver.findElement(By.xpath("//img[@src='https://omrbranch.com/front/images/plus.png']"));	
		btnAddress.click();
		Thread.sleep(2000);
		WebElement selectAddressType = driver.findElement(By.id("address_type"));
		Select select = new Select(selectAddressType);
		select.selectByValue("Home");
		WebElement txtFirstNam = driver.findElement(By.xpath("(//input[@name='first_name'])[1]"));
		txtFirstNam.sendKeys("Abdul Karim");
		WebElement txtLastName = driver.findElement(By.xpath("(//input[@name='last_name'])[1]"));
		txtLastName.sendKeys("Azad");
		WebElement txtMobile = driver.findElement(By.xpath("(//input[@name='mobile'])[1]"));
		txtMobile.sendKeys("12345667891");
		WebElement txtHouseNum = driver.findElement(By.xpath("(//input[@name='apartment'])[1]"));
		txtHouseNum.sendKeys("10");
		WebElement txtAddress = driver.findElement(By.xpath("(//input[@name='address'])[1]"));
		txtAddress.sendKeys("No.6 Vivekandar st");
		WebElement txtState = driver.findElement(By.xpath("//select[@name='state']"));
		Select select2 = new Select(txtState);
		select2.selectByValue("35");
		WebElement txtCity = driver.findElement(By.xpath("//select[@name='city']"));
		Select select3 = new Select(txtCity);
		select3.selectByValue("3659");
		WebElement txtZipCode = driver.findElement(By.xpath("//input[@name='zipcode']"));
		txtZipCode.sendKeys("600059");
		WebElement btnSave = driver.findElement(By.xpath("(//button[text()='Save'])[3]"));
		btnSave.click();
		Thread.sleep(3000);
		WebElement paymentType = driver.findElement(By.id("payment_type"));
		Select select4 = new Select(paymentType);
		select4.selectByValue("debit_card");
		WebElement radiobtnVisa = driver.findElement(By.xpath("(//label[@class='custom-control-label font14 color20'])[2]"));
		radiobtnVisa.click();
		WebElement txtCardNo = driver.findElement(By.xpath("//input[@name='card_no']"));
		txtCardNo.sendKeys("5555555555552222");
		WebElement txtMonth = driver.findElement(By.id("month"));
		Select selectMonth = new Select(txtMonth);
		selectMonth.selectByValue("April");
		WebElement txtYear = driver.findElement(By.id("year"));
		Select selectYear = new Select(txtYear);
		selectYear.selectByValue("2028");
		WebElement txtCvv = driver.findElement(By.xpath("//input[@name='cvv']"));
		txtCvv.sendKeys("123");
		WebElement btnPlaceOrder = driver.findElement(By.xpath("//button[@id='placeOrder']"));
		btnPlaceOrder.click();
		Thread.sleep(8000);
		WebElement txtOrderId = driver.findElement(By.xpath("(//p[@class='font18 color20 fontSemiBold mb-0'])[1]"));
		orderNoText = txtOrderId.getText();
		String[] split = orderNoText.split(":");
		orderNoNewText = split[1].trim();
		System.out.println(orderNoNewText);
	}
	@Test(priority = 4, dependsOnMethods = {"placingOrderUsingWebUI"})
	public void getAllOrder() {
		
		
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logToken);
		
		List<Header> listHeader = new ArrayList<Header>();
		listHeader.add(h1);
		listHeader.add(h2);

		Headers headers = new Headers(listHeader);
		addHeader(headers);
		
		Response response = addRequest("GET", "https://omrbranch.com/api/getAllOrders");
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200,"Verify Status code");
		GetAllOrders_Output_Pojo getAllOrders_Output_Pojo = response.as(GetAllOrders_Output_Pojo.class);
		ArrayList<GetOrderData> getAllOrdersData = getAllOrders_Output_Pojo.getData();
		for (GetOrderData getOrderData : getAllOrdersData) {
			
			String order_no = getOrderData.getOrder_no();
			if (order_no.equals(orderNoNewText)) {
				
				int getOrderId = getOrderData.getId();
				orderIdText = String.valueOf(getOrderId);
				System.out.println(orderIdText);
			}
		}
		

	}

	@Test(priority = 5)
	public void cancelOrder() {

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logToken);
		Header h3 = new Header("Content-Type", "application/json");

		List<Header> listHeader = new ArrayList<Header>();
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);
		addHeader(headers);

		CancelOrder_Input_Pojo cancelOrder_Input_Pojo = new CancelOrder_Input_Pojo(orderIdText);
		addPayload(cancelOrder_Input_Pojo);

		Response response = addRequest("POST", "https://omrbranch.com/api/cancelOrder");
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200,"Verifying Cancel order Status code");
		CancelOrder_Output_Pojo cancelOrder_Output_Pojo = response.as(CancelOrder_Output_Pojo.class);
		String cancelOrderMessage = cancelOrder_Output_Pojo.getMessage();
		System.out.println("Order Message : " + cancelOrderMessage);
	}
	

}

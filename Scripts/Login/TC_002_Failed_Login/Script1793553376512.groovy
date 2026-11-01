import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import internal.GlobalVariable
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

String jdbcUrl = "jdbc:mysql://localhost:3306/db_testing_jpetstore"
String dbUser = "root"
String dbPassword = "" 
Connection conn = null
Statement stmt = null
ResultSet rs = null

try {
	Class.forName("com.mysql.cj.jdbc.Driver")
	conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)
	stmt = conn.createStatement()
	rs = stmt.executeQuery("SELECT username, password FROM Users WHERE test_case_id = 'TC_JPS_002'")
	
	if (rs.next()) {
		GlobalVariable.Username = rs.getString("username")
		GlobalVariable.Password = rs.getString("password")
	}
} catch (Exception e) {
	e.printStackTrace()
} finally {
	if (rs != null) rs.close()
	if (stmt != null) stmt.close()
	if (conn != null) conn.close()
}

delay_time =  10
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl(GlobalVariable.URL)

def btn_sign_in = findTestObject('Object Repository/Login/Btn_Sign_In')
WebUI.click(btn_sign_in)
def field_username =findTestObject('Object Repository/Login/field username')
def field_password= findTestObject('Object Repository/Login/field password')
def btn_signon = findTestObject('Object Repository/Login/btn_signon')
WebUI.waitForElementPresent(field_username, delay_time, FailureHandling.STOP_ON_FAILURE)
WebUI.waitForElementPresent(field_password, delay_time, FailureHandling.STOP_ON_FAILURE)
WebUI.setText(field_username,GlobalVariable.Username)
WebUI.setText(field_password, GlobalVariable.Password)
WebUI.click(btn_signon)
def failed_login =  findTestObject('Object Repository/Login/Alert failed Login')
WebUI.waitForElementPresent(failed_login, delay_time)
WebUI.verifyElementPresent(failed_login, delay_time)
WebUI.verifyElementText(failed_login, 'Invalid username or password. Signon failed.')
WebUI.closeBrowser()
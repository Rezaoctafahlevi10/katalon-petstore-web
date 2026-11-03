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

String missingProduct = ""

try {
	Class.forName("com.mysql.cj.jdbc.Driver")
	conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)
	stmt = conn.createStatement()
	
	rs = stmt.executeQuery("SELECT name_fish FROM Fish LIMIT 1")
	if (rs.next()) {
		String validFishName = rs.getString("name_fish")
		missingProduct = validFishName + "999_Invalid"
	}
	
	println("Product Name: " + missingProduct)
	
} catch (Exception e) {
	e.printStackTrace()
} finally {
	if (rs != null) rs.close()
	if (stmt != null) stmt.close()
	if (conn != null) conn.close()
}

int delay_time = 10
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl(GlobalVariable.URL)

def input_search = findTestObject('Object Repository/Product_Fish/input__keyword')
def btn_serach_product = findTestObject('Object Repository/Product_Fish/btn_searchProducts')
def catalog_table_fish = findTestObject('Object Repository/Product_Fish/Catalog_Table_Fish')

WebUI.setText(input_search, missingProduct)
WebUI.click(btn_serach_product)
WebUI.verifyElementPresent(catalog_table_fish, delay_time)
WebUI.closeBrowser()
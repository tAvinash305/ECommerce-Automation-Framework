package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + ".\\testData\\Opencart_loginData.xlsx";
		ExcelUtility excel = new ExcelUtility(path);
		
		int totalRows = excel.getRowCount("Sheet1");
		int totalCells = excel.getCellCount("Sheet1", 1);
		
		String loginData[][] = new String[totalRows][totalCells];
		
		for(int i=1; i<=totalRows; i++) {
			for(int j=0; j<totalCells; j++) {
				loginData[i-1][j] = excel.getCellData("Sheet1", i, j);
			}
		}
		
		return loginData;
	}
}
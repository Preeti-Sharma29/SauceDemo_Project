package DataProviders;

import org.testng.annotations.DataProvider;

public class DataProviders_class {
	
	
	@DataProvider(name="dp")
	public Object[][] Verify_user()
	{
		Object[][] data= {{"standard_user","secret_sauce"},
				            //{"visual_user","secret_sauce"}
		                 // {"error_user","secret_sauce"}
		                 };
		                  return data;
				                            };
		 
		

}

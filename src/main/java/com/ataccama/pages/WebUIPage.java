package com.ataccama.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ataccama.utils.ObjectRepoUtility;

public class WebUIPage {

	ObjectRepoUtility objRepo;

	public WebUIPage(WebDriver driver) {
		objRepo = new ObjectRepoUtility(driver);
	}

	public WebElement textBox(String texboxVal) {
		WebElement textBox =  objRepo.ConstructElementByCssTagAttributeValue("input", "name",
				texboxVal);
		return textBox;
	}

	public WebElement radioButton(String operationVal) {
		return objRepo.ConstructElementByCssTagAttributeValue("input", "value",
				operationVal);
	}

	public WebElement calculateButton() {
		return objRepo.ConstructElementByCss("[value=Calculate]");
	}

	public String Calculate(String val1, String val2, String operation){
    	objRepo.enterTextArgumentJS(textBox("val1"), val1);
    	objRepo.enterTextArgumentJS(textBox("val2"), val2);
    	radioButton(operation).click();
    	calculateButton().click();
    	return objRepo.getAttributeValue("[name=result]","value");

	}

}

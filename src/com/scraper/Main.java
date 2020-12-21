package com.scraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.NoSuchElementException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

    // global driver variable
    public static WebDriver driver = new FirefoxDriver();

    public static void main(String[] args) throws IOException {

        driver.get("https://github.com/trending?since=weekly%5D");

        // getting number of repos on the website
        // Each repo is wrapped under <article class="Box-row"></article>
        List<WebElement> elements = driver.findElements(By.className("Box-row"));
        int numRepos = elements.size();

        // create an empty array list with an initial capacity
        // every element in rows is a repo object (which is a List)
        List<List<String>> rows = new ArrayList<List<String>>(numRepos);

        // loop through the number of repos to
        // populate the rows List with the data
        for (int i = 1; i <= numRepos; i++) {
            List<String> repoData = Arrays.asList(getRepoName(i), getStars(i), getProgrammingLang(i));
            rows.add(repoData);
            System.out.println(" ");
        }

        System.out.println("Scraping is completed...\n");
        System.out.println("Writing to CSV now...\n");

        // Initialize new csv file
        FileWriter csvWriter = new FileWriter("github_trends.csv");

        // create the headers
        csvWriter.append("repo_name");
        csvWriter.append(",");
        csvWriter.append("number_of_stars");
        csvWriter.append(",");
        csvWriter.append("programming_language");
        csvWriter.append("\n");

        // populate rows
        for (List<String> rowData : rows) {
            String row = String.join(",", rowData);
            csvWriter.append(row);
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();

        // print a success message
        System.out.println("CSV File is completed.\n");
        System.out.println("Filename: github_trends.csv");


    }

    public static String getRepoName(int num) {
        System.out.println("Getting Repo Name...");
        // method:
        // use XPath: /html/body/div[4]/main/div[3]/div/div[2]/article[{num}]/h1/a
        // the repo name is located in the href attribute in the form of "domain/{user}/{repo_name}"
        // split the string and get the repo_name from the array
        try {
            String XPath = "/html/body/div[4]/main/div[3]/div/div[2]/article[" + num + "]/h1/a";
            // get href value
            String href = driver.findElement(By.xpath(XPath)).getAttribute("href");
            String[] textArr = href.split("/");
            String repoName = textArr[4];
            return repoName;
        } catch (NoSuchElementException error) {
            return "NA";
        }
    }

    public static String getStars(int num) {
        System.out.println("Getting Stars...");
        // method:
        // use XPath: /html/body/div[4]/main/div[3]/div/div[2]/article[{num}]/div[2]/span[3]
        // the number of stars is located in the innerText of the span tag in the form of "{num} stars today"
        // split the text and get the number of stars from the array
        try {
            String XPath = "/html/body/div[4]/main/div[3]/div/div[2]/article[" + num + "]/div[2]/span[3]";
            String[] textArr = driver.findElement(By.xpath(XPath)).getAttribute("innerText").split("\\s+");
            String numStars = textArr[1];
            return numStars;
        } catch (NoSuchElementException error) {
            return "NA";
        }
    }

    public static String getProgrammingLang(int num) {
        System.out.println("Getting Programming Lang...");
        // method:
        // use XPath: /html/body/div[4]/main/div[3]/div/div[2]/article[{num}]/div[2]/span[1]/span[2]
        // the programming language is located in the innerHTML of <span></span> tag
        try {
            String XPath = "/html/body/div[4]/main/div[3]/div/div[2]/article[" + num + "]/div[2]/span[1]/span[2]";
            String programmingLang = driver.findElement(By.xpath(XPath)).getAttribute("innerHTML");
            return programmingLang;
        } catch (NoSuchElementException error) {
            return "NA";
        }

    }


}

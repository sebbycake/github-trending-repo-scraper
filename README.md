# GitHub Trending Repositories Scraper

A web scraper to pull a list of trending repositories

CSV file is generated with the following headers: 

1. GitHub repo name
2. Number of stars
3. Programming language used

## About The Project

### Built With

* [IntelliJ](https://www.jetbrains.com/idea/) 
* [Java](https://www.java.com/en/download/)
* [Selenium](https://www.selenium.dev/p)


## Getting Started

### Prerequisites

* [Java](https://www.java.com/en/download/)
* [Selenium](https://www.selenium.dev/downloads/)
* [Mozilla Firefox](https://www.mozilla.org/en-US/firefox/new/)



### Installation

1. Install this project:

```
git clone https://github.com/sebbycake/github-trending-repo-scraper.git
```
Main Java source code is located at `src > com > scraper > Main.java`

2. Install Selenium Java dependencies [here](https://www.selenium.dev/downloads/).

3. Configure IntelliJ with Selenium by adding the Selenium's .jar files into intelliJ as external libraries:

    1. `File` -> `Project Structure` -> in a project setting tab look for `Modules` -> `Dependencies` -> Click on `'+'` Sign -> Select for JARs or directories.
    2. Select all Selenium `.jar` files from the directory and subdirectory `/lib`, where you have extracted after download.


### Usage

1. Open the files with IntelliJ

2. Run `Main.java`




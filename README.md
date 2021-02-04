# TestNGSelendroid

This repo is a starter `Appium` QA framework to automate tests for both `Android` and `iOS` Selendroid application. 
This guide will help you setup and run the testing environment.

# Getting Started

## Pre-Requisites 
The following tools are required to be downloaded and installed:

-   [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download) (Enterprise Edition if license is available)
-   [Android Studio](https://developer.android.com/studio) + Android Emulator with API Level 26 or higher.
-   [Xcode](https://developer.apple.com/xcode/) or download it directly from the **App Store**
-   [Java SE Development Kit 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
-   [Appium Desktop](http://appium.io/downloads.html)

## Setup

### Clone this repo:

```bash
git clone https://github.com/kinngsanjay/TestNGSelendroid.git
```

Then open the cloned repo with `IntelliJ`, the project will build and download all the `Maven` dependencies.

**To improve the performance of test execution on simulator:**

-   Run any android simulator say `Nexus_5X_API_26` from the AVD manager in android studio
-   Disable `Window animation scale`, `Transition animation scale`, and `Animator duration scale` in android
-   Follow the below steps to disable animation - 
    -   Make sure developer options in simulator are enabled. If they're not, go to Settings > About phone, then tap on Build number several times to enable it
    -   Go to Settings > Developer options, and scroll down to Window animation scale, Transition animation scale, and Animator duration scale
    -   Tap on each of the animation options and turn them off
 
### Run Tests

**To run the tests from the IDE:**

-   Download the apk
-   Update application.properties with the path to the app files (note, these changes shouldn't be committed)
-   Start `Appium` Server.
-   For `Android`, launch an emulator with API Level 26 or higher. For `iOS`, the simulator will launch automatically once you run the tests.
-   Open `com.selendroid.TestSuite.java` and press ^R or right click and press `Run 'Test'`   

**To run the tests from Maven command line:**

-   From the terminal, navigate to the project root directory
-   Run the following:

```bash
mvn test -Dplatform.name=<platform_name> -Dapp.path=<app_path>
```
-   `platform.name` should be either `Android` or `iOS`
-   `app.path` should be e.g. `/Users/some path/selendroid-test-app.apk`

# Major Libraries / Tools

| Category                           | Library/Tool      | Link                                                          |
|---------------------------------   |----------------   |------------------------------------------------------------   |
| QA Automation Framework            | Appium Java       | https://github.com/appium/java-client                         |
| Boilerplate Code Generation        | Lombok (see below) | https://projectlombok.org/                                   |
| Automate Build & Release           | Maven             | https://docs.fastlane.tools/actions/xcodebuild/               |
| Base Testing Framework             | TestNG            | http://testng.org/                                            |


## Boilerplate Code Generation Using Lombok

This project used [Lombok](https://projectlombok.org/) to automatically generate boilerplate code such as getter, setters, constructors, etc. It even includes a builder patter. Lombok is compile time only and is setup in Maven as `provided`. To install it for Eclipse follow the instructions [here](https://projectlombok.org/setup/eclipse). For Intellij the setup instructions can be found [here](https://projectlombok.org/setup/intellij)

## EditorConfig

To ensure editor settings are the same for all developers, the project contains a `.editorconfig` file to set editor config such as **indent**, **end of line character**. It's recommended to install the EditorConfig plugin for Eclipse [https://marketplace.eclipse.org/content/editorconfig-eclipse](https://marketplace.eclipse.org/content/editorconfig-eclipse)

## Best practices for using locators in automation project

Following is the sequence of priority for the most recommended locator in automation (1 being the highest), if properties of elements in the application are not dynamic in nature - 
1.  Accessibility ID
2.  Id
3.  xpath

If properties of elements are dynamic in nature, then following is the priority -
1.  xpath
2.  Accessibility Id

Note - Because we generally do not have accessibility ID and Id's updated for all the elements in the application, xpath should be used to maintain the locator details.

## Best practices for writing xpath
we should be very conscious in using xpath as a locator to make sure that we use it as a asset and it do not turn out to be a liability later.

We should always use `Relative Xpath` and shall never use `Absolute Xpath`.

`Absolute Xpath:` It uses Complete path from the Root Element to the desire element.

Example: /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.EditText

Note: Issue with the above xpath is :- 
-   If there is any minor change in the structure of page anywhere, it will break the xpath and execution will fail
-   It is tedious to maintain the absolute xpath's therefore is always a liability to correct before every run 

`Relative Xpath:` You can simply start by referencing the element you want and go from there.

Example: Above absolute xpath can be reduced to - 
//android.widget.FrameLayout[@resource-id='resource-id']//descendant::android.widget.EditText

Benefits of using relative xpath is - 
-   We do not refer element from the root element however we directly use the property of the element or its parent, child, descendant, anscestor or siblings
-   We do not have to change xpath every time there is a change on the page as relative xpath is not impacted mostly
-   We can utilize relative xpath for inspecting dynamic elements i.e. elements whose properties are changing on run time

Following are functions defined and can be utilized for creating the relative xpath's - 
-   `parent`
    -   //XCUIElementTypeButton[@name='nametag']//parent::XCUIElementTypeCollectionView
-   `child`
    -   //androidx.recyclerview.widget.RecyclerView[@resource-id='resource-id']//child::android.widget.FrameLayout
-   `following`
    -   //XCUIElementTypeButton[@name='nametag']//following::XCUIElementTypeCollectionView
-   `preceding`
    -   //android.widget.CheckBox[@resource-id='resource-id']//preceding::android.widget.EditText
-   `siblings`
    -   `following-sibling`
        -   //android.widget.TextView[@text='some text']//following-sibling::android.widget.TextView
    -   `preceding-sibling`
        -   //android.widget.TextView[@text='some text']//preceding-sibling::android.widget.TextView
-   `ancestors`
    -   //android.widget.FrameLayout[@resource-id='resource-id']//ancestor::android.widget.EditText
-   `descendants`
    -   //android.widget.FrameLayout[@resource-id='resource-id']//descendant::android.widget.EditText
-   `starts-with`
    -   //android.widget.EditText[ends-with(@text,'text')]
-   `ends-with`
    -   //android.widget.EditText[ends-with(@text,'text')]
-   `contains`
    -   //android.widget.EditText[contains(@text,'text')]
-   `AND and OR`
    -   //*[@type='type' OR @name='name']
    -   //input[@type='type' and @name='name']
-   `NOT`
    -   //UIAStaticText[not(contains(@name, 'name'))]

Note: We can use above functions - 
-   To make xpath more generic to be used at multiple places
-   If the properties of the element is not available, then you can refer its relative elements using functions mentioned above to reach the desired elements
-   We can parameterize the xpath and pass the text from feature file.
    -   Below is the code snippet - 
    
        public static final String ANDROID_SETTINGS_OPTION_XPATH = "//android.widget.TextView[contains(@text,''{0}'')]";
        
        optionLocator = MessageFormat.format(PersonalInformationElements.ANDROID_SETTINGS_OPTION_XPATH, option);  
 -  You can grow the chain to reach the desired elements using the relations multiple time
    
    //XCUIElementTypeStaticText[`contains`(@name,'name')]//`following-sibling`::XCUIElementTypeCollectionView//`child`:: XCUIElementTypeCell[1]//`descendant`::XCUIElementTypeStaticText[1]

Follow below link for detailed information on above - https://www.softwaretestingclass.com/complete-guide-on-xpath-in-selenium/

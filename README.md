# Short instruction how to run tests and generate allure report

## How to run tests
Use the following maven command for run UI tests:

    mvn clean install

Additional parameters:

- config.url - base url of app
- config.browser - browser name(chrome, firefox). chrome by default.
- config.language - language in which site will be tested(ru, en). ru by default.
- config.rp.integration - integration with RP. false by default 

## How to generate allure report

Use the following command for generation allure report:

    allure serve path_to_allure_report_folder
    
How to install allure see the following link:
https://docs.qameta.io/allure/#_installing_a_commandline
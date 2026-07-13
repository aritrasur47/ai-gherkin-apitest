# AI Gherkin API Test

[![CI](https://github.com/aritrasur47/ai-gherkin-apitest/actions/workflows/ci.yml/badge.svg)](https://github.com/aritrasur47/ai-gherkin-apitest/actions/workflows/ci.yml)

Cucumber + TestNG API test suite for the Place APIs (add/delete place)
against the public `rahulshettyacademy` practice API, using REST Assured
for HTTP calls and Allure for reporting.

## Prerequisites

- Java 17
- Maven 3.6+

## Setup

Clone the repo and let Maven pull dependencies (uses the config already
checked into `src/test/resources/global.properties`, no extra setup
needed):

```bash
git clone https://github.com/aritrasur47/ai-gherkin-apitest.git
cd ai-gherkin-apitest
mvn dependency:resolve
```

## Running the tests

Run the full suite (Cucumber scenarios + plain TestNG tests):

```bash
mvn test
```

This runs:
- `cucumber.Options.TestRunner` — executes all scenarios in
  `src/test/java/features/place_validations.feature`
- `tests.SpecBuilderTest` and `tests.UtilsTest` — standalone TestNG tests

## Output

- **Request/response log**: `target/logging.txt` — plain-text log of
  every add/delete API call made during the run (banner + request +
  response), truncated fresh at the start of each `mvn test` invocation.
- **Cucumber HTML report**: `target/cucumber-reports.html`
- **TestNG report**: `target/surefire-reports/index.html`
- **Allure results**: `target/allure-results/` — generate a viewable
  report with:

  ```bash
  mvn allure:serve
  ```

## Configuration

API base URL, key, and endpoints are in
`src/test/resources/global.properties`. Any property can be overridden
at runtime via a system property, e.g.:

```bash
mvn test -DbaseURI=https://example.com
```

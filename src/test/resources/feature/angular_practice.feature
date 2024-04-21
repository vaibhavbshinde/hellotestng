Feature: Creating the Post request for Angular
  I want to test the login page detail

  Scenario Outline: [POST] Login with valid user credential
    When User execute login request API (POST) using valid data
    Then Verify status code for api is <status>

    Examples:
    |status|
    | 200  |
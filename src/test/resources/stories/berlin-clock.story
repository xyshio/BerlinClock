Story: The Berlin Clock

Narrative:
    As a clock enthusiast
    I want to tell the time using the Berlin Clock
    So that I can increase the number of ways that I can read the time


Scenario: Checking time at 00:00:00
Given it is 00:00:00 in Berlin
When I look at Berlin-Clock
Then I should see
Y
OOOO
OOOO
OOOOOOOOOOO
OOOO

Scenario: Checking time at 13:17:01
Given it is 13:17:01 in Berlin
When I look at Berlin-Clock
Then I should see
O
RROO
RRRO
YYROOOOOOOO
YYOO

Scenario: Checking time at 23:59:59
Given it is 23:59:59 in Berlin
When I look at Berlin-Clock
Then I should see
O
RRRR
RRRO
YYRYYRYYRYY
YYYY

Scenario: Checking time at 24:00:00
!--TODO: Requirements specification review
!--Following scenario calls for requirements review.
!--Although the clock has ability to display such time, the time itself is invalid.
Given it is 24:00:00 in Berlin
When I look at Berlin-Clock
Then I should see
Y
RRRR
RRRR
OOOOOOOOOOO
OOOO
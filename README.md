# Online Test Management System

# Abstract:
This project is aimed at developing Online Test Management System for user. This is a web based application that can be accessed throughout the web. This system can be used to add Test, add Questions, assign Test to user &amp; user can give test . This is an integrated system that contains both the user component and the administration component. Using the Online Test Management System, a user can give only one test at a time and see questions whereas the admin can add and update test and also add questions to a specific test. The admin can also delete tests and the questions in them and assign the tests to a specific user.

# Assumptions:
1. It is assumed that the test is only a Multiple Choice Questions(MCQs) test.
2. It is assumed that every question has only four choices and only one of them is correct.
3. A single user can be assigned to a single test at a given point of time.

# Relationship:
Association exists between the User and Test classes as the user can give a test. The relationship is one - to - one relationship as only one user can give a test and one test is assigned to a single user. Composition exists between Test and Question classes as it represents a whole-part relationship where the part(question) has no
individual existence. The relationship is one - to - many relationship as a test can have many questions and many questions can be a part of one test.

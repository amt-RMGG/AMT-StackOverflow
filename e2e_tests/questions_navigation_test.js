Feature('questions navigation');

Scenario('test something', (I) => {
    I.amOnPage(':8080/stackunderflow');
    I.see('Welcome to StackUnderFlow!')
    I.click('Lists of questions')
    I.see("Welcome to FlowOverStack!")
    I.click("What's the meaning of life?")
    I.see("User_1")
    I.see("cannot find out :(")
});

Feature('askQuestion');

Scenario('test asking a question', (I) => {
    I.amOnPage(':8080/stackunderflow/askquestion');
    I.click('Poser une question');
    I.fillField('.title', 'tfTitle');
    I.fillField('.question', 'tfText');
    I.click('.bSubmitQuestion');
});

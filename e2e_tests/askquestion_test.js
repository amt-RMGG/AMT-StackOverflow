
Feature('askQuestion');

Scenario('test asking a question', ({I}) => {
    I.amOnPage(':8080/stackunderflow')
    I.click('Sign in')
    I.fillField('Nom d\'utilisateur', 'testUserName1')
    I.fillField( 'Nom', 'testName1')
    I.fillField( 'Prénom', 'testFirstName1')
    I.fillField( 'Email', 'testEmail1')
    I.fillField( 'Mot de passe', 'testPassword1')
    I.fillField( 'Confirmer le mot de passe', 'testPassword1')
    I.click('input[type="submit"]')
    I.click('Poser une question');
    I.fillField('input[type="text"]', 'tfTitle');
    I.fillField('.text', 'tfText');
    I.click('.bSubmitQuestion');
});


Feature('askQuestion');

Scenario('test asking a question', ({I}) => {
    I.amOnPage(':8080/stackunderflow')
    I.click('S\'enregistrer')
    I.fillField('Nom d\'utilisateur', 'testUserName1')
    I.fillField( 'Nom', 'testName1')
    I.fillField( 'Prénom', 'testFirstName1')
    I.fillField( 'Email', 'testEmail1')
    I.fillField( 'Mot de passe', 'testPassword1')
    I.fillField( 'Confirmer le mot de passe', 'testPassword1')
    I.click('input[type="submit"]')
    I.click('Poser une question');
    I.fillField('input[type="text"]', 'question title');
    I.executeScript('tinyMCE.activeEditor.setContent("question text");')
    I.click('button[id="bSubmitQuestion"]');
    I.click('Voir plus')
    I.see('question text')
});

Feature('answerToQuestion');

Scenario('test answering to a question', ({I}) => {
   
    I.amOnPage(':8080/stackunderflow/home')
    I.click('Login')
    I.fillField('Nom d\'utilisateur', 'testUserName1')
    I.fillField( 'Mot de passe', 'testPassword1')
    I.click('input[type="submit"]')
    

    I.amOnPage(':8080/stackunderflow/questions');
    I.click('Voir plus');
    I.see('Réponse(s)');
    I.executeScript('tinyMCE.activeEditor.setContent("this is an answer");')
    I.click('button[id="bSubmitQuestion"]')
    I.seeCurrentUrlEquals('http://localhost:8080/stackunderflow/questions')
    I.click('Voir plus')
    I.see("this is an answer")
    
});
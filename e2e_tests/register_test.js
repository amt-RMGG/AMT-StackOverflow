Feature('register');

Scenario('register to the site', ({ I }) => {
    I.amOnPage(':8080/stackunderflow')
    I.click('Sign in')
    I.seeCurrentUrlEquals('http://localhost:8080/stackunderflow/register')

    I.fillField('Nom d\'utilisateur', 'testUserName')
    I.fillField( 'Nom', 'testName')
    I.fillField( 'Prénom', 'testFirstName')
    I.fillField( 'Email', 'testEmail')
    I.fillField( 'Mot de passe', 'testPassword')
    I.fillField( 'Confirmer le mot de passe', 'testPassword')
    I.click('input[type="submit"]')
    I.seeCurrentUrlEquals('http://localhost:8080/stackunderflow/questions')
    I.click('testFirstName testName')
    I.seeCurrentUrlEquals('http://localhost:8080/stackunderflow/user')
    I.see('User profile')

    I.click('Poser une question')
    I.seeCurrentUrlEquals('http://localhost:8080/stackunderflow/askQuestion')
    I.see('Title')

    I.click('Déconnexion')
    I.seeCurrentUrlEquals('http://localhost:8080/stackunderflow/home')

    I.click('Login')
    I.fillField('Nom d\'utilisateur', 'testUserName')
    I.fillField( 'Mot de passe', 'testPassword')
    I.wait(10)
});

/*
Scenario('register to the site', ({ I }) => {
    I.seeCurrentUrlEquals('localhost:8080/stackunderflow')
    I.click('Sign in')
    I.fillField('Nom d\'utilisateur', 'testUserName')
    I.fillField( 'Nom', 'testName')
    I.fillField( 'Prénom', 'testFirstName')
    I.fillField( 'Email', 'testEmail')
    I.fillField( 'Mot de passe', 'testPassword')
    I.fillField( 'Confirmer le mot de passe', 'testPassword')
    I.click('S\'enregistrer')
    I.click('testFirstName testName')
    I.seeCurrentUrlEquals('localhost:8080/stackunderflow/user')
    I.see('User profile')
});
*/
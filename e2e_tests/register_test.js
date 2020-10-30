Feature('register');

Scenario('register to the site', ({ I }) => {
    I.amOnPage(':8080/stackunderflow')
    I.click('Sign in')
    I.seeCurrentUrlEquals('http://localhost:8080/stackunderflow/register')

    //Register
    I.fillField('Nom d\'utilisateur', 'testUserName')
    I.fillField( 'Nom', 'testName')
    I.fillField( 'Prénom', 'testFirstName')
    I.fillField( 'Email', 'testEmail')
    I.fillField( 'Mot de passe', 'testPassword')
    I.fillField( 'Confirmer le mot de passe', 'testPassword')
    I.click('input[type="submit"]')
    I.seeCurrentUrlEquals('http://localhost:8080/stackunderflow/questions')

    //Go to profile page
    I.click('testFirstName testName')
    I.seeCurrentUrlEquals('http://localhost:8080/stackunderflow/user?username=testUserName')
    I.see('User profile')

    //go to question creation page
    I.click('Poser une question')
    I.seeCurrentUrlEquals('http://localhost:8080/stackunderflow/askQuestion')
    I.see('Title')

    //Disconnect
    I.click('Déconnexion')
    I.seeCurrentUrlEquals('http://localhost:8080/stackunderflow/home')

    //log in again with created account
    I.click('Login')
    I.fillField('Nom d\'utilisateur', 'testUserName')
    I.fillField( 'Mot de passe', 'testPassword')
    I.click('input[type="submit"]')
    I.seeCurrentUrlEquals('http://localhost:8080/stackunderflow/questions')
    I.see('Liste des questions')

    //Try incorrect credentials
    I.click('Déconnexion')
    I.click('Login')
    I.fillField('Nom d\'utilisateur', 'wrongName')
    I.fillField( 'Mot de passe', 'testPassword')
    I.click('input[type="submit"]')
    I.seeCurrentUrlEquals('http://localhost:8080/stackunderflow/login')
    I.fillField('Nom d\'utilisateur', 'testUserName')
    I.fillField( 'Mot de passe', 'wrongPassword')
    I.click('input[type="submit"]')
    I.seeCurrentUrlEquals('http://localhost:8080/stackunderflow/login')

});

Feature('update profile infos');

Scenario('update my profile infos', ({ I }) => {

    I.amOnPage(':8080/stackunderflow')

    //log in
    I.click('Login')
    I.fillField('Nom d\'utilisateur', 'testUserName')
    I.fillField( 'Mot de passe', 'testPassword')
    I.click('input[type="submit"]')
    I.seeCurrentUrlEquals('http://localhost:8080/stackunderflow/questions')

    //visit profile
    I.click('testFirstName testName')
    I.seeCurrentUrlEquals('http://localhost:8080/stackunderflow/user?username=testUserName')
    I.see('User profile')
    I.see('testFirstName testName')
    I.see('Username : testUserName')
    I.see('Email : testEmail')

    //update info
    I.click('edit')
    I.fillField('#fname', 'updatedFirstName')
    I.fillField('#lname', 'updatedName')
    I.fillField('#email', 'updatedEmail')
    I.click('Confirm')

    //Check results
    I.seeCurrentUrlEquals('http://localhost:8080/stackunderflow/user?username=testUserName')
    I.dontSee('testFirstName testName')
    I.dontSee('Username : testUserName')
    I.see('Email : testEmail')

    I.see('updatedFirstName updatedName')
    I.see('Username : testUserName')
    I.see('Email : updatedEmail')


    //check that the edit button is not visible if i am on another profile
    
    

});

Feature('register');

Scenario('test registering', (I) => {
    I.amOnPage(':8080/stackunderflow/register');
    I.click('Login');
    I.fillField('.input-register-username', 'testUsername');
    I.fillField('.input-register-password', 'pass');
    I.fillField('.input-register-confirm', 'pass');
    I.click('.btn-register');
    I.see('Registration successful');

    I.amOnPage(':8080/stackunderflow/question');
    I.click('Login');
    I.fillField('.input-register-username', 'testUsername');
    I.fillField('.input-register-password', 'pass');
    I.fillField('.input-register-confirm', 'wrongpass');
    I.click('.btn-register');
    I.see('Error : Passwords do not match');
});

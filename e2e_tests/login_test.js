Feature('login');

Scenario('test something', (I) => {
    I.amOnPage(':8080/stackunderflow');
    I.click('Login');
    I.fillField('.input-register-username', 'testUsername');
    I.fillField('.input-register-password', 'pass');
    I.fillField('.input-register-confirm', 'pass');
    I.click('.btn-register');
    I.see('Registration successful');
});

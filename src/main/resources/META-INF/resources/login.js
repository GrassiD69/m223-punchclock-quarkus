let action = "";
function init() {

    const URL = 'http://localhost:8080';

    loginForm = document.getElementById("loginForm");
    const username = document.getElementById("uname");
    const password = document.getElementById("psw");

    function submitLogin() {
        let strings =  {username: username.value, passwort: password.value};
        tryLogin(strings);
    }

    document.getElementById("loginbtn").onclick = function() {
        action = "login";
        submitLogin();
    }

    document.getElementById("registerbtn").onclick = function() {
        action = "register";
        submitLogin();
    }
   


    const tryLogin = (strings) => {
    
        fetch(`${URL}/user/${action}`, {
            method: 'POST'
            ,headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(strings)
        }).then(function(response) {
            if(action = 'login'){
                if(response.status != 200) {
                    //TODO error handling
                }else {
                    response.text().then((token) => {
                        localStorage.setItem("token", token);
                        console.log("token");
                        location.href = `${URL}/index.html`
                    })
                }
            }
            else{
                location.href = `${URL}/login.html`
            }
            
        });
    };
}
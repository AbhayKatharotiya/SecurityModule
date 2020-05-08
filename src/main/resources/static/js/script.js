function signup() {
    var user = {};
    user["name"] = document.getElementById('name').value;
    user["email"] = document.getElementById("email").value;
    user["contactNo"] = document.getElementById("contactNo").value;
    user['address'] = document.getElementById("address").value;
    user["password"] = document.getElementById("password").value;
    var req = new XMLHttpRequest();
    var json = JSON.stringify(user);
    req.open('POST', 'http://localhost:8080/signup');
    req.setRequestHeader('Content-Type', 'application/json');
    req.send(json);
    req.onload = function () {
        if (this.status === 200) {
            console.log(this.responseText);
            document.getElementById('message').innerHTML = this.responseText;
        } else {
            console.log(this.status);
            var json = JSON.parse(this.responseText);
            if (req.status === 400)
                document.getElementById('message').innerHTML = json['errors'][0]['defaultMessage'];
            else
                document.getElementById('message').innerHTML = "Email must be unique";
        }
    }
}

function login() {
    var loginDetails = {};
    loginDetails["username"] = document.getElementById("username").value;
    loginDetails["password"] = document.getElementById("password").value;
    var json = JSON.stringify(loginDetails);
    var request = new XMLHttpRequest();
    request.open('POST' , 'http://localhost:8080/login');
    request.setRequestHeader('Content-type','application/json');
    request.send(json);
    request.onload = function () {
        if (this.status === 200){
            window.location.href = "http://localhost:8080/static/index.html";
        }else{
            var json = JSON.parse(this.responseText);
            if (this.status === 400)
                document.getElementById('message').innerHTML = json['error'][0]['defaultMessage'];
            else
                document.getElementById('message').innerHTML = json['message'];
        }
    }
}

function checkAuthentication(){
    var request = new XMLHttpRequest();
    request.open('GET', 'http://localhost:8080/');
    request.send();
    request.onload = function () {
        if (this.status === 200){
            document.getElementById('message').innerHTML = "Hello there you are logged in...";
        }else
            window.location.href = "http://localhost:8080/static/login.html";
    }
}

function logout() {
    var request = new XMLHttpRequest();
    request.open('GET', 'http://localhost:8080/logout');
    request.send();
    request.onload = function () {
        window.location.href = "http://localhost:8080/static/login.html";
    }
}
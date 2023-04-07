const form = document.querySelector("form");
const forgotPassword = document.querySelector("#forgot-password");
const signUp = document.querySelector("#sign-up");

form.addEventListener("submit", function(event) {
  event.preventDefault();})

  const username = document.querySelector("#username").value;
  const password = document.querySelector("#password").value;

  console.log("Username:", username);
  console.log("Password:", password);


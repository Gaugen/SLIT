<?php
//hentet fra w3 schools
if (isset($_REQUEST['email']))

//hvis epost er fylt ut, send epost
  {
  //send epost
  if (empty($_GET["email"])){
      $emailErr = "email is required";
  } else {
    $email = test_input($_GET['email']);
  }
 
  if (empty($_GET["email"])){
      $emailErr = "email is required";
  } else {
    $email = test_input($_GET['email']);
  }
  if (empty($_GET["email"])){
      $emailErr = "email is required";
  } else {
    $email = test_input($_GET['email']);
  }
  $subject = $_GET['subject'] ;
  $message = $_GET['message'] ;
  mail($email, $subject, $message);
  echo "Mailen har blitt sendt";
  }
else

//hvis epost ikke er fylt ut, vis formen
  {
  echo "<form method='post' action='mail.php'>
  Email: <input name='email' type='text'><br>
  Subject: <input name='subject' type='text'><br>
  Message:<br>
  <textarea name='message' rows='15' cols='40'>";
  }
  
?>

// define variables and set to empty values
$nameErr = $emailErr = $genderErr = $websiteErr = "";
$name = $email = $gender = $comment = $website = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
  if (empty($_POST["name"])) {
    $nameErr = "Name is required";
  } else {
    $name = test_input($_POST["name"]);
  }

  if (empty($_POST["email"])) {
    $emailErr = "Email is required";
  } else {
    $email = test_input($_POST["email"]);
  }

  if (empty($_POST["website"])) {
    $website = "";
  } else {
    $website = test_input($_POST["website"]);
  }

  if (empty($_POST["comment"])) {
    $comment = "";
  } else {
    $comment = test_input($_POST["comment"]);
  }

  if (empty($_POST["gender"])) {
    $genderErr = "Gender is required";
  } else {
    $gender = test_input($_POST["gender"]);
  }
}
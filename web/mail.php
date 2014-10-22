<?php
$myServer = "localhost";
$myUser = "tomee";
$myPass = "tomee";
$myDB = "examples"; 
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
 
    if (empty($_GET["subject"])){
        $subjectErr = "subject is required";
    } else {
      $subject = test_input($_GET['subject']);
    }
    if (empty($_GET["message"])){
       $messageErr = "message is required";
    } else {
      $message = test_input($_GET['message']);
    }
    
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
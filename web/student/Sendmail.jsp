<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Email</title>
    </head>
    <body>
        <h1>Email</h1>
    <form method="POST" action="/SLIT/EmailServlet">
    <label for="to">To:</label><input id="to" name="to" type="text"/><br/>
    <label for="subject">Subject:</label><input id="subject" name="subject" type="text"/><br/>
    <textarea name="body" id="body" cols="60" rows="15"></textarea><br/>
    <input type="submit" value="Send"/>
        </form>
    </body>
</html>
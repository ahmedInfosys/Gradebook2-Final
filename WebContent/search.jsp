<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Student fields</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<form action="Gradebook.html" method="get">
<div class="form-group col-xs-5">
 <label for="sel1">Search for Assignments</label>
		<select class="form-control" id="studentID" name="studentID">
			${IDs}
		</select>
		<select class="form-control" id="types" name="types">
			${types}
		</select>
		<button type="submit" class="btn btn-default" formaction="search_results">Search</button>
		<a href="http://localhost:8082/GradeBook2/Gradebook.html"><button type="submit" class="btn btn-default" >Add</button></a>
		<h5>Average: ${average}</h5>
		<h5>Max grade: ${max}</h5>
		<h5>Min grade: ${min}</h5>
		
</div>
</form>
<form action="All_Assignment" method="post">
	${table}

</form>
</body>

</html>
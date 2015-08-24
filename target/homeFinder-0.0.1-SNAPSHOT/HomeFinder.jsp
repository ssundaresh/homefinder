<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title> Home Finder   </title>
		<link href="style.css" rel="stylesheet" type="text/css">
	</head>	
	<body>		
	<form action="HomeFinder" class="basic-grey">
    <h1>Craigslist Rental Search Alerts
        <span>Please fill all the texts in the fields.</span>
    </h1>
    <label>
        <span>Phone Number :</span>
        <input id="phone_num_to" type="text" name="phone_num_to" placeholder="(555)555-5555" />
    </label>
    
    <label>
        <span>Max Rent :</span>
        <input id="maxRent" type="text" name="maxRent" placeholder="Maximum rent per month (USD)" />
    </label>
    
     <label>
        <span>Min Bedrooms :</span>
        <input id="minBedrooms" type="text" name="minBedrooms" placeholder="Minimum number of bedrooms" />
    </label>

     <label>
        <span>Notification Frequency :</span><select name="selection">
        <option value="86400">Once a day</option>
        <option value="3600">Once every hour</option>
        <option value="0">Call me now</option>
        </select>
    </label>
     <label>
        <span>&nbsp;</span> 
        <input type="submit" class="button" value="Send" /> 
    </label>    
</form>
	</body>	
</html>
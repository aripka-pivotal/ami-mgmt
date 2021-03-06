<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<%@ page session="false" %>
<html class="no-js" lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Pivotal CF Workshop | AMI Assignment </title>
    <link rel="stylesheet" href="resources/css/foundation.css" />
    <script src="resources/js/modernizr.js"></script>
  </head>
  <body>

    <nav class="top-bar" data-topbar>
      <ul class="title-area">
        <li class="name">
          <h1><a href="./">Pivotal CF</a></h1>
        </li>
      </ul>
            <section class="top-bar-section">
        <ul class="right">
          <li><a href="./amiLookup">AMI Lookup</a></li>
          <li><a href="addAttendee">Add Attendee</a></li>
        </ul>
      </section>

    </nav>

    <div class="row">
      <div class="large-12 columns">
        <h3>All Attendees</h3>
        <hr/>
      </div>
    </div>
	
	<div class="row">
      <div class="large-12 columns">
        <table>
          <tbody>
          	<tr>
          	  <td>Unassigned AMI count</td>
          	  <td>${amiCount}</td>
          	</tr>
           </tbody>
      </table>
      </div>
    </div>
    	
    <div class="row">
      <div class="large-12 columns">
        <table>
          <thead>
            <tr>
              <th>First Name</th>
              <th>Last Name</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${attendees}" var="attendee">
            <tr>
              <td>${attendee.firstName}</a></td>
              <td>${attendee.lastName}</td>
            </tr>
            </c:forEach>
           </tbody>
        </table>
      </div>
    </div>
	<div class="row">
      <div class="large-12 columns">
        <a href="amiAssign" class="medium button">Assign Amis</a>
      </div>
    </div>
    <script src="resources/js/jquery.js"></script>
    <script src="resources/js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>

    </body>
</html>

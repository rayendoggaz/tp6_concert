<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Genre</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <%@include file="header.jsp" %>
    <div class="container">
        <div class="card">
            <div class="card-header">
                Ajouter un Genre
            </div>
            <div class="card-body">
                <form action="saveGenre" method="post">
                    <div class="form-group">
                        <label for="nom" class="control-label">Nom Genre :</label>
                        <input type="text" name="nom" class="form-control" required />
                    </div>
                    <div class="form-group">
                        <label for="dateCreation" class="control-label">Date de création :</label>
                        <input type="date" name="dateCreation" class="form-control" required />
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Ajouter</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
